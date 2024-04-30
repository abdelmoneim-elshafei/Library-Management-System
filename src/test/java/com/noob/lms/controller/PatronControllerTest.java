package com.noob.lms.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.noob.lms.entity.Patron;
import com.noob.lms.service.PatronService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@WebMvcTest(PatronController.class)
class PatronControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private PatronService patronService;
    @Autowired
    private ObjectMapper objectMapper;



    @Test
    public void testGetAllPatrons() throws Exception {

        Patron patron1 = getTestPatron();
        Patron patron2 = getTestPatron();
        List<Patron> patrons = new ArrayList<>();
        patrons.add(patron1);
        patrons.add(patron2);

        Mockito.when(patronService.getPatrons()).thenReturn(patrons);

        mockMvc.perform(get("/api/patrons")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.length()").value(2));
    }

    @Test
    public void testGetPatronById() throws Exception {
        Patron patron = getTestPatron();
        Long patronId = 1L;

        Mockito.when(patronService.getPatronById(patronId)).thenReturn(patron);

        mockMvc.perform(get("/api/patrons/{id}", patronId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.email").value(patron.getEmail()));
    }

    @Test
    public void testAddPatron() throws Exception {
        Patron patron = getTestPatron();

        Mockito.when(patronService.addPatron(any(Patron.class))).thenReturn(patron);

        mockMvc.perform(post("/api/patrons")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(patron)))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.name").value(patron.getName()));
    }
    @Test
    public void testUpdatePatron() throws Exception {
        Patron updatedPatron = getTestPatron();
        Long patronId = 1L;

        Mockito.when(patronService.updatePatron(any(Patron.class), Mockito.eq(patronId))).thenReturn(updatedPatron);

        mockMvc.perform(put("/api/patrons/{id}", patronId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updatedPatron)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.phoneNumber").value(updatedPatron.getPhoneNumber()));
    }

    @Test
    public void testDeletePatron() throws Exception {
        Long patronId = 1L;
        mockMvc.perform(delete("/api/patrons/{id}", patronId))
                .andExpect(status().isNoContent());
    }

    @Test
    public void testInvalidEmailHandler() throws Exception {
        Patron p = getTestPatron();
        p.setEmail("invalid-email");
        mockMvc.perform(post("/api/patrons")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(p)))
                .andExpect(status().isBadRequest());

    }

    private Patron getTestPatron(){
        Patron p = new Patron();
        int r = 1 + (int) (Math.random() * ((1000 - 1) + 1));
        p.setName("Patron " + r);
        p.setEmail("Patron"+r+"@gmail.com");
        p.setPhoneNumber("8343143"+r+"38"+r);
        return p;
    }
}