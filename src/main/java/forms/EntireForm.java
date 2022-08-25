package forms;

import base.baseForm.BaseForm;
import base.elements.IFactory;
import base.elements.TextField;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

public class EntireForm extends BaseForm {

    private final TextField usernameField = getIFactory().getTextField(By.name("login"), "Username field");
    private TextField passwordField = null;

    public EntireForm() {
        super(new TextField(By.xpath("//div[@class='vkc__Auth__pageBox']"),"Entire page"));
    }

    public void enterAndLogin(String username, String password) {
        usernameField.sendText(username);
        usernameField.sendKeys(Keys.ENTER);
        passwordField = getIFactory().getTextField(By.name("password"), "Password field");
        passwordField.sendText(password);
        passwordField.sendKeys(Keys.ENTER);
    }
}
