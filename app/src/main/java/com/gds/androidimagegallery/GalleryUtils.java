package com.gds.androidimagegallery;

import android.content.Context;
import android.content.res.AssetManager;
import android.content.res.Resources;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Created by giuseppedistasi on 27/04/17.
 */

public class GalleryUtils {

    public static List<File> getListFiles2(File parentDir) {
        List<File> inFiles = new ArrayList<>();
        Queue<File> files = new LinkedList<>();
        files.addAll(Arrays.asList(parentDir.listFiles()));
        while (!files.isEmpty()) {
            File file = files.remove();
            if (file.isDirectory()) {
                files.addAll(Arrays.asList(file.listFiles()));
            } else if (file.getName().endsWith(".csv")) {
                inFiles.add(file);
            }
        }
        return inFiles;
    }

    public static List<File> listFilesFromAssets(Context context, String parentDir) {

        String[] fileList;

        List<File> list = new ArrayList<File>();

        try {

            Resources res = context.getResources(); //if you are in an activity
            AssetManager am = res.getAssets();

            /**TODO mettere last path component fix*/
            fileList = am.list("gallery");


            for (int i = 0; i < fileList.length; i++) {

                File f = new File(parentDir + "/"+ fileList[i]);
                list.add(f);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return list;

    }

    public static List<File> listFiles(String parentDir) {
        File directory = new File(parentDir);
        File[] files = directory.listFiles();
        return getListFile(files);
    }

    private static List<File> getListFile(File[] files) {

        List<File> files2 = null;

        if(files != null) {

            files2 = new ArrayList<>();

            for (int i = 0; i < files.length; i++) {
                files2.add(files[i]);
            }
        }

        return files2;
    }

}
