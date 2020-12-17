package com.bilgeadam.tutorial.springboot.services;

import com.bilgeadam.tutorial.springboot.dao.EmployeeDAO;
import com.bilgeadam.tutorial.springboot.entities.Employee;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.never;

@ExtendWith(MockitoExtension.class)
class EmployeeServiceTest {

    @Mock
    private EmployeeDAO employeeDAO;

    @InjectMocks
    private EmployeeService testClass;

    @Test
    void findAllSuccess() {
        given(employeeDAO.findAll()).willReturn(Arrays.asList(new Employee("test", "name")));

        assertFalse(testClass.findAll().isEmpty());

        Mockito.verify(employeeDAO).findAll();
    }

    @Test
    void findAllFailure() {
        assertTrue(testClass.findAll().isEmpty());

        Mockito.verify(employeeDAO).findAll();
    }

    @Test
    void findByIdSuccess() {
        int id = 4;
        Employee testEmployee = new Employee("Junit", "Tests");
        testEmployee.setId(id);
        given(employeeDAO.findById(id)).willReturn(testEmployee);

        assertEquals(testEmployee, testClass.findById(id));
        Mockito.verify(employeeDAO).findById(id);
    }

    @Test
    void findByIdFailure() {
        int id = 4;
        given(employeeDAO.findById(id)).willReturn(null);

        assertThrows(RuntimeException.class,() -> testClass.findById(id));
        Mockito.verify(employeeDAO).findById(id);
        Mockito.verify(employeeDAO, never()).save(any(Employee.class));
    }

    @Test
    void saveSuccess() {
        int id = 4;
        Employee testEmployee = new Employee("Junit", "Tests");
        testEmployee.setId(id);
        given(employeeDAO.findById(id)).willReturn(null);

        assertDoesNotThrow(() -> testClass.save(testEmployee));
        Mockito.verify(employeeDAO).findById(id);
        Mockito.verify(employeeDAO).save(testEmployee);
    }
    @Test
    void saveFailure() {
        int id = 4;
        Employee testEmployee = new Employee("Junit", "Tests");
        testEmployee.setId(id);
        given(employeeDAO.findById(id)).willReturn(testEmployee);

        assertThrows(RuntimeException.class,() -> testClass.save(testEmployee));
        Mockito.verify(employeeDAO).findById(id);
        Mockito.verify(employeeDAO, never()).save(testEmployee);
    }

    @Test
    void updateFailure() {
        Employee testEmployee = new Employee("Junit", "Tests");
        assertThrows(RuntimeException.class, ()->testClass.update(testEmployee));
        Mockito.verify(employeeDAO, never()).save(testEmployee);
    }

    @Test
    void updateSuccess() {
        int id = 4;
        Employee testEmployee = new Employee("Junit", "Tests");
        testEmployee.setId(id);
        given(employeeDAO.findById(id)).willReturn(testEmployee);

        testClass.update(testEmployee);

        Mockito.verify(employeeDAO).save(testEmployee);
    }
    @Test
    void deleteFailure() {
        int id = 1;
        given(employeeDAO.findById(id)).willReturn(null);

        assertThrows(RuntimeException.class, () -> testClass.delete(id));
        Mockito.verify(employeeDAO, never()).delete(id);
    }

    @Test
    void deleteSuccess() {
        int id = 1;
        Employee testEmployee = new Employee("Junit", "Tests");
        testEmployee.setId(id);
        given(employeeDAO.findById(id)).willReturn(testEmployee);

        testClass.delete(id);

        Mockito.verify(employeeDAO).delete(id);
    }
}