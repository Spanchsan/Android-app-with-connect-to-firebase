    package com.example.lesson;

    import androidx.annotation.NonNull;
    import androidx.appcompat.app.AppCompatActivity;
    import androidx.fragment.app.Fragment;

    import android.content.Intent;
    import android.os.Bundle;
    import android.view.Menu;
    import android.view.MenuItem;


    import com.google.android.material.bottomnavigation.BottomNavigationView;


    public class MainActivity extends AppCompatActivity {

        private Fragment studentFragment, groupFragment;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.main_action);
            studentFragment = new StudentFragment();
            groupFragment = new GroupFragment();
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, groupFragment).commit();
            BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation_menu);

            bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    Fragment fragment = studentFragment;
                    switch (item.getItemId()){
                        case R.id.nav_students:
                            fragment = studentFragment;
                            break;
                        case R.id.nav_groups:
                            fragment = groupFragment;
                            break;
                    }
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, fragment).commit();
                    return true;
                }
            });


        }
        @Override
        public boolean onCreateOptionsMenu(Menu menu) {
            getMenuInflater().inflate(R.menu.main_menu, menu);
            return super.onCreateOptionsMenu(menu);
        }

        @Override
        public boolean onOptionsItemSelected(@NonNull MenuItem item) {

            if (item.getItemId() == R.id.menuItemStudentAdd) {
                Intent intent = new Intent(this, StudentAddActivity.class);
                startActivity(intent);
            } else if (item.getItemId() == R.id.menuItemGroupAdd) {
                Intent intent = new Intent(this, GroupAddActivity.class);
                startActivity(intent);
            }

            return super.onOptionsItemSelected(item);
        }


    }