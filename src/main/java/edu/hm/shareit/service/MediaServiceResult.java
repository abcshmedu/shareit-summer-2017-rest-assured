/*
 * Wolfgang Gabler
 *
 * Software: Mac OS X 10.12, Oracle Java 1.8.0_111 SE
 * System: Intel Core i7-4850HQ, 16 GByte RAM
 */
package edu.hm.shareit.service;

/**
 * @author Wolfgang Gabler, wgabler@hm.edu
 * @author Andrea Limmer, limmer@hm.edu
 * @since 19.04.17
 */
public enum MediaServiceResult {

    OK(200, "OK"),
    INVALID_ISBN(401, "Invalid ISBN, must conform to ISBN-13"),
    INVALID_BARCODE(401, "Invalid Barcode, must conform to EAN-13"),
    ISBN_ALREADY_IN_USE(401, "ISBN already in use."),
    BARCODE_ALREADY_IN_USE(401, "Barcode already in use."),

    MISSING_AUTHOR(401, "Missing author"),
    MISSING_TITLE(401, "Missing title"),
    MISSING_DIRECTOR(401, "Missing director"),

    ISBN_NOT_FOUND(404, "ISBN not found"),
    BARCODE_NOT_FOUND(404, "Barcode not found"),
    ISBN_MISMATCH(401, "ISBN in URI and JSON do not match"),
    BARCODE_MISMATCH(401, "Barcode in URI and JSON do not match"),
    
    ERROR(401, "Error while processing data"),

    UNAUTHORIZED(403, "Invalid Token");

    private int code;
    private String status;

    /**
     * Constructor for the enum.
     * @param code Statuscode.
     * @param status Status message.
     */
    MediaServiceResult(int code, String status) {
        this.code = code;
        this.status = status;
    }

    /**
     * Getter for the code.
     * @return Status code.
     */
    public int getCode() {
        return code;
    }

    /**
     * Getter for the status.
     * @return Status message.
     */
    public String getStatus() {
        return status;
    }
}
