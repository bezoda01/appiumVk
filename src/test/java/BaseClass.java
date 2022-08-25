import base.driver.Driver;
import com.github.automatedowl.tools.AllureEnvironmentWriter;
import com.google.common.collect.ImmutableMap;
import org.json.simple.JSONObject;
import org.testng.IHookCallBack;
import org.testng.IHookable;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import utils.JsonUtils;
import java.nio.file.Paths;
import java.util.concurrent.TimeUnit;

import static base.Appium.browser;
import static utils.AllureUtils.addAttachment;
import static settings.Settings.*;
import static utils.HostUtils.getHostName;

public class BaseClass implements IHookable {

    @BeforeClass
    public void beforeClass() {
        Driver.getDriver().manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
        browser().goTo();
    }

    @AfterClass
    public void afterClass() {
        addAttachment(Paths.get("log.log"), "Log");
        AllureEnvironmentWriter.allureEnvironmentWriter(
                ImmutableMap.<String, String> builder()
                        .put("Browser", new JSONObject(JsonUtils.convertCapsToMap()).get("browserName").toString())
                        .put("ENV", getHostName())
                        .put("URL", config.get("url").toString())
                        .build(),"allure-results/");
    }

    @Override
    public void run(IHookCallBack callBack, ITestResult testResult) {
        callBack.runTestMethod(testResult);
        if (testResult.getThrowable() != null) {
            browser().makeScreenShotByByte(testResult.getMethod().getMethodName());
        }
    }
}