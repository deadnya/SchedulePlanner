package com.example.control1.service.uuid;

/**
 * Service interface for generating UUIDs.
 */
public interface UUIDService {

    /**
     * Generates a random UUID.
     *
     * @return randomly generated UUID without dashes as a string
     */
    String getRandomUUID();
}
