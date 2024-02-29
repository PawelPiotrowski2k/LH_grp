package EDS.decryption;

import EDS.Encryption.Encryption3DESException;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Base64;


public class Decryption3DES {
    private static final String pathToHiddenFile = "C:\\Users\\admin\\.passwords";
    private static final String UNICODE_FORMAT = "UTF8";
    private final String appName;
//    final private KeySpec ks;
//    final private SecretKeyFactory skf;
    private byte[] passwordInBytes;
//    private static final String DESDE_ENCRYPTION_SCHEME = "DESede";
//    private final String myDescryptionScheme;
    private Cipher cipher;
    final byte[] arrayBytes;
    private String myDecryptionKey;
    private SecretKey key;

    public Decryption3DES(String appName) throws Decryption3DESException, UnsupportedEncodingException, InvalidKeySpecException, InvalidKeyException, NoSuchAlgorithmException {
        this.myDecryptionKey = "randomWord";
//        myDescryptionScheme = DESDE_ENCRYPTION_SCHEME;
        arrayBytes = myDecryptionKey.getBytes(UNICODE_FORMAT);
//        ks = new DESedeKeySpec(arrayBytes);
//        skf = SecretKeyFactory.getInstance(myDescryptionScheme);
//        key = skf.generateSecret(ks);
        this.appName = appName;
        readFieldFromFile();
    }

    public static void main(String[] args) throws UnsupportedEncodingException, Decryption3DESException, InvalidKeySpecException, NoSuchAlgorithmException, InvalidKeyException, Encryption3DESException {
        Decryption3DES decryption3DES = new Decryption3DES("spotify");
        decryption3DES.readFieldFromFile();
        System.out.println(decryption3DES.decrypt());
    }

    public String decrypt() throws Encryption3DESException {
        String decryptedText = null;
        try {
            cipher = Cipher.getInstance("DESede/CBC/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE,key);
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
            throw new RuntimeException(e);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
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
                    this.key = new SecretKeySpec(decode(secretkey), "DESede");
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
