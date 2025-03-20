package com.example.control1.dto.common;

/**
 * Data transfer object for create responses.
 * Contains the ID of the created entity.
 *
 * @param id ID of the created entity
 */
public record CreateResponseDTO(
        String id
) {
}
