package com.summer.tech.springboot.importconfig;

import com.summer.tech.springboot.config.TestConfiguration;
import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

public class MyImportSelector implements ImportSelector {

    @Override
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {
        System.out.println("class:"+importingClassMetadata.getClassName());
        System.out.println("annotation:"+importingClassMetadata.getAnnotationTypes());
        return new String[]{TestConfiguration.class.getName()};
    }
}
