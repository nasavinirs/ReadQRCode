# ReadQRCode - Java Maven Project

This is a Java Maven project that demonstrates how to read QR codes using Selenium WebDriver and TestNG for testing.

## Dependencies

- **Selenium WebDriver 4.35.0**: For web automation and browser interaction
- **TestNG 7.8.0**: For testing framework
- **WebDriverManager 5.5.3**: For automatic browser driver management
- **ZXing Core & JavaSE**: For QR code reading functionality

## Project Structure

```
ReadQRCode/
├── pom.xml
├── README.md
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/example/qrcode/
│   │   │       └── QRCodeReader.java
│   │   └── resources/
│   └── test/
│       ├── java/
│       │   └── com/example/qrcode/
│       │       └── QRCodeReaderTest.java
│       └── resources/
│           └── testng.xml
```

## Features

- Read QR codes from image files
- Read QR codes from web elements using Selenium
- Read QR codes from full page screenshots
- TestNG integration for comprehensive testing
- Automatic browser driver management with WebDriverManager

## Running the Tests

### Prerequisites

- Java 11 or higher
- Maven 3.6 or higher
- Chrome browser (for Selenium tests)

### Commands

1. **Compile the project:**

   ```bash
   mvn compile
   ```

2. **Run all tests:**

   ```bash
   mvn test
   ```

3. **Run tests with TestNG XML:**

   ```bash
   mvn test -DsuiteXmlFile=src/test/resources/testng.xml
   ```

4. **Run specific test class:**
   ```bash
   mvn test -Dtest=QRCodeReaderTest
   ```

## Usage Examples

### Reading QR Code from File

```java
QRCodeReader reader = new QRCodeReader();
String content = reader.readQRCodeFromFile("path/to/qrcode.png");
System.out.println("QR Code Content: " + content);
```

### Reading QR Code from Web Element

```java
WebDriver driver = new ChromeDriver();
QRCodeReader reader = new QRCodeReader();
WebElement qrElement = driver.findElement(By.id("qr-code"));
String content = reader.readQRCodeFromWebElement(driver, qrElement);
System.out.println("QR Code Content: " + content);
```

### Reading QR Code from Screenshot

```java
WebDriver driver = new ChromeDriver();
QRCodeReader reader = new QRCodeReader();
String content = reader.readQRCodeFromScreenshot(driver);
System.out.println("QR Code Content: " + content);
```

## Notes

- The tests run in headless Chrome mode by default
- WebDriverManager automatically downloads and configures the Chrome driver
- Some tests are disabled by default and can be enabled when you have actual QR code images to test with

## Contributing

Feel free to contribute to this project by submitting pull requests or reporting issues.
