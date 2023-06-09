package tech.enfint.studyplatform.service;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;
import tech.enfint.studyplatform.dto.CardFilterDTO;
import tech.enfint.studyplatform.persistence.entity.OrderCardsBy;
import tech.enfint.studyplatform.persistence.entity.OrderDirection;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class CardFilterArgumentResolver
        implements HandlerMethodArgumentResolver {

    @Override
    public boolean supportsParameter(MethodParameter methodParameter) {
        return methodParameter.getParameterType().equals(CardFilterDTO.class);
    }

    @Override
    public Object resolveArgument(
            MethodParameter methodParameter,
            ModelAndViewContainer modelAndViewContainer,
            NativeWebRequest nativeWebRequest,
            WebDataBinderFactory webDataBinderFactory) throws Exception {

        HttpServletRequest request
                = (HttpServletRequest) nativeWebRequest.getNativeRequest();

        List<String> words = Collections.singletonList(request.getParameter("words"));

        CardFilterDTO.CardFilterDTOBuilder builder = CardFilterDTO.builder();

        Optional.ofNullable(request.getParameter("orderDirection"))
                .map(OrderDirection::valueOf)
                .ifPresent(builder::orderDirection);

        Optional.ofNullable(request.getParameter("orderBy"))
                .map(OrderCardsBy::valueOf)
                .ifPresent(builder::orderBy);

        return builder
                .words(words)
                .build();
    }
}
