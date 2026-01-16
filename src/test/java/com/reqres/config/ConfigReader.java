package com.reqres.config;

/**
 * ConfigReader Class - Manages application configuration properties
 * 
 * This class provides centralized access to configuration values
 * such as base URI, timeouts, and other API endpoints
 */
public class ConfigReader {

    // API Base Configuration
    // Using httpbin.org for initial testing (no Cloudflare protection)
    // Change back to https://reqres.in after fixing Cloudflare issue
    private static final String BASE_URI = "https://httpbin.org";
    private static final String API_VERSION = "";
    private static final String API_ENDPOINT = BASE_URI + API_VERSION;
    
    // Request Configuration
    private static final int CONNECTION_TIMEOUT = 15000; // 15 seconds
    private static final int READ_TIMEOUT = 15000; // 15 seconds
    private static final int RESPONSE_TIMEOUT = 15000; // 15 seconds

    /**
     * Get the base URI for API requests
     * 
     * @return Base URI string
     */
    public static String getBaseURI() {
        return BASE_URI;
    }

    /**
     * Get the full API endpoint with version
     * 
     * @return Full API endpoint
     */
    public static String getAPIEndpoint() {
        return API_ENDPOINT;
    }

    /**
     * Get connection timeout in milliseconds
     * 
     * @return Connection timeout value
     */
    public static int getConnectionTimeout() {
        return CONNECTION_TIMEOUT;
    }

    /**
     * Get read timeout in milliseconds
     * 
     * @return Read timeout value
     */
    public static int getReadTimeout() {
        return READ_TIMEOUT;
    }

    /**
     * Get response timeout in milliseconds
     * 
     * @return Response timeout value
     */
    public static int getResponseTimeout() {
        return RESPONSE_TIMEOUT;
    }
}
