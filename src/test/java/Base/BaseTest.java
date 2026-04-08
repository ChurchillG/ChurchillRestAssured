package Base;
import io.restassured.RestAssured;
import org.testng.annotations.BeforeClass;
import Utils.ApiPaths;


public class BaseTest {

    @BeforeClass
    public void setup() {
        RestAssured.baseURI = "https://ndosiautomation.co.za";
        RestAssured.basePath = ApiPaths.BASE_PATH;
    }
}
