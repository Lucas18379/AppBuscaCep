package br.lucas.appbuscacep;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

public class ListaActivity extends AppCompatActivity {
    private ListView listView;
    private TextView tvVoltar,tvRes;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista);
        listView=findViewById(R.id.listView);
        tvVoltar=findViewById(R.id.btVoltar);
        tvRes=findViewById(R.id.tvRes);

        List<Endereco> end = Singleton.util.getEnds();

        tvRes.setText("Resultados encontrados: "+end.size());
        EnderecoAdapter adapter =
                new EnderecoAdapter(this, R.layout.item_lista, end);
        listView.setAdapter(adapter);

        tvVoltar.setOnClickListener(e->{finish();});

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Endereco end=(Endereco)listView.getItemAtPosition(i);
                Bundle bundle=new Bundle();
                bundle.putString("CEP", end.getCep());
                bundle.putString("UF", end.getUf());
                bundle.putString("DDD", end.getDdd());
                bundle.putString("Logradouro", end.getLogradouro());
                bundle.putString("Bairro", end.getBairro());
                bundle.putString("Complemento", end.getComplemento());
                bundle.putString("Localidade", end.getLocalidade());
                bundle.putString("IBGE", end.getIbge());
                bundle.putString("GIA", end.getGia());
                bundle.putString("SIAFI", end.getSiafi());
                Intent intent = new Intent(ListaActivity.this, DetalhesActivity.class);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }
}