package blank.djaja_works.models;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import java.util.HashMap;
import blank.djaja_works.view.login;

public class SessionManager {
    SharedPreferences pref;

    // Editor for Shared preferences
    SharedPreferences.Editor editor;

    // Context
    Context _context;

    // Shared pref mode
    int PRIVATE_MODE = 0;

    // Sharedpref file name
    private static final String PREF_NAME = "DjayaPref";

    // All Shared Preferences Keys
    private static final String IS_LOGIN = "IsLoggedIn";

    // User name (make variable public to access from outside)
    public static final String KEY_EMAIL = "email";
    public static final String KEY_NAME = "name";
    public static final String KEY_USIA = "usia";
    public static final String KEY_JK = "jk";
    public static final String KEY_NREK = "n_rek";
    public static final String KEY_NPEND = "n_pend";
    public static final String KEY_SALDO = "saldo";
    public static final String KEY_PSS = "password";


    // Email address (make variable public to access from outside)
    //public static final String KEY_JENISUSER = "status";

    private boolean login;

    public SessionManager(Context context){
        this._context = context;
        pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }

    /**
     * Create login session
     * */
    public void createLoginSession(String email, String name, int saldo, String usia, String jk, String nPend, String nRek, String pss){
        // Storing login value as TRUE
        editor.putBoolean(IS_LOGIN, true);
        // Storing name in pref
        editor.putString(KEY_NAME, name);
        // Storing email in pref
        editor.putString(KEY_EMAIL, email);
        // Storing status in pref
        editor.putInt(KEY_SALDO, saldo);

        editor.putString(KEY_JK, jk);
        editor.putString(KEY_USIA, usia);
        editor.putString(KEY_NPEND, nPend);
        editor.putString(KEY_NREK, nRek);
        editor.putString(KEY_PSS,pss);
        //editor.putString(KEY_JENISUSER, status);
        // commit changes
        editor.commit();
    }

    public void reWriteAkun(String email, String name, int saldo, String usia, String jk, String nPend, String nRek, String pss){
        // Storing name in pref
        editor.putString(KEY_NAME, name);
        // Storing email in pref
        editor.putString(KEY_EMAIL, email);
        // Storing status in pref
        editor.putInt(KEY_SALDO, saldo);

        editor.putString(KEY_JK, jk);
        editor.putString(KEY_USIA, usia);
        editor.putString(KEY_NPEND, nPend);
        editor.putString(KEY_NREK, nRek);
        editor.putString(KEY_PSS,pss);
        //editor.putString(KEY_JENISUSER, status);
        // commit changes
        editor.commit();
    }

    /**
     * Check login method wil check user login status
     * If false it will redirect user to login page
     * Else won't do anything
     * */
    public void checkLogin(){
        // Check login status
        if(!this.isLoggedIn()){
            // user is not logged in redirect him to Login Activity
            Intent i = new Intent(_context, login.class);
            // Closing all the Activities
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

            // Add new Flag to start new Activity
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

            // Staring Login Activity
            _context.startActivity(i);
        }

    }
    /**
     * Get stored session data
     * */
    public HashMap<String, String> getUserDetails(){
        HashMap<String, String> user = new HashMap<String, String>();
        // user name
        user.put(KEY_NAME, pref.getString(KEY_NAME, null));
        // user email
        user.put(KEY_EMAIL, pref.getString(KEY_EMAIL, null));
        // user status
        //user.put(KEY_JENISUSER, pref.getString(KEY_JENISUSER, null));
        // return user
        return user;
    }

    /**
     * Clear session details
     * */
    public void logoutUser(){
        // Clearing all data from Shared Preferences
        editor.clear();
        editor.commit();

        // After logout redirect user to Loing Activity
        Intent i = new Intent(_context, login.class);
        // Closing all the Activities
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        // Add new Flag to start new Activity
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        // Staring Login Activity
        _context.startActivity(i);
    }

    /**
     * Quick check for login
     **/
    // Get Login State
    public boolean isLoggedIn() {
        return pref.getBoolean(IS_LOGIN, false);
    }

    public void setLogin(boolean login) {
        this.login = login;
    }

    public String getNama(){
        String baru = pref.getString("name","");
        return baru;
    }

    public void setNama(String nama){
        editor.putString(KEY_NAME,nama);
        editor.commit();
    }

    public void setEmail(String nama){
        editor.putString(KEY_EMAIL,nama);
        editor.commit();
    }

    public void setUsia(String nama){
        editor.putString(KEY_USIA,nama);
        editor.commit();
    }

    public void setJK(String nama){
        editor.putString(KEY_JK,nama);
        editor.commit();
    }

    public void setNPend(String nama){
        editor.putString(KEY_NPEND,nama);
        editor.commit();
    }

    public void setRek(String nama){
        editor.putString(KEY_NREK,nama);
        editor.commit();
    }

    public void setPss(String nama){
        editor.putString(KEY_PSS,nama);
        editor.commit();
    }

    /*public String getStatus(){
        String baru = pref.getString("status","");
        return baru;
    }*/
    public String getEmail(){
        String baru = pref.getString("email","");
        return baru;
    }

    public String getJK(){
        String baru = pref.getString(KEY_JK,"");
        return baru;
    }

    public String getUsia(){
        String baru = pref.getString(KEY_USIA,"");
        return baru;
    }

    public String getNPend(){
        String baru = pref.getString(KEY_NPEND,"");
        return baru;
    }

    public String getNrek(){
        String baru = pref.getString(KEY_NREK,"");
        return baru;
    }

    public String getPss(){
        String baru = pref.getString(KEY_PSS,"");
        return baru;
    }

    public void setSaldo(int sal){
        editor.putInt(KEY_SALDO, sal);
        editor.commit();
    }

    public int getSaldo(){
        int bar = pref.getInt(KEY_SALDO,0);
        return bar;
    }

}
