package DiGraph_A5;

public class EntryPair implements EntryPair_Interface {
    public String value;
    public long priority;

    public EntryPair(String aValue, long dist) {
        value = aValue;
        priority = dist;
    }

    public String getValue() {
        return value;
    }

    public long getPriority() {
        return priority;
    }
}