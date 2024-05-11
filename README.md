# Library Management System

The Library Management System is a web application that allows users to manage books, categories, and checkout histories. Users can register, log in, and perform various operations related to library management.

## API Endpoints

### User API

- **Register a User**
    - **Method**: POST
    - **Endpoint**: `/api/auth/register`

- **Login a User**
    - **Method**: POST
    - **Endpoint**: `/api/auth/login`

### Book API

- **Create a Book**
    - **Method**: POST
    - **Endpoint**: `/api/books`

- **Get All Books**
    - **Method**: GET
    - **Endpoint**: `/api/books`

- **Get a Book by ID**
    - **Method**: GET
    - **Endpoint**: `/api/books/{id}`

- **Update a Book by ID**
    - **Method**: PUT
    - **Endpoint**: `/api/books/{id}`

- **Delete a Book by ID**
    - **Method**: DELETE
    - **Endpoint**: `/api/books/{id}`

### Category API

- **Create a Category**
    - **Method**: POST
    - **Endpoint**: `/api/categories`

- **Get All Categories**
    - **Method**: GET
    - **Endpoint**: `/api/categories`

- **Delete a Category by ID**
    - **Method**: DELETE
    - **Endpoint**: `/api/categories/{id}`

### Checkout History API

- **Record Checkout History**
    - **Method**: POST
    - **Endpoint**: `/api/checkout-history`

- **Get All Checkout Histories**
    - **Method**: GET
    - **Endpoint**: `/api/checkout-history`

- **Delete Checkout History by ID**
    - **Method**: DELETE
    - **Endpoint**: `/api/checkout-history/{id}`
