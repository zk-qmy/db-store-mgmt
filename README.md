# Store Retail Management System

## Design
Use 3-layer and MVC architecture patterns.

## Features
to be updated

## Technology 
- **Java**: coding language
- **Java Swing**: GUI
- **MySQL**: database management system
  
## Tentative project structure
```
store-retail-management-system/
│
├── src/
│   ├── main/
│   │   ├── Main.java                  # Entry point of the application
│   │   └── DatabaseConnection.java    # Centralized DB connection management
│   ├── product/
│   │   ├── Product.java               # Product entity/model class
│   │   ├── ProductDAO.java            # Data Access Object for Product
│   │   ├── ProductService.java        # Service layer for Product
│   │   └── ProductController.java     # Controller handling Product logic
│   ├── order/
│   │   ├── Order.java                 # Order entity/model class
│   │   ├── OrderDAO.java              # Data Access Object for Order
│   │   ├── OrderService.java          # Service layer for Order
│   │   └── OrderController.java       # Controller handling Order logic
│   ├── customer/
│       ├── Customer.java              # Customer entity/model class
│       ├── CustomerDAO.java           # Data Access Object for Customer
│       ├── CustomerService.java       # Service layer for Customer
│       └── CustomerController.java    # Controller handling Customer logic
├── resources/
│   └── config.properties              # Database configuration file
├── sql/
│   └── schema.sql                     # SQL schema for creating database tables
├── README.md
├── .gitignore
├── pom.xml or build.gradle             # Build configuration (Maven or Gradle)
└── LICENSE


```
