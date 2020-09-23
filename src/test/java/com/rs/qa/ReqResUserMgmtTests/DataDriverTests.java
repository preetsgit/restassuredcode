package com.rs.qa.ReqResUserMgmtTests;

import org.json.simple.JSONObject;
import org.junit.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.restassured.http.ContentType;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

public class DataDriverTests {

    @DataProvider(name = "ddd")
    public Object[][] dataForPost()
    {
        return new Object[][]{
            {"ti2","au2",46},{"ti3","au3",47}};
    }

@Test(dataProvider = "ddd")
 public void TestDataDrivenPost(String title,String author,int id)
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

 @Test
 public void TestBDDP()
 {
     JSONObject json = new JSONObject();
     json.put("id", 20);
     json.put("title", "myt");
     json.put("author","me");     
     
     baseURI = "http://localhost:3000/";
     String responsestring = given().
        header("Content-Type","application/json").
        contentType(ContentType.JSON).
        accept(ContentType.JSON).
        body(json.toJSONString()).
    when().
          post("/posts").
    then().
          statusCode(500).
          statusLine("HTTP/1.1 500 Internal Server Error").
          log().all().
    extract().
          response().body().asString();
        
    Assert.assertTrue(responsestring.contains("Insert failed"));
          //   then().
        //   statusCode(201).
        //   body("author",equalTo("me")).log().all();                
 }

 @DataProvider(name="test")
 public Object[][] MyDdd()
 {
     Object[][] d = new Object[2][2];
     d[0][0] = "ti1";
     d[0][1] = "au1";
     d[1][0] = "ti2";
     d[1][1] = "au2";
     return d;
 }

 @Test(dataProvider = "test")
 public void TestDataPor(String title,String author)
 {
    System.out.println(title);
    System.out.println(author);
 }
}
