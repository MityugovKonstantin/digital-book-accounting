package ru.mityugov.digitalbookaccounting.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.mityugov.digitalbookaccounting.models.Employee;
import ru.mityugov.digitalbookaccounting.services.EmployeeDetailService;

@Component
public class EmployeeValidator implements Validator {

    private final EmployeeDetailService employeeDetailService;

    @Autowired
    public EmployeeValidator(EmployeeDetailService employeeDetailService) {
        this.employeeDetailService = employeeDetailService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Employee.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Employee employee = (Employee) target;

        try {
            employeeDetailService.loadUserByUsername(employee.getUsername());
        } catch (UsernameNotFoundException e) {
            return;
        }

        errors.rejectValue(
                "username",
                "",
                "A employee with this username already exists"
        );
    }
}
