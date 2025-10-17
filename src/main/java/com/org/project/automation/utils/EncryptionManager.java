package com.org.project.automation.utils;

import org.apache.commons.net.util.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;

/**
 * This class allows user to encrypt and decrypt sensitive information
 */
public class EncryptionManager {
    private static final Logger LOGGER = LoggerFactory.getLogger(EncryptionManager.class);
    private static final String UNICODE_FORMAT = "UTF8";
    private static final String DESEDE_ENCRYPTION_SCHEME = "DESede";
    private static KeySpec ks;
    private static SecretKeyFactory skf;
    private static Cipher cipher;
    private static byte[] arrayBytes;
    private static SecretKey key;

    static {
        String myEncryptionKey = "ThisIsSpartaThisIsSparta";
        String myEncryptionScheme = DESEDE_ENCRYPTION_SCHEME;
        try {
            arrayBytes = myEncryptionKey.getBytes(UNICODE_FORMAT);
        } catch (UnsupportedEncodingException e) {
            LOGGER.info(e.getMessage());
        }
        try {
            ks = new DESedeKeySpec(arrayBytes);
        } catch (InvalidKeyException e) {
            LOGGER.info(e.getMessage());
        }
        try {
            skf = SecretKeyFactory.getInstance(myEncryptionScheme);
        } catch (NoSuchAlgorithmException e) {
            LOGGER.info(e.getMessage());
        }
        try {
            cipher = Cipher.getInstance(myEncryptionScheme);
        } catch (NoSuchAlgorithmException | NoSuchPaddingException e) {
            LOGGER.info(e.getMessage());
        }
        try {
            key = skf.generateSecret(ks);
        } catch (InvalidKeySpecException e) {
            LOGGER.info(e.getMessage());
        }
    }

    /**
     * Method to encrypt given value
     *
     * @param unEncryptedString
     * @return encryptedString
     */
    public static String encrypt(String unEncryptedString) {
        String encryptedString = null;
        try {
            cipher.init(Cipher.ENCRYPT_MODE, key);
            byte[] plainText = unEncryptedString.getBytes(UNICODE_FORMAT);
            byte[] encryptedText = cipher.doFinal(plainText);
            encryptedString = new String(Base64.encodeBase64(encryptedText));
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }
        return encryptedString;
    }

    /**
     * Method to decrypt given value
     *
     * @param encryptedString
     * @return decryptedString
     */
    public static String decrypt(String encryptedString) {
        String decryptedString = null;
        try {
            cipher.init(Cipher.DECRYPT_MODE, key);
            byte[] encryptedText = Base64.decodeBase64(encryptedString);
            byte[] plainText = cipher.doFinal(encryptedText);
            decryptedString = new String(plainText);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }
        return decryptedString;
    }

    public static void main(String[] args) {
        String a=new EncryptionManager().encrypt("admin");
        System.out.println("a: "+a);
        String b=new EncryptionManager().decrypt(a);
        System.out.println("b: "+b);
    }

}
