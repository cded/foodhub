# Restaurant Food Delivery Backend

This repository contains the backend code for a white-label restaurant food delivery web application. The backend is built using Spring Boot, providing RESTful APIs to manage products, users, and orders for multiple restaurants.

## Table of Contents

- [Technologies Used](#technologies-used)
- [Project Structure](#project-structure)
- [Getting Started](#getting-started)
- [API Endpoints](#api-endpoints)
- [Contributing](#contributing)
- [License](#license)

## Technologies Used

- **Spring Boot:** A powerful framework for building Java-based enterprise applications.
- **Spring Data JPA:** Simplifies data access using the Java Persistence API.
- **PostgreSQL:** A powerful, open-source relational database.
- **Maven:** A build automation tool for managing project dependencies.
- **Spring Security:** Provides authentication and authorization for the APIs.
- **Flyway:** Manages database schema versioning.

## Project Structure

The project follows a modular structure to organize different components:

- **restaurant:** Manages various aspects of restaurant functionalities, such as menus, products, categories.
- **auth:** Manages users, authentication, and authorization.
- **order:** Manages the order lifecycle.
- **delivery:** Manages the delivery process.
- **payment:** Integrates with payment gateways to handle payment transactions securely.
- **transaction:** Manages the transaction process.
- **billing:** Handle the billing portion.

- Each module has a similar structure:
  - **model:** Contains entity classes (e.g., `Product`, `ProductCategory`).
  - **repository:** Defines Spring Data JPA repositories for database access.
  - **service:** Implements business logic for products and categories.
  - **controller:** Handles API requests and responses.
  - **payload:** Contains request payload classes for API endpoints.

- **config:** Contains configuration classes (e.g., security configuration).

## Getting Started

To run this project locally, follow these steps:

1. Clone the repository: `git clone https://github.com/cded/foodhub.git`
2. Navigate to the project folder: `cd foodhub`
3. Build the project: `mvn clean install`
4. Run the application: `mvn spring-boot:run`

The application will start on [http://localhost:8080](http://localhost:8080).

## API Endpoints

- **Product Management:**
  - GET `/api/products`: Get a list of all products.
  - GET `/api/products/{productId}`: Get details of a specific product.
  - POST `/api/products`: Create a new product.
  - PUT `/api/products/{productId}`: Update an existing product.
  - DELETE `/api/products/{productId}`: Delete a product.

- **Category Management:**
  - GET `/api/product-categories`: Get a list of all product categories.
  - GET `/api/product-categories/{categoryId}`: Get details of a specific product category.
  - POST `/api/product-categories`: Create a new product category.
  - PUT `/api/product-categories/{categoryId}`: Update an existing product category.
  - DELETE `/api/product-categories/{categoryId}`: Delete a product category.

- **Order Management:**
  - GET `/api/orders`: Get a list of all orders.
  - GET `/api/orders/{orderId}`: Get details of a specific order.
  - POST `/api/orders`: Create a new order.
  - PUT `/api/orders/{orderId}`: Update an existing order.
  - DELETE `/api/orders/{orderId}`: Delete an order.

## Contributing

Contributions are welcome! Feel free to open issues or pull requests for any improvements or bug fixes.

## License

This project is licensed under the [GNU General Public License v3.0](LICENSE).
