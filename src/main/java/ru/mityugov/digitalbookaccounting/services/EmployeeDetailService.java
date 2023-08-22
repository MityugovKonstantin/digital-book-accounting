package ru.mityugov.digitalbookaccounting.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.mityugov.digitalbookaccounting.models.Employee;
import ru.mityugov.digitalbookaccounting.repositories.EmployeeRepository;
import ru.mityugov.digitalbookaccounting.security.EmployeeDetails;

import java.util.Optional;

@Service
public class EmployeeDetailService implements UserDetailsService {

    private final EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeDetailService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Employee> employee = employeeRepository.findByUsername(username);
        if (employee.isEmpty())
            throw new UsernameNotFoundException("Username not found");
        return new EmployeeDetails(employee.get());
    }
}
