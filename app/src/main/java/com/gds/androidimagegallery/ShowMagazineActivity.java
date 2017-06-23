package com.gds.androidimagegallery;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.davemorrissey.labs.subscaleview.ImageSource;
import com.davemorrissey.labs.subscaleview.SubsamplingScaleImageView;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


public class ShowMagazineActivity extends Activity {

    private ArrayList<String> _images = new ArrayList<>();
    private ArrayList<URL> imagesUrl;
    private GalleryPagerAdapter _adapter;
    private ViewPager _pager;
    private RecyclerView _thumbnailsView;
    private RelativeLayout thmbnailsLayout;

    public static final String SOURCE = "source";
    private GallerySource dataSource;
    private final static int maxZoom = 6;
    private String title;
    private Toolbar toolbar;
    private TextView toolbarTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_gallery);

        dataSource = (GallerySource) getIntent().getSerializableExtra(SOURCE);

        toolbar = (Toolbar) findViewById(R.id.toolbar);

        toolbarTitle = (TextView) findViewById(R.id.toolbar_title);

        thmbnailsLayout = (RelativeLayout) findViewById(R.id.thmbnailsLayout);

        _thumbnailsView = (RecyclerView) findViewById(R.id.thumbnailsView);

        _pager = (ViewPager) findViewById(R.id.pager);

        title = dataSource.getTitle();

        if (title == null) {
            title = getString(R.string.gallery);
        }


        _images = new ArrayList<String>();

        toolbarTitle.setText(title);


        switch (dataSource.getType()) {
            case ASSET: {

                List<File> files = GalleryUtils.listFilesFromAssets(ShowMagazineActivity.this, dataSource.getFolder());

                if (files == null) {
                    showNoImagesAlert();
                    return;
                } else {
                    for (File f : files) {
                        if (!f.getName().contains(".png")) {
                            _images.add(getAssetsUri(f.getName(), dataSource.getFolder()).toString());
                        }
                    }
                }


                configureThumbnails();
                configurePager();

                break;
            }
            case WEB: {

                if(dataSource.getUrls() != null){
                    _images = dataSource.getUrls();
                }
                else {
                    String[] stringArray = getResources().getStringArray(dataSource.getResourceFile());
                    _images = new ArrayList<>();

                    for (int i = 0; i < stringArray.length; i++) {
                        _images.add(stringArray[i]);
                    }
                }


                configureThumbnails();

                configurePager();

                break;
            }
            default: {
                showNoImagesAlert();
                break;
            }
        }

    }
    private void configureThumbnails(){
        _thumbnailsView.setAdapter(new ThumbnailRecyclerAdapter(this, _images, true));

        LinearLayoutManager horizontalLayoutManagaer
                = new LinearLayoutManager(ShowMagazineActivity.this, LinearLayoutManager.HORIZONTAL, false);
        _thumbnailsView.setLayoutManager(horizontalLayoutManagaer);

        _thumbnailsView.addOnItemTouchListener(
                new RecyclerItemClickListener(this, new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        _pager.setCurrentItem(position, true);
                    }
                })
        );
    }

    private void configurePager(){
        _pager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                _thumbnailsView.smoothScrollToPosition(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        _adapter = new GalleryPagerAdapter(this);
        _pager.setAdapter(_adapter);
        _pager.setOffscreenPageLimit(2);
    }

    private void showNoImagesAlert() {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setMessage("No Images to display")
                .setNegativeButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                    }
                })
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    class GalleryPagerAdapter extends PagerAdapter {

        Context _context;
        LayoutInflater _inflater;

        public GalleryPagerAdapter(Context context) {
            _context = context;
            _inflater = (LayoutInflater) _context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        @Override
        public int getCount() {
            return _images.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == ((LinearLayout) object);
        }

        @Override
        public Object instantiateItem(ViewGroup container, final int position) {
            View itemView = _inflater.inflate(R.layout.pager_gallery_item, container, false);
            container.addView(itemView);

            final SubsamplingScaleImageView imageView =
                    (SubsamplingScaleImageView) itemView.findViewById(R.id.image);

            imageView.setMaxScale(maxZoom);

            Glide.with(_context)
                    .load(_images.get(position))
                    .asBitmap()
                    .into(new SimpleTarget<Bitmap>() {
                        @Override
                        public void onResourceReady(Bitmap bitmap, GlideAnimation anim) {
                            imageView.setImage(ImageSource.bitmap(bitmap));
                        }
                    });

            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if (toolbar.getVisibility() == View.GONE) {

                        toolbar.setVisibility(View.VISIBLE);
                        thmbnailsLayout.setVisibility(View.VISIBLE);

                    } else {
                        toolbar.setVisibility(View.GONE);
                        thmbnailsLayout.setVisibility(View.GONE);
                    }

                }
            });

            return itemView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((LinearLayout) object);
        }
    }


    @Override
    public void onBackPressed() {
        if (toolbar.getVisibility() == View.VISIBLE)
            super.onBackPressed();
        else {
            toolbar.setVisibility(View.VISIBLE);
            thmbnailsLayout.setVisibility(View.VISIBLE);
        }
    }

    public static Uri getAssetsUri(String relativeName, String folderName) {
        return Uri.fromFile(new File("//android_asset/" + folderName + "/" + relativeName));
    }
}