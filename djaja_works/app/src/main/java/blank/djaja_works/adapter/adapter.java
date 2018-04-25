package blank.djaja_works.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import blank.djaja_works.R;
import blank.djaja_works.models.Investment;

public class adapter extends RecyclerView.Adapter<ViewHolder> {
    private List<Investment> invList;
    private Context context;
    private Intent it;
    private View itemView;

    public adapter(List<Investment> inv, Context con){
        invList = inv;
        this.context = con;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_items, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        Investment in = invList.get(position);
        //nanti pake db
        holder.tvNamaUsaha.setText(in.getNamaUsaha());
        holder.tvDeskripsi.setText(in.getDeskripsi());

        /*holder.btnInvest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });*/
        holder.setContext(context);
    }

    @Override
    public int getItemCount() {
        return invList.size();
    }

    public Context getContext() {
        return context;
    }
}
