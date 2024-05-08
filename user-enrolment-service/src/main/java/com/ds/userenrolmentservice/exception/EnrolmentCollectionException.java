package com.ds.userenrolmentservice.exception;

public class EnrolmentCollectionException extends Exception{

    private static final long serialVersionUID = 1L;

    public EnrolmentCollectionException(String message) {
        super(message);
    }

    public static String NotFoundException(String userId) {
        return "Enrolment with " + userId + " not found";
    }

    public static String NotFoundException1(String userId, String courseId) {
        return "Enrolment with " + userId + " and " + courseId + " not found";
    }


    public static String AlreadyExists() {
        return "Enrolment already exists";
    }
}
