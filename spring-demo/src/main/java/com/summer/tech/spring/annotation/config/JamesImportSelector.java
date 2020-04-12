package com.summer.tech.spring.annotation.config;

import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

public class JamesImportSelector implements ImportSelector{
	@Override
	public String[] selectImports(AnnotationMetadata importingClassMetadata){
		//返回全类名的bean
		return new String[]{"com.summer.tech.spring.annotation.entity.Fish","com.summer.tech.spring.annotation.entity.Tiger"};
	}
}
