package maquina_Snacks_Archivo.servicio;

import maquinaSnacks.Snack;

import java.util.ArrayList;

public class ServicioSnacksLista implements IServicioSnacks {
    private final ArrayList<Snack> snacks = new ArrayList<>();
    private final ArrayList<Snack> listaCompra=new ArrayList<>();
    public void agregarSnack(String nombre,double precio) {
        Snack snack=new maquinaSnacks.Snack(nombre,precio);
        snacks.add(snack);
    }

    //comprar snacks
    public void comprarSnacks(String nombreSnack){
        //bandera
        boolean seEncontro=false;
        //recorrer el inventario para ver si existe,pero es un objeto necesitamos el array
        for(Snack s: snacks) {
            if (s.getNombre().equalsIgnoreCase(nombreSnack)) {
                listaCompra.add(s);
                System.out.println("producto añadido al carrito");
                seEncontro = true;
            }
        }
        if(!seEncontro){
            System.out.println("no existe el producto: " + nombreSnack);
        }
    }
    //devolver ticket
    public ArrayList<Snack> devolverCarrito(){
        return listaCompra;
    }
    //devolver total del carrito
    public double devolverTotal() {
        double total = 0;
        for (Snack s : listaCompra) {
            total += s.getPrecio();
        }
        return total;
    }
    //devolver lista
    public ArrayList<Snack>getLista(){
        return snacks;
    }
}