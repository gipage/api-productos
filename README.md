# API REST CHALLENGE PAYGOAL
API REST para la gestión de productos, consta de operaciones CRUD de un producto así como también obterner todos los productos ordenados por precio.
## Índice
- [Instalacion](#Instalación)
- [Endpoints API](#endpoints-api)
	- [Create Product](#create-product)
	- [Get Product](#get-product)
	- [Get All](#get-all)
	- [Update Product](#update-product)
  - [Delete User](#delete-product)
  
## Instalación

### Configuración y ejecución de la aplicación
Para configurar, instalar y ejecutar la aplicación se debe seguir los siguientes pasos. Requisito: Java 17.

### Clonar el repositorio
Clonar este repositorio en la máquina local usando el siguiente comando en la terminal:
git clone https://github.com/gipage/api-productos

### Abrir el proyecto en un Entorno de Desarrollo (IDE)
Abrir el  entorno de desarrollo (IntelliJ IDEA, NetBeans, Eclipse, Spring Tool Suite) y seleccionar "Open Project" (Abrir Proyecto) o su equivalente. Navegar hasta la carpeta del proyecto que se acabó de clonar y abrirlo.

### Ejecutar la Aplicación
Buscar la clase principal "ApiProductosApplication" (etiquetada como @SpringBootApplication) y clickear el botón run (ejecutar) del entorno de desarrollo.
Probar los diferentes endpoints siguiendo las instrucciones en la misma docu.

## Endpoints API

#### Create Product
```http
  POST localhost:8080/api/v1/products
```
| Parámetro | Tipo     | Descripción              | Ejemplo|
| :-------- | :------- | :------------------------- | :------------------------- |
| id| `long` | **Requerido** por body.  |1
| name| `String` | **Requerido** por body.  |Heladera
| description| `String` | **Requerido** por body.  | Alto: 142.7. Ancho: 61.4. Color: BLANCO.
| price| `BigDecimal` | **Requerido** por body.  | 300000.00
| quantity| `int` | **Requerido** por body.  | 30

- URL: localhost:8080/api/v1/products
- Método: POST
- Respuesta:
  
201 - CREATED: En el body de la response se devuelve CreateSuccessfullyDTO que consta del id del producto y su fecha de creación.
  
409 - CONFLICT: El producto a crear ya existe. Se lanza una excepción personalizada (ProductAlreadyExist).
  
#### Get Product
```http
  GET localhost:8080/api/v1/products/{id}
```
| Parámetro | Tipo     | Descripción              | Ejemplo|
| :-------- | :------- | :------------------------- | :------------------------- |
| id| `long` | **Requerido** por url.  |1


- URL: localhost:8080/api/v1/products/{id}
- Método: GET
- Respuesta:
  
200 - OK: En el body de la response se devuelve ProductDTO que consta de todos los campos del producto.
  
404 - NOT FOUND: El producto a buscar no existe. Se lanza una excepción personalizada (ProductDoesNotExist)

#### Get All
```http
  GET localhost:8080/api/v1/products
```
| Parámetro | Tipo     | Descripción              | Ejemplo|
| :-------- | :------- | :------------------------- | :------------------------- |
| |  |   |


- URL: localhost:8080/api/v1/products
- Método: GET
- Respuesta:
  
200 - OK: En el body de la response se devuelve una lista de ProductDTO o vacía si no existen productos.

#### Update Product
```http
  PUT localhost:8080/api/v1/products/{id}
```
| Parámetro | Tipo     | Descripción              | Ejemplo|
| :-------- | :------- | :------------------------- | :------------------------- |
| id| `long` | **Requerido** por url.  |1
| name| `String` | **Requerido** por body.  |Heladera
| description| `String` | **Requerido** por body.  | Alto: 142.7. Ancho: 61.4. Color: BLANCO.
| price| `BigDecimal` | **Requerido** por body.  | 300000.00
| quantity| `int` | **Requerido** por body.  | 30

- URL: localhost:8080/api/v1/products/{id}
- Método: PUT
- Respuesta:
  
200 - OK: En el body de la response se devuelve ProductDTO que consta de todos los campos del producto actulizado.
  
404 - NOT FOUND: El producto a actualizar no existe. Se lanza una excepción personalizada (ProductDoesNotExist)

#### Delete Product
```http
  DELETE localhost:8080/api/v1/products/{id}
```
| Parámetro | Tipo     | Descripción              | Ejemplo|
| :-------- | :------- | :------------------------- | :------------------------- |
| id| `long` | **Requerido** por url.  |1

- URL: localhost:8080/api/v1/products/{id}
- Método: DELETE
- Respuesta:
  
204 NO CONTENT: La solicitud se ha procesado correctamente, pero no hay información para enviar en el cuerpo de la respuesta.
  
404 - NOT FOUND: El producto a eliminar no existe. Se lanza una excepción personalizada (ProductDoesNotExist)

  
