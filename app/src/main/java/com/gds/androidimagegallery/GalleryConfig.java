package com.gds.androidimagegallery;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by giuseppedistasi on 23/06/17.
 */

public class GalleryConfig implements Serializable{

    enum Type{
        ASSET,WEB
    }

    private Type type;
    private String folder;
    private ArrayList<String> urls;
    private String title;
    private int resourceFile;

    public GalleryConfig(GalleryConfigBuilder builder){

        this.folder = builder.folder;
        this.urls = builder.urls;
        this.title = builder.title;
        this.resourceFile = builder.resourceFile;

        if(this.folder!= null){
            this.type = Type.ASSET;
        }
        else if(this.urls != null || this.resourceFile != 0){
            this.type = Type.WEB;
        }
    }

    public Type getType() {
        return type;
    }

    public String getFolder() {
        return folder;
    }

    public ArrayList<String> getUrls() {
        return urls;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getResourceFile() {
        return resourceFile;
    }


    public static class GalleryConfigBuilder {

        private String folder;
        private ArrayList<String> urls;
        private String title;
        private int resourceFile;

        public GalleryConfigBuilder title(String title){
            this.title = title;
            return this;
        }

        public GalleryConfigBuilder resourceFile(int resFile){
            this.resourceFile = resFile;
            return this;
        }

        public GalleryConfigBuilder urls(ArrayList<String> urls){
            this.urls = urls;
            return this;
        }

        public GalleryConfigBuilder folder(String folder){
            this.folder = folder;
            return this;
        }

        public GalleryConfig build(){
            return new GalleryConfig(this);
        }

    }
}


