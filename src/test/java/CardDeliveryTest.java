import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class CardDeliveryTest {



    @BeforeEach
    void setUp() {
        open("http://localhost:9999/");
    }

    @Test
    public void shouldGetSuccess() {
        $("[data-test-id=city] input").setValue("Москва");
        String date = LocalDate.now().plusDays(3).format((DateTimeFormatter.ofPattern("dd.MM.yyyy")));
        $("[data-test-id=date] input").doubleClick().sendKeys(Keys.BACK_SPACE);
        $("[data-test-id=date] input").sendKeys(date);
        $("[data-test-id=name] input").setValue("Андрей Горькаев");
        $("[data-test-id=phone] input").setValue("+79017054075");
        $("[class=checkbox__box]").click();
        $$("[class=button__text]").find(exactText("Забронировать")).click();
        $("[data-test-id=notification]").shouldBe(text("Успешно! Встреча успешно забронирована на "
                + date), Duration.ofSeconds(15)).shouldBe(visible);
    }

    @Test
    public void should() {
        $("[data-test-id=city] input").setValue("Мо");
        $$("[class=menu-item__control]").find(exactText("Москва")).click();
        $("[data-test-id=date] input").click();
        int days = 4;
        for (int i = 0; i < days; i++) {
            $(".calendar").sendKeys(Keys.ARROW_RIGHT);
        }
        String date = LocalDate.now().plusDays(7).format((DateTimeFormatter.ofPattern("dd.MM.yyyy")));
        $("[data-test-id=name] input").setValue("Андрей Горькаев");
        $("[data-test-id=phone] input").setValue("+79017054075");
        $("[class=checkbox__box]").click();
        $$("[class=button__text]").find(exactText("Забронировать")).click();
        $("[data-test-id=notification]").shouldBe(text("Успешно! Встреча успешно забронирована на "
                + date), Duration.ofSeconds(15)).shouldBe(visible);
    }
}