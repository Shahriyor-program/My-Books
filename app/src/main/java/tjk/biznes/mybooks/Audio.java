package tjk.biznes.mybooks;


public class Audio {

    int title;
    int descTitle;
    private int mp3;

    public Audio(int title, int descTitle, int mp3) {
        this.title = title;
        this.descTitle = descTitle;
        this.mp3 = mp3;
    }

    public int getTitle() {
        return title;
    }

    public int getDescTitle() {
        return descTitle;
    }

    public int getMp3() {
        return mp3;
    }

    public void setTitle(int title) {
        this.title = title;
    }

    public void setDescTitle(int descTitle) {
        this.descTitle = descTitle;
    }

    public void setMp3(int mp3) {
        this.mp3 = mp3;
    }
}
