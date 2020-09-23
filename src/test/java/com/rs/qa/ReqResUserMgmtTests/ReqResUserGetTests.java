package com.rs.qa.ReqResUserMgmtTests;

import org.json.simple.JSONObject;
import org.junit.Assert;
import org.testng.annotations.Test;


import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

public class ReqResUserGetTests {

    final static String url="https://reqres.in/api/users?page=2";
    final static String getuserwithidurl="https://reqres.in/api/users?id=3";
    final static String posturl="https://reqres.in/api/users";

    @Test
    public void GetUserWithIdAndValidate()
    {
        int id = given().when().get(getuserwithidurl).then().statusCode(200).body("data.first_name",equalTo("Emma")).extract().path("data.id");
    }
    
}
