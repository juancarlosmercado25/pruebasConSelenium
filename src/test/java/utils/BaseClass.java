package utils;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;


//la clase base define las acciones de selenium (Wrapper)
public class BaseClass {
    protected WebDriver driver;
    protected JavascriptExecutor js;

    protected WebDriverWait wait;

    protected Select select;

    public WebDriver getDriver() {
        return driver;
    }

    public void setDriver(WebDriver driver) {
        this.driver = driver;
    }

    //Métodos
    public BaseClass(WebDriver driver) {
        this.driver = driver;
    }

    //Enmascarar las acciones(Métodos) de selenium

    public WebElement buscarElementoWeb(By localizador){
        return this.driver.findElement(localizador);
    }

    public void cargarPagina(String url){
        this.driver.get(url);
    }

    //Conectar el driver al browser
    public WebDriver conexionBrowser(String browser,String propertyDriver,String rutaDriver){
        switch (browser){
            case "CHROME":
                System.setProperty(propertyDriver,rutaDriver);
                this.driver = new ChromeDriver();
                return this.driver;
            case "EDGE":
                System.setProperty(propertyDriver,rutaDriver);
                this.driver =  new EdgeDriver();
                return this.driver;
            case "FIREFOX":
                System.setProperty(propertyDriver,rutaDriver);
                this.driver =  new FirefoxDriver();
                return this.driver;
            default:
                this.driver = null;
                return this.driver;
        }
    }

    //Scroll
    public void ScrollElementoWeb(By localizador){
        js = (JavascriptExecutor) this.driver;

        js.executeScript("arguments[0].scrollIntoView();", this.driver.findElement(localizador));
    }

    public void ScrollElementoWeb(WebElement elemento){
        js = (JavascriptExecutor) this.driver;

        js.executeScript("arguments[0].scrollIntoView();", elemento);
    }


    //Tiempos de espera

    //Espera por X segundos
    public void esperarXSegundos(int milisegundos){
        try{
            Thread.sleep(milisegundos);
        }catch (Exception ex){
            System.out.println("Fallo la espera en milisegundos definida.");
        }
    }

    //Espera en base a evento
    public WebElement esperarAElementoWeb(By localizador){
        wait = new WebDriverWait(this.driver,20);

        return wait.until(ExpectedConditions.presenceOfElementLocated(localizador));
    }

    //click
    public void click(WebElement elemento){
        elemento.click();
    }

    public void click(By localizador){
        this.driver.findElement(localizador).click();
    }

    //obtenerTexto
    public String obtenerTexto(WebElement elemento){
        return elemento.getText();
    }

    public String obtenerTexto(By localizador){
        return this.driver.findElement(localizador).getText();
    }

    //Submit
    public void submitFormulario(By localizador){
        this.driver.findElement(localizador).submit();
    }

    //agregarTexto
    public void agregarTexto(WebElement elemento, String texto){
        elemento.sendKeys(texto);
    }

    public void agregarTexto(By localizador,String texto){
        this.driver.findElement(localizador).sendKeys(texto);
    }

    //Maximizar la pagina
    public void maximizarBrowser(){
        driver.manage().window().maximize();
    }

    public void seleccionarDDlPorTexto(WebElement elemento,String texto){ //Febrero
        select = new Select(elemento);
        select.selectByVisibleText(texto);
    }

    public String obtenertituloPagina(){
        return this.driver.getTitle();
    }

    public void cerrarBrowser(){
        this.driver.quit();
    }
}
