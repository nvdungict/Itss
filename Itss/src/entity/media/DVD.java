package entity.media;

import entity.media.Product;

public class DVD extends Product {
    private String director;
    private String discType;
    private int runtime;
    private String studio;
    private String language;

    // Constructor
    public DVD(int productId, String productName, String category, double price, int stock,
               String director, String discType, int runtime, String studio, String language) {
        super(productId, productName, category, price, stock); // Call superclass constructor
        this.director = director;
        this.discType = discType;
        this.runtime = runtime;
        this.studio = studio;
        this.language = language;
    }

    // Getter and Setter for DVD-specific attributes
    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getDiscType() {
        return discType;
    }

    public void setDiscType(String discType) {
        this.discType = discType;
    }

    public int getRuntime() {
        return runtime;
    }

    public void setRuntime(int runtime) {
        this.runtime = runtime;
    }

    public String getStudio() {
        return studio;
    }

    public void setStudio(String studio) {
        this.studio = studio;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    @Override
    public String toString() {
        return super.toString() + ", Director: " + director + ", Disc Type: " + discType + ", Runtime: " + runtime;
    }
}
