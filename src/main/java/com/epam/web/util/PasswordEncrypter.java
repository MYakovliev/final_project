package com.epam.web.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Optional;

/**
 * util class to encrypt string (password)
 */
public class PasswordEncrypter {
    private final static Logger logger = LogManager.getLogger();
    private static final String ALGORITHM = "SHA-1";

    public static Optional<String> encrypt(String password){
        Optional<String> encryptedPassword = Optional.empty();
        try {
            MessageDigest messageDigest = MessageDigest.getInstance(ALGORITHM);
            byte[] bytePassword = password.getBytes();
            byte[] encryptedBytePassword = messageDigest.digest(bytePassword);
            encryptedPassword = Optional.of(new String(encryptedBytePassword));
        } catch (NoSuchAlgorithmException e) {
            logger.error(e);
        }
        return encryptedPassword;
    }
}
