package com.spring.project.exception;

public class DuplicatedRecordException extends Exception {

    DuplicatedRecordException(String message) {
        super (message);
    }

    DuplicatedRecordException() {
        super ("Duplicated Record Exception");
    }
}
