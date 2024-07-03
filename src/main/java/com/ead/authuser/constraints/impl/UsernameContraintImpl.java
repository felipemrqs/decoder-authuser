package com.ead.authuser.constraints.impl;

import com.ead.authuser.constraints.UsernameContraint;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class UsernameContraintImpl implements ConstraintValidator<UsernameContraint, String> {
    @Override
    public void initialize(UsernameContraint constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String userName, ConstraintValidatorContext constraintValidatorContext) {
        return !userName.contains(" ");
    }
}
