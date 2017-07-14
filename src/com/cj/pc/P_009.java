package com.cj.pc;

import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.junit.Assert.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.cj.util.SmartProperties;
/**
 * 
 * @author 조성주
 * Date : 2017-06-12
 * Subject : CJ Mall 운영 
 * Name : TC_9
 * Scenario : 로그인 > 아이디 찾기 > 이름/생년월일 입력 > 확인
 * Assertion : ***** 입니다. Text 체크
 *
 */  
public class P_009 {
  private WebDriver driver;
  private StringBuffer verificationErrors = new StringBuffer();
  private String P_URL = null;
  private String NAME = null;
  private String BIRTH = null;
   
  @Before
  public void setUp() throws Exception {
    driver = new ChromeDriver();
    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    driver.manage().window().maximize();
  
    SmartProperties sp = SmartProperties.getInstance();
    P_URL = sp.getProperty("P_URL");
    NAME = sp.getProperty("NAME");
    BIRTH = sp.getProperty("BIRTH");
    //sp.list(System.out);

	// System.out.println("ID_1 	= " + ID_1);

  }

  @Test
  public void p_009() throws Exception {
	driver.get(P_URL);
	// 로그인 클릭
    driver.findElement(By.cssSelector("span.ico")).click();
    //이름,생일 입력
    driver.findElement(By.xpath(".//*[@id='findId']")).click();
    driver.findElement(By.xpath(".//*[@id='oneName']")).clear();
    driver.findElement(By.xpath(".//*[@id='oneName']")).sendKeys(NAME);
    driver.findElement(By.xpath(".//*[@id='oneBirth']")).clear();
    driver.findElement(By.xpath(".//*[@id='oneBirth']")).sendKeys(BIRTH);
    driver.findElement(By.xpath(".//*[@id='mb_button']")).click();
    System.out.println("생년월일 입력성공");
    Thread.sleep(3000);
    //얼럿창노출
    System.out.println(driver.findElement(By.xpath(".//*[@id='txt_success']")).getText());
    if ("고객님의 CJmall 아이디는\njsjjo**8입니다.".equals(driver.findElement(By.xpath(".//*[@id='txt_success']")).getText())) {
        System.out.println("TC_9 Pass");
        assertTrue(true);
        return;
     } else {
        System.out.println("TC_9 Fail");
        assertTrue(false);
     }
  }

  @After
  public void tearDown() throws Exception {
    driver.quit();
    String verificationErrorString = verificationErrors.toString();
    if (!"".equals(verificationErrorString)) {
      fail(verificationErrorString);
    }
  }

	public boolean existElement(WebDriver wd, By by, String meaning) {
		WebDriverWait wait = new WebDriverWait(wd, 2);
		try {
			wait.until(ExpectedConditions.presenceOfElementLocated(by));

		} catch (TimeoutException e) {

			System.out.println("[" + meaning + "] WebElement does not Exist. time out ");
			return false;
		}
		System.out.println("[" + meaning + "] WebElement Exist.");
		return true;
	}
}
