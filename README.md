# API Testing Example with SerenityRest BDD (and Cucumber)
This repo demonstrates how to do API Test Automation using SerenityRest (RestAssured under the hood) with Cucumber BDD format.

<!--ts-->
* [API Testing Example with SerenityRest BDD (and Cucumber)](#api-testing-example-with-serenityrest-bdd-and-cucumber)
   * [Application under Test](#application-under-test)
   * [Exactly what APIs are we testing?](#exactly-what-apis-are-we-testing)
      * [Add User](#add-user)
      * [Login User](#login-user)
      * [Logout User](#logout-user)
   * [Repository Structure](#repository-structure)
   * [Run the tests](#run-the-tests)
   * [Resources](#resources)

<!-- Created by https://github.com/ekalinin/github-markdown-toc -->
<!-- Added by: rashad, at: Sun Feb 18 11:38:05 AM EST 2024 -->

<!--te-->

## Application under Test

We will be testing the [Thinking Tester Contact List App](https://thinking-tester-contact-list.herokuapp.com/)

Specifically, the [API](https://documenter.getpostman.com/view/4012288/TzK2bEa8)

## Exactly what APIs are we testing?

In case Thinking App goes down or the API is updated, here is the gist of the current APIs tested at the time of writing.

We specifically will cover the workflow of adding a new user, logging in and logging out.

### Add User

```
POST Request ->
https://thinking-tester-contact-list.herokuapp.com/users

Request Body ->
{
    "firstName": "Test",
    "lastName": "User",
    "email": "test@fake.com",
    "password": "myPassword"
}

Response Status ->
201
```

### Login User

```
POST Request ->
https://thinking-tester-contact-list.herokuapp.com/users/login

Request Body ->
{
    "email": "test2@fake.com",
    "password": "myNewPassword"
}

Response Status ->
200

Response Body ->
{
  "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJfaWQiOiI2MDgyMWYzMDYyZmJiMjEzZTJhZDlhMjAiLCJpYXQiOjE2MTk3M
}
```

### Logout User

```
POST Request ->
https://thinking-tester-contact-list.herokuapp.com/users/logout

Header ->
Authorization: Bearer $token

Response Status ->
200
```

## Repository Structure
```
src/test/resources/features
└── User.feature #the test scenario
```

```
src/test/java/starter
├── apis #resquest specifications for various APIs
│   └── UserAPI.java
├── CucumberTestSuite.java #useful if running tests from an IDE
└── steps #step definitions for our feature files
    └── UserSteps.java
```

## Run the tests

```
mvn clean verify
```

## Resources

Hopefully, we'll be adding a Youtube demo and Blog tutorial soon.
