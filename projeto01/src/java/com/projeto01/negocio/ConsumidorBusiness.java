package com.projeto01.negocio;

import com.projeto01.entidade.Consumidor;
import com.projeto01.persistencia.ConsumidorDAO;
import com.projeto01.persistencia.PersisteConsumidorExcecao;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;

@Stateless
public class ConsumidorBusiness {

    @EJB
    private ConsumidorDAO dao;

    private boolean verificaExisteConsumidor(Consumidor consumidor) {
        boolean existe = false;

        for (Consumidor c : listar()) {
            if (c.getNome().equalsIgnoreCase(consumidor.getNome())) {
                existe = true;
            }
        }

        return existe;
    }

    public void criar(Consumidor consumidor) throws CriaConsumidorExcecao {

        try {
            if (!verificaExisteConsumidor(consumidor))
                dao.criar(consumidor);
            
        } catch (PersisteConsumidorExcecao e) {
            throw new CriaConsumidorExcecao();
        }

    }

    public List<Consumidor> listar() {
        return dao.listar();
    }

}
