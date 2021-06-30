package com.authentication.api.services;

public interface BlockBruteForceService {

    Boolean isBruteForcedBlocked(String key);
    void logFailedAttempt(String key);
    void logSuccessAttempt(String key);
}
