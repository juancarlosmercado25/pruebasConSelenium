package test;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.*;

public class CasosPrueba {

    private HomePage homePage;
    private RegisterPage registerPage;
    private LoginPage loginPage;
    private UserPage userPage;
    private FacebookPage facebookPage;
    private GooglePage googlePage;
    private SearchPage searchPage;
    private ArtistPage artistPage;
    private WebDriver driver;
    private String browser = "CHROME"; //Este valor eventualmente se vera modificado
    private String propertyDriver = "webdriver.chrome.driver";
    private String urlDriver = System.getProperty("user.dir")+"\\src\\test\\resources\\drivers\\chromedriver.exe";
    private String url = "https://www.spotify.com/";

    @BeforeMethod
    public void preparacionTests(){
        homePage = new HomePage(driver); //Se crea la page del home
        homePage.conexionBrowser(browser,propertyDriver,urlDriver); //Se conecta el driver de chrome
        registerPage = new RegisterPage(homePage.getDriver()); //se crea la page de registro
        //--------------------------------------------------------------------------------------------------
        loginPage= new LoginPage(homePage.getDriver());
        userPage= new UserPage(homePage.getDriver());
        facebookPage= new FacebookPage(homePage.getDriver());
        googlePage= new GooglePage(homePage.getDriver());
        searchPage= new SearchPage(homePage.getDriver());
        artistPage= new ArtistPage(homePage.getDriver());



        homePage.cargarPagina(url);
        homePage.maximizarBrowser();
    }

    @AfterMethod
    public void posTests(){
        registerPage.cerrarBrowser();
    }

    @Test
    public void CP001_Inicio_Sesion_correcto(){
        homePage.irAIniciarSession();
        loginPage.iniciarSession("mercadojuanc25@gmail.com", "Nosomosnada25.");
        Assert.assertEquals("JuanMercado25",userPage.obtenerUsuario());
    }

    @Test
    public void CP002_Inicio_Sesion_contrasena_Incorreta(){
        homePage.irAIniciarSession();
        loginPage.iniciarSession("mercadojuanc25@gmail.com", "123456a.");
        Assert.assertEquals("Nombre de usuario o contraseña incorrectos.",loginPage.mensajeContrasenaUsuarioFallido());
    }

    @Test
    public void CP003_abrir_portal_facebook_incio_session() {
        homePage.irAIniciarSession();
        loginPage.irAFacebookSession();
        Assert.assertEquals("Iniciar sesión en Facebook | Facebook", facebookPage.obtenertituloPagina());
    }

    @Test
    public void CP004_abrir_portal_google_incio_session() {
        homePage.irAIniciarSession();
        loginPage.irAGoogleSession();
        Assert.assertEquals("Inicia sesión: Cuentas de Google", googlePage.obtenertituloPagina());
    }

    @Test
    public void CP005_Buscar_artista_registrado() {
        String artista= "David Bowie";
        homePage.irABuscar();
        searchPage.buscar(artista);
        searchPage.irAResutladoPrincipalBusqueda();
        Assert.assertEquals(artista,artistPage.obtenerArtista());
    }
}
