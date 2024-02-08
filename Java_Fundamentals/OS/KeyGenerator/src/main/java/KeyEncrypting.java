import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import javax.crypto.*;
import java.io.*;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class KeyEncrypting {
    private final String pathToHiddenFile = "C:\\Users\\admin\\.passwords";
    private final String appName;
    private final String password;
    private final String encryptionalgorithm;
    private Cipher encryptionCipher;
    private SecretKey key;

    private final static int KEY_SIZE = 128;


    public KeyEncrypting(String appName, String password, String encryptionalgorithm) {
        this.appName = appName;
        this.password = password;
        this.encryptionalgorithm = encryptionalgorithm;
    }

    public void init() throws KeyEncryptingException {
        try {
            KeyGenerator generator = KeyGenerator.getInstance(encryptionalgorithm);
            generator.init(KEY_SIZE);
            key = generator.generateKey();
        }catch (NoSuchAlgorithmException e){
            throw new KeyEncryptingException("no such algorithm");
        }
    }

    public String encrypt() throws KeyEncryptingException {
        try {
            byte[] passwordInBytes = password.getBytes();
            encryptionCipher = Cipher.getInstance("AES/GCM/NoPadding");
            encryptionCipher.init(Cipher.ENCRYPT_MODE, key);
            byte[] encryptedBytes = encryptionCipher.doFinal(passwordInBytes);
            return encode(encryptedBytes);
        }catch (NoSuchAlgorithmException e){
            throw new KeyEncryptingException("no such algorythm");
        }catch (InvalidKeyException e){
            throw new KeyEncryptingException("invalid key");
        }catch (IllegalBlockSizeException e){
            throw new KeyEncryptingException("illegal block size");
        }catch (BadPaddingException e){
            throw new KeyEncryptingException("bad padding");
        }catch (NoSuchPaddingException e){
            throw new KeyEncryptingException("no such padding exception");
        }
    }

    private void saveToJSON() throws KeyEncryptingException {
        File file = new File(pathToHiddenFile);
        JSONArray jsonArray;
        if (file.exists()) {
            try (FileReader reader = new FileReader(pathToHiddenFile)) {
                jsonArray = new JSONArray(new JSONTokener(reader));
            }catch (IOException e){
                throw new KeyEncryptingException("There is a problem with opening the file");
            }
        } else {
            try {
                file.createNewFile();
            }catch (IOException e){
                throw new KeyEncryptingException("There is a problem with opening the file");
            }
            jsonArray = new JSONArray();
        }
        JSONObject newData = new JSONObject();
        newData.put("AppName", appName);
        newData.put("password", encrypt());
        newData.put("IV", encode(encryptionCipher.getIV()));
        newData.put("key", encode(key.getEncoded()));
        jsonArray.put(newData);
        try (FileWriter fileWriter = new FileWriter(pathToHiddenFile)) {
            fileWriter.write(jsonArray.toString(4));
        }catch (IOException e){
            throw new KeyEncryptingException("there was a problem with opening the file");
        }
    }

    private String encode(byte[] data) {
        return Base64.getEncoder().encodeToString(data);
    }

}
