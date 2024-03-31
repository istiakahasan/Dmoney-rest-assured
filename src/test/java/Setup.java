import org.testng.annotations.BeforeTest;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class  Setup {
    Properties prop;
    FileInputStream file;
    @BeforeTest
    public void setup() throws IOException {
        prop = new Properties();
        file=new FileInputStream("./src/test/resoources/config.properties");
        prop.load(file);

    }
}
