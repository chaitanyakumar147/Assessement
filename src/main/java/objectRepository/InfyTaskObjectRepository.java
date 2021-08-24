package objectRepository;
import java.time.Duration;
import java.util.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import genericutils.SeleniumLibrary;
import io.netty.handler.timeout.TimeoutException;

/**
 * 
 * @author chaitanyakumar B 
 * {@summary} : Object repository 
 *
 */
public class InfyTaskObjectRepository {

	private WebDriver driver;
	SeleniumLibrary lib;
	WebDriverWait wait;

	// initilizing the page factory class
	public InfyTaskObjectRepository(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
		lib = new SeleniumLibrary(driver);
		wait = new WebDriverWait(driver , Duration.ofSeconds(20));

	}

	// Declaring the elements 

	@FindBy(xpath = "//span[contains(text(),'Coupe')]/preceding-sibling::button")private WebElement bodyTypeTitle;
	@FindBy(xpath = "//span[text()='More details'][1]")private WebElement moreDetailedOption;
	@FindBy(xpath = "//span[text()='Contact Me']/..")private WebElement contactMeOption;
	@FindBy(xpath = "//h2[text()=' Contact details ']")private WebElement contactDetailsPageHeader;
	@FindBy(xpath = "//div[@data-test-id='rfq-contact__first-name']//wb-input-control")private WebElement firstNameTextBox;
	@FindBy(xpath = "//div[@role='dialog']//label[contains(text(),'First name')]/following-sibling::input")private WebElement firstNameTextField;
	@FindBy(xpath = "//div[@data-test-id='rfq-contact__last-name']//wb-input-control")private WebElement lastnameTextBox;
	@FindBy(xpath = "//div[@role='dialog']//label[contains(text(),'Last Name')]/following-sibling::input")private WebElement lastNameTextField;
	@FindBy(xpath = "//div[@data-test-id='rfq-contact__email']//wb-input-control")private WebElement emailIdTextBox;
	@FindBy(xpath = "//div[@role='dialog']//label[contains(text(),'E-mail Address')]/following-sibling::input")private WebElement emailIdTextField;
	@FindBy(xpath = "//div[@data-test-id='rfq-contact__phone']//wb-input-control")private WebElement mobileNumberTextBox;
	@FindBy(xpath = "//div[@role='dialog']//label[contains(text(),'Mobile Phone')]/following-sibling::input")private WebElement mobileNoTextField;
	@FindBy(xpath = "//div[@data-test-id='rfq-contact__comment']//wb-input-control")private WebElement textareaBox;
	@FindBy(xpath = "//label[text()='Comment (optional)']/following-sibling::textarea")private WebElement optionalTextArea;
	@FindBy(xpath = "//input[@data-test-id='rfq-contact__consent-data-privacy']/following-sibling::wb-icon")private WebElement privacyPolicyCheckBox;
	@FindBy(xpath = "//input[@data-test-id='rfq-contact__consent-marketing']/following::wb-icon")private WebElement marketConsentCheckBox;
	@FindBy(xpath = "//button[@data-test-id='dcp-rfq-contact-button-container__button-next']")private WebElement proceedBotton;
	@FindBy(xpath = "(//div[@id='cmm_bookie_banner']//button[text()='Agree to all'])[1]")private WebElement coockiesPermissionButton;


	// declaring business methods to call above elements 

	public void selectVehicleBasedOnBodyType() {
		lib.waitAndClick(bodyTypeTitle);
	}

	public void clickOnMoreDetails() {
		lib.waitAndClick(moreDetailedOption);
	}

	public void clickOnContactMe() {
		lib.waitForPageToLoad();
		lib.threadTimeOut(1000);
		lib.waitForElementToAppear(contactMeOption);
		lib.waitAndClick(contactMeOption);
	}
	public void getText() {

	}

	public void typeFirstname(String firstName) {

		lib.waitForPageToLoad();
		lib.waitAndClick(firstNameTextBox);
		lib.clearAndType(firstNameTextField, firstName);
	}

	public void typeLastName(String lastName) {
		lib.waitForPageToLoad();
		lib.waitAndClick(lastnameTextBox);
		lib.clearAndType(lastNameTextField, lastName);
	}

	public void typeEmailId(String emailId) {
		lib.waitForPageToLoad();
		lib.waitAndClick(emailIdTextBox);
		lib.clearAndType(emailIdTextField, emailId);
	}

	public void typeMobileNo(String mobileNumber) {
		lib.waitForPageToLoad();
		lib.waitAndClick(mobileNumberTextBox);
		lib.type(mobileNoTextField, mobileNumber);
	}

	public void typeComments(String comments) {
		lib.waitForPageToLoad();
		lib.waitAndClick(textareaBox);
		lib.clearAndType(optionalTextArea, comments);
	}

	public void selectPrivacyPolicyCheckBox() {
		lib.waitAndClick(privacyPolicyCheckBox);
	}

	public void selectMarketConsentCheckBox() {
		lib.waitAndClick(marketConsentCheckBox);
	}

	public boolean checkProceedButton() {
		return proceedBotton.isEnabled();
	}

	public void acceptTheCoockiePoliy() {
		lib.waitForPageToLoad();
		int count = 0;
		while(count<20) {
			try {
				lib.waitAndClick(coockiesPermissionButton);
				if(!coockiesPermissionButton.isDisplayed())
					break;
			}catch (TimeoutException| NoSuchElementException e) {
				lib.threadTimeOut(2000);
			}
		}

	}
}

