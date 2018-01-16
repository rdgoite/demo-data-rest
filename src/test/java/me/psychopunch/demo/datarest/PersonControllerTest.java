package me.psychopunch.demo.datarest;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.rest.webmvc.config.RepositoryRestMvcConfiguration;
import org.springframework.data.web.HateoasPageableHandlerMethodArgumentResolver;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@RunWith(SpringRunner.class)
@SpringBootTest
@EnableAutoConfiguration
@AutoConfigureMockMvc
public class PersonControllerTest {

    @Configuration
    @Import(RepositoryRestMvcConfiguration.class)
    static class TestConfiguration {

        @Bean
        PersonController personController() {
            return new PersonController();
        }

        @Bean
        PagedResourcesAssembler pagedResourcesAssembler() {
            return new PagedResourcesAssembler(new HateoasPageableHandlerMethodArgumentResolver(),
                    null);
        }

    }

    @Autowired
    private MockMvc webApp;

    @Test
    public void testGetPersons() throws Exception {
        //when:
        MvcResult result = webApp.perform(get("/api/people"))
                .andReturn();

        //then:
        assertThat(result.getResponse().getStatus()).isEqualTo(HttpStatus.OK.value());
    }

}