package base.baseForm;

import base.elements.BaseElement;
import base.elements.IFactory;

public class BaseForm {

    private BaseElement uniqueElement;

    public BaseForm(BaseElement uniqueElement) {
        this.uniqueElement = uniqueElement;
    }

    public IFactory getIFactory() {
        return new IFactory();
    }

    public boolean isDisplayed() {
        return uniqueElement.getElement().isDisplayed();
    }

    public boolean isEnabled() {
        return uniqueElement.getElement().isEnabled();
    }
}