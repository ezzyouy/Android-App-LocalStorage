package com.example.jeu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private SeekBar seekBarSound ;
    private SeekBar seekBarBrightness;

    private RadioGroup radioGroup;
    private RadioButton radioButtonEasy;
    private RadioButton radioButtonMedium;
    private RadioButton radioButtonHard;

    private Button buttonSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        seekBarBrightness=findViewById(R.id.seekBar2);
        seekBarSound=findViewById(R.id.seekBar);

        seekBarBrightness.setMax(100);
        seekBarSound.setMax(100);

        radioButtonEasy=findViewById(R.id.radioButton3);
        radioButtonMedium=findViewById(R.id.radioButton4);
        radioButtonHard=findViewById(R.id.radioButton5);

        radioGroup=findViewById(R.id.radioGroup_diffLevel);
        buttonSave=findViewById(R.id.button);

        buttonSave.setOnClickListener((view)->{
            MainActivity.this.doSave(view);
        });
        this.loadGameSetting();
    }

    private void doSave(View view) {
        SharedPreferences pref=this.getSharedPreferences("GSetting", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor= pref.edit();

        editor.putInt("brightness", this.seekBarBrightness.getProgress());
        editor.putInt("sound", this.seekBarSound.getProgress());
        int checkedRadioButtonId = radioGroup.getCheckedRadioButtonId();

        editor.putInt("checkedRadioButtonId", checkedRadioButtonId);

        editor.commit();
        Toast.makeText(this, "Game Setting saved!",Toast.LENGTH_LONG).show();
    }

    private void loadGameSetting() {
        SharedPreferences pref=this.getSharedPreferences("GSetting", Context.MODE_PRIVATE);
        if(pref!=null){
            int brightness=pref.getInt("brightness",90);
            int sound=pref.getInt("sound", 95);
            int checkedRadioButtonId=pref.getInt("checkedRadioButtonId", R.id.radioButton4);

            this.seekBarSound.setProgress(sound);
            this.seekBarBrightness.setProgress(brightness);
            this.radioGroup.check(checkedRadioButtonId);
        }else {
            this.radioButtonMedium.setChecked(true);
            Toast.makeText(this,"Use the default game seeting", Toast.LENGTH_LONG).show();
        }
    }


}