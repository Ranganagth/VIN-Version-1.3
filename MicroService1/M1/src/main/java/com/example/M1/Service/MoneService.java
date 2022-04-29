package com.example.M1.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.TimeZone;
import java.util.concurrent.ThreadLocalRandom;

import org.springframework.stereotype.Service;

@Service
public class MoneService {
	
	//Generate Random VIN Number
	public String generateVin() 
	{       
		 StringBuilder sb = new StringBuilder(10);
         String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ" + "0123456789";
	        for (int j = 0; j < 11; j++) {
	               int index = (int)(AlphaNumericString.length() * Math.random());
	               sb.append(AlphaNumericString.charAt(index));
	           }
	           Random rnd = new Random();
	           int number = rnd.nextInt(999999);
	           String covertednumber = String.format("%06d", number);
	           String vin = sb+covertednumber;
			return vin;
	}
	
	
	
	//Generate Random Speed
			public String generateSpeed() {
				int randomSpeed = ThreadLocalRandom.current().nextInt(0, 300 + 1);
			    return String.format("%03d", randomSpeed);
				
			}
	
			
			public String getSpeedWithPercentage(String speed, Integer Delay) {
				Integer sp= Integer.parseInt(speed);
				Integer delay = Delay;
				 int percent = 0;
				Boolean inc_or_dec = incOrDec();
				//System.out.println(inc_or_dec);
				if(inc_or_dec) {
					if(delay>=1 && delay<=5) {
						percent = (int)Math.floor( Math.random()*(5-1+1)+1);
					}
					else if(delay>5 && delay <=10) {
							percent = (int)Math.floor(Math.random()*(10-2+1)+2);
					}
					else if(delay>10 && delay <=15) {
						 if(sp<=150) percent = (int)Math.floor(Math.random()*(20-11+1)+11);
						 else percent=(int)Math.floor(Math.random()*(15-11+1)+11);
					}
					else if(delay>15 && delay <=30) {
						if(sp<=150) percent = (int)Math.floor(Math.random()*(30-16+1)+16);
						 else percent = (int)Math.floor(Math.random()*(25-16+1)+16);
					}
					else {
						if(sp<=150) percent = (int)Math.floor(Math.random()*(30-40+1)+40);
						 else percent = (int)Math.floor(Math.random()*(25-16+1)+16);
					}
					sp = sp + percent;
				}else 
				{
					if(delay>=1 && delay<=5) {
						percent = (int)Math.floor(Math.random()*(5-1+1)+1);
					}
					else if(delay>5 && delay <=10) {
							percent = (int)Math.floor(Math.random()*(10-2+1)+2);
					}
					else if(delay>10 && delay <=15) {
						 if(sp<=150) percent=(int)Math.floor(Math.random()*(15-11+1)+11);
						 else percent = (int)Math.floor(Math.random()*(20-11+1)+11);
					}
					else if(delay>15 && delay <=30) {
						if(sp<=150) percent =(int)Math.floor(Math.random()*(25-16+1)+16);
						 else percent =  (int)Math.floor(Math.random()*(30-16+1)+16);
					}
					else {
						if(sp<=150) percent = (int)Math.floor(Math.random()*(25-16+1)+16);
						 else percent = (int)Math.floor(Math.random()*(30-40+1)+40);
					}
					sp = sp - percent;
					
				}
				
			    if(sp>=300)sp = 300;
			    else if(sp<=0) sp = 0;
			    System.out.println("final speed = "+sp);
				return Integer.toString(sp);
			}
			
			private Boolean incOrDec() {
				Random myrand = new Random();
				return myrand.nextBoolean();
			}

			//Generate TimeStamp
			public String generateTimestamp() {
				
				/*
				 * Date date = new Date(); Timestamp timestamp = new Timestamp(date.getTime());
				 * SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				 * String timeStamp = dateFormat.format(timestamp);
				 */
				 
					  SimpleDateFormat gmtDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS");
					  gmtDateFormat.setTimeZone(TimeZone.getTimeZone("Asia/Kolkata")); //CurrentDate Time in GMT 
					  String timeStamp = gmtDateFormat.format(new Date());
					 
				return timeStamp;
				
			}
			//Add Delay to Generate VIN Number
			public void addDelay(Integer delay) 
			{
				long sleepTime = delay*1000;        //Delay starts
					try 
					{
						Thread.sleep(sleepTime);
					} 
					catch (Exception e) 
					{
						System.out.println(e);
					}                                        //Delay ends
				
			}
			
}
