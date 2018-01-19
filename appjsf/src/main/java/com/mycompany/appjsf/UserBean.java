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
import java.util.ArrayList;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author JULIEN
 */
@ManagedBean(name = "userBean")
@SessionScoped
public class UserBean implements Serializable{

    protected DBProvider db = DBProvider.getInstance();

    public ArrayList<PersonWithId> getPersonnes () {
        SQLPersonHibernate dbstub = this.db.getDb();
        return dbstub.getAllPersons();
    }    
}
