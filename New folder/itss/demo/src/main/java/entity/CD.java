package entity;

public class CD extends Product {
    private String artist;
    private String recordLabel;
    private String trackList;
    private String genre;
    

    public CD() {}

    @Override
    public String getType() {
        return "CD";
    }

    // Getters and setters
    public String getArtist() {
        return artist;
    }
    public void setArtist(String artist) {
        this.artist = artist;
    }
    public String getRecordLabel() {
        return recordLabel;
    }
    public void setRecordLabel(String recordLabel) {
        this.recordLabel = recordLabel;
    }
    public String getTrackList() {
        return trackList;
    }
    public void setTrackList(String trackList) {
        this.trackList = trackList;
    }
    public String getGenre() {
        return genre;
    }
    public void setGenre(String genre) {
        this.genre = genre;
    }
    
}
