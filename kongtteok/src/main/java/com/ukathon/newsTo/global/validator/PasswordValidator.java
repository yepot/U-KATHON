package com.ukathon.newsTo.global.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PasswordValidator implements ConstraintValidator<ValidPassword, String> {

    @Override
    public boolean isValid(String password, ConstraintValidatorContext context) {
        if (password == null) return false;

        boolean hasUpperOrLower = password.matches(".*[a-zA-Z].*");
        boolean hasDigit = password.matches(".*[0-9].*");
        boolean hasSpecial = password.matches(".*[^a-zA-Z0-9].*");

        int count = 0;
        if (hasUpperOrLower) count++;
        if (hasDigit) count++;
        if (hasSpecial) count++;

        return count >= 2;
    }
}