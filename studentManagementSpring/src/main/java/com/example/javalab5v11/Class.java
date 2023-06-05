package com.example.javalab5v11;

import java.util.*;

public class Class {

    String nazwaGrupy;
    double maksymalnaIloscStudentow;
    int id;
    public List<Student> students = new ArrayList<>();

    public Class(int id, String nazwaGrupy, List<Student> students,double maksymalnaIloscStudentow) {
        this.id=id;
        this.nazwaGrupy = nazwaGrupy;
        this.students = students;
        this.maksymalnaIloscStudentow = maksymalnaIloscStudentow;
    }

    public double getSize(){
        return students.size();
    }
    public void addStudent(Student student) {
        students.add(student);
    } //dziala
    public void addPoints(Student student, double punkty) {
        student.iloscPunktow = student.getIloscPunktow() + punkty;
    }
    public void getStudent(Student student) {
        if(student.iloscPunktow <= 0){
            students.remove(student);
        }
    }
    public void changeCondition(Student student, StudentCondition obecnosc) {
        student.stanStudenta = obecnosc;
    }
    public void removePoints(Student student, double punkty) {
        student.iloscPunktow = student.getIloscPunktow() - punkty;
    }
    public List<Student> search(String nazwisko) {
        List<Student> searched = new ArrayList<>();
        int j=0;
        for(Student student : students){
            if(student.compare(nazwisko) == 0){
                j++;
                searched.add(student);
            }
        }
        if(j==0) {
            System.out.println("Nie znaleziono studenta o nazwisku: "+ nazwisko);
        }
        return searched;
    }
    public List<Student> searchPartial(String s){
        int j=0;
        s = s.toLowerCase();
        List<Student> searched = new ArrayList<>();
        for(Student student : students) {
            if(student.imie.toLowerCase().contains(s) || student.nazwisko.toLowerCase().contains(s))
            {
                searched.add(student);
                j++;
            }
        }
        if(j==0) {
            System.out.println("Nie znaleziono studenta o podanej czesci nazwiska lub imienia: "+ s);
        }
        return searched;
    }
    public void countByCondition(StudentCondition obecnosc){
        for(Student student : students){
            if(student.stanStudenta == obecnosc)
            {
                student.print();
            }
        }
    }
    public void summary(){
        if (students.size() > 0) {
            for(Student student : students){
                student.print();
            }
        }
        else{
            System.out.println("Lista studentow jest pusta");
        }

    }
    public void sortByName(){
        if (students.size() > 0) {
            Collections.sort(students, new Comparator<Student>() {
                @Override
                public int compare(final Student object1, final Student object2) {
                    return object1.getNazwisko().compareTo(object2.getNazwisko());
                }
            });
        }
    }
    public void sortByPoints(){
        if (students.size() > 0) {
            Collections.sort(students, new Comparator<Student>() {
                @Override
                public int compare(final Student object1, final Student object2) {
                    return Double.compare(object1.getIloscPunktow(), object2.getIloscPunktow());
                }
            });
        }
    }
    public void max(){
        if (students.size() > 0) {
            Student max = Collections.max(students, new Comparator<Student>() {
                @Override
                public int compare(final Student object1, final Student object2) {
                    return Double.compare(object1.getIloscPunktow(), object2.getIloscPunktow());
                }
            });
            System.out.println(max.iloscPunktow);
        }
    }

}
