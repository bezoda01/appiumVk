package forms;

import base.baseForm.BaseForm;
import base.elements.Button;
import org.openqa.selenium.By;

public class NewsForm extends BaseForm {

    private final Button menu = getIFactory().getButton(By.xpath("//div[@class='_BottomMenu__icon_js BottomMenu__icon_menu BottomMenu__icon']"), "Button menu");

    public NewsForm() {
        super(new Button(By.xpath("//div[@class='basis']"), "News page"));
    }

    public void goToMenu() {
        menu.click();
    }
}