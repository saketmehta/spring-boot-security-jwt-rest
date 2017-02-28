package com.saketmehta;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringBootSecurityDemoApplicationTests {

	@Test
	public void patternTest() {
	    String string = "hello world hi hi hi";
        Pattern pattern = Pattern.compile("hello|hi");
        Matcher matcher = pattern.matcher(string);
    }

}
