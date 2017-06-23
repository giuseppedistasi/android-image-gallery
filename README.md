# android-image-gallery

# Features

- A smart, fast and easy to use image gallery for android 

- Display image gallery with a set of features like thumbnails, pinch-to-zoom, fullscreen, tap-to-zoom

- Loads picture from assets or from web

# Usage

Edit Manifest adding this line

    <uses-permission android:name="android.permission.INTERNET" />
  
Edit Build.gradle adding the following dependencies

    compile 'com.github.bumptech.glide:glide:3.7.0'
    compile 'com.davemorrissey.labs:subsampling-scale-image-view:3.6.0'
    compile 'com.android.support:recyclerview-v7:25.2.0'
    compile 'com.squareup.picasso:picasso:2.5.2'
    compile 'com.android.support:design:25.2.0'
    compile 'com.android.support:appcompat-v7:25.3.1'
    
Add the following files to your project

- content of package com.gds.androidimagegallery in your project
- activity_image_gallery.xml
- pager_gallery_item.xml
- thumb.xml

# Load local images from asset folder (subfolder "gallery")

          Intent i = new Intent(MainActivity.this,ShowMagazineActivity.class);

          GalleryConfig galleryConfig = new GalleryConfig.GalleryConfigBuilder()
                    .title(getString(R.string.gallery))
                    .folder("gallery")
                    .build();

           i.putExtra(CONFIG, galleryConfig);
           startActivity(i);
                
                
                
  # Load remote images from resource file
  
  images.xml
 
         <resources>
            <string-array name="images_array">
               <item>https://processing.org/tutorials/pixels/imgs/tint1.jpg</item>
               <item>http://www.androidmaster.info/images/dhiraj.jpg</item>
               <item>http://www.android-examples.com/wp-content/uploads/2016/01/image_view_check.png</item>
               <item>https://cdn-images-1.medium.com/max/1600/1*QUO-bbhxtocaU0XC_dap_Q.png</item>
               <item>https://lh6.ggpht.com/OTyrezY1SaGSeusi0E85V05fl5Td6i9xEBKBGyGCCMfCOK7T6CUveuIvxcJykf8H4zA=w300</item>
               <item>http://ecx.images-amazon.com/images/I/516LOQ1JOhL._SY300_QL70_.jpg</item>
               <item>https://i.kinja-img.com/gawker-media/image/upload/s--aai-ucu9--/xqca8m8uizcp653gvdzp.png</item>
            </string-array>
         </resources>
  
  code
         
         Intent i = new Intent(MainActivity.this,ShowMagazineActivity.class);

         GalleryConfig galleryConfig = new GalleryConfig.GalleryConfigBuilder()
                    .title(getString(R.string.gallery))
                    .resourceFile(R.array.images_array)
                    .build();

          i.putExtra(CONFIG, galleryConfig);
          startActivity(i);
                
                
   
  
  # Load remote images from a List
  
           String a = "https://processing.org/tutorials/pixels/imgs/tint1.jpg";
           String b = "http://www.androidmaster.info/images/dhiraj.jpg";
           String c = "http://www.android-examples.com/wp-content/uploads/2016/01/image_view_check.png";
           String d = "https://cdn-images-1.medium.com/max/1600/1*QUO-bbhxtocaU0XC_dap_Q.png";
           String e = "https://lh6.ggpht.com/OTyrezY1SaGSeusi0E85V05fl5Td6i9xEBKBGyGCCMfCOK7T6CUveuIvxcJykf8H4zA=w300";
           String f = "http://ecx.images-amazon.com/images/I/516LOQ1JOhL._SY300_QL70_.jpg";
           String g = "https://i.kinja-img.com/gawker-media/image/upload/s--aai-ucu9--/xqca8m8uizcp653gvdzp.png";

           ArrayList<String> list = new ArrayList<>(Arrays.asList(a,b,c,d,e,f,g));

           Intent i = new Intent(MainActivity.this,ShowMagazineActivity.class);

           GalleryConfig galleryConfig = new GalleryConfig.GalleryConfigBuilder()
                    .title(getString(R.string.gallery))
                    .urls(list)
                    .build();

            i.putExtra(CONFIG, galleryConfig);
            startActivity(i);
  
