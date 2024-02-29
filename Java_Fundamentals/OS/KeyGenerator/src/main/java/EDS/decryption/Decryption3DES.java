package EDS.decryption;

import EDS.Encryption.Encryption3DESException;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;


public class Decryption3DES {
    private final String UNICODE_FORMAT = "UTF8";
    private final String DESDE_ENCRYPTION_SCHEME = "DESede";
    private final String pathToHiddenFile = "C:\\Users\\Pawel\\.passwords";
    private final String appName;
    private byte[] passwordInBytes;
    private Cipher cipher;
    private byte[] IV;
    private final byte[] arrayBytes;
    private String myDecryptionKey;
    private SecretKey key;

    public Decryption3DES(String appName) throws Decryption3DESException, UnsupportedEncodingException {
        this.myDecryptionKey = "randomWord";
        arrayBytes = myDecryptionKey.getBytes(UNICODE_FORMAT);
        this.appName = appName;
        readFieldFromFile();
    }

    public static void main(String[] args) throws UnsupportedEncodingException, Decryption3DESException, Encryption3DESException {
        Decryption3DES decryption3DES = new Decryption3DES("spotify");
        decryption3DES.readFieldFromFile();
        System.out.println(decryption3DES.decrypt());
    }

    public String decrypt() throws Encryption3DESException {
        String decryptedText = null;
        try {
            cipher = Cipher.getInstance("DESede/CBC/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE,key,new IvParameterSpec(IV));
            byte[] encryptedText = passwordInBytes;
            byte[] plainText = cipher.doFinal(encryptedText);
            decryptedText = new String(plainText);
        } catch (IllegalBlockSizeException e) {
            throw new Encryption3DESException("Illegal block size" + e);
        } catch (BadPaddingException e) {
            throw new Encryption3DESException("bad padding" + e);
        } catch (InvalidKeyException e) {
            throw new Encryption3DESException("invalid key" + e);
        } catch (NoSuchPaddingException e) {
            throw new Encryption3DESException("no such padding" + e);
        } catch (NoSuchAlgorithmException | InvalidAlgorithmParameterException e) {
            throw new Encryption3DESException("no such algorithm" + e);
        }
        return decryptedText;
    }
    private void readFieldFromFile() throws  Decryption3DESException {
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
                    this.key = new SecretKeySpec(decode(secretkey), DESDE_ENCRYPTION_SCHEME);
                    this.IV = decode((String) jsonObject.get("IV"));

                }else{
                    throw new Decryption3DESException("aplication was not found");
                }
            }
        } catch (FileNotFoundException e) {
            throw new Decryption3DESException("file not found" + e);
        } catch (IOException e) {
            throw new Decryption3DESException("there is a problem with opening file" + e);
        } catch (Decryption3DESException e) {
            throw new Decryption3DESException("aplication was not found" + e);
        }
    }
    private byte[] decode(String data) {
        return Base64.getDecoder().decode(data);
    }
}
