package sys.exe.co.jp.documents;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class SeleniumDemo implements ApplicationListener<ApplicationReadyEvent> {

	public WebDriver driver;
	private String baseUrl;

	@Override
	public void onApplicationEvent(ApplicationReadyEvent event) {
//		launch_test();
		launch_test2();
	}

	public void launch_test2() {

		System.setProperty("webdriver.gecko.driver",
				"D:\\02_Projects\\18_SunshineCity\\03_workspace_aws\\geckodriver.exe");
		driver = new FirefoxDriver();

		driver.get("https://sunshine-city-mockup.web.app/login");

		// Locating firstname web element
		WebElement firstNameInputbox = driver.findElement(By.id("username"));

		/***************** Way 1 ********************************/

		// Type more than 10 characters as max limit is defined as 10 as per requirement
		firstNameInputbox.sendKeys("12345678901234567890");

		// Get the typed value
		String typedValue = firstNameInputbox.getAttribute("value");

		// Get the length of typed value
		int size = typedValue.length();

		// Assert with expected
		if (size == 15) {
			System.out.println("Max character functionality is working fine.");
		}

		else {
			System.out.println("No limit is set.");
		}

		/************************ Way 2 ********************************************/

		// Clear already typed value.
		firstNameInputbox.clear();

		// Get maxlength attribute of input box
		String maxLengthDefined = firstNameInputbox.getAttribute("maxlength");

		if (maxLengthDefined == null) {
			System.out.println("No limit is set.");
		}

		else {
			if (maxLengthDefined.equals("10")) {
				System.out.println("Max character limit is set as expected.");
			}
		}

		// Closing driver
		driver.quit();
	}

	public void launch_test() {
		try {
			System.setProperty("webdriver.gecko.driver",
					"D:\\02_Projects\\18_SunshineCity\\03_workspace_aws\\geckodriver.exe");
			driver = new FirefoxDriver();
			baseUrl = "http://demo.tanmaysarkar.com/sample_03.html";
			driver.get(baseUrl);
			driver.manage().window().maximize();
			System.out.println("Opening " + baseUrl);

			// retrieve max length

			String flength = driver.findElement(By.xpath("//input[contains(@id,'ts_first_name')]"))
					.getAttribute("maxlength");
			System.out.println("First name's max length is - " + flength);

			String llength = driver.findElement(By.xpath("//input[contains(@id,'ts_last_name')]"))
					.getAttribute("maxlength");
			System.out.println("Last name's max length is - " + llength);

			String addlength = driver.findElement(By.xpath("//textarea[contains(@id,'ts_address')]"))
					.getAttribute("maxlength");
			System.out.println("Address max length is - " + addlength);

			// Read only ?
			String add = driver.findElement(By.xpath("//textarea[contains(@id,'ts_address')]"))
					.getAttribute("readonly");
			if (add == null)
				System.out.println("Address has NO such property");
			else if (add.contentEquals("true"))
				System.out.println("Address has readonly Property");
			else
				System.out.println("Address is -" + add);

			String terms = driver.findElement(By.xpath("//textarea[contains(@id,'ts_terms')]"))
					.getAttribute("readonly");
			if (terms == null)
				System.out.println("Terms has NO such property");
			else if (terms.contentEquals("true"))
				System.out.println("Terms has readonly Property");
			else
				System.out.println("Terms is -" + terms);

			driver.quit();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

}
