package com.sg.classroster.controller;


import com.sg.classroster.dao.ClassRosterDao;
import com.sg.classroster.dao.ClassRosterDaoException;
import com.sg.classroster.dto.Student;
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
    private final ClassRosterDao dao;

    public ClassRosterController(ClassRosterView view, ClassRosterDao dao) {
        this.view = view;
        this.dao = dao;
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
        } catch (ClassRosterDaoException e) {
            view.displayError(e.getMessage());
        }
        this.exit();
    }
    
    private int getMenuSelection(){
        return view.printMenuGetChoice();
    }
    
    private void createStudent() throws ClassRosterDaoException{
        view.displayCreateStudentBanner();
        
        Student newStudent = view.getNewStudentInfo();
        dao.addStudent(newStudent.getStudentID(), newStudent);
        
        view.displayCreateSuccessBanner();
    }
    
    private void showAllStudents() throws ClassRosterDaoException {
        view.displayAllStudentBanner();
        
        List<Student> studentList = dao.getAllStudents();
        
        view.displayStudents(studentList);
    }
    
    private void showStudent() throws ClassRosterDaoException {
        view.showStudentBanner();
        
        String studentID = view.getStudentChoice();
        Student student = dao.getStudent(studentID);
        
        view.displayStudent(student);
    }
    
    private void removeStudent() throws ClassRosterDaoException {
        view.removeStudentBanner();
        
        String studentID = view.getStudentChoice();
        Student student = dao.removeStudent(studentID);
        
        view.removeStudent(student);
    }
    
    private void unknownCommand(){
        view.displayUnknownCommandBanner();
    }
    
    private void exit(){
        view.displayExitBanner();
    }
}
