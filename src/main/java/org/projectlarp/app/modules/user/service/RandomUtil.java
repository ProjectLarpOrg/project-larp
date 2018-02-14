package org.projectlarp.app.modules.user.service;

import org.apache.commons.lang3.RandomStringUtils;

/**
 * Utility class for generating random Strings.
 */
public final class RandomUtil {

    private static final int DEF_COUNT = 20;

    private RandomUtil() {
    }

    public static String generatePassword() {
        return RandomStringUtils.randomAlphanumeric(DEF_COUNT);
    }

    public static String generateActivationKey() {
        return RandomStringUtils.randomNumeric(DEF_COUNT);
    }
    
    public static String generateAccessToken() {
        return RandomStringUtils.randomNumeric(DEF_COUNT);
    }
    
    public static String generateResetKey() {
        return RandomStringUtils.randomNumeric(DEF_COUNT);
    }
}
