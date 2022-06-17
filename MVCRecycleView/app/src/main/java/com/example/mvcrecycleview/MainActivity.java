package com.example.mvcrecycleview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mvcrecycleview.model.User;
import com.example.mvcrecycleview.recycleViewAdapter.UserRecycleViewAdapter;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

    public class MainActivity extends AppCompatActivity {

        private Button buttonCreate;
        private RecyclerView recycleView;
        private UserRecycleViewAdapter adapter;


        private EditText txtName;
        private EditText txtPhone;

        private ArrayList<User> objects = new ArrayList<>();
        private int index = 0;

        public static WeakReference<MainActivity> weakActivity;

        public static MainActivity getmInstanceActivity() {
            return weakActivity.get();
        }

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);

            weakActivity = new WeakReference<>(this);

            setupComponent();
            setupDefaultRecord();
            setupRecycleView();
            setupEvent();
        }

        /**
         * @author Phong-Kaster
         * this function will map defined component in this activity with XML layout.
         * */
        private void setupComponent()
        {
            buttonCreate = findViewById(R.id.buttonCreate);
            recycleView = findViewById(R.id.mainRecycleView);
        }

        /**
         * @author Phong-Kaster
         * create a default record for recycle view
         * */
        private void setupDefaultRecord()
        {
            User user1 = new User("Phong","0366253623");
            User user2 = new User("Kaster", "0794104124");
            User user3 = new User("Nguyen", "0975646476");

            objects.add(user1);
            objects.add(user2);
            objects.add(user3);
        }

        /**
         * @author Phong-Kaster
         * set up recycleView
         */
        private void setupRecycleView()
        {
            adapter = new UserRecycleViewAdapter(this, objects);
            recycleView.setAdapter(adapter);

            recycleView.setLayoutManager(new LinearLayoutManager(this));
        }

        /**
         * @author Phong-Kaster
         *
         */
        @SuppressLint("NotifyDataSetChanged")
        private void setupEvent()
        {
            buttonCreate.setOnClickListener(view->{
                String name = "Phong " + index++;
                String phone = "0366253623";

                 User user = new User(name, phone);
                 objects.add(user);
                 adapter.notifyDataSetChanged();
            });
        }


        /**
         * @author Phong-Kaster
         * @param position
         * @param name
         * @param phone
         */
        @SuppressLint("NotifyDataSetChanged")
        public void modify(int position, String name, String phone)
        {
            if( position < 0 || name.length() < 1 || phone.length() < 0)
            {
                Toast.makeText(this, "Your information is invalid !", Toast.LENGTH_SHORT).show();
                return;
            }
            else
            {
                objects.get(position).setName(name);
                objects.get(position).setPhone(phone);
                adapter.notifyDataSetChanged();
                Toast.makeText(this, "Modified successfully", Toast.LENGTH_SHORT).show();
            }
        }


        /**
         * @author Phong-Kaster
         * @param position is the vacancy deleted
         */
        @SuppressLint("NotifyDataSetChanged")
        public void eradicate(int position)
        {
            if( position < 0)
            {
                Toast.makeText(this, "Your position is invalid !", Toast.LENGTH_SHORT).show();
                return;
            }
            Toast.makeText(this, "Eradicate successfully !", Toast.LENGTH_SHORT).show();
            objects.remove(position);
            adapter.notifyDataSetChanged();
        }

    }