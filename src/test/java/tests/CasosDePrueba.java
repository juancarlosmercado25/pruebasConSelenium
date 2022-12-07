package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;


import java.util.concurrent.TimeUnit;

public class CasosDePrueba {
    //Atributos
    private WebDriver driver;
    private WebDriverWait wait;

    private JavascriptExecutor js;

    private String rutaDriver= System.getProperty("user.dir")+"\\src\\test\\resources\\drivers\\chromedriver.exe";
    private String propertyDriver = "webdriver.chrome.driver";

    @AfterMethod
    public void posCondicion(){
        driver.close();
    }

    @BeforeMethod
    public void preCondiciones(){

        System.setProperty(propertyDriver,rutaDriver);

        driver = new ChromeDriver();

        driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);

        driver.manage().timeouts().setScriptTimeout(40, TimeUnit.SECONDS);

        wait = new WebDriverWait(driver,10);

        js = (JavascriptExecutor) driver;

        driver.navigate().to("https://open.spotify.com/");

        driver.manage().window().maximize();
    }

    /*
    @Test
    public void CP003_Inicio_Sesion() throws InterruptedException {

        By localizadorBtnIniciarSesion = By.xpath("//span[contains(text(),'Iniciar sesión')]");

        WebElement btnIniciarSesion = driver.findElement(localizadorBtnIniciarSesion);

        btnIniciarSesion.click();

        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("login-username"))).sendKeys("juancarlos.mercado@tsoftglobal.com");

        driver.findElement(By.id("login-password")).sendKeys("123456");

        WebElement btnInicioSesion  = driver.findElement(By.xpath("//button[@id='login-button']"));

        //js.executeScript("arguments[0].scrollIntoView();", btnInicioSesion);

        btnInicioSesion.click();

        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[contains(text(),'Nombre de usuario o contraseña incorrectos.')]")));

        Assert.assertEquals(driver.findElement(By.xpath("//div[contains(text(),'Nombre de usuario o contraseña incorrectos.')]")).getText(),"Nombre de usuario o contraseña incorrectos.");

        Thread.sleep(10000);
    }

*/
    @Test
    public void CP004_Inicio_Sesion_con_Facebook() {

        By localizadorBtnIniciarSesion = By.xpath("//span[contains(text(),'Iniciar sesión')]");

        WebElement btnIniciarSesion = driver.findElement(localizadorBtnIniciarSesion);

        btnIniciarSesion.click();
        //---------------------------------------------------------------------------------------------------------

        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[@data-testid='facebook-login']"))).click();

        Assert.assertEquals(driver.getTitle(),"Iniciar sesión en Facebook | Facebook");

    }

    @Test
    public void CP005_Inicio_Sesion_con_Google(){

        By localizadorBtnIniciarSesion = By.xpath("//span[contains(text(),'Iniciar sesión')]");

        WebElement btnIniciarSesion = driver.findElement(localizadorBtnIniciarSesion);

        btnIniciarSesion.click();
        //---------------------------------------------------------------------------------------------------------

        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[@data-testid='google-login']"))).click();

        Assert.assertEquals(driver.getTitle(),"Inicia sesión: Cuentas de Google");

    }

    /*
    @Test
    public void CP006(){

        By localizadorBtnIniciarSesion = By.xpath("//span[contains(text(),'Iniciar sesión')]");

        WebElement btnIniciarSesion = driver.findElement(localizadorBtnIniciarSesion);

        btnIniciarSesion.click();
        //---------------------------------------------------------------------------------------------------------

        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[@data-testid='google-login']"))).click();

        Assert.assertEquals(driver.getTitle(),"Inicia sesión: Cuentas de Google");

    }

*/


/*
    @Test
    public void CP001_Registro_Fallido_Captcha_en_blanco() {

        By localizadorBtnRegistrase = By.xpath("//button[contains(text(),'Registrarte')]");

        WebElement btnRegistrarse = driver.findElement(localizadorBtnRegistrase);

        btnRegistrarse.click();

        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("email"))).sendKeys("domingo.saavedra@tsoftglobal.com");

        driver.findElement(By.name("confirm")).sendKeys("domingo.saavedra@tsoftglobal.com");

        driver.findElement(By.name("password")).sendKeys("123454321");

        driver.findElement(By.name("displayname")).sendKeys("Pobre Domingo :D");

        driver.findElement(By.id("day")).sendKeys("28");

        Select cmbMes = new Select(driver.findElement(By.id("month")));

        cmbMes.selectByValue("02");

        driver.findElement(By.name("year")).sendKeys("1991");


        driver.findElement(By.xpath("//label[@for='gender_option_male']")).click();

        driver.findElement(By.xpath("//label[@for='marketing-opt-checkbox']")).click();


        driver.findElement(By.xpath("//label[@for='third-party-checkbox']")).click();


        WebElement btnRegistro  = driver.findElement(By.xpath("//button[@type='submit']"));

        js.executeScript("arguments[0].scrollIntoView();", btnRegistro);

        btnRegistro.click();

        Assert.assertEquals(driver.findElement(By.xpath("//div[contains(text(),'Confirma que no eres un robot.')]")).getText(),"Confirma que no eres un robot.");

    }

    @Test
    public void CP002_Correo_fallido() {

        By localizadorBtnRegistrase = By.xpath("//button[contains(text(),'Registrarte')]");

        WebElement btnRegistrarse = driver.findElement(localizadorBtnRegistrase);

        btnRegistrarse.click();

        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("email"))).sendKeys("domingo.saavedra@tsoftglobal.com");

        driver.findElement(By.name("confirm")).sendKeys("domingo.saedra@tsoftglobal.com");

        driver.findElement(By.name("password")).sendKeys("123454321");

        driver.findElement(By.name("displayname")).sendKeys("Pobre Domingo :D");

        driver.findElement(By.id("day")).sendKeys("28");

        Select cmbMes = new Select(driver.findElement(By.id("month")));

        cmbMes.selectByValue("02");


        driver.findElement(By.name("year")).sendKeys("1991");

        WebElement optionMale = driver.findElement(By.xpath("//label[@for='gender_option_male']"));

        js.executeScript("arguments[0].scrollIntoView();", optionMale);


        optionMale.click();

        driver.findElement(By.xpath("//label[@for='marketing-opt-checkbox']")).click();


        driver.findElement(By.xpath("//label[@for='third-party-checkbox']")).click();

        WebElement btnRegistro  = driver.findElement(By.xpath("//button[@type='submit']"));

        js.executeScript("arguments[0].scrollIntoView();", btnRegistro);

        btnRegistro.click();

        Assert.assertEquals(driver.findElement(By.xpath("//div[contains(text(),'Las direcciones de correo')]")).getText(),"Las direcciones de correo electrónico no coinciden.");
    }

 */
}
