package com.example.android.miwok;

public class Word {
    private String deafultTranslation ;
    private String miwokTranslation ;
    private int imageResourceId;
    private int musicResourceId;

    @Override
    public String toString() {
        return "Word{" +
                "deafultTranslation='" + deafultTranslation + '\'' +
                ", miwokTranslation='" + miwokTranslation + '\'' +
                ", imageResourceId=" + imageResourceId +
                ", musicResourceId=" + musicResourceId +
                '}';
    }

    public String getDeafultTranslation() {
        return deafultTranslation;
    }
    public int getImageResourceId() {
        return imageResourceId;
    }
    public int getMusicResourceId(){return musicResourceId;}
    public String getMiwokTranslation() {
        return miwokTranslation;
    }
    public Word(String defaultTranslation1, String miwok, int musicResource){
        miwokTranslation = miwok;
        deafultTranslation = defaultTranslation1;
        musicResourceId = musicResource;
    }
    public Word(String defaultTranslation1, String miwok, int imageResourceId1, int musicResource ){
        miwokTranslation = miwok;
        deafultTranslation = defaultTranslation1;
        imageResourceId = imageResourceId1;
        musicResourceId = musicResource;
    }

}
