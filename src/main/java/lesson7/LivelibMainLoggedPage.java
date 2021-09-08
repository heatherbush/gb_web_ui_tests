package lesson7;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static org.hamcrest.MatcherAssert.assertThat;
import static ru.yandex.qatools.htmlelements.matchers.WebElementMatchers.isDisplayed;

public class LivelibMainLoggedPage extends BaseView {

    public static final String userNavIconLocator = "//summary[@class='user-nav__toggle']/img[@alt='BeattleTranced']";
    public static final String myBookLinkLocator = "//a[contains(text(),'Мои книги')]";
    public static final String cookiesAgreeLocator = "//div[@class='btn-cookies-agree']";

    @FindBy(xpath = userNavIconLocator)
    public WebElement userNavIcon;

    @Step("Открыть меню пользователя")
    public LivelibMainLoggedPage openUserNav() {
        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(userNavIconLocator)));
        userNavIcon.click();
        return this;
    }

    @FindBy(xpath = myBookLinkLocator)
    public WebElement myBookLink;

    @Step("Проверить наличие выбранной ссылки в меню пользователя")
    public void assertLogin() {
        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(myBookLinkLocator)));
        assertThat(myBookLink, isDisplayed());
    }

    @FindBy(xpath = cookiesAgreeLocator)
    public WebElement cookiesAgreeButton;

    @Step("Согласиться на куки")
    public void cookiesAgree() {
        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(cookiesAgreeLocator)));
        cookiesAgreeButton.click();
    }


    public LivelibMainLoggedPage(WebDriver driver) {
        super(driver);
    }
}
