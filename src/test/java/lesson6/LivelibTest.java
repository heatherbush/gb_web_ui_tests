package lesson6;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

public class LivelibTest extends BaseLiveLibTest {

    public static final String BASE_URL = "https://www.livelib.ru/";

    @Nested
    class OnlyLoginTest {

        @Test
        void loginToLiveLib() {
            driver.get(BASE_URL);
            new LivelibMainLoggedOutPage(driver)
                    .fillLogin("BeattleTranced")
                    .clickProceedButton()
                    .fillPassword("7h3f4f3t85")
                    .clickSubmitButton()
                    .openUserNav()
                    .assertLogin();
        }

        @Nested
        class RestTests {
            @BeforeEach
            void loginAndCookieAgree() {
                loginToLiveLib();
                new LivelibMainLoggedPage(driver)
                        .cookiesAgree();
            }

            @Test
            void addBookToReadingNowListFromWantToRead() {
                driver.get(BASE_URL + "reader/BeattleTranced/wish");
                new LivelibWishPage(driver)
                        .openMenuChoiceOfBookStatus()
                        .changeStatusToReadNow()
                        .saveBookStatus()
                        .assertReadNowStatus();
            }

            @Test
            void addBookToWishList() {
                driver.get(BASE_URL + "book/1001350571-soyuz-kapitana-forpatrila-lois-makmaster-budzhold");
                new LivelibBookPage(driver)
                        .clickButtonToAddBookToWishList()
                        .assertBookInWishList();
            }

            @Test
            void deleteBookFromWishList() {
                driver.get(BASE_URL + "reader/BeattleTranced/wish");
                new LivelibWishPage(driver)
                        .openChoiceOfActionBlock()
                        .chooseAnActionForThisBook("Удалить")
                        .applyAction()
                        .assertFirstBookNumber();
            }
        }
    }
}
