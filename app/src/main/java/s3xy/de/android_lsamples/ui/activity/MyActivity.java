package s3xy.de.android_lsamples.ui.activity;

import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.RelativeLayout;

import butterknife.ButterKnife;
import butterknife.InjectView;
import s3xy.de.android_lsamples.R;
import s3xy.de.android_lsamples.ui.fragments.RecyclerViewFragment;


public class MyActivity extends ActionBarActivity implements RecyclerViewFragment.OnFragmentInteractionListener {

    DrawerLayout drawerLayout;
    ActionBarDrawerToggle drawerToggle;
    @InjectView(R.id.content_frame)
    RelativeLayout mContentFrame;
    @InjectView(R.id.leftDrawer)
    ListView mLeftDrawer;
    @InjectView(R.id.drawerLayout)
    DrawerLayout mDrawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);
        ButterKnife.inject(this);

        drawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.app_name, R.string.app_name) {
        };

        drawerLayout.setDrawerListener(drawerToggle);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        getFragmentManager().beginTransaction().add(R.id.content_frame, RecyclerViewFragment.newInstance(null, null)).commit();

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
    public void onFragmentInteraction(Uri uri) {

    }
}
