package tjk.biznes.mybooks;

public class model {

    String name_chapter, desc_chapter, music_url;

    model(){

    }

    public model(String name_chapter, String desc_chapter, String music_url) {
        this.name_chapter = name_chapter;
        this.desc_chapter = desc_chapter;
        this.music_url = music_url;
    }

    public String getName_chapter() {
        return name_chapter;
    }

    public void setName_chapter(String name_chapter) {
        this.name_chapter = name_chapter;
    }

    public String getDesc_chapter() {
        return desc_chapter;
    }

    public void setDesc_chapter(String desc_chapter) {
        this.desc_chapter = desc_chapter;
    }

    public String getMusic_url() {
        return music_url;
    }

    public void setMusic_url(String music_url) {
        this.music_url = music_url;
    }
}
