package forms;

import base.baseForm.BaseForm;
import base.elements.Button;
import base.elements.TextField;
import org.openqa.selenium.By;

public class MenuForm extends BaseForm {

    private Button userPageButton = getIFactory().getButton(By.xpath("//div[@class='Row__right Icon Icon_chevron_right']"), "user page");

    public MenuForm() {
        super(new TextField(By.xpath("//div[@class='OwnerRow__title']"), "Menu form"));
    }

    public void toUserPage() {
        userPageButton.click();
    }
}