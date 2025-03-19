package com.example.control1.service.uuid.impl;

import com.example.control1.service.uuid.UUIDService;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UUIDServiceImpl implements UUIDService {

    @Override
    public String getRandomUUID() {
        return UUID.randomUUID().toString().replace("-", "");
    }
}
