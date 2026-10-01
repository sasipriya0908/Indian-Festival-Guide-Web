package com.festival.selenium;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.Duration;
import java.util.Scanner;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Selenium practical for the integrated Web_Final application.
 *
 * a) Basics: launch browser, URL, title, page source, refresh, navigation, SwitchTo
 * b) Web elements: isSelected, isDisplayed, isEnabled, click, clear, sendKeys,
 *    getText, getAttribute, findElement, findElements
 * c) Locators: id, name, className, linkText, tagName, cssSelector, xpath
 *
 * Default browser: Chrome
 * Default URL: http://localhost:8080/Web_Final/index.jsp
 *
 * For multiple browsers, set:
 * -Dbrowsers=chrome,edge,firefox
 */
public class SeleniumIFGTest {

    private static final String DEFAULT_BASE_URL =
            "http://localhost:8080/Web_Final/index.jsp";

    private static final int PAUSE = 2000;
    private static final int TYPE_DELAY = 90;
    private static final String OUTPUT_DIR = "selenium-output";

    public static void main(String[] args) {
        String baseUrl = System.getProperty("baseUrl", DEFAULT_BASE_URL);
        String browser = System.getProperty("browser", "chrome");

        System.out.println("========================================");
        System.out.println(" INDIAN FESTIVAL GUIDE");
        System.out.println(" SELENIUM TESTING DEMONSTRATION");
        System.out.println("========================================");
        System.out.println("Base URL : " + baseUrl);
        System.out.println("Browser  : " + browser);
        System.out.println("========================================");

        WebDriver driver = null;
        try {
            driver = createDriver(browser);
            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
            pause();

            runBasics(driver, baseUrl);
            runElementsAndLocators(driver, baseUrl);
            runRegistrationFormDemo(driver, baseUrl);
            runSwitchToAlert(driver, baseUrl);

            System.out.println("\n========================================");
            System.out.println(" SELENIUM TEST COMPLETED SUCCESSFULLY");
            System.out.println("========================================");
            keepBrowserOpen();

        } catch (WebDriverException e) {
            System.out.println("\nBrowser/session error: " + e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println("\nTest error: " + e.getMessage());
            e.printStackTrace();
        }
        // Intentionally no driver.quit() so the browser remains visible for demonstration.
    }

    private static WebDriver createDriver(String browser) {
        return switch (browser.toLowerCase()) {
            case "chrome" -> new ChromeDriver();
            case "edge" -> new EdgeDriver();
            case "firefox" -> new FirefoxDriver();
            default -> throw new IllegalArgumentException(
                    "Unsupported browser: " + browser + ". Use chrome, edge, or firefox.");
        };
    }

    // =========================================================
    // A) BASIC SELENIUM CONCEPTS
    // =========================================================

    private static void runBasics(WebDriver driver, String baseUrl) throws IOException {
        System.out.println("\n========================================");
        System.out.println("A) BASIC SELENIUM CONCEPTS");
        System.out.println("========================================");

        System.out.println("1. Opening website...");
        driver.get(baseUrl);
        pause();

        System.out.println("2. Capturing current URL...");
        System.out.println("Current URL: " + driver.getCurrentUrl());
        pause();

        System.out.println("3. Capturing page title...");
        String title = driver.getTitle();
        System.out.println("Page Title: " + title);
        pause();

        System.out.println("4. Capturing page source...");
        Path output = Path.of(OUTPUT_DIR, safeFileName(title) + "-page-source.html");
        Files.createDirectories(output.getParent());
        Files.writeString(output, driver.getPageSource(), StandardCharsets.UTF_8);
        System.out.println("Page source saved to: " + output.toAbsolutePath());
        pause();

        System.out.println("5. Refreshing page...");
        driver.navigate().refresh();
        pause();

        System.out.println("6. Navigating to Pongal page...");
        driver.navigate().to(baseUrl.replace("index.jsp", "pongal.html"));
        System.out.println("Current page: " + driver.getCurrentUrl());
        pause();

        System.out.println("7. Navigating BACK...");
        driver.navigate().back();
        pause();

        System.out.println("8. Navigating FORWARD...");
        driver.navigate().forward();
        pause();

        System.out.println("Basic concepts completed.");
    }

    // =========================================================
    // B) WEB ELEMENTS + C) LOCATORS
    // =========================================================

    private static void runElementsAndLocators(WebDriver driver, String baseUrl) {
        System.out.println("\n========================================");
        System.out.println("B) WEB ELEMENTS");
        System.out.println("C) LOCATORS");
        System.out.println("========================================");

        System.out.println("1. Locating element using ID...");
        driver.get(baseUrl);
        pause();
        WebElement welcome = driver.findElement(By.id("welcome-text"));
        System.out.println("ID -> getText(): " + welcome.getText());
        System.out.println("ID -> isDisplayed(): " + welcome.isDisplayed());
        pause();

        System.out.println("2. Locating element using NAME...");
        driver.get(baseUrl.replace("index.jsp", "registration.jsp"));
        pause();
        WebElement fullName = driver.findElement(By.name("fullName"));
        System.out.println("NAME -> isDisplayed(): " + fullName.isDisplayed());
        slowType(fullName, "Test User");
        System.out.println("NAME -> getAttribute(value): " + fullName.getAttribute("value"));
        pause();

        System.out.println("3. Demonstrating clear()...");
        fullName.clear();
        pause();

        System.out.println("4. Locating elements using CLASS NAME...");
        driver.get(baseUrl);
        pause();
        var cards = driver.findElements(By.className("card"));
        System.out.println("CLASS_NAME -> findElements(): " + cards.size() + " festival cards found.");
        pause();

        System.out.println("5. Locating Registration link using LINK TEXT...");
        WebElement registrationLink = driver.findElement(By.linkText("🎉 Festival Registration"));
        System.out.println("LINK_TEXT -> isEnabled(): " + registrationLink.isEnabled());
        pause();
        System.out.println("6. Clicking Registration link...");
        registrationLink.click();
        pause();

        System.out.println("7. Locating elements using TAG NAME...");
        var buttons = driver.findElements(By.tagName("button"));
        System.out.println("TAG_NAME -> findElements(): " + buttons.size() + " buttons found.");
        pause();

        System.out.println("8. Locating element using CSS SELECTOR...");
        driver.get(baseUrl);
        pause();
        WebElement firstCard = driver.findElement(By.cssSelector("article.card"));
        System.out.println("CSS_SELECTOR -> getAttribute(class): " + firstCard.getAttribute("class"));
        pause();

        System.out.println("9. Locating element using XPATH...");
        WebElement firstFestivalHeading = driver.findElement(
                By.xpath("//article[contains(@class,'card')]//h2"));
        System.out.println("XPATH -> getText(): " + firstFestivalHeading.getText());
        pause();

        System.out.println("10. Testing isEnabled()...");
        WebElement resetButton = driver.findElement(By.id("reset-profile-btn"));
        System.out.println("Reset button isEnabled(): " + resetButton.isEnabled());
        pause();

        System.out.println("11. Demonstrating isSelected()...");
        driver.get(baseUrl.replace("index.jsp", "registration.jsp"));
        pause();
        WebElement festivalSelect = driver.findElement(By.name("favouriteFestival"));
        Select select = new Select(festivalSelect);
        select.selectByVisibleText("Pongal");
        pause();
        WebElement pongalOption = festivalSelect.findElement(
                By.cssSelector("option[value='Pongal']"));
        System.out.println("Pongal isSelected(): " + pongalOption.isSelected());
        pause();

        System.out.println("12. Demonstrating sendKeys() + getAttribute()...");
        WebElement email = driver.findElement(By.name("email"));
        slowType(email, "selenium@example.com");
        System.out.println("Email isDisplayed(): " + email.isDisplayed());
        System.out.println("Email value: " + email.getAttribute("value"));
        pause();

        WebElement submit = driver.findElement(By.cssSelector("button[type='submit']"));
        System.out.println("Submit isEnabled(): " + submit.isEnabled());
        pause();

        System.out.println("Web elements and locators completed.");
    }

    // =========================================================
    // REGISTRATION FORM DEMO
    // =========================================================

    private static void runRegistrationFormDemo(WebDriver driver, String baseUrl) {
        System.out.println("\n========================================");
        System.out.println("REGISTRATION FORM DEMONSTRATION");
        System.out.println("========================================");

        driver.get(baseUrl.replace("index.jsp", "registration.jsp"));
        pause();

        WebElement name = driver.findElement(By.name("fullName"));
        WebElement email = driver.findElement(By.name("email"));
        WebElement age = driver.findElement(By.name("age"));
        WebElement festival = driver.findElement(By.name("favouriteFestival"));

        System.out.println("1. Entering Full Name slowly...");
        name.clear();
        slowType(name, "Selenium Student");
        pause();

        System.out.println("2. Entering Email slowly...");
        email.clear();
        slowType(email, "student@example.com");
        pause();

        System.out.println("3. Entering Age slowly...");
        age.clear();
        slowType(age, "20");
        pause();

        System.out.println("4. Selecting Favourite Festival...");
        Select festivalSelect = new Select(festival);
        festivalSelect.selectByVisibleText("Diwali");
        pause();

        WebElement selected = festival.findElement(By.cssSelector("option[value='Diwali']"));
        System.out.println("Diwali isSelected(): " + selected.isSelected());
        pause();

        System.out.println("Entered values:");
        System.out.println("Name: " + name.getAttribute("value"));
        System.out.println("Email: " + email.getAttribute("value"));
        System.out.println("Age: " + age.getAttribute("value"));
        System.out.println("Festival: Diwali");
        pause();

        System.out.println("Registration form demonstration completed.");
    }

    // =========================================================
    // SWITCH TO / ALERT
    // =========================================================

    private static void runSwitchToAlert(WebDriver driver, String baseUrl) {
        System.out.println("\n========================================");
        System.out.println("SWITCH TO / ALERT DEMONSTRATION");
        System.out.println("========================================");

        driver.get(baseUrl);
        pause();

        WebElement resetButton = driver.findElement(By.id("reset-profile-btn"));

        System.out.println("1. Clicking Reset Profile...");
        resetButton.click();
        pause();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

        System.out.println("2. Switching to Confirm dialog...");
        Alert confirm = wait.until(ExpectedConditions.alertIsPresent());
        System.out.println("Confirm message: " + confirm.getText());
        pause();
        confirm.accept();
        pause();

        System.out.println("3. Switching to Alert dialog...");
        Alert message = wait.until(ExpectedConditions.alertIsPresent());
        System.out.println("Alert message: " + message.getText());
        pause();
        message.accept();
        pause();

        System.out.println("SwitchTo / Alert demonstration completed.");
    }

    // =========================================================
    // HELPERS
    // =========================================================

    private static void slowType(WebElement element, String text) {
        for (char c : text.toCharArray()) {
            element.sendKeys(String.valueOf(c));
            try {
                Thread.sleep(TYPE_DELAY);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }
    }

    private static void pause() {
        try {
            Thread.sleep(PAUSE);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    private static void keepBrowserOpen() {
        System.out.println("\n========================================");
        System.out.println("TEST COMPLETED.");
        System.out.println("BROWSER WILL REMAIN OPEN.");
        System.out.println("Press ENTER in Eclipse Console when your demonstration is finished.");
        System.out.println("========================================");

        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();
        System.out.println("Demonstration finished. Close the browser manually.");
    }

    private static String safeFileName(String value) {
        if (value == null || value.isBlank()) {
            return "page-source";
        }
        return value.replaceAll("[^a-zA-Z0-9._-]", "_");
    }
}
