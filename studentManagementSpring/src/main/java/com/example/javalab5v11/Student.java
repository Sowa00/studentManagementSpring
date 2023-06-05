package com.example.javalab5v11;

public class Student {
    int id;
    String imie;
    String nazwisko;
    double iloscPunktow;
    StudentCondition stanStudenta;
    int rokUrodzenia;

    public Student(int id, String imie, String nazwisko, double iloscPunktow, StudentCondition stanStudenta, int rokUrodzenia) {
        this.id = id;
        this.imie = imie;
        this.nazwisko = nazwisko;
        this.iloscPunktow = iloscPunktow;
        this.stanStudenta = stanStudenta;
        this.rokUrodzenia = rokUrodzenia;
    }




    public int getId() {
        return id;
    }

    public String getImie() {
        return imie;
    }

    public String getNazwisko() {
        return nazwisko;
    }

    public double getIloscPunktow() {
        return iloscPunktow;
    }

    public StudentCondition getStanStudenta() {
        return stanStudenta;
    }

    public int getRokUrodzenia() {
        return rokUrodzenia;
    }

    public void print(){
        System.out.println("Imie: "+ imie +" Nazwisko: "+ nazwisko +" Rok urodzenia: "+ rokUrodzenia +" Ilosc punktow: "+ iloscPunktow+" Stan studenta: "+stanStudenta);
    }
    public int compare(String search){
        if(nazwisko.toLowerCase().equals(search.toLowerCase())){
            return 0;
        }
        else{
            return 1;
        }
    }
}
