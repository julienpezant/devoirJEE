/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.sqlpersons;

import com.mycompany.personnes.PersonDB;
import com.mycompany.personnes.PersonWithId;
import java.util.ArrayList;
import java.sql.*;
import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import static java.lang.Integer.parseInt;

/**
 *
 * @author JULIEN
 */
public class SQLPersonDB implements PersonDB{
    
    // JDBC driver name and database URL
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
    static final String DB_URL = "localhost";
    static final String DB_NAME = "m2";

    //  Database credentials
    static final String USER = "root";
    static final String PASS = "";
    static private Connection conn = null;
    
    public SQLPersonDB(){
        createDB();
    }
    
    protected void createDB(){
        try{
            //STEP 2: Register JDBC driver
            Class.forName(JDBC_DRIVER);

            //STEP 3: Open a connection
            System.out.println("Connecting to database...");

            createLink(DB_URL, DB_NAME, USER, PASS);
            
            System.out.println("Connected to db");         
        }catch(SQLException se){
            //Handle errors for JDBC
            throw new RuntimeException("error JDBC",se);
        }catch(Exception e){
            //Handle errors for Class.forName
            throw new RuntimeException("error class.forName",e);
        }
    }//end main
    
    protected void closeLink(){
        try {
            conn.close();
        } catch (SQLException ex) {
            throw new RuntimeException("error close link",ex);
        }
    }
    
    protected void printResponse(ResultSet response){
        try{
            ResultSetMetaData rsmd = response.getMetaData();
            int columnsNumber = rsmd.getColumnCount();
            while (response.next()) {
                //Print one row          
                for(int i = 1 ; i <= columnsNumber; i++){
                    System.out.print(response.getString(i) + " "); //Print one element of a row
                }
                System.out.println();//Move to the next line to print the next row.           
            }
        }catch(SQLException se){
            //Handle errors for JDBC
            throw new RuntimeException("error printed response",se);
        }
    }
    
    protected void createLink (String host, String database, String username, String password) throws SQLException {
        MysqlDataSource ds=new MysqlDataSource();
        ds.setServerName(host);
        ds.setDatabaseName(database);
        Connection link=ds.getConnection(username,password);
        if (!link.isValid(0)) {
            throw new SQLException("Failed to initialize a valid connection to database.");
        }
        conn = link;
    }
    
    @Override
    public void createPerson(String nom, String prenom, String Dnaissance) {
        try{
            Statement st = conn.createStatement();
            String sql = "INSERT INTO persons(nom, prenom, dateNaissance) VALUES (\""+nom+"\",\""+prenom+"\",\""+Dnaissance+"\")";
            st.execute(sql);
        }catch(SQLException se){
            /*se.printStackTrace();*/
            throw new RuntimeException("error create",se);
        }
    }

    @Override
    public void updatePerson(int id, String nom, String prenom, String Dnaissance) {
        try{
            Statement st = conn.createStatement();
            String sql = "UPDATE persons SET nom=\""+nom+"\",prenom=\""+prenom+"\",dateNaissance=\""+Dnaissance+"\" WHERE id=\""+id+"\"";
            st.execute(sql);
        }catch(SQLException se){
            throw new RuntimeException("error edit",se);
        }
    }

    @Override
    public void deletePerson(int id) {
        try{
            Statement st = conn.createStatement();
            String sql = "DELETE * FROM persons WHERE id=\""+id+"\"";
            st.execute(sql);
        }catch(SQLException se){
            throw new RuntimeException("error delete",se);
        }
    }

    @Override
    public PersonWithId readPerson(int id) {
        PersonWithId person = null;
        try{
            Statement st = conn.createStatement();
            String sql = "SELECT * FROM persons WHERE id=\""+id+"\"";
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {        
                String nom = rs.getString(2);
                String prenom = rs.getString(3);
                String dateNaissance = rs.getString(4);
                person = new PersonWithId(id,nom,prenom,dateNaissance); 
            }
        }catch(SQLException se){
            throw new RuntimeException("error read person",se);
        }
        return person;
    }
    
    @Override
    public ArrayList<PersonWithId> getAllPersons(){
        ArrayList<PersonWithId> persons = new ArrayList<>();
        try{
            Statement st = conn.createStatement();
            String sql = "SELECT * FROM persons";
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {        
                int id = parseInt(rs.getString(1));
                String nom = rs.getString(2);
                String prenom = rs.getString(3);
                String dateNaissance = rs.getString(4);
                persons.add(new PersonWithId(id,nom,prenom,dateNaissance)); 
            }
        }catch(SQLException se){
            throw new RuntimeException("error read all",se);
        }
        return persons;
    }
}
