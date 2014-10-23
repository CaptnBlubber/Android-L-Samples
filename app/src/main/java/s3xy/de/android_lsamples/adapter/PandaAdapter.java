package s3xy.de.android_lsamples.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import s3xy.de.android_lsamples.R;
import s3xy.de.android_lsamples.model.Panda;

public class PandaAdapter extends RecyclerView.Adapter<PandaAdapter.ViewHolder> {

    private List<Panda> pandas;
    private int rowLayout;
    private Context mContext;

    public PandaAdapter(List<Panda> pandas, int rowLayout, Context context) {
        this.pandas = pandas;
        this.rowLayout = rowLayout;
        this.mContext = context;
    }

    public PandaAdapter(List<Panda> pandas, Context context) {
        this.pandas = pandas;
        this.rowLayout = R.layout.row_panda;
        this.mContext = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(rowLayout, viewGroup, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        Panda panda = pandas.get(i);
        viewHolder.mPandaImage.setImageResource(R.drawable.ic_launcher);
        viewHolder.mPandaName.setText(panda.getName());
    }

    @Override
    public int getItemCount() {
        return pandas == null ? 0 : pandas.size();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {

        @InjectView(R.id.pandaImage)
        ImageView mPandaImage;
        @InjectView(R.id.pandaName)
        TextView mPandaName;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.inject(this, itemView);
        }
    }
}