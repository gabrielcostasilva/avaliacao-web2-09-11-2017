package com.projeto01.modelo;

import com.projeto01.entidade.Produto;
import java.util.List;
import javax.annotation.Resource;
import javax.faces.bean.ManagedBean;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;

@ManagedBean
public class ProdutoBean {
    
    @PersistenceContext
    private EntityManager em;
    
    @Resource
    private UserTransaction utx;
    
    public String criar() {
        Produto p = new Produto();
        p.setNome(this.getNome());
        p.setValor(this.getValor());
        p.setTipo(this.getTipo());
        
        try {
            utx.begin();
            em.persist(p);
            utx.commit();
            return "irGerenciarProdutos";
            
        } catch (Exception e) {
            return "irPaginaPrincipal";
        }
    }
    
    public List<Produto> listar() {
        return em.createQuery("SELECT p FROM Produto p").getResultList();
    }
    
    
    private String nome;
    private double valor;
    private String tipo;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}
