package EncryptionDecryption;

import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class Decryption {
    private final SecretKey key;
    private final String stringKey = "mnwrpanorama2024";

    public Decryption() {
        this.key = new SecretKeySpec(stringKey.getBytes(),"AES");
    }
    public String decrypt(String password) throws DecryptionException {
        try {
            Cipher encryptionCipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            encryptionCipher.init(Cipher.DECRYPT_MODE, key);
            return new String(encryptionCipher.doFinal(decode(password)));
        } catch (NoSuchPaddingException e) {
            throw new DecryptionException("no such padding " + e);
        } catch (IllegalBlockSizeException e) {
            throw new DecryptionException("Illegal block size " + e);
        } catch (NoSuchAlgorithmException e) {
            throw new DecryptionException("No such algorithm " + e);
        } catch (BadPaddingException e) {
            throw new DecryptionException("Bad Padding " + e);
        } catch (InvalidKeyException e) {
            throw new DecryptionException("Invalid Key " + e);
        }
    }
    private byte[] decode(String data) {
        return Base64.getDecoder().decode(data);
    }
}
