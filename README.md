# Application_Autoservice_Spring

Autoservice application in `Java` `Spring`

>This web application is created for managing autoservices' orders. There are three groups of users, each with different permissions and available actions. Clients are able to add cars, check theirs' cars orders, and create new order requests. Autoservice manager is able to accept or reject order requests, add jobs to orders and assign employes to them, mark orders as completed. Employees are able to accept jobs add car parts to them and finish them

## Source code contains
1. [Spring boot](https://spring.io/projects/spring-boot/)
2. [Thymeleaf](https://www.thymeleaf.org/)
3. [Java™ database connectivity (JDBC)](https://docs.oracle.com/javase/8/docs/technotes/guides/jdbc/)

   *[MsSQl Server](https://www.microsoft.com/en-us/sql-server/)
5. [Hibernate](https://hibernate.org/)

## Start the application

### Standalone

At first, you need to have up and running [MsSQL](https://www.microsoft.com/sql-server/sql-server-downloads) database server on localhost with initialized
database by [mssql script](service%20station/db/mssql/database.sql).

Then the application can be started by using IDE. By default it will be available under http://localhost:8080/.

### Docker (recommended)

Just run `docker-compose up` command in the root directory and after successful start of services visit http://localhost:8080/.

## Config

### Backend
```shell
localhost:8080
```
### Database
```shell
localhost:1433
```
## Usage

After opening http://localhost:8080/ you could log in as one of three users credential (Case sensetive), which represents three groups of users:
1. Client (Log:Pas)
2. Manager (Paswe:Loig)
3. Employee (paos:adsdfe)

After successful authorization you can test fuctionality of the app, available to the chosen user group 


