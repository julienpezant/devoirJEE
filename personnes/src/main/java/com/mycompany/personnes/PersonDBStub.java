/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.personnes;

import java.util.*;

/**
 *
 * @author JULIEN
 */
public class PersonDBStub implements PersonDB{
    
    protected static ArrayList<PersonWithId> personList;

    public PersonDBStub() {
        this.personList = new ArrayList<>();
        this.personList.add(0,new PersonWithId(0,"pezant","julien","06/02/1995"));
        this.personList.add(1,new PersonWithId(1,"jean","jean","08/04/1990"));
    }

    @Override
    public void createPerson(String nom, String prenom, String Dnaissance) {
        PersonWithId person = new PersonWithId(this.personList.size(), nom, prenom, Dnaissance);
        this.personList.add(this.personList.size(),person);
    }

    @Override
    public void updatePerson(int id, String nom, String prenom, String Dnaissance) {
        PersonWithId person = new PersonWithId(id, nom, prenom, Dnaissance);
        this.personList.set(id, person);
    }

    @Override
    public void deletePerson(int id) {
        this.personList.remove(id);
    }

    @Override
    public PersonWithId readPerson(int id) {
        return this.personList.get(id);
    }
    
    @Override
    public ArrayList<PersonWithId> getAllPersons(){
        return this.personList;
    }
    
}
