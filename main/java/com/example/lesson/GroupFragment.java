package com.example.lesson;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

import models.Group;

public class GroupFragment extends Fragment {
    private ListView groupsListView;

    private FirebaseDatabase db;
    private DatabaseReference users;

    private GroupAdapter groupsAdapter;
    private ArrayList<Group> groupArrayList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_group, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        groupsListView = view.findViewById(R.id.groupsListView);

        groupArrayList = new ArrayList<>();

        groupsAdapter = new GroupAdapter(getContext(), R.layout.group_item,
                groupArrayList);

        groupsListView.setAdapter(groupsAdapter);

        db = FirebaseDatabase.getInstance();
        users = db.getReference().child("Groups");

        onGroupDataListener listener = new onGroupDataListener() {
            @Override
            public void onGroupListener(ArrayList<Group> groups) {
                groupArrayList.clear();
                groupArrayList.addAll(groups);
                groupsAdapter.notifyDataSetChanged();
            }
        };
        UserDataProvider provider = UserDataProvider.getInstance();
        provider.getGroups(listener);
    }
}
