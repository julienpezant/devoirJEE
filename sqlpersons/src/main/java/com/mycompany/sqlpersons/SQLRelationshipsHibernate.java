/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.sqlpersons;

import com.mycompany.personnes.PersonDB;
import com.mycompany.personnes.PersonWithId;
import com.mycompany.personnes.Relationship;
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
public class SQLRelationshipsHibernate {
    
    private SessionFactory sessionFactory;

    public SQLRelationshipsHibernate() {
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
    
    public void createRelationship(int id1, int id2, String name) {
        Relationship rl = null;
        Session session=sessionFactory.openSession();
        Transaction tr = null;
        try{
            tr= session.beginTransaction();
            //Add new PersonWithId object
            rl = new Relationship(1,id1,id2,name);
         
            //Save the person in database
            session.save(rl);

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
    
    public void updateRelationship(int id, int id1, int id2, String name) {
        Relationship rl = null;
        Session session=sessionFactory.openSession();
        Transaction tr = null;
        try{
            tr= session.beginTransaction();
            rl = (Relationship)session.load(Relationship.class,id);
            rl.setId1(id1);
            rl.setId2(id2);
            rl.setName(name);

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
    
    public void deleteRelationship(int id) {
        Relationship rl = null;
        Session session=sessionFactory.openSession();
        Transaction tr = null;
        try{
            tr= session.beginTransaction();
            rl = (Relationship)session.load(Relationship.class,id);
            session.delete(rl);

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
    
    public Relationship readRelationship(int id) {
        Relationship rl = null;
        Session session=sessionFactory.openSession();
        Transaction tr = null;
        try{
            tr= session.beginTransaction();
            Query query = session.createQuery("from Relationship where id = :rid ");
	    query.setParameter("rid", id);
            List<?> list = query.list();
	    rl = (Relationship) list.get(0);
            tr.commit();
        }catch(Exception e){
            if (tr != null) {
                tr.rollback();
            }
            e.printStackTrace();
        }finally{
            session.close();
            return rl;
        }
    }
    
    public ArrayList<Relationship> getAllRelationships(int idperson) {
        ArrayList<Relationship> rls = new ArrayList<>();
        Session session=sessionFactory.openSession();
        Transaction transaction=null;
        try {
            transaction=session.beginTransaction();
            Query query = session.createQuery("from Relationship where id1 = :pid or id2 = :pid");
            query.setParameter("pid", idperson);
            rls =(ArrayList<Relationship>)query.list();
            transaction.commit();
        } catch (Exception e) {
            if (transaction!=null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
            return rls;
        }
    }  
}
