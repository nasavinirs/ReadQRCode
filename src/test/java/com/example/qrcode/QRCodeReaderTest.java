package com.example.qrcode;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

/**
 * TestNG test class for QR Code Reader functionality
 */
public class QRCodeReaderTest {

    private WebDriver driver;
    private QRCodeReader qrCodeReader;

    @BeforeMethod
    public void setUp() {
        // Configure Chrome options
        ChromeOptions options = new ChromeOptions();
        // options.addArguments("--headless"); // Run in headless mode
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--disable-gpu");
        options.addArguments("--window-size=1920,1080");

        // Initialize WebDriver
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));

        // Initialize QR Code Reader
        qrCodeReader = new QRCodeReader();
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test(description = "Test reading QR code from a web page")
    public void testReadQRCodeFromWebPage() {
        try {
            // Navigate to a QR code generator website
            driver.get("https://www.qr-code-generator.com/");

            // Wait for page to load
            Thread.sleep(2000);

            // Check if we can find any QR code images on the page
            // This is a basic test that verifies the setup is working
            String pageTitle = driver.getTitle();
            Assert.assertNotNull(pageTitle, "Page title should not be null");
            Assert.assertTrue(pageTitle.contains("QR") || pageTitle.contains("qr"),
                    "Page should be related to QR codes");

            System.out.println("Successfully navigated to QR code website: " + pageTitle);

        } catch (Exception e) {
            Assert.fail("Test failed with exception: " + e.getMessage());
        }
    }

    @Test(description = "Test QR code reader initialization")
    public void testQRCodeReaderInitialization() {
        Assert.assertNotNull(qrCodeReader, "QR Code Reader should be initialized");
        System.out.println("QR Code Reader initialized successfully");
    }

    @Test(description = "Test WebDriver functionality")
    public void testWebDriverFunctionality() {
        try {
            // Test basic WebDriver functionality
            driver.get("https://www.google.com");

            String title = driver.getTitle();
            Assert.assertNotNull(title, "Page title should not be null");
            Assert.assertTrue(title.toLowerCase().contains("google"),
                    "Should navigate to Google");

            System.out.println("WebDriver is working correctly. Page title: " + title);

        } catch (Exception e) {
            Assert.fail("WebDriver test failed: " + e.getMessage());
        }
    }

    @Test(description = "Test reading QR code from file", enabled = false)
    public void testReadQRCodeFromFile() {
        // This test is disabled by default as it requires an actual QR code image file
        // To enable: create a QR code image file and update the path below
        try {
            String qrCodeContent = qrCodeReader.readQRCodeFromFile("path/to/qrcode.png");
            Assert.assertNotNull(qrCodeContent, "QR code content should not be null");
            System.out.println("QR Code Content: " + qrCodeContent);
        } catch (Exception e) {
            Assert.fail("Failed to read QR code from file: " + e.getMessage());
        }
    }
}