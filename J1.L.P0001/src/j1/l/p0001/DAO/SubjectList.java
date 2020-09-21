/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package j1.l.p0001.DAO;

import j1.l.p0001.DTO.Subject;
import j1.l.p0001.Utils.EssentialUtils;
import j1.l.p0001.Utils.Menu;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 *
 * @author ACER
 */
public class SubjectList extends ArrayList<Subject> {
    
    
    Scanner sc = new Scanner(System.in);
    
    
    public Integer getIdIndex(String ID){
        for(int i = 0; i < size(); i++){
            if(get(i).getID().equals(ID)){
                return i;
            }
        }
        return -1;
    }
    //Adding new Subject
    
    public void addSubject(){
        String subjectID;
        String subjectName;
        
        Double credit = null;
        
        Integer idIndex;
        
        boolean isNull = true;
        boolean isCreditValid = true;
        boolean isContinue = true;
        
        do{
            
            //Inputting Subject ID
            do{
                System.out.print("Input Subject ID:  ");
                subjectID = sc.nextLine().toUpperCase();
                idIndex = getIdIndex(subjectID);
                isNull = EssentialUtils.isEmptyString(subjectID);
                if(idIndex != -1){
                    System.out.println("ID existed try again!");
                }
                if(isNull){
                    System.out.println("ID cannot be null!");
                }
            }while(idIndex != -1 || isNull);
            
            
            //Inputting Subject Name
            do{
                
                System.out.print("Input Subject name:  ");
                subjectName = EssentialUtils.capitalizeName(sc.nextLine());
                isNull = EssentialUtils.isEmptyString(subjectName);

                if(isNull){
                    System.out.println("First name or Last name is empty! Try again!");
                }

            }while(isNull);
            
            
            //Inputting Credit
            do{
                try{
                    System.out.print("Input Subject Credit Score:   ");
                    credit = sc.nextDouble();
                    isCreditValid = EssentialUtils.isCreditValid(credit);
                    if(!isCreditValid){
                        System.out.println("Credit must be in range 0 - 10");
                    }
                }catch(InputMismatchException e){
                    System.out.println("Input mismatched!");     
                    isCreditValid = false;
                }
            }while(!isCreditValid);
            
            this.add(new Subject(subjectID,subjectName,credit));
            //Ask user whether to continue adding subject or not
            
            isContinue = EssentialUtils.chooseYN("Do you want to continue? (Y/N):  ");
            
            
        }while(isContinue);
        
        
    }    
        
    
    //Menu for update and remove
    
    public void menuUpdateAndRemove(){
        
        
       //Check if the list is empty 
       if(this.isEmpty()){
           System.out.println("The List Is Empty!");
       } 
       else{
           
        String id;
        Integer checkPos = -1;
        
        System.out.println("Input your ID here");
        id = sc.nextLine();
        
        if((checkPos = getIdIndex(id)) == -1){
            System.out.println("Subject Code Does Not Exist!");
        }else{
            //Menu for choosing update or delete
                Menu UDmenu = new Menu("Subject Update and Delete");
                UDmenu.addItems("Update");
                UDmenu.addItems("Delete");
                UDmenu.addItems("Exit");

                
                UDmenu.printMenu();
                Integer choice = UDmenu.getChoice();
                switch(choice){
                    case 1:
                        updateSubject(checkPos);
                        break;
                    case 2:
                        deleteSubject(checkPos);
                        break;
                    default:
                        System.out.println("Update and Delete Menu Exited!");

                }
            }
        } 
   }
    
    
    //Delete a Student based Position
    private void deleteSubject(Integer posID){
        
        boolean confirm = false;
        
        
        confirm = EssentialUtils.chooseYN("Are you sure you want to delete this student? (Y/N):  ");
        
        if(!confirm){
            
            System.out.println("Delete Aborted!");
            
        }else{
        
            if(!get(posID).canDelete){
                System.out.println("Cannot delete this student!");
            } else{
                try{
                    
                    remove(get(posID));
                    System.out.println("Delete Succescful");
                    
                }catch(Exception e){
                    System.out.println("Delete Failed");
                }
            }
        }
    }
    private void updateSubject(Integer posID){
        //Initalize the Menu
        Menu updateMenu = new Menu("Subject Update Menu:");
        updateMenu.addItems("Update Subject Name");
        updateMenu.addItems("Update Credit Score");
        
        
        String subjectName;
               
        Double creditScore;
        
        Integer choice;
        
        boolean isNull = true;
        boolean isCreditValid = false;
        
        boolean changed = false;
        
        updateMenu.printMenu();
        choice = updateMenu.getChoice();
        
        //Using case switch to make decision
        switch(choice){
            case 1:
                System.out.println("Input new Subject Name to update");
                subjectName = sc.nextLine();
                isNull = EssentialUtils.isEmptyString(subjectName);
                if(!isNull){
                    this.get(posID).setName(subjectName);
                    changed = true;
                } else{
                    changed = false;
                }
                break;
            case 2:
                do{
                try{
                    System.out.println("Input new Credit to update");
                    creditScore = sc.nextDouble();
                    if(creditScore == 0){
                        changed = false;
                        break;
                    }else{
                        isCreditValid = EssentialUtils.isCreditValid(creditScore);
                        if(isCreditValid){
                            
                            this.get(posID).setCredit(creditScore);
                            changed = true;
                            
                        }
                    }
                }catch(InputMismatchException e){
                    System.out.println("Input mismatched!");
                    
                }
                }while(!isCreditValid);
            
        }
        if(changed){
            System.out.println("Changed successfully");
        } else{
            System.out.println("Nothing Changed");
        }
        
        
        
    }
    
}
