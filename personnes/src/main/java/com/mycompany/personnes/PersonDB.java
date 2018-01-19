/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.personnes;

import java.util.ArrayList;

/**
 *
 * @author JULIEN
 */
public interface PersonDB {
    
    public void createPerson(String nom, String prenom, String Dnaissance);
    public void updatePerson(int id, String nom, String prenom, String Dnaissance);
    public void deletePerson(int id);
    public PersonWithId readPerson(int id);
    public ArrayList<PersonWithId> getAllPersons();
}
