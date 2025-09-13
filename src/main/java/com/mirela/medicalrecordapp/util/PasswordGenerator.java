package com.mirela.medicalrecordapp.util;

import java.util.UUID;

public class PasswordGenerator {

    public static String generateSimplePassword() {
        return UUID.randomUUID().toString().substring(0, 8);
    }
}
