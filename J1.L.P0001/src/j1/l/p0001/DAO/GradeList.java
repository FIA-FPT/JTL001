/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package j1.l.p0001.DAO;

import j1.l.p0001.DTO.Grade;
import j1.l.p0001.DAO.StudentList;
import j1.l.p0001.DAO.SubjectList;
import java.util.ArrayList;

/**
 *
 * @author ACER
 */
public class GradeList extends ArrayList<Grade> {
    StudentList stdList = new StudentList();
    SubjectList subList  = new SubjectList();

    public GradeList(StudentList stdList, SubjectList subList) {
        this.stdList = stdList;
        this.subList = subList;
    }
    
}
