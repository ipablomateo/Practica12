package greenway.com.gt;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;


public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private Toolbar toolbar;
    //private TextView tituloToolbar;

    private GridView gridView;
    private String[] options = {
            "Acelerometro",
            "Guardar en preferencias",
            "Creditos"
    };

    private void setup(){
        this.gridView = (GridView) findViewById(R.id.gridMenu);
        this.gridView.setAdapter(new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_expandable_list_item_1, this.options));
        this.gridView.setOnItemClickListener(this);
    }

    private void setupToolbar(){
        this.toolbar = (Toolbar) findViewById(R.id.toolbarMain);
        //this.tituloToolbar = (TextView) findViewById(R.id.toolbarTitulo);
        //this.tituloToolbar.setText("Practica_12");
        this.setSupportActionBar(toolbar);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.setup();
        this.setupToolbar();
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        switch (position){
            case 0:
                startActivity(new Intent(this, AcelerometroActivity.class));
                break;
            case 1:
                startActivity(new Intent(this, SavePreferenceActivity.class));
                break;
            case 2:
                startActivity(new Intent(this, AboutActivity.class));
                break;
        }
    }
}
