package s3xy.de.android_lsamples;

import android.app.Application;

import com.crashlytics.android.Crashlytics;

import timber.log.Timber;

/**
 * Noodlesoup
 * <p/>
 * Created by arueggeberg on 14.11.14.
 */
public class ExampleApp extends Application {


    @Override public void onCreate() {
        super.onCreate();

        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        }

        else {
            Crashlytics.start(this);
        }


    }
}
