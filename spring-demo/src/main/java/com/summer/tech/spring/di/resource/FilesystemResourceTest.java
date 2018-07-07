package com.summer.tech.spring.di.resource;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;

public class FilesystemResourceTest  {

    /**
     * @param args
     */
    public static void main(String[] args) {

        File file = new File("d:/test.txt");  
        Resource resource = new FileSystemResource(file);  
        if(resource.exists()) {  
            dumpStream(resource);  
        }  
            
            System.out.println(resource.isOpen()); 
    }
    
    
    private static void dumpStream(Resource resource) {  
        InputStream is = null;  
        try {  
            //1.获取文件资源  
            is = resource.getInputStream();  
            //2.读取资源  
            byte[] descBytes = new byte[is.available()];  
            is.read(descBytes);  
            System.out.println(new String(descBytes));  
        } catch (IOException e) {  
            e.printStackTrace();  
        }  
        finally {  
            try {  
                //3.关闭资源  
                is.close();  
            } catch (IOException e) {  
            }  
        }  
    }

}