package mk.ukim.finki.lab4_firebaseapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.fragment.NavHostFragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import mk.ukim.finki.lab4_firebaseapp.model.Student;


public class EditActivity extends AppCompatActivity {
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference mystudentsdatabase = database.getReference("students");
    private EditText ime1,prezime1,index1,tel1,adr1;
    private Button editbtn,viewstudents;
    String key;
    String key_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        ime1=(EditText)findViewById(R.id.addimeedit);
        prezime1=(EditText)findViewById(R.id.addprezimeedit);
        index1=(EditText)findViewById(R.id.addindexrdit);
        tel1=(EditText)findViewById(R.id.addteledit);
        adr1=(EditText)findViewById(R.id.addadredit);
        editbtn=(Button)findViewById(R.id.addedit);
        viewstudents=(Button)findViewById(R.id.sitebtnedit);
        viewstudents.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(EditActivity.this,SecondFragment.class);
                startActivity(i);
            }
        });
        getData();
        editbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ime=(String)ime1.getText().toString();
                String prezime=(String)prezime1.getText().toString();
                String index=(String)index1.getText().toString();
                String tel=(String)tel1.getText().toString();
                String adr=(String)adr1.getText().toString();
                if (ime.isEmpty() || prezime.isEmpty() || index.isEmpty() || tel.isEmpty() || adr.isEmpty()){
                    return;
                }
                uploadDataBase(ime,prezime,index,tel,adr);
            }

        });
    }

    private void uploadDataBase(String ime, String prezime, String index, String tel, String adr) {
        mystudentsdatabase.push().child(key_id).setValue(new Student(key,index,ime,prezime,tel,adr))
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()) {
                            Toast.makeText(EditActivity.this, "Mission successful!", Toast.LENGTH_LONG).show();


                        }
                        else{
                            Toast.makeText(EditActivity.this, "Error: " +task.getException().getMessage(), Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }

    private void getData() {
        Intent i = getIntent();
        String ime = i.getStringExtra("ime");
        String prezime = i.getStringExtra("prezime");
        String index = i.getStringExtra("adresa");
        String adr = i.getStringExtra("tel");
        String tel = i.getStringExtra("index");
        key = i.getStringExtra("key");
        key_id = i.getStringExtra("key_id");

        ime1.setText(ime);
        prezime1.setText(prezime);
        index1.setText(index);
        tel1.setText(tel);
        adr1.setText(adr);

    }
}