package beans;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import databse.tables.Supplier;

@Configuration
public class BeanAnnotation {
	
	@Bean(name="supplierBean")
	@Scope("singleton")
	public Supplier supplier() {
		return new Supplier();
	}
}
