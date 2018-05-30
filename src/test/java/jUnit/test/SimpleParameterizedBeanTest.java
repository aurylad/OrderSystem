package jUnit.test;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import databse.tables.Orders;

class SimpleParameterizedBeanTest {

	static AbstractApplicationContext beansContext = new ClassPathXmlApplicationContext("beans/Beans.xml");
	static Orders ordersBeanObj = (Orders) beansContext.getBean("ordersBean");

	@ParameterizedTest
	@ValueSource(strings = { "a", "b" })
	void check_if_beans_are_set_always_correct(String str) {
		ordersBeanObj.setOrder_name(str);
		assertEquals(str, ordersBeanObj.getOrder_name());
	}

}
