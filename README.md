# Muestra de código para Corunet

Este repositorio contiene cuatro proyectos de Spring seleccionados con el objetivo de mostrar mis conocimientos de Spring, y un Script MySQL por si queréis probar.

>Si en lugar o aparte de Spring, estáis interesados en otras tecnologías hacedmelo saber y os envío algo más.

Tras la conversación con Daniel y Rubén me he decidido por este formato: de código muy sencillo pero buena muestra de arquitectura e implementación de dependencias.

>Una vez mas si buscáis algo mas centrado en algoritmos, por ejemplo, solo pedidlo.

-El primero es un proyecto de Eclipse de hace años, de cuando aprendí Spring, (en realidad no es mi proyecto sino el que facilitaba el curso,
ya que no recuerdo donde esta guardado el que yo hice en su momento y mi objetivo es desarrollar el comentario mas que el código en si mismo)
Este es un servicio sencillo, toma una tabla de una BBDD y expone endpoints REST con la funcionalidad básica CRUD.

En el proyecto encontramos una arquitectura en capas con paquetes convenientemente organizados para representarla:

  * `.entity`: Una clase anotada `@Entity` que representa la tabla del mismo nombre. No tiene nada de especial.
  * `.service`: La capa de servicio de la aplicación, que al no tener lógica de negocio es realmente innecesaria, como veremos mas adelante. Contiene una interfaz y su implementación
  * `.DAO`: Aquí es donde entramos en harina; 
  Primero una interfaz `EmployeeDAO` que tiene cuatro métodos para las funcionalidades básicas. Y luego, y esto es lo interesante, dos implementaciones una con Hibernate y otra con JPA.
  A primera vista no parecen muy diferentes pero lo importante es que en el caso de JPA no tenemos la necesidad de manejar sesiones, Spring lo hará por nosotros tras las bambalinas. Lo 
  cual no solo ahorra trabajo pero hace que el código corra de forma mas eficiente. Y ya no hablemos de JDBC y el el manejo de `Conection`, `Session` y `Statement` con captura de excepciones y demás familia.
  * `.rest`: Este ultimo paquete incluye un `@RestController` que se encarga de exponer endpoints para conectarse mediante HttpRequest.

>La sintaxis en este ejemplo es muy... ¿académica?
Por ejemplo: se cachean variables de forma innecesaria lo cual ayuda a entender el código al separar mas cada instrucción. Como dije antes el foco no de este envío no es la sintaxis o lógica complejas.


```
@Override
public List<Employee> findAll() {

  // create a query
  Query theQuery = 
      entityManager.createQuery("from Employee");

  // execute query and get result list
  List<Employee> employees = theQuery.getResultList();

  // return the results		
  return employees;
}
```
Esto se abreviar como:
```
@Override
public List<Employee> findAll() {
  return entityManager.createQuery("from Employee").getResultList();
}
```
-El segundo proyecto es una variación del primero. Y si la capa de DAO era quizás la mas interesante en uno, en este se hace uso de `JpaRepository`.
Al extender esta interfaz nos proporciona todos los métodos declarados e implementados en la anterior versión. Ahorrando tiempo, trabajo y volumen 
de código, lo cual, especialmente en proyectos mas grandes, aumenta enormemente la eficiencia en el desarrollo.

Esto expone el valor de escoger las tecnologías adecuadas y la necesidad por parte de los desarrolladores de tener un buen bagaje en lo que a conocer frameworks y dependencias se refiere.
También hace brillar un parte a veces olvidada de la arquitectura de micro servicios que nos permitiría utilizar distintas implementaciones e incluso lenguajes de programación, contenedores, frameworks etc.. para cada parte de la aplicación.

-Y si Reducir código y utilizar los frameworks mas apropiados es lo que queremos el tercer proyecto lleva esto todavía más lejos con la inclusión de Spring Data REST!
En este proyecto no solo se elimina casi todo el código de la capa DAO sino que las capas de servicio y controladores desaparecen por completo!

Es evidente que este no es un ejemplo transferible a un escenario real, la capa de servicio se elimina solo por que no existe lógica de negocio, y la de controlador por que no hay un requerimiento de endpoints específicos. Sin embargo me parece una buena forma de ilustrar lo que se puede llegar a hacer cuando se utilizan las herramientas adecuadas.
Ahorrando cantidades ingentes de tiempo y dinero o proporcionando una gran ventaja competitiva a aquellos que mejor sepan valerse de estos recursos.

-Por ultimo el cuarto proyecto recrea al tercero pero utilizando Kotlin en lugar de Java. En lineas generales es lo mismo pero la única clase que todavía conservaba el código original `Employee` ahora también se ha abreviado hasta lo irrisorio, manteneiendo toda la funcionalidad.
 
 Otro ejemplo:
 
 ```
 //Java
 private EmployeeService employeeService;
	
@Autowired
public EmployeeRestController(EmployeeService theEmployeeService) {
  employeeService = theEmployeeService;
}
 ```

```
//Kotlin

@Autowired
private lateinit var service:EmployeeService
```

## Ejecución de proyectos:
Todos son proyectos de Maven y todos usan la BBDD incluida en `SSQ/employee.sql`.<br>
Todos lo proyectos tienen una clase Main que se puede ejecutar e incluyen la dependencia `Spring WEB MVC` por lo que se pueden testear desde el IDE.<br>
Los datos de las dependencias están en pom.xml asi como la versión de Java y demás.<br>
Y los datos de la conexión de MySQL estan en `/'nombre-projecto'/src/main/resources/application.properties`.<br>

>Finalmente insisto: si queréis otro tipo de proyecto o mas de lo mismo por favor no dudéis en pedírmelo o consultarme cualquier cosa sobre lo aquí incluido.

