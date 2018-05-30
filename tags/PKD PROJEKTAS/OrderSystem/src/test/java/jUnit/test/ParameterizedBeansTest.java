package jUnit.test;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


import databse.tables.Orders;

@RunWith(Parameterized.class)
class ParameterizedBeansTest {
	
	 static AbstractApplicationContext beansContext = new ClassPathXmlApplicationContext("beans/Beans.xml");
	 static Orders ordersBeanObj = (Orders) beansContext.getBean("ordersBean");
	
	 private String input;
		private String expectedOutput;
		
		public ParameterizedBeansTest(String input, String expectedOutput) {
			this.input = input;
			this.expectedOutput = expectedOutput;
		}

		@Parameters
		public static Collection<String[]> testConditions() {
			String expectedOutputs[][] = { 
					{ "AACD", ordersBeanObj.getOrder_name()}, 
					{ "ACD", ordersBeanObj.getOrder_name()} };
			return Arrays.asList(expectedOutputs);
		}

		@Test
		public void check_if_beans_are_set_always_correct() {
			ordersBeanObj.setOrder_name(input);
			assertEquals(expectedOutput, input);
		}
}
