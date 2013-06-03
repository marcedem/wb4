package tuwien.big.formel0.persistence;

import java.util.Collection;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import tuwien.big.formel0.entities.Player;

public class PlayerService {
    
    private EntityManager em;
    
    public PlayerService(EntityManager em){
        this.em = em;
    }
    
    public Player createPlayer(String name, String firstname, String lastname, String password){
        Player pl = new Player();
        
        pl.setName(name);
        pl.setFirstname(firstname);
        pl.setLastname(lastname);
        pl.setPassword(password);
        
        em.persist(pl);
        return pl;
    }
    
    public Collection<Player> findAllPlayers(){
        Query query = em.createQuery("select p from Player p");
        return (Collection<Player>) query.getResultList();
    }
    
}
