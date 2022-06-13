/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

package com.sg.classroster;

import com.sg.classroster.controller.ClassRosterController;


/**
 *
 * @author abdulrahman
 */
public class App {

    public static void main(String[] args) {
        
        ClassRosterController controller = new ClassRosterController();
        
        controller.run();
        
    }
}
