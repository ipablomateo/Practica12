package greenway.com.gt;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.TextView;

public class AcelerometroActivity extends AppCompatActivity implements SensorEventListener {

    private Toolbar toolbar;
    private TextView tituloToolbar;

    private SurfaceViewPablo surfaceViewPablo;
    private SensorManager sensorManager;
    private Bitmap img;
    private float sensorX, sensorY;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setup();
        this.setupSurface();
        setContentView(this.surfaceViewPablo);

    }

    private void setupSurface() {
        this.surfaceViewPablo = new SurfaceViewPablo(this);
        this.surfaceViewPablo.resumen();
    }

    private void setup() {
        img = BitmapFactory.decodeResource(getResources(), R.drawable.greenball);
        sensorX = sensorY = 0;
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        if (sensorManager.getSensorList(Sensor.TYPE_ACCELEROMETER).size() != 0) {
            Sensor sensor = sensorManager.getSensorList(Sensor.TYPE_ACCELEROMETER).get(0);
            sensorManager.registerListener(this, sensor, SensorManager.SENSOR_DELAY_UI);
        }
    }

    private class SurfaceViewPablo extends SurfaceView implements Runnable {
        private SurfaceHolder surfaceHolder;
        private Thread thread = null;
        private boolean isRun = false;

        public SurfaceViewPablo(Context context) {
            super(context);
            surfaceHolder = getHolder();
        }

        @Override
        public void run() {
            while (isRun) {
                if (!surfaceHolder.getSurface().isValid()) continue;
                Canvas canvas = surfaceHolder.lockCanvas();
                canvas.drawRGB(00, 00, 00);
                float centroX = canvas.getWidth() / 2;
                float centroY = canvas.getHeight() / 2;
                canvas.drawBitmap(img, centroX + sensorX * 30, centroY + sensorY * 30, null);
                surfaceHolder.unlockCanvasAndPost(canvas);
            }
        }

        public void pausa() {
            isRun = false;
            while (true) {
                try {
                    thread.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                break;
            }
            thread = null;
        }

        public void resumen() {
            isRun = true;
            thread = new Thread(this);
            thread.start();
        }
    }

    @Override
    protected void onPause() {
        this.sensorManager.unregisterListener(this);
        this.surfaceViewPablo.pausa();
        super.onPause();
    }

    @Override
    public void onAccuracyChanged(Sensor arg0, int arg1) {
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        try {
            Thread.sleep(64);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        sensorX = event.values[0];
        sensorY = event.values[1];
    }
}