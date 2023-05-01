package tech.enfint.studyplatform.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import tech.enfint.studyplatform.service.CardFilterArgumentResolver;
import tech.enfint.studyplatform.service.DeckFilterArgumentResolver;

import java.util.List;

@Configuration
public class WebConfig implements WebMvcConfigurer
{

    @Override
    public void addArgumentResolvers(
            List<HandlerMethodArgumentResolver> argumentResolvers) {
        argumentResolvers.add(new DeckFilterArgumentResolver());
        argumentResolvers.add(new CardFilterArgumentResolver());
    }

}
