# Products and Image API 

Product API with Image resource developed for studying purpose.

## Getting Started

This API was developed for studying purpose.

The developed API is following the pre-requisites below:

1) Create, update and delete products
2) Create, update and delete images
3) Get all products excluding relationships (child products, images) 
4) Get all products including specified relationships (child product and/or images) 
5) Same as 3 using specific product identity 
6) Same as 4 using specific product identity 
7) Get set of child products for specific product 
8) Get set of images for specific product


### Build

Project can be build by Maven:

```
mvn install
```

### Running

Products API was developed with JAX-RS/Jersey + Spring Boot. the following Sprint Boot instruction will run the API

```
mvn spring-boot:run
```

### Testing

Unit and integration Test was developed to cover API functionalities developed. The following Maven instruction run all automated tests

```
mvn test
```

## API enpoints

Below, there are a brief description of API endpoints and what they are

### Create products

Products are created by POST

```
POST: /products
```

### Update products

Products are updated by PUT

```
PUT: /products/{productID}
```

### Delete products

Images are Updated by  DELETE

```
DELETE: /products/{productID}
```

### Create images as sub-resources of Product

Images sub-resoruces are created by POST

```
POST: /products/{productId}/images
```

### Update images

Images sub-resources are updated by PUT. A valid product ID must be informed

```
PUT: /products/{productID}/images/{imageId}
```

### Delete images

Images sub-resources are removed by DELETE. A valid product ID must be informed

```
DELETE: /products/{productID}/images/{imageId}
```

### Get All Products without relationship

All products without relationship can be returned by GET

```
GET: /products
```

### Get Product BY ID without relationship

A specific product without relationship can be returned get by GET. A valid product ID must be informed

```
GET: /products/{productID}
```

### Get All Products with Images sub-resources

All products with Image resource relationship can be returned get by GET

```
GET: /products/images
```

### Get Product BY ID with Images sub-resources

A specific product with Image resource relationship can be returned get by GET. A valid product ID must be informed

```
GET: /products/{productID}/images
```

### Get All Products with Product Children 

All products with Children Product resource relationship can be returned get by GET

```
GET: /products/childProducts
```

### Get Product BY ID with Children Product sub-resources

A specific product with Children Product resource relationship can be returned get by GET. A valid product ID must be informed

```
GET: /products/{productID}/childProducts
```

### Get All Products with Images and Product Children 

All products with Children Product resource relationship can be returned get by GET

```
GET: /products/images/childProducts
```

### Get Product BY ID with Images and Children Product sub-resources

A specific product with Children Product resource relationship can be returned get by GET. A valid product ID must be informed

```
GET: /products/{productID}/images/childProducts
```

### Get set of Child Products By Product ID

A set of Child Products can be returned get by GET. A valid product ID must be informed

```
GET: /products/{productID}/childProducts/set
```

### Get set of Images By Product ID

A set of Images can be returned get by GET. A valid product ID must be informed

```
GET: /products/{productID}/images/set
```

## Deployment

Add additional notes about how to deploy this on a live system

## Built With

* JAX-RS/Jersey
* Spring Boot
* Spring Data
* Maven
* JUnit
* Hamcrest

## Author

* **Rodrigo Peleias** - [GitHub](https://github.com/rpeleias)

