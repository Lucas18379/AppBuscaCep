package br.lucas.appbuscacep;

import android.content.Context;
import android.graphics.Color;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class EnderecoAdapter extends ArrayAdapter<Endereco> {
    private int layout;
    public EnderecoAdapter(Context context, int resource, List<Endereco> objects) {
        super(context, resource, objects);
        layout=resource;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView==null) {
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(this.layout, parent, false);
        }
        TextView tvCEP=convertView.findViewById(R.id.tvCEP);
        TextView tvLogradouro=convertView.findViewById(R.id.tvLogradouro);
        Endereco end =this.getItem(position);
        tvCEP.setText(""+end.getCep());
        tvLogradouro.setText(""+end.getLogradouro());

        if (position % 2 == 0)
            convertView.setBackgroundColor(Color.parseColor("#B2EBF2"));
        else
            convertView.setBackgroundColor(Color.parseColor("#DFE5E6"));
        return convertView;
    }
}
