/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sg.classroster.dao;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;

/**
 *
 * @author abdulrahman
 */
public class ClassRosterAuditDaoFileImpl implements ClassRosterAuditDao{
    
    public static final String AUDIT_FILE = "audit.txt";
    
    @Override
    public void writeAuditEntry(String entry) throws ClassRosterPersistenceException {
        
        PrintWriter out;
        
        try{
            
            out = new PrintWriter(new FileWriter(AUDIT_FILE, true));
            
        } catch (IOException e) {
            
            throw new ClassRosterPersistenceException(
                    "Could not write to audit file!"
            );
            
        }
        
        LocalDateTime timeStamp = LocalDateTime.now();
        out.println(timeStamp.toString() + ": " + entry);
        out.flush();
        
    }
    
}
