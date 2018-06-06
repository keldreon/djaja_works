package blank.djaja_works.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import blank.djaja_works.R;
import blank.djaja_works.models.Investment;
import blank.djaja_works.view.Proposal;

public class adapter extends RecyclerView.Adapter<ViewHolder> {
    private List<Investment> invList;
    private Context context;
    private Intent it;
    private View itemView;
    private LayoutInflater mInflater;
    private static int pos;
    private Investment in;

    public adapter(List<Investment> inv, Context con){
        this.invList = inv;
        this.context = con;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_items, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        setPosi(holder.getAdapterPosition());
        in = invList.get(position);
        //nanti pake db
        holder.tvNamaUsaha.setText(in.getNamaUsaha());
        holder.tvDeskripsi.setText(in.getDeskripsi());
        holder.rl.setClickable(true);
        //holder.rl.setFocusable(true);
        holder.rl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                it = new Intent(context, Proposal.class);
                int post = invList.get(position).getId();
                it.putExtra("data_calon_ID", post);
                context.startActivity(it);
            }
        });
        holder.setContext(context);
    }

    @Override
    public int getItemCount() {
        return invList.size();
    }

    public Context getContext() {
        return context;
    }

    // convenience method for getting data at click position
    public Investment getItem(int id) {
        return invList.get(id);
    }

    private void setPosi(int position) {
        adapter.pos = position;
    }

    public void swap(List<Investment> data){
        if(invList!=null){
            invList.clear();
            invList.addAll(data);
        }else {
            invList = data;
        }
        notifyDataSetChanged();
    }

    public static int getPos() {
        return pos;
    }
}
