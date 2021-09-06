package lesson6;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static ru.yandex.qatools.htmlelements.matchers.WebElementMatchers.hasText;

public class LivelibWishPage extends BaseView {

    public static final String bookStatusBlockLocator = "//a[contains(@id,'span-userbook')]";
    public static final String menuChoiceOfStatusLocator = "//p[text()='Выбор действия']";
    public static final String listOfBookStatusLocator = "//input[@id='read-now']/parent::div";
    public static final String readNowStatusBlockLocator = "//a[contains(@title,'Читаю сейчас')]";
    public static final String checkboxSelectListLocator = "//a[contains(@data-id,'my-book-list-checkbox')]/span";
    public static final String blockChoiceOfActionLocator = "//div[@id='my-book-list-block']";
    public static final String bookNumbersListLocator = "//div[@class='brow-priority']/span";

    @FindBy(xpath = bookStatusBlockLocator)
    public List<WebElement> statusAllBooksInPageList;

    public LivelibWishPage openMenuChoiceOfBookStatus() {
        webDriverWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(bookStatusBlockLocator)));
        statusAllBooksInPageList.get(0).click();
        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(menuChoiceOfStatusLocator)));
        return this;
    }

    @FindBy(xpath = "//div[@class='add-book__action-item']/input[@type='radio'][@value='2']/following-sibling::label")
    public WebElement readNowStatus;

    public LivelibWishPage changeStatusToReadNow() {
        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(listOfBookStatusLocator)));
        readNowStatus.click();
        return this;
    }

    @FindBy(xpath = "//div[contains(@class,'add-book__footer-buttons')]/button[contains(text(),'Сохранить')]")
    public WebElement saveStatusButton;

    public LivelibWishPage saveBookStatus() {
        saveStatusButton.click();
        return this;
    }

    @FindBy(xpath = readNowStatusBlockLocator)
    public WebElement readNowStatusBlock;

    public void assertReadNowStatus() {
        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(readNowStatusBlockLocator)));
        assertThat(readNowStatusBlock, hasText("Читаю"));
    }

    @FindBy(xpath = checkboxSelectListLocator)
    public List<WebElement> checkboxSelectList;

    public LivelibWishPage openChoiceOfActionBlock() {
        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.xpath(checkboxSelectListLocator)));
        checkboxSelectList.get(0).click();
        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(blockChoiceOfActionLocator)));
        return this;
    }

    @FindBy(id = "my-book-list-select")
    public WebElement selectAction;

    public LivelibWishPage chooseAnActionForThisBook(String action) {
        new Select(selectAction).selectByVisibleText(action);
        return this;
    }

    @FindBy(xpath = "//input[@value='Применить']")
    public WebElement applyActionButton;

    public LivelibWishPage applyAction() {
        applyActionButton.click();
        driver.switchTo().alert().accept();
        return this;
    }

    @FindBy(xpath = bookNumbersListLocator)
    public List<WebElement> bookNumbersList;

    public void assertFirstBookNumber() {
        webDriverWait.until(driver -> bookNumbersList.get(0).getText().equals("№ 2"));
        assertThat(bookNumbersList.get(0), hasText("№ 2"));
    }

    public LivelibWishPage(WebDriver driver) {
        super(driver);
    }
}
