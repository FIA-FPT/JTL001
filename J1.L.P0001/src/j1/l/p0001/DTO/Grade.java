/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package j1.l.p0001.DTO;

/**
 *
 * @author ACER
 */
public class Grade {
    private String studentID;
    private String subjectID;
    private Double lab,test,FE;

    public Grade(String studentID, String subjectID, Double lab, Double test, Double FE) {
        this.studentID = studentID;
        this.subjectID = subjectID;
        this.lab = lab;
        this.test = test;
        this.FE = FE;
    }

    public Double getLab() {
        return lab;
    }

    public void setLab(Double lab) {
        this.lab = lab;
    }

    public Double getTest() {
        return test;
    }

    public void setTest(Double test) {
        this.test = test;
    }

    public Double getFE() {
        return FE;
    }

    public void setFE(Double FE) {
        this.FE = FE;
    }
    
    private Double getAverage(){
        return 0.3*lab + 0.2*test + 0.5*FE;
        
    }
}
