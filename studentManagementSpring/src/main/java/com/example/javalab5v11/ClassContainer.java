package com.example.javalab5v11;

import java.util.*;

public class ClassContainer {
    public Map<Integer, Class> grupy;

    public ClassContainer(Map<Integer, Class> grup) {
        grupy = grup;
    }

    public void addClass(int id, String nazwa, List<Student> grupa, double pojemnosc) {
        grupy.put(id, new Class(id, nazwa, grupa, pojemnosc));
        //System.out.println("Dodano grupe:"+grupy.size());
    }

    public void removeClass(int id)
    {
        grupy.remove(id);
    }
    public List<Class> findEmpty(){
        int j=0;
        List<Class> empty = new ArrayList<>();
        for (Class grupa : grupy.values()) {
            if(grupa.getSize()==0) {
                System.out.println("Grupa "+grupa.nazwaGrupy+" jest pusta");
                empty.add(grupa);
                j++;
            }
        }
        if(j==0){
            System.out.println("Brak pustych grup");
        }
        return empty;
    }
    public List<String> summary(){
        List<String> s = new ArrayList<>();
        if(grupy.size()>0){
            //Collections.sort(grupy, Collections.reverseOrder());
            for (Class grupa : grupy.values()) {
                s.add("Nazwa grupy: " + grupa.nazwaGrupy + " Procentowe zapelnienie: " + (grupa.getSize() / grupa.maksymalnaIloscStudentow) * 100.0 + "%");
                //System.out.println("Nazwa grupy: " + grupa.nazwaGrupy + " Procentowe zapelnienie: " + (grupa.getSize() / grupa.maksymalnaIloscStudentow) * 100.0 + "%");
            }
        }
        else{
            s.add("Lista grup jest pusta");
            //System.out.println("Lista grup jest pusta");
        }
        return s;
    }
}
