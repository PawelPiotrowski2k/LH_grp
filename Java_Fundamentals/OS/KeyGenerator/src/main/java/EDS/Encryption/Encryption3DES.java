package EDS.Encryption;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.io.*;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class Encryption3DES {
private final String UNICODE_FORMAT = "UTF-8";
private final String DESDE_ENCRYPTION_SCHEME = "DESede";
private final int KEY_SIZE = 112;
private final String pathToHiddenFile = "C:\\Users\\Pawel\\.passwords";
private Cipher cipher;
private final SecretKey key;
private final SecretKeySpec sks;
private final String appName;
private final String password;

public Encryption3DES(String appName, String password) throws Encryption3DESException {
    this.appName = appName;
    this.password = password;
    try{
        KeyGenerator generator = KeyGenerator.getInstance(DESDE_ENCRYPTION_SCHEME);
        generator.init(KEY_SIZE);
        key = generator.generateKey();
        this.sks = new SecretKeySpec(key.getEncoded(),DESDE_ENCRYPTION_SCHEME);
    } catch (NoSuchAlgorithmException e){
        throw new Encryption3DESException("no such algorithm" + e);
    }
}

    public static void main(String[] args) throws Encryption3DESException {
        Encryption3DES encryption3DES = new Encryption3DES("spotify","pawelos");
        encryption3DES.encrypt();
        encryption3DES.saveToJSON();
    }
public String encrypt() throws Encryption3DESException {
    String encryptedString = null;
    try {
        cipher = Cipher.getInstance("DESede/CBC/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, key);
        byte[] plainText = password.getBytes(UNICODE_FORMAT);
        byte[] encryptedText = cipher.doFinal(plainText);
        encryptedString = encode(encryptedText);;
    } catch (UnsupportedEncodingException e) {
        throw new RuntimeException(e);
    } catch (IllegalBlockSizeException e) {
        throw new Encryption3DESException("illegal block size" + e);
    } catch (BadPaddingException e) {
        throw new Encryption3DESException("Bad padding" + e);
    }catch (InvalidKeyException e){
        throw new Encryption3DESException("invalid key" + e);
    } catch (NoSuchPaddingException e) {
        throw new Encryption3DESException("no such padding" + e);
    } catch (NoSuchAlgorithmException e) {
        throw new Encryption3DESException("no such algorithm" + e);
    }
    return encryptedString;
}
    private void saveToJSON() throws Encryption3DESException {
        File file = new File(pathToHiddenFile);
        JSONArray jsonArray;
        if (file.exists()) {
            try (FileReader reader = new FileReader(pathToHiddenFile)) {
                jsonArray = new JSONArray(new JSONTokener(reader));
            }catch (IOException e){
                throw new Encryption3DESException("There is a problem with opening the file" + e);
            }
        } else {
            try {
                file.createNewFile();
            }catch (IOException e){
                throw new Encryption3DESException("There is a problem with opening the file");
            }
            jsonArray = new JSONArray();
        }
        JSONObject newData = new JSONObject();
        newData.put("AppName", appName);
        newData.put("password", encrypt());
        newData.put("key", encode(sks.getEncoded()));
        newData.put("IV", encode(cipher.getIV()));
        jsonArray.put(newData);
        try (FileWriter fileWriter = new FileWriter(pathToHiddenFile)) {
            fileWriter.write(jsonArray.toString(4));
        }catch (IOException e){
            throw new Encryption3DESException("there was a problem with opening the file");
        }
    }
    private String encode(byte[] data) {
        return Base64.getEncoder().encodeToString(data);
    }
}
