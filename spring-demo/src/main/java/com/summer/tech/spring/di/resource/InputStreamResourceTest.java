package com.summer.tech.spring.di.resource;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;

public class InputStreamResourceTest  {

    /**
     * @param args
     */
    public static void main(String[] args) {

           ByteArrayInputStream bis = new ByteArrayInputStream("Hello World!".getBytes());  
           Resource resource = new InputStreamResource(bis);  
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