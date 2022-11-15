package com.example.springbootcrud.entity;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class StudentTest {

    @Test
    void testConstructor() {
        Student actualStudent = new Student();
        actualStudent.setAge(1);
        actualStudent.setId(1);
        actualStudent.setName("Name");
        assertEquals(1, actualStudent.getAge());
        assertEquals(1, actualStudent.getId());
        assertEquals("Name", actualStudent.getName());
    }
}

