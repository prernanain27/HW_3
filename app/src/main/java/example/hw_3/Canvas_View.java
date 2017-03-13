package example.hw_3;

import android.app.Fragment;
import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;

/**
 * Created by prernaa on 3/11/2017.
 */

public class Canvas_View extends View {

    private Paint paint = new Paint();
    private Path path = new Path();

    private static final String EXTRA_EVENT_LIST = "event_list";
    private static final String EXTRA_STATE = "instance_state";
    private ArrayList<MotionEvent> eventList = new ArrayList<MotionEvent>(100);


    public Canvas_View(Context context) {
        super(context);
        init(null,0);
    }

    public Canvas_View(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs,0);
    }

    public Canvas_View(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs,defStyleAttr);
    }

    public void init(AttributeSet attrs, int defstyle){
        paint.setColor(Color.RED);
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.STROKE);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
       // canvas.drawLine(0,0,getWidth(),getHeight(),paint);
        canvas.drawPath(path, paint);
    }

    public boolean onTouchEvent(MotionEvent motion){
        float touchX = motion.getX();
        float touchY = motion.getY();

        switch (motion.getAction()){
            case MotionEvent.ACTION_DOWN :
                path.moveTo(touchX,touchY);
                break;
            case MotionEvent.ACTION_MOVE:
                path.lineTo(touchX,touchY);
                break;
            case MotionEvent.ACTION_UP :
                break;
        }
        invalidate();
        eventList.add(MotionEvent.obtain(motion));
        return true;
    }


    @Override
    public Parcelable onSaveInstanceState()
    {
        System.out.println("save instance");
        Bundle bundle = new Bundle();
        bundle.putParcelable(EXTRA_STATE, super.onSaveInstanceState());
        bundle.putParcelableArrayList(EXTRA_EVENT_LIST, eventList);

        return bundle;
    }

    @Override
    public void onRestoreInstanceState(Parcelable state)
    {
        if (state instanceof Bundle)
        {
            Bundle bundle = (Bundle) state;
            super.onRestoreInstanceState(bundle.getParcelable(EXTRA_STATE));
            eventList = bundle.getParcelableArrayList(EXTRA_EVENT_LIST);
            if (eventList == null) {
                eventList = new ArrayList<MotionEvent>(100);
            }
            for (MotionEvent event : eventList) {
                onTouchEvent(event);
            }
            return;
        }
        super.onRestoreInstanceState(state);
    }
}
