package com.cmcc.util;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ApiResponse<T> {
    private int code = 200;
    private String message;
    private T data;

    public static ApiResponse build() {
        return new ApiResponse();
    }

    public ApiResponse success() {
        code = 200;
        return this;
    }

    public ApiResponse error(String message) {
        code = 500;
        this.message = message;
        return this;
    }

    public ApiResponse error(int code, String message) {
        this.code = code;
        this.message = message;
        return this;
    }

    public ApiResponse paramError(String message) {
        code = 500;
        this.message = message;
        return this;
    }

    public ApiResponse tokenError(String message) {
        code = 401;
        this.message = message;
        return this;
    }

    public ApiResponse success(T obj) {
        this.data = obj;
        return this;
    }

}
