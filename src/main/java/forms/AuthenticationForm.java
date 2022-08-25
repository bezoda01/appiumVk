package forms;

import base.baseForm.BaseForm;
import base.elements.Button;
import base.elements.TextField;
import org.openqa.selenium.By;

public class AuthenticationForm extends BaseForm {

    private final Button signIn = getIFactory().getButton(By.xpath("//a[@class='button wide_button' and not(contains(@href, 'hidden'))]"), "Login in button");

    public AuthenticationForm() {
        super(new TextField(By.xpath("//div[@class='VkIdForm__title']"),"Authentication page"));
    }

    public void clickToEntire() {
        signIn.click();
    }
}