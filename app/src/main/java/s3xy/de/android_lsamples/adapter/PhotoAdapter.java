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
import s3xy.de.android_lsamples.R;
import s3xy.de.android_lsamples.api.model.Photo;

public class PhotoAdapter extends RecyclerView.Adapter<PhotoAdapter.ViewHolder> {

    private List<Photo> photos;
    private int rowLayout;
    private Context mContext;

    public PhotoAdapter(List<Photo> photos, int rowLayout, Context context) {
        this.photos = photos;
        this.rowLayout = rowLayout;
        this.mContext = context;
    }

    public PhotoAdapter(List<Photo> photos, Context context) {
        this.photos = photos;
        this.rowLayout = R.layout.row_photo_card;
        this.mContext = context;
    }

    public List<Photo> getPhotos() {
        return photos;
    }

    public void setPhotos(List<Photo> photos) {
        this.photos = photos;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(rowLayout, viewGroup, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        Photo p = photos.get(i);
        Picasso.with(mContext).load(p.getUrl()).fit().into(viewHolder.mPhoto);
        viewHolder.mPhotoTitle.setText(p.getTitle());
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

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.inject(this, itemView);
        }
    }
}