/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package j1.l.p0001.DAO;

import j1.l.p0001.DTO.Student;
import j1.l.p0001.Utils.Menu;
import j1.l.p0001.Utils.EssentialUtils;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author ACER
 */
public class StudentList extends ArrayList<Student> {
    Scanner sc = new Scanner(System.in);
    
    public Integer getIdIndex(String ID){
        for(int i = 0; i < size(); i++){
            if(get(i).getId().equals(ID)){
                return i;
            }
        }
        return -1;
    }
    
    
    public void addStudent(){
        Integer idIndex = -1;
        
        
        boolean isNull = true;
        boolean continueChoice = true;
        boolean isDOBVerified = false;
        boolean isEmailVerified = false;
        boolean isPhoneNumberVerified = false;
        
        String id;
        String firstName;
        String lastName;
        String gender;
        String DOB;
        String email;
        String phoneNumber;
        String userChoice = null;
        
        
        
      
        
        //Loop until User Choice = N
            do{


                //Input ID and check with the database


                do{
                    System.out.print("Input Student ID:  ");
                    id = sc.nextLine();
                    idIndex = getIdIndex(id);
                    isNull = EssentialUtils.isEmptyString(id);
                    if(idIndex != -1){
                        System.out.println("Code existed try again!");
                    }
                    if(isNull){
                        System.out.println("ID cannot be null!");
                    }
                }while(idIndex != -1 || isNull);

                //Input First Name


                do{
                    System.out.print("Input First name:  ");
                    firstName = EssentialUtils.capitalizeName(sc.nextLine());
                    isNull = EssentialUtils.isEmptyString(firstName);
                    if(isNull){
                        System.out.println("First name or Last name is empty! Try again!");
                    }

                }while(isNull);
                
                
                //Input Last Name
                do{

                    System.out.print("Input Last name:  ");
                    lastName =  EssentialUtils.capitalizeName(sc.nextLine());       
                    isNull = EssentialUtils.isEmptyString(lastName);

                    if(isNull){
                        System.out.println("First name or Last name is empty! Try again!");
                    }

                }while(isNull);

                //Input Gender


                do{

                    Menu genderMenu = new Menu();


                    genderMenu.addItems("Male");
                    genderMenu.addItems("Female");
                    genderMenu.addItems("Others");

                    genderMenu.printMenu();
                    Integer choice = genderMenu.getChoice();

                    switch(choice){
                        case 1:
                            gender = "Male";
                            break;
                        case 2:
                            gender = "Female";
                            break;
                        case 3:
                            gender = "Other";
                            break;
                        default:
                            gender = null;                                          
                        }


                    isNull = EssentialUtils.isEmptyString(gender);
                    if(isNull){
                        System.out.println("Gender cannot be null! Try Again");
                    }


                }while(isNull);


                //Input DOB
                do{
                    System.out.print("Input Studentss Date of Birth(Format DD/MM/YYYY):  ");
                    DOB = sc.nextLine();
                    isDOBVerified = EssentialUtils.isDateValid(DOB);
                    if(!isDOBVerified){
                        System.out.println("Date Of Birth format error, please try again!");
                    }

                }while(!isDOBVerified);


                //Input email
                do{
                    System.out.print("Input email addresss here:  ");
                    email = sc.nextLine();
                    isEmailVerified = EssentialUtils.isEmailValid(email);
                    if(!isEmailVerified){
                        System.out.println("Email address invalid! Try Again! ");
                    }
                }while(!isEmailVerified);


                //Input Phone Number
                do{
                    System.out.print("Input phone number here:  ");
                    phoneNumber = sc.nextLine();
                    isPhoneNumberVerified = EssentialUtils.isPhoneNumberValid(phoneNumber);

                    if(!isPhoneNumberVerified){
                        System.out.println("Phone Number invalid! Try again!");
                    }
                }while(!isPhoneNumberVerified);

                //Adding Student to the list

                add(new Student(id,firstName,lastName,gender,DOB,email,phoneNumber));

                //Asking user whether if it want to continue or exit
                continueChoice = EssentialUtils.chooseYN("Do you want to continue adding? (Y/N) : ");

            }while(continueChoice == true);
        
        
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
            System.out.println("Student Does Not Exist!");
        }else{
            //Menu for choosing update or delete
                Menu UDmenu = new Menu();
                UDmenu.addItems("Update");
                UDmenu.addItems("Delete");
                UDmenu.addItems("Exit");

                
                UDmenu.printMenu();
                Integer choice = UDmenu.getChoice();
                switch(choice){
                    case 1:
                        updateStudent(checkPos);
                        break;
                    case 2:
                        deleteStudent(checkPos);
                        break;
                    default:
                        System.out.println("Update and Delete Menu Exited!");

                }
            }
        } 
   }
    
    
    //Delete a Student based Position
    private void deleteStudent(Integer posID){
        
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
    
    //Update Student
    private void updateStudent(Integer posID){
        
        
        Menu updateMenu = new Menu();
        updateMenu.add("Change First Name");
        updateMenu.add("Change Last Name");
        updateMenu.add("Change Date Of Birth");
        updateMenu.add("Change Email");
        updateMenu.add("Change Gender");
        updateMenu.add("Delete");
        
        
        
        Integer choice = 0;
        
        boolean isNull = true;
        boolean isEmailVerified = false;
        boolean isPhoneNumberVerified = false;
        boolean isDOBVerified = true;
        boolean changed = false;
        
        String DOB;
        String firstName;
        String lastName;
        String email;
        
        updateMenu.printMenu();
        choice = updateMenu.getChoice();
        
        switch(choice){
            
            //Update First Name
            case 1:
                
                
                System.out.print("Input First Name:  ");
                firstName = sc.nextLine();
                isNull = EssentialUtils.isEmptyString(firstName);
                if(!isNull){
                   changed = true;
                   this.get(posID).setFirstName(firstName);
                }
                break;
                
            //Update Last Name
            case 2:
                System.out.print("Input Last Name:  ");
                lastName = sc.nextLine();
                isNull = EssentialUtils.isEmptyString(lastName);
                if(!isNull){
                   changed = true;
                   this.get(posID).setFirstName(lastName);
                }
                break;
                
            //Update Date Of Birth
            case 3:
                do{
                    System.out.print("Input Date Of Birth:  ");
                    DOB = sc.nextLine();
                    isNull = EssentialUtils.isEmptyString(DOB);
                    isDOBVerified = EssentialUtils.isDateValid(DOB);
                    if(isNull){
                        break;
                    }
                }while(!isDOBVerified);
                if(isNull){
                    break;
                }
                this.get(posID).setDOB(DOB);
                changed = true;
                break;
                
            //Update Email
            case 4:
                do{
                    
                    System.out.print("Input Email:  ");
                    email = sc.nextLine();
                    isNull = EssentialUtils.isEmptyString(email);
                    isEmailVerified = EssentialUtils.isEmailValid(email);
                    if(isNull){
                        break;
                    }
                }while(!isEmailVerified);
                if(isNull){
                    break;
                }
                this.get(posID).setEmail(email);
                changed = true;
                break;
                   
           
                
                
        }
        if(changed){
            System.out.println("Update successfully");
        } else{
            System.out.println("Nothing updated");
        }
    }
    
    public void print(){
        for(Student s : this){
            System.out.println(s);
        }
    }
    
 
    
  
   
    

}
