/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.appjsf;

import com.mycompany.sqlpersons.SQLPersonHibernate;
import com.mycompany.sqlpersons.SQLRelationshipsHibernate;

/**
 *
 * @author JULIEN
 */
public class DBProvider {
    
    /*private PersonDBStub db;*/
    private final SQLPersonHibernate db;
    private final SQLRelationshipsHibernate dbr;
        
    /** Constructeur privé */
    private DBProvider(){
        /*this.db = new PersonDBStub();*/
        this.db = new SQLPersonHibernate();
        this.dbr = new SQLRelationshipsHibernate();
    }
 
    /** Instance unique pré-initialisée */
    private static DBProvider INSTANCE = new DBProvider();
 
    /** Point d'accès pour l'instance unique du singleton */
    public static DBProvider getInstance(){	
        return INSTANCE;
    }

    /*public PersonDBStub getDb() {
        return db;
    }*/
    
    public SQLPersonHibernate getDb() {
        return db;
    }
    
    public SQLRelationshipsHibernate getDbRel(){
        return dbr;
    }
}

