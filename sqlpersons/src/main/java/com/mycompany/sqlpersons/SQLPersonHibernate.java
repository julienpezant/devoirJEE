/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.sqlpersons;

import com.mycompany.personnes.PersonDB;
import com.mycompany.personnes.PersonWithId;
import java.util.ArrayList;
/*import java.sql.*;
import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import static java.lang.Integer.parseInt;*/
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Query;
import org.hibernate.Transaction;
import org.hibernate.InvalidMappingException;
import org.hibernate.HibernateException;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

/**
 *
 * @author JULIEN
 */
public class SQLPersonHibernate implements PersonDB{

    /*static private Connection conn = null;*/
    private SessionFactory sessionFactory;

    public SQLPersonHibernate() {
        this.initialize();
    }
    
    // Handling Hibernate sessions ===================================================

    protected void initialize () throws InvalidMappingException {
        ServiceRegistry serviceRegistry = null;
        try {
            Configuration configuration = new Configuration().configure();
            StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
            serviceRegistry = builder.build();
            this.sessionFactory = configuration.buildSessionFactory(serviceRegistry);
        } catch (Throwable e) {
            //erreur
            StandardServiceRegistryBuilder.destroy(serviceRegistry);
            throw e;
        }
    }

    protected void close () throws HibernateException {
        if (this.sessionFactory!=null) {
            this.sessionFactory.close();
        }
    }
    
    @Override
    public void createPerson(String nom, String prenom, String Dnaissance) {
        PersonWithId person = null;
        Session session=sessionFactory.openSession();
        Transaction tr = null;
        try{
            tr= session.beginTransaction();
            //Add new PersonWithId object
            person = new PersonWithId(1,nom,prenom,Dnaissance);
         
            //Save the person in database
            session.save(person);

            tr.commit();
        }catch(Exception e){
            if (tr != null) {
                tr.rollback();
            }
            e.printStackTrace();
        }finally{
            session.close();
        }
    }

    @Override
    public void updatePerson(int id, String nom, String prenom, String Dnaissance) {
        PersonWithId person = null;
        Session session=sessionFactory.openSession();
        Transaction tr = null;
        try{
            tr= session.beginTransaction();
            //updates PersonWithId object
            person = (PersonWithId)session.load(PersonWithId.class,id);
            person.setNom(nom);
            person.setPrenom(prenom);
            person.setDatenaissance(Dnaissance);

            tr.commit();
        }catch(Exception e){
            if (tr != null) {
                tr.rollback();
            }
            e.printStackTrace();
        }finally{
            session.close();
        }
    }

    @Override
    public void deletePerson(int id) {
        PersonWithId person = null;
        Session session=sessionFactory.openSession();
        Transaction tr = null;
        try{
            tr= session.beginTransaction();
            //deletes PersonWithId object
            person = (PersonWithId)session.load(PersonWithId.class,id);
            session.delete(person);

            tr.commit();
        }catch(Exception e){
            if (tr != null) {
                tr.rollback();
            }
            e.printStackTrace();
        }finally{
            session.close();
        }
    }

    @Override
    public PersonWithId readPerson(int id) {
        PersonWithId person = null;
        Session session=sessionFactory.openSession();
        Transaction tr = null;
        try{
            tr= session.beginTransaction();
            Query query = session.createQuery("from PersonWithId where id = :pid ");
	    query.setParameter("pid", id);
            List<?> list = query.list();
	    person = (PersonWithId) list.get(0);
            tr.commit();
        }catch(Exception e){
            if (tr != null) {
                tr.rollback();
            }
            e.printStackTrace();
        }finally{
            session.close();
            return person;
        }
    }

    @Override
    public ArrayList<PersonWithId> getAllPersons() {
        ArrayList<PersonWithId> persons = new ArrayList<>();
        Session session=sessionFactory.openSession();
        Transaction transaction=null;
        try {
            transaction=session.beginTransaction();
            Query query = session.createQuery("from PersonWithId");
            persons=(ArrayList<PersonWithId>)query.list();
            transaction.commit();
        } catch (Exception e) {
            if (transaction!=null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
            return persons;
        }
    }
    
}
