package ru.otus.hw.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("StudentServiceImplTest | Student service ")
public class StudentServiceImplTest {

    private final String expectedFirstName = "Chuck";
    private final String expectedLastName = "Norris";
    private IOService ioService;
    private StudentServiceImpl studentServiceImpl;

    @BeforeEach
    public void init() {
        ioService = Mockito.mock(IOService.class);
        Mockito.when(ioService.readStringWithPrompt(Mockito.contains("first"))).thenReturn(expectedFirstName);
        Mockito.when(ioService.readStringWithPrompt(Mockito.contains("last"))).thenReturn(expectedLastName);

        studentServiceImpl = new StudentServiceImpl(ioService);
    }

    @Test
    @DisplayName("given a first name, the correct first name should be returned")
    void determineCurrentStudent_givenValidStudentFirstName_expectedValuesReturned() {

        assertEquals(studentServiceImpl.determineCurrentStudent().firstName(), expectedFirstName);
    }

    @Test
    @DisplayName("given a last name, the correct last name should be returned")
    void determineCurrentStudent_givenValidStudentLastName_expectedValuesReturned() {

        assertEquals(studentServiceImpl.determineCurrentStudent().lastName(), expectedLastName);
    }

    @Test
    @DisplayName("given a first and last name, the correct full name should be returned")
    void determineCurrentStudent_givenValidStudentFullName_expectedValuesReturned() {

        assertEquals(studentServiceImpl.determineCurrentStudent().getFullName(),
                String.format("%s %s", expectedFirstName, expectedLastName)
        );
    }
}