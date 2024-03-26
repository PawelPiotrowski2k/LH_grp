package EncryptionDecryption;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;

public class SaltEncrypting {
    byte[] salt;
    public SaltEncrypting() {
        salt = generateSalt();
    }


    public  String encrypt(String password) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");

            byte[] saltedText = addSalt(password.getBytes(), salt);

            byte[] hashBytes = digest.digest(saltedText);

            String hashedText = Base64.getEncoder().encodeToString(hashBytes);

            return hashedText;

        } catch (NoSuchAlgorithmException e) {
            System.err.println("Nie można znaleźć algorytmu haszującego: " + e.getMessage());
            return null;
        }
    }
    public  String encrypt(String password, byte[] salter) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");

            byte[] saltedText = addSalt(password.getBytes(), salter);

            byte[] hashBytes = digest.digest(saltedText);

            String hashedText = Base64.getEncoder().encodeToString(hashBytes);

            return hashedText;

        } catch (NoSuchAlgorithmException e) {
            System.err.println("Nie można znaleźć algorytmu haszującego: " + e.getMessage());
            return null;
        }
    }

        private static byte[] generateSalt () {
            byte[] salt = new byte[16];
            SecureRandom random = new SecureRandom();
            random.nextBytes(salt);
            return salt;
        }
        public String getSalt(){
            return Base64.getEncoder().encodeToString(salt);
        }
        private static byte[] addSalt ( byte[] textBytes, byte[] salt){
            byte[] saltedText = new byte[textBytes.length + salt.length];
            System.arraycopy(textBytes, 0, saltedText, 0, textBytes.length);
            System.arraycopy(salt, 0, saltedText, textBytes.length, salt.length);
            return saltedText;
        }
    }


