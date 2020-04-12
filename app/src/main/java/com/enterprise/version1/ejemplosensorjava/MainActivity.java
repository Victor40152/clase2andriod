package com.enterprise.version1.ejemplosensorjava;

import androidx.appcompat.app.AppCompatActivity;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.enterprise.version1.ejemplosensorjava.herencia.Grados;

public class MainActivity extends AppCompatActivity {

    Grados grad = new Grados();

    private RadioButton aFarenheit;
    private RadioButton aKelvin;
    private EditText grados;
    private Button calcular;
    private TextView resultado;

    SensorManager sensorManager;
    Sensor sensor;
    SensorEventListener sensorEventListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        aFarenheit = findViewById(R.id.rdfarenheit);
        aKelvin = findViewById(R.id.rdkelvin);
        grados = findViewById(R.id.txtgrados);
        calcular = findViewById(R.id.btncalcular);
        resultado = findViewById(R.id.lblresultado);

        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);

        if (sensor.equals(null)) {
            finish();
        }
        sensorEventListener = new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent event) {
                if (event.values[0] < sensor.getMaximumRange()) {
                    System.out.println("Valor sensorEvent ->" + event.values[0]);
                    System.out.println("Rango del sensor ->" + sensor.getMaximumRange());

                    if (grados.getText().toString().isEmpty()) {
                        Toast.makeText(MainActivity.this, "Debes colocar los datos", Toast.LENGTH_LONG).show();
                    } else {
                        grad.setGrados(Double.parseDouble(grados.getText().toString()));

                        if (aFarenheit.isChecked()) {
                            resultado.setText(String.valueOf(grad.calcularCentigradosAfarenheit() + "F"));
                            resultado.setVisibility(View.VISIBLE);

                        } else if (aKelvin.isChecked()) {
                            resultado.setText(String.valueOf(grad.calcularCentigradosAkelvin() + "K"));
                            resultado.setVisibility(View.VISIBLE);
                        } else {
                            Toast.makeText(MainActivity.this, "Debes seleccionar un tipo de grado", Toast.LENGTH_LONG).show();
                        }
                    }
                } else {

                    grados.setText("");
                    resultado.setVisibility(View.INVISIBLE);
                    aFarenheit.setChecked(false);
                    aKelvin.setChecked(false);
                }
            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int i) {

            }
        };
        start();


        calcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (grados.getText().toString().isEmpty()) {
                    Toast.makeText(MainActivity.this, "Debes colocar los datos", Toast.LENGTH_LONG).show();
                } else {
                    grad.setGrados(Double.parseDouble(grados.getText().toString()));

                    if (aFarenheit.isChecked()) {
                        resultado.setText(String.valueOf(grad.calcularCentigradosAfarenheit() + "F"));
                        resultado.setVisibility(View.VISIBLE);

                    } else if (aKelvin.isChecked()) {
                        resultado.setText(String.valueOf(grad.calcularCentigradosAkelvin() + "K"));
                        resultado.setVisibility(View.VISIBLE);
                    } else {
                        Toast.makeText(MainActivity.this, "Debes seleccionar un tipo de grado", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });

    }


    public void start() {
        sensorManager.registerListener(sensorEventListener, sensor, 2000 * 1000);
    }

    public void stop() {
        sensorManager.unregisterListener(sensorEventListener);
    }
}

