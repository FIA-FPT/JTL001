/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package j1.l.p0001.DAO;

import j1.l.p0001.DTO.Grade;
import j1.l.p0001.Utils.EssentialUtils;
import java.util.ArrayList;
import java.util.Collections;
import java.util.InputMismatchException;
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
       sc = new Scanner(System.in);
       String studentID;
       String subjectID;
        
       boolean isNull = true;
       boolean isGradeValid = false;
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
            
            sc = new Scanner(System.in);
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
                subjectID = sc.nextLine().toUpperCase();
                subjectPos = subList.getIdIndex(subjectID);
                if(subjectPos == -1){
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
                            isGradeValid = EssentialUtils.isGradeValid(progressTest);
                            if(!isGradeValid){
                                System.out.println("Input invalid, try again!");
                            }

                        }while(!isGradeValid);

                        do{
                            System.out.print("Input Lab Score: ");
                            lab = sc.nextDouble();
                            isGradeValid = EssentialUtils.isGradeValid(lab);
                            if(!isGradeValid){
                                System.out.println("Input invalid, try again!");
                            }
                        }while(!isGradeValid);
                            System.out.print("Input Final Exam Score: ");
                            FE = sc.nextDouble();
                            isGradeValid = EssentialUtils.isGradeValid(FE);
                            if(!isGradeValid){
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
                            isGradeValid = EssentialUtils.isGradeValid(progressTest);
                            if(!isGradeValid){
                                System.out.println("Input invalid, try again!");
                            }

                        }while(!isGradeValid);

                        do{
                            System.out.print("Input Lab Score: ");
                            lab = sc.nextDouble();
                            isGradeValid = EssentialUtils.isGradeValid(lab);
                            if(!isGradeValid){
                                System.out.println("Input invalid, try again!");
                            }
                        }while(!isGradeValid);
                            System.out.print("Input Final Exam Score: ");
                            FE = sc.nextDouble();
                            isGradeValid = EssentialUtils.isGradeValid(FE);
                            if(!isGradeValid){
                                System.out.println("Input invalid, try again!");
                            }
                        



                    }catch(InputMismatchException e){
                        System.out.println("Input mismatched!");
                    }
                    this.add(new Grade(studentID,subjectID,lab,progressTest,FE));
                    stdList.get(stdList.getIdIndex(studentID)).canDelete = false;
                    subList.get(subList.getIdIndex(subjectID)).canDelete = false;
                    
            }
            
            isContinue = EssentialUtils.chooseYN("Do you want to continue? (Y/N): ");

        }while(isContinue);
        
       }
       
       
       
    }
    
    public void printStudentReport(){
        sc = new Scanner(System.in);
        boolean isNull;
        
        Integer idIndex;
        
        String studentID;
        if(isEmpty()){
            System.out.println("List is empty!");
            return ;
        }
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
        
        //Sorting By Subject Name
        Collections.sort(this, (Grade t, Grade t1) -> {
            String subjectID1 = t.getSubjectID();
            String subjectID2 = t1.getSubjectID();
            return subList.get(subList.getIdIndex(subjectID1)).compareTo(subList.get(subList.getIdIndex(subjectID2)));
        });
        
        System.out.println(String.format("Student ID: %s", studentID));
        System.out.println(String.format("Student Name: %s %s", stdList.get(idIndex).getFirstName(),stdList.get(idIndex).getLastName()));
        
        System.out.println(String.format("| %-3s | %-40s | %-14s | %-10s |","No.","Subject Name","Average","Status"));
        Integer j = 1;
        for(int i = 0; i < size();i++){
            if(this.get(i).getStudentID().equals(studentID)){
                System.out.println(String.format("| %-3s | %-40s | %-14.2f | %-10s |",
                        j,
                        subList.get(subList.getIdIndex(get(i).getSubjectID())).getName(),                       
                        get(i).getAverage(),
                        get(i).checkPassed() ? "Passed" : "Not Passed"));
                j++;
            }
        }
    }
    
    public void printSubjectReport(){
        sc = new Scanner(System.in);
        boolean isNull;
        
        Integer idIndex;
        
        String subjectID;
        
        if(isEmpty()){
            System.out.println("List is empty!");
            return ;
        }
        
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
        
        //Sorting Student ID by name
        Collections.sort(this, (Grade t, Grade t1) -> {
            String studentID1 = t.getStudentID();
            String studentID2 = t1.getStudentID();
            return stdList.get(stdList.getIdIndex(studentID1)).compareTo(stdList.get(stdList.getIdIndex(studentID2)));
        });
        
       
      
        System.out.println(String.format("Subject ID: %s", subjectID));
        System.out.println(String.format("Subject Name: %s", subList.get(idIndex).getName()));
        
        System.out.println(String.format("| %-10s | %-40s | %-14s | %-10s |","Student ID","Student Name","Average","Status"));
        
        for(int i = 0; i < size();i++){
            if(this.get(i).getSubjectID().equals(subjectID)){
                
                String name = stdList.get(stdList.getIdIndex(this.get(i).getStudentID())).getName();
                System.out.println(String.format("| %-10s | %-40s | %-14.2f | %-10s |"
                        ,this.get(i).getStudentID()               
                        ,name                       
                        ,get(i).getAverage(),get(i).checkPassed() ? "Passed" : "Not Passed"));
            }
        }
    }
    
}
