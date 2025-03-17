package com.example.control1.common.util;

import java.util.UUID;

public class Utility {

    public static String generateRandomUUID() {
        return UUID.randomUUID().toString().replace("-", "");
    }
}
