/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sg.classroster.dao;

/**
 *
 * @author abdulrahman
 */
public interface ClassRosterAuditDao {
    
    public void writeAuditEntry(String entry) throws ClassRosterPersistenceException;
    
}
