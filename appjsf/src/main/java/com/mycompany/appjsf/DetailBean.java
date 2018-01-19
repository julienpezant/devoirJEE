/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.appjsf;

import javax.faces.bean.ManagedBean;
import com.mycompany.personnes.PersonWithId;
import com.mycompany.sqlpersons.SQLPersonHibernate;
import java.io.Serializable;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author JULIEN
 */
@ManagedBean(name = "detailBean")
@RequestScoped
public class DetailBean implements Serializable{

    @ManagedProperty(value = "#{param.id}")
    private int id;
    protected DBProvider db = DBProvider.getInstance();

    public PersonWithId getPersonne () {
        SQLPersonHibernate dbstub = this.db.getDb();
        return dbstub.readPerson(id);
    }  
    
    public String getNom(){
        return this.getPersonne().getNom();
    }

    public String getPrenom(){
        return this.getPersonne().getPrenom();
    }
    
    public String getDatenaissance(){
        return this.getPersonne().getDatenaissance();
    }
    
    public void setId(int id) {
        this.id = id;
    }
}
