package lesson6;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CrmContactPage extends BaseView {
    @FindBy(xpath = "//a[@title='Создать контактное лицо']")
    public WebElement createNewContactButton;

    public CrmCreateNewContactPage clickToCreateNewContactButton() {
        createNewContactButton.click();
        return new CrmCreateNewContactPage(driver);
    }

    public CrmContactPage(WebDriver driver) {
        super(driver);
    }
}
