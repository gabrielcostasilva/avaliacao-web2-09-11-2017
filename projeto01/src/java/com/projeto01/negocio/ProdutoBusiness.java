package com.projeto01.negocio;

import com.projeto01.entidade.Produto;
import com.projeto01.persistencia.PersisteProdutoExcecao;
import com.projeto01.persistencia.ProdutoDAO;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;

@Stateless
public class ProdutoBusiness {
    
    @EJB
    private ProdutoDAO dao;
    
    private boolean verificaExisteProduto (Produto produto) {
        boolean existe = false;
        
        for (Produto p: listar())
            if (p.getTipo().equalsIgnoreCase(produto.getTipo()))
                if (p.getNome().equalsIgnoreCase(produto.getNome()))
                    existe = true;
        
        return existe;
    }
    
    public void criar (Produto produto) throws CriaProdutoExcecao {
        try {
            if (!verificaExisteProduto(produto))
                dao.criar(produto);
            
        } catch (PersisteProdutoExcecao e) {
            throw new CriaProdutoExcecao();
        }
    }
    
    public List<Produto> listar() {
        return dao.listar();
    }
    
}
