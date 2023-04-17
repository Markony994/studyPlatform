package tech.enfint.studyplatform.dto;

import jakarta.validation.constraints.Pattern;

public class FlashCardRequestDTO
{
    @Pattern(regexp = "^[A-Z]{1}([a-z\\d]+(\\s|,\\s){1}[a-z\\d]+){1,498}\\?$",
            message = "Question must start with uppercase letter\n" +
                    "Question must end with question mark" +
                    "Maximum characters allowed is 500")
    private String question;

    @Pattern(regexp = "^[A-Z]{1}([a-z\\d]+(\\s|,\\s){1}[a-z\\d]+){1,498}(\\.|\\!)$",
            message = "Answer must start with uppercase letter\n" +
                    "Answer must end with dot or exclamation mark" +
                    "Maximum characters allowed is 500")
    private String answer;

    public String getQuestion() {
        return question;
    }

    public String getAnswer() {
        return answer;
    }
}
