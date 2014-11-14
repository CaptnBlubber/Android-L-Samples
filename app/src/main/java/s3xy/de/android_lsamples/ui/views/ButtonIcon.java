package s3xy.de.android_lsamples.ui.views;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.util.AttributeSet;
import android.view.MotionEvent;


public class ButtonIcon extends ButtonFloat {

	@TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    public ButtonIcon(Context context, AttributeSet attrs) {
		super(context, attrs);
		setBackground(new ColorDrawable(getResources().getColor(android.R.color.transparent)));
		rippleSpeed = Utils.dpToPx(2, getResources());
		rippleSize = Utils.dpToPx(5, getResources());
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		boolean returnBool = super.onTouchEvent(event);
		if(x != -1){
			x = getWidth() / 2;
			y = getHeight() / 2;
		}
		return returnBool;
	}
	
	@Override
	protected void onDraw(Canvas canvas) {
		if (x != -1) {
			Paint paint = new Paint();
			paint.setAntiAlias(true);
			paint.setColor(makePressColor());
			canvas.drawCircle(x, y, radius, paint);
			if(radius > getHeight()/rippleSize)
				radius += rippleSpeed;
			if(radius >= getWidth() / 2 - rippleSpeed){
				x = -1;
				y = -1;
				radius = getHeight()/rippleSize;
				if(onClickListener != null)
					onClickListener.onClick(this);
			}
		}
		invalidate();
	}
	
	@Override
	protected int makePressColor() {
		return backgroundColor;
	}

}
