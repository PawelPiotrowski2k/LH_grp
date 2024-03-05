package EncryptionDecryption;

import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class Encryption {
    private final SecretKey key;
    private final String stringKey = "mnwrpanorama2024";
    private Cipher encryptionCipher;



    public Encryption() {
        key = new SecretKeySpec(stringKey.getBytes(),"AES");
    }

    public String encrypt(String password) throws EncryptionException {
        try {
            byte[] passwordInBytes = password.getBytes();
            encryptionCipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            encryptionCipher.init(Cipher.ENCRYPT_MODE, key);
            byte[] encryptedBytes = encryptionCipher.doFinal(passwordInBytes);
            return encode(encryptedBytes);
        }catch (NoSuchAlgorithmException e){
            throw new EncryptionException("no such algorythm" + e);
        }catch (InvalidKeyException e){
            throw new EncryptionException("invalid key" + e);
        }catch (IllegalBlockSizeException e){
            throw new EncryptionException("illegal block size" + e);
        }catch (BadPaddingException e){
            throw new EncryptionException("bad padding" + e);
        }catch (NoSuchPaddingException e){
            throw new EncryptionException("no such padding exception" + e);
        }
    }


    private String encode(byte[] data) {
        return Base64.getEncoder().encodeToString(data);
    }
}
