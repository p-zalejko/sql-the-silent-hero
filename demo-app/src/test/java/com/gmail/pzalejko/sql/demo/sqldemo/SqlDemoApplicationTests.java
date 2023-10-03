package com.gmail.pzalejko.sql.demo.sqldemo;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

@SpringBootTest
@Import(TestContainersConfiguration.class)
class SqlDemoApplicationTests {

	@Test
	void contextLoads() {
	}

}
