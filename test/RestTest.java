/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import static com.jayway.restassured.RestAssured.*;
import static com.jayway.restassured.matcher.RestAssuredMatchers.*;
import com.jayway.restassured.parsing.Parser;
import static org.hamcrest.Matchers.*;

/**
 *
 * @author martamiszczyk
 */
public class RestTest
{

    public RestTest()
    {
    }

    @BeforeClass
    public static void setUpBeforeClass() throws Exception
    {
        baseURI = "http://localhost:8080";
        defaultParser = Parser.JSON;
        basePath = "/ExamPrep/api/";
    }
    @Test
    public void testGetAllProjects()
    { 
        when()
            .get("/projects")
        .then().
            statusCode(200).
            body("ID", equalTo("1"));
    }

}
