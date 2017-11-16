package com.projeto01.modelo;

import com.projeto01.entidade.Consumidor;
import com.projeto01.negocio.ConsumidorBusiness;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;


@ManagedBean
public class ConsumidorBean {
    
    @EJB
    private ConsumidorBusiness consumidorBusiness;

    public String criar() {
        Consumidor c = new Consumidor();
        c.setNome(this.getNome());
        c.setIdade(this.getIdade());
        c.setSenha(this.getSenha());
        
        try {
            consumidorBusiness.criar(c);
            
            return "irGerenciarConsumidores";

        } catch (Exception e) {
            return "irPaginaPrincipal";
        }
    }

    public List<Consumidor> listar() {
        return consumidorBusiness.listar();
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
