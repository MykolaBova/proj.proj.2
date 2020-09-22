package bova.general.thrott.domain;

public class LeftRequests {
    private int leftRequests;
    private long lastRequestTime;

    public LeftRequests(int leftRequests, long lastRequestTime) {
        super();

        this.leftRequests = leftRequests;
        this.lastRequestTime = lastRequestTime;
    }

    public int getLeftRequests() {
        return this.leftRequests;
    }

    public long getLastRequestTime() {
        return this.lastRequestTime;
    }

    public void setLeftRequests(int leftRequests) {
        this.leftRequests = leftRequests;
    }

    public void setLastRequestTime(long lastRequestTime) {
        this.lastRequestTime = lastRequestTime;
    }

    @Override
    public String toString() {
        return "LeftRequests{" +
                "leftRequests=" + leftRequests +
                ", lastRequestTime=" + lastRequestTime +
                '}';
    }
}
