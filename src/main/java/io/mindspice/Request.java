package io.mindspice;

import io.mindspice.endpoints.Endpoint;


public class Request {
    public final String endpoint;
    public final byte[] data;

    public Request(Endpoint endpoint, byte[] data) {
        this.endpoint = endpoint.getPath();
        this.data = data;
    }

    public Request(String endpoint, byte[] data) {
        this.endpoint = endpoint;
        this.data = data;
    }
}
