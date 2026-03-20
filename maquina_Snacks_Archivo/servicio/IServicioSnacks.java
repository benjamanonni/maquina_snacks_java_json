package maquina_Snacks_Archivo.servicio;

import maquinaSnacks.Snack;

import java.util.ArrayList;
import java.util.List;

public interface IServicioSnacks {
    //los metodos en interface son publicos y abstractos
   void agregarSnack(String nombre,double precio);
   void comprarSnacks(String nombreSnack);
   ArrayList<Snack> devolverCarrito();
   double devolverTotal();
   ArrayList<Snack> getLista();
}
