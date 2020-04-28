package springmvc;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;

import java.util.Arrays;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = {"classpath:spring-mvc.xml"})
public class MvcTest {

    @Autowired
    WebApplicationContext webApplicationContext;

    MockMvc mockMvc;

    @Before
    public void initMockMvc(){
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void testRequest() throws Exception {
        String[] beanDefinitionNames = webApplicationContext.getBeanDefinitionNames();
        for(String name:beanDefinitionNames){
            System.out.println(name);
        }
        System.out.println("-----");
        String[] adapeter = webApplicationContext.getBeanNamesForType(RequestMappingHandlerAdapter.class);
        System.out.println(Arrays.toString(adapeter));

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/test/format").param("phoneNumber", "010-12345678")).andReturn();
        System.out.println(result.getResponse().getContentAsString());
    }

    @Test
    public void testValid() throws Exception {
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/validator").param("username", "wang").param("password","123456")).andReturn();
        System.out.println(result.getResponse().getContentAsString());
    }
}
