/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package j1.l.p0001.Utils;

import com.sun.xml.internal.ws.util.StringUtils;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author ACER
 */
public class EssentialUtils {
    private static final String PHONE_PATTERN = "^[0-9]{10,12}$";
    private static final Pattern EMAIL_PATTERN = Pattern.compile("^\\w+[A-Z0-9._%+-]?+@[A-Z0-9.-]+\\.[A-Z]{2,6}$",Pattern.CASE_INSENSITIVE);
    private static final String DATE_FORMAT = "dd/MM/yyyy";
    
    
    private static Scanner sc = new Scanner(System.in);
    
    //Validate phone number through RegExp
    public static boolean isPhoneNumberValid(String number){
        return number.matches(PHONE_PATTERN);
    }
    
    //Validate email through RegExp
    public static boolean isEmailValid(String email){
        Matcher matcher = EMAIL_PATTERN.matcher(email);
        boolean isValid = matcher.find();
        return isValid;
    }
    
    //Validate Date through date
    public static boolean isDateValid(String date){
        SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);
        dateFormat.setLenient(false);
        try{
            dateFormat.parse(date);
            
        }catch(ParseException e){
            return false;
        }
        return true;
    }
    
    //Check null and empty String
    public static boolean isEmptyString(String string){
        return string == null || string.isEmpty();
    }
    
    //Capitalize first word of name
    public static String capitalizeName(String name){
        name = name.toLowerCase().trim();
        String[] nameArray = name.split("\\s");
        StringBuilder newName = new StringBuilder();
        for(String word : nameArray){
            newName.append(StringUtils.capitalize(word));
            newName.append(" ");
        }
        return newName.toString().trim();
    }
    
    //Utility for checking returning yes or no with Question dialog
    public static boolean chooseYN(String questionDialog){
        String userChoice = null;
        boolean choice = false;
        do{
            System.out.print(questionDialog);
            userChoice = sc.nextLine().toUpperCase();
            if(userChoice.equals("N")){
                choice = false;
            }else if(userChoice.equals("Y")){
                choice = true;
            } else{
                    System.out.println("Must be Y or N");
                }
        }while(!"N".equals(userChoice) && !"Y".equals(userChoice));
        
        return choice;
    }
    
    //Utility for checking grade score. Must be positive number and not null
    public static boolean isGradeValid(Double grade){
        if(grade < 0 || grade > 10){
            return false;
        }       
        return true;
    }
    
    public static boolean isCreditValid(Double credit){
        if(credit < 0 || credit > 30){
            return false;
        }
        return true;
    }
    
}
