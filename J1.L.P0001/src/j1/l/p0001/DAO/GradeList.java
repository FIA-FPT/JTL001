/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package j1.l.p0001.DAO;

import j1.l.p0001.DTO.Grade;
import j1.l.p0001.DAO.StudentList;
import j1.l.p0001.DAO.SubjectList;
import j1.l.p0001.Utils.EssentialUtils;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author ACER
 */
public class GradeList extends ArrayList<Grade> {
    StudentList stdList = new StudentList();
    SubjectList subList  = new SubjectList();
    
    Scanner sc = new Scanner(System.in);

    public GradeList(StudentList stdList, SubjectList subList) {
        this.stdList = stdList;
        this.subList = subList;
    }
    
    
    
    //Add new grade to the list
    public void addGrade(){
       String studentID;
       String subjectID;
        
       boolean isNull = true;
       boolean isCreditValid = false;
       boolean isContinue = true;
       
       Integer isStudentNameExist = -1;
       Integer isSubjectNameExist = -1;      
       Integer isGradeExist = -1;
       
       Double grade;
       Double fE;
       Double lab;
       Double progressTest;
       
       
        //Check if whether Student List Or Subject Is Empty, if one of it is Empty, Terminate the program      
       if(stdList.isEmpty() || subList.isEmpty()){
           System.out.println("Student List or Subject is Empty, Cannot add grade!");
       } else{
           
       //A loop until user decided not to continue
        do{
            
            //Loop until Student Name and Subject Name is inputted correctly
            do{
                System.out.print("Enter Student ID  ");
                studentID = sc.nextLine();
                
                System.out.print("Enter Subject ID:  ");
                subjectID = sc.nextLine();
                
                if(EssentialUtils.isEmptyString(studentID) || EssentialUtils.isEmptyString(subjectID)){
                    
                    System.out.println("IDs cannot be null!");
                    
                } else{
                    isStudentNameExist = stdList.getIdIndex(studentID);
                    isSubjectNameExist = subList.getIdIndex(subjectID);
                    if(isStudentNameExist == -1){
                        System.out.println("Student ID Does Not Exist!");
                    }
                    if(isSubjectNameExist == -1){
                        System.out.println("Subject ID Does not Exist");
                    }
                }
                
                
            }while(isStudentNameExist != -1 || isSubjectNameExist != -1);
            
            
            

        }while(isContinue = true);
        
       }
               
    }
}
