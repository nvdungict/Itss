package entity.media;

import entity.media.Product;

public class CD extends Product {
    private String artist;
    private String recordLabel;
    private String tracklist;

    // Constructor
    public CD(int productId, String productName, String category, double price, int stock,
              String artist, String recordLabel, String tracklist) {
        super(productId, productName, category, price, stock); // Call superclass constructor
        this.artist = artist;
        this.recordLabel = recordLabel;
        this.tracklist = tracklist;
    }

    // Getter and Setter for CD-specific attributes
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

    public String getTracklist() {
        return tracklist;
    }

    public void setTracklist(String tracklist) {
        this.tracklist = tracklist;
    }

    @Override
    public String toString() {
        return super.toString() + ", Artist: " + artist + ", Record Label: " + recordLabel + ", Tracklist: " + tracklist;
    }
}
