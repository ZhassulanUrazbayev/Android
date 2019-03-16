package kz.production.kuanysh.tarelka.job;

import com.evernote.android.job.Job;
import com.evernote.android.job.JobCreator;


/**
 * Created by User on 12.08.2018.
 */

public class DemoJobCreator implements JobCreator {

    @Override
    public Job create(String tag) {
        switch (tag) {
            case MorningNotification.TAG:
                return new MorningNotification();
            case AfternoonNotification.TAG:
                return new AfternoonNotification();
            case EveningNotification.TAG:
                return new EveningNotification();
            default:
                return null;
        }
    }
}