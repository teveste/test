package com.irtrans_demo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.lang.Thread;
import java.net.Socket;
import java.net.UnknownHostException;


public class IRTrans 
{	
	// here define the name of the remote control 
	// which is the same as the .rem file at ..\IRTrans\remotes
	static String remote = "mediacenter,"; 
		
	enum Commands
	{
		BUTTON_UP,
		BUTTON_DOWN,
		BUTTON_LEFT,
		BUTTON_RIGHT,
		BUTTON_OK,
		BUTTON_PLAY,
		BUTTON_PAUSE,
		BUTTON_STOP,
		BUTTON_VOL_UP,
		BUTTON_VOL_DOWN
	}
	
	static Socket s;
	static boolean connectionOpen = false;
	
	public IRTrans() 
	{
	}
	
		
	
	public static void closeConnection()
	{
		if(connectionOpen)
		{
			try {
				s.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			connectionOpen = false;
		}
	}
	
	static void delay( int delayMs)
	{
		try {
			Thread.sleep(delayMs);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	// compiles the command string depending on the button ID
	// if you have several remotes you need to change the name 'remote'
	// change the command to the right name regarding the .rem file
	// i.e. mediacenter.rem
	// [up][T]0[D]S11101010000000000011110000010000011110 for the command 'up'
	static String sendCommand(IRTrans.Commands command)
	{
		String status = "";
		
		switch(command)
		{
		case BUTTON_UP:
			status = sendCommandToIrtrans("Asnd " + remote + "up,0");
			break;
		case BUTTON_DOWN:
			status = sendCommandToIrtrans("Asnd " + remote + "down,0");
			break;
		case BUTTON_LEFT:
			status = sendCommandToIrtrans("Asnd " + remote + "left,0");
			break;
		case BUTTON_RIGHT:
			status = sendCommandToIrtrans("Asnd " + remote + "right,0");
			break;
		case BUTTON_OK:
			status = sendCommandToIrtrans("Asnd " + remote + "ok,0");
			break;
		case BUTTON_PLAY:
			status = sendCommandToIrtrans("Asnd " + remote + "play,0");
			break;
		case BUTTON_PAUSE:
			status = sendCommandToIrtrans("Asnd " + remote + "Pause,0");
			break;
		case BUTTON_STOP:
			status = sendCommandToIrtrans("Asnd " + remote + "Stop,0");
			break;
		case BUTTON_VOL_UP:
			status = sendCommandToIrtrans("Asnd " + remote + "Vol+,0");
			break;
		case BUTTON_VOL_DOWN:
			status = sendCommandToIrtrans("Asnd " + remote + "Vol-,0");
			break;
		default:
			
		}
		return status;
	}
	
	// send the command over TCP to the IRTrans device on the configured IP
	static String sendCommandToIrtrans(String command)
	{
		PrintWriter output = null;
		String returnVal = "";
		
		try
		{
			if(!connectionOpen)
			{
				if(irtrans_demo.ip_addr.length() > 10)
				{
				s = new Socket(irtrans_demo.ip_addr, 21000);
					
				output = new PrintWriter(s.getOutputStream(), true);	
				output.print("ASCI");
				delay(10);

				connectionOpen = true;
				}
				else
				{
					return "Please configure the IP-addr!";						
				}
			}
			else
			{
				if(s != null)
				{
					output = new PrintWriter(s.getOutputStream(), true);
				}
			}
			output.println(command);
			BufferedReader input = new BufferedReader(new InputStreamReader(s.getInputStream()));
			String st = input.readLine();

			returnVal = st;

			if(!st.contains("**00018 RESULT OK"))
			{
				s.close();
				connectionOpen = false;
				delay(1000);
			}
		}
		catch(UnknownHostException e)
		{
			return e.toString();
		}
		catch(IOException e)
		{
			return e.toString();
		}
		
		return returnVal;
	}
}
