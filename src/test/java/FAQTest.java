package org.example;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.Arrays;
import java.util.Collection;

import static junit.framework.TestCase.assertEquals;
import static org.example.Resources.*;


@RunWith(Parameterized.class)
public class FAQTest {
    private WebDriver driver;
    private HomePageScooter homePage;
    private int questionNumber;
    private String expectedAnswer;

    public FAQTest(int questionNumber, String expectedAnswer) {
        this.questionNumber = questionNumber;
        this.expectedAnswer = expectedAnswer;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> provideFAQData() {
        return Arrays.asList(new Object[][]{
                {1, answer1Text},
                {2, answer2Text},
                {3, answer3Text},
                {4, answer4Text},
                {5, answer5Text},
                {6, answer6Text},
                {7, answer7Text},
                {8, answer8Text}
        });
    }

    @Before
    public void setUp() {
        driver = new ChromeDriver();
        driver.get("https://qa-scooter.praktikum-services.ru");
        WebElement tableFAQ = driver.findElement(By.xpath(".//div[@class='accordion']"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", tableFAQ);
        homePage = new HomePageScooter(driver);
    }

    @After
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void testFAQCorrectAnswerText() {
        homePage.clickQuestion(questionNumber);
        String actualAnswer = homePage.getAnswer(questionNumber);
        System.out.println(actualAnswer);
        assertEquals("Ответ на вопрос №" + questionNumber + " неверен.", expectedAnswer, actualAnswer);

    }

}