package lesson7;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CrmContactPage extends BaseView {
    @FindBy(xpath = "//a[@title='Создать контактное лицо']")
    public WebElement createNewContactButton;

    @Step("Кликнуть на кнопку 'Создать контактное лицо'")
    public CrmCreateNewContactPage clickToCreateNewContactButton() {
        createNewContactButton.click();
        return new CrmCreateNewContactPage(driver);
    }

    public CrmContactPage(WebDriver driver) {
        super(driver);
    }
}
