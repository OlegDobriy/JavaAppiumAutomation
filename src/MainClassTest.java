import org.junit.Assert;
import org.junit.Test;

public class MainClassTest extends MainClass{

    @Test
    public void MainClassTest() {
        Assert.assertTrue("getLocalNumber gave not 14" , this.getLocalNumber() == 14);
    }
}
