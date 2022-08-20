package test;

import model.LogRecord;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.assertEquals;


public class TestLogRecord {
    private LogRecord l ;
    @Before
    public void setUp(){
        l = new LogRecord(1,1,"ssss");
    }

    @Test
    public void testLogRecordCu(){
        assertEquals(l.getTime().toString(), new Date().toString());
    }

}
