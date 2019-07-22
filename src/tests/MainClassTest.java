package tests;

import org.junit.Assert;
import org.junit.Test;

public class MainClassTest extends MainClass{

    @Test
    public void MainClassTest() {
        Assert.assertTrue("getLocalNumber method gave not 14" , this.getLocalNumber() == 14);
    }

    @Test
    public void testGetClassNumber() {
        Assert.assertTrue("getClassNumber method gave less than 45",this.getClassNumber() > 45);
    }

    @Test
    public void testGetClassString() {
        Assert.assertTrue("getClassString method didn't give 'Hello' or 'hello' string",
                this.getClassString().contains("Hello") || this.getClassString().contains("hello"));
    }
}


