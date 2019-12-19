package utility;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Stage;
import io.klimigo.privatebank.server.execution.guice.GuiceModule;
import io.klimigo.privatebank.server.repository.DBModule;

import java.io.IOException;
import java.util.Properties;

import static java.util.Objects.isNull;

public class Globals {
    private static Injector guiceInjector = null;

    public static Injector getGuiceInjector() {
        if (isNull(guiceInjector)) {
            guiceInjector = Guice.createInjector(Stage.PRODUCTION,
                    new GuiceModule(), new DBModule());
        }
        return guiceInjector;
    }

    public static Properties initializeConfig() {
        Properties prop = new Properties();

        try {
            prop.load(Globals.class.getClassLoader().
                    getResourceAsStream("config.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return prop;
    }
}
