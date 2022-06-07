package com.chat360.chattingapp.Holders;

public class ConversationHolder {
    boolean hasStory,hasOnline;
    String imageUrl,message,name,time;

    public ConversationHolder(boolean hasStory, boolean hasOnline, String imageUrl, String message, String name, String time) {
        this.hasStory = hasStory;
        this.hasOnline = hasOnline;
        this.imageUrl = imageUrl;
        this.message = message;
        this.name = name;
        this.time = time;
    }

    public ConversationHolder() {
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

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
