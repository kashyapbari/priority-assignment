# priority-assignment
Tatsam Hiring Software Engineer - Java Spring Assignment
---------------------------------------------------
## Table of contents
* [Desciption](https://github.com/kashyapbari/priority-assignment#description)
* [Tech stack](https://github.com/kashyapbari/priority-assignment#tech-stack)
* [End Points](https://github.com/kashyapbari/priority-assignment#end-points)
* [Development](https://github.com/kashyapbari/priority-assignment#development)

---------------------------------------------------
## Desciption
Write a spring boot application with Priority as the central entity.
The purpose of this application is to allow users to define a priority
order to various areas at the same time specifying the level of satisfaction

### User Requirements:
- As a user I want to assign a priority order to various areas
- As a user I want to assign a satisfaction level to the same areas
- As a user I want to know all the areas available
- As a user I want to fetch all the users and their priority orders
- As an admin I want to add new areas in future

---------------------------------------------------
### Tech stack:
##### 1. JDK 11
##### 2. Spring Boot
##### 3. h2database

---------------------------------------------------
### End Points:
#### 1.Get: all users and their priorities
fetches a paginated list of users and their priority order with satisfaction level

#### 2.Get: all available areas
fetches a paginated list of available areas

#### 1.Post: save user priority order and their satisfaction level
saves a priority order of areas for an individual user along with satisfaction level for each area

-------------------------------------------------------
### Development:

#### Using:
  - in memory based Db(data is lost when application is terminated)
  - initial DB data is loaded on start up by bootstrap loaders

#### Usecase:
  - Saving priority and satisfaction level for user
  - Updating priority and or satisfaction of an user
  - Satisfaction level can be only 1 - 5
  - User can not assign same priority for different areas
  - User can only assign one priority and satisfaction level per area

#### Endpoints with examples are in postman folder
