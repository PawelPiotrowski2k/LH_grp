package AES.decryption;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;
import javax.crypto.*;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class KeyDecrypting {
    private SecretKey key;
    private String appName;
    private byte[] passwordInBytes;
    private byte[] IV;
    private final int T_LEN = 128;
    private final String pathToHiddenFile = "C:\\Users\\admin\\.passwords";

    public KeyDecrypting(String appName) throws KeyDecryptingException {
        this.appName = appName;
        readFieldFromFile();
    }

    public String decrypt() throws KeyDecryptingException {
        try {
            Cipher decryptionCipher = Cipher.getInstance("AES/GCM/NoPadding");
            GCMParameterSpec spec = new GCMParameterSpec(T_LEN, IV);
            decryptionCipher.init(Cipher.DECRYPT_MODE, key, spec);
            byte[] decryptedPassword = decryptionCipher.doFinal(passwordInBytes);
            return new String(decryptedPassword);
        } catch (InvalidAlgorithmParameterException e) {
            throw new KeyDecryptingException("invalid algorithm");
        } catch (NoSuchPaddingException e) {
            throw new KeyDecryptingException("no such padding");
        } catch (IllegalBlockSizeException e) {
            throw new KeyDecryptingException("illegal block size");
        } catch (NoSuchAlgorithmException e) {
            throw new KeyDecryptingException("no such algorithm");
        } catch (BadPaddingException e) {
            throw new KeyDecryptingException("bad padding");
        } catch (InvalidKeyException e) {
            throw new KeyDecryptingException("invalid key");
        }
    }
    private void readFieldFromFile() throws KeyDecryptingException {
        try {
            JSONArray jsonArray;
            try (FileReader reader = new FileReader(pathToHiddenFile)) {
                jsonArray = new JSONArray(new JSONTokener(reader));
            }
            for (Object obj : jsonArray) {
                JSONObject jsonObject = (JSONObject) obj;
                if (jsonObject.get("AppName").equals(appName)) {
                    String pswd = (String) jsonObject.get("password");
                    this.passwordInBytes = decode(pswd);
                    String secretkey = (String) jsonObject.get("key");
                    this.key = new SecretKeySpec(decode(secretkey), "AES");
                    this.IV = decode((String) jsonObject.get("IV"));
                }
            }
        } catch (FileNotFoundException e) {
            throw new KeyDecryptingException("file not found");
        } catch (IOException e) {
            throw new KeyDecryptingException("there is a problem with opening file");
        }
    }

    private byte[] decode(String data) {
        return Base64.getDecoder().decode(data);
    }
}