/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sg.classroster.dao;

import com.sg.classroster.dto.Student;
import java.util.List;

/**
 *
 * @author abdulrahman
 */
public interface ClassRosterDao {
    
    Student addStudent(String studentID, Student student) throws ClassRosterPersistenceException;
    
    List<Student> getAllStudents() throws ClassRosterPersistenceException;
    
    Student getStudent(String studentID) throws ClassRosterPersistenceException;
    
    Student removeStudent(String studentID) throws ClassRosterPersistenceException;
}
