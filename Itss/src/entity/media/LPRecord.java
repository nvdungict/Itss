package entity.media;

import entity.media.Product;

public class LPRecord extends Product {
    private String artist;
    private String recordLabel;
    private String genre;

    // Constructor
    public LPRecord(int productId, String productName, String category, double price, int stock,
                    String artist, String recordLabel, String genre) {
        super(productId, productName, category, price, stock); // Call superclass constructor
        this.artist = artist;
        this.recordLabel = recordLabel;
        this.genre = genre;
    }

    // Getter and Setter for LPRecord-specific attributes
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

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    @Override
    public String toString() {
        return super.toString() + ", Artist: " + artist + ", Record Label: " + recordLabel + ", Genre: " + genre;
    }
}
