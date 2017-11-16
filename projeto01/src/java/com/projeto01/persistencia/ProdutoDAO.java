package com.projeto01.persistencia;

import com.projeto01.entidade.Produto;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class ProdutoDAO {
    
    @PersistenceContext
    private EntityManager em;
    
    public void criar (Produto p) throws PersisteProdutoExcecao {
        em.persist(p);
    }
    
    public List<Produto> listar() {
        return em.
                createQuery(
                        "SELECT p FROM Produto p", 
                        Produto.class).
                getResultList();
    }
}
