package com.example.phuon.intro;

public class DataRecyclerViewYoutube {
    private String imageIcon;
    private String NameList;
    private String KeyListYoutube;

    public DataRecyclerViewYoutube(String imageIcon, String nameList, String keyListYoutube) {
        this.imageIcon = imageIcon;
        NameList = nameList;
        KeyListYoutube = keyListYoutube;
    }

    public String getImageIcon() {
        return imageIcon;
    }

    public void setImageIcon(String imageIcon) {
        this.imageIcon = imageIcon;
    }

    public String getNameList() {
        return NameList;
    }

    public void setNameList(String nameList) {
        NameList = nameList;
    }

    public String getKeyListYoutube() {
        return KeyListYoutube;
    }

    public void setKeyListYoutube(String keyListYoutube) {
        KeyListYoutube = keyListYoutube;
    }
}
