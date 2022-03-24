package com.bitirmeprojesibugrayus.controller;

import com.bitirmeprojesibugrayus.BaseTest;
import com.bitirmeprojesibugrayus.BitirmeprojesiBugrayusApplication;
import com.bitirmeprojesibugrayus.config.SQLTestProfileJPAConfig;
import com.bitirmeprojesibugrayus.model.request.CreateCategoryRequestModel;
import com.bitirmeprojesibugrayus.model.request.UpdateCategoryRequestModel;
import com.bitirmeprojesibugrayus.model.response.CategoryResponseModel;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
@SpringBootTest(classes = {BitirmeprojesiBugrayusApplication.class, SQLTestProfileJPAConfig.class})
class CategoryControllerTest extends BaseTest {

    private static final String BASE_PATH = "/category";


    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext context;

    @BeforeEach
    void setUp() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.context).build();
        this.objectMapper = new ObjectMapper().registerModule(new JavaTimeModule());
    }

    @Test
    void createCategory() throws Exception {
        CreateCategoryRequestModel categoryResponseModel = CreateCategoryRequestModel.builder()
                .name("erhdem")
                .taxPercent(BigDecimal.ONE)
                .build();

        String content = objectMapper.writeValueAsString(categoryResponseModel);

        MvcResult result = mockMvc.perform(
                post(BASE_PATH).content(content).contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk()).andReturn();

        assertTrue(isSuccess(result));
    }

    @Test
    void findAllCategories() throws Exception{
        MvcResult result = mockMvc.perform(
                get(BASE_PATH).content("").contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk()).andReturn();

        boolean isSuccess = isSuccess(result);

        assertTrue(isSuccess);
    }

    @Test
    void findCategoryById() throws Exception{
        MvcResult result = mockMvc.perform(
                get(BASE_PATH + "/1").content("1L").contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk()).andReturn();

        boolean isSuccess = isSuccess(result);

        assertTrue(isSuccess);
    }

    @Test
    void findCategoryDetails() throws Exception{

    }

    @Test
    void updateCategory() throws Exception{
        UpdateCategoryRequestModel updateCategoryRequestModel = new UpdateCategoryRequestModel();
        updateCategoryRequestModel.setId(2052L);
        updateCategoryRequestModel.setName("test2");
        updateCategoryRequestModel.setTaxPercent(BigDecimal.ONE);

        String content = objectMapper.writeValueAsString(updateCategoryRequestModel);

        MvcResult result = mockMvc.perform(
                put(BASE_PATH).content(content).contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk()).andReturn();

        boolean isSuccess = isSuccess(result);

        assertTrue(isSuccess);
    }

    @Test
    void deleteCategory() throws Exception{
        MvcResult result = mockMvc.perform(
                delete(BASE_PATH + "/2202").content("2202").contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk()).andReturn();

        boolean isSuccess = isSuccess(result);

        assertTrue(isSuccess);
    }
}