package tech.enfint.studyplatform.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import tech.enfint.studyplatform.service.DescFilterArgumentResolver;

import java.util.List;

@Configuration
public class WebApplicationInitializer implements WebMvcConfigurer
{

    @Override
    public void addArgumentResolvers(
            List<HandlerMethodArgumentResolver> argumentResolvers) {
        argumentResolvers.add(new DescFilterArgumentResolver());
    }

}
