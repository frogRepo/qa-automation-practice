package com.veevapractice.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;


// represents web page
// locators and methods to cover user actions
public class LoginPage {
    
    private WebDriver driver;
    
    // get locators using field and selenium
    private By usernameField = By.id("username");
    private By passwordField = By.id("password");
    //private By loginButton = By.cssSelector("button[type='submit']");
    private By loginButton = By.cssSelector("button.radius");
    private By flashMessage = By.id("flash");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public void navigateTo() {
        driver.get("https://the-internet.herokuapp.com/login");
    }

    public void loginAs(String username, String password) {
        driver.findElement(usernameField).sendKeys(username);
        driver.findElement(passwordField).sendKeys(password);
        driver.findElement(loginButton).click();
    }

    // try catch so if something goes wrong and we get no message no crash
    // check if the message matches the one given if logged in successfully
    public boolean isLoginSuccessful() {
    try {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOfElementLocated(flashMessage));
        String messageText = driver.findElement(flashMessage).getText();
        return messageText.contains("You logged into a secure area!");
    } catch (Exception e) {
        return false;
    }
    }
}