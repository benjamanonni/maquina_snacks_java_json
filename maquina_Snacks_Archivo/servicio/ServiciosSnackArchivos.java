package maquina_Snacks_Archivo.servicio;

import maquinaSnacks.Snack;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

//servicios
public class ServiciosSnackArchivos implements IServicioSnacks {
    private ArrayList<Snack> listaCompra = new ArrayList<>();
    private final String nombreArchivo = "archivoJson.txt";
    //creamos la lista de snacks
    private ArrayList<Snack> snacks = new ArrayList<>();

    //contructor vacio buena practica asi al crear un objeto ServiciosSnackArchivos este ya crea un archivo
    public ServiciosSnackArchivos() {
        //creamos archivo si este no existe
        var archivo = new File(nombreArchivo);
        boolean existe = false;
        try {
            //verificar si existe para no sobrescribir
            if (archivo.exists()) {
                System.out.println("archivo ya existe");
                existe = true;
            }
            //si el archivo existe entonces obtenemos informacion en nuestro arraylist snacks
            if (existe) {
                this.snacks=leerArchivo();
            } else {
                //si no existe creamos el archivo con File Writer  y PrintWriter es el encargado de darle el metodo para escribir en el archivo con printl
                var salida = new PrintWriter(new FileWriter(archivo));
                //despeus de crear un archivo en MEMORIA SECUNDARIA ES IMPORTANTE CERRAR ESTE BUFFER
                salida.close();
                System.out.println("archivo creado con exito");
            }

        } catch (Exception e) {
            throw new RuntimeException("error al crear el archivo" + e);
        }
    }
    @Override
    public void agregarSnack(String nombre, double precio) {
        //agregamos snack a la lista
        //agregamos el snack al archivo
        Snack snack = new Snack(nombre, precio);
        snacks.add(snack);
        this.agregarSnackArchivo(snack);
    }

    //metodo de agregar snack al archivo
    private void agregarSnackArchivo(Snack snack) {
        boolean anexar = false;
        //abrimos el archivo
        File archivo = new File(nombreArchivo);
        try {
            //comprobamos que exista archivo
            if (archivo.exists()) {
                anexar = true;
            }
            //abre el archivo con el sistema operativo si no existe lo crea y le pasamos la propiedad de println,si existe nos paramos en el ultimo indice
            var salida = new PrintWriter(new FileWriter(archivo, anexar));
            //si guardamos con println snack tenemos un problema que llama al metodo toString y guarda de forma completa entonces al tener que parsear le cuesta
            salida.println(snack.getNombre()+ "," +snack.getPrecio());
            //guardamos
            salida.close();
        } catch (Exception e) {
            throw new RuntimeException("error al agregar un snack" + e);
        }
    }

    @Override
    public void comprarSnacks(String nombreSnack) {
        //bandera
        boolean seEncontro = false;
        //recorrer el inventario para ver si existe,pero es un objeto necesitamos el array
        for (Snack s : snacks) {
            if (s.getNombre().equalsIgnoreCase(nombreSnack)) {
                listaCompra.add(s);
                System.out.println("producto añadido al carrito");
                seEncontro = true;
            }
        }
        if (!seEncontro) {
            System.out.println("no existe el producto: " + nombreSnack);
        }
    }

    @Override
    public ArrayList<Snack> devolverCarrito(){
        return listaCompra;
    }
    @Override
    public double devolverTotal() {
        double total=0;
        for(Snack s:listaCompra){
            total+=s.getPrecio();
        }
        return total;
    }
    public ArrayList<Snack> getLista(){
        return snacks;
    }

    //traer lista de json a java
    private ArrayList<Snack> leerArchivo(){
        try{
            ArrayList<Snack>lista=new ArrayList<>();
            //leer todas las lineas del archivo,para eso creamos un objeto path donde abre el buffer y escribe ahi
           List<String>lineas;
           /*no podemos almacenar las lineas de forma directa en nuestro array snacks porque este es de tipo Snack y lineas String para eso pasamos
            el string a objeto con split,paths lo que hace es traerlo a cada linea como un array pero traeria coca 90 como un mismo elemento entonces necesita
            mos un split*/
            lineas= Files.readAllLines(Paths.get(nombreArchivo));
            for(String l:lineas){
                //split corta el string cada vez que ve una coma
                String[]lineasSnack=l.split(",");
                //nombre en indice 0
                //toma el primer elementro del arreglo
                String nombre=lineasSnack[0];
                //precio en indice 1
                double precio=Double.parseDouble(lineasSnack[1]);
                Snack snack=new Snack(nombre,precio);
                lista.add(snack);
            }
            return lista;
        } catch (Exception e) {
            throw new RuntimeException("error al leer el archivo"+e);
        }
    }
}
