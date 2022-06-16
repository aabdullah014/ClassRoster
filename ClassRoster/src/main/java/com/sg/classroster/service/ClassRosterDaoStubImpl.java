/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sg.classroster.service;

import com.sg.classroster.dao.ClassRosterDao;
import com.sg.classroster.dao.ClassRosterPersistenceException;
import com.sg.classroster.dto.Student;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author abdulrahman
 */
public class ClassRosterDaoStubImpl implements ClassRosterDao{
    
    public Student onlyStudent;
    
    public ClassRosterDaoStubImpl() {
        
        onlyStudent = new Student("001");
        onlyStudent.setFirstName("Ada");
        onlyStudent.setLastName("Dingus");
        onlyStudent.setCohort("Java");
        
    }
    
    public ClassRosterDaoStubImpl(Student testStudent) {
        
        this.onlyStudent = testStudent;
        
    }
    
    @Override
    public Student addStudent(String studentID, Student student) throws ClassRosterPersistenceException {
        
        if (studentID.equals(onlyStudent.getStudentID())) {
            
            return onlyStudent;
            
        } else {
        
        return null;
        
        }
        
    }

    @Override
    public List<Student> getAllStudents() throws ClassRosterPersistenceException {
        
        List<Student> studentList = new ArrayList<>();
        studentList.add(onlyStudent);
        return studentList;
        
    }

    @Override
    public Student getStudent(String studentID) throws ClassRosterPersistenceException {
        
        if (studentID.equals(onlyStudent.getStudentID())) {
            
            return onlyStudent;
            
        } else {
            
            return null;
            
        }
        
    }

    @Override
    public Student removeStudent(String studentID) throws ClassRosterPersistenceException {
        if (studentID.equals(onlyStudent.getStudentID())) {
            return onlyStudent;
        } else {
            return null;
        }
    }
    
    
    
}
