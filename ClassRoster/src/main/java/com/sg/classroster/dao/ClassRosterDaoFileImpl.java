/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sg.classroster.dao;

import com.sg.classroster.dto.Student;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 *
 * @author abdulrahman
 */
public class ClassRosterDaoFileImpl implements ClassRosterDao{
    
    private Map<String, Student> students = new HashMap<>();
    
    public static final String ROSTER_FILE = "roster.txt";
    public static final String DELIMETER = "::";
    
    @Override
    public Student addStudent(String studentID, Student student) throws ClassRosterDaoException {
        this.loadRoster();
        Student prevStudent = students.put(studentID, student);
        writeRoster();
        return prevStudent;
    }

    @Override
    public List<Student> getAllStudents() throws ClassRosterDaoException {
        this.loadRoster();
        return new ArrayList<Student>(students.values());
    }

    @Override
    public Student getStudent(String studentID) throws ClassRosterDaoException {
        this.loadRoster();
        Student student = students.get(studentID);
        return student;
    }

    @Override
    public Student removeStudent(String studentID) throws ClassRosterDaoException {
        this.loadRoster();
        Student student = students.remove(studentID);
        this.writeRoster();
        return student;
    }
    
    private Student unmarshallStudent(String studentAsText){
        String[] studentTokens = studentAsText.split(DELIMETER);
        
        String studentID = studentTokens[0];
        String fName = studentTokens[1];
        String lName = studentTokens[2];
        String cohort = studentTokens[3];
        
        Student student = new Student(studentID);
        student.setFirstName(fName);
        student.setLastName(lName);
        student.setCohort(cohort);
        
        return student;
    }
    
    private String marshallStudent(Student student) {
        String studentAsText = student.getStudentID() + DELIMETER;
        
        studentAsText += student.getFirstName() + DELIMETER 
                        + student.getLastName() + DELIMETER
                                     + student.getCohort();
        
        return studentAsText;
    }
    
    private void writeRoster() throws ClassRosterDaoException{
        PrintWriter out;
        
        try{
            out = new PrintWriter(new FileWriter(ROSTER_FILE));
        } catch (IOException e){
            throw new ClassRosterDaoException("Could not save student data.", e);
        }
        
        String studentAsText;
        
        List<Student> studentList = this.getAllStudents();
        for (Student student: studentList) {
            studentAsText = marshallStudent(student);
            out.println(studentAsText);
            out.flush();
        }
        
        out.close();
    }
    
    private void loadRoster() throws ClassRosterDaoException{
        Scanner scanner;
        
        try{
            scanner = new Scanner(
                            new BufferedReader(
                                new FileReader(ROSTER_FILE)));
        } catch (FileNotFoundException e) {
            throw new ClassRosterDaoException("Could not load roster data into memory.", e);
        }
        
        String currentLine;
        
        Student currentStudent;
        
        while (scanner.hasNextLine()) {
            currentLine = scanner.nextLine();
            
            currentStudent = unmarshallStudent(currentLine);
            
            students.put(currentStudent.getStudentID(), currentStudent);
        }
        
        scanner.close();
    }
}
