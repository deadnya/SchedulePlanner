package com.example.control1.service.uuid.impl;

import com.example.control1.service.uuid.UUIDService;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * Implementation of the UUIDService interface.
 * Provides functionality to generate random UUIDs.
 */
@Service
public class UUIDServiceImpl implements UUIDService {

    /**
     * Generates a random UUID without dashes.
     *
     * @return a randomly generated UUID as a string without dashes
     */
    @Override
    public String getRandomUUID() {
        return UUID.randomUUID().toString().replace("-", "");
    }
}
