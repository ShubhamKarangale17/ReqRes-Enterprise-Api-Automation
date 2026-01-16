package com.reqres.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * LoggerUtil Class - Centralized logging utility
 * 
 * Provides centralized logging functionality for all test classes
 */
public class LoggerUtil {

    /**
     * Get logger instance for a specific class
     * 
     * @param className Class for which to get the logger
     * @return Logger instance
     */
    public static Logger getLogger(Class<?> className) {
        return LogManager.getLogger(className);
    }

    /**
     * Log informational message
     * 
     * @param logger Logger instance
     * @param message Message to log
     */
    public static void logInfo(Logger logger, String message) {
        logger.info(message);
    }

    /**
     * Log error message
     * 
     * @param logger Logger instance
     * @param message Error message
     */
    public static void logError(Logger logger, String message) {
        logger.error(message);
    }

    /**
     * Log debug message
     * 
     * @param logger Logger instance
     * @param message Debug message
     */
    public static void logDebug(Logger logger, String message) {
        logger.debug(message);
    }

    /**
     * Log warning message
     * 
     * @param logger Logger instance
     * @param message Warning message
     */
    public static void logWarning(Logger logger, String message) {
        logger.warn(message);
    }
}
