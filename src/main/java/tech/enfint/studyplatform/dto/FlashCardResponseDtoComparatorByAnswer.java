package tech.enfint.studyplatform.dto;

import java.util.Comparator;

public class FlashCardResponseDtoComparatorByAnswer
        implements Comparator<FlashCardResponseDTO> {
    @Override
    public int compare(FlashCardResponseDTO o1, FlashCardResponseDTO o2) {
        return o1.getAnswer().compareTo(o2.getAnswer());
    }

    @Override
    public Comparator<FlashCardResponseDTO> reversed() {
        return Comparator.super.reversed();
    }
}
