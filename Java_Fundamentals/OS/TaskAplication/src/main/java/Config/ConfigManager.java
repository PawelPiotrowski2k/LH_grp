package Config;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Base64;
import java.util.Properties;

public class ConfigManager {
    private final static String PATH_TO_CONFIG_FILE = "src/config.properties";
    private Properties properties;

    public ConfigManager() {
        this.properties = loadProperties();
    }


    public void saveProperties() throws ConfigManagerException {
        try(FileOutputStream fos = new FileOutputStream(PATH_TO_CONFIG_FILE);) {
            properties.store(fos,"changes saved");
        }catch (IOException e){
            throw new ConfigManagerException("There was problem with config file");
        }
    }
    public void setDbUrl(String url){
        properties.setProperty("db.url",url);
    }
    public void setDbPassword(String password){
        properties.setProperty("db.password",encodePassword(password));
    }
    public void setDbLogin(String login){
        properties.setProperty("db.username",login);
    }
    public void setMailLogin(String login){
        properties.setProperty("mail.login",login);
    }
    public void setMailPassword(String password){
        properties.setProperty("mail.password",encodePassword(password));
    }
    public String getDbUrl(){
        return properties.getProperty("db.url");
    }
    public String getDbUsername(){
        return properties.getProperty("db.username");
    }
    public String getDbPassword(){
        return decodePassword(properties.getProperty("db.password"));
    }
    public String getMailLogin(){
        return  properties.getProperty("mail.login");
    }
    public String getMailPassword(){
        return decodePassword(properties.getProperty("mail.password"));
    }

    private Properties loadProperties(){
        properties = new Properties();
        try (FileInputStream fis = new FileInputStream(PATH_TO_CONFIG_FILE)){
            properties.load(fis);
            return properties;
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    private static String encodePassword(String password) {
        byte[] encodedBytes = Base64.getEncoder().encode(password.getBytes());
        return new String(encodedBytes);
    }
    private static String decodePassword(String encodedPassword) {
        byte[] decodedBytes = Base64.getDecoder().decode(encodedPassword);
        return new String(decodedBytes);
    }

}

