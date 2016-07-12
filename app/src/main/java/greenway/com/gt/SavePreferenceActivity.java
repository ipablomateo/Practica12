package greenway.com.gt;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import greenway.com.gt.utils.SharedPreference;


public class SavePreferenceActivity extends AppCompatActivity implements View.OnClickListener {

    private Toolbar toolbar;
    private TextView tituloToolbar;

    private EditText editText;
    private Button button;


    private void setup(){
        this.editText = (EditText) findViewById(R.id.etNombre);
        this.button = (Button) findViewById(R.id.bGuardar);
        this.button.setOnClickListener(this);
    }

    private void setupToolbar(){
        this.toolbar = (Toolbar) findViewById(R.id.toolbarSavePreference);
        this.tituloToolbar = (TextView) findViewById(R.id.toolbarTitulo);
        this.tituloToolbar.setText("Guardar en preferencias");
        this.setSupportActionBar(toolbar);
        this.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preferencia);
        this.setup();
        this.setupToolbar();
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){

            case R.id.bGuardar:
                //save in shared preference and start a new activity
                String name = this.editText.getText().toString().trim();
                Log.i("pablo", name);
                if ((name.equals(null))||(name.equals(""))) {
                    Toast.makeText(getApplicationContext(), "Debes introducir tu nombre", Toast.LENGTH_SHORT).show();
                }
                else{
                    SharedPreference sharedPreference = new SharedPreference();
                    sharedPreference.setName(getApplicationContext(), name);
                    startActivity(new Intent(this, LoadPreferenceActivity.class));
                }

                break;
        }
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

    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }
}
