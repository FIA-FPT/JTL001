package j1.s.p0001;


import java.util.ArrayList;
import java.util.Scanner;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author ACER
 */
public class Menu extends ArrayList<String>{
    Scanner sc = new Scanner(System.in);
    public Menu() {
        super();
    }
    public void addItems(String item){
        this.add(item);
    }
    
    public int getChoice(){
        System.out.println("Input your choice: ");
        return sc.nextInt();
    }
    public void printMenu(){
        for(int i = 0; i < size();i++){
            System.out.println(i + ". " + get(i));
        }
    }
}
