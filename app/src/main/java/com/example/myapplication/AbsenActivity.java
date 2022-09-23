package com.example.myapplication;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class AbsenActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    private static final String TAG = MainActivity.class.getSimpleName();
    EditText et1;
    EditText et2;
    EditText et3;
    Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_absen);

        // inisiasi id
        et1 = findViewById(R.id.et1);
        et2 = findViewById(R.id.et2);
        et3 = findViewById(R.id.et3);

        // menutup keyboard
        et1.setKeyListener(null);
        et2.setKeyListener(null);

        // focus date
        et1.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if(b){
                    showDatePickerDialog(view);
                }
            }
        });

        // focus time
        et2.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if(b){
                    showTimePickerDialog(view);
                }
            }
        });

        // spinnner
        spinner = (Spinner) findViewById(R.id.label_spinner);
        if (spinner != null) {
            spinner.setOnItemSelectedListener((AdapterView.OnItemSelectedListener) this);
        }

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.keterangan, android.R.layout.simple_spinner_item);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        if (spinner != null) {
            spinner.setAdapter(adapter);
        }
    }

    // Date and Time Picker
    public void showDatePickerDialog(View v) {
        DialogFragment newFragment = new DatePickerFragment();
        newFragment.show(getSupportFragmentManager(), getString(R.string.date_picker));

    }

    public void showTimePickerDialog(View view) {
        DialogFragment newFragment = new TimePickerFragment();
        newFragment.show(getSupportFragmentManager(), getString(R.string.time_picker));
    }

    public void processDatePickerResult(int year, int month, int day) {
        String month_string = Integer.toString(month + 1);
        String day_string = Integer.toString(day);
        String year_string = Integer.toString(year);

        String dateMessage = month_string + "-" + day_string + "-" + year_string;
        et1.setText(dateMessage);
    }

    public void processTimePickerResult(int hour, int minute) {
        String hour_string = Integer.toString(hour);
        String minute_string = Integer.toString(minute);

        String timeMessage = hour_string + ":" + minute_string;
        et2.setText(timeMessage);
    }

    // Alert
    public void onClickShowAlert(View view){
        AlertDialog.Builder peringatan = new AlertDialog.Builder(AbsenActivity.this);
        peringatan.setTitle("Konfirmasi");
        peringatan.setMessage(R.string.pesan_peringatan);

        peringatan.setPositiveButton("Ya", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(getApplicationContext(), "Absen Berhasil",Toast.LENGTH_SHORT).show();
                et1.setText("");
                et2.setText("");
                et3.setText("");
                spinner.setSelection(0);
            }
        });

        peringatan.setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(getApplicationContext(), "Absen Dibatalkan",Toast.LENGTH_SHORT).show();
            }
        });

        peringatan.show();
    }

    // Spinner
    public void onItemSelected(AdapterView<?> adapterView, View view, int pos, long id) {
        String mSpinnerLabel = adapterView.getItemAtPosition(pos).toString();
        if(mSpinnerLabel.equals("Hadir Tepat Waktu")){
            et3.setVisibility(View.INVISIBLE);
        } else {
            et3.setVisibility(View.VISIBLE);
        }
    }

    public void onNothingSelected(AdapterView<?> adapterView) {
        Log.d(TAG, getString(R.string.tidak_dipilih));
        et3.setVisibility(View.INVISIBLE);
    }
}