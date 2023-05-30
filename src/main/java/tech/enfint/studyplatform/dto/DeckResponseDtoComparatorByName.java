package tech.enfint.studyplatform.dto;

import java.util.Comparator;

public class DeckResponseDtoComparatorByName
        implements Comparator<DeckResponseDTO> {
    @Override
    public int compare(DeckResponseDTO o1, DeckResponseDTO o2) {
        return o1.getName().compareTo(o2.getName());
    }

    @Override
    public Comparator<DeckResponseDTO> reversed() {
        return Comparator.super.reversed();
    }
}
