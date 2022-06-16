/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

package com.sg.classroster;

import com.sg.classroster.controller.ClassRosterController;
import com.sg.classroster.dao.ClassRosterAuditDao;
import com.sg.classroster.dao.ClassRosterAuditDaoFileImpl;
import com.sg.classroster.dao.ClassRosterDao;
import com.sg.classroster.dao.ClassRosterDaoFileImpl;
import com.sg.classroster.service.ClassRosterServiceLayer;
import com.sg.classroster.service.ClassRosterServiceLayerImpl;
import com.sg.classroster.ui.ClassRosterView;
import com.sg.classroster.ui.UserIO;
import com.sg.classroster.ui.UserIOConsoleImpl;


/**
 *
 * @author abdulrahman
 */
public class App {

    public static void main(String[] args) {
        
        UserIO io = new UserIOConsoleImpl();
        ClassRosterView view = new ClassRosterView(io);
        
        ClassRosterDao dao = new ClassRosterDaoFileImpl();
        
        ClassRosterAuditDao auditDao = new ClassRosterAuditDaoFileImpl();
        ClassRosterServiceLayer serv = new ClassRosterServiceLayerImpl(dao, auditDao);
        
        ClassRosterController controller = new ClassRosterController(view, serv);
        
        controller.run();
        
    }
}
