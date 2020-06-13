package com.lxisoft.web.rest;

import com.lxisoft.MockTestApp;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
/**
 * Test class for the MocktestControllerResource REST controller.
 *
 * @see MocktestControllerResource
 */
@SpringBootTest(classes = MockTestApp.class)
public class MocktestControllerResourceIT {

    private MockMvc restMockMvc;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        MocktestControllerResource mocktestControllerResource = new MocktestControllerResource();
        restMockMvc = MockMvcBuilders
            .standaloneSetup(mocktestControllerResource)
            .build();
    }

    /**
     * Test getRegistrationDetails
     */
    @Test
    public void testGetRegistrationDetails() throws Exception {
        restMockMvc.perform(get("/api/mocktest-controller/get-registration-details"))
            .andExpect(status().isOk());
    }
}
