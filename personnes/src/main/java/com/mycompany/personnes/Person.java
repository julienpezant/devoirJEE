/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.personnes;

/**
 *
 * @author JULIEN
 */
public class Person {
    
    protected String nom;
    protected String prenom;
    protected String datenaissance;

    public Person(String nom, String prenom, String datenaissance) {
        this.nom = nom;
        this.prenom = prenom;
        this.datenaissance = datenaissance;
    }
    
    public Person(){}

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getDatenaissance() {
        return datenaissance;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void setDatenaissance(String datenaissance) {
        this.datenaissance = datenaissance;
    }
    
    @Override
    public String toString(){
        return "{ \"person\": { \"nom\" : \"" + getNom() + "\", \"prenom\" : \"" + getPrenom() + "\", \"datenaissance\" : \"" + getDatenaissance() + "\"}}";
    }   
}
