package lesson6;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CrmLoginPage extends BaseView {
    @FindBy(id = "prependedInput")
    public WebElement inputLogin;

    @FindBy(id = "prependedInput2")
    public WebElement inputPassword;

    @FindBy(xpath = "//button")
    public WebElement loginButton;

    public CrmLoginPage fillInputLogin(String login) {
        inputLogin.sendKeys(login);
        return this;
    }

    public CrmLoginPage fillInputPassword(String password) {
        inputPassword.sendKeys(password);
        return this;
    }

    public CrmMainPage clickLoginButton() {
        loginButton.click();
        return new CrmMainPage(driver);
    }

    public void loginToCrm(String login, String password) {
        inputLogin.sendKeys(login);
        inputPassword.sendKeys(password);
        loginButton.click();
    }

    public CrmLoginPage(WebDriver driver) {
        super(driver);
    }
}
