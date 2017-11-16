package com.projeto01.persistencia;

import com.projeto01.entidade.Consumidor;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class ConsumidorDAO {

    @PersistenceContext
    private EntityManager em;
    
    public void criar (Consumidor consumidor) throws PersisteConsumidorExcecao {
        em.persist(consumidor);
    }
    
    public List<Consumidor> listar() {
        return em.
                createQuery(
                        "SELECT c FROM Consumidor c", 
                        Consumidor.class).
                getResultList();
    }
    
}
