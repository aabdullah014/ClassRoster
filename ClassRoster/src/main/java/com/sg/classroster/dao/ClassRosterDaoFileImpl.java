/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sg.classroster.dao;

import com.sg.classroster.dto.Student;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author abdulrahman
 */
public class ClassRosterDaoFileImpl implements ClassRosterDao{
    
    private Map<String, Student> students = new HashMap<>();
    
    @Override
    public Student addStudent(String studentID, Student student) {
        Student prevStudent = students.put(studentID, student);
        return prevStudent;
    }

    @Override
    public List<Student> getAllStudents() {
        return new ArrayList<Student>(students.values());
    }

    @Override
    public Student getStudent(String studentID) {
        Student student = students.get(studentID);
        return student;
    }

    @Override
    public Student removeStudent(String studentID) {
        Student student = students.remove(studentID);
        return student;
    }
    
}
