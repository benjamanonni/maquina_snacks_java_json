package maquina_Snacks_Archivo.presentacion;

import maquinaSnacks.Snack;
import maquinaSnacks.Snacks;
import maquina_Snacks_Archivo.servicio.IServicioSnacks;
import maquina_Snacks_Archivo.servicio.ServicioSnacksLista;
import maquina_Snacks_Archivo.servicio.ServiciosSnackArchivos;

import java.util.ArrayList;
import java.util.Scanner;

public class MaquinaSnacks {
    //creamos array de lista de snacks
    //IServicioSnacks inventario = new ServicioSnacksLista();
    IServicioSnacks inventario=new ServiciosSnackArchivos();
    Scanner entrada=new Scanner(System.in);
    public void menu(){
        System.out.println("---------------MAQUINA DE SNACKS-----------------");
        System.out.println("ingrese la opcion para acceder");
        int opcion;
        do{
            System.out.println("""
                    1-Añadir snack al inventario
                    2-Comprar snack
                    3-Mostrar snacks
                    4-salir
                    """);
            opcion= entrada.nextInt();
            entrada.nextLine();
            switch (opcion){
                case 1:
                    System.out.println("ingrese nombre de snack");
                    String nombre= entrada.nextLine();
                    System.out.println("ingrese precio");
                    int precio=entrada.nextInt();
                    entrada.nextLine();
                    inventario.agregarSnack(nombre,precio);
                    break;

                case 2:
                    mostrarSnacks();
                    System.out.println("ingrese el nombre del snack a comprar");
                    String snackCompra=entrada.nextLine();
                    inventario.comprarSnacks(snackCompra);
                    break;
                case 3:
                    mostrarSnacks();
                    break;
                case 4:
                    System.out.println("TICKET");
                    ArrayList<Snack>listaCompra=inventario.devolverCarrito();
                    //imprimimos carrito
                    if(listaCompra==null)return;
                    for(Snack s:listaCompra){
                        System.out.println(s);
                    }
                    //obtenemos total
                    double total=inventario.devolverTotal();
                    System.out.println("El total es: "+ total);
                    break;
                default:
                    System.out.println("opcion erronea........");
            }
        }while(opcion!=4);
    }
    public void mostrarSnacks() {
        System.out.println("\n--- CATÁLOGO DE SNACKS ---");
        if (inventario==null) {
            System.out.println("INVENTARIO VACIO");
            return;
        }
        for (Snack s : inventario.getLista()) {
            System.out.println(s);
        }
    }
}
