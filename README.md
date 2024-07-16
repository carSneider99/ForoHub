<h1>ForoHub: Challenge del curso de Alura y Microsoft One</h1>

Se propone solución al desafío ForoHub: Challenge del curso de Alura y Microsoft One para desarrollar una API Rest usando Spring con el objetivo de implementar un CRUD. Se utiliza Liquibase para el versionamiento de la base de datos MySQL, además de la utilización de Spring Security y buenas prácticas para el desarrollo de una API REST

Se utiliza la dependencia Swagger para la documentación de la API Rest implementada. Los endpoints disponibles de la API son:

![1-Swagger.jpeg](img/1-Swagger.jpeg)

Al ejecutar el proyecto, se tiene el siguiente usuario por defecto para ser usado y probar la distintas funcionalidades de la API Rest:
![img.png](img/2-UsuarioPorDefecto.png)

Dentro de las funcionalidades solicitadas por el reto, se tiene:

1) Listar tópicos: El proyecto cuenta con unos tópicos ya creados en la BBDD. Se implementa paginación para listar los tópicos.

![3-ListarTopicos.jpeg](img/3-ListarTópicos.jpeg)


2) Registrar Nuevo tópico: Se registra nuevo tópico, validando que el id sea igual a -1 (Lo que lo diferencia con actualizar), además de implementar
la regla de negocio de no registrar un tópico con título y mensaje duplicados.
![img.png](img/4-AgregarTópico.png)


3) Detallar tópico especifico: Se implementa la funcionalidad de detallar tópico por Id. La API Rest válida que el id especificado no sea nulo,
además que exista en la base de datos. 
![img.png](img/5-DetallarTópico.png)


4) Actualizar tópico: Se implementa endpoint de actualizar tópico. Al actualizar, debe existir el identificador del curso y del autor, de lo contrario se 
tendrá un error. Además, el título y mensaje a actualizar no deben de ya estar registrados, o se tendrá el siguiente error

![img.png](img/6-ActualizarTópico.png)

5) Eliminar tópico: Se debe de especificar el Id del tópico a eliminar. De no existir, se tendrá un error cómo la siguiente imagen: 

![img.png](img/7-EliminarTópicoError.png)

En caso de existir, eliminará de forma permanente el tópico por el id específicado, obteniendo una respuesta 204. 
![img.png](img/8-EliminarTópico.png)

<h1>Muchas gracias, quedo atento a su comentario y opiniones.</h1>