package s3xy.de.android_lsamples.adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.Optional;
import s3xy.de.android_lsamples.R;
import s3xy.de.android_lsamples.api.model.Photo;
import s3xy.de.android_lsamples.interfaces.OnItemClickListener;
import timber.log.Timber;

public class PhotoAdapter extends RecyclerView.Adapter<PhotoAdapter.ViewHolder> {

    private final OnItemClickListener mListener;
    private List<Photo> photos;
    private int rowLayout;
    private Context mContext;
    private Picasso mPicasso;

    public PhotoAdapter(List<Photo> photos, int rowLayout, Context context, OnItemClickListener listener) {
        this.photos = photos;
        this.rowLayout = rowLayout;
        this.mContext = context;
        mListener = listener;
        mPicasso = new Picasso.Builder(context.getApplicationContext())
                .indicatorsEnabled(true)
                .build();
    }

    public PhotoAdapter(List<Photo> photos, Context context, OnItemClickListener listener) {
        this(photos, R.layout.row_photo_card, context, listener);
    }

    public List<Photo> getPhotos() {
        return photos;
    }

    public void setPhotos(List<Photo> photos) {
        this.photos = photos;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        Timber.d("onCreateViewHolder");
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(rowLayout, viewGroup, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final ViewHolder viewHolder, final int i) {
        Timber.d("onBindViewHolder");
        Photo p = photos.get(i);

//        if (viewHolder.mPhoto.getDrawable() == null) {
//            Picasso.with(mContext).load(p.getUrl()).error(android.R.drawable.stat_notify_error).into(viewHolder.mPhoto);
//        }

        mPicasso.load(p.getUrl()).error(android.R.drawable.stat_notify_error).placeholder(android.R.drawable.stat_notify_sync).into(viewHolder.mPhoto);

        viewHolder.mPhotoTitle.setText(p.getId());

        if (viewHolder.mPhotographerTitle != null) {
            viewHolder.mPhotographerTitle.setText(p.getTitle());
        }

        viewHolder.mCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onClick(viewHolder.mPhoto, i);
            }
        });

    }

    @Override
    public int getItemCount() {
        return photos == null ? 0 : photos.size();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {

        @InjectView(R.id.photo)
        ImageView mPhoto;
        @InjectView(R.id.photoTitle)
        TextView mPhotoTitle;
        @InjectView(R.id.cardView)
        CardView mCardView;

        @Optional
        @InjectView(R.id.photographer)
        TextView mPhotographerTitle;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.inject(this, itemView);
        }
    }
}