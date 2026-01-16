package com.reqres.utils;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.specification.RequestSpecification;
import com.reqres.config.ConfigReader;

/**
 * RequestSpecificationUtil Class - Utility for building request specifications
 * 
 * This utility class provides:
 * - Request specification builder with logging
 * - Response logging configuration
 * - Common request headers setup
 */
public class RequestSpecificationUtil {

    /**
     * Build and return a RequestSpecification with logging enabled
     * This specification includes:
     * - Request and response logging
     * - Content-Type headers
     * 
     * @return Configured RequestSpecification object
     */
    public static RequestSpecification getRequestSpecification() {
        return new RequestSpecBuilder()
                // Add request logging filter
                .addFilter(new RequestLoggingFilter())
                // Add response logging filter
                .addFilter(new ResponseLoggingFilter())
                // Set base path (empty for httpbin.org)
                .setBasePath("")
                // Set request headers
                .addHeader("Content-Type", "application/json")
                .addHeader("Accept", "application/json")
                // Build and return the specification
                .build();
    }

    /**
     * Get a custom RequestSpecification with additional headers
     * 
     * @param authToken Optional authentication token
     * @return Configured RequestSpecification with additional headers
     */
    public static RequestSpecification getRequestSpecificationWithAuth(String authToken) {
        RequestSpecBuilder specBuilder = new RequestSpecBuilder()
                .addFilter(new RequestLoggingFilter())
                .addFilter(new ResponseLoggingFilter())
                .setBasePath("")
                .addHeader("Content-Type", "application/json")
                .addHeader("Accept", "application/json");

        // Add authorization header if token is provided
        if (authToken != null && !authToken.isEmpty()) {
            specBuilder.addHeader("Authorization", "Bearer " + authToken);
        }

        return specBuilder.build();
    }
}
