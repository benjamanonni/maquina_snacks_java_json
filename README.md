# Máquina de Snacks en Java

Aplicación de consola desarrollada en Java para gestionar una máquina de snacks, aplicando separación por capas, programación contra interfaces y persistencia en archivo plano.

## Descripción

Este proyecto implementa un sistema simple de inventario y compra de snacks desde consola.  
Permite agregar productos al inventario, visualizarlos, comprarlos y generar un ticket final con el detalle del carrito y el total acumulado.

El diseño está organizado en capas para separar el modelo de dominio, la lógica de negocio y la interacción con el usuario.

## Funcionalidades

- Agregar snacks al inventario
- Mostrar catálogo de snacks disponibles
- Comprar snacks por nombre
- Mantener un carrito de compra
- Mostrar ticket final con el detalle de productos comprados
- Calcular el total de la compra
- Persistir inventario en archivo plano
- Reconstruir objetos desde archivo al iniciar el sistema

## Estructura del proyecto

- `dominio/Snack.java`: modelo del snack
- `servicio/IServicioSnacks.java`: contrato de operaciones del sistema
- `servicio/ServicioSnacksLista.java`: implementación en memoria usando listas
- `servicio/ServiciosSnackArchivos.java`: implementación con persistencia en archivo
- `presentacion/MaquinaSnacks.java`: menú e interacción con el usuario
- `main.java`: punto de entrada de la aplicación

## Conceptos aplicados

- Programación orientada a objetos
- Separación de responsabilidades
- Arquitectura en capas
- Programación contra interfaces
- Persistencia en archivo plano
- Reconstrucción de objetos desde texto
- Manejo de colecciones con `ArrayList`

## Tecnologías utilizadas

- Java
- Scanner
- File / FileWriter / PrintWriter
- Files / Paths

## Funcionamiento de la persistencia

El sistema utiliza un archivo plano para almacenar los snacks del inventario.

- Al iniciar, verifica si el archivo existe
- Si existe, lee su contenido y reconstruye objetos `Snack`
- Si no existe, crea el archivo
- Cada nuevo snack agregado también se guarda en el archivo

El formato de persistencia se maneja manualmente, guardando cada snack como una línea de texto con nombre y precio.

## Objetivo del proyecto

Este proyecto fue desarrollado como práctica para aplicar conceptos de diseño en Java, especialmente separación por capas, desacoplamiento mediante interfaces y persistencia básica sin base de datos.

## Posibles mejoras

- Reemplazar archivo plano por JSON
- Reescribir completamente el archivo para evitar inconsistencias
- Mejorar el manejo de errores y validaciones
- Separar aún más la lógica del ticket
- Incorporar pruebas unitarias
- Migrar la persistencia a una base de datos

## Estado

Proyecto funcional de práctica, orientado al aprendizaje de arquitectura básica y persistencia en Java.
