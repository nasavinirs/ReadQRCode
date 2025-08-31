package com.example.qrcode;

import com.google.zxing.BinaryBitmap;
import com.google.zxing.DecodeHintType;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.Result;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * QR Code Reader utility class that can read QR codes from images
 * and web elements using Selenium WebDriver
 */
public class QRCodeReader {

    /**
     * Reads QR code from a file
     * 
     * @param filePath Path to the image file containing QR code
     * @return Decoded QR code content
     * @throws Exception if QR code cannot be read
     */
    public String readQRCodeFromFile(String filePath) throws Exception {
        BufferedImage bufferedImage = ImageIO.read(new File(filePath));
        return decodeQRCode(bufferedImage);
    }

    /**
     * Reads QR code from a web element using Selenium
     * 
     * @param driver  WebDriver instance
     * @param element WebElement containing the QR code
     * @return Decoded QR code content
     * @throws Exception if QR code cannot be read
     */
    public String readQRCodeFromWebElement(WebDriver driver, WebElement element) throws Exception {
        // Take screenshot of the element
        byte[] screenshot = element.getScreenshotAs(OutputType.BYTES);
        BufferedImage bufferedImage = ImageIO.read(new ByteArrayInputStream(screenshot));
        return decodeQRCode(bufferedImage);
    }

    /**
     * Reads QR code from full page screenshot
     * 
     * @param driver WebDriver instance
     * @return Decoded QR code content
     * @throws Exception if QR code cannot be read
     */
    public String readQRCodeFromScreenshot(WebDriver driver) throws Exception {
        TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
        byte[] screenshot = takesScreenshot.getScreenshotAs(OutputType.BYTES);
        BufferedImage bufferedImage = ImageIO.read(new ByteArrayInputStream(screenshot));
        return decodeQRCode(bufferedImage);
    }

    /**
     * Decodes QR code from BufferedImage
     * 
     * @param bufferedImage BufferedImage containing QR code
     * @return Decoded QR code content
     * @throws Exception if QR code cannot be decoded
     */
    private String decodeQRCode(BufferedImage bufferedImage) throws Exception {
        BinaryBitmap binaryBitmap = new BinaryBitmap(
                new HybridBinarizer(
                        new BufferedImageLuminanceSource(bufferedImage)));

        Map<DecodeHintType, Object> hints = new HashMap<>();
        hints.put(DecodeHintType.CHARACTER_SET, "UTF-8");

        Result result = new MultiFormatReader().decode(binaryBitmap, hints);
        return result.getText();
    }
}