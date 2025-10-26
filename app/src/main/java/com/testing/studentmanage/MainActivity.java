package com.testing.studentmanage;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private EditText edtName, edtYear, edtGPA;
    private CheckBox chkAmNhac, chkTheThao, chkKhoaHoc;
    private Button btnAdd, btnView, btnDelete;
    StudentManager studentManager = new StudentManager();

    private View.OnClickListener addOnClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String name = edtName.getText().toString().trim();
            String yearStr = edtYear.getText().toString().trim();
            String gpaStr = edtGPA.getText().toString().trim();

            if (name.isEmpty() || yearStr.isEmpty() || gpaStr.isEmpty()) {
                Toast.makeText(MainActivity.this, "Vui lòng nhập đầy đủ thông tin!", Toast.LENGTH_SHORT).show();
                return;
            }

            try {
                int year = Integer.parseInt(yearStr);
                float gpa = Float.parseFloat(gpaStr);

                ArrayList<String> hobbies = new ArrayList<>();
                if (chkKhoaHoc.isChecked()) hobbies.add("Khoa học");
                if (chkAmNhac.isChecked()) hobbies.add("Âm nhạc");
                if (chkTheThao.isChecked()) hobbies.add("Thể thao");

                Student student = new Student(name, year, gpa, hobbies);
                studentManager.addStudent(student);

                Toast.makeText(MainActivity.this, "Thêm sinh viên thành công!", Toast.LENGTH_SHORT).show();
            } catch (NumberFormatException e) {
                Toast.makeText(MainActivity.this, "Năm sinh hoặc GPA không hợp lệ!", Toast.LENGTH_SHORT).show();
            }
        }
    };

    private View.OnClickListener viewOnClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (studentManager.getStudents().isEmpty()) {
                Toast.makeText(MainActivity.this, "Danh sách sinh viên trống!", Toast.LENGTH_SHORT).show();
                return;
            }

            Intent intent = new Intent(MainActivity.this, Detail.class);
            intent.putExtra("student_manager", studentManager);
            startActivity(intent);
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        edtName = findViewById(R.id.edtName);
        edtYear = findViewById(R.id.edtYear);
        edtGPA = findViewById(R.id.edtGPA);
        chkAmNhac = findViewById(R.id.chkAmNhac);
        chkKhoaHoc = findViewById(R.id.chkKhoaHoc);
        chkTheThao = findViewById(R.id.chkTheThao);
        btnAdd = findViewById(R.id.btnAdd);
        btnView = findViewById(R.id.btnView);
        btnDelete = findViewById(R.id.btnDelete);
        btnAdd.setOnClickListener(addOnClick);
        btnView.setOnClickListener(viewOnClick);
    }
}