import common.util.GebConfigHelper
import org.openqa.selenium.Platform
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.firefox.FirefoxDriver
import org.openqa.selenium.phantomjs.PhantomJSDriver
import org.openqa.selenium.remote.DesiredCapabilities
import org.openqa.selenium.remote.RemoteWebDriver
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.ie.InternetExplorerDriver

waiting {
	timeout = 2
}

environments {
	
	chrome {
        if(GebConfigHelper.username && GebConfigHelper.apiKey) {
            DesiredCapabilities desiredCapabilities = DesiredCapabilities.chrome();
            if(GebConfigHelper.profile){
                desiredCapabilities.setCapability("name",GebConfigHelper.profile+"-ChromeTest");//Job name
            }else{
                desiredCapabilities.setCapability("name","ChromeTest");//Job name
            }
            driver = {
                new RemoteWebDriver(new URL(GebConfigHelper.automationURL), desiredCapabilities)
            }
        }else{
            driver = {new ChromeDriver()}
        }

	}
	
	firefox {
        if(GebConfigHelper.username && GebConfigHelper.apiKey) {
            DesiredCapabilities desiredCapabilities = DesiredCapabilities.firefox();

            if(GebConfigHelper.profile){
                desiredCapabilities.setCapability("name",GebConfigHelper.profile+"-FireFoxTest");//Job name
            }else{
                desiredCapabilities.setCapability("name","FireFoxTest");//Job name
            }

            driver = {
                new RemoteWebDriver(new URL(GebConfigHelper.automationURL), desiredCapabilities)
            }
        }else{
            driver = { new FirefoxDriver() }
        }
	}

    phantomJs {
        driver = { new PhantomJSDriver() }
    }

	safari {

        if(GebConfigHelper.username && GebConfigHelper.apiKey) {
            DesiredCapabilities desiredCapabilities = DesiredCapabilities.safari();
            desiredCapabilities.setVersion("9");
            desiredCapabilities.setPlatform(Platform.MAC);

            if(GebConfigHelper.profile){
                desiredCapabilities.setCapability("name",GebConfigHelper.profile+"-SafariTest");//Job name
            }else{
                desiredCapabilities.setCapability("name","SafariTest");//Job name
            }

            desiredCapabilities.setCapability("screenResolution","1024x768")

            driver = {
                new RemoteWebDriver(new URL(GebConfigHelper.automationURL), desiredCapabilities)
            }
        }else{
            driver = { new SafariDriver() }
        }
	}
	ie {
        if(GebConfigHelper.username && GebConfigHelper.apiKey) {
            DesiredCapabilities desiredCapabilities = DesiredCapabilities.internetExplorer();
            if(GebConfigHelper.profile){
                desiredCapabilities.setCapability("name",GebConfigHelper.profile+"-IETest");//Job name
            }else{
                desiredCapabilities.setCapability("name","IETest");//Job name
            }
            desiredCapabilities.setVersion("11.0")
            desiredCapabilities.setPlatform(Platform.WIN8_1);

            driver = {
                new RemoteWebDriver(new URL(GebConfigHelper.automationURL), desiredCapabilities)
            }
        }else{
            String ieDriverVersion = "2.46.0"
            String ieDriverVersionMajor = ieDriverVersion.substring(0, ieDriverVersion.lastIndexOf('.'))

            String ieDriverZipFileName = "IEDriverServer_Win32_${ieDriverVersion}.zip"

            String ieDriverDownloadFullPath = "http://selenium-release.storage.googleapis.com/${ieDriverVersionMajor}/${ieDriverZipFileName}"

            File ieDriverLocalFile = downloadDriver(ieDriverDownloadFullPath, "IEDriverServer.exe", 'zip')

            System.setProperty('webdriver.ie.driver', ieDriverLocalFile.absolutePath)
            driver = { new InternetExplorerDriver() }
        }

	}

}
private File downloadDriver(String driverDownloadFullPath, String driverFilePath, String archiveFileExtension) {
	File destinationDirectory = new File("target/drivers")
	if (!destinationDirectory.exists()) {
		destinationDirectory.mkdirs()
	}

	File driverFile = new File("${destinationDirectory.absolutePath}/${driverFilePath}")

	String localArchivePath = "target/driver.${archiveFileExtension}"

	if (!driverFile.exists()) {
		def ant = new AntBuilder()
		ant.get(src: driverDownloadFullPath, dest: localArchivePath)

		if (archiveFileExtension == "zip") {
			ant.unzip(src: localArchivePath, dest: destinationDirectory)
		} else {
			ant.untar(src: localArchivePath, dest: destinationDirectory, compression: 'bzip2')
		}

		ant.delete(file: localArchivePath)
		ant.chmod(file: driverFile, perm: '700')
	}

	return driverFile
}
// To run the tests with all browsers just run “./gradlew test”



baseUrl=GebConfigHelper.getBaseURL()
