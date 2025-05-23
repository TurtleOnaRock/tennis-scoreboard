package dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class FinishedMatchesDTOWrapper {
    private final List<FinishedMatchDTO> finishedMatchesDto;
    private final int currentPage;
    private final int totalPages;
}
