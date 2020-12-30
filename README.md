# JDBC Connection App

## Goals

The main goals for this project are to develop a databse connection using MySQL and JDBC.

The project was built from scratch, using core Java and JDBC drivers.

## Tecnologies

 + Java 15
 + mysql-connector-java 8.0.22
 + Github
 + MySQL

## How to test

In order to test this app, it's recommended to hava your favorite IDE installed.
The Java SDK 15 should also be correctly installed.
A running MySQL instance.

Clone the repository.

Create the databse and table named "aluno";
The table includes:
 + id primary key auto-generated;
 + nome varchar(80) not null
 + idade int not null
 + estado char(2) not null

Fill in the variables as depicted in resources -> connection-sample.properties and rename this file to connection.properties

Run the app and test basic CRUD functionalities.