package com.example.lesson;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import models.Group;
import models.Student;

public class UserDataProvider {
    private FirebaseDatabase db;
    private DatabaseReference groups;
    private DatabaseReference students;

    private UserDataProvider(){
    db = FirebaseDatabase.getInstance();
    groups = db.getReference().child("Groups");
    students = db.getReference().child("Students");
}

public static UserDataProvider provider = new UserDataProvider();

    public static UserDataProvider getInstance(){
        return provider;
    }
    public void getGroups(onGroupDataListener listener) {
        ArrayList<Group> groupsArrayList = new ArrayList<>();

        groups.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                groupsArrayList.clear();
                for (DataSnapshot dataGroup : dataSnapshot.getChildren()) {
                    Group group = dataGroup.getValue(Group.class);
                    groupsArrayList.add(group);
                }

                listener.onGroupListener(groupsArrayList);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }

        });

    }

    public void addGroup(String title , String key){
        Group group = new Group();
        group.setTitle(title);
        group.setKey(key);
        DatabaseReference push = groups.push();
        push.setValue(group);

    }

    public void addStudent(String name, String groupKey, int grade ){
        Student student = new Student();
        student.setName(name);
        student.setGroupKey(groupKey);
        student.setGrade(grade);
        DatabaseReference push = students.push();
        student.setKey(push.getKey());
        push.setValue(student);
    }

    public void getStudents(onStudentDataListener listener) {
        ArrayList<Student> studentArrayList = new ArrayList<>();

        Query query = students.orderByChild("grade");

        query.addListenerForSingleValueEvent(new ValueEventListener() {
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot dataStudent : dataSnapshot.getChildren()) {
                    Student student = dataStudent.getValue(Student.class);
                    studentArrayList.add(student);
                }

                listener.onStudentListener(studentArrayList);
            }


            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public void updateStudent(Student student){
        students.child(student.getKey()).setValue(student);
    }

    public void deleteStudent(Student student){
        students.child(student.getKey()).removeValue();
    }

        public void getStudentsByGroupKey(onStudentDataListener listener, String groupKey){
            ArrayList<Student> studentsArrayList = new ArrayList<>();
            ArrayList<Student> temp = new ArrayList<>();

            Query query = students.orderByChild("grade");

            query.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    studentsArrayList.clear();
                    int counter = 0;

                    for (DataSnapshot dataStudent : dataSnapshot.getChildren()) {
                         Student student = dataStudent.getValue(Student.class);
                         if(student.getGroupKey().equals(groupKey)) {
                             temp.add(student);
                             counter++;
                         }
                    }
                    counter--;
                    for (int i = 0; i <= counter; i++) {
                        studentsArrayList.add(temp.get(counter - i));
                    }



                    listener.onStudentListener(studentsArrayList);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        }


    }

