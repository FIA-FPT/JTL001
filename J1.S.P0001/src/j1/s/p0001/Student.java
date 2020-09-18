/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package j1.s.p0001;

import java.util.Comparator;

/**
 *
 * @author ACER
 */
public class Student implements Comparator<Student> {
    String id;
    String name;
    String courseName;
    int semester;

    /**
     *
     */
    
    public Student() {
    }

    public Student(String name, String courseName) {
        this.name = name;
        this.courseName = courseName;
    }

    public Student(String id, String name, String courseName, int semester) {
        this.id = id;
        this.name = name;
        this.courseName = courseName;
        this.semester = semester;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public int getSemester() {
        return semester;
    }

    public void setSemester(int semester) {
        this.semester = semester;
    }
    
    @Override
    public boolean equals(Object o) {
        Student s;
        if(!(o instanceof Student)){
            return false;
        } else{
            s = (Student) o;
            if(this.getName().equals(s.getName()) && (this.getCourseName().equals(s.getCourseName()))){
                return true;
            } else{
                return false;
            }
        }
    }

    @Override
    public int compare(Student t, Student t1) {
        return t.getName().compareTo(t1.getName());
    }
    
}
