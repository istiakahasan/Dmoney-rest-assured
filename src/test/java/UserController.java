import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import static io.restassured.RestAssured.given;

public class UserController extends Setup{
public UserController()throws IOException{
     initConfig();
}

    private void initConfig() {
    }


    public void doLogin(String email,String password) throws ConfigurationException {
        RestAssured.baseURI=prop.getProperty("baseUrl");
        UserModel model=new UserModel();
        model.setEmail(email);
        model.setPassword(password);
        Response res=given().contentType("application/json").body(model).when().post("/user/login").then().assertThat().statusCode(200).extract().response();
        System.out.println(res.asString());


        JsonPath jsonobj=res.jsonPath();
        String token=jsonobj.get("token");
        System.out.println();
        Utils.setEnvVar("token",token);
    }
    public void searchUser(){
    RestAssured.baseURI=prop.getProperty("baseUrl");
    Response res=given().contentType("appllication/json").header("Authorization",prop.getProperty("token")).when().get("/user/research/id/1");

    }
    public JsonPath createUser(UserModel model){

        RestAssured.baseURI=prop.getProperty("baseUrl");


        Response res=given().contentType("application/json").body(model)
                .header("Authorization",prop.getProperty("token"))
                .header("X-AUTH-SECREET-KEY","ROADTOSDET")
                .when().post("/user/create").then().assertThat().statusCode(201).extract().response();

        System.out.println(res.asString());
        return res.jsonPath();
    }

    public static  void setEnvVar(String key,String value) throws ConfigurationException {
        PropertiesConfiguration config=new PropertiesConfiguration("./src/test/resoources/config.properties");
        config.setProperty(key,value);
        config.save();

    }
    public JsonPath serchUser(String userId){

        RestAssured.baseURI=prop.getProperty("baseUrl");
        Response res=given().contentType("application/json").header("Authorization",prop.getProperty("token")).when().get("/user/search/id/1"+userId);
        System.out.println(res.asString());
    return res.jsonPath();
    }
   public void loadConfigData() throws IOException {
        prop = new Properties();
        file=new FileInputStream("./src/test/resoources/config.properties");
        prop.load(file);

       
   }

}
