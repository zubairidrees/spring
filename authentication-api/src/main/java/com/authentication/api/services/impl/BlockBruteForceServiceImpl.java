package com.authentication.api.services.impl;

import com.authentication.api.services.BlockBruteForceService;
import com.authentication.api.util.Constants;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * This service just depicts the concept of blocking brute force,
 * for exercise purpose logs for attempts are being maintained in memory
 */
@Service
public class BlockBruteForceServiceImpl implements BlockBruteForceService {
    //recording ip as key and number of attempts made as value
    private Map<String, Integer> failedAttemptsMap = new HashMap<>();

    /**
     *
     * @param key ip address of user machine
     * @return True if user exceeds number of invalid attempts
     */
    @Override
    public Boolean isBruteForcedBlocked(String key) {
        if(failedAttemptsMap.containsKey(key)){
            Integer attemptsMade = failedAttemptsMap.get(key);
            if(attemptsMade >= Constants.MAX_ATTEMPT ){
                return Boolean.TRUE;
            }else{
                return Boolean.FALSE;
            }
        }else {
            return Boolean.FALSE;
        }
    }

    /**
     * Logging how many invalid attempts have been made
     * @param key ip address of user machine
     */
    @Override
    public void logFailedAttempt(String key) {

        if(failedAttemptsMap.containsKey(key)){
            Integer attemptsMade = failedAttemptsMap.get(key);
            attemptsMade++;
            failedAttemptsMap.put(key,attemptsMade);
        }else {
            failedAttemptsMap.put(key,1);
        }
    }

    /**
     * It invalidates unsuccessful attempts after a
     * successful attempt
     * @param key ip address of user machine
     */
    @Override
    public void logSuccessAttempt(String key) {

        if(failedAttemptsMap.containsKey(key)) {
            failedAttemptsMap.put(key, 0);
        }
    }
}
