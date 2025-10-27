package com.testing.studentmanage;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Detail extends AppCompatActivity {
    private StudentManager studentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        studentManager = getIntent().getParcelableExtra("student_manager");

        TableLayout tableLayout = findViewById(R.id.tableStudents);

        if (studentManager != null && !studentManager.getStudents().isEmpty()) {
            for (Student s : studentManager.getStudents()) {
                TableRow row = new TableRow(this);

                TextView tvName = new TextView(this);
                tvName.setText(s.getName());
                tvName.setPadding(8, 8, 8, 8);

                TextView tvYear = new TextView(this);
                tvYear.setText(String.valueOf(s.getYearOfBirth()));
                tvYear.setPadding(8, 8, 8, 8);

                TextView tvGPA = new TextView(this);
                tvGPA.setText(String.valueOf(s.getGpa()));
                tvGPA.setPadding(8, 8, 8, 8);

                TextView tvHobbies = new TextView(this);
                tvHobbies.setText(String.join(", ", s.getHobbies()));
                tvHobbies.setPadding(8, 8, 8, 8);

                row.addView(tvName);
                row.addView(tvYear);
                row.addView(tvGPA);
                row.addView(tvHobbies);

                tableLayout.addView(row);
            }
        } else {
            Toast.makeText(this, "Không có dữ liệu sinh viên!", Toast.LENGTH_SHORT).show();
        }

        Button btnBack = findViewById(R.id.btnBack);
        btnBack.setOnClickListener(v -> {
            Intent resultIntent = new Intent();
            resultIntent.putExtra("student_manager", studentManager);
            setResult(RESULT_OK, resultIntent);
            finish();
        });
    }
}
