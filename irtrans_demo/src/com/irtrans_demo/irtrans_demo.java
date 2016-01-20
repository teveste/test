package com.irtrans_demo;

import com.irtrans_demo.IRTransPreferences;
import com.irtrans_demo.R;

import android.app.Activity;
import android.os.Bundle;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


public class irtrans_demo extends Activity 
implements View.OnClickListener
{
	public static final int EDIT_ID = Menu.FIRST+2;
    public static String ip_addr;
	TextView headline;
    Button button_up;
    Button button_down;
    Button button_left;
    Button button_right;
    Button button_ok;
    Button button_play;
    Button button_pause;
    Button button_stop;
    Button button_vol_up;
    Button button_vol_down;
    
    
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        setContentView(R.layout.main);
        
        headline = (TextView)findViewById(R.id.headline);
        button_up=(Button)findViewById(R.id.button_up);
        button_up.setOnClickListener(this);
        button_down=(Button)findViewById(R.id.button_down);
        button_down.setOnClickListener(this);
        button_left=(Button)findViewById(R.id.button_left);
        button_left.setOnClickListener(this);
        button_right=(Button)findViewById(R.id.button_right);
        button_right.setOnClickListener(this);
        button_ok=(Button)findViewById(R.id.button_ok);
        button_ok.setOnClickListener(this);
        button_play=(Button)findViewById(R.id.button_play);
        button_play.setOnClickListener(this);
        button_pause=(Button)findViewById(R.id.button_pause);
        button_pause.setOnClickListener(this);
        button_stop=(Button)findViewById(R.id.button_stop);
        button_stop.setOnClickListener(this);
        button_vol_up=(Button)findViewById(R.id.button_vol_up);
        button_vol_up.setOnClickListener(this);
        button_vol_down=(Button)findViewById(R.id.button_vol_down);
        button_vol_down.setOnClickListener(this);
    }
    
    // on return from preference activity reads out the IP-addr
    @Override
	public void onResume() {
		super.onResume();
		
		SharedPreferences prefs=PreferenceManager.getDefaultSharedPreferences(this);
		
		ip_addr = prefs.getString("ip_address", "");
		
	}
    
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		menu.add(Menu.NONE, EDIT_ID, Menu.NONE, "Prefs")
				.setIcon(R.drawable.misc)
				.setAlphabeticShortcut('e');

		return(super.onCreateOptionsMenu(menu));
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
			case EDIT_ID:
				startActivity(new Intent(this, IRTransPreferences.class));
				return(true);
		}

		return(super.onOptionsItemSelected(item));
	}
	
	@Override
	public void onClick(View view) 
	{
		String result = "";
		
    	if(view == button_up)
    	{
    		result = IRTrans.sendCommand(IRTrans.Commands.BUTTON_UP);
    	}
    	if(view == button_down)
    	{
    		result = IRTrans.sendCommand(IRTrans.Commands.BUTTON_DOWN);
    	}
        if(view == button_left)
    	{
        	result = IRTrans.sendCommand(IRTrans.Commands.BUTTON_LEFT);
    	}
        if(view == button_right)
    	{
        	result = IRTrans.sendCommand(IRTrans.Commands.BUTTON_RIGHT);
    	}
        if(view == button_ok)
    	{
        	result = IRTrans.sendCommand(IRTrans.Commands.BUTTON_OK);	
    	}
        if(view == button_play)
    	{
        	result = IRTrans.sendCommand(IRTrans.Commands.BUTTON_PLAY);
    	}
        if(view == button_pause)
    	{
        	result = IRTrans.sendCommand(IRTrans.Commands.BUTTON_PAUSE);	
    	}
        if(view == button_stop)
    	{
        	result = IRTrans.sendCommand(IRTrans.Commands.BUTTON_STOP);
    	}
        if(view == button_vol_up)
    	{
        	result = IRTrans.sendCommand(IRTrans.Commands.BUTTON_VOL_UP);
    	}
        if(view == button_vol_down)
    	{
        	result = IRTrans.sendCommand(IRTrans.Commands.BUTTON_VOL_DOWN);
    	}
        // displays the result of the transaction
        // which is the return string from the IRTrans device in most cases
        Toast.makeText(this, result, Toast.LENGTH_SHORT).show();
	}
}