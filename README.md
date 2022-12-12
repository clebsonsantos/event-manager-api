<h1 align=center>
  <img src="./github/icon.png">
</h1>

[![Run in Insomnia}](https://insomnia.rest/images/run.svg)](https://insomnia.rest/run/?label=Event%20Manager%20Api&uri=https%3A%2F%2Fgithub.com%2Fclebsonsantos%2Fevent-manager-api%2Fblob%2Fmaster%2Finsomnia%2Fhttp_routes.json)

## Config Database

`Change the database information and create the database. `

Run the query to create the database
```
create database events;
```
`Access application.properties`
```
spring.datasource.url= jdbc:mysql://localhost:3306/events?useSSL=false
spring.datasource.username=root
spring.datasource.password=docker
```
## Running
```bash
  mvn install
```

```bash
  mvn spring-boot:run
```
## Tests

```bash
mvn test
``` 

## Features

- [X] Create an event
- [X] Create a user;
- [X] Carry out the operation of registering a user in an event;
- [X] Unsubscribe a user from an event;
- [X] List a user's subscriptions;
- [X] List the registrants of an event;
- [ ] Perform user input into the event.

