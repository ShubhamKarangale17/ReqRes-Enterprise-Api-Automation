package com.reqres.tests;

import io.restassured.response.Response;
import org.testng.annotations.Test;
import org.apache.logging.log4j.Logger;

import com.reqres.api.UserAPI;
import com.reqres.base.BaseTest;
import com.reqres.utils.LoggerUtil;

import static org.testng.Assert.*;

/**
 * DeleteUserAPITest
 *
 * ReqRes DELETE behavior:
 * - Mock API
 * - No persistence
 * - Success indicated by any 2xx status code
 */
public class DeleteUserAPITest extends BaseTest {

    private static final Logger logger =
            LoggerUtil.getLogger(DeleteUserAPITest.class);

    @Test(description = "Delete user - success contract validation")
    public void deleteUser() {
        int userId = 10;

        Response response = UserAPI.deleteUser(requestSpec, userId);

        logger.info("DELETE user {} -> {}", userId, response.getStatusCode());

        // ReqRes success = any 2xx
        assertTrue(response.getStatusCode() < 300,
                "DELETE should return a successful status code (<300)");
    }

    @Test(description = "Delete non-existing user - idempotent behavior")
    public void deleteNonExistingUser() {
        int userId = 9999;

        Response response = UserAPI.deleteUser(requestSpec, userId);

        logger.info("DELETE non-existing user {} -> {}", userId, response.getStatusCode());

        assertTrue(response.getStatusCode() < 300,
                "DELETE should be idempotent and return success");
    }
}

