package io.mindspice.databaseservice.client;

import org.apache.http.HttpHeaders;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.TrustAllStrategy;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.entity.ContentType;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.ssl.SSLContexts;
import org.apache.http.util.EntityUtils;

import javax.net.ssl.SSLContext;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;


public class DBServiceClient {
    private final CloseableHttpClient client;
    private final String address;

    public  DBServiceClient(String addr, String username, String password)
            throws NoSuchAlgorithmException, KeyManagementException, KeyStoreException {

        address = addr;

        PoolingHttpClientConnectionManager poolingConnManager = new PoolingHttpClientConnectionManager();
        poolingConnManager.setMaxTotal(200);
        poolingConnManager.setDefaultMaxPerRoute(200);


        KeyStore keystore = KeyStore.getInstance("PKCS12");

        CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
        credentialsProvider.setCredentials(AuthScope.ANY, new UsernamePasswordCredentials(username, password));

        RequestConfig requestConfig = RequestConfig.custom()
                .setConnectTimeout(60_000)
                .setConnectionRequestTimeout(60_000)
                .setSocketTimeout(60_000).build();

        SSLContext sslContext = SSLContexts.custom()
                .loadTrustMaterial(TrustAllStrategy.INSTANCE)
                .build();

        client = HttpClients.custom()
                .setConnectionManager(poolingConnManager)
                .setDefaultCredentialsProvider(credentialsProvider)
                .setDefaultRequestConfig(requestConfig)
                .setSSLContext(sslContext)
                .setSSLHostnameVerifier(NoopHostnameVerifier.INSTANCE)
                .build();
    }



    public  String getAddress() {
        return address;
    }

    public  byte[] makeRequest(Request req) throws IOException {
        URI uri = null;
        try {
            uri = new URI(address + req.endpoint);
        } catch (URISyntaxException e) {
            throw new RuntimeException("Invalid URI: " + uri);
        }
        var httpPost = new HttpPost(uri);
        httpPost.setEntity(new ByteArrayEntity(req.data));
        httpPost.setHeader(HttpHeaders.CONTENT_TYPE, ContentType.APPLICATION_JSON.getMimeType());

        try (CloseableHttpResponse response = client.execute(httpPost)) {
            InputStream content = response.getEntity().getContent();
            byte[] bytes = content.readAllBytes();
            EntityUtils.consume(response.getEntity());
            return bytes;
        }
    }

}