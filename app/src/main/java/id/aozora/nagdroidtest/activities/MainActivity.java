package id.aozora.nagdroidtest.activities;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.app.Activity;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toolbar;

import id.aozora.nagdroidtest.R;
import id.aozora.nagdroid.Nagdroid;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        AlertDialog dialog = new Nagdroid(this)
        .show();
        
        // Toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setActionBar(toolbar);
        
        // Date
        TextView current = (TextView) findViewById(R.id.current);
        TextView saved = (TextView) findViewById(R.id.saved);
        
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date currentDate = new Date(System.currentTimeMillis());
        Date savedDate = new Date(getSharedPreferences("nagdroid", Context.MODE_PRIVATE).getLong("time", 0));
        
        current.setText(dateFormat.format(currentDate));
        saved.setText(dateFormat.format(savedDate));
        
        // Button to show the dialog
        Button show = (Button) findViewById(R.id.show);
        show.setOnClickListener((d) -> {
            dialog.show();
        });
    }
}
