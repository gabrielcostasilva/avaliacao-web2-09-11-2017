package com.projeto01.modelo;

import com.projeto01.entidade.Consumidor;
import java.util.List;
import javax.annotation.Resource;
import javax.faces.bean.ManagedBean;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;

@ManagedBean
public class ConsumidorBean {
    
    @PersistenceContext
    private EntityManager em;

    @Resource
    private UserTransaction utx;

    public String criar() {
        Consumidor c = new Consumidor();
        c.setNome(this.getNome());
        c.setIdade(this.getIdade());
        c.setSenha(this.getSenha());
        
        try {
            utx.begin();
            em.persist(c);
            utx.commit();
            return "irGerenciarConsumidores";

        } catch (Exception e) {
            return "irPaginaPrincipal";
        }
    }

    public List<Consumidor> listar() {
        return em.createQuery("SELECT c FROM Consumidor c").getResultList();
    }

    
    private String nome;
    private int idade;
    private String senha;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
    
}
