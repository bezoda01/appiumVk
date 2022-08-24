package base.driver;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;

import static settings.Settings.*;
import static utils.JsonUtils.*;

public class Driver {
    private static AndroidDriver driver;

    public static AndroidDriver getDriver() {
        if (driver == null) {
            driver = getInstance();
        }
        return driver;
    }

    private static AndroidDriver getInstance() {
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        Map<String, Object> caps = convertCapsToMap();
        for(String st: caps.keySet()) {
            desiredCapabilities.setCapability(st, caps.get(st));
        }
        AndroidDriver driver = null;
        try {
            driver = new AndroidDriver(new URL(config.get("urlAppium").toString()), desiredCapabilities);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return driver;
    }
}