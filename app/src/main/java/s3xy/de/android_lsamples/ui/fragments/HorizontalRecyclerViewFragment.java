package s3xy.de.android_lsamples.ui.fragments;

import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import butterknife.ButterKnife;
import s3xy.de.android_lsamples.R;
import s3xy.de.android_lsamples.adapter.PhotoAdapter;
import s3xy.de.android_lsamples.api.model.Photo;


/**
 * A simple {@link android.app.Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link s3xy.de.android_lsamples.ui.fragments.HorizontalRecyclerViewFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link s3xy.de.android_lsamples.ui.fragments.HorizontalRecyclerViewFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HorizontalRecyclerViewFragment extends RecyclerViewFragment {

    public static final String TAG = "HorizontalRecyclerViewFragment";

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     *
     * @return A new instance of fragment RecyclerViewFragment.
     */
    public static HorizontalRecyclerViewFragment newInstance() {
        HorizontalRecyclerViewFragment fragment = new HorizontalRecyclerViewFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_recycler_view, container, false);

        ButterKnife.inject(this, rootView);

        mLinearLayoutManager = new LinearLayoutManager(getActivity());
        mLinearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);

        mList.setLayoutManager(mLinearLayoutManager);
        mList.setItemAnimator(new DefaultItemAnimator());


        mPhotos = new PhotoAdapter(new ArrayList<Photo>(), R.layout.row_photo_card_horizontal, getActivity());

        mList.setAdapter(mPhotos);

        return rootView;
    }
}
