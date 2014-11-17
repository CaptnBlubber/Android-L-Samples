package s3xy.de.android_lsamples.ui.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.RelativeLayout;
import android.widget.TextView;

import s3xy.de.android_lsamples.R;


public class ButtonFlat extends Button {
	
	TextView textButton;

	public ButtonFlat(Context context, AttributeSet attrs) {
		super(context, attrs);
		
	}
	
	protected void setDefaultProperties(){
		minHeight = 36;
		minWidth = 88;
		rippleSize = 3;
		// Min size
		setMinimumHeight(Utils.dpToPx(minHeight, getResources()));
		setMinimumWidth(Utils.dpToPx(minWidth, getResources()));
		setBackgroundResource(R.drawable.background_transparent);
	}

	@Override
	protected void setAttributes(AttributeSet attrs) {
		// Set text button
		String text = null;
		int textResource = attrs.getAttributeResourceValue(ANDROIDXML,"text",-1);
		if(textResource != -1){
			text = getResources().getString(textResource);
		}else{
			text = attrs.getAttributeValue(ANDROIDXML,"text");
		}
		if(text != null){
			textButton = new TextView(getContext());
			textButton.setText(text.toUpperCase());
			textButton.setTextColor(backgroundColor);
			textButton.setTypeface(null, Typeface.BOLD);
			RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
			params.addRule(RelativeLayout.CENTER_IN_PARENT, RelativeLayout.TRUE);
			textButton.setLayoutParams(params);
			addView(textButton);
		}
		int backgroundColor = attrs.getAttributeResourceValue(ANDROIDXML,"background",-1);
		if(backgroundColor != -1){
			setBackgroundColor(getResources().getColor(backgroundColor));
		}else{
			// Color by hexadecimal
			String background = attrs.getAttributeValue(ANDROIDXML,"background");
			if(background != null)
				setBackgroundColor(Color.parseColor(background));
		}
	}
	
	
	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		if (x != -1) {
			
			Paint paint = new Paint();
			paint.setAntiAlias(true);
			paint.setColor(makePressColor());
			canvas.drawCircle(x, y, radius, paint);
			if(radius > getHeight()/rippleSize)
				radius += rippleSpeed;
			if(radius >= getWidth()){
				x = -1;
				y = -1;
				radius = getHeight()/rippleSize;
				if(onClickListener != null)
					onClickListener.onClick(this);
			}
		}		
		invalidate();
	}
	
	/**
	 * Make a dark color to ripple effect
	 * @return
	 */
	@Override
	protected int makePressColor(){
		return Color.parseColor("#88DDDDDD");
	}
	
	public void setText(String text){
		textButton.setText(text.toUpperCase());
	}
	
	// Set color of background
	public void setBackgroundColor(int color){
		backgroundColor = color;
		if(isEnabled())
			beforeBackground = backgroundColor;
		textButton.setTextColor(color);
	}

	@Override
	public TextView getTextView() {
		return textButton;
	}
	
	public String getText(){
        	return textButton.getText().toString();
 	}

}
