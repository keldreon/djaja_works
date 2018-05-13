package blank.djaja_works.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupMenu;
import android.widget.Toast;

import java.util.List;

import blank.djaja_works.R;
import blank.djaja_works.models.Investment;

public class adapter extends RecyclerView.Adapter<ViewHolder> {
    private List<Investment> invList;
    private Context context;
    private Intent it;
    private View itemView;
    private LayoutInflater mInflater;
    private int position;

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
        /*holder.rl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });*/

        holder.rl.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                PopupMenu popup = new PopupMenu(mInflater.getContext(), v);
                popup.getMenuInflater()
                        .inflate(R.menu.menu_temp, popup.getMenu());
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()){
                            case R.id.mn_delete:
                                Toast.makeText(mInflater.getContext(), "hapus "+holder.tvNamaUsaha, Toast.LENGTH_SHORT).show();
                                return true;
                            default:
                                return true;
                        }
                    }
                });
                popup.show(); //showing popup menu
                return true;
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

    public void setPosition(int position) {
        this.position = position;
    }
}
