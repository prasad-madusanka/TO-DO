package mobileapplicationdevelopment.lynx.todo;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

public class AddNewToDo extends AppCompatActivity {

    private Button save;
    private TextView todoDate;
    private EditText todoDescription;
    private DatabaseConnection dc;
    private String date, description;
    private DatePickerDialog.OnDateSetListener datePick;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_todo);

        try {

            init();
            dc = new DatabaseConnection(getApplicationContext());

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {

                    date = todoDate.getText().toString();
                    description = todoDescription.getText().toString();

                    if(!date.matches("") && !description.matches("")) {
                        boolean insert = dc.insert(date, description);
                    }else {
                        Toast.makeText(getApplicationContext(), "Please fill the fields", Toast.LENGTH_LONG).show();
                    }
                    startActivity(new Intent(AddNewToDo.this, MainActivity.class));

                } catch (Exception ex) {
                    ex.printStackTrace();
                }


            }
        });

        todoDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        AddNewToDo.this, android.R.style.Theme_Holo_Light_Dialog_MinWidth, datePick, year, month, day);

                datePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                datePickerDialog.show();
            }
        });

        datePick = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                String date = year + "-" + (month + 1) + "-" + dayOfMonth;
                todoDate.setText(date);
            }
        };


    }

    private void init() {
        save = (Button) findViewById(R.id.btnSave);
        todoDate = (TextView) findViewById(R.id.tvDate);
        todoDescription = (EditText) findViewById(R.id.etDescription);
    }
}
