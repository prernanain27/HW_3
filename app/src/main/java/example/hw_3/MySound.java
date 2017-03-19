package example.hw_3;

import android.app.IntentService;
import android.content.Intent;
import android.media.MediaPlayer;
import android.widget.Toast;

/**
 * Created by prernaa on 3/18/2017.
 */

public class MySound extends IntentService {
    public MySound() {
        super("Sound_will be played");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Toast.makeText(this , "Erasing your content!!!!",Toast.LENGTH_SHORT).show();
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Toast.makeText(this , "Erased!!!!",Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        synchronized (this){
            final MediaPlayer sound = MediaPlayer.create(this , R.raw.eraser);
            sound.start();
        }

    }
}
