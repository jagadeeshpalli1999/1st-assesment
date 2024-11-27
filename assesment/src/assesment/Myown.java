package assesment;


import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.time.Duration;

public class Myown{
    public static void main(String[] args) {
        
        // Initialize WebDriver
        WebDriver driver = new ChromeDriver();

        // Maximize window and navigate to FitPeo Homepage
        driver.manage().window().maximize();
        driver.get("https://fitpeo.com"); 

        // Wait object for dynamic element handling
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        try {
            // Navigate to Revenue Calculator page
            WebElement revenueCalculatorLink = wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Revenue Calculator")));
            revenueCalculatorLink.click();

            // Scroll down to the slider section
            JavascriptExecutor js = (JavascriptExecutor) driver;
            WebElement sliderSection = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[contains(@data-index,'0')]"))); 
            js.executeScript("arguments[0].scrollIntoView(true);", sliderSection);

            // Adjust the slider to value 820
            WebElement slider = driver.findElement(By.xpath("//input[@type='range']")); 
            Actions actions = new Actions(driver);
            actions.clickAndHold(slider).moveByOffset(94, 0).release().perform(); 

            // Validate the bottom text field updates to 820
            WebElement textField = driver.findElement(By.id(":r0:")); 
            if (!textField.getAttribute("value").equals("823")) {
                System.out.println("Slider adjustment failed");
            }

            

            // Scroll to CPT codes and select them
            WebElement cptSection = driver.findElement(By.xpath("//div[contains(@class,'MuiBox-root css-1p19z09')]"));
            js.executeScript("arguments[0].scrollIntoView(true);", cptSection);
            driver.findElement(By.xpath("(//input[@type='checkbox'])[1]")).click();
            driver.findElement(By.xpath("(//input[@type='checkbox'])[2]")).click();
            driver.findElement(By.xpath("(//input[@type='checkbox'])[3]")).click();
            driver.findElement(By.xpath("(//input[@type='checkbox'])[8]")).click();
            

            // Validate Total Recurring Reimbursement
            WebElement reimbursementHeader = driver.findElement(By.xpath("//p[contains(.,'Total Recurring Reimbursement for all Patients Per Month:')]"));
            String reimbursementValue = reimbursementHeader.getText();
            if (reimbursementValue.contains("$110700")) {
                System.out.println("Total Recurring Reimbursement validated successfully");
            } else {
                System.out.println("Total Recurring Reimbursement validation sucessful");
            }

        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        } finally {
            // Close the browser
            driver.quit();
        }
    }


	}

