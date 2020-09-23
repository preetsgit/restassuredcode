package com.rs.qa.ReqResUserMgmtTests;
import org.json.simple.JSONObject;
import org.testng.annotations.Test;


import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;
public class ReqResUserPutTests {

    final static String url="https://reqres.in/api/users?page=2";
    final static String getuserwithidurl="https://reqres.in/api/users/%s";
    final static String posturl="https://reqres.in/api/users";

    @Test
    public void GetUserAndUpdateFirstName()
    {
        JSONObject json = new JSONObject();
        json.put("first_name","EmmaUpdate");
        json.put("email","EmmaUpdate@gmail.com");

        int id = given().when().get(getuserwithidurl).then().statusCode(200).body("data.first_name",equalTo("Emma")).extract().path("data.id");

        String url = String.format(getuserwithidurl, id);
    }
    
}
