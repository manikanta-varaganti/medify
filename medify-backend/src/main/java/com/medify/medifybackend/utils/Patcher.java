package com.medify.medifybackend.utils;

import com.medify.medifybackend.models.Patient;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;

@Component
public class Patcher {
    public static void patientPatcher(Patient existingPatient, Patient newPatient)  throws IllegalAccessException{
        //GET THE COMPILED VERSION OF THE CLASS
        Class<?> patientClass = Patient.class;
        Field[] patientFields  = patientClass.getDeclaredFields();
        for (Field patientField : patientFields) {
            patientField.setAccessible(true);
            Object value = patientField.get(existingPatient);
            //CHECK IF THE VALUE OF THE FIELD IS NOT NULL, IF NOT UPDATE EXISTING PATIENT
            if (value != null) {
                patientField.set(existingPatient, value);
            }
            //Make field private again
            patientField.setAccessible(false);
        }
    }
}
