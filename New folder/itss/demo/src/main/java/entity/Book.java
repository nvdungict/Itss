package entity;

public class Book extends Product {
    private String author;
    private String coverType;
    private String publisher;
    private String publishDate;

    public Book() {}

    @Override
    public String getType() {
        return "Book";
    }

    // Getters and setters (omitted for brevity)
    public String getAuthor() {
        return author;
    }
    public void setAuthor(String author) {
        this.author = author;
    }
    public String getCoverType() {
        return coverType;
    }
    public void setCoverType(String coverType) {
        this.coverType = coverType;
    }
    public String getPublisher() {
        return publisher;
    }
    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }
    public String getPublishDate() {
        return publishDate;
    }
    public void setPublishDate(String publishDate) {
        this.publishDate = publishDate;
    }
    
    
}
    