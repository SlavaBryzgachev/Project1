import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;



public class MetroHomePage {
    private final WebDriver driver;
    private final By selectCityButton = By.className("select_metro__button");
    private final By fieldFrom = By.xpath("//*[@id=\"uniqs2090\"]");
    private final By fieldTo = By.xpath("//*[@id=\"uniqs2091\"]");
    private final By routeStationFromTo = By.className("route-details-block__terminal-station");


    // конструктор класса MetroHomePage с нужным параметром
    public MetroHomePage(WebDriver driver) {
        this.driver = driver;
    }
    // метод ожидания загрузки страницы: проверили видимость станции «Театральная»
    public void waitForLoadHomePage() {
        // подожди 8 секунд, пока появится веб-элемент с нужным текстом
        new WebDriverWait(driver, 8)
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//*[text() = 'Театральная']")));
    }

    // метод выбора города по переданному названию
    public void chooseCity(String cityName) {
        driver.findElement(selectCityButton).click();
        driver.findElement(By.xpath(String.format("//*[text()='%s']", cityName))).click();
    }

    // метод ввода названия станции в поле «Откуда»
    public void setStationFrom(String stationFrom) {
        // введи название станции в поле ввода, а затем с помощью клавиш «Вниз» и Enter выбери его в выпадающем списке саджеста
        driver.findElement(fieldFrom).sendKeys(stationFrom, Keys.DOWN, Keys.ENTER);
    }

    // метод ввода названия станции в поле «Куда»
    public void setStationTo(String stationTo) {
        // введи название станции в поле ввода, а затем с помощью клавиш «Вниз» и Enter выбери его в выпадающем списке саджеста
        driver.findElement(fieldTo).sendKeys(stationTo, Keys.DOWN, Keys.ENTER);

    }

    // метод ожидания построения маршрута: проверяется видимость кнопки «Получить ссылку на маршрут»
    public String waitForLoadRoute() {
        // подожди 3 секунды, чтобы элемент с нужным текстом стал видимым
        new WebDriverWait(driver, 3)
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//*[text() = 'Получить ссылку на маршрут']")));
        return waitForLoadRoute();
    }

    // метод построения маршрута
    public void buildRoute(String stationFrom, String stationTo) {
        // указание станции «Откуда»
        setStationFrom(stationFrom);
        // указание станции «Куда»
        setStationTo(stationTo);
        // ожидание построения маршрута
        waitForLoadRoute();
    }

    // метод получения имени станции «Откуда» для построенного маршрута
    public String getRouteStationFrom() {
        // возвращается текст первого элемента коллекции — станции «Откуда» и «Куда»
        return driver.findElements(fieldFrom).get(0).getText();
    }

    // метод получения имени станции «Куда» построенного маршрута
    public String getRouteStationTo() {
        // возвращается текст второго элемента коллекции — станции «Откуда» и «Куда»
        return driver.findElements(fieldTo).get(1).getText();
    }

    // метод получения примерного времени маршрута
    public String getApproximateRouteTime(int routeNumber) {
        // возвращается текст из требуемого по номеру элемента из коллекции времен всех маршрутов
        return driver.findElements(By.className("route-list-item__time")).get(routeNumber).getText();
    }

    // метод проверки с ожиданием видимости станции метро
    public void waitForStationVisibility( String stationName) {
        // ждем видимости элемента с нужным текстом из параметра в течение 8 секунд
        new WebDriverWait(driver, 8)
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath(String.format("//*[text()='%s']", stationName))));

    }


}
