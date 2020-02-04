package com.mckesson.controllers;

import com.mckesson.entities.Person;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Calendar;
import java.util.Date;

import static org.hamcrest.Matchers.containsString;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class HelloRestControllerTest {

    @Autowired
    MockMvc mvc;

    @Test
    public void testHelloRegistration() {
        HelloRestController hrc = new HelloRestController();
        Person p = hrc.helloRegistration("rob", getTestDob(10), "rob.wing@galvanize.com");
        assertEquals(p.getAge(), 10);
    }

    @Test
    public void testHelloRegistrationPost() {
        Person p = new Person();
        HelloRestController hrc = new HelloRestController();
        assertEquals(p, hrc.helloRegistration(p));
    }

    @Test
    void helloRegGetReturnsPerson() throws Exception {
        String url = "/hello?name=rob&birthDate=11/16/1962&email=rob.wing@galvanize.com";
        mvc.perform(get(url))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("rob.wing@galvanize.com")))
                .andExpect(jsonPath("$.age").value(57));
    }

    @Test
    void helloRegPostReturnsPerson() throws Exception {
        String json = "{\"name\":\"Rob\",\"birthDate\":\"1962-11-16\",\"email\":\"rob.wing@galvanize.com\"}";
        mvc.perform(post("/hello")
                .content(json)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().string(containsString("rob.wing@galvanize.com")))
                .andExpect(jsonPath("$.age").value(57));
    }

    private Date getTestDob(int years) {
        Calendar ci = Calendar.getInstance();
        ci.add(Calendar.YEAR, -years);
        return ci.getTime();
    }
}