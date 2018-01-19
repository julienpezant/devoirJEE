/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import com.mycompany.app2.DBProvider;
import com.mycompany.personnes.PersonDBStub;
import com.mycompany.sqlpersons.SQLPersonHibernate;
import com.mycompany.personnes.PersonWithId;
import com.mycompany.personnes.Relationship;
import com.mycompany.sqlpersons.SQLRelationshipsHibernate;
import static java.lang.Integer.parseInt;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 *
 * @author JULIEN
 */
@RestController
public class FrontController {
    
    @Autowired
    private DBProvider db;
    
    @RequestMapping("/personnes")
    public String getPersonnes () {
        SQLPersonHibernate dbstub = this.db.getDb();
        ArrayList<PersonWithId> persons = dbstub.getAllPersons();
        String res = "{\"persons\" : [";
        int cp = 0;
        for (PersonWithId person : persons){
            res += person.toString()+" ";
            cp++;
            if (persons != null && !persons.isEmpty()) {
                if(cp < persons.size()-1){
                    res+= ", ";
                }
            }
        }
        res += "]}";
        return persons != null ? res : "null";
    }
    
    @RequestMapping("/personnes/{id}")
    public String getPersonFromId (@PathVariable() String id) {
        SQLPersonHibernate dbstub = this.db.getDb();
        PersonWithId person = dbstub.readPerson(parseInt(id));
        return person != null ? person.toString() : "null";
    }

    @RequestMapping(value = "/personnes/add", method = RequestMethod.POST)
    public String addPerson (@RequestParam(value="name", required = true, defaultValue="name") String name,
                             @RequestParam(value="firstname", required = true, defaultValue="firstname") String firstname,
                             @RequestParam(value="date", required = true, defaultValue="date") String date) {
        SQLPersonHibernate dbstub = this.db.getDb();
        dbstub.createPerson(name, firstname, date);
        return "added person";
    }
    
    @RequestMapping(value = "/personnes/edit", method = RequestMethod.POST)
    public String editPerson (@RequestParam(value="id", required = true, defaultValue="-1") String id,
                             @RequestParam(value="name", required = true, defaultValue="name") String name,
                             @RequestParam(value="firstname", required = true, defaultValue="firstname") String firstname,
                             @RequestParam(value="date", required = true, defaultValue="date") String date) {
        SQLPersonHibernate dbstub = this.db.getDb();
        dbstub.updatePerson(parseInt(id), name, firstname, date);
        return "updated person";
    }
    
    @RequestMapping(value = "/personnes/delete", method = RequestMethod.POST)
    public String deletePerson (@RequestParam(value="id", required = true, defaultValue="-1") String id) {
        SQLPersonHibernate dbstub = this.db.getDb();
        dbstub.deletePerson(parseInt(id));
        return "deleted person";
    }
    
    //gets relationships associated with one person's id
    @RequestMapping("/personnes/{id}/relations")
    public String getRelationshipsForPerson (@PathVariable() String id) {
        SQLRelationshipsHibernate dbstub = this.db.getDbRel();
        ArrayList<Relationship> rels = dbstub.getAllRelationships(parseInt(id));
        String res = "{\"relationships\" : [";
        int cp = 0;
        for (Relationship rel : rels){
            res += rel.toString();
            cp++;
            if (rels != null && !rels.isEmpty()) {
                if(cp < rels.size()-1){
                    res+= ", ";
                }
            }
        }
        res += "]}";
        return rels != null ? res : "null";
    }
    
    @RequestMapping("/relations/{id}")
    public String getRelationshipById (@PathVariable() String id) {
        SQLRelationshipsHibernate dbstub = this.db.getDbRel();
        Relationship rel = dbstub.readRelationship(parseInt(id));
        return rel != null ? rel.toString() : "null";
    }
    
    @RequestMapping(value = "/relations/add", method = RequestMethod.POST)
    public String addRelationship (@RequestParam(value="id1", required = true, defaultValue="-1") String id1,
                             @RequestParam(value="id2", required = true, defaultValue="-1") String id2,
                             @RequestParam(value="name", required = true, defaultValue="name") String name) {
        SQLRelationshipsHibernate dbstub = this.db.getDbRel();
        dbstub.createRelationship(Integer.parseInt(id1), Integer.parseInt(id2), name);
        return "added relationship";
    }
    
    @RequestMapping(value = "/relations/edit", method = RequestMethod.POST)
    public String editRelationship (@RequestParam(value="id", required = true, defaultValue="-1") String id,
                             @RequestParam(value="id1", required = true, defaultValue="-1") String id1,
                             @RequestParam(value="id2", required = true, defaultValue="-1") String id2,
                             @RequestParam(value="name", required = true, defaultValue="name") String name) {
        SQLRelationshipsHibernate dbstub = this.db.getDbRel();
        dbstub.updateRelationship(Integer.parseInt(id), Integer.parseInt(id1), Integer.parseInt(id2), name);
        return "edited relationship";
    }
    
    @RequestMapping(value = "/relations/delete", method = RequestMethod.POST)
    public String deleteRelationship (@RequestParam(value="id", required = true, defaultValue="-1") String id) {
        SQLRelationshipsHibernate dbstub = this.db.getDbRel();
        dbstub.deleteRelationship(Integer.parseInt(id));
        return "deleted relationship";
    }
    
}

