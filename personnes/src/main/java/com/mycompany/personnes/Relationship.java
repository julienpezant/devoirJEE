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
public class Relationship {
   
    protected int id;
    protected int id1;
    protected int id2;
    protected String name;
    
    public Relationship(int id, int id1, int id2, String name){
        this.id = id;
        this.id1 = id1;
        this.id2 = id2;
        this.name = name;
    }
    
    public Relationship(){}

    public int getId() {
        return id;
    }

    public int getId1() {
        return id1;
    }

    public int getId2() {
        return id2;
    }

    public String getName() {
        return name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setId1(int id1) {
        this.id1 = id1;
    }

    public void setId2(int id2) {
        this.id2 = id2;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    
    @Override
    public String toString(){
        return "{\"relationship\" : { \"id\" : \""+ getId() + "\",  \"user1\" : \"" + getId1() + "\", \"nom\" : \"" + getName() + "\" , \"user2\" : \"" +getId2()+ "\"}}";
    }
    

    
}
