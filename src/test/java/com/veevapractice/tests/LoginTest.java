package com.veevapractice.tests;

import com.veevapractice.pages.LoginPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class LoginTest {

    private WebDriver driver;
    private LoginPage loginPage;

    @BeforeEach
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        driver = new ChromeDriver(options);
        loginPage = new LoginPage(driver);
    }

    @Test
    public void validLoginTest() {
        loginPage.navigateTo();
        loginPage.loginAs("tomsmith", "SuperSecretPassword!");
        assertTrue(loginPage.isSuccessMessageDisplayed());
    }

    @Test
    public void invalidLoginTest() {
        loginPage.navigateTo();
        loginPage.loginAs("wronguser", "wrongpassword");
        assertFalse(loginPage.isSuccessMessageDisplayed());
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}