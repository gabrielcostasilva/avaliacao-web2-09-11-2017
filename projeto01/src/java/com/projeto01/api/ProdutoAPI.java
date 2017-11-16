package com.projeto01.api;

import com.projeto01.entidade.Produto;
import com.projeto01.negocio.CriaProdutoExcecao;
import com.projeto01.negocio.ProdutoBusiness;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path ("/produto")
public class ProdutoAPI {
    
    @EJB
    private ProdutoBusiness produtoBusiness;
    
    @POST
    @Consumes (MediaType.APPLICATION_JSON)
    public Response criar (String nome, double valor, String tipo) {
        Produto p = new Produto();
        p.setNome(nome);
        p.setValor(valor);
        p.setTipo(tipo);
        
        try {
            produtoBusiness.criar(p);
            return Response.status(Response.Status.CREATED).build();
            
        } catch (CriaProdutoExcecao ex) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }
    
    @GET
    @Produces (MediaType.APPLICATION_JSON)
    public List<Produto> listar() {
        return produtoBusiness.listar();
    }
    
    @Path("/{nome}")
    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    public Produto listar (@PathParam ("nome") String nome) {
        for (Produto p: produtoBusiness.listar())
            if (p.getNome().equalsIgnoreCase(nome))
                return p;
        
        return null;
    }
    
}
