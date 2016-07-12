package greenway.com.gt;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;

import greenway.com.gt.utils.SharedPreference;


public class LoadPreferenceActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private TextView tituloToolbar;

    private TextView textView;

    private void setup(){
        this.textView = (TextView) findViewById(R.id.tvNombre);
    }

    private void setupToolbar(){
        this.toolbar = (Toolbar) findViewById(R.id.toolbarLoadPreference);
        this.tituloToolbar = (TextView) findViewById(R.id.toolbarTitulo);
        this.tituloToolbar.setText("Cargar preferencias");
        this.setSupportActionBar(toolbar);
        this.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void loadPreferenceData() {
        String name = SharedPreference.getName(getApplicationContext());
        Log.i("pablo", name);
        if (name!=null) {
            this.textView.setText("Bienvenido " + name);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_load_preference);
        this.setup();
        this.setupToolbar();
        this.loadPreferenceData();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                break;
        }
        return false;
    }

}
