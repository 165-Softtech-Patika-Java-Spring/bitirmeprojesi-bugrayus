package com.bitirmeprojesibugrayus;

import com.bitirmeprojesibugrayus.core.model.ApiResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.test.web.servlet.MvcResult;

import java.io.UnsupportedEncodingException;

public class BaseTest {

    protected ObjectMapper objectMapper;

    protected boolean isSuccess(MvcResult result) throws com.fasterxml.jackson.core.JsonProcessingException, UnsupportedEncodingException {
        ApiResponse restResponse = getRestResponse(result);

        return isSuccess(restResponse);
    }

    protected ApiResponse getRestResponse(MvcResult result) throws com.fasterxml.jackson.core.JsonProcessingException, UnsupportedEncodingException {
        return objectMapper.readValue(result.getResponse().getContentAsString(), ApiResponse.class);
    }

    private boolean isSuccess(ApiResponse restResponse) {
        return restResponse.isSuccess();
    }

}