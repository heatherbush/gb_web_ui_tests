package lesson7;

import io.qameta.allure.Step;
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

    @Step("Заполнить логин")
    public LivelibMainLoggedOutPage fillLogin(String login) {
        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.id(loginInputLocator)));
        loginField.click();
        loginField.sendKeys(login);
        return this;
    }

    @FindBy(xpath = "//button[contains(text(),'Продолжить')]")
    public WebElement proceedButton;

    @Step("Кликнуть на кнопку 'Продолжить'")
    public LivelibMainLoggedOutPage clickProceedButton() {
        proceedButton.click();
        return this;
    }

    @FindBy(name = passwordInputLocator)
    public WebElement passwordField;

    @Step("Заполнить пароль")
    public LivelibMainLoggedOutPage fillPassword(String password) {
        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.name(passwordInputLocator)));
        passwordField.sendKeys(password);
        return this;
    }

    @FindBy(id = "user[submit]")
    public WebElement submitButton;

    @Step("Кликнуть на кнопку 'Войти'")
    public LivelibMainLoggedPage clickSubmitButton() {
        submitButton.click();
        return new LivelibMainLoggedPage(driver);
    }

    public LivelibMainLoggedOutPage(WebDriver driver) {
        super(driver);
    }
}
