package bridge.view;

import bridge.dto.GameResultDto;
import bridge.dto.PlayResultDto;
import java.util.List;

/**
 * 사용자에게 게임 진행 상황과 결과를 출력하는 역할을 한다.
 */
public class OutputView {

    private static final String BRIDGE_FROM = "[ %s ]";

    private static final String BRIDGE_MIDDLE = " | ";

    public void printGameStartMessage() {
        System.out.println("다리 건너기 게임을 시작합니다.");
    }

    public void printInputBridgeSizeMessage() {
        System.out.println("\n다리의 길이를 입력해주세요.");
    }

    public void printErrorMessage(final Exception exception) {
        System.out.println(exception.getMessage());
    }

    public void printInputMovingMessage() {
        System.out.println("\n이동할 칸을 선택해주세요. (위: U, 아래: D)");
    }

    public void printRestartMessage() {
        System.out.println("\n게임을 다시 시도할지 여부를 입력해주세요. (재시도: R, 종료: Q)");
    }

    /**
     * 현재까지 이동한 다리의 상태를 정해진 형식에 맞춰 출력한다.
     * <p>
     * 출력을 위해 필요한 메서드의 인자(parameter)는 자유롭게 추가하거나 변경할 수 있다.
     */
    public void printMap(final PlayResultDto resultDto) {
        printBridgeFormat(resultDto.getUpward());
        printBridgeFormat(resultDto.getDownward());
    }
    
    private void printBridgeFormat(List<String> bridge) {
        String formatBridge = String.join(BRIDGE_MIDDLE, bridge);
        String outputBridge = String.format(BRIDGE_FROM, formatBridge);
        System.out.println(outputBridge);
    }

    /**
     * 게임의 최종 결과를 정해진 형식에 맞춰 출력한다.
     * <p>
     * 출력을 위해 필요한 메서드의 인자(parameter)는 자유롭게 추가하거나 변경할 수 있다.
     */
    public void printResult(final GameResultDto gameResultDto) {
        System.out.println("\n최종 게임 결과");
        printMap(gameResultDto.getPlayResultDto());
        System.out.println("게임 성공 여부: " + getGameClearStatus(gameResultDto.isClear()));
        System.out.println("총 시도한 횟수: " + gameResultDto.getNumberOfTries());
    }

    private String getGameClearStatus(final boolean isGameCleared) {
        if (isGameCleared) {
            return "성공";
        }
        return "실패";
    }
}
