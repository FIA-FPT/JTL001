/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package j1.l.p0001;

import j1.l.p0001.DAO.StudentList;
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
        Menu menu = new Menu();
        StudentList stdList = new StudentList();
        
            stdList.addStudent();
        while(true){
            stdList.menuUpdateAndRemove();
            stdList.print();
        }
        
    }
    
}
