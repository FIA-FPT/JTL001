/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package j1.s.p0001;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

/**
 *
 * @author ACER
 */
public class StudentList extends ArrayList<Student> {
    Set<Student> distinctStudentList = new TreeSet<>();                 //For Distinctive Student Name, For calculating frequency
    Scanner sc = new Scanner(System.in);
    
    public StudentList() {
        super();
    }
    public String chooseCourse(){
        String courseName;
        System.out.println("Input Course");

        Menu courseList = new Menu();
        courseList.addItems("Java");
        courseList.addItems(".NET");
        courseList.addItems("C/C++");
        System.out.println("Default is Java");
        courseList.printMenu();
        int choice = courseList.getChoice();


        switch(choice){
            case 1:
                courseName = "Java";
                break;
            case 2:
                courseName = "C/C++";
                break;
            case 3:
                courseName = ".NET";
                break;
            default:
                courseName = "Java";
                break;
        }
        return courseName;
    }
    public void add(){                      //Adding new student, at least 3
        String courseName;
        boolean cont = true;
        int time = 0;
        do
        {
            System.out.println("Input name");
            String name = sc.nextLine();       

            courseName = chooseCourse();


            System.out.println("Input semester");
            int semester = sc.nextInt();


            distinctStudentList.add(new Student(name,courseName));
            add(new Student(Integer.toString(size()),name,courseName,semester));
            time++;
            if(time > 3){
                System.out.println("Continue? Y/N");
                String contChoice = sc.nextLine().toUpperCase();
                if(contChoice == "Y"){
                    cont = true;
                } else cont = false;
            }
        }while(time < 3 || cont == true);
        
        
    }
    public void find(){
        System.out.println("Input name");   //Find for name
        String name = sc.nextLine();
        Collections.sort(this,new Student());
        for(int i = 0; i < size();i++){
            System.out.println(String.format("Student Name: %-20s Semester: %d Course Name: %-10s ",get(i).getName(),get(i).getSemester(),get(i).getCourseName()));
            
        }
        
    }
    
    
    public int seek(String name){
        for(int i = 0; i < size(); i++){
            if(get(i).getName().equals(name)){
                return i;
            }
        }
        return -1;
    }
    public void updateAndDelete(){
        System.out.println("Enter name");
        String name = sc.nextLine();
        int seekPos = seek(name);
        if(seekPos == -1){
            System.out.println("No name was found");
        }else{
            Menu UDMenu = new Menu();
            UDMenu.addItems("Update");
            UDMenu.addItems("Delete");
            UDMenu.addItems("None");
            UDMenu.printMenu();
            int choice = UDMenu.getChoice();
            switch(choice){
                case 1:
                    Menu updateMenu = new Menu();
                    updateMenu.addItems("Semester");
                    updateMenu.addItems("Course Name");
                    updateMenu.addItems("None");
                    updateMenu.printMenu();
                    int updateChoice = UDMenu.getChoice();
                    switch(updateChoice){
                        case 1:
                            System.out.println("Enter Semester");
                            int semester = sc.nextInt();
                            get(seekPos).setSemester(semester);
                        case 2:
                            System.out.println("Enter Course");
                            String courseName = chooseCourse();
                            get(seekPos).setCourseName(courseName);
                        default:
                            System.out.println("None was chosen");
                    }
                    break;
                case 2:
                    this.remove(get(seekPos));
                    System.out.println("Deleted!");
            }
        }
        
    }
}
