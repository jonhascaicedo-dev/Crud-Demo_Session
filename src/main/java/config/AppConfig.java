package config;

import java.io.InputStream;
import java.io.IOException;
import java.util.Properties;

public class AppConfig {

    private static final Properties props = new Properties();

    static {
        // Busca el archivo directamente en la raíz de src/main/resources
        try (InputStream input = AppConfig.class.getClassLoader()
                .getResourceAsStream("config-local.properties")) {

            if (input != null) {
                props.load(input);
            } else {
                System.out.println("No se encontró config-local.properties. Usando opciones VM / Sistema.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getDbUser() {
        // Intenta obtener primero del .properties local, si no de VM Options (-Ddb.user=...)
        return props.getProperty("db.user", System.getProperty("db.user"));
    }

    public static String getDbPassword() {
        return props.getProperty("db.password", System.getProperty("db.password"));
    }

    public static String getApiKey() {
        return props.getProperty("api.thirdparty.key", System.getProperty("api.thirdparty.key"));
    }
}


