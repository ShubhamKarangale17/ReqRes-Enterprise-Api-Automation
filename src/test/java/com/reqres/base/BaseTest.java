package com.reqres.base;

import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeClass;
import com.reqres.config.ConfigReader;
import com.reqres.utils.RequestSpecificationUtil;

/**
 * BaseTest Class - Initializes RestAssured configuration and common setup
 * 
 * This class provides:
 * - RestAssured base URI initialization
 * - RequestSpecification setup
 * - Global test configuration
 */
public class BaseTest {

    protected RequestSpecification requestSpec;

    /**
     * Setup method executed before each test class
     * Initializes RestAssured base URI and request specification
     */
    @BeforeClass
    public void setUp() {
        // Initialize base URI from configuration
        String baseURI = ConfigReader.getBaseURI();
        RestAssured.baseURI = baseURI;

        // Initialize request specification with logging and common headers
        this.requestSpec = RequestSpecificationUtil.getRequestSpecification();
    }
}
