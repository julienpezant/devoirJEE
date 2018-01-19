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
public class PersonWithId extends Person{
    
    protected int id;

    public PersonWithId(int id, String nom, String prenom, String datenaissance) {
        super(nom, prenom, datenaissance);
        this.id = id;
    }
    
    public PersonWithId(){
        super();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    @Override
    public String toString(){
        return "{ \"person\": { \"id\" : \"" + getId() + "\", \"nom\" : \"" + getNom() + "\", \"prenom\" : \"" + getPrenom() + "\", \"datenaissance\" : \"" + getDatenaissance() + "\"}}";
    } 
}
