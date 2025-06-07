package entity;

public class DVD extends Product {
    private String discType;
    private String director;
    private int runtime;
    private String studio;
    private String language;
    private String subtitles;

    public DVD() {}

    @Override
    public String getType() {
        return "DVD";
    }

    // Getters and setters
    public String getDiscType() {
        return discType;
    }
    public void setDiscType(String discType) {
        this.discType = discType;
    }
    public String getDirector() {
        return director;
    }
    public void setDirector(String director) {
        this.director = director;
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
    public String getSubtitles() {
        return subtitles;
    }
    public void setSubtitles(String subtitles) {
        this.subtitles = subtitles;
    }
    
}
