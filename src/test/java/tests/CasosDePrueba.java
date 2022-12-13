package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
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


    @Test
    public void CP001_Inicio_Sesion_correcto(){

        By byBtnIniciarSesion = By.xpath("//span[contains(text(),'Iniciar sesión')]");
        wait.until(ExpectedConditions.presenceOfElementLocated(byBtnIniciarSesion)).click();

        By byUserName= By.xpath("//input[@id='login-username']");
        wait.until(ExpectedConditions.presenceOfElementLocated(byUserName)).sendKeys("mercadojuanc25@gmail.com");

        By byPassword= By.xpath("//input[@id='login-password']");
        wait.until(ExpectedConditions.presenceOfElementLocated(byPassword)).sendKeys("Nosomosnada25.");
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[@id='login-button']"))).click();

        By byUser= By.xpath("//span[contains(text(),'JuanMercado25')]");
        wait.until(ExpectedConditions.presenceOfElementLocated(byUser));

        Assert.assertEquals(driver.findElement(byUser).getText(),"JuanMercado25");

    }

    @Test
    public void CP002_Inicio_Sesion_fallido(){

        By byBtnIniciarSesion = By.xpath("//span[contains(text(),'Iniciar sesión')]");
        wait.until(ExpectedConditions.presenceOfElementLocated(byBtnIniciarSesion)).click();

        By byUserName= By.xpath("//input[@id='login-username']");
        wait.until(ExpectedConditions.presenceOfElementLocated(byUserName)).sendKeys("mail_fallido@gmail.com");

        By byPassword= By.xpath("//input[@id='login-password']");
        wait.until(ExpectedConditions.presenceOfElementLocated(byPassword)).sendKeys("123456");
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[@id='login-button']"))).click();

        By byMensajeAlerta= By.xpath("//span[contains(text(),'Nombre de usuario o contraseña incorrectos.')]");
        wait.until(ExpectedConditions.presenceOfElementLocated(byMensajeAlerta));

        Assert.assertEquals(driver.findElement(byMensajeAlerta).getText(),"Nombre de usuario o contraseña incorrectos.");

    }


    @Test
    public void CP003_abrir_portal_facebook_incio_session() {

        By byBtnIniciarSesion = By.xpath("//button[@data-testid='login-button']");
        wait.until(ExpectedConditions.presenceOfElementLocated(byBtnIniciarSesion)).click();

        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[@data-testid='facebook-login']"))).click();

        Assert.assertEquals(driver.getTitle(),"Iniciar sesión en Facebook | Facebook");
    }


    @Test
    public void CP004_abrir_portal_google_incio_session(){

        By byBtnIniciarSesion = By.xpath("//span[contains(text(),'Iniciar sesión')]");
        wait.until(ExpectedConditions.presenceOfElementLocated(byBtnIniciarSesion)).click();

        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[@data-testid='google-login']"))).click();

        Assert.assertEquals(driver.getTitle(),"Inicia sesión: Cuentas de Google");

    }

    @Test
    public void CP005_Buscar_artista_registrado() {

        By byBuscador= By.xpath("//span[contains(text(),'Buscar')]");
        wait.until(ExpectedConditions.presenceOfElementLocated(byBuscador)).click();

        By byTxtBuscador= By.xpath("//input[@placeholder='¿Qué te apetece escuchar?']");
        wait.until(ExpectedConditions.presenceOfElementLocated(byTxtBuscador)).sendKeys("David Bowie");

        By byArtista= By.xpath("//div[contains(text(),'David Bowie')]");
        wait.until(ExpectedConditions.presenceOfElementLocated(byArtista));

        Assert.assertEquals(driver.findElement(byArtista).getText(),"David Bowie");
    }

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
