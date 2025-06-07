package entity.media;

import entity.media.Product;

public class Book extends Product {
    private String author;
    private String publisher;
    private String coverType;
    private String publicationDate;
    private int numberOfPages;

    // Constructor
    public Book(int productId, String productName, String category, double price, int stock,
                String author, String publisher, String coverType, String publicationDate, int numberOfPages) {
        super(productId, productName, category, price, stock); // Call superclass constructor
        this.author = author;
        this.publisher = publisher;
        this.coverType = coverType;
        this.publicationDate = publicationDate;
        this.numberOfPages = numberOfPages;
    }

    // Getter and Setter for Book-specific attributes
    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getCoverType() {
        return coverType;
    }

    public void setCoverType(String coverType) {
        this.coverType = coverType;
    }

    public String getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(String publicationDate) {
        this.publicationDate = publicationDate;
    }

    public int getNumberOfPages() {
        return numberOfPages;
    }

    public void setNumberOfPages(int numberOfPages) {
        this.numberOfPages = numberOfPages;
    }

    @Override
    public String toString() {
        return super.toString() + ", Author: " + author + ", Publisher: " + publisher + ", Cover Type: " + coverType;
    }
}
