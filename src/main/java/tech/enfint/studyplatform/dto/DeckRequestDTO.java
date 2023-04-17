package tech.enfint.studyplatform.dto;

import jakarta.validation.constraints.Pattern;

public class DeckRequestDTO
{
    @Pattern(regexp = "^[A-Z]{1}[a-z]{2,99}$",
            message = "Deck name must start with capital letter\n" +
                    "Minimum name length is 3")
    private String name;

    @Pattern(regexp = "^\\S+(\\n.*)*", message = "Description can't be empty!")
    private String description;

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }
}
