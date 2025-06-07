package controller;

import entity.*;

public class AddProductController {

    /**
     * Add a Book product and optionally add it to the cart.
     */
    public boolean addBook(String id, String title, String category, double value, double price, int quantity,
                           String author, String coverType, String publisher, String publishDate) {

        if (!isValidPrice(price, value)) {
            System.out.println("Error: Price must be between 30% and 150% of value.");
            return false;
        }

        Book book = new Book();
        book.setId(id);
        book.setTitle(title);
        book.setCategory(category);
        book.setValue(value);
        book.setPrice(price);
        book.setQuantity(quantity);
        book.setAuthor(author);
        book.setCoverType(coverType);
        book.setPublisher(publisher);
        book.setPublishDate(publishDate);

        // Add to cart (optional, depending on context)
        CartMedia cartItem = new CartMedia(book, quantity);
        Cart.getInstance().addItem(cartItem);

        // Later: persist to database via DAO

        System.out.println("✅ Book added successfully: " + book.getTitle());
        return true;
    }

    /**
     * Add a DVD product.
     */
    public boolean addDVD(String id, String title, String category, double value, double price, int quantity,
                          String director, int runtime, String studio) {

        if (!isValidPrice(price, value)) {
            System.out.println("Error: Price must be between 30% and 150% of value.");
            return false;
        }

        DVD dvd = new DVD();
        dvd.setId(id);
        dvd.setTitle(title);
        dvd.setCategory(category);
        dvd.setValue(value);
        dvd.setPrice(price);
        dvd.setQuantity(quantity);
        dvd.setDirector(director);
        dvd.setRuntime(runtime);
        dvd.setStudio(studio);

        CartMedia cartItem = new CartMedia(dvd, quantity);
        Cart.getInstance().addItem(cartItem);

        System.out.println("✅ DVD added successfully: " + dvd.getTitle());
        return true;
    }

    /**
     * Add a CD product.
     */
    public boolean addCD(String id, String title, String category, double value, double price, int quantity,
                         String artist) {

        if (!isValidPrice(price, value)) {
            System.out.println("Error: Price must be between 30% and 150% of value.");
            return false;
        }

        CD cd = new CD();
        cd.setId(id);
        cd.setTitle(title);
        cd.setCategory(category);
        cd.setValue(value);
        cd.setPrice(price);
        cd.setQuantity(quantity);
        cd.setArtist(artist);
     

        CartMedia cartItem = new CartMedia(cd, quantity);
        Cart.getInstance().addItem(cartItem);

        System.out.println("✅ CD added successfully: " + cd.getTitle());
        return true;
    }

    /**
     * Validate the price logic.
     */
    private boolean isValidPrice(double price, double value) {
        return price >= value * 0.3 && price <= value * 1.5;
    }
}
