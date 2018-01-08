package testcase;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class applogin {
    public static void main(String[] args) throws MalformedURLException, InterruptedException {

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(CapabilityType.BROWSER_NAME, "");//这句不是必须的
//        capabilities.setCapability("deviceName","Android Emulator");
        capabilities.setCapability("deviceName", "yzmd");
        capabilities.setCapability("platformVersion", "4.4");
        capabilities.setCapability("platformName","Android");
        capabilities.setCapability("appPackage", "com.lianchuan.yzmd");
        capabilities.setCapability("appActivity", "com.lianchuan.yzmd.activity.SplashActivity");
        AppiumDriver driver = new AppiumDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
        driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
        WebElement element = driver.findElement(By.id("com.lianchuan.yzmd:id/textView10"));
        System.out.println(element.getText().toString());
    }
}
