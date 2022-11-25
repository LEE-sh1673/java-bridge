package bridge.model;

public enum CompareResult {
    MATCH, MISS;

    public boolean isMatch() {
        return this == MATCH;
    }
}
