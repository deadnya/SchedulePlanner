package com.example.control1.common.exception;

import java.util.List;

public record ErrorModel(
        Integer statusCode,
        List<String> errors
) {
}
