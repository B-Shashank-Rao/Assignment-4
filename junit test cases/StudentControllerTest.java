package com.example.springbootcrud.controller;

import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.when;

import com.example.springbootcrud.entity.Student;
import com.example.springbootcrud.service.StudentService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ContextConfiguration(classes = {StudentController.class})
@ExtendWith(SpringExtension.class)
class StudentControllerTest {
    @Autowired
    private StudentController studentController;

    @MockBean
    private StudentService studentService;

    /**
     * Method under test: {@link StudentController#addStudent(Student)}
     */
    @Test
    void testAddStudent() throws Exception {
        Student student = new Student();
        student.setAge(1);
        student.setId(1);
        student.setName("Name");
        String content = (new ObjectMapper()).writeValueAsString(student);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/student")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(studentController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(405));
    }

    /**
     * Method under test: {@link StudentController#getStudent(int)}
     */
    @Test
    void testGetStudent() throws Exception {
        Student student = new Student();
        student.setAge(1);
        student.setId(1);
        student.setName("Name");
        when(studentService.getStudent(anyInt())).thenReturn(student);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/student/{id}", 1);
        MockMvcBuilders.standaloneSetup(studentController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("{\"id\":1,\"name\":\"Name\",\"age\":1}"));
    }
}

