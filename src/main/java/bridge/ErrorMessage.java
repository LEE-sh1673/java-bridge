package bridge;

public class ErrorMessage {

    private static final String MESSAGE_HEAD = "[ERROR] ";

    public static final String INVALID_DIRECTION_TYPE
        = MESSAGE_HEAD + "이동할 칸은 U 또는 D가 입력되어야 합니다.";

    public static final String INVALID_BRIDGE_SIZE
        = MESSAGE_HEAD + "다리의 길이는 3 ~ 20의 범위의 숫자만 입력가능합니다.";
}
