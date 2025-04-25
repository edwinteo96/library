package com.example.library;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.contains;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc  // Enables MockMvc
public class ControllerTest {

    @Autowired
    private MockMvc mockMvc;  // Injects MockMvc

    @Test
    public void testGetBorrowerById() throws Exception {
        mockMvc.perform(get("/api/borrower/1"))  // Simulate GET request
                .andExpect(status().isOk())    // Assert HTTP 200
                .andExpect(jsonPath("$.email").value("edwinteo96@gmail.com"));  // Validate JSON response
    }

    @Test
    public void testCreateBorrower() throws Exception {
        String userJson = "{\"name\":\"Bob\", \"email\":\"bob@example.com\"}";

        mockMvc.perform(post("/api/borrower/create")
                        .contentType("application/json")
                        .content(userJson))
                .andExpect(status().isCreated());  // Assert HTTP 201
    }

    @Test
    public void testCreateBook() throws Exception {
        String userJson = "{\"isbn\":\"12366767676\", \"author\":\"midaz\" , \"title\":\"java book\"}";

        mockMvc.perform(post("/api/books/create")
                        .contentType("application/json")
                        .content(userJson))
                .andExpect(status().isCreated());  // Assert HTTP 201
    }

    @Test
    public void testGetBooksById() throws Exception {
        mockMvc.perform(get("/api/books"))  // Simulate GET request
                .andExpect(status().isOk())    // Assert HTTP 200
                .andExpect(jsonPath("$[?(@.isbn == '12366767676')].title", contains("java book"))); // Loop for isbn = 12366767676 and then match the title // Validate JSON response
    }
}