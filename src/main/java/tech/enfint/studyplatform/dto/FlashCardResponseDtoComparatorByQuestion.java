package tech.enfint.studyplatform.dto;

import java.util.Comparator;

public class FlashCardResponseDtoComparatorByQuestion
        implements Comparator<FlashCardResponseDTO>
{
    @Override
    public int compare(FlashCardResponseDTO o1, FlashCardResponseDTO o2)
    {
        return o1.getQuestion().compareTo(o2.getQuestion());
    }

    @Override
    public Comparator<FlashCardResponseDTO> reversed() {
        return Comparator.super.reversed();
    }
}
