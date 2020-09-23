package com.rs.qa.ReqResUserMgmtTests;

import org.json.simple.JSONObject;
import org.junit.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.restassured.http.ContentType;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

public class ReqResUserPostTests extends DataDriverTests {
    
@Test(dataProvider = "ddd")
public void TestDataDrivenInherti(String title,String author,int id)
{
    JSONObject json = new JSONObject();
    json.put("id", id);
    json.put("title", title);
    json.put("author",author);
   
    baseURI = "http://localhost:3000/";
    given().
       header("Content-Type","application/json").
       contentType(ContentType.JSON).
       accept(ContentType.JSON).
       body(json.toJSONString()).
   when().
         post("/posts").
   then().
         statusCode(201).          
         log().all().
   extract().
         response().body().asString();
       
   //Assert.assertTrue(responsestring.contains("Insert failed"));
}
    
}
