package ru.aplana.autotest.steps;

import cucumber.api.java.ru.Когда;
import io.qameta.allure.Step;
import ru.aplana.autotest.pages.BasketPage;
import ru.aplana.autotest.pages.ItemsListPage;
import ru.aplana.autotest.pages.MainPage;
import ru.aplana.autotest.util.AllureListener;

import java.io.IOException;

public class ScenarioSteps {
    MainPage mainPage = new MainPage();
    ItemsListPage itemsListPage = new ItemsListPage();
    BasketPage basketPage = new BasketPage();

    @Когда("выбран товар (.*)")
    @Step("выбран товар {0}")
    public void inputItemNameAndSearch(String name) {
       mainPage.search(name);
    }

    @Когда("скрыт баннер с куки")
    @Step("скрыт баннер с куки")
    public void closeBanner() {
        itemsListPage.closeCookieBanner();
    }

    @Когда("выбрана опция (.*) с параметром (.*)")
    @Step("выбрана опция {0} с параметром {1}")
    public void chooseOption(String optionName, String par) {
        itemsListPage.findOption(optionName, par);
    }

    @Когда("поставлена галочка Высокий рейтинг")
    @Step("поставлена галочка Высокий рейтинг")
    public void highRate() {
        itemsListPage.getRating().click();
    }

    @Когда("выбор нечетных элементов из списка")
    @Step("выбор нечетных элементов из списка")
    public void selectListItems() {
        itemsListPage.selectItems();
        itemsListPage.selectOdd();
    }

    @Когда("выбор четных элементов из списка")
    @Step("выбор четных элементов из списка")
    public void selectAnotherListItems() {
        itemsListPage.selectItems();
        itemsListPage.selectEven();
    }

    @Когда("переход в корзину")
    @Step("переход в корзину")
    public void goToBasketPage() {
        itemsListPage.getCartButton().click();
    }

    @Когда("проверяется наличие добавленных элементов в корзине")
    @Step("проверяется наличие добавленных элементов в корзине")
    public void checkItems() {
        basketPage.checkNames();
    }

    @Когда("проверка отображения текста (.*) и количества товаров - (.*)")
    @Step("проверка отображения текста {0} и количества товаров - {1}")
    public void checkNumberOfItems(String name, Integer q) {

        basketPage.checkTotal(name,q);
    }

    @Когда("удалить все товары из корзины")
    @Step("удалить все товары из корзины")
    public void deleteAllFromBasket() {
        basketPage.deleteAll();
    }

    @Когда("проверить, что корзина пуста")
    @Step("проверить, что корзина пуста")
    public void isBasketEmpty() {
        basketPage.checkIfEmpty();
    }

    @Когда("записать наименования товаров в файл и прикрепить к отчету")
    @Step("записать наименования товаров в файл и прикрепить к отчету")
    public void writeAndAttach() throws IOException {
        basketPage.writeIntoFile();
        AllureListener.getBytes("items.txt");
    }


}
