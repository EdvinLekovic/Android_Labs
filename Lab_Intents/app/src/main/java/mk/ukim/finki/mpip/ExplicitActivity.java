package mk.ukim.finki.mpip;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class ExplicitActivity extends AppCompatActivity {


    EditText editText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_explicit);
        editText = findViewById(R.id.editText);
    }

    public void onClickOk(View view){
        Intent okIntent = new Intent();
        okIntent.putExtra(Intent.EXTRA_TEXT,editText.getText().toString());
        setResult(RESULT_OK,okIntent);
        finish();
    }

    public void onClickCancel(View view){
        Intent cancelIntent = new Intent();
        setResult(RESULT_CANCELED,cancelIntent);
        finish();
    }

}