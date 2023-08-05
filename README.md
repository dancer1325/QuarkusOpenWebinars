# getting-started

This project uses Quarkus, the Supersonic Subatomic Java Framework.

If you want to learn more about Quarkus, please visit its website: https://quarkus.io/ .

## How was it created?
* `mvn io.quarkus:quarkus-maven-plugin:3.2.2.Final:create -DprojectGroupId=org.openwebinars.course -DprojectArtifactId=getting-started -DclassName="org.openwebinars.course.gettingStarted.Hello" -Dpath="/hello"`


## Running the application in dev mode

You can run your application in dev mode that enables live coding!! using:
```shell script
./mvnw compile quarkus:dev
```
* `./mvnw` because we use maven wrapper
* `dev` comes from [quarkus-maven-plugin](https://github.com/quarkusio/quarkus-platform/blob/main/generated-platform-project/quarkus-maven-plugin/src/main/java/io/quarkus/maven/DevMojo.java#L121)
* Test your application running fine
  * `curl localhost:8080/hello`
  * `curl localhost:8080/hello/beer`
  * `curl -d '{"name":"Alfredo2", "capacity":5000}' -H "Content-Type: application/json" -X POST localhost:8080/hello`
  * `curl -d '{"name":"Alfredo2", "capacity":50}' -H "Content-Type: application/json" -X POST localhost:8080/hello -v`
    * "-v" shows more detailed information
  * ` curl -d '{"name":"Alfredo2", "capacity":150, "expirationDate":"2020-02-11"}' -H "Content-Type: application/json" -X POST localhost:8080/hello -v`
  * `curl localhost:8080/hello/currentDateTime`
  * `curl localhost:8080/hello/currentDateTimeWithHeaders -v`
  * `curl localhost:8080/hello/currentDateTimeWithJAXRS -v`
  * `curl -d '{"name":"Alfredo2", "id":50}' -H "Content-Type: application/json" -X POST localhost:8080/hello/developer -v`
    * Persist data in the MariaDB via JPA
  * `curl -d -H "Content-Type: application/json" -X GET localhost:8080/hello/developer/50 -v`
    * Get concrete persisted data in the MariaDB via JPA
  * `curl -d '{"name":"Rosi"}' -H "Content-Type: application/json" -X POST localhost:8080/hello/developerWithPanache -v`
    * Persist data in the MariaDB via Panache
  * `curl -d -H "Content-Type: application/json" -X GET localhost:8080/hello/developerWithPanache/1 -v`
    * Get concrete persisted data in the MariaDB via Panache
  * `curl -d '{"name":"Judith", "age":26}' -H "Content-Type: application/json" -X POST localhost:8080/hello/developerWithPanache -v`
    * Persist data in the MariaDB via Panache adding a new column with data already persisted
  * `curl -d -H "Content-Type: application/json" -X GET localhost:8080/hello/developerWithPanache/all -v`
    * Get all persisted data with Panache
  * `curl -d -H "Content-Type: application/json" -X GET localhost:8080/hello/developerWithPanache/allAsEntityBase -v`
    * Get all persisted data with Panache
  * `curl -d -H "Content-Type: application/json" -X GET localhost:8080/hello/developerWithPanache/name/Rosi -v`
  * `curl -d -H "Content-Type: application/json" -X GET localhost:8080/hello/developerWithPanache/nameJQL/Rosi -v`
  * `curl -d -H "Content-Type: application/json" -X GET localhost:8080/hello/developerWithPanache/name/Judith/age/26 -v`
  * `curl -d -H "Content-Type: application/json" -X GET localhost:8080/hello/developerWithPanacheViaDAO/name/Rosi -v`
  * `curl -d '{"name":"Paloma", "age":26, "id":22}' -H "Content-Type: application/json" -X POST localhost:8080/hello/developerWithPanacheViaDAO -v`
  * `curl -d -H "Content-Type: application/json" -X GET localhost:8080/hello/async -v`

> **_NOTE:_**  Quarkus now ships with a Dev UI, which is available in dev mode only at http://localhost:8080/q/dev/.

## Run with a custom profile
`./mvnw compile quarkus:dev -Dquarkus.profile=NameOfTheProfile`

## Packaging and running the application in JDK mode

The application can be packaged using:
```shell script
./mvnw package
```
It produces the `quarkus-run.jar` file in the `target/quarkus-app/` directory.
Be aware that it’s not an _über-jar_ as the dependencies are copied into the `target/quarkus-app/lib/` directory.

The application is now runnable using `java -jar target/quarkus-app/quarkus-run.jar`.

If you want to build an _über-jar_, execute the following command:
```shell script
./mvnw package -Dquarkus.package.type=uber-jar
```

The application, packaged as an _über-jar_, is now runnable using `java -jar target/*-runner.jar`.

## Creating a native executable

You can create a native executable using: 
```shell script
./mvnw package -Pnative
```

Or, if you don't have GraalVM installed, you can run the native executable build in a container (-> You need to run your Docker daemon previously) using: 
```shell script
./mvnw package -Pnative -Dquarkus.native.container-build=true
```
If it's stuck in the first build step -> restart it the process

If you want to learn more about building native executables, please consult https://quarkus.io/guides/maven-tooling.

### Run the native executable
After generating the previous packages, run:

`docker build -f src/main/docker/Dockerfile.native -t quarkus/getting-started .`

`docker run -i --rm -p 8080:8080 quarkus/getting-started`

Now you can test that it's working `curl localhost:8080/hello` 


## Provided Code

### RESTEasy Reactive

Easily start your Reactive RESTful Web Services

[Related guide section...](https://quarkus.io/guides/getting-started-reactive#reactive-jax-rs-resources)

## Persistence
### JPA
### Panache
* Via 
  * Repository Pattern
  * DAO pattern

## Notes
* 'mvnw' is the maven wrapper
  * `./mvnw quarkus:list-extensions`
    * Check all the supported quarkus extensions
  * `./mvnw quarkus:add-extension -Dextensions="quarkus-resteasy-jsonb"`
    * Add a quarkus extension
  * `./mvnw quarkus:add-extension -Dextensions="quarkus-hibernate-validator"`
    * Add a quarkus extension
  * `./mvnw quarkus:add-extension -Dextensions="quarkus-rest-client"`
    * Add a quarkus extension
  * `./mvnw test`
    * Run tests
  * `./mvnw verify -Pnative`
    * Run integration tests.
    * Previous requirements
      * Install GraalVM locally
  * `./mvnw quarkus:add-extension -Dextensions="quarkus-hibernate-orm, quarkus-jdbc-mariadb"`
  * `./mvnw quarkus:add-extension -Dextensions="quarkus-hibernate-orm-panache"`
  *  `./mvnw quarkus:add-extension -Dextensions="quarkus-flyway"`
* Download a [mariaDB image from dockerHub](https://hub.docker.com/_/mariadb)
  * `docker run --detach --name some-mariadb --env MARIADB_USER=example-user --env MARIADB_PASSWORD=my_cool_secret --env MARIADB_ROOT_PASSWORD=my-secret-pw --env MARIADB_DATABASE=some-mariadb -p 3306:3306  mariadb:latest`

  