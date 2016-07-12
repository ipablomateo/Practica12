package greenway.com.gt;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;


public class AboutActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private TextView tituloToolbar;

    private void setupToolbar() {
        this.toolbar = (Toolbar) findViewById(R.id.toolbarAbout);
        this.tituloToolbar = (TextView) findViewById(R.id.toolbarTitulo);
        this.tituloToolbar.setText("Creditos");
        this.setSupportActionBar(toolbar);
        this.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        this.setupToolbar();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
        }
        return false;
    }
}
