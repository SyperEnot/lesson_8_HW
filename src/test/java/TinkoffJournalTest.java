import data.Category;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.ValueSource;
import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Configuration.baseUrl;
import static com.codeborne.selenide.Selenide.*;

public class TinkoffJournalTest extends TestBase {

    @ValueSource(strings = {
            "ипотека",
            "дневник трат"
    })
    @ParameterizedTest(name = "Результат поиска по слову: {0} возвращает не пустую выдачу")
    @Tag("SMOKE")
    @DisplayName("Поисковая выдача по слову не пустая")
    void searchResultsShouldNotBeEmpty(String searchQuery) {
        open(baseUrl);
        $("._loupe_wvz82_55").click();
        $("._input_wvz82_21").setValue(searchQuery).pressEnter();
        $$("._root_1m2dt_3")
                .shouldBe(sizeGreaterThan(0));
    }

    @CsvFileSource(resources = "/test_data/searchResultsShouldContainExpectedCategory.csv")
    @ParameterizedTest(name = "Для поискового запроса {0} в первой карточке указан раздел статей {1}")
    @Tag("SMOKE")
    @DisplayName("Раздел первой карточки релевантен поисковому запросу")
    void searchResultsShouldContainExpectedCategory(String searchQuery,String expectedCategory) {
        open(baseUrl);
        $("._loupe_wvz82_55").click();
        $("._input_wvz82_21").setValue(searchQuery).pressEnter();
        $("._flowLink_1xbvf_59")
                .shouldHave(text(expectedCategory));
    }

    @EnumSource(Category.class)
    @ParameterizedTest(name = "Проверка категорий {0} первого потока")
    @Tag("SMOKE")
    @DisplayName("Отображение категорий для первого потока")
    void checkFlowCategory(Category city) {
        open("/flows");
        $("._wrapper_q0pgf_5")
                .shouldHave(text(city.description));
    }
}
