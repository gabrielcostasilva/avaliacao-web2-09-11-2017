package com.projeto01.api;

import com.projeto01.entidade.Consumidor;
import com.projeto01.negocio.ConsumidorBusiness;
import com.projeto01.negocio.CriaConsumidorExcecao;
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

@Path("/consumidor")
public class ConsumidorAPI {
    
    @EJB
    private ConsumidorBusiness consumidorBusiness;
    
    @POST
    @Consumes (MediaType.APPLICATION_JSON)
    public Response criar (String nome, String senha, int idade) {
        
        Consumidor c = new Consumidor();
        c.setNome(nome);
        c.setSenha(senha);
        c.setIdade(idade);
        
        try {
            consumidorBusiness.criar(c);
            return Response.status(Response.Status.CREATED).build();
            
        } catch (CriaConsumidorExcecao ex) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        
    }
    
    @GET
    @Produces (MediaType.APPLICATION_JSON)
    public List<Consumidor> listar() {
        return consumidorBusiness.listar();
    }
    
    @Path("/{id}")
    @GET
    @Produces (MediaType.APPLICATION_JSON)
    public Consumidor listar(@PathParam("id") Long id) {
        for (Consumidor c: consumidorBusiness.listar())
            if (c.getId().equals(id))
                return c;
        
        return null;    
    }
    
}
