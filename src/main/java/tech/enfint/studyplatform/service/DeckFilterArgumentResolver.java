package tech.enfint.studyplatform.service;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;
import tech.enfint.studyplatform.dto.DeckFilterDto;
import tech.enfint.studyplatform.persistence.entity.OrderDecksBy;
import tech.enfint.studyplatform.persistence.entity.OrderDirection;

import java.time.LocalDateTime;
import java.util.Optional;

public class DeckFilterArgumentResolver
        implements HandlerMethodArgumentResolver {

    @Override
    public boolean supportsParameter(MethodParameter methodParameter) {
        return methodParameter.getParameterType().equals(DeckFilterDto.class);
    }

    @Override
    public Object resolveArgument(
            MethodParameter methodParameter,
            ModelAndViewContainer modelAndViewContainer,
            NativeWebRequest nativeWebRequest,
            WebDataBinderFactory webDataBinderFactory) throws Exception {

        HttpServletRequest request
                = (HttpServletRequest) nativeWebRequest.getNativeRequest();

        String name = request.getParameter("name");
        String description = request.getParameter("description");

        DeckFilterDto.DeckFilterDtoBuilder builder = DeckFilterDto.builder();

        Optional.ofNullable(request.getParameter("creationDate"))
                .map(LocalDateTime::parse)
                .ifPresent(builder::creationDate);

        Optional.ofNullable(request.getParameter("orderDirection"))
                .map(OrderDirection::valueOf)
                .ifPresent(builder::orderDirection);

        Optional.ofNullable(request.getParameter("orderBy"))
                .map(OrderDecksBy::valueOf)
                .ifPresent(builder::orderBy);

        return builder
                .name(name)
                .description(description)
                .build();

    }
}
