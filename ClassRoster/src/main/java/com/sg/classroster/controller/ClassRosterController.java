package com.sg.classroster.controller;


import com.sg.classroster.dao.ClassRosterPersistenceException;
import com.sg.classroster.dto.Student;
import com.sg.classroster.service.ClassRosterDataValidationException;
import com.sg.classroster.service.ClassRosterDuplicateIDException;
import com.sg.classroster.service.ClassRosterServiceLayer;
import com.sg.classroster.ui.ClassRosterView;
import java.util.List;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author abdulrahman
 */
public class ClassRosterController {
    
    private final ClassRosterView view;
    private final ClassRosterServiceLayer serv;

    public ClassRosterController(ClassRosterView view, ClassRosterServiceLayer serv) {
        this.view = view;
        this.serv = serv;
    }
    
    
    
    public void run(){
        boolean keepGoing = true;
        int menuSelection = 0;
        
        try{
            while (keepGoing) {

                menuSelection = getMenuSelection();

                switch (menuSelection) {
                    case 1:
                        this.showAllStudents();
                        break;
                    case 2:
                        this.createStudent();
                        break;
                    case 3:
                        this.showStudent();
                        break;
                    case 4:
                        this.removeStudent();
                        break;
                    case 5:
                        keepGoing = false;
                        break; 
                    default:
                        this.unknownCommand();
                } // end switch
            } // end while
        } catch (ClassRosterPersistenceException e) {
            view.displayError(e.getMessage());
        }
        this.exit();
    }
    
    private int getMenuSelection(){
        return view.printMenuGetChoice();
    }
    
    private void createStudent() throws ClassRosterPersistenceException{
        view.displayCreateStudentBanner();
        
        boolean hasErrors = false;
        
        do{
            
            Student newStudent = view.getNewStudentInfo();
            
            try{
                
                serv.addStudent(newStudent);
        
                view.displayCreateSuccessBanner();
                
                hasErrors = false;
                
            } catch (ClassRosterDuplicateIDException | ClassRosterDataValidationException e) {
                
                hasErrors = true;
                view.displayError(e.getMessage());
                
            }
            
        } while (hasErrors);
        
    }
    
    private void showAllStudents() throws ClassRosterPersistenceException {
        view.displayAllStudentBanner();
        
        List<Student> studentList = serv.getAllStudents();
        
        view.displayStudents(studentList);
    }
    
    private void showStudent() throws ClassRosterPersistenceException {
        view.showStudentBanner();
        
        String studentID = view.getStudentChoice();
        Student student = serv.getStudent(studentID);
        
        view.displayStudent(student);
    }
    
    private void removeStudent() throws ClassRosterPersistenceException {
        view.removeStudentBanner();
        
        String studentID = view.getStudentChoice();
        Student student = serv.removeStudent(studentID);
        
        view.removeStudent(student);
    }
    
    private void unknownCommand(){
        view.displayUnknownCommandBanner();
    }
    
    private void exit(){
        view.displayExitBanner();
    }
}
