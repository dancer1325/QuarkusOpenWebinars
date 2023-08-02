package org.openwebinars.course.gettingStarted;

import io.quarkus.test.common.QuarkusTestResource;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
@QuarkusTestResource(HelloQuarkusTestResourceLifecycleManager.class)
public class HelloTest {

    @Test
    public void testHelloEndpoint() {
        given()
                .when().get("/hello")
                .then()
                .statusCode(200)
                // Test to return has been replaced because, we are using QuarkusTestResource
                //.body(is("Hello from RESTEasy Reactive"));
                //.body(is("HELLO WORLD"));
                .body(is("TESTING HELLO"));
    }

    @Test
    public void testCalculate() {
        given()
                .when().get("/hello/calculate")
                .then()
                .statusCode(200)
                .body(is("5"));
    }

}