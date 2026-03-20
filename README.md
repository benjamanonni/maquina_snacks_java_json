# MAQUINASNACKS
codigo basico donde implementamos permanencia en archivos,donde no buscamos mas complejidad que implementar esto
🥨 Máquina de Snacks – Arquitectura Multicapa con Persistencia en Archivo
📌 Descripción

Proyecto de práctica en Java aplicando:

Arquitectura en 3 capas (Dominio – Servicio – Interfaz)

Programación contra interfaces

Persistencia en archivo plano

Manejo de memoria vs almacenamiento secundario

Reconstrucción de objetos desde texto

El objetivo fue refactorizar un sistema simple en memoria hacia un diseño más organizado y escalable.

🏗️ Arquitectura

El sistema está dividido en tres capas principales:

1️⃣ Dominio

Contiene las clases que representan el modelo del negocio.

Ejemplo:

Snack

Carrito

Reglas:

No contiene Scanner

No contiene System.out

No maneja archivos

Solo representa datos y comportamiento propio

2️⃣ Servicio

Contiene la lógica de negocio.

Ejemplo:

agregarSnack

comprarSnack

calcularTotal

Reglas:

No contiene Scanner

No contiene System.out

No interactúa directamente con el usuario

Trabaja con objetos del dominio

Se programa contra una interfaz:

IServicioSnacks inventario = new ServiciosSnackArchivos();

La interfaz define el contrato.
La implementación define cómo se ejecutan las operaciones.

Esto permite cambiar la persistencia (lista, archivo, MySQL) sin modificar la UI.

3️⃣ Interfaz (UI)

Es la capa que interactúa con el usuario.

Contiene:

Scanner

System.out

Menú

Reglas:

No contiene lógica de negocio

Solo entrada y salida

📦 Persistencia con Archivo

Se implementó una clase ServiciosSnackArchivos que:

Crea el archivo si no existe

Carga datos en memoria al iniciar

Persiste cambios en el archivo

🔹 Flujo de Persistencia
Al iniciar el sistema

Se verifica si el archivo existe.

Si no existe → se crea.

Si existe → se leen las líneas y se reconstruyen los objetos Snack.

🔹 Escritura en archivo

Se utiliza:

new FileWriter(archivo, true);

Modo append para no sobrescribir el contenido.

Se guarda en formato estructurado:

queso,78.0
peperina,89.0

No se utiliza toString() para persistencia, ya que está pensado para presentación, no almacenamiento.

📖 Lectura de Archivo Plano

Para reconstruir objetos:

List<String> lineas = Files.readAllLines(Paths.get(nombreArchivo));

Luego:

String[] partes = linea.split(",");
String nombre = partes[0].trim();
double precio = Double.parseDouble(partes[1].trim());
Snack snack = new Snack(nombre, precio);

Proceso:

Archivo → String → Separación → Conversión → Objeto

Esto se denomina deserialización manual.

🧠 Manejo de Memoria

En este sistema:

La memoria (RAM) es el estado activo.

El archivo es persistencia secundaria.

A diferencia de MySQL, el archivo no es un motor transaccional.
Por lo tanto, se requiere sincronización manual entre memoria y almacenamiento.

📌 Diferencia con Base de Datos

Con MySQL:

La base de datos es la fuente de verdad.

No es necesario mantener toda la información en memoria.

Se trabaja directamente contra el motor de BD.

Con archivo plano:

Se debe cargar la información en memoria.

El archivo solo almacena datos.

No hay manejo automático de transacciones ni consistencia.

⚠️ Problemas Identificados Durante el Desarrollo

Duplicación de datos al leer archivo múltiples veces.

Creación innecesaria de objetos duplicados.

Mezcla de lógica de negocio con presentación.

Uso incorrecto de toString() para persistencia.

Necesidad de limpiar espacios con trim() al parsear.

🎯 Conceptos Aplicados

Separación de responsabilidades

Programación contra interfaces

Encapsulación

Desacoplamiento

Persistencia básica en archivo

Reconstrucción de objetos desde texto

Manejo de memoria vs almacenamiento secundario

🚀 Posibles Mejoras Futuras

Migrar persistencia a JSON usando Jackson o Gson

Implementar reescritura completa del archivo en lugar de append

Agregar manejo de excepciones más robusto

Agregar pruebas unitarias

Migrar a base de datos relacional

Implementar patrón DAO

📚 Aprendizajes Clave

La memoria es el estado activo; el archivo es persistencia.

Nunca usar toString() como formato de almacenamiento.

La interfaz define el contrato; la implementación define el comportamiento.

El dominio no debe depender de la interfaz.

La UI no debe contener lógica de negocio.
