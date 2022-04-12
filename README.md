# Back-End Java Spring Boot - Project employees vaccination

## Información
El proyecto  se encuentra desarrollado haciendo uso de:
* Framework Spring Boot
* Base de datos Postgres.
* Jdk 11
* Maven
* Oauth2

Se encuentra desarrollando bajo el paradigma de programación orientada a objetos.

## Pre-Requisitos

1. Java 11 o superior
2. [Spring Boot](https://spring.io/projects/spring-boot) 
3. [Maven](https://maven.apache.org/) 3.6.0 o superior
4. IDE: [Spring Tools Suite](https://spring.io/tools3/sts/all), Intellij IDEA o Eclipse con soporte para Spring

## Estructura
_DIRECTORIO RAIZ DEL PROYECTO_

* src
  * main
      * java
          * com
              * krugercorp
                  * **employeesvaccination**
                      * commons
                          * bo
                          * enumerations
                          * exceptions
                          * request
                          * response
                          * util
                      * config
                      * entity
                      * repository
                          * impl
                      * rest
                      * service
                          * impl
      * resources
          * keys
          * scripts
          * static
          * templates
          * application.properties
  * test
      * java
          * com
              * krugercorp
                  * **employeescvaccination**
                      * commons
                      * service
* pom.xml


## Versionado 📌

Se uso **GIT** para el versionado 

Se uso **GITHUB** para el repositorio remoto.

## Instrucciones 🚀

1. El repositorio remoto  del proyecto se encuentra en GITHUB [repositorio remoto](https://github.com/viniciohuertas/employeesvaccination).

    Podemos clonar el proyecto con el siguiente comando:
      
      `git clone https://github.com/viniciohuertas/employeesvaccination.git`


2. Ejecutar los scripts en la base de datos que se encuentran en: **/resources/scripts**
   Para ello se debe ingresar a la terminal interactiva de PostgreSQL con este comando:
   
   `$psql postgres`
   * Primero ejecutar el script: **/resources/scripts/schema.sql**
   * Ejecutar el script: **/resources/scripts/data.sql**
    

3. Compilar el proyecto, haciendo uso del comando: 
   
   `mvn clean install`
   
   Puede hacerlo ubicándose en la raíz del proyecto, donde se encuentre el archivo **pom.xml**
   

4. Para ejecutar el proyecto se puede hacer uso del comando: 
    
    `java -jar /target/employeesvaccination-1.0.0.jar`



## Manual de usuario

* Para probar la aplicación puede hacer uso de **SOAP-UI**
* Los métodos se encuentran publicados en: **http://localhost:8080/swagger-ui/index.html**


* Los *API REST* disponibles son:
  * POST `http://localhost:8080/login` 
  * POST `http://localhost:8080/employees` (ADMIN) - Inserta un empleado, usuario y rol con los campos obligatorios: identificacion, 
  * GET  `http://localhost:8080/employees` (ADMIN) - 
  * GET  `http://localhost:8080/employees/{id}` (ADMIN, EMPLOYEE)
  * PUT  `http://localhost:8080/employees/{id}` (ADMIN, EMPLOYEE)
  * DELETE  `http://localhost:8080/employees/{id}` (ADMIN)
  * GET  `http://localhost:8080/catalogues/{abbreviation}` (ADMIN, EMPLOYEE)
  * PATCH `http://localhost:8080/employees/{id}` (EMPLOYEE)
  * POST  `http://localhost:8080/employees/{id}/vaccines` (EMPLOYEE)


* Puede acceder con oauth2, los datos de acceso como administrador son:

  * Oauth flow: **Resource Owner Password Credentials Grant**
  * name: **admin**
  * password: **1234**
  * client_id: **admin**
  * cliente_secret: **1234**
  * Access toke URI: **http://localhost:8080/login**



## Autor ✒️

* **Mgs. Vinicio Javier Huertas C.**
