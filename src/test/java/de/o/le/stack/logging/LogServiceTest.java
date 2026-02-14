package de.o.le.stack.logging;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.util.logging.SimpleFormatter;
import java.util.logging.StreamHandler;

import static org.junit.jupiter.api.Assertions.*;

class LogServiceTest {

    private final String TEST_STRING = "This is a custom log message.";

    @Test
    public void testOutput() {

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        StreamHandler testHandler = new StreamHandler(out, new SimpleFormatter());
        LogService ls = new LogService(LogService.class.getName(), testHandler);

        ls.info(TEST_STRING);
        testHandler.flush();

        String log = out.toString();
        assertTrue(log.contains(TEST_STRING));
    }

    @Test
    public void testKillSwitch() {

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        StreamHandler testHandler = new StreamHandler(out, new SimpleFormatter());
        LogService ls = new LogService(LogService.class.getName(), testHandler);

        LogService.disableAll();
        ls.info(TEST_STRING);
        testHandler.flush();

        String log = out.toString();
        assertTrue(log.isEmpty());
    }
}
