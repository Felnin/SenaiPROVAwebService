
package controller;

import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.List;
import model.Carro;

public class CarroController {
    static private List <Carro> ListCarro = new ArrayList <Carro>();
    static private List <Carro> carrosDisponiveis = new ArrayList <Carro>();
    static private double margemLucro = 15.0; 
    
     public void addCarro(Carro obj){
         ListCarro.add(obj);
     }
     
    public static String getMargemLucro() {
        return new Gson().toJson(margemLucro);
    }
    
    public static String getMargemLucroTotal() {
        double lucroTotal = 0.0;
        for (Carro ListCarro1 : ListCarro) {
            if (ListCarro1.isVendido() == true) {
                double temp = Double.parseDouble(ListCarro1.getPreco());
                temp += temp * 0.15;
                lucroTotal += temp;
            }
        }
        return new Gson().toJson(String.valueOf(lucroTotal));
    }

    public static String setMargemLucro(String content) {
        Gson gson = new Gson();
        margemLucro = gson.fromJson(content, Double.class);
        String valorResultado = gson.toJson(margemLucro);
        return valorResultado;
    }
     
     public List <Carro> getList(){
         return ListCarro;
     }
     
    public Carro getCarro(int id){
         for(int i =0; i < ListCarro.size(); i++){
             if(ListCarro.get(i).getId() == id){
                 return ListCarro.get(i); 
             }
         }       
         return null;    
     }
     
     public boolean update(int id, Carro obj){
         boolean Achou = false;
         
         for(int i =0; i < ListCarro.size(); i++){
             if(ListCarro.get(i).getId() == id){
                 ListCarro.get(i).setModelo(obj.getModelo());
                 ListCarro.get(i).setPreco(obj.getPreco());
                 ListCarro.get(i).setCor(obj.getCor());
                 Achou = true;
             }
         }
         
         return Achou;    
     }
   
     public boolean delete(int id){
         boolean Achou = false;
         
         for(int i =0; i < ListCarro.size(); i++){
             if(ListCarro.get(i).getId() == id){
                 ListCarro.remove(i);
                 Achou = true;
             }
         }
         
         return Achou;    
     }
     
    static public String buscarCarrosDisponiveis() {

        Gson gson = new Gson();
        
        for (int i = 0; i < ListCarro.size(); i++) {
            if (ListCarro.get(i).isVendido() == false) {
                carrosDisponiveis.add(ListCarro.get(i));
            }

        }
        String resultado = gson.toJson(carrosDisponiveis);

        if (carrosDisponiveis.size() > 0) {
            return resultado;
        } else {
            return "Lista vazia";
        }

    }
     
}
