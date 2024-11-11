<h1 align="left"> :shopping_cart: SISTEMA DE VENTAS </h1>

<br><img align="left" src="https://skillicons.dev/icons?i=vscode,java,mysql,github,git" height="37" alt="Tecnologias"><br><br>

## :pen: Acerca de este proyecto

Este es un proyecto personal, consiste en un sistema de ventas en el que se pueden realizar registros de usuarios/empleados, clientes, provedores y por supuesto, registrar el stock de productos disponibles, además, esta pensado para que se puedan registrar pedidos. Todas las ventas que se realizan son registrados con la finalidad de usar los registros para un analisis posterior.

## :hammer: Estructura del proyecto

Usando el patrón de arquitectura MVC (Modelo-Vista-Controlador) he construido la arquitectura de archivos y carpetas, esto permite que la aplicación sea escalable, facilita su mantenimiento y también permite que el contenido sea ordenado. El espacio de trabajo contiene dos carpetas de forma predeterminada, donde:

- `src`: la carpeta para mantener las fuentes
- `lib`: la carpeta para mantener las dependencias

Mientras tanto, los archivos de salida compilados se generarán en el `bin` carpeta de forma predeterminada.

> Si desea personalizar la estructura de carpetas, abra `.vscode/settings.json` y actualice las configuraciones relacionadas allí.

## :package: Resultados

Vista inicial, se muestra la pantalla principal del sistema de ventas, lugar donde se generan las ventas y se accede a los diferentes apartados existentes.

<p align="center">
  <img src="https://raw.githubusercontent.com/samoel-andres/crud_java_mariadb/main/evidence/home.JPG" alt="Vista inicial">
</p>

Más opciones, se muestran otras opciones que dirigen a apartados especificos como agregar productos nuevos, proveedores, etc.

<p align="center">
  <img src="https://raw.githubusercontent.com/samoel-andres/crud_java_mariadb/main/evidence/more_options.JPG" alt="Más opciones">
</p>

Búsqueda de productos, es un apartado en el cual se puede consultar información acerca de todos los productos.

<p align="center">
  <img src="https://raw.githubusercontent.com/samoel-andres/crud_java_mariadb/main/evidence/search_product.JPG" alt="Consultar productos">
</p>

Esta interfaz permite visualizar una lista con todos los productos, además, se muestra un formulario en el que puede agregar productos nuevos o modificar detalles de existentes.

<p align="center">
  <img src="https://raw.githubusercontent.com/samoel-andres/crud_java_mariadb/main/evidence/view_stock.JPG" alt="Ver stock">
</p>

Parte de los detalles de un producto se asocian a un proveedor, los cuales se pueden consultar directamente desde la interfaz anterior.

<p align="center">
  <img src="https://raw.githubusercontent.com/samoel-andres/crud_java_mariadb/main/evidence/details_of_provider.JPG" alt="Detalles del proveedor">
</p>

## :pen: Conclusión

