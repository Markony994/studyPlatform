package tech.enfint.studyplatform.dto;

import lombok.Builder;
import lombok.Value;

import java.time.LocalDateTime;

@Value
@Builder
public class DeskFilterDto {

    String name;
    String description;

    LocalDateTime creationDate;

    String orderBy;

    //TODO User enum DESC, ASC
    String orderDirection;

}