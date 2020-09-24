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
public class Subject implements Comparable<Subject> {
    String ID,name;
    Double credit;
    public boolean canDelete = true;

    public Subject(String ID, String name, Double credit) {
        this.ID = ID;
        this.name = name;
        this.credit = credit;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getCredit() {
        return credit;
    }

    public void setCredit(Double credit) {
        this.credit = credit;
    }
    
    @Override
    public int compareTo(Subject t) {
        return this.getName().compareTo(t.getName());
    }
    
    
}
