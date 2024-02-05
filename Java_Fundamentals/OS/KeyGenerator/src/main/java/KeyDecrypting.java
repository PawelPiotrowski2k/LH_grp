import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Base64;

public class KeyDecrypting {
    private SecretKey key;
    private String appName;
    private byte[] passwordInBytes;
    private byte[] IV;
    private final int T_LEN = 128;
    private final String pathToHiddenFile = "C:\\Users\\admin\\.passwords";

    public KeyDecrypting(String appName) {
        try (FileReader fileReader = new FileReader(pathToHiddenFile)) {
            JSONParser parser = new JSONParser();
            Object obj = parser.parse(fileReader);


            JSONObject obiektJSON = (JSONObject) obj;
            if (obiektJSON.containsKey(appName)) {
                JSONObject daneAplikacji = (JSONObject) obiektJSON.get(appName);
                this.passwordInBytes = (byte[]) daneAplikacji.get("password");
                String secretkey = (String) daneAplikacji.get("password");
                this.key = new SecretKeySpec(decode(secretkey), "AES");
                this.IV = decode((String) daneAplikacji.get("IV"));
            }else {
                throw new KeyDecryptingException("this app name is not saved");
            }

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException | ParseException e) {
            throw new RuntimeException(e);
        } catch (KeyDecryptingException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) throws Exception {
     KeyDecrypting keyDecrypting = new KeyDecrypting("spotify");
        System.out.println(keyDecrypting.decrypt());
    }

    public String decrypt() throws Exception {
        Cipher decryptionCipher = Cipher.getInstance("AES/GCM/NoPadding");
        GCMParameterSpec spec = new GCMParameterSpec(T_LEN, IV);
        decryptionCipher.init(Cipher.DECRYPT_MODE, key, spec);
        byte[] decryptedPassword = decryptionCipher.doFinal(passwordInBytes);
        return new String(decryptedPassword);
    }

    private byte[] decode(String data) {
        return Base64.getDecoder().decode(data);
    }

    private void readJSON() {


    }
}

