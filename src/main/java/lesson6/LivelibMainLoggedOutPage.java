package lesson6;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class LivelibMainLoggedOutPage extends BaseView {

    public static final String loginInputLocator = "checkin-email-block";
    public static final String passwordInputLocator = "user[password]";

    @FindBy(id = loginInputLocator)
    public WebElement loginField;

    public LivelibMainLoggedOutPage fillLogin(String login) {
        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.id(loginInputLocator)));
        loginField.click();
        loginField.sendKeys(login);
        return this;
    }

    @FindBy(xpath = "//button[contains(text(),'Продолжить')]")
    public WebElement proceedButton;

    public LivelibMainLoggedOutPage clickProceedButton() {
        proceedButton.click();
        return this;
    }

    @FindBy(name = passwordInputLocator)
    public WebElement passwordField;

    public LivelibMainLoggedOutPage fillPassword(String password) {
        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.name(passwordInputLocator)));
        passwordField.sendKeys(password);
        return this;
    }

    @FindBy(id = "user[submit]")
    public WebElement submitButton;

    public LivelibMainLoggedPage clickSubmitButton() {
        submitButton.click();
        return new LivelibMainLoggedPage(driver);
    }

    public LivelibMainLoggedOutPage(WebDriver driver) {
        super(driver);
    }
}
