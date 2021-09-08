package lesson7;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class CrmProjectPage extends BaseView {

    public static final String buttonCreateNewProjectLocator = "//a[@title='Создать проект']";

    @FindBy(xpath = buttonCreateNewProjectLocator)
    public WebElement buttonCreateNewProject;

    @Step("Кликнуть на кнопку 'Создать проект'")
    public CrmCreateNewProjectPage clickToCreateProjectButton() {
        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(buttonCreateNewProjectLocator)));
        buttonCreateNewProject.click();
        return new CrmCreateNewProjectPage(driver);
    }

    public CrmProjectPage(WebDriver driver) {
        super(driver);
    }
}
