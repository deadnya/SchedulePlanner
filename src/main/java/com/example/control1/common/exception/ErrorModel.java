package com.example.control1.common.exception;

import java.util.List;

/**
 * Data transfer object for representing error details.
 *
 * @param statusCode HTTP status code of the error
 * @param errors A list of error messages
 */
public record ErrorModel(
        Integer statusCode,
        List<String> errors
) {
}
