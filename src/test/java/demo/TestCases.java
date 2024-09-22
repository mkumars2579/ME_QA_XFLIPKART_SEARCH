package demo;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;


// import io.github.bonigarcia.wdm.WebDriverManager;
import demo.wrappers.Wrappers;

public class TestCases {
    static ChromeDriver driver;

    /*
     * TODO: Write your tests here with testng @Test annotation. 
     * Follow `testCase01` `testCase02`... format or what is provided in instructions
     */

     
    /*
     * Do not change the provided methods unless necessary, they will help in automation and assessment
     */
    @BeforeTest
    public void startBrowser()
    {
        System.setProperty("java.util.logging.config.file", "logging.properties");

        // NOT NEEDED FOR SELENIUM MANAGER
        // WebDriverManager.chromedriver().timeout(30).setup();

        ChromeOptions options = new ChromeOptions();
        LoggingPreferences logs = new LoggingPreferences();

        logs.enable(LogType.BROWSER, Level.ALL);
        logs.enable(LogType.DRIVER, Level.ALL);
        options.setCapability("goog:loggingPrefs", logs);
        options.addArguments("--remote-allow-origins=*");

        System.setProperty(ChromeDriverService.CHROME_DRIVER_LOG_PROPERTY, "build/chromedriver.log"); 

        driver = new ChromeDriver(options);

        driver.manage().window().maximize();
    }

@Test
public static void testCase01() throws InterruptedException{
    int count=0;
driver.get("https://www.flipkart.com/");
WebElement Searchbox=driver.findElement(By.xpath("//input[@type='text']"));
Searchbox.sendKeys("Washing Machine");
Searchbox.sendKeys(Keys.ENTER);
Thread.sleep(4000);
List<WebElement> wb1=driver.findElements(By.xpath("//div[@class='XQDdHH']"));
for(WebElement Wb:wb1){
    String starvalue=Wb.getText();
    float starval=Float.parseFloat(starvalue);
    if(starval<=4){
        count++;
    }

}
System.out.println("washing machine with less than 4.2 star "+count);
}

@Test
public static void testCase02() throws InterruptedException{


driver.navigate().back();
WebDriverWait mywait=new WebDriverWait(driver,Duration.ofSeconds(5));
       boolean registerButton =mywait.until(ExpectedConditions.urlToBe("https://www.flipkart.com/"));
Thread.sleep(2000);
    WebElement Searchbox2=driver.findElement(By.xpath("//input[@type='text']"));
    Searchbox2.clear();
    Searchbox2.sendKeys("iPhone");
    Searchbox2.sendKeys(Keys.ENTER);
    Thread.sleep(4000);
    
    String path="//div[@class='UkUFwK']/span";
      List<WebElement> ls1=driver.findElements(By.xpath(path));
      for(WebElement wb1:ls1){
        String dis=wb1.getText();
        int num=dis.indexOf("%");
        String discountvalue=dis.substring(0, num).trim();
        int didcnum=Integer.parseInt(discountvalue);
        if(didcnum>17){
           String final_path=String.format("//span[normalize-space()='%s']/parent::div/parent::div/parent::div/parent::div/preceding-sibling::div/div", dis);
           Thread.sleep(1000);
           WebElement ws1=driver.findElement(By.xpath(final_path));
           String titletxt=ws1.getText();
           System.out.println(titletxt+""+dis);
        }



      }
}

@Test
public static void testCase03() throws InterruptedException{
    driver.navigate().back();
    Thread.sleep(2000);
    WebDriverWait mywait=new WebDriverWait(driver,Duration.ofSeconds(5));
       boolean registerButton =mywait.until(ExpectedConditions.urlToBe("https://www.flipkart.com/"));
    WebElement Searchbox2=driver.findElement(By.xpath("//input[@type='text']"));
    Searchbox2.clear();
    Searchbox2.sendKeys("Coffee Mug");
    Searchbox2.sendKeys(Keys.ENTER);
    Thread.sleep(3000);
    driver.findElement(By.xpath("//div [contains(normalize-space(),'4') and @class='ewzVkT _3DvUAf']")).click();
    Thread.sleep(3000);
    List<WebElement>ls3=driver.findElements(By.xpath("//span[@class='Wphh3N']"));
    ArrayList<Integer>al1=new ArrayList<>();
    for(WebElement wb1:ls3){
        String rev=wb1.getText();
      
      if(rev.contains(",")){
              rev=rev.replace(",", "");
      }
      if(rev.contains("(")){
        rev=rev.replace("(", "");
}
if(rev.contains(")")){
    rev=rev.replace(")", "");
}
    int curr_num= Integer.parseInt(rev);


al1.add(curr_num) ;}

ArrayList<String>al2=new ArrayList<>();

Collections.sort(al1);
int count=0;
for(int i=al1.size()-1;i>=0;i--){
  int temp_int=al1.get(i);
  String temp_str=String.valueOf(temp_int);
  
  String final_val=temp_str;
  StringBuilder sb1 = new StringBuilder(final_val);
  sb1.insert(1, ",");
  al2.add(sb1.toString());
  sb1.setLength(0);
    count++;
    if(count==5){
        break;
    }



}
for (String s1:al2){
    String xpath_value=String.format("//span[contains(text() ,'%s')]/parent::div/preceding-sibling::a[@class='wjcEIp']", s1);
    WebElement wb1=driver.findElement(By.xpath(xpath_value));
    String title=wb1.getText();
    String image_link=wb1.getAttribute("href");
    System.out.println("Title value"+title+" image url"+image_link +"Rating value:"+s1);
}





        
        
        


    
  
}

    @AfterTest
    public void endTest()
    {
       // driver.close();
       // driver.quit();

    }
}