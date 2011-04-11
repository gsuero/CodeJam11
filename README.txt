Descripcion General
----
Quedamos en suministrar una aplicación sencilla, que permita a cualquier persona hacer una orden suministrando datos basicos como nombre, telefono y direccion. En la orden, los productos que quiere y su cantidad.

Existe una pantalla de especiales donde los clientes pueden ver los especiales vigente y es la URL utilizada para ser compartida cuando un especial es creado y enviado via REDES sociales

Po ejemplo: Facebook user: PuestoDeQuipe: Empanadas a $RD25, Quipes a Tanto... Mas Info [URL]

Existe una administracion para los productos y los ingredientes (por cantidad) que utiliza cada uno. Quedamos en hacer un AutoRestocking cuando un ingrediente este al limite (2 dias antes que se pueda acabar)... Ademas de la parte manual, tambien quedamos en hacer un Restocking manual a Suplidora Quisqueyana.

Existe tambien una pantalla para que el "cocinero" o administrador vea las ordenes via la Web, con un autorefresh y que estos puedan proceder a entregar las ordenes y marcarlas/borralas como terminadas...

Usuarios: 
Administrador Web : admin/admin
Twitter: PuestoQuipePMF/ProgrammingMotherfucker$5
Facebook: puestodequipe.pmf@gmail.com/ProgrammingMotherfucker$5



Requerimientos tecnicos 
-----
Java 1.6
Glassfish 3.1 Application Server
OS: cualquiera



Instrucciones para “instalar” el software compliado /CMP/.
-----
1. Instalar el PuestoDeQuipe.ear en el application server Glassfish 3.1+ suministrado.
2. Correr script.sql en base de datos derby del Glassfish. por default user: app, password: app