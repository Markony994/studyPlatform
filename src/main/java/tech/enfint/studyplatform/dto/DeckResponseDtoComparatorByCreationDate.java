package tech.enfint.studyplatform.dto;

import java.util.Comparator;

public class DeckResponseDtoComparatorByCreationDate
        implements Comparator<DeckResponseDTO> {
    @Override
    public int compare(DeckResponseDTO o1, DeckResponseDTO o2) {
        return o1.getCreationDate().compareTo(o2.getCreationDate());
    }

    @Override
    public Comparator<DeckResponseDTO> reversed() {
        return Comparator.super.reversed();
    }
}
