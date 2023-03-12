# Video Library

This is a pet project of Lana Timofeeva.
In this project I wanted to show my work with the following technologies/tools:
- Java 14+
- Servlets
- JSP, JSTL
- JDBC
- Flyway
- Postgres
- Docker
- Maven
- Git

## Prerequisites

On you machine you must have:

- Java 14 or higher
- Docker - for application start

## How to get and run application

**First, make sure you have Docker up and running, and also that ports 8080 and 5432 are free.**

The following set of commands will clone the project from GitHub repo and 
set permissions to execute `start-app` and `stop-app` scripts.
```
git clone git@github.com:tisv2000/video-library-servlets.git
cd video-library-servlets
chmod +x start-app
chmod +x stop-app
```
To start the app, run the following script.
It will build the project, start docker containers for postgres and video-library and set up a network
between them. Then it will run migration scripts to create and fill out database tables,
and after that it will automatically open video-library start page in the browser under
the localhost.
```
./start-app
```

To stop docker containers for Postgres and Video Library and remove network between them, run the cleanup script:
```
./stop-app
```
There are two predefined users that you can use:
- Admin - login: **admin@gmail.com**, password: **pass12345**
- User - login: **user1@gmail.com**, password: **pass12345**

## Key features
- This application is a movie catalog with a following functionality:
  - All users:
    - Filtering movies by different filter parameters
    - Viewing movies detailed information, including movie participants (actors/directors/producers/composers) and users' reviews
    - Viewing movie participants detailed information
    - Adding reviews for movies
  - Admins only:
    - Adding new movies to the library
    - Adding new movie participants
    - Viewing reviews for a specific user
- Login/registration/logout are available
- There is localization available for three languages: `English`, `German` and `Russian`

## Database scheme

![database-scheme.png](/doc/img/database-scheme.png)
