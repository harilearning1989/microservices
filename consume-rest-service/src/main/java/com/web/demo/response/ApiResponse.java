package com.web.demo.response;

public class ApiResponse {
    private String data;

    public ApiResponse() {
    }

    public ApiResponse(String data) {
        this.data = data;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "ApiResponse{" +
                "data='" + data + '\'' +
                '}';
    }
}

