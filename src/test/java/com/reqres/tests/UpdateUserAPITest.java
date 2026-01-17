package com.reqres.tests;

import io.restassured.response.Response;
import org.testng.annotations.Test;
import org.apache.logging.log4j.Logger;

import com.reqres.api.UserAPI;
import com.reqres.base.BaseTest;
import com.reqres.utils.LoggerUtil;

import static org.testng.Assert.*;

/**
 * UpdateUserAPITest
 *
 * ReqRes UPDATE behavior:
 * - Mock API
 * - Response fields are NOT guaranteed
 * - Only contract: HTTP 200 + valid response
 */
public class UpdateUserAPITest extends BaseTest {

    private static final Logger logger =
            LoggerUtil.getLogger(UpdateUserAPITest.class);

    @Test(description = "Update user with PUT - contract validation only")
    public void updateUserWithPUT() {
        Response response =
                UserAPI.updateUser(requestSpec, 2, "Test User", "Engineer");

        logger.info("PUT response -> {}", response.asString());

        assertEquals(response.getStatusCode(), 200);
        assertFalse(response.asString().isEmpty(),
                "Response body should not be empty");
    }

    @Test(description = "Update user with PATCH - contract validation only")
    public void updateUserWithPATCH() {
        Response response =
                UserAPI.partialUpdateUser(requestSpec, 2, "Patch User", "Lead");

        logger.info("PATCH response -> {}", response.asString());

        assertEquals(response.getStatusCode(), 200);
        assertFalse(response.asString().isEmpty());
    }

    @Test(description = "Update with special characters")
    public void updateWithSpecialCharacters() {
        Response response =
                UserAPI.updateUser(requestSpec, 4, "O'Connor & Sons", "Manager");

        logger.info("Special char update -> {}", response.asString());

        assertEquals(response.getStatusCode(), 200);
    }

    @Test(description = "Multiple consecutive updates")
    public void multipleUpdates() {
        String[][] updates = {
                {"First", "Dev"},
                {"Second", "Manager"},
                {"Third", "Lead"}
        };

        for (String[] update : updates) {
            Response response =
                    UserAPI.updateUser(requestSpec, 6, update[0], update[1]);

            logger.info("Sequential update -> {}", response.asString());

            assertEquals(response.getStatusCode(), 200);
        }
    }
}

