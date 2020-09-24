/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package j1.l.p0001.DTO;

import java.util.Comparator;

/**
 *
 * @author ACER
 */
public class Grade {
    
    private String studentID;
    private String subjectID;
    private Double lab,progressTest,FE;

    public Grade(String studentID, String subjectID, Double lab, Double test, Double FE) {
        this.studentID = studentID;
        this.subjectID = subjectID;
        this.lab = lab;
        this.progressTest = test;
        this.FE = FE;
    }

    public Double getLab() {
        return lab;
    }

    public void setLab(Double lab) {
        this.lab = lab;
    }

    public Double getProgressTest() {
        return progressTest;
    }

    public void setProgressTest(Double progressTest) {
        this.progressTest = progressTest;
    }

    public Double getFE() {
        return FE;
    }

    public void setFE(Double FE) {
        this.FE = FE;
    }
    
    public Double getAverage(){
        return 0.3*lab + 0.2*progressTest + 0.5*FE;
        
    }
    public String getStudentID() {
        return studentID;
    }

    public void setStudentID(String studentID) {
        this.studentID = studentID;
    }

    public String getSubjectID() {
        return subjectID;
    }

    public void setSubjectID(String subjectID) {
        this.subjectID = subjectID;
    }
    
    public boolean checkPassed(){
        return getAverage() > 5.0;   
    }
    
   
    
}
