package br.lucas.appbuscacep;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.List;

public class DetalhesActivity extends AppCompatActivity implements OnMapReadyCallback {
    private TextView tvCEP, tvUF, tvDDD, tvLogradouro, tvComplemento, tvBairro, tvLocalidade, tvIBGE, tvGIA, tvSIAFI,
            btVoltar, tvLatitude, tvLongitude;
    private MapView mv;
    private GoogleMap gmap;
    private static final String MAP_VIEW_BUNDLE_KEY="MapViewBundleKey";
    private double lat,lng;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhes);
        lat=lng=0;
        tvCEP=findViewById(R.id.tvCEP);
        tvUF=findViewById(R.id.tvUF);
        tvDDD=findViewById(R.id.tvDDD);
        tvLogradouro=findViewById(R.id.tvLogradouro);
        tvComplemento=findViewById(R.id.tvComplemento);
        tvBairro=findViewById(R.id.tvBairro);
        tvLocalidade=findViewById(R.id.tvLocalidade);
        tvIBGE=findViewById(R.id.tvIBGE);
        tvGIA=findViewById(R.id.tvGIA);
        tvSIAFI=findViewById(R.id.tvSIAFI);
        btVoltar=findViewById(R.id.btVoltar);
        tvLatitude=findViewById(R.id.tvLatitude);
        tvLongitude=findViewById(R.id.tvLongitude);

        mv=findViewById(R.id.mv);
        mv.setClickable(true);
        Bundle mapViewBundle = null;
        if (savedInstanceState != null) {
            mapViewBundle = savedInstanceState.getBundle(MAP_VIEW_BUNDLE_KEY);
        }
        mv.onCreate(mapViewBundle);
        mv.getMapAsync(this);

        Intent intent = this.getIntent();
        Bundle bundle = intent.getExtras();
        tvCEP.setText("CEP: "+bundle.getString("CEP"));
        tvUF.setText("UF: "+bundle.getString("UF"));
        tvDDD.setText("DDD: "+bundle.getString("DDD"));
        tvLogradouro.setText("Logradouro: "+bundle.getString("Logradouro"));
        tvComplemento.setText("Complemento: "+bundle.getString("Complemento"));
        tvBairro.setText("Bairro: "+bundle.getString("Bairro"));
        tvLocalidade.setText("Localidade: "+bundle.getString("Localidade"));
        tvIBGE.setText("IBGE: "+bundle.getString("IBGE"));
        tvGIA.setText("GIA: "+bundle.getString("GIA"));
        tvSIAFI.setText("SIAFI: "+bundle.getString("SIAFI"));

        btVoltar.setOnClickListener(e->{finish();});

        AcessaWsTask task=new AcessaWsTask();
        try {
            String json=task.execute("https://maps.googleapis.com/maps/api/geocode/json?address="+bundle.getString("Logradouro")+","+bundle.getString("Complemento")+","+bundle.getString("Localidade")+","+bundle.getString("UF")+"&key=AIzaSyAQDPyZpYpBioUpLB8DnIvmL103hj1N13c").get();
            System.out.println("https://maps.googleapis.com/maps/api/geocode/json?address="+bundle.getString("Logradouro")+","+bundle.getString("Complemento")+","+bundle.getString("Localidade")+","+bundle.getString("UF")+"&key=AIzaSyAQDPyZpYpBioUpLB8DnIvmL103hj1N13c");
            System.out.println(json);

            JSONObject jobject = new JSONObject(json);
            JSONArray jarray = jobject.getJSONArray("results");
            jobject=jarray.getJSONObject(0);
            jobject=jobject.getJSONObject("geometry");
            jobject=jobject.getJSONObject("location");
            lat=jobject.getDouble("lat");
            lng=jobject.getDouble("lng");

            tvLatitude.setText("Latitude: "+lat);
            tvLongitude.setText("Longitude: "+lng);
        }catch (Exception e)
        {
            Log.e("Erro na conexão", e.toString());
        };

    }

    @Override
    protected void onResume() {
        super.onResume();
        mv.onResume();
    }

    @Override
    protected void onStart() {
        super.onStart();
        mv.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mv.onStop();
    }
    @Override
    protected void onDestroy() {
        mv.onDestroy();
        super.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mv.onLowMemory();
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        gmap = googleMap;
        gmap.setMinZoomPreference(12);
        gmap.setIndoorEnabled(true);

        UiSettings ponto =
                gmap.getUiSettings();
        ponto.setIndoorLevelPickerEnabled(true);

        ponto.setMyLocationButtonEnabled(true);
        ponto.setMapToolbarEnabled(true);
        ponto.setCompassEnabled(true);
        ponto.setZoomControlsEnabled(true);

        //PONTO CENTRAL DA CIDADE
        LatLng latLong = new LatLng(lat, lng);

        MarkerOptions markeroptions = new MarkerOptions();
        markeroptions.position(latLong);
        gmap.addMarker(markeroptions);

        gmap.moveCamera
                (CameraUpdateFactory.newLatLng(latLong));


    }
}