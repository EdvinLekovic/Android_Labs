package mk.ukim.finki.lab4_firebaseapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import mk.ukim.finki.lab4_firebaseapp.model.Student;

public class FirstFragment extends Fragment {

    private EditText etIndex,etName,etSurname,etPhone,etAddress;
    private Button btnUpload;
    FirebaseAuth mAuth;

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference studentsRef = database.getReference("students");

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_first, container, false);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mAuth = FirebaseAuth.getInstance();
        etIndex = view.findViewById(R.id.etIndex);
        etName = view.findViewById(R.id.etName);
        etSurname = view.findViewById(R.id.etSurname);
        etPhone = view.findViewById(R.id.etPhone);
        etAddress = view.findViewById(R.id.etAddress);

        btnUpload = view.findViewById(R.id.btnUpload);

        view.findViewById(R.id.btnViewStudents).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(FirstFragment.this)
                        .navigate(R.id.action_FirstFragment_to_SecondFragment);
            }
        });

        btnUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String index = etIndex.getText().toString();
                String name = etName.getText().toString();
                String surname = etSurname.getText().toString();
                String phone = etPhone.getText().toString();
                String address = etAddress.getText().toString();
                if(index.isEmpty()||name.isEmpty()||surname.isEmpty()||phone.isEmpty()||address.isEmpty()){
                    return;
                }
                uploadData(index,name,surname,phone,address);
            }
        });
    }

    private void uploadData(String index, String name, String surname, String phone, String address) {
        studentsRef.push().setValue(new Student(mAuth.getUid(),index,name,surname,phone,address))
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(getActivity(),"Success",Toast.LENGTH_LONG).show();
                            etIndex.setText("");
                            etName.setText("");
                            etSurname.setText("");
                            etPhone.setText("");
                            etAddress.setText("");
                        }
                        else{
                            Toast.makeText(getActivity(),"Error: "+task.getException().getMessage(),Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }
}