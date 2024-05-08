package com.example.courseservice.exception;

public class CourseCollectionException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public CourseCollectionException(String message) {
        super(message);
    }

    public static String CourseNotFoundException(String id) {
        return "Course with " + id + " not found!";
    }

    public static String CourseNameAlreadyExists(String name) {
        return "Course with name " + name + " already exists!";
    }

    public static String CourseCodeAlreadyAllocated(String code) {
        return "Course with course code " + code + " is already exists!";
    }

    public static String CourseContentNotFoundException(String code) {
        return "Course content not found with course code " + code + " !";
    }
}
