import base.driver.Driver;
import org.testng.annotations.Test;

public class TestSuit {

    @Test
    public void test() {
        Driver.getDriver().get("https://www.headspin.io/blog/using-appium-for-testing-mobile-web-apps");
        System.out.println(Driver.getDriver().getTitle());
    }
}
