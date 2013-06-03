package tuwien.big.formel0.persistence;
import java.sql.*;
import tuwien.big.formel0.picasa.*;
import javax.persistence.*;
import tuwien.big.formel0.entities.Player;

/**
 *
 * @author edem
 */

    public class ConFormel0 implements TableWriter{
    EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("lab4");
   
    EntityManager entityManager;
     
   // public ConFormel0(){}
    
    //@Override
    public void create(Player p){
        
        entityManager = entityManagerFactory.createEntityManager();
        
        entityManager.getTransaction().begin();
        entityManager.persist(p);

        entityManager.getTransaction().commit();
        entityManager.close();
        
       // return p;
        
    }


}
    

