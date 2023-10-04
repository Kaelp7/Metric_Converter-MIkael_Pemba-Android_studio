package com.example.metricconverter;

import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.text.Editable;
import android.text.TextWatcher;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private EditText inputEditText;
    private EditText resultEditText;
    private Spinner inputTypeSpinner;
    private Spinner resultTypeSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG, "onCreate: Aplikasi telah dibuat.");

        inputEditText = findViewById(R.id.inputEditText);
        resultEditText = findViewById(R.id.resultEditText);
        inputTypeSpinner = findViewById(R.id.inputTypeSpinner);
        resultTypeSpinner = findViewById(R.id.resultTypeSpinner);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.metric_units, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        inputTypeSpinner.setAdapter(adapter);
        resultTypeSpinner.setAdapter(adapter);

        inputTypeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                convertMetric();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
            }
        });

        resultTypeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                convertMetric();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
            }
        });


        inputEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                convertMetric();
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });
    }

    private void convertMetric() {
        String inputText = inputEditText.getText().toString().trim();

        if (inputText.isEmpty()) {
            resultEditText.setText("");
            return;
        }

        try {
            double inputValue = Double.parseDouble(inputText);
            String inputType = inputTypeSpinner.getSelectedItem().toString();
            String resultType = resultTypeSpinner.getSelectedItem().toString();

            double resultValue = performConversion(inputValue, inputType, resultType);

            resultEditText.setText(String.valueOf(resultValue));
        } catch (NumberFormatException e) {
            resultEditText.setText("Invalid Input");
        }
    }

    private double performConversion(double inputValue, String inputType, String resultType) {

        if ((inputType.equals("Hektometer") && resultType.equals("Kilometer"))
                || (inputType.equals("Dekameter") && resultType.equals("Hektometer"))
                || (inputType.equals("Meter") && resultType.equals("Dekameter"))
                || (inputType.equals("Desimeter") && resultType.equals("Meter"))
                || (inputType.equals("Centimeter") && resultType.equals("Desimeter"))
                || (inputType.equals("Milimeter") && resultType.equals("Centimeter"))) {
            return inputValue * 10;
        } else if ((inputType.equals("Dekameter") && resultType.equals("Kilometer"))
                || (inputType.equals("Meter") && resultType.equals("Hektometer"))
                || (inputType.equals("Desimeter") && resultType.equals("Dekameter"))
                || (inputType.equals("Centimeter") && resultType.equals("Meter"))
                || (inputType.equals("Milimeter") && resultType.equals("Desimeter"))) {
            return inputValue * 100;
        } else if ((inputType.equals("Meter") && resultType.equals("Kilometer"))
                || (inputType.equals("Desimeter") && resultType.equals("Hektometer"))
                || (inputType.equals("Centimeter") && resultType.equals("Dekameter"))
                || (inputType.equals("Milimeter") && resultType.equals("Meter"))) {
            return inputValue * 1000;
        } else if ((inputType.equals("Desimeter") && resultType.equals("Kilometer"))
                || (inputType.equals("Centimeter") && resultType.equals("Hektometer"))
                || (inputType.equals("Milimeter") && resultType.equals("Dekameter"))) {
            return inputValue * 10000;
        } else if ((inputType.equals("Centimeter") && resultType.equals("Kilometer"))
                || (inputType.equals("Milimeter") && resultType.equals("Hektometer"))) {
            return inputValue * 100000;
        } else if (inputType.equals("Milimeter") && resultType.equals("Kilometer")) {
            return inputValue * 1000000;

        } else if (inputType.equals("Kilometer") && resultType.equals("Milimeter")) {
            return inputValue * 0.000001;
        } else if ((inputType.equals("Kilometer") && resultType.equals("Centimeter"))
                || (inputType.equals("Hektometer") && resultType.equals("Milimeter"))) {
            return inputValue * 0.00001;
        } else if ((inputType.equals("Kilometer") && resultType.equals("Desimeter"))
                || (inputType.equals("Hektometer") && resultType.equals("Centimeter"))
                || (inputType.equals("Dekameter") && resultType.equals("Milimeter"))) {
            return inputValue * 0.0001;
        } else if ((inputType.equals("Kilometer") && resultType.equals("Meter"))
                || (inputType.equals("Hektometer") && resultType.equals("Desimeter"))
                || (inputType.equals("Dekameter") && resultType.equals("Centimeter"))
                || (inputType.equals("Meter") && resultType.equals("Milimeter"))) {
            return inputValue * 0.001;
        } else if ((inputType.equals("Kilometer") && resultType.equals("Dekameter"))
                || (inputType.equals("Hektometer") && resultType.equals("Meter"))
                || (inputType.equals("Dekameter") && resultType.equals("Desimeter"))
                || (inputType.equals("Meter") && resultType.equals("Centimeter"))
                || (inputType.equals("Desimeter") && resultType.equals("Milimeter"))) {
            return inputValue * 0.01;
        } else if ((inputType.equals("Kilometer") && resultType.equals("Hektometer"))
                || (inputType.equals("Hektometer") && resultType.equals("Dekameter"))
                || (inputType.equals("Dekameter") && resultType.equals("Meter"))
                || (inputType.equals("Meter") && resultType.equals("Desimeter"))
                || (inputType.equals("Desimeter") && resultType.equals("Centimeter"))
                || (inputType.equals("Centimeter") && resultType.equals("Milimeter"))) {
            return inputValue * 0.1;

        } else {
            return inputValue;
        }
    }
        @Override
        protected void onStart() {
            super.onStart();
            Log.d(TAG, "onStart: Aplikasi telah dimulai.");
        }
        @Override
        protected void onResume() {
            super.onResume();
            Log.d(TAG, "onResume: Aplikasi telah dilanjutkan.");
        }

        @Override
        protected void onPause() {
            super.onPause();
            Log.d(TAG, "onPause: Aplikasi telah dijeda.");
        }

        @Override
        protected void onStop() {
            super.onStop();
            Log.d(TAG, "onStop: Aplikasi telah dihentikan.");
        }

        @Override
        protected void onDestroy() {
            super.onDestroy();
            Log.d(TAG, "onDestroy: Aplikasi telah dihancurkan.");
        }
}
