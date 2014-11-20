package s3xy.de.android_lsamples.ui.activity;

import android.app.Fragment;
import android.content.Intent;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.InjectView;
import s3xy.de.android_lsamples.R;
import s3xy.de.android_lsamples.adapter.MenuAdapter;
import s3xy.de.android_lsamples.api.model.Photo;
import s3xy.de.android_lsamples.interfaces.OnItemClickListener;
import s3xy.de.android_lsamples.ui.fragments.CardViewFragment;
import s3xy.de.android_lsamples.ui.fragments.GridRecyclerViewFragment;
import s3xy.de.android_lsamples.ui.fragments.HorizontalRecyclerViewFragment;
import s3xy.de.android_lsamples.ui.views.BezelImageView;


public class MyActivity extends ActionBarActivity implements CardViewFragment.OnFragmentInteractionListener, OnItemClickListener {

    private static final String LAST_FRAGMENT = "KEY_LAST_FRAGMENT";

    ActionBarDrawerToggle drawerToggle;
    @InjectView(R.id.content_frame)
    RelativeLayout mContentFrame;

    @InjectView(R.id.left_drawer)
    LinearLayout mLeftDrawer;


    @InjectView(R.id.drawerLayout)
    DrawerLayout mDrawerLayout;

    @InjectView(R.id.navdrawer_items_list)
    RecyclerView mNavDrawerList;
    @InjectView(R.id.profile_cover_image)
    ImageView mProfileCoverImage;
    @InjectView(R.id.profile_image)
    BezelImageView mProfileImage;

    @InjectView(R.id.profile_email_text)
    TextView mProfileEmailText;
    @InjectView(R.id.profile_name_text)
    TextView mProfileNameText;
    @InjectView(R.id.chosen_account_content_view)
    RelativeLayout mChosenAccountContentView;
    @InjectView(R.id.chosen_account_view)
    FrameLayout mChosenAccountView;
    @InjectView(R.id.account_list)
    LinearLayout mAccountList;

    private ArrayList<String> mMenuItems;
    private String mCurrentFragmentTag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);
        ButterKnife.inject(this);

        drawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.app_name, R.string.app_name) {
        };


        mMenuItems = new ArrayList<String>();

        mMenuItems.add("CardView");
        mMenuItems.add("Horizontal List");
        mMenuItems.add("GridView");

        // improve performance by indicating the list if fixed size.
        mNavDrawerList.setHasFixedSize(true);
        mNavDrawerList.setLayoutManager(new LinearLayoutManager(this));
        mNavDrawerList.setAdapter(new MenuAdapter(mMenuItems, this));


        Picasso.with(this).load(R.drawable.inspectocat).into(mProfileImage);

        mDrawerLayout.setDrawerListener(drawerToggle);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        mChosenAccountContentView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(getString(R.string.github_url)));
                startActivity(i);
            }
        });


        if (savedInstanceState == null) {
            mCurrentFragmentTag = CardViewFragment.TAG;
        } else {
            mCurrentFragmentTag = savedInstanceState.getString(LAST_FRAGMENT, CardViewFragment.TAG);
        }

        Fragment f = getFragmentManager().findFragmentByTag(mCurrentFragmentTag);
        if (f == null) {
            f = CardViewFragment.newInstance();
        }

        getFragmentManager().beginTransaction().replace(R.id.content_frame, f, mCurrentFragmentTag).commit();

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (drawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        drawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        drawerToggle.onConfigurationChanged(newConfig);
    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(LAST_FRAGMENT, mCurrentFragmentTag);
    }

    @Override
    public void onClick(View view, int position) {
        Log.d("NoodleSoup", "Click at postion: " + position);

        Fragment f;
        String tag;

        switch (position) {
            case 1:
                f = getFragmentManager().findFragmentByTag(HorizontalRecyclerViewFragment.TAG);
                tag = HorizontalRecyclerViewFragment.TAG;
                if (f == null) {
                    f = HorizontalRecyclerViewFragment.newInstance();
                }
                break;
            case 2:
                f = getFragmentManager().findFragmentByTag(GridRecyclerViewFragment.TAG);
                tag = GridRecyclerViewFragment.TAG;
                if (f == null) {
                    f = GridRecyclerViewFragment.newInstance();
                }
                break;
            default:
                f = getFragmentManager().findFragmentByTag(CardViewFragment.TAG);
                tag = CardViewFragment.TAG;
                if (f == null) {
                    f = CardViewFragment.newInstance();
                }
                break;
        }

        mCurrentFragmentTag = tag;
        getFragmentManager().beginTransaction().replace(R.id.content_frame, f, tag).commit();

        mDrawerLayout.closeDrawers();
    }

    @Override
    public void onFragmentInteraction(View transitionView, Photo p) {
        ActionBarActivity activity = MyActivity.this;

        ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(
                activity, transitionView, DetailActivity.EXTRA_IMAGE);

        Intent intent = new Intent(activity, DetailActivity.class);
        intent.putExtra(DetailActivity.EXTRA_IMAGE, p.getUrl());
        intent.putExtra(DetailActivity.EXTRA_CAPTION, p.getTitle());

        ActivityCompat.startActivity(activity, intent, options.toBundle());
    }
}
