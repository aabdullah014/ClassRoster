/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sg.classroster.ui;

import com.sg.classroster.dto.Student;
import java.util.List;

/**
 *
 * @author abdulrahman
 */
public class ClassRosterView {
    
    private final UserIO io = new UserIOConsoleImpl();
    
    public int printMenuGetChoice(){
        io.print("Main Menu");
        io.print("1. List Student IDs");
        io.print("2. Create a New Student");
        io.print("3. View a Student");
        io.print("4. Remove a Student");
        io.print("5. Exit");
            
        return io.readInt("Please Select from the numbers above.", 1, 5);
    }
    
    public Student getNewStudentInfo(){
        String studentID = io.readString("Please enter student ID");
        String fName = io.readString("Please enter student's first name.");
        String lName = io.readString("Please enter student's last name.");
        String cohort = io.readString("Please enter cohort");
        
        Student currentStudent = new Student(studentID);
        
        currentStudent.setFirstName(fName);
        currentStudent.setLastName(lName);
        currentStudent.setCohort(cohort);
        
        return currentStudent;
    }
    
    public void displayCreateStudentBanner(){
        io.print("**********************CREATE STUDENT**********************");
    }
    
    public void displayCreateSuccessBanner(){
        io.print("**********************SUCCESS*************************");
        io.readString("Please hit ENTER to proceed.");
    }
    
    public void displayStudents(List<Student> studentList){
        for(Student s: studentList){
            String studentInfo = String.format("#%s : %s %s",
                    s.getStudentID(),
                    s.getFirstName(),
                    s.getLastName());
            io.print(studentInfo);
        }
        
        io.readString("Please hit ENTER to proceed.");
    }
    
    public void displayAllStudentBanner(){
        io.print("**********************ALL STUDENTS**********************");
    }
    
    public void showStudentBanner(){
        io.print("**********************DISPLAY STUDENT**********************");
    }
    
    public String getStudentChoice(){
        return io.readString("Please enter Student ID to fetch.");
    }
    
    public void displayStudent(Student student){
        if (student != null){
            io.print(student.getStudentID());
            io.print(student.getFirstName());
            io.print(student.getLastName());
            io.print(student.getCohort());
        } else {
            io.print("No such student found.");
        }
        
        io.readString("Please hit ENTER to proceed.");
    }
    
    public void removeStudentBanner(){
        io.print("**********************REMOVE STUDENT**********************");
    }
    
    public void removeStudent(Student student){
        if (student != null){
            io.print("Student successfully removed.");
        } else {
            io.print("No such student found.");
        }
        
        io.readString("Please hit ENTER to proceed.");
    }
    
    public void displayExitBanner(){
        io.print("Goodbye!");
    }
    
    public void displayUnknownCommandBanner(){
        io.print("Unknown command!");
    }
}
