package kz.production.kuanysh.tarelka.services;

import android.content.Context;
import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

import javax.inject.Inject;

import kz.production.kuanysh.tarelka.app.Config;
import kz.production.kuanysh.tarelka.data.prefs.PreferencesHelper;

/**
 * Created by User on 13.07.2018.
 */

public class MyFirebaseInstanceIDService extends FirebaseInstanceIdService {

    @Inject
    Context mContext;

    @Inject
    PreferencesHelper mPreferenceHelper;

    @Override
    public void onTokenRefresh() {
        super.onTokenRefresh();

        String refreshedToken= FirebaseInstanceId.getInstance().getToken();

        sendRegistrationToServer(refreshedToken);

        // Notify UI that registration has completed, so the progress indicator can be hidden.
        Intent registrationComplete = new Intent(Config.REGISTRATION_COMPLETE);
        registrationComplete.putExtra("token", refreshedToken);
        LocalBroadcastManager.getInstance(mContext).sendBroadcast(registrationComplete);

    }
    private void sendRegistrationToServer(final String token) {
        // sending gcm token to server
        Log.e("myTag", "sendRegistrationToServer: " + token);
    }
}
