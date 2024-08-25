Este proyecto tiene funcionalidades CRUD, utilizando principalmente GetMapping, DeleteMapping, PutMapping y PostMapping.
A día de hoy puede realizar todo tipo de acciones para los ejemplos listados ya existentes en el controlador, así como añadir nuevos a la memoria.
Para las actualizaciones de estados de envío, éste valida según la clase "EstadoEnvio.java", el cual tiene 3 opciones para realizar esta modificación.

Ejemplo eliminación:
en POSTMAN:
DELETE http://localhost:8080/api/envios/3

Elimina registro 3 del producto Juguete para Perro que se dirigía a Ciudad C, desde el Centro de Distribución con status PENDIENTE.

Ejemplo modificación:
en FIREFOX:
http://localhost:8080/api/envios/update/1/EN_CAMINO

Actualiza registro 1, donde estaba el envío PENDIENTE a EN_CAMINO
