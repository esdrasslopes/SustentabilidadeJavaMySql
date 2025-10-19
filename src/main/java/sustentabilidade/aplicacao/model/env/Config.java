package sustentabilidade.aplicacao.model.env;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Config {
    private Properties props = new Properties();

    public Config() {
        try (FileInputStream fis = new FileInputStream(".env")) {
            props.load(fis);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String get(String key) {
        return props.getProperty(key);
    }
}