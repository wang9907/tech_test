package com.summer.tech.spring.annotation.config;

import com.summer.tech.spring.annotation.entity.Bird;
import com.summer.tech.spring.annotation.entity.Dog;
import com.summer.tech.spring.annotation.entity.Person;
import org.springframework.context.annotation.*;

@Configuration
@PropertySource(value="classpath:/test.properties")
@Import({Dog.class, Person.class})
public class MainConfig4 {

//	@Primary
	@Bean
	public Dog dog(){
		return new Dog("史努比");
	}
}
