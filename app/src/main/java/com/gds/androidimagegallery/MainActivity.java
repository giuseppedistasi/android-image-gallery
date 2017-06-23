package com.gds.androidimagegallery;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.Arrays;

import static com.gds.androidimagegallery.ShowMagazineActivity.SOURCE;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button startGalleryBtn = (Button) findViewById(R.id.start_gallery_asset);

        startGalleryBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this,ShowMagazineActivity.class);

                GalleryConfig gallerySource = new GalleryConfig.GalleryConfigBuilder()
                        .title(getString(R.string.gallery))
                        .folder("gallery")
                        .build();

                i.putExtra(SOURCE, gallerySource);
                startActivity(i);
            }
        });

        Button startGalleryWebBtn = (Button) findViewById(R.id.start_gallery_web_xml);

        startGalleryWebBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(MainActivity.this,ShowMagazineActivity.class);

                GalleryConfig gallerySource = new GalleryConfig.GalleryConfigBuilder()
                        .title(getString(R.string.gallery))
                        .resourceFile(R.array.images_array)
                        .build();

                i.putExtra(SOURCE, gallerySource);
                startActivity(i);
            }
        });

        Button startGalleryWebBtn2 = (Button) findViewById(R.id.start_gallery_web);

        startGalleryWebBtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String a = "https://processing.org/tutorials/pixels/imgs/tint1.jpg";
                String b = "http://www.androidmaster.info/images/dhiraj.jpg";
                String c = "http://www.android-examples.com/wp-content/uploads/2016/01/image_view_check.png";
                String d = "https://cdn-images-1.medium.com/max/1600/1*QUO-bbhxtocaU0XC_dap_Q.png";
                String e = "https://lh6.ggpht.com/OTyrezY1SaGSeusi0E85V05fl5Td6i9xEBKBGyGCCMfCOK7T6CUveuIvxcJykf8H4zA=w300";
                String f = "http://ecx.images-amazon.com/images/I/516LOQ1JOhL._SY300_QL70_.jpg";
                String g = "https://i.kinja-img.com/gawker-media/image/upload/s--aai-ucu9--/xqca8m8uizcp653gvdzp.png";

                ArrayList<String> list = new ArrayList<>(Arrays.asList(a,b,c,d,e,f,g));

                Intent i = new Intent(MainActivity.this,ShowMagazineActivity.class);

                GalleryConfig gallerySource = new GalleryConfig.GalleryConfigBuilder()
                        .title(getString(R.string.gallery))
                        .urls(list)
                        .build();

                i.putExtra(SOURCE, gallerySource);
                startActivity(i);
            }
        });
    }
}
