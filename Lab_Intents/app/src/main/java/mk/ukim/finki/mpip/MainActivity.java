package mk.ukim.finki.mpip;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {


    TextView textView;
    static final int request_explicit = 1;
    static final int request_select_photo = 2;
    static final int PICK_IMAGE = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.text_for_showing);
    }

    public void callExplicitActivity(View view){
        Intent explicitCall = new Intent(MainActivity.this,ExplicitActivity.class);
        startActivityForResult(explicitCall,request_explicit);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode,Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == request_explicit){
            if (resultCode == RESULT_OK){
                String result = data.getStringExtra(Intent.EXTRA_TEXT);
                textView.setText(result);
            }
        }
        else if (requestCode == request_select_photo){
            if (resultCode == RESULT_OK){
                Uri pictureUri = data.getData();
                Intent pictureIntent = new Intent();
                pictureIntent.setAction(Intent.ACTION_VIEW);
                pictureIntent.setData(pictureUri);
                if (pictureIntent.resolveActivity(getPackageManager())!=null){
                    startActivity(pictureIntent);
                }
            }
        }
    }

    public void callImplicitActivity(View view){
        Intent implicitActivityIntent = new Intent();
        implicitActivityIntent.setAction("mk.ukim.finki.mpip.IMPLICIT_ACTION");
        if(implicitActivityIntent.resolveActivity(getPackageManager())!=null){
            startActivity(implicitActivityIntent);
        }
    }

    public void callShare(View view){
        Intent shareIntent = new Intent();
        shareIntent.setAction(Intent.ACTION_SEND);
        shareIntent.setType("text/plain");
        shareIntent.putExtra(Intent.EXTRA_TEXT,"Content send from MainActivity");
        Intent chooser = Intent.createChooser(shareIntent,"MPiP Send Title");
        if(chooser.resolveActivity(getPackageManager())!=null) {
            startActivity(chooser);
        }
    }

    public void callSelectPhoto(View view){
        Intent selectPhotoIntent = new Intent();
        selectPhotoIntent.setType("image/*");
        selectPhotoIntent.setAction(Intent.ACTION_PICK);
        Intent chooser = Intent.createChooser(selectPhotoIntent,"Select photo");
        if(chooser.resolveActivity(getPackageManager())!=null) {
            startActivityForResult(chooser,request_select_photo);
        }
    }
}