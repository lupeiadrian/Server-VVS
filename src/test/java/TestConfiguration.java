import org.codefromscratch.httpserver.config.Configuration;
import org.junit.Before;
import org.junit.Test;

import java.net.Socket;

import static org.junit.Assert.assertEquals;

public class TestConfiguration {
    private Configuration op;

    @Before
    public void testPortSet(){
        op.setPort(-2);
    }

    @Test
    public void testPortUnderZero() {

        assertEquals(false,op.getPort());
    }

    @Before
    public void testPortSetAboveMax(){
        op.setPort(100001);
    }

    @Test
    public void testPortAboveMax() {
        assertEquals(false,op.getPort());
    }

    @Before
    public void TestPortSetPortZero(){
        op.setPort(0);
    }

    @Test
    public void testSetPortZero() {
        assertEquals(false,op.getPort());
    }

    @Test
    public void testAcceptServerPort()
    {
        op.setPort(8080);
        assertEquals(true,op.getPort());
    }

    @Test
    public void testNotAcceptServerPort()
    {
        op.setPort(10001);
        assertEquals(false,op.getPort());
    }

    @Test
    public void testNotAcceptServerPort2()
    {
        op.setPort(-10);
        assertEquals(false,op.getPort());
    }

}
