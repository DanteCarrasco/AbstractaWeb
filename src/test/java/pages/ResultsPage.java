package pages;
import org.openqa.selenium.*;
import java.util.*;

public class ResultsPage {

    private WebDriver driver;
    private By productSelector = By.cssSelector("a[class=\"ui-search-item__group__element shops__items-group-details ui-search-link\"]");
    private By nameSelector = By.cssSelector("h2[class=\"ui-search-item__title shops__item-title\"");
    private By priceSelector = By.cssSelector("span[class=\"andes-money-amount__fraction\"]");
    private By nextPageSelector = By.cssSelector("li[class=\"andes-pagination__button andes-pagination__button--next shops__pagination-button\"]");

    public ResultsPage(WebDriver driver) {
        this.driver = driver;
    }

    public List<Map<String, String>> getProductInfo() {
        List<WebElement> productsNames = driver.findElements(nameSelector);
        List<WebElement> productsPrices = driver.findElements(priceSelector);
        List<WebElement> productsLinks = driver.findElements(productSelector);
        List<Map<String, String>> productInfo = new ArrayList<>();

        for(int i = 0; i < productsNames.size(); i++) {
            Map<String, String> info = new HashMap<>();
            info.put("name", productsNames.get(i).getText());
            info.put("price", productsPrices.get(i).getText());
            info.put("link", productsLinks.get(i).getAttribute("href"));
            productInfo.add(info);
        }
        return productInfo;
    }

    public void goToNextPage() {
        driver.findElement(nextPageSelector).click();
    }

}
