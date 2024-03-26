package com.example.weterynarz1;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    EditText editTextOwner, editTextPurpose, editTextTime;
    SeekBar seekBarAge;
    TextView textViewAgeValue;
    Button buttonOK;
    Spinner spinnerSpecies;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextOwner = findViewById(R.id.editTextText);
        editTextPurpose = findViewById(R.id.editTextText2);
        editTextTime = findViewById(R.id.editTextText3);
        seekBarAge = findViewById(R.id.seekBar);
        textViewAgeValue = findViewById(R.id.textView2);
        buttonOK = findViewById(R.id.button);
        spinnerSpecies = findViewById(R.id.spinnerSpecies);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.species_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerSpecies.setAdapter(adapter);

        spinnerSpecies.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                int maxAge;
                String selectedSpecies = parent.getItemAtPosition(position).toString();
                switch (selectedSpecies) {
                    case "Pies":
                        maxAge = 18;
                        break;
                    case "Kot":
                        maxAge = 20;
                        break;
                    case "Świnka morska":
                        maxAge = 9;
                        break;
                    default:
                        maxAge = 20; // Default value
                }
                seekBarAge.setMax(maxAge);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });

        seekBarAge.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                textViewAgeValue.setText("Ile masz lat? " + progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {}
        });

        buttonOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ownerName = editTextOwner.getText().toString();
                String age = textViewAgeValue.getText().toString().split(" ")[3]; // Poprawione pobieranie wieku
                String purpose = editTextPurpose.getText().toString();
                String species = spinnerSpecies.getSelectedItem().toString();
                String time = editTextTime.getText().toString();

                // Komunikat z informacjami
                String message = "Właściciel: " + ownerName + "\n" +
                        "Wiek: " + age + "\n" +
                        "Cel wizyty: " + purpose + "\n" +
                        "Gatunek zwierzęcia: " + species + "\n" +
                        "Godzina wizyty: " + time;

                // Wyświetlenie komunikatu w oknie dialogowym
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setMessage(message);
                builder.setPositiveButton("OK", null);
                builder.show();
            }
        });
    }
}
