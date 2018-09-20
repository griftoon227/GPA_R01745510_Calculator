package com.example.grift.gpa_r01745510_calculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;

import static android.graphics.Color.red;
import static android.graphics.Color.rgb;

public class MainActivity extends AppCompatActivity {

    EditText eTV1, eTV2, eTV3, eTV4, eTV5;
    TextView gpaTV;
    Button gpaBtn;
    ArrayList<EditText> grades;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        eTV1=(EditText)findViewById(R.id.grade1ET);
        eTV2=(EditText)findViewById(R.id.grade2ET);
        eTV3=(EditText)findViewById(R.id.grade3ET);
        eTV4=(EditText)findViewById(R.id.grade4ET);
        eTV5=(EditText)findViewById(R.id.grade5ET);
        grades = new ArrayList<EditText>(Arrays.asList(eTV1, eTV2, eTV3, eTV4, eTV5));
        gpaTV = (TextView)findViewById(R.id.show_computed_gpa);
        gpaBtn = (Button)findViewById(R.id.btn_compute_gpa);
    }

    public void compute_gpa(View view) {
        Button b = (Button)view;
        if(b.getText().toString().contains(getString(R.string.btn_text_compute_gpa))) {
            String eTVData = "";
            boolean isInputEmpty = false;
            int sumGrades = 0;
            for (EditText i : grades) {
                eTVData = i.getText().toString().trim();
                if (eTVData.isEmpty()) {
                    isInputEmpty = true;
                    break;
                }
                sumGrades += Integer.parseInt(eTVData);
            }
            if (!isInputEmpty) {
                float gpa = ((float) sumGrades) / grades.size();
                gpaTV.setText(gpaTV.getText() + Float.toString(gpa));
                if (gpa <= 60)
                    setActivityBackgroundColor(getResources().getColor(R.color.red));
                else if (gpa >= 61 && gpa <= 79)
                    setActivityBackgroundColor(getResources().getColor(R.color.yellow));
                else
                    setActivityBackgroundColor(getResources().getColor(R.color.green));
                b.setText("Clear Form Once");
            }
        }
        else {
            for(EditText i : grades)
                i.setText("");
            gpaTV.setText(getString(R.string.text_gpa));
            b.setText(R.string.btn_text_compute_gpa);
            setActivityBackgroundColor(getResources().getColor(R.color.white));
        }
    }

    public void setActivityBackgroundColor(int color) {
        View view = this.getWindow().getDecorView();
        view.setBackgroundColor(color);
    }
}
