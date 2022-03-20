package com.bitirmeprojesibugrayus.core.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApiResponse<T> implements Serializable {

    private T data;
    private Date responseDate;
    private boolean isSuccess;
    private String messages;

    public ApiResponse(T data, boolean isSuccess) {
        this.data = data;
        this.isSuccess = isSuccess;
        responseDate = new Date();
    }

    public static <T> ApiResponse<T> of(T t) {
        return new ApiResponse<>(t, true);
    }

    public static <T> ApiResponse<T> error(T t) {
        return new ApiResponse<>(t, false);
    }

    public static <T> ApiResponse<T> empty() {
        return new ApiResponse<>(null, true);
    }

    public void setMessages(String messages) {
        this.messages = messages;
    }
}
