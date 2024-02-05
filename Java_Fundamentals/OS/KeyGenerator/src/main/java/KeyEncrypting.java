import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.io.FileWriter;
import java.security.Key;
import java.util.Base64;
import org.json.simple.JSONObject;

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

    public static void main(String[] args) throws Exception {
        KeyEncrypting keyEncrypting = new KeyEncrypting("spotify", "1234","AES");
        keyEncrypting.init();
        keyEncrypting.encrypt();
        keyEncrypting.saveToJSON();
    }
    public void init() throws Exception {
        KeyGenerator generator = KeyGenerator.getInstance(encryptionalgorithm);
        generator.init(KEY_SIZE);
        key = generator.generateKey();
    }
    public String encrypt()throws Exception{
        byte[] passwordInBytes = password.getBytes();
        encryptionCipher = Cipher.getInstance("AES/GCM/NoPadding");
        encryptionCipher.init(Cipher.ENCRYPT_MODE,key);
        byte[] encryptedBytes = encryptionCipher.doFinal(passwordInBytes);
        return encode(encryptedBytes);
    }
    private void saveToJSON() throws Exception {
        JSONObject obiektJSON = new JSONObject();
        obiektJSON.put("AppName", appName);
        obiektJSON.put("password", encrypt());
        obiektJSON.put("IV", encode(encryptionCipher.getIV()));
        obiektJSON.put("key", encode(key.getEncoded()));
        try (FileWriter fileWriter = new FileWriter(pathToHiddenFile)) {
            fileWriter.write(obiektJSON.toJSONString());
        }
    }
    private String encode(byte[] data){
        return Base64.getEncoder().encodeToString(data);
    }

}
