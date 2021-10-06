package br.lucas.appbuscacep;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;

public class MainActivity extends AppCompatActivity {
    private Spinner snUF,snCidade;
    private EditText etIden;
    private TextView btBuscar,btSair;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        snUF=findViewById(R.id.snUF);
        snCidade=findViewById(R.id.snCidade);
        etIden=findViewById(R.id.etIden);
        btBuscar=findViewById(R.id.btBuscar);
        btSair=findViewById(R.id.btSair);

        ArrayAdapter<CharSequence> adapter =
                ArrayAdapter.createFromResource(this,R.array.uf,
                        android.R.layout.simple_spinner_item);
        snUF.setAdapter(adapter);
        btBuscar.setOnClickListener(e->{acessarAPICEP();});
        snUF.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                int index = adapterView.getSelectedItemPosition();
                final String item = adapterView.getSelectedItem().toString();
                ArrayAdapter<CharSequence> adapter;

                switch (item) {
                    case "AC":adapter = ArrayAdapter.createFromResource(MainActivity.this,R.array.AC, android.R.layout.simple_spinner_item);snCidade.setAdapter(adapter);break;
                    case "AL":adapter = ArrayAdapter.createFromResource(MainActivity.this,R.array.AL, android.R.layout.simple_spinner_item);snCidade.setAdapter(adapter);break;
                    case "AP":adapter = ArrayAdapter.createFromResource(MainActivity.this,R.array.AP, android.R.layout.simple_spinner_item);snCidade.setAdapter(adapter);break;
                    case "AM":adapter = ArrayAdapter.createFromResource(MainActivity.this,R.array.AM, android.R.layout.simple_spinner_item);snCidade.setAdapter(adapter);break;
                    case "BA":adapter = ArrayAdapter.createFromResource(MainActivity.this,R.array.BA, android.R.layout.simple_spinner_item);snCidade.setAdapter(adapter);break;
                    case "CE":adapter = ArrayAdapter.createFromResource(MainActivity.this,R.array.CE, android.R.layout.simple_spinner_item);snCidade.setAdapter(adapter);break;
                    case "DF":adapter = ArrayAdapter.createFromResource(MainActivity.this,R.array.DF, android.R.layout.simple_spinner_item);snCidade.setAdapter(adapter);break;
                    case "ES":adapter = ArrayAdapter.createFromResource(MainActivity.this,R.array.ES, android.R.layout.simple_spinner_item);snCidade.setAdapter(adapter);break;
                    case "GO":adapter = ArrayAdapter.createFromResource(MainActivity.this,R.array.GO, android.R.layout.simple_spinner_item);snCidade.setAdapter(adapter);break;
                    case "MA":adapter = ArrayAdapter.createFromResource(MainActivity.this,R.array.MA, android.R.layout.simple_spinner_item);snCidade.setAdapter(adapter);break;
                    case "MT":adapter = ArrayAdapter.createFromResource(MainActivity.this,R.array.MT, android.R.layout.simple_spinner_item);snCidade.setAdapter(adapter);break;
                    case "MS":adapter = ArrayAdapter.createFromResource(MainActivity.this,R.array.MS, android.R.layout.simple_spinner_item);snCidade.setAdapter(adapter);break;
                    case "PA":adapter = ArrayAdapter.createFromResource(MainActivity.this,R.array.PA, android.R.layout.simple_spinner_item);snCidade.setAdapter(adapter);break;
                    case "PB":adapter = ArrayAdapter.createFromResource(MainActivity.this,R.array.PB, android.R.layout.simple_spinner_item);snCidade.setAdapter(adapter);break;
                    case "PR":adapter = ArrayAdapter.createFromResource(MainActivity.this,R.array.PR, android.R.layout.simple_spinner_item);snCidade.setAdapter(adapter);break;
                    case "PE":adapter = ArrayAdapter.createFromResource(MainActivity.this,R.array.PE, android.R.layout.simple_spinner_item);snCidade.setAdapter(adapter);break;
                    case "PI":adapter = ArrayAdapter.createFromResource(MainActivity.this,R.array.PI, android.R.layout.simple_spinner_item);snCidade.setAdapter(adapter);break;
                    case "RJ":adapter = ArrayAdapter.createFromResource(MainActivity.this,R.array.RJ, android.R.layout.simple_spinner_item);snCidade.setAdapter(adapter);break;
                    case "RN":adapter = ArrayAdapter.createFromResource(MainActivity.this,R.array.RN, android.R.layout.simple_spinner_item);snCidade.setAdapter(adapter);break;
                    case "RS":adapter = ArrayAdapter.createFromResource(MainActivity.this,R.array.RS, android.R.layout.simple_spinner_item);snCidade.setAdapter(adapter);break;
                    case "RO":adapter = ArrayAdapter.createFromResource(MainActivity.this,R.array.RO, android.R.layout.simple_spinner_item);snCidade.setAdapter(adapter);break;
                    case "RR":adapter = ArrayAdapter.createFromResource(MainActivity.this,R.array.RR, android.R.layout.simple_spinner_item);snCidade.setAdapter(adapter);break;
                    case "SC":adapter = ArrayAdapter.createFromResource(MainActivity.this,R.array.SC, android.R.layout.simple_spinner_item);snCidade.setAdapter(adapter);break;
                    case "SP":adapter = ArrayAdapter.createFromResource(MainActivity.this,R.array.SP, android.R.layout.simple_spinner_item);snCidade.setAdapter(adapter);break;
                    case "SE":adapter = ArrayAdapter.createFromResource(MainActivity.this,R.array.SE, android.R.layout.simple_spinner_item);snCidade.setAdapter(adapter);break;
                    case "TO":adapter = ArrayAdapter.createFromResource(MainActivity.this,R.array.TO, android.R.layout.simple_spinner_item);snCidade.setAdapter(adapter);break;
                    case "MG":adapter = ArrayAdapter.createFromResource(MainActivity.this,R.array.MG, android.R.layout.simple_spinner_item);snCidade.setAdapter(adapter);break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        btSair.setOnClickListener(e->{finish();});

    }

    private void acessarAPICEP() {
        AcessaWsTask task=new AcessaWsTask();
        try {
            String uf=snUF.getSelectedItem().toString();
            String cid=snCidade.getSelectedItem().toString();

            String json=task.execute("https://viacep.com.br/ws/"+uf+"/"+cid+"/"+etIden.getText()+"/json/").get();
            System.out.println("https://viacep.com.br/ws/"+uf+"/"+cid+"/"+etIden.getText()+"/json/");
            System.out.println(json);
            JSONArray jarray = new JSONArray(json);
            if(jarray.length()>0) {
                Singleton.util = new Util();
                Util.montar(jarray);
                Intent intent = new Intent(this, ListaActivity.class);
                startActivity(intent);
            }
            else
            {
                Toast.makeText(MainActivity.this,"Sem Resultado",Toast.LENGTH_LONG).show();
            }
        }catch (Exception e)
        {
            Log.e("Erro na conexão", e.toString());
            Toast.makeText(MainActivity.this,"Sem Resultado",Toast.LENGTH_LONG).show();
        };
    }



}