import com.github.javafaker.Faker;
import io.restassured.path.json.JsonPath;
import org.apache.commons.configuration.ConfigurationException;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
public class TestRunner extends Setup{
    private System prop;

    @Test(priority=1)
    public void doLogin() throws ConfigurationException, IOException {
        UserController userController=new UserController();
        userController.doLogin("salman@roadtocareer.net","1234");

    }

    @Test(priority = 2)
    public void createUser( ) throws IOException {
    UserController usercontroller=new UserController();
    Faker faker=new Faker();
    String name=faker.name().fullName();
        String email=faker.name().firstName().toLowerCase()+"@test.com";
        String password="1234";
        String phone_number="01500"+Utils.generateRandom(100000,999999);
        String  nid="123456789";
        String role="Customer";
    UserModel model=new UserModel();
        model.setEmail(email);
        model.setPassword(password);
        model.setPhone_number(phone_number);
        model.setNid(nid);
        model.setRole(role);
        model.setName(name);

        JsonPath jsonPath=usercontroller.createUser(model);

        String userId=jsonPath.get("user.id");

        Utils.setEnvVar("userId",String.valueOf(userId));

    }
    @Test(priority=3 )
    public void serchUser() throws IOException {
        UserController userController=new UserController();
       JsonPath jsonObj= userController.serchUser(prop.getProperty("userId"));
        String mesageActual=jsonObj.get("message");
        String messageExpected="User found";
        Assert.assertTrue(mesageActual.contains(messageExpected));
    }
}
