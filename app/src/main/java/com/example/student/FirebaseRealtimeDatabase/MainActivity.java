package com.example.student.FirebaseRealtimeDatabase;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {
    TextView tv;
    EditText et;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv=(TextView)findViewById(R.id.tv);
        et=(EditText) findViewById(R.id.et);

    }

    public void write(View view) {
        myRef = database.getReference("message");
        myRef.setValue(et.getText().toString());
    }

    public void read(View view) {myRef.addValueEventListener(new ValueEventListener() {
        @Override
        public void onDataChange(DataSnapshot dataSnapshot) {
            // This method is called once with the initial value and again
            // whenever data at this location is updated.
            String value = dataSnapshot.getValue(String.class);
            tv.setText(value);
            MyApplication app = (MyApplication) getApplication();
            app.str = value;
        }

        @Override
        public void onCancelled(DatabaseError error) {
            // Failed to read value
            //Log.w(TAG, "Failed to read value.", error.toException());
        }
    });
    }
}


