package data;

public class WellKnownCoreEntry {

    private int currentPosition = 0;
    private String result = "";

    public WellKnownCoreEntry() {
    }

    public WellKnownCoreEntry(int currentPosition, String result) {
        this.currentPosition = currentPosition;
        this.result = result;
    }

    public int getCurrentPosition() {
        return currentPosition;
    }

    public void setCurrentPosition(int currentPosition) {
        this.currentPosition = currentPosition;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
