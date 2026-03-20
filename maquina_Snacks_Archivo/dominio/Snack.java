package maquina_Snacks_Archivo.dominio;

public class Snack {
    private static int contadorSnacks = 0;
    private int idSnack;
    private String nombre;
    private double precio;

    public Snack(String nombre, double precio) {
        if(nombre==null||nombre.isEmpty()){
            throw  new IllegalArgumentException("ERROR AL AÑADIR NOMBRE");
        }
        if(precio<=0){
            throw new IllegalArgumentException("ERROR AL AÑADIR PRECIO");
        }
        this.idSnack = ++contadorSnacks;
        this.nombre = nombre;
        this.precio = precio;
    }

    // Getters y Setters
    public String getNombre() { return nombre; }
    public double getPrecio() { return precio; }

    @Override
    public String toString() {
        return nombre + "-" + precio;
    }
}
