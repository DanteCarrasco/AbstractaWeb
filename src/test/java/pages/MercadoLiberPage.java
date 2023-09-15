package pages;
import org.openqa.selenium.*;

public class MercadoLiberPage {

    private WebDriver driver;
    private By searchBoxSelector = By.name("as_word");
    private By cookiesSelector = By.xpath("//button[text()=\"Aceptar cookies\"]");

    public MercadoLiberPage(WebDriver driver) {
        this.driver = driver;
    }

    public void search(String input) {
        driver.findElement(searchBoxSelector).sendKeys(input + Keys.RETURN);
    }

    public void acceptCookies() {
        driver.findElement(cookiesSelector).click();
    }
}
