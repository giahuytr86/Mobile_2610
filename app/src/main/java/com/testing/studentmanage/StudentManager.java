package com.testing.studentmanage;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.ArrayList;

public class StudentManager implements Parcelable {
    private ArrayList<Student> students;

    public StudentManager() {
        students = new ArrayList<>();
    }

    // Constructor đọc từ Parcel
    protected StudentManager(Parcel in) {
        students = in.createTypedArrayList(Student.CREATOR);
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(students);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<StudentManager> CREATOR = new Creator<StudentManager>() {
        @Override
        public StudentManager createFromParcel(Parcel in) {
            return new StudentManager(in);
        }

        @Override
        public StudentManager[] newArray(int size) {
            return new StudentManager[size];
        }
    };

    // Các hàm xử lý logic
    public void addStudent(Student student) {
        students.add(student);
    }

    public void viewStudents() {
        if (students.isEmpty()) {
            System.out.println("Danh sách trống!");
        } else {
            for (Student s : students) {
                System.out.println(s);
            }
        }
    }

    public ArrayList<Student> getStudents() {
        return students;
    }
}
