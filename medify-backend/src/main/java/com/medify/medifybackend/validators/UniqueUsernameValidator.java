package com.medify.medifybackend.validators;

import com.medify.medifybackend.config.ContextProvider;
import com.medify.medifybackend.repositories.UserRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;


//FIXME Unable to instantiate ConstraintValidator error
@Service
public class UniqueUsernameValidator implements ConstraintValidator<UniqueUserName, String> {

    private UserRepository userRepository;

    @Override
    public void initialize(UniqueUserName constraintAnnotation) {
        this.userRepository = (UserRepository) ContextProvider.getBean(UserRepository.class);

    }

    @Override
    public boolean isValid(String userName, ConstraintValidatorContext context) {
        if(userRepository == null)
            return true;
        return userName != null &&!userRepository.findByUsername(userName).isPresent();
    }

}
