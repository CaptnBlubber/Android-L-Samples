package s3xy.de.android_lsamples.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import s3xy.de.android_lsamples.R;
import s3xy.de.android_lsamples.interfaces.OnItemClickListener;

/**
 * Noodlesoup
 * <p/>
 * Created by arueggeberg on 27.10.14.
 */
public class MenuAdapter extends RecyclerView.Adapter<MenuAdapter.ViewHolder> {
    private ArrayList<String> mDataset;
    private OnItemClickListener mListener;

    public MenuAdapter(ArrayList<String> myDataset, OnItemClickListener listener) {
        mDataset = myDataset;
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater vi = LayoutInflater.from(parent.getContext());
        View v = vi.inflate(R.layout.row_menu, parent, false);
        TextView tv = (TextView) v.findViewById(R.id.text);
        return new ViewHolder(tv);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.mTextView.setText(mDataset.get(position));
        holder.mTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.onClick(view, position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }


    /**
     * Custom viewholder for our planet views.
     */
    public static class ViewHolder extends RecyclerView.ViewHolder {
        public final TextView mTextView;

        public ViewHolder(TextView v) {
            super(v);
            mTextView = v;
        }
    }
}