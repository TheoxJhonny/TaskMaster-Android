package com.example.sandboxgood.data;

import android.content.Context;
import android.content.SharedPreferences;

public class UserSession {
    private static final String PREFS = "taskmaster_user";
    private static final String DEMO_EMAIL = "demo@taskmaster.cl";
    private static final String DEMO_PASSWORD = "1234";
    private final SharedPreferences prefs;

    public UserSession(Context context) {
        prefs = context.getSharedPreferences(PREFS, Context.MODE_PRIVATE);
    }

    public boolean login(String email, String password) {
        String savedEmail = prefs.getString("email", DEMO_EMAIL);
        String savedPassword = prefs.getString("password", DEMO_PASSWORD);
        boolean valid = savedEmail.equalsIgnoreCase(email) && savedPassword.equals(password);
        if (valid) prefs.edit().putBoolean("logged", true).apply();
        return valid;
    }

    public void register(String name, String email, String password) {
        prefs.edit().putString("name", name).putString("email", email)
                .putString("password", password).putBoolean("logged", true).apply();
    }

    public String getName() { return prefs.getString("name", "Estudiante"); }

    public void logout() { prefs.edit().putBoolean("logged", false).apply(); }
}
