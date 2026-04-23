package config;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;

public class TestConfig {

    @BeforeAll
    public static void setUp() {
        String path = "file:///" + System.getProperty("user.dir") + "/src/test/resources/test.html";
        Configuration.baseUrl = path;
        Configuration.browser = "chrome";
        Configuration.headless = false;
        Configuration.timeout = 5000;
    }
}