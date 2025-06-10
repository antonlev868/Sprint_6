package org.example;

import org.hamcrest.MatcherAssert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import static org.hamcrest.CoreMatchers.is;

public class HomePageScooter {
    private WebDriver driver;

    // Локаторы для вопросов
    private By[] questions = {
            By.xpath(".//div[@class='accordion__item'][1]"),
            By.xpath(".//div[@class='accordion__item'][2]"),
            By.xpath(".//div[@class='accordion__item'][3]"),
            By.xpath(".//div[@class='accordion__item'][4]"),
            By.xpath(".//div[@class='accordion__item'][5]"),
            By.xpath(".//div[@class='accordion__item'][6]"),
            By.xpath(".//div[@class='accordion__item'][7]"),
            By.xpath(".//div[@class='accordion__item'][8]")
    };

    // Локаторы для ответов
    private By[] answers = {
            By.id("accordion__panel-0"),
            By.id("accordion__panel-1"),
            By.id("accordion__panel-2"),
            By.id("accordion__panel-3"),
            By.id("accordion__panel-4"),
            By.id("accordion__panel-5"),
            By.id("accordion__panel-6"),
            By.id("accordion__panel-7")
    };

    // Локаторы для кнопок "Заказать"
    private By headerOrderButton = By.xpath(".//button[text()='Заказать'][1]");
    private By pageOrderButton = By.xpath(".//div[contains(@class, 'Home_FinishButton')]/button");

    public HomePageScooter(WebDriver driver) {
        this.driver = driver;
    }

    // Метод для раскрытия вопроса по номеру
    public void clickQuestion(int number) {
        driver.findElement(questions[number - 1]).click(); // Исправление индекса
    }

    // Метод для получения текста ответа по номеру
    public String getAnswer(int number) {
        return driver.findElement(answers[number - 1]).getText(); // Исправление индекса
    }

    // Метод для сравнения ответа на вопрос с правильным текстом
    public void isCorrectText(String answer, String text) {
        MatcherAssert.assertThat(answer, is(text));
    }

    // Методы для клика по кнопкам "Заказать"
    public void clickHeaderOrderButton() {
        driver.findElement(headerOrderButton).click();
    }

    public void clickPageOrderButton() {
        // Проскролить до появления кнопки
        WebElement bigButton = driver.findElement(pageOrderButton);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", bigButton);
        driver.findElement(pageOrderButton).click();
    }
}