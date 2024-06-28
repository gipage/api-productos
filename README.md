# RESTful API of products for Paygoal Challenge
REST API for product management, consists of CRUD operations of a product as well as getting all products sorted by price.
## Table of Contents
- [Installation](#installation)
- [Endpoints API](#endpoints-api)
	- [Create Product](#create-product)
	- [Get Product](#get-product)
	- [Get All](#get-all)
	- [Update Product](#update-product)
  - [Delete User](#delete-product)
- [Swagger documentation](#swagger-documentation) 
  
## Installation

### Configuration and execution of the application
To configure, install and run the application the following steps must be followed. Requirement: Java 17.

### Clone the repository
Clone this repository on the local machine using the following command in the terminal:
```bash
git clone https://github.com/gipage/api-productos
```
### Opening the project in a Development Environment (IDE)
Open the development environment (IntelliJ IDEA, NetBeans, Eclipse, Spring Tool Suite) and select "Open Project" or its equivalent. Navigate to the folder of the project that was just cloned and open it.

### Running the Application
Find the main class "ApiProductsApplication" (labeled as @SpringBootApplication) and click the run button of the development environment.
Test the different endpoints following the instructions in the same docu.

## Endpoints API

#### Create Product
```http
  POST localhost:8080/api/v1/products
```
| Parameter | Type     | Descripction             | Example|
| :-------- | :------- | :------------------------- | :------------------------- |
| name| `String` | **Required** by body.  |Heladera
| description| `String` | **Required** by body.  | Alto: 142.7. Ancho: 61.4. Color: BLANCO.
| price| `BigDecimal` | **Required** by body.  | 300000.00
| quantity| `int` | **Required** by body.  | 30

- URL: localhost:8080/api/v1/products
- Method: POST
- Response:
  
201 - CREATED: In the body of the response we return CreateSuccessfullyDTO which consists of the product id and its creation date.
  
409 - CONFLICT: The product to be created already exists. A custom exception (ProductAlreadyExist) is thrown.
  
#### Get Product
```http
  GET localhost:8080/api/v1/products/{id}
```
| Parameter | Type     | Description              | Example|
| :-------- | :------- | :------------------------- | :------------------------- |
| id| `long` | **Required** by url.  |1


- URL: localhost:8080/api/v1/products/{id}
- Method: GET
- Response:
  
200 - OK: The body of the response returns ProductDTO which consists of all the fields of the product.
  
404 - NOT FOUND: The product to search for does not exist. A custom exception (ProductDoesNotExist) is thrown.

#### Get All
```http
  GET localhost:8080/api/v1/products
```
| Parameter | Type     | Description              | Example|
| :-------- | :------- | :------------------------- | :------------------------- |
| |  |   |


- URL: localhost:8080/api/v1/products
- Method: GET
- Response:
  
200 - OK: In the body of the response a list of ProductDTO is returned sorted from highest to lowest (this convention was used) or empty if there are no products.
#### Update Product
```http
  PUT localhost:8080/api/v1/products/{id}
```
| Parameter | Type     | Description              | Example|
| :-------- | :------- | :------------------------- | :------------------------- |
| id| `long` | **Required** by url.  |1
| name| `String` | **Required** by body.  |Heladera
| description| `String` | **Required** by body.  | Alto: 142.7. Ancho: 61.4. Color: BLANCO.
| price| `BigDecimal` | **Required** by body.  | 300000.00
| quantity| `int` | **Required** by body.  | 30

- URL: localhost:8080/api/v1/products/{id}
- Method: PUT
- Response:
  
200 - OK: In the body of the response, ProductDTO is returned, which consists of all the fields of the updated product.
  
404 - NOT FOUND: The product to be updated does not exist. A custom exception (ProductDoesNotExist) is thrown.

#### Delete Product
```http
  DELETE localhost:8080/api/v1/products/{id}
```
| Parameter | Type     | Description              | Example|
| :-------- | :------- | :------------------------- | :------------------------- |
| id| `long` | **Required** by url.  |1

- URL: localhost:8080/api/v1/products/{id}
- Method: DELETE
- Response:
  
204 NO CONTENT: The request has been processed successfully, but there is no information to send in the body of the response.
  
404 - NOT FOUND: The product to be deleted does not exist. A custom exception (ProductDoesNotExist) is thrown.

## Swagger Documentation
Once the application has been executed, access from the browser to [http://localhost:8080/swagger-ui/index.html#/](http://localhost:8080/swagger-ui/index.html)

  
