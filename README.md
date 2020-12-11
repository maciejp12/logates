# Logates
Java desktop application for logic circuts simulation
## Table of contents
* [General info](#general-info)
* [Technologies](#technologies)
* [Setup](#setup)

## General info
The project is java desktop application created with java swing library. Project features simulator of logic gates and cables circuts.
## Technology
Project is created with Java 11.

To run tests junit-4.9 library is needed.
## Setup
To compile project use:
```
$ git clone "https://github.com/maciejp12/logates.git"
$ cd logates
$ find src/logates/ -name "*.java" -print | xargs javac -d out/production/logates/logates/
```
Run tests:
```
$ java -cp junit-4.9.jar org.junit.runner.JUnitCore out/production/logates/test/*
```
Run application:
```
$ java -cp out/production/logates/ logates.Main
```
Example screenshot:

![Application Demo](./images/demo/logatesdemo.png)
