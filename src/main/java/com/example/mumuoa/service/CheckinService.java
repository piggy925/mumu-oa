package com.example.mumuoa.service;

/**
 * The interface Checkin service.
 */
public interface CheckinService {
    /**
     * Valid can checking string.
     *
     * @param userId the user id
     * @param date   the date
     * @return the string
     */
    String validCanChecking(Integer userId, String date);
}