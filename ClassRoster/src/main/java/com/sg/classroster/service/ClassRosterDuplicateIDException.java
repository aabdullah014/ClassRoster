/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sg.classroster.service;

/**
 *
 * @author abdulrahman
 */
public class ClassRosterDuplicateIDException extends Exception{

    public ClassRosterDuplicateIDException(String message) {
        super(message);
    }

    public ClassRosterDuplicateIDException(String message, Throwable cause) {
        super(message, cause);
    }
    
}
