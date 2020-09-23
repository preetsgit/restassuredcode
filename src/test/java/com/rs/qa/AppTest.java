package com.rs.qa;

import org.json.simple.JSONObject;
import org.junit.Assert;
import org.testng.annotations.Test;

import io.restassured.http.ContentType;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import javax.swing.text.AbstractDocument.Content;

public class AppTest {

   final static String url="https://reqres.in/api/users?page=2";
   final static String getuserwithidurl="https://reqres.in/api/users?id=3";
   final static String posturl="http://localhost:3000/posts";

 @Test
 public void TestBDD()
 {
     given().when().get(url).then().statusCode(200).body("data.id[1]",equalTo(8));
 }

 @Test
 public void TestBDDPost()
 {
     JSONObject json = new JSONObject();
     json.put("id", 20);
     json.put("title", "myt");
     json.put("author","me");

     String s = json.toJSONString();
     
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

 @Test()
 public void TestBDDDelete()
 {

    baseURI = "http://localhost:3000";

    given().
        header("Content-Type","application/json").
        contentType(ContentType.JSON).
        accept(ContentType.JSON).
    when().
        delete("/posts/21").
    then().
        statusCode(204);
 }

 @Test
 public void TestBDDPut()
 {
    baseURI = "http://localhost:3000";

    JSONObject jsonput = new JSONObject();    
    jsonput.put("title","preetitle");
    jsonput.put("author","preeauthor");

    given().
        header("Content-Type","application/json").
        contentType(ContentType.JSON).
        accept(ContentType.JSON).
        body(jsonput.toJSONString()).
    when().
        put("/posts/21").
    then().
        statusCode(200).
        body("title",equalTo("preetitle")); 
 }
}


