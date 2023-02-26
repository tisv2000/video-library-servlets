# Video Library

This is a pet project of Lana Timofeeva.
In this project I wanted to show my work with the following technologies:
- Java 17
- Servlets
- JSP
- Docker
- Postgres
- Migration scripts

## Prerequisites

On you machine you should have:

- Java 14 or higher
- Docker - for application start

##Start the server locally:
For local run, just simple execute the start script. 

To make scripts executable from a command line, run:
```
chmod +x start
chmod +x cleanup
```
Run start script to start up docker containers for Postgres and Video Library and set up a network between them:
```
./script-start
```

To stop docker containers for Postgres and Video Library and remove network between them, run the cleanup script:
```
./script-cleanup
```

## Future improvements

- Use H2 DB for tests, so that real (production) DB is not affected
- More unit tests - for service and util classes
- Localization
- Hide password
- Restrict user to only be able to add one review for the movies + add possibility to delete reviews
- Add tests with Selenium Web Driver
