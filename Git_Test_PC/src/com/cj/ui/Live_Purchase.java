package com.cj.ui;


import java.util.regex.Pattern;
import java.awt.Dimension;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.io.File;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.junit.*;
import org.monte.media.Format;
import org.monte.media.FormatKeys.MediaType;
import org.monte.media.math.Rational;
import org.monte.screenrecorder.ScreenRecorder;

import static org.junit.Assert.*;
import static org.monte.media.FormatKeys.EncodingKey;
import static org.monte.media.FormatKeys.FrameRateKey;
import static org.monte.media.FormatKeys.KeyFrameIntervalKey;
import static org.monte.media.FormatKeys.MIME_AVI;
import static org.monte.media.FormatKeys.MediaTypeKey;
import static org.monte.media.FormatKeys.MimeTypeKey;
import static org.monte.media.VideoFormatKeys.CompressorNameKey;
import static org.monte.media.VideoFormatKeys.DepthKey;
import static org.monte.media.VideoFormatKeys.ENCODING_AVI_TECHSMITH_SCREEN_CAPTURE;
import static org.monte.media.VideoFormatKeys.QualityKey;
import static org.hamcrest.CoreMatchers.*;

import org.openqa.selenium.*;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Point;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.cj.util.SmartProperties;

public class Live_Purchase {
	private WebDriver driver;
	private String baseUrl;
	private boolean acceptNextAlert = true;
	private StringBuffer verificationErrors = new StringBuffer();

	private ScreenRecorder screenRecorder;

	private String userId = null;
	private String passwd = null;
	private String browser = null;
	private long waitTime = 50;
	private String videoPath = null;

	@Before
	public void setUp() throws Exception {

		SmartProperties sp = SmartProperties.getInstance();
		userId = sp.getProperty("ID");
		passwd = sp.getProperty("PWD");
		waitTime = Long.parseLong(sp.getProperty("WaitTime"));
		videoPath = sp.getProperty("VIDEO_LOC");
		browser = sp.getProperty("Browser");

		

  	
		if (browser.equalsIgnoreCase("firefox")){
			driver = new FirefoxDriver();
			}
			
		else {
			System.setProperty("webdriver.chrome.driver", "c:\\chromedriver.exe");
			//driver = new ChromeDriver();
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--disable-extensions");
			driver = new ChromeDriver(options);
		}

		baseUrl = "http://www.cjmall.com/";
		driver.manage().timeouts().implicitlyWait(waitTime, TimeUnit.SECONDS);

		sp.list(System.out);

		System.out.println("userId 	= " + userId);
		System.out.println("passwd 	= " + passwd);
		System.out.println("waitTime=" + waitTime);
		System.out.println("videoPath = " + videoPath);

	}

	  /**
	   * @author SeokheeLee 
	   * Date : 2016-06-22
	   * Subject : CJ Mall
	   * Assertion : PC 생방송 자동화
	   * History
	    - 2016-06-21 : 보험상품중 구매하기 버튼이 있는경우 예외처리 추가
	    - 2016-06-22 : Xpath 변경 및 불필요한 리소스 삭제
	  **/
	    
	
	@Test
	public void testLivePurchase() throws Exception {

		try {
			this.startRecording();
			
			WebElement element = null;
			WebDriverWait wait = null;

		    int window_num = 0;
		    String mainWindow = driver.getWindowHandle();
		    System.out.println("main Windows ="+mainWindow);

		    Set<String> handles = driver.getWindowHandles();
		    window_num = handles.size();

		    System.out.println("Windows Num ="+window_num);

		    for (String handle : handles) {
		    	System.out.println("windows handles :"+handle);
		    	if (!handle.equals(mainWindow)){
		    		driver.switchTo().window(handle);
		    		System.out.println("Switch Windows");
		    		break;
		    		}
		    	}

			driver.manage().window().maximize();

			// 1. Main Page 요청
			driver.get(baseUrl + "/index_tab1.jsp");

			// 2. 메인 페이지 뜨고, 로그인 객체가 뜰 때까지 기다림.
			wait = new WebDriverWait(driver, waitTime);
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(".//*[@id='cjo_wrap']/div[2]/div/div/div[2]/ul/li[1]/a/span")));

/*			// 2-1. 팝업창이 뜰경우 팝업창 다시 보지 않기 선택
			if(window_num > 0){
				Set<String> allWindows1 = driver.getWindowHandles();
				for(String curWindow1 : allWindows1){
					driver.switchTo().window(curWindow1);}
				Point hoverItem = driver.findElement(By.xpath(".//*[@id='pClose']")).getLocation();
				((JavascriptExecutor)driver).executeScript("window.scrollBy(0,"+(hoverItem.getY())+");");
				System.out.println("앞으로 이 창을 열지 않음 버튼으로 이동");
				Thread.sleep(3000);
				//앞으로 이 창을 열지 않음 버튼 클릭
				driver.findElement(By.xpath(".//*[@id='pClose']")).click();
				System.out.println("앞으로 이 창을 열지 않음 버튼 선택");
				Thread.sleep(3000);
				
				Set<String> allWindows0 = driver.getWindowHandles();
				for(String curWindow0 : allWindows0){
					driver.switchTo().window(curWindow0);}
				Thread.sleep(3000);}
			
			else{
				System.out.println("팝업창 없음");
			}
			*/
			driver.findElement(By.xpath(".//*[@id='cjo_wrap']/div[2]/div/div/div[2]/ul/li[1]/a/span")).click();

			//// 2-2. 로그인 창이 뜰 때까지 기다림. 창이 늦게 떠서 에러로 리턴된 경우가 있었음.
			wait = new WebDriverWait(driver, waitTime);
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(".//*[@id='mid_1']")));

			driver.findElement(By.xpath(".//*[@id='mid_1']")).click();
			driver.findElement(By.xpath(".//*[@id='mid_1']")).clear();
			driver.findElement(By.xpath(".//*[@id='mid_1']")).sendKeys(userId);
			driver.findElement(By.xpath(".//*[@id='mpass']")).clear();
			driver.findElement(By.xpath(".//*[@id='mpass']")).sendKeys(passwd);

			driver.findElement(By.xpath(".//*[@id='login_area']/form/div/div/div[2]/input")).click();

			//// 3. 로그인이 되고 메인화면이 뜰 때까지 기다림.
			wait = new WebDriverWait(driver, waitTime);
			wait.until(ExpectedConditions.textToBePresentInElementLocated(By.xpath(".//*[@id='cjo_wrap']/div[2]/div/div/div[2]/ul/li[1]/a/span"), "로그아웃"));

			//// 4. 상단에 생방송 화면 누르기
			driver.findElement(By.xpath(".//*[@id='cjo_wrap']/div[1]/div[1]/div/div[3]/div/a/span")).click();

			//// 5. 방송혜택 종료까지 XX:XX:XX 입니다라는 객체 나올때까지 기다림.
			wait = new WebDriverWait(driver, waitTime);
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(".//*[@id='schedule_top']/div[1]/div[1]")));

			//// 6. 방송상품보기 2개가 존재하는지 검사
			boolean isExist = existElement(driver, By.xpath(".//*[@id='schedule_top']/div[1]/div[4]/div[2]/a[2]"),
					"방송상품보기");
			if (isExist) {
				System.out.println("구매하기 및 방송상품보기 존재함");
			}
			// 방송상품보기 버튼이 없는 경우. 보험상품같은 경우는 없음.
			else {
				System.out.println("보험상품임..  여기서 종료함");
				assertTrue(true);
				this.stopRecording();
				return;
			}

			element = driver.findElement(By.xpath(".//*[@id='schedule_top']/div[1]/div[4]/div[1]/p[2]/a/span/strong"));
			if (element.getText().equals("상담신청 상품")) {
				System.out.println("상당신청 상품임.. 여기서 종료함");
				assertTrue(true);
				this.stopRecording();
				return;
			}
			// 방송상품보기 버튼이 없는 경우. 보험상품같은 경우는 없음
			else {
				System.out.println("상당신청 상품이 아님");
			}

			// 보험 상품 아님. 구매하기 선택
			driver.findElement(By.xpath(".//*[@id='schedule_top']/div[1]/div[4]/div[2]/a[1]")).click();			
			System.out.println("보험 상품 아님. 구매하기 선택");

			// '방송 종료까지...' UI 노출에 따라 path 값이 변경됨. 상세 페이지 전체를 기다리는 것으로 수정
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(".//*[@id='cjm_container']/div[1]/div[3]")));
			System.out.println("상품 상세 페이지 뜰 때까지 기다림");
		
			//// 8. "매진되었습니다"라는 메시지 있는지 먼저 검사
			isExist = existElement(driver, By.xpath(".//*[@id='isSoldOut']/div/p/img"), "매진되었습니다라는 메시지");
			if (isExist) {
				System.out.println("매진되었기 때문에 구매하기 버튼 누르지 않고 종료.");
				assertTrue(true);
				this.stopRecording();
				return;
			}

			//// 8-1. 본 상품은 상담을 통해 구매가 가능합니다 라며 상담신청 메뉴가 있는 경우가 있음. 그래서 바로구매가 없으면 바로 멈춤
			isExist = existElement(driver, By.xpath(".//*[@id='btnBuyNow']"), "바로구매");
			if (!isExist) {
				String altName = driver.findElement(By.cssSelector(".buyingWrap1>a>img")).getAttribute("alt");

				System.out.println("alt name = " + altName);
				System.out.println("바로구매 버튼 없음. 여기서 종료");
				assertTrue(true);
				this.stopRecording();
			return;
			}

			//// 9. 수량이 표시되었는지 안되어있는지 검사. 수량 표시가 업는 경우... 화면에 안보일 때 아래 항목은 오면서 Style로 구분함. <li style="display: none;" id="unit_qty_view">
			if (!(driver.findElement((By.id("unit_qty_view"))).isDisplayed())) {

				List elements = driver.findElements(By.className("detailTit"));
				System.out.println("List Size = " + elements.size());

				// 색상 및 종류 선택 상품. 자동화 할 수 없어 종료	
				isExist = existElement(driver, By.xpath(".//*[@id='colorSelect']"), "id=colorSelect");
				if (isExist) {
					System.out.println("'색상 및 종류 선택 상품' 이 옵션은 UI 자동화 불가능. 객체 이름이 상품가격과 연관이 있어 자동화 할 수 없음");
					assertTrue(true);
					this.stopRecording();
					return;
				}
				else{
					// 크기전체 선택 상품. 자동화 할 수 없어 종료	
					isExist = existElement(driver, By.xpath(".//*[@id='diff_id1']"), "id=diff_id1");
					if (isExist) {
						System.out.println("'크기전체 선택 상품' 이 옵션은 UI 자동화 불가능. 객체 이름이 상품가격과 연관이 있어 자동화 할 수 없음");
						assertTrue(true);
						this.stopRecording();
						return;
					}											
				}

			}

			// 수량 표시가 되어 있는 경우는 바로 구배하기 누르면 됨
			else {
				System.out.println("수량표시가 되어 있음");
				List elements = driver.findElements(By.className("detailTit"));
				System.out.println("List Size = " + elements.size());
			}
			
			driver.findElement(By.xpath(".//*[@id='btnBuyNow']")).click();

			//// 10. 결제하기 화면이 나올때까지 기다림
			wait = new WebDriverWait(driver, waitTime);
			wait.until(ExpectedConditions.textToBePresentInElementLocated(By.xpath(".//*[@id='ORDER_BUTTON']/button"),"결제하기"));

			assertEquals("결제하기", driver.findElement(By.xpath(".//*[@id='ORDER_BUTTON']/button")).getText());

			this.stopRecording();
		} catch (Exception e) {
			e.printStackTrace();
			assertTrue(false);

		} finally {
			this.stopRecording();
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

	private boolean isElementPresent(By by) {
		try {
			driver.findElement(by);
			return true;
		} catch (NoSuchElementException e) {
			return false;
		}
	}

	private boolean isAlertPresent() {
		try {
			driver.switchTo().alert();
			return true;
		} catch (NoAlertPresentException e) {
			return false;
		}
	}

	private String closeAlertAndGetItsText() {
		try {
			Alert alert = driver.switchTo().alert();
			String alertText = alert.getText();
			if (acceptNextAlert) {
				alert.accept();
			} else {
				alert.dismiss();
			}
			return alertText;
		} finally {
			acceptNextAlert = true;
		}
	}

	public void startRecording() throws Exception {
		File file = new File(videoPath);

		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int width = screenSize.width;
		int height = screenSize.height;

		Rectangle captureSize = new Rectangle(0, 0, width, height);

		GraphicsConfiguration gc = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice()
				.getDefaultConfiguration();

		this.screenRecorder = new SpecializedScreenRecorder(gc, captureSize,
				new Format(MediaTypeKey, MediaType.FILE, MimeTypeKey, MIME_AVI),
				new Format(MediaTypeKey, MediaType.VIDEO, EncodingKey, ENCODING_AVI_TECHSMITH_SCREEN_CAPTURE,
						CompressorNameKey, ENCODING_AVI_TECHSMITH_SCREEN_CAPTURE, DepthKey, 24, FrameRateKey,
						Rational.valueOf(15), QualityKey, 1.0f, KeyFrameIntervalKey, 15 * 60),
				new Format(MediaTypeKey, MediaType.VIDEO, EncodingKey, "black", FrameRateKey, Rational.valueOf(30)),
				null, file, "MyVideo");
		this.screenRecorder.start();

	}

	public void stopRecording() throws Exception {
		this.screenRecorder.stop();
	}

	public boolean existElement(WebDriver wd, By by, String meaning) {
		boolean ret = false;
		WebElement element = null;
		WebDriverWait wait = new WebDriverWait(wd, 2);
		// wait.ignoring(NoSuchElementException.class);

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