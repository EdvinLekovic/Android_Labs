package mk.ukim.finki.mpip;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.os.Build;
import android.os.Bundle;
import android.widget.TextView;

import java.util.List;

public class ImplicitActivity extends AppCompatActivity {

    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_implicit);
        textView = findViewById(R.id.showing_activities);
        Intent allMainActivities = new Intent(Intent.ACTION_MAIN,null);
        allMainActivities.addCategory(Intent.CATEGORY_LAUNCHER);
        List<ResolveInfo> appList = getPackageManager().queryIntentActivities(allMainActivities,0);
        for(int i = 0;i<appList.size();i++){
            textView.append(appList.get(i).activityInfo.name+"\n\n\n");
        }
    }
}