package services;

import controller.CarroController;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import static javax.management.Query.times;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import model.Carro;


/**
 *
 * @author RaelH
 */
@Path("/carro")
public class CarroService {
   
    static CarroController controller = new CarroController();
    
    
    @GET
    @Path("/lista")
    @Produces(MediaType.APPLICATION_JSON)
    public List <Carro> getListaCarros(){   
        return controller.getList();    
    }
    
    @GET
    @Path("/listaDisponivel")
    @Produces(MediaType.APPLICATION_JSON)
    public String getListaCarrosDisponiveis(){
        return controller.buscarCarrosDisponiveis();
    }
       
    @GET
    @Path("/getLucro")
    @Produces(MediaType.APPLICATION_JSON)
    public String getMargemLucro(){   
        return controller.getMargemLucro();    
    }
    
    @GET
    @Path("/lucroTotal")
    @Produces(MediaType.APPLICATION_JSON)
    public String getLucroTotal(){   
        return controller.getMargemLucroTotal();    
    }
    
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/insert")
    public Response saveJson(Carro obj){
        try {
            System.out.println(obj.toString());
            obj.setId(controller.getList().size() );
            controller.addCarro(obj);
        return Response.ok((Object)obj).build();
        }catch (Exception e) {
            e.printStackTrace();
            return Response.serverError().build();
        }
    }
    
    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/update/{id}")
    public Response updateJson(@PathParam("id") int id,Carro obj){
        try {
            System.out.println(obj.toString());
            controller.update(id, obj);
     
        return Response.ok((Object)obj).build();
        }catch (Exception e) {
            e.printStackTrace();
            return Response.serverError().build();
        }
    }
    
    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/updateLucro")
    public String updateMargemLucro(String content){
        return CarroController.setMargemLucro(content);
    }
    
    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/delete/{id}")
    public Response delete(@PathParam("id") int id) {
        if(controller.delete(id)){
            return Response.ok().build();
        }else{
            return Response.serverError().build();
        }
        
    }
    
}
