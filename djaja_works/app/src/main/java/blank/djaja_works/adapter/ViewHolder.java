package blank.djaja_works.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import blank.djaja_works.R;

public class ViewHolder extends RecyclerView.ViewHolder{
    public TextView tvNamaUsaha,tvDeskripsi;
    public Button btnDanai;
    private Context context;
    //private String[] tes;
    //private Intent it;
    private View itemView;

    public ViewHolder(View itemView) {
        super(itemView);
        context = itemView.getContext();
        tvNamaUsaha = itemView.findViewById(R.id.title);
        tvDeskripsi = itemView.findViewById(R.id.headline);
        btnDanai = itemView.findViewById(R.id.danai);
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public Context getContext() {
        return context;
    }
}
