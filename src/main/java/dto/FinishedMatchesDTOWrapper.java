package dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@AllArgsConstructor
@Setter
public class FinishedMatchesDTOWrapper {
    private final List<FinishedMatchDTO> finishedMatchesDto;
    private final int currentPage;
    private final int totalPages;
    private String filterPlayerName;

    public FinishedMatchesDTOWrapper(List<FinishedMatchDTO> finishedMatchesDto, int currentPage, int totalPages) {
        this.finishedMatchesDto = finishedMatchesDto;
        this.currentPage = currentPage;
        this.totalPages = totalPages;
    }
}
