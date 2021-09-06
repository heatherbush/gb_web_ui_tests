package lesson6;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static org.hamcrest.MatcherAssert.assertThat;
import static ru.yandex.qatools.htmlelements.matchers.WebElementMatchers.isDisplayed;

public class LivelibBookPage extends BaseView {

    public static final String addBookButtonLocator = "//div[@data-view_mode='main']/a[contains(@id,'span-userbook')]";
    public static final String wantToReadMessageLocator = "//span/a[@href='/reader/BeattleTranced/wish'][text()='Хочу прочитать']";

    @FindBy(xpath = addBookButtonLocator)
    public WebElement addBookButton;

    public LivelibBookPage clickButtonToAddBookToWishList() {
        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(addBookButtonLocator)));
        addBookButton.click();
        return this;
    }

    @FindBy(xpath = wantToReadMessageLocator)
    public WebElement wantToReadMessage;

    public void assertBookInWishList() {
        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(wantToReadMessageLocator)));
        assertThat(wantToReadMessage, isDisplayed());
    }

    public LivelibBookPage(WebDriver driver) {
        super(driver);
    }
}
