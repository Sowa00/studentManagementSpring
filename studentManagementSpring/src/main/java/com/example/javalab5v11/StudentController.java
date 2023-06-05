package com.example.javalab5v11;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.atomic.AtomicLong;

@RestController
public class StudentController {

    @Autowired
    private StudentService studentService;

    @PostMapping("/api/student/{id}")//dodaj studenta do danej grupy nr1
    public Student addStudent(@PathVariable int id,@RequestBody Student student){
        Student std = this.studentService.addStudent(student, id);
        return std;
    }

    @DeleteMapping("/api/student/{id}")//usun studenta nr2
    public void deleteStudent(@PathVariable("id") int id){
        this.studentService.deleteStudent(id);
    }

    @GetMapping("/api/student/{id}/grade")//pokaz punkty studenta po id nr3
    public double getPoints(@PathVariable("id") int id){
        return this.studentService.getPunkty(id);
    }

    @GetMapping("/api/student/csv")//stworz plik csv wszystkich studentow nr4
    void saveToCSV(){
        try{
            this.studentService.saveToFileToCSV();
        } catch (IOException e){
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/api/course")//pokaz grupy i studentow nr5
    public Map<Integer, Class> getGroups(){
        return this.studentService.getGroups();
    }

    @PostMapping("/api/course")//dodaj grupe nr6
    public List<Student> addGroup(@RequestParam String id,@RequestParam String nazwa,@RequestParam String wielkosc){
        int idd = Integer.parseInt(id);
        int wielkoscc = Integer.parseInt(wielkosc);
        Class clas = this.studentService.addGroup(idd, nazwa, wielkoscc);
        return clas.students;
    }

    @DeleteMapping("/api/course/{id}")//usuwanie grupy nr7
    public void deleteGroup(@PathVariable("id") int id){
        this.studentService.deleteGroup(id);
    }

    @GetMapping("/api/course/{id}/students")//wyswietlanie wszystkich studentow w danej grupie nr8
    public List<String> getGroupStudents(){
        return this.studentService.getGroupsFill();
    }

    @GetMapping("/api/course/fill")//pokaz wypelnienie grup i ich nazwy nr9
    public List<String> getGroupsFil(){
        List<String> lista = this.studentService.getGroupsFill();
        return lista;
    }

    @GetMapping("/api/student")//pokaz studentow z listy1
    public List<Student> getStudents(){
        return this.studentService.getAllStudents();
    }

    @GetMapping("/api/student/{id}")//pokaz studenta po id
    public List<Student> getStudent(@PathVariable("id") int id){
        return this.studentService.getOneStudent(id);
    }

}
