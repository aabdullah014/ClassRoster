/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package com.sg.classroster.service;

import com.sg.classroster.dao.ClassRosterAuditDao;
import com.sg.classroster.dao.ClassRosterDao;
import com.sg.classroster.dao.ClassRosterPersistenceException;
import com.sg.classroster.dto.Student;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import org.junit.Test;

/**
 *
 * @author abdulrahman
 */
public class ClassRosterServiceLayerImplTest {
    
    private ClassRosterServiceLayer testService;
    
    public ClassRosterServiceLayerImplTest() {
        
        ClassRosterDao dao = new ClassRosterDaoStubImpl();
        ClassRosterAuditDao auditDao = new ClassRosterAuditDaoStubImpl();
        
        this.testService = new ClassRosterServiceLayerImpl(dao, auditDao);
        
    }

    @Test
    public void testCreateValidStudent() {
        // ARRANGE
        Student testStudent = new Student("002");
        testStudent.setFirstName("Bada");
        testStudent.setLastName("Ding");
        testStudent.setCohort("Python");
        
        // ACT
        try{
            
            testService.addStudent(testStudent);
            
        } catch ( ClassRosterDuplicateIDException 
                | ClassRosterDataValidationException 
                | ClassRosterPersistenceException e) {
            
            fail("Something went wrong.");
            
        }
        
    }
    
    @Test
    public void testDuplicateValidStudent() {
        
        // ARRANGE
        Student testStudent = new Student("001");
        testStudent.setFirstName("Bada");
        testStudent.setLastName("Ding");
        testStudent.setCohort("Python");
        
        // ACT
        try {
            
            testService.addStudent(testStudent);
            fail("Something went wrong.");
            
        } catch (ClassRosterDataValidationException | ClassRosterPersistenceException e) {
            
            fail("Something went wrong. That's the wrong exception.");
            
        } catch (ClassRosterDuplicateIDException e) {
            
            return;
            
        }
        
    }
    
    @Test
    public void testCreateInvalidData() throws Exception {
        // ARRANGE
        Student testStudent = new Student("002");
        testStudent.setFirstName("");
        testStudent.setLastName("Ding");
        testStudent.setCohort("Python");
        
        //ACT
        try {
            
            testService.addStudent(testStudent);
            fail("Something went wrong. That should not have worked.");
            
        } catch ( ClassRosterDuplicateIDException | ClassRosterPersistenceException e ) {
            
            fail("Something went wrong. That's the wrong exception.");
            
        } catch ( ClassRosterDataValidationException e ) {
            
            return;
            
        }
        
    }
    
    @Test
    public void testGetAllStudents() throws Exception {
        // ARRANGE
        Student testStudent = new Student("001");
        testStudent.setFirstName("Ada");
        testStudent.setLastName("Dingus");
        testStudent.setCohort("Java");
        
        // ACT & ASSERT
        assertEquals("Student stored under 001 should be Bada.", 1, testService.getAllStudents().size());
        assertTrue("The one student should be Bada", testService.getAllStudents().contains(testStudent));
        
    }
    
    @Test
    public void testGetStudent() throws Exception {
        // ARRANGE
       Student testStudent = new Student("001");
        testStudent.setFirstName("Ada");
        testStudent.setLastName("Dingus");
        testStudent.setCohort("Java");
        
        // ACT & ASSERT
        Student shouldBeBada = testService.getStudent("001");
        assertNotNull("Getting 001 should not be null.", shouldBeBada);
        assertEquals("Student stored under 001 should be Bada.", testStudent, shouldBeBada);
        
        Student shouldBeNull = testService.getStudent("0042");
        assertNull("Getting 0042 should be null", shouldBeNull);
        
    }
    
    @Test
    public void testRemove() throws Exception {
        // ARRANGE
        Student testStudent = new Student("001");
        testStudent.setFirstName("Ada");
        testStudent.setLastName("Dingus");
        testStudent.setCohort("Java");
        
        // ACT
        Student shouldBeAda = testService.getStudent("001");
        assertNotNull("Should be Ada", shouldBeAda);
        assertEquals("Should be a clone", shouldBeAda, testStudent);
        
        Student shouldBeNull = testService.removeStudent("0042");
        assertNull("Removing 0042 should be null", shouldBeNull);
        
    }
    
}
