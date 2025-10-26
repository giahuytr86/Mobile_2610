package com.testing.studentmanage;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.ArrayList;

public class Student implements Parcelable {
    private String name;
    private int yearOfBirth;
    private float gpa;
    private ArrayList<String> hobbies;

    public Student(String name, int yearOfBirth, float gpa, ArrayList<String> hobbies) {
        this.name = name;
        this.yearOfBirth = yearOfBirth;
        this.gpa = gpa;
        this.hobbies = hobbies;
    };

    @Override
    public String toString() {
        return "Tên: " + name +
                " | Năm sinh: " + yearOfBirth +
                " | GPA: " + gpa +
                " | Sở thích: " + String.join(", ", hobbies);
    };


    // Constructor đọc dữ liệu từ Parcel
    protected Student(Parcel in) {
        name = in.readString();
        yearOfBirth = in.readInt();
        gpa = in.readFloat();
        hobbies = in.createStringArrayList(); // đọc ArrayList<String>
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeInt(yearOfBirth);
        dest.writeFloat(gpa);
        dest.writeStringList(hobbies); // ghi ArrayList<String>
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Student> CREATOR = new Creator<Student>() {
        @Override
        public Student createFromParcel(Parcel in) {
            return new Student(in);
        }

        @Override
        public Student[] newArray(int size) {
            return new Student[size];
        }
    };

    // Getter/Setter (tùy chọn, có thể thêm nếu cần)
    public String getName() {
        return name;
    }

    public int getYearOfBirth() {
        return yearOfBirth;
    }

    public float getGpa() {
        return gpa;
    }

    public ArrayList<String> getHobbies() {
        return hobbies;
    }
}
