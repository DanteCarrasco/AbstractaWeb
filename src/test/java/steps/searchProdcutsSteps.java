package steps;
import io.cucumber.java.en.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.MercadoLiberPage;
import pages.ResultsPage;
import java.io.*;
import java.time.Duration;
import java.util.*;

public class searchProdcutsSteps {
    private WebDriver driver;
    private Actions actions;
    private MercadoLiberPage MercadoLiberPage;
    private ResultsPage resultsPage;
    private WebDriverWait wait;
    private List<Map<String, String>> productInfo = new ArrayList<>();
    private final String FILE_NAME = "product_info.txt";

    @Given("^the browser is open on the main page of MercadoLibre$")
    public void openBrowser() {
        driver = new ChromeDriver();
        actions = new Actions(driver);
        driver.manage().window().maximize();
        driver.get("https://www.mercadolibre.com.pe");
        MercadoLiberPage = new MercadoLiberPage(driver);
        resultsPage = new ResultsPage(driver);
    }

    @When("^I accept all the cookies$")
    public void iAcceptAllTheCookies() {
        MercadoLiberPage.acceptCookies();
    }

    @And("I enter {string} into the search bar and press Enter")
    public void search(String input) {
        MercadoLiberPage.search(input);
    }

    @When("I navigate through the first {int} pages of results using the paginator and save info")
    public void navigatePages(int numPages) {
        for(int i = 0; i < numPages; i++) {
            wait = new WebDriverWait(driver, Duration.ofSeconds(5));
            productInfo.addAll(resultsPage.getProductInfo());
            if (i < numPages -1 ) {
                resultsPage.goToNextPage();
            }
        }
    }

    @Then("^I save this information into a text file$")
    public void saveProductInfo() {
        try (FileWriter writer = new FileWriter(FILE_NAME, false)) {
            for (Map<String, String> info : productInfo) {
                writer.write("nombre: " + info.get("name") + "\t\t\tprecio: " + info.get("price") + "\t\t\tlink: " + info.get("link") + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
