package tech.enfint.studyplatform.dto;

import java.util.Comparator;

public class FlashCardResponseDtoComparatorByStatus
        implements Comparator<FlashCardResponseDTO> {
    @Override
    public int compare(FlashCardResponseDTO o1, FlashCardResponseDTO o2) {
        return o1.getStatus().compareTo(o2.getStatus());
    }

    @Override
    public Comparator<FlashCardResponseDTO> reversed() {
        return Comparator.super.reversed();
    }
}
