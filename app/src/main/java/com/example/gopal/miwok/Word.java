package com.example.gopal.miwok;

/**
 * Custom class (@Link Word)
 */

public class Word {
    private String mDefaultTranslation;
    private String mMiwokTranslation;
    private int mImageResourceId = -1;
    private int mSongResourceId;

    public Word(String defaultTranslation, String miwokTranslation,int songResourceId){
        mDefaultTranslation = defaultTranslation;
        mMiwokTranslation = miwokTranslation;
        mSongResourceId = songResourceId;

    }
    public Word(String defaultTranslation, String miwokTranslation, int imageResourceId, int songResourceId){
        mDefaultTranslation = defaultTranslation;
        mMiwokTranslation = miwokTranslation;
        mImageResourceId = imageResourceId;
        mSongResourceId = songResourceId;
    }

    public String getDefaultTranslation(){
        return mDefaultTranslation;
    }
    public String getMiwokTranslation(){
        return mMiwokTranslation;
    }
    public int getImageImageResourceId(){return mImageResourceId;}

    public int getmSongResourceId() {
        return mSongResourceId;
    }
}
