/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.cphbusiness.jpa.tester;

import dk.cphbusiness.entity.Facade;
import dk.cphbusiness.entity.Project;
import dk.cphbusiness.entity.ProjectUser;
import java.util.Date;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author sofus
 */
public class Tester {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("PU");
        EntityManager em = emf.createEntityManager();
        Persistence.generateSchema("PU", null);
        
        ProjectUser pu = new ProjectUser();
        pu.setUserName("sofus");
        pu.setEmail("sal@ca.dk");
        pu.setCreated(new Date());
        Project p= new Project();
        p.setName("Project1");
        System.out.println(Facade.getAllProjects());     
        em.getTransaction().begin();
        em.persist(pu);
        em.persist(p);      
        em.getTransaction().commit();
        
    }
    
}
