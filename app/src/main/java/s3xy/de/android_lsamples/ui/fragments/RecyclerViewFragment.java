package s3xy.de.android_lsamples.ui.fragments;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.InjectView;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;
import s3xy.de.android_lsamples.R;
import s3xy.de.android_lsamples.adapter.PhotoAdapter;
import s3xy.de.android_lsamples.api.SampleClient;
import s3xy.de.android_lsamples.api.model.Photo;
import s3xy.de.android_lsamples.api.model.SearchResult;
import s3xy.de.android_lsamples.interfaces.OnItemClickListener;


/**
 * A simple {@link android.app.Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link s3xy.de.android_lsamples.ui.fragments.RecyclerViewFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link s3xy.de.android_lsamples.ui.fragments.RecyclerViewFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RecyclerViewFragment extends Fragment implements OnItemClickListener {
    public static final String TAG = "RecyclerViewFragment";
    protected PhotoAdapter mPhotos;
    protected LinearLayoutManager mLinearLayoutManager;
    @InjectView(R.id.list)
    RecyclerView mList;
    private OnFragmentInteractionListener mListener;

    public RecyclerViewFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment RecyclerViewFragment.
     */
    public static RecyclerViewFragment newInstance() {
        RecyclerViewFragment fragment = new RecyclerViewFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_recycler_view, container, false);

        ButterKnife.inject(this, rootView);

        mLinearLayoutManager = new LinearLayoutManager(getActivity());

        mList.setLayoutManager(mLinearLayoutManager);
        mList.setItemAnimator(new DefaultItemAnimator());


        mPhotos = new PhotoAdapter(new ArrayList<Photo>(), getActivity(), this);

        mList.setAdapter(mPhotos);

        return rootView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        SampleClient.getFlickrApiClient(getActivity()).getSearchResults("sunset", new Callback<SearchResult>() {
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

    @Override
    public void onClick(View view, int position) {
        if (mListener != null) {
            mListener.onFragmentInteraction(view, mPhotos.getPhotos().get(position));
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        public void onFragmentInteraction(View triggeringView, Photo p);
    }

}
