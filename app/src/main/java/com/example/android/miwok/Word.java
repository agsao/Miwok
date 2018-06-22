package com.example.android.miwok;

public class Word {
    private String mMiwokTranslation, mDefaultTranslation;
    private int mImageResourceID,mAudioResourceID;

    public Word(String defaultTranslation, String miwokTranslation,int audioResourceID) {
        this.mMiwokTranslation = miwokTranslation;
        this.mDefaultTranslation = defaultTranslation;
        this.mAudioResourceID=audioResourceID;
    }

    public Word(String defaultTranslation, String miwokTranslation, int imageResourceID,int audioResourceID) {
        this(defaultTranslation, miwokTranslation,audioResourceID);
        this.mImageResourceID = imageResourceID;
    }

    public String getMiwokTranslation() {
        return mMiwokTranslation;
    }

    public String getmDefaultTranslation() {
        return mDefaultTranslation;
    }

    public int getImageResourceID() {
        return mImageResourceID;
    }

    public int getAudioResourceID() {
        return mAudioResourceID;
    }

    @Override
    public String toString() {
        return "Word{" +
                "mMiwokTranslation='" + mMiwokTranslation + '\'' +
                ", mDefaultTranslation='" + mDefaultTranslation + '\'' +
                ", mImageResourceID=" + mImageResourceID +
                ", mAudioResourceID=" + mAudioResourceID +
                '}';
    }
}
