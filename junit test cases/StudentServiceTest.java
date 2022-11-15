package com.example.springbootcrud.service;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.example.springbootcrud.entity.Student;
import com.example.springbootcrud.repository.StudentRepository;

import java.util.Optional;

import org.junit.jupiter.api.Disabled;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {StudentService.class})
@ExtendWith(SpringExtension.class)
class StudentServiceTest {
    @MockBean
    private StudentRepository studentRepository;

    @Autowired
    private StudentService studentService;

    /**
     * Method under test: {@link StudentService#save(Student)}
     */
    @Test
    void testSave() {
        Student student = new Student();
        student.setAge(1);
        student.setId(1);
        student.setName("Name");
        when(studentRepository.save((Student) any())).thenReturn(student);

        Student student1 = new Student();
        student1.setAge(1);
        student1.setId(1);
        student1.setName("Name");
        assertSame(student, studentService.save(student1));
        verify(studentRepository).save((Student) any());
    }

    /**
     * Method under test: {@link StudentService#getStudent(int)}
     */
    @Test
    void testGetStudent() {
        Student student = new Student();
        student.setAge(1);
        student.setId(1);
        student.setName("Name");
        Optional<Student> ofResult = Optional.of(student);
        when(studentRepository.findById((Integer) any())).thenReturn(ofResult);
        assertSame(student, studentService.getStudent(1));
        verify(studentRepository).findById((Integer) any());
    }

    /**
     * Method under test: {@link StudentService#getStudent(int)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testGetStudent2() {


        when(studentRepository.findById((Integer) any())).thenReturn(Optional.empty());
        studentService.getStudent(1);
    }

    /**
     * Method under test: {@link StudentService#update(Student)}
     */
    @Test
    void testUpdate() {
        Student student = new Student();
        student.setAge(1);
        student.setId(1);
        student.setName("Name");
        Optional<Student> ofResult = Optional.of(student);

        Student student1 = new Student();
        student1.setAge(1);
        student1.setId(1);
        student1.setName("Name");
        when(studentRepository.save((Student) any())).thenReturn(student1);
        when(studentRepository.findById((Integer) any())).thenReturn(ofResult);

        Student student2 = new Student();
        student2.setAge(1);
        student2.setId(1);
        student2.setName("Name");
        assertSame(student1, studentService.update(student2));
        verify(studentRepository).save((Student) any());
        verify(studentRepository).findById((Integer) any());
    }

    /**
     * Method under test: {@link StudentService#update(Student)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testUpdate2() {


        Student student = new Student();
        student.setAge(1);
        student.setId(1);
        student.setName("Name");
        when(studentRepository.save((Student) any())).thenReturn(student);
        when(studentRepository.findById((Integer) any())).thenReturn(Optional.empty());

        Student student1 = new Student();
        student1.setAge(1);
        student1.setId(1);
        student1.setName("Name");
        studentService.update(student1);
    }


    @Test
    void testDelete() {
        doNothing().when(studentRepository).deleteById((Integer) any());
        studentService.delete(1);
        verify(studentRepository).deleteById((Integer) any());
    }


    @Test
    void testConstructor() {
        assertNull((new StudentService()).getStudentRepository());
    }
}

