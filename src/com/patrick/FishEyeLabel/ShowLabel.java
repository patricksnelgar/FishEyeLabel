package com.patrick.FishEyeLabel;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.*;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by Patrick on 04-Nov-15.
 */
public class ShowLabel extends Activity {

	final String TAG = "FishEye Label";
	EditText counterText;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

		setContentView(R.layout.showlabel);

		counterText = (EditText)findViewById(R.id.counterText);

		View decView = findViewById(R.id.decButton);
		decView.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				int count = Integer.parseInt(counterText.getText().toString());
				Log.d(TAG, "(Decrement) Count is " + count);
				count--;
				if(count < 0) count = 0;
				Log.d(TAG, "(Decrement) New count is " + count);
				counterText.setText(String.format("%03d%n", count).trim());
			}
		});

		View incView = findViewById(R.id.incButton);
		incView.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				int count = Integer.parseInt(counterText.getText().toString());
				Log.d(TAG, "(Increment) Count is " + count);
				count++;
				if(count > 999) count = 999;
				Log.d(TAG, "(Increment) New count is " + count);
				counterText.setText(String.format("%03d%n", count).trim());
			}
		});

		counterText.setOnEditorActionListener(mEditorListener);

	}

	protected final TextView.OnEditorActionListener mEditorListener = new TextView.OnEditorActionListener(){
		@Override
		public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
			if(i == EditorInfo.IME_ACTION_SEARCH ||
					i == EditorInfo.IME_ACTION_DONE ||
					keyEvent.getAction() == KeyEvent.ACTION_DOWN &&
							keyEvent.getKeyCode() == KeyEvent.KEYCODE_ENTER) {
				int count = Integer.parseInt(counterText.getText().toString());
				Log.d(TAG, "Counter value changed " + count);
				counterText.setText(String.format("%03d%n", count).trim());
			}
			return false;
		}

	};
}