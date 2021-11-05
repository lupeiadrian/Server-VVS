import org.codefromscratch.httpserver.config.Configuration;
import org.codefromscratch.httpserver.config.ConfigurationManager;
import org.junit.Before;
import org.junit.Test;

import java.net.Socket;

import static org.junit.Assert.assertEquals;
public class TestConfigurationManager {
    private ConfigurationManager obtest;

    @Test
    public void TestgetCurrentConfigurationNULL(){
        obtest = null;
        assertEquals(false, obtest.getCurrentConfiguration());
    }

    @Test
    public void TestgetCurrentConfigurationACC(){

        assertEquals(true, obtest.getCurrentConfiguration());
    }
}
