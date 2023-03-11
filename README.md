# Video Library

This is a pet project of Lana Timofeeva.
In this project I wanted to show my work with the following technologies:
- Java 14+
- Servlets
- JSP
- JDBC
- Docker
- Postgres
- Migration scripts

## Prerequisites

On you machine you must have:

- Java 14 or higher
- Docker - for application start

## How to get and run application

**First, make sure you have Docker up and running, and also that ports 8080 and 5432 are free.**

The following script will clone the project from github repo, 
set permissions to execute start scripts and execute those scripts 
which will start docker containers for postgres and video library
and set a network between them.
```
git clone git@github.com:tisv2000/video-library-servlets.git
cd video-library-servlets
chmod +x start-app
chmod +x stop-app
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
- Login/registration/logout are available, also with errors handling
- Following functionalities are available
  - Filtering movies by different filter parameters, adding new movies to the library
  - Adding reviews for movies, and also reviewing a list of all reviews for a specific user
  - Adding new movie persons (actors/directors/producers/composers), searching for them, reviewing their data
- There are two **authorization** roles available - User and Admin. Admin has more permissions than a User.
Here are some differences in those permissions:
  - User is only able to see his own reviews, while Admin can also check reviews of any user under `/reviews`
  - Only Admin has permissions to add a new movie or a new movie person, users have read-only permissions 
  and can only add reviews for a movie
- There is localization available for three languages: English, German and Russian

## Database scheme

![database-scheme.png](/src/images/readme/database-scheme.png)
