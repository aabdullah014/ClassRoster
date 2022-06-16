/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package com.sg.classroster.dao;

import com.sg.classroster.dto.Student;
import java.io.FileWriter;
import java.util.List;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author abdulrahman
 */
public class ClassRosterDaoFileImplTest {
    
    ClassRosterDao testDao;
    
    public ClassRosterDaoFileImplTest() {
    }
    
    @Before
    public void setUp() throws Exception{
        
        String testFile = "testRoster.txt";
        
        new FileWriter(testFile);
        testDao = new ClassRosterDaoFileImpl(testFile);
        
    }

    @org.junit.Test
    public void testSomeMethod() throws Exception{
        // Create our method test inputs
        Student student = new Student("001");
        student.setFirstName("Ada");
        student.setLastName("Johnson");
        student.setCohort("Java");
        
        // Add Student to the Dao
        testDao.addStudent(student.getStudentID(), student);
        
        // Get the student from the DAO
        Student fetchDaoStudent = testDao.getStudent(student.getStudentID());
        
        // Check if the data is equal
        assertEquals("Checking Student ID", student.getStudentID(), fetchDaoStudent.getStudentID());
        assertEquals("Checking Student First Name", student.getFirstName(), fetchDaoStudent.getFirstName());
        assertEquals("Checking Student Last Name", student.getLastName(), fetchDaoStudent.getLastName());
        assertEquals("Checking Student Cohort", student.getCohort(), fetchDaoStudent.getCohort());
        
    }

    @Test
    public void testAddGetAllStudents() throws Exception {
        
        Student student1 = new Student("001");
        student1.setFirstName("Ada");
        student1.setLastName("Johnson");
        student1.setCohort("Java");
        
        Student student2 = new Student("002");
        student2.setFirstName("Bada");
        student2.setLastName("Johnson");
        student2.setCohort("Java");
        
        testDao.addStudent(student1.getStudentID(), student1);
        testDao.addStudent(student2.getStudentID(), student2);
        
        List<Student> allStudents = testDao.getAllStudents();
        
        assertNotNull("List must not be null", allStudents);
        assertEquals("Should be two students in list.", 2, allStudents.size());
        
        assertTrue("Should have Ada", allStudents.contains(student1));
        assertTrue("Should have Bada", allStudents.contains(student2));
        
    }
    
    @Test
    public void testRemoveStudent() throws Exception {
        
        Student student1 = new Student("001");
        student1.setFirstName("Ada");
        student1.setLastName("Johnson");
        student1.setCohort("Java");
        
        Student student2 = new Student("002");
        student2.setFirstName("Bada");
        student2.setLastName("Johnson");
        student2.setCohort("Java");
        
        testDao.addStudent(student1.getStudentID(), student1);
        testDao.addStudent(student2.getStudentID(), student2);
        
        Student removedStudent = testDao.removeStudent(student1.getStudentID());
        
        assertEquals("Ada should be the one removed", student1, removedStudent);
        
        List<Student> allStudents = testDao.getAllStudents();
        
        assertNotNull("All students list should not be null", allStudents);
        assertEquals("All students list should only have one student", 1, allStudents.size());
        
        assertFalse("Ada should not be in the list", allStudents.contains(student1));
        assertTrue("Bada should be in the list", allStudents.contains(student2));
        
        removedStudent = testDao.removeStudent(student2.getStudentID());
        
        assertEquals("The removed student should be Bada", removedStudent, student2);
        
        allStudents = testDao.getAllStudents();
        
        assertTrue("The retreived list of students should be empty.", allStudents.isEmpty());
        
        Student fetchDaoStudent = testDao.getStudent(student1.getStudentID());
        assertNull("Ada was removed, should be null", fetchDaoStudent);
        
        fetchDaoStudent = testDao.getStudent(student2.getStudentID());
        assertNull("Bada was removed, should be null", fetchDaoStudent);
        
    }
    
}
