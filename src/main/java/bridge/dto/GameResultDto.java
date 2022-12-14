package bridge.dto;

public class GameResultDto {

    private final PlayResultDto playResultDto;

    private final boolean isClear;

    private final int numberOfTries;

    public GameResultDto(final PlayResultDto playResultDto,
        final boolean isClear, final int numberOfTries) {

        this.playResultDto = playResultDto;
        this.isClear = isClear;
        this.numberOfTries = numberOfTries;
    }

    public PlayResultDto getPlayResultDto() {
        return playResultDto;
    }

    public boolean isClear() {
        return isClear;
    }

    public int getNumberOfTries() {
        return numberOfTries;
    }
}
