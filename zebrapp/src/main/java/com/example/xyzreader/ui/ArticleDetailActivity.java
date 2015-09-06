package com.example.xyzreader.ui;

import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.FrameLayout;

import com.example.xyzreader.R;
import com.example.xyzreader.data.ArticleLoader;
import com.example.xyzreader.data.ItemsContract;

import butterknife.Bind;

/**
 * An activity representing a single Article detail screen, letting you swipe between articles.
 */
public class ArticleDetailActivity extends AppCompatActivity {

    private Cursor mCursor;
    private long mStartId;

    private int mTopInset;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().getDecorView().setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN |
                            View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
        }
        setContentView(R.layout.activity_article_detail);


        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            postponeEnterTransition();
        }



        if (savedInstanceState == null) {
            if (getIntent() != null && getIntent().getData() != null) {
                mStartId = ItemsContract.Items.getItemId(getIntent().getData());
            }
        }

        loadFragment();
    }

    private void loadFragment() {
        ArticleDetailFragment detailFragment =
                ArticleDetailFragment.newInstance(mStartId);

        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, detailFragment, "detail").commit();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        View fab = findViewById(R.id.share_fab);
        if(fab != null) {
            fab.setVisibility(View.GONE);
        }

        View cover = findViewById(R.id.detail_cover);
        if(cover != null){
            cover.setVisibility(View.INVISIBLE);
        }
        supportFinishAfterTransition();
    }
}
