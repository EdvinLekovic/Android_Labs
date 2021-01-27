package mk.ukim.finki.lab4_firebaseapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.net.CookieHandler;
import java.util.List;

import mk.ukim.finki.lab4_firebaseapp.EditActivity;
import mk.ukim.finki.lab4_firebaseapp.R;
import mk.ukim.finki.lab4_firebaseapp.model.Student;

public class StudentAdapter extends RecyclerView.Adapter<StudentAdapter.StudentViewHolder> {

    private List<Student> studentList;
    private List<String> keys;
    ViewGroup parent;
    Context context;


    public StudentAdapter(List<Student> studentList) {
        this.studentList = studentList;
        keys = null;
    }

    @NonNull
    @Override
    public StudentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        this.parent = parent;
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.student_row,null);
        return new StudentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StudentViewHolder holder, int position) {
        holder.bindData(position);
    }

    public void updateData(List<Student> students){
        studentList = students;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return studentList.size();
    }

    public void setKeys(List<String> keys) {
        this.keys = keys;
    }

    class StudentViewHolder extends RecyclerView.ViewHolder{

        private TextView indexId,nameId,surnameId,phoneId,addressId;
        private Button edit,delete;


        public StudentViewHolder(@NonNull View itemView) {
            super(itemView);
            indexId = itemView.findViewById(R.id.indexId);
            nameId = itemView.findViewById(R.id.nameId);
            surnameId = itemView.findViewById(R.id.surnameId);
            phoneId = itemView.findViewById(R.id.phoneId);
            addressId = itemView.findViewById(R.id.addressId);
            edit = itemView.findViewById(R.id.edit);
            delete = itemView.findViewById(R.id.delete);
        }

        public void bindData(int position){
            Student student = studentList.get(position);
            indexId.setText(student.getIndex());
            nameId.setText(student.getName());
            surnameId.setText(student.getSurname());
            phoneId.setText(student.getPhone());
            addressId.setText(student.getAddress());
            delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("students");
                    String key = keys.get(position);
                    ref.child(key).removeValue();
                    studentList.remove(position);
                    notifyItemRemoved(position);

                }
        });
            edit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Student s=studentList.get(position);
                    String key = keys.get(position);

                    Intent i=new Intent(context, EditActivity.class);

                    i.putExtra("ime",s.getName());
                    i.putExtra("prezime",s.getSurname());
                    i.putExtra("adresa",s.getAddress());
                    i.putExtra("tel",s.getPhone());
                    i.putExtra("index",s.getIndex());
                    i.putExtra("key",s.getuId());
                    i.putExtra("key_id",key);
                    context.startActivity(i);

                }
            });
}

}

}
