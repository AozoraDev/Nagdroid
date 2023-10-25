package id.aozora.nagdroid;

import java.util.Locale;

public class NagdroidLocale {
    public static String getMessage(String lang) {
        Locale locale = Locale.getDefault();
        StringBuilder message = new StringBuilder();
        String code = (lang != null) ? lang : locale.getLanguage();
        
        switch(code.toLowerCase()) {
            // Indonesia
            case "in":
                message.append("Hai! Terima kasih telah menggunakan/memainkan %s!");
                message.append("\n\n");
                message.append("Jika Anda menyukai aplikasi/game ini, silahkan membeli aplikasi/game ini di Play Store untuk mendukung sang pengembang atas kerja keras mereka.");
            break;
            
            // English (Default)
            default:
                message.append("Hi! Thank you for using/playing %s!");
                message.append("\n\n");
                message.append("If you enjoy this app/game, please consider purchasing it on the Play Store to support the developer for their hard work.");
            break;
        }
        
        return message.toString();
    }
}