package id.aozora.nagdroid;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.net.Uri;

public class Nagdroid {
    private Context context;
    private SharedPreferences pref;
    private AlertDialog.Builder dialog;
    private ApplicationInfo info;
    private String label;
    
    public Nagdroid(Context context) {
        this.context = context;
        
        dialog = new AlertDialog.Builder(context);
        pref = context.getSharedPreferences("nagdroid", Context.MODE_PRIVATE);
        info = context.getApplicationInfo();
        label = info.loadLabel(context.getPackageManager()).toString();
        
        // Set up default settings
        defaultSettings();
        
        // Initiate the time when the app is first time opened
        if(!pref.contains("time")) {
            long firstTime = System.currentTimeMillis()
            + (30L * 24 * 60 * 60 * 1000); // Start with 30 days
            
        	pref.edit()
            .putLong("time", firstTime)
            .commit();
        }
    }
    
    /**
    * Default settings for the dialog
    */
    private void defaultSettings() {
        this//shit good..... *cough cough*
        .showAppIcon(false)
        .showAppName(true)
        .forceMessageLang(null);
        
        // Da buttons
        dialog.setPositiveButton("OK", (b, d) -> {
            Uri uri = Uri.parse("market://details?id=" + info.packageName);
            context.startActivity(new Intent(Intent.ACTION_VIEW, uri));
        });
        dialog.setNegativeButton("Later", null);
    }
    
    /**
    * For displaying the app icon on the dialog
    *
    * @param bool    true for displaying the app icon, false if not
    */
    public Nagdroid showAppIcon(boolean bool) {
        if (bool == true) {
            dialog.setIcon(info.icon);
        } else {
            dialog.setIcon(null);
        }
        
        return this;
    }
    
    /**
    * For displaying the app name on the dialog.
    *
    * @param bool    true for displaying the app name, false for diplaying "Nagdroid" instead
    */
    public Nagdroid showAppName(boolean bool) {
        if (bool == true) {
            dialog.setTitle(label);
        } else {
            dialog.setTitle("Nagdroid");
        }
        
        return this;
    }
    
    /**
    * Force the dialog to show message in specific language (if available)
    *
    * @param lang    Language in ISO-639 format. Null will use system's default language.
    */
    public Nagdroid forceMessageLang(String lang) {
        String message = String.format(NagdroidLocale.getMessage(lang), label);
        dialog.setMessage(message);
        
        return this;
    }
    
    /**
    * Show the dialog if the time has comes
    */
    public AlertDialog show() {
        long saved = pref.getLong("time", 0);
        long current = System.currentTimeMillis();
        
        if (current >= saved) {
            return dialog.show();
        } else {
            return dialog.create();
        }
    }
}