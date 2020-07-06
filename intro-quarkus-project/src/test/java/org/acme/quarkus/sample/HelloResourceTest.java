package org.acme.quarkus.sample;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
public class HelloResourceTest {

    

    /**
     *  // rest-assured  ==> BDD ( Behav Driven Developemet ) style
     * 
     *   BDD style => given,when,then
     * 
     * 
     * 
     * 
     * 
     */

    @Test
    public void testHelloEndpoint() {
        given()
          .when().get("/hello")
          .then()
             .statusCode(200)
             .body(is("hello"));
    }

    @Test
    public void testHelloWithNameEndpoint() {
        given()
          .pathParam("name", "ibm")
          .when().get("/hello/{name}")
          .then()
             .statusCode(200)
             .body(is("hello ibm"));
    }

}