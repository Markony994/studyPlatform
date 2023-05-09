package tech.enfint.studyplatform.dto;

import java.util.Comparator;

public class DeckResponseDtoComparatorByDescription
        implements Comparator<DeckResponseDTO>
{
    @Override
    public int compare(DeckResponseDTO o1, DeckResponseDTO o2)
    {
        return o1.getDescription().compareTo(o2.getDescription());
    }

    @Override
    public Comparator<DeckResponseDTO> reversed() {
        return Comparator.super.reversed();
    }
}
