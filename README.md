# PixelPopShop API

A Spring Boot REST API for an e-commerce platform that allows users to browse products, 
manage categories, and build shopping functionality. The API uses JWT authentication and 
MySQL for data persistence.

## Features Implemented

- User authentication (login/register) with JWT tokens
- Category management (CRUD operations, admin only)
- Product browsing and search with filtering
- Product search by category, price range, and subcategory
- Admin-only product management
- Role-based access control (User/Admin)

## Future Features

- Shopping cart (Phase 3 - started, not completed)
- User profile management
- Checkout and order processing

## Technologies Used

- **Backend:** Spring Boot 2.x
- **Database:** MySQL
- **Authentication:** JWT (JSON Web Tokens)
- **Build Tool:** Maven
- **ORM:** JDBC (PreparedStatements)
- **Security:** Spring Security with BCrypt password encoding

## API Endpoints

### Categories (Admin Only)
- GET `/categories` — Get all categories
- GET `/categories/{id}` — Get category by ID
- POST `/categories` — Create new category
- PUT `/categories/{id}` — Update category
- DELETE `/categories/{id}` — Delete category

### Products
- GET `/products` — Get all products with optional filters
- GET `/products?cat=1` — Filter by category
- GET `/products?minPrice=25&maxPrice=100` — Filter by price range
- GET `/products?subCategory=red` — Filter by subcategory
- GET `/products/{id}` — Get product by ID
- POST `/products` — Create product (admin only)
- PUT `/products/{id}` — Update product (admin only)
- DELETE `/products/{id}` — Delete product (admin only)

### Authentication
- POST `/register` — Register new user
- POST `/login` — Login user (returns JWT token)

## Screenshots

<img width="1227" height="828" alt="Screenshot 2025-12-17 101731" src="https://github.com/user-attachments/assets/ef71fb46-b4ec-418a-a36c-011897cdb75a" />

- Postman testing for all basic/required functions

## Code Highlight: Dynamic Search Filter

The search functionality uses a clever SQL approach to handle optional parameters:

<img width="1046" height="927" alt="Screenshot 2025-12-17 102023" src="https://github.com/user-attachments/assets/6bc339f8-3035-490c-b356-7026b1211001" />


This allows users to filter by category, price range, and subcategory independently without having to provide all filters. 
The SQL uses OR conditions with default values to include results when a filter isn't provided. It was the first bug I had to fix 
and was the hardest for me to figure out, I had to learn sometimes you have to take a break and come back later to look at it with fresh eyes!
