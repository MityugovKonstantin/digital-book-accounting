package ru.mityugov.digitalbookaccounting.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.mityugov.digitalbookaccounting.models.Employee;
import ru.mityugov.digitalbookaccounting.repositories.EmployeeRepository;

@Service
public class RegistrationService {

    private final EmployeeRepository employeeRepository;

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public RegistrationService(EmployeeRepository employeeRepository, PasswordEncoder passwordEncoder) {
        this.employeeRepository = employeeRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public void register(Employee employee) {
        employee.setPassword(passwordEncoder.encode(employee.getPassword()));
        employeeRepository.save(employee);
    }
}
