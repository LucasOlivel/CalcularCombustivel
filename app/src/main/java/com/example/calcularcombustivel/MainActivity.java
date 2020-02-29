package com.example.calcularcombustivel;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import java.text.NumberFormat;

public class MainActivity extends AppCompatActivity {

    private static final NumberFormat precoFormat =
            NumberFormat.getCurrencyInstance();

    private double gasolina = 2.0;
    private double etanol = 2.0;
    private double result = 0.0;

    private TextView precoGasTextView;
    private TextView precoEtaTextView;
    private TextView resultEditInput;
    private ImageView resultImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        precoGasTextView = findViewById(R.id.precoGasTextView);
        precoEtaTextView = findViewById(R.id.precoEtaTextView);
        resultEditInput = findViewById(R.id.resultEditInput);
        resultImageView = findViewById(R.id.resultImageView);

        SeekBar gasolinaSeekBar = findViewById(R.id.gasolinaSeekBar);
        SeekBar etanolSeekBar = findViewById(R.id.etanolSeekBar);

        //Começa a aplicação como 2
        precoGasTextView.setText(precoFormat.format(gasolina));
        precoEtaTextView.setText(precoFormat.format(etanol));

        gasolinaSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                gasolina = progress/100d;
                precoGasTextView.setText(precoFormat.format(gasolina));
                if(gasolina != 0 && etanol != 0){
                    calcularResult();
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        etanolSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                etanol = progress/100d;
                precoEtaTextView.setText(precoFormat.format(etanol));
                if(etanol != 0 && gasolina != 0) {
                    calcularResult();
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

    }

    private void calcularResult() {
        try {
            result = etanol / gasolina;
            if(result <= 0.7){
                resultEditInput.setText(R.string.resultEtanol);
                resultImageView.setImageResource(R.drawable.etanol);
            }
            else{
                resultEditInput.setText(R.string.resultGasolina);
                resultImageView.setImageResource(R.drawable.gasolina);
            }
        }
        catch (NullPointerException e){

        }
    }
}
