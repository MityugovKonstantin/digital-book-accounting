package ru.mityugov.digitalbookaccounting.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.mityugov.digitalbookaccounting.models.Employee;
import ru.mityugov.digitalbookaccounting.services.RegistrationService;
import ru.mityugov.digitalbookaccounting.utils.EmployeeValidator;

@Controller
@RequestMapping("/auth")
public class AuthController {

    private final RegistrationService registrationService;

    private final EmployeeValidator employeeValidator;

    @Autowired
    public AuthController(RegistrationService registrationService, EmployeeValidator employeeValidator) {
        this.registrationService = registrationService;
        this.employeeValidator = employeeValidator;
    }

    @GetMapping("/login")
    public String showLoginPage() {
        return "auth/login";
    }

    @GetMapping("/registration")
    public String showRegistrationPage(@ModelAttribute("employee") Employee employee) {
        return "auth/registration";
    }

    @PostMapping("/registration")
    public String register(@ModelAttribute("employee") @Valid Employee employee, BindingResult bindingResult) {
        employeeValidator.validate(employee, bindingResult);

        if (bindingResult.hasErrors())
            return "auth/registration";

        registrationService.register(employee);

        return "redirect:/auth/login";
    }
}
