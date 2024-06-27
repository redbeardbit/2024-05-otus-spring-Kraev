package ru.otus.hw.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.contains;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@DisplayName("StudentServiceImplTest | Student service ")
public class StudentServiceImplTest {

    private final String expectedFirstName = "Chuck";
    private final String expectedLastName = "Norris";

    @Mock
    private IOService ioService;

    @InjectMocks
    private StudentServiceImpl studentServiceImpl;

    @BeforeEach
    public void init() {
        assertNotNull(ioService);
        assertNotNull(studentServiceImpl);
        when(ioService.readStringWithPrompt(contains("first"))).thenReturn(expectedFirstName);
        when(ioService.readStringWithPrompt(contains("last"))).thenReturn(expectedLastName);
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