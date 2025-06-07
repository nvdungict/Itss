package entity;

public class LPRecord extends Product {
    private String artist;
    private String recordLabel;
    private String trackList;
    private String genre;

    public LPRecord() {}

    @Override
    public String getType() {
        return "LPRecord";
    }

    // Getters and setters
}
