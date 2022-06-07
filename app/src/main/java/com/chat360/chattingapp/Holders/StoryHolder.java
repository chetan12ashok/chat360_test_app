package com.chat360.chattingapp.Holders;

public class StoryHolder {
    boolean hasStory,hasOnline;
    String name,imageUrl;

    public StoryHolder(boolean hasStory, boolean hasOnline, String name, String imageUrl) {
        this.hasStory = hasStory;
        this.hasOnline = hasOnline;
        this.name = name;
        this.imageUrl = imageUrl;
    }

    public StoryHolder() {
    }

    public boolean isHasStory() {
        return hasStory;
    }

    public void setHasStory(boolean hasStory) {
        this.hasStory = hasStory;
    }

    public boolean isHasOnline() {
        return hasOnline;
    }

    public void setHasOnline(boolean hasOnline) {
        this.hasOnline = hasOnline;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
