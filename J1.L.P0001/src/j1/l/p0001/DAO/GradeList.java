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
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.Set;

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
    
    
    //Check if the record is already exist in the database, If yes return the index of it
    public Integer getGradeIndex(String studentID, String subjectID){
        for(int i = 0; i < size(); i++){
            if(this.get(i).getStudentID().equals(studentID)  && this.get(i).getSubjectID().equals(subjectID)){
                return i;
            }
        }
        return -1;
    }
    
    
    //Add new grade to the list
    public void addGrade(){
       String studentID;
       String subjectID;
        
       boolean isNull = true;
       boolean isCreditValid = false;
       boolean isContinue = true;
       boolean isOverwriteConfirm = false ;
      
       
       Integer studentPos = -1;
       Integer subjectPos = -1;      
       Integer gradeIndex = -1;
       
       
       Double FE = null;
       Double lab = null;
       Double progressTest = null;
       
       
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
                studentPos = stdList.getIdIndex(studentID);
                if(studentPos == -1){
                    System.out.println("ID does not exist! ");
                }
                
            }while(studentPos == -1);
            
            
            do{
                System.out.print("Enter Subject ID:  ");
                subjectID = sc.nextLine();
                subjectPos = subList.getIdIndex(subjectID);
                if(studentPos == -1){
                    System.out.println("ID does not exist! ");
                }
            }while(subjectPos == -1);
               
                
                
            
            
            
            //Check if the user want to overwrite the score? if not continue the process
            
            if((gradeIndex = getGradeIndex(studentID,subjectID)) != -1 ){
                
                isOverwriteConfirm = EssentialUtils.chooseYN("Do you want to overwrite this grade? (Y/N): ");
                
                if(!isOverwriteConfirm){
                    System.out.println("Operation Aborted!");
                    break;
                } else{
                    
                    
                    //Loop until the input correct
                    
                    try{
                        do{
                            System.out.print("Input Progress Test Score: ");
                            progressTest = sc.nextDouble();
                            isCreditValid = EssentialUtils.isCreditValid(progressTest);
                            if(!isCreditValid){
                                System.out.println("Input invalid, try again!");
                            }

                        }while(!isCreditValid);

                        do{
                            System.out.print("Input Lab Score: ");
                            lab = sc.nextDouble();
                            isCreditValid = EssentialUtils.isCreditValid(lab);
                            if(!isCreditValid){
                                System.out.println("Input invalid, try again!");
                            }
                        }while(!isCreditValid);
                            System.out.print("Input Final Exam Score: ");
                            FE = sc.nextDouble();
                            isCreditValid = EssentialUtils.isCreditValid(FE);
                            if(!isCreditValid){
                                System.out.println("Input invalid, try again!");
                            }
                        



                    }catch(InputMismatchException e){
                        System.out.println("Input mismatched!");
                    }
                    this.get(gradeIndex).setFE(FE);
                    this.get(gradeIndex).setLab(lab);
                    this.get(gradeIndex).setProgressTest(progressTest);
                    
                }
                
            } else{
                 //Loop until the input correct
                    
                    try{
                        do{
                            System.out.print("Input Progress Test Score: ");
                            progressTest = sc.nextDouble();
                            isCreditValid = EssentialUtils.isCreditValid(progressTest);
                            if(!isCreditValid){
                                System.out.println("Input invalid, try again!");
                            }

                        }while(!isCreditValid);

                        do{
                            System.out.print("Input Lab Score: ");
                            lab = sc.nextDouble();
                            isCreditValid = EssentialUtils.isCreditValid(lab);
                            if(!isCreditValid){
                                System.out.println("Input invalid, try again!");
                            }
                        }while(!isCreditValid);
                            System.out.print("Input Final Exam Score: ");
                            FE = sc.nextDouble();
                            isCreditValid = EssentialUtils.isCreditValid(FE);
                            if(!isCreditValid){
                                System.out.println("Input invalid, try again!");
                            }
                        



                    }catch(InputMismatchException e){
                        System.out.println("Input mismatched!");
                    }
                    this.add(new Grade(studentID,subjectID,lab,progressTest,FE));
            }
            
            isContinue = EssentialUtils.chooseYN("Do you want to continue? (Y/N): ");

        }while(isContinue);
        
       }
       
       
       
    }
    
    public void printStudentReport(){
        
        boolean isNull;
        
        Integer idIndex;
        
        String studentID;
        
        do{
                    System.out.print("Input Student ID:  ");
                    studentID = sc.nextLine();
                    idIndex = stdList.getIdIndex(studentID);
                    isNull = EssentialUtils.isEmptyString(studentID);
                    if(idIndex == -1){
                        System.out.println("Code Did Not Exist, try again!");
                    }
                    if(isNull){
                        System.out.println("ID cannot be null!");
                    }
        }while(idIndex == -1 || isNull);
        
        System.out.println(String.format("Student ID: %s", studentID));
        System.out.println(String.format("Student Name: %s %s", stdList.get(idIndex).getFirstName(),stdList.get(idIndex).getLastName()));
        
        System.out.println(String.format("| %-3s | %-40s | %-14s | %-10s |","No.","Subject Name","Average","Status"));
        
        for(int i = 0; i < size();i++){
            if(this.get(i).getStudentID().equals(studentID)){
                System.out.format("| %-3s | %-40s | %-14.2f | %-10s |",
                        (i+1),
                        subList.get(subList.getIdIndex(get(i).getSubjectID())).getName(),                       
                        get(i).getAverage(),
                        get(i).checkPassed() ? "Passed" : "Not Passed");
            }
        }
    }
    
    public void printSubjectReport(){
        boolean isNull;
        
        Integer idIndex;
        
        String subjectID;
        
        do{
                    System.out.print("Input Subject ID:  ");
                    subjectID = sc.nextLine();
                    idIndex = subList.getIdIndex(subjectID);
                    isNull = EssentialUtils.isEmptyString(subjectID);
                    if(idIndex == -1){
                        System.out.println("Code Did Not Exist, try again!");
                    }
                    if(isNull){
                        System.out.println("ID cannot be null!");
                    }
        }while(idIndex == -1 || isNull);
        
        
        
        System.out.println(String.format("Subject ID: %s", subjectID));
        System.out.println(String.format("Subject Name: %s", subList.get(idIndex).getName()));
        
        System.out.println(String.format("| %-3s | %-40s | %-14s | %-10s |","No.","Student Name","Average","Status"));
        
        for(int i = 0; i < size();i++){
            if(this.get(i).getSubjectID().equals(subjectID)){
                StringBuilder name = new StringBuilder();
                name =  name.append(stdList.get(stdList.getIdIndex(get(i).getStudentID())).getFirstName()).
                        append(" ").
                        append(stdList.get(stdList.getIdIndex(get(i).getStudentID())).getLastName());
                System.out.println(String.format("| %-3d | %-40s | %-14.2f | %-10s |"
                        ,(i+1)                 
                        ,name                       
                        ,get(i).getAverage(),get(i).checkPassed() ? "Passed" : "Not Passed"));
            }
        }
    }
    
}
