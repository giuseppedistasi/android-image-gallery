package com.gds.androidimagegallery;

import android.support.annotation.IdRes;

import java.io.Serializable;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by giuseppedistasi on 23/06/17.
 */

public class GallerySource implements Serializable{

    enum Type{
        ASSET,WEB
    }

    private Type type;
    private String folder;
    private ArrayList<String> urls;
    private String title;
    private int resourceFile;

    public GallerySource(String title, String folder) {
        this.title = title;
        this.type = Type.ASSET;
        this.folder = folder;
    }

    public GallerySource(String title, ArrayList<String> urls) {
        this.title = title;
        this.type = Type.WEB;
        this.urls = urls;
    }

    public GallerySource(String title, int resourceFile) {
        this.title = title;
        this.type = Type.WEB;
        this.resourceFile = resourceFile;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public String getFolder() {
        return folder;
    }

    public void setFolder(String folder) {
        this.folder = folder;
    }

    public ArrayList<String> getUrls() {
        return urls;
    }

    public void setUrls(ArrayList<String> urls) {
        this.urls = urls;
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

    public void setResourceFile(int resourceFile) {
        this.resourceFile = resourceFile;
    }
}
