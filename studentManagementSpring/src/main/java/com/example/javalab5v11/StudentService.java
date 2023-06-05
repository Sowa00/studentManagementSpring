package com.example.javalab5v11;

import org.springframework.stereotype.Repository;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Repository
public class StudentService {
    private static final String CSV_FILE_NAME = "plik.csv";
    private static List<Student> list = new ArrayList<>();
    private static List<Student> list1 = new ArrayList<>();
    private static List<Student> list2 = new ArrayList<>();
    public static Map<Integer, Class> map1 = new TreeMap<>();
    public static ClassContainer groups = new ClassContainer(map1);
    static List<String> dataLines = new ArrayList<>();
    public static Class klasa1 = new Class(1,"Klasa 1", list1, 30);
    public static Class klasa2 = new Class(2,"Klasa 2", list2, 30);


    static{

        klasa1.addStudent(new Student(1, "Jakub", "Sowa", 10, StudentCondition.obecny, 2001));
        klasa1.addStudent(new Student(2, "Jan", "Kowalski", 5, StudentCondition.nieobecny, 2000));
        klasa1.addStudent(new Student(3, "Anna", "Kowalska", 21, StudentCondition.chory, 1998));

        klasa2.addStudent(new Student(1, "Jakub", "Sowa", 10, StudentCondition.obecny, 2001));
        klasa2.addStudent(new Student(2, "Jan", "Kowalski", 5, StudentCondition.nieobecny, 2000));
        klasa2.addStudent(new Student(3, "Anna", "Kowalska", 21, StudentCondition.chory, 1998));
        klasa2.addStudent(new Student(1, "Andrzej", "Sowa", 10, StudentCondition.obecny, 2001));
        klasa2.addStudent(new Student(2, "Pawel", "Kowalski", 5, StudentCondition.nieobecny, 2000));
        klasa2.addStudent(new Student(3, "Jacek", "Kowalski", 21, StudentCondition.chory, 1998));


        groups.addClass(1, "klasa1", klasa1.students, klasa1.maksymalnaIloscStudentow);
        groups.addClass(2, "klasa2", klasa2.students, klasa2.maksymalnaIloscStudentow);

        //grupy.put(2, klasa1);
    }
    public List<String> getGroupsFill(){
        return groups.summary();
    }

    public Map<Integer, Class> getGroups(){
        return groups.grupy;
    }
    public double getPunkty(int id){
        return klasa1.students.get(id).iloscPunktow;
    }
    //get all studens
    public List<Student> getAllStudents(){
        return list1;
    }
    //add student
    public List<Student> getOneStudent(int id){
        List<Student> listId = new ArrayList<>();
        for(Student student:list1){
            if(student.id == id)
            {
                listId.add(student);
            }
        }
        return listId;
    }
    public Student addStudent(Student s, int id){
        groups.grupy.get(id).addStudent(s);
        //list1.add(s);
        return s;
    }
    public Class addGroup(int id, String nazwaGrupy, int maksymalnaIloscStudentow){
        Class c=new Class(id,nazwaGrupy, new ArrayList<>(), maksymalnaIloscStudentow);
        groups.addClass(c.id, c.nazwaGrupy, new ArrayList<>(), c.maksymalnaIloscStudentow);
        return c;
    }
    public void deleteStudent(int id){
        list1=list1.stream().filter(student -> student.getId()!=id).collect(Collectors.toList());
    }

    public void deleteGroup(int id){
        for(Class grupa : map1.values()){
            if(grupa.id == id){
                groups.removeClass(grupa.id);
            }
        }
    }

    public String convertToCSV(String data){
        return Stream.of(data)
                .map(this::escapeSpecialCharacters)
                .collect(Collectors.joining(","));
    }
    private String escapeSpecialCharacters(String s){
        String escapedData = s.replaceAll("\\R", " ");
        if(s.contains(",")||s.contains("\"")||s.contains("'")){
            s = s.replace("\"","\"\"");
            escapedData = "\"" + s + "\"";
        }
        return escapedData;
    }
    public void saveToFileToCSV() throws IOException{
        dataLines.add("Id;Imie;Nazwisko;Ilosc punktow;Stan studenta;Rok urodzenia");
        for(Class klasa : groups.grupy.values()){
            for(Student student:klasa.students){
                dataLines.add(Integer.toString(student.id)+";"+student.imie+";"+student.nazwisko+";"+Double.toString(student.iloscPunktow)+";"+String.valueOf(student.getStanStudenta())+";"+Integer.toString(student.rokUrodzenia));
            }
        }
        File csvOutputFile = new File(CSV_FILE_NAME);
        try(PrintWriter pw = new PrintWriter(csvOutputFile)) {
            dataLines.stream()
                    .map(this::convertToCSV)
                    .forEach(pw::println);
        }
        assertTrue(csvOutputFile.exists());
        }


    private void assertTrue(boolean exists) {

    }
}

