package com.example.palindromexiaoju;

import android.support.v7.app.ActionBarActivity;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends ActionBarActivity {

	private EditText editText;
	private Button testButton;
	private TextView textView1;
	private Button clearButton;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.fragment_main);

//		if (savedInstanceState == null) {
//			getSupportFragmentManager().beginTransaction()
//					.add(R.id.container, new PlaceholderFragment()).commit();
//		}

		editText = (EditText) findViewById(R.id.editText1);
		testButton = (Button) findViewById(R.id.testButton);
		textView1 = (TextView) findViewById(R.id.textView1);
		clearButton = (Button) findViewById(R.id.clearButton);

		testButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				if(isPalindrome(editText.getText().toString()))
					textView1.setText(
							"Bravo! " + editText.getText().toString() 
							+ " est un palindrome!"
							);
				else
					textView1.setText("Nope!");
			}
		});
		
		clearButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				editText.getText().clear();
				textView1.setText("");
			}
		});
		
		
	}
	
	public boolean isPalindrome(String mot) {
		for (int i = 0; i < mot.length()/2; i++) {
			Log.d("deb", "1:"+mot.charAt(i));
			Log.d("deb", "2:"+mot.charAt(mot.length()-i-1));
			if(mot.charAt(i) != mot.charAt(mot.length()-i-1))
				return false;
		}
		return true;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment {

		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_main, container,
					false);
			return rootView;
		}
	}

}
