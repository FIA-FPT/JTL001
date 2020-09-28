/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package j1.l.p0001;

import j1.l.p0001.DAO.GradeList;
import j1.l.p0001.DAO.StudentList;
import j1.l.p0001.DAO.SubjectList;
import j1.l.p0001.Utils.Menu;

/**
 *
 * @author ACER
 */
public class J1LP0001 {

    /**
     * @param args the command line arguments
     */
    
    public static void main(String[] args) {
        Integer choice;
        
        
        StudentList stdList = new StudentList();
        SubjectList subList = new SubjectList();
        GradeList gradeList = new GradeList(stdList,subList);
        
        Menu mainMenu = new Menu("Student Managing Application");
        
        mainMenu.add("Add New Student");
        mainMenu.add("Update or Delete Student");
        mainMenu.add("Add New Subject");
        mainMenu.add("Update Or Delete Subject");
        mainMenu.add("Add Grade");
        mainMenu.add("Print Student Report");
        mainMenu.add("Print Subject Report");
        
        
        do{
            mainMenu.printMenu("Exit");
            choice = mainMenu.getChoice();
            switch(choice){
                case 1:
                    stdList.addStudent();
                    break;
                case 2:
                    stdList.menuUpdateAndRemove();
                    break;
                case 3:
                    subList.addSubject();
                   break;
                case 4:
                    subList.menuUpdateAndRemove();
                    break;
                case 5:
                    gradeList.addGrade();
                    break;
                case 6:
                    gradeList.printStudentReport();
                    break;
                case 7:
                    gradeList.printSubjectReport();
                    break;
                default:
                    System.out.println("Application Exited");
            }
            
        }while(choice >= 1 && choice <= 7);
    }
    
}

