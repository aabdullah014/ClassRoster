/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sg.classroster.service;

import com.sg.classroster.dao.ClassRosterAuditDao;
import com.sg.classroster.dao.ClassRosterDao;
import com.sg.classroster.dao.ClassRosterPersistenceException;
import com.sg.classroster.dto.Student;
import java.util.List;

/**
 *
 * @author abdulrahman
 */
public class ClassRosterServiceLayerImpl implements ClassRosterServiceLayer{
    
    ClassRosterDao dao;
    ClassRosterAuditDao auditDao;

    public ClassRosterServiceLayerImpl(ClassRosterDao dao, ClassRosterAuditDao auditDao) {
        this.dao = dao;
        this.auditDao = auditDao;
    }
    
    private void validateStudentData(Student student) throws ClassRosterDataValidationException{
        
        if (student.getFirstName() == null
                || student.getFirstName().trim().length() == 0
                || student.getLastName() == null 
                || student.getLastName().trim().length() == 0
                || student.getCohort() == null
                || student.getCohort().trim().length() == 0) {
            throw new ClassRosterDataValidationException (
                    "ERROR: All fields [First Name, Last Name, Cohort] are required!"
            );
        }
    }
    
    @Override
    public void addStudent(Student student) throws 
            ClassRosterDuplicateIDException, 
            ClassRosterDataValidationException, 
            ClassRosterPersistenceException {
        
        if (dao.getStudent(student.getStudentID()) != null) {
            
            throw new ClassRosterDuplicateIDException (
                    "ERROR: Could not create student. Student ID "
                    + student.getStudentID() 
                    + " already exists!"
            );
        }
        
        this.validateStudentData(student);
        
        dao.addStudent(student.getStudentID(), student);
        
        auditDao.writeAuditEntry("Added student " + student.getStudentID() + ".");
        
    }

    @Override
    public List<Student> getAllStudents() throws ClassRosterPersistenceException {
        return dao.getAllStudents();
    }

    @Override
    public Student getStudent(String studentId) throws ClassRosterPersistenceException {
        return dao.getStudent(studentId);
    }

    @Override
    public Student removeStudent(String studentId) throws ClassRosterPersistenceException {
        Student removedStudent = dao.removeStudent(studentId);
        
        if (removedStudent == null) {
            
            auditDao.writeAuditEntry("No such student");
            return null;
            
        } else {
        
            auditDao.writeAuditEntry("Removed student " + removedStudent.getStudentID() + ".");
        
        }
        
        return removedStudent;
    }
    
}
