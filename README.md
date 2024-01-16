# Lighter Management System

Welcome to the Lighter Management System, a web application that provides a management system for selling lighters. 
This system allows authorized users to access, update, and delete data stored in a MongoDB database.

## Description

The project consists of a web-based system that requires authentication for access. Users can log in using the the login credentials:

- **Username:** `your_login`
- **Password:** `your_password`

Once authenticated, users can interact with the system to view, update, and delete data related to lighter products.

### Project Structure

The backend of the project is organized into two main folders:

- **LighterConfig:** Handles the processing of POST, PUT, GET, and DELETE requests. It ensures proper handling of interactions with the MongoDB database, allowing users to manage lighter data effectively.

- **Authentication:** Manages user authentication and token validation. It ensures that only users with the correct password can access the web application.

## Installation

To use this project, follow these steps:

1. Set up a MongoDB database and obtain the connection URL.
2. Open the `application.properties` file in the project.
3. Update the `spring.data.mongodb.database` and `spring.data.mongodb.uri` properties with your database name and connection URL, respectively.

Example `application.properties`:

```properties
spring.data.mongodb.database=your_database_name
spring.data.mongodb.uri=mongodb://your_username:your_password@your_host:your_port/your_database_name
```


## Usage

1. Run the MyApplication.java file to start the backend server.
2. Open the login.html file in a web browser to access the login page.
3. Enter the predefined login credentials:
    Username: your_login
    Password: your_password
4. Explore and interact with the Lighter Management System.

