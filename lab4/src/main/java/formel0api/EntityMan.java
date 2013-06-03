package formel0api;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;


@ManagedBean(name="entity")
@ApplicationScoped
public class EntityMan {
    EntityManagerFactory emf;
    EntityManager em;
    /**
     * Creates a new instance of EntityManager
     */
    public EntityMan() {
    }

    public void invoke() {
        emf = Persistence.createEntityManagerFactory("java:comp/env/jdbc/lab4");
        em = emf.createEntityManager();
    }
}
