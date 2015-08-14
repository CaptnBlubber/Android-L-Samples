package s3xy.de.android_lsamples.ui.fragments;

import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import butterknife.ButterKnife;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;
import s3xy.de.android_lsamples.R;
import s3xy.de.android_lsamples.adapter.PhotoAdapter;
import s3xy.de.android_lsamples.api.SampleClient;
import s3xy.de.android_lsamples.api.model.Photo;
import s3xy.de.android_lsamples.api.model.SearchResult;


/**
 * A simple {@link android.app.Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link s3xy.de.android_lsamples.ui.fragments.GridRecyclerViewFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link s3xy.de.android_lsamples.ui.fragments.GridRecyclerViewFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class GridRecyclerViewFragment extends CardViewFragment {

    public static final String TAG = "GridRecyclerViewFragment";


    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment RecyclerViewFragment.
     */
    public static GridRecyclerViewFragment newInstance() {
        GridRecyclerViewFragment fragment = new GridRecyclerViewFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_recycler_view, container, false);

        ButterKnife.bind(this, rootView);

        StaggeredGridLayoutManager manager;

        if (getResources().getConfiguration().orientation == 1) {
            manager = new StaggeredGridLayoutManager(getResources().getInteger(R.integer.grid_size), StaggeredGridLayoutManager.HORIZONTAL);
        } else {
            manager = new StaggeredGridLayoutManager(getResources().getInteger(R.integer.grid_size), StaggeredGridLayoutManager.VERTICAL);
        }

        mList.setLayoutManager(manager);
        mList.setItemAnimator(new DefaultItemAnimator());


        mPhotos = new PhotoAdapter(new ArrayList<Photo>(), R.layout.row_photo_card_grid, getActivity(), this);

        mList.setAdapter(mPhotos);

        return rootView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        SampleClient.getFlickrApiClient(getActivity()).getSearchResults("panda", new Callback<SearchResult>() {
            @Override
            public void success(SearchResult searchResult, Response response) {
                mPhotos.getPhotos().addAll(searchResult.getPhotos().getPhoto());
                mPhotos.notifyDataSetChanged();
            }

            @Override
            public void failure(RetrofitError error) {

            }
        });
    }
}
