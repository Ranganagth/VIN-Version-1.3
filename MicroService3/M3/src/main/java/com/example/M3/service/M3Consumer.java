package com.example.M3.service;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.example.M3.model.User;
import com.example.M3.respository.UserRepository;
  
  @Service 
  public class M3Consumer {
	  @Autowired
	  UserRepository userrepo;
	  @Autowired
	  EmailSenderService senderservice;
	  
	  HashMap<String, Integer> vinCounter = new HashMap<String, Integer>();
	  HashMap<String, String> vinSpeeds = new HashMap<String,String>();
	  HashMap<String, String> vinTimestamp = new HashMap<String,String>();
	 
	  int count = 0;
	 
	  
	  
	  @KafkaListener(topics="k2-topic",groupId="mygroup2")
	  public void consumeFromTopic(String message) throws Exception {
		  
	  System.out.println("consumed message "+message);
	  String msg = message;
	  String vin = msg.substring(0, 17);
	  String verified = msg.substring(17, 18);
	  String speeds = msg.substring(18,21);
	  int sp = Integer.parseInt(msg.substring(18,21));
	  String alert = msg.substring(21,22);
	  String checkSpeedandVin = "y";
	  String timeStamp = msg.substring(22);
		
  
  try {
	User obj = new User(vin,verified,sp,alert,timeStamp);
		userrepo.save(obj);
		  if(vinCounter.containsKey(vin)) {
			   
			   
			   if(sp>=100) {
				   vinCounter.put(vin,vinCounter.get(vin)+1);
				   vinSpeeds.put(vin,vinSpeeds.get(vin)+speeds);
				   vinTimestamp.put(vin,vinTimestamp.get(vin)+timeStamp);
				   //System.out.println(vinSpeeds.get(vin));
				   int count = vinCounter.get(vin);
					   if(count == 5) {
							  
						   	  String maildata = vinSpeeds.get(vin);
						   	  String timestampData = vinTimestamp.get(vin);
							  String bd = "Hai There !\nYour Vehicle VIN No is : "+vin
							  +"\nYou are Constantly Overspeeding Your Car"
							  +"\n Please refer below details"
							  +"\n"+maildata.substring(0,3)+"km/hr at "+ timestampData.substring(0,23)
							  +"\n"+maildata.substring(3,6)+"km/hr at "+ timestampData.substring(23,46)
							  +"\n"+maildata.substring(6,9)+"km/hr at "+ timestampData.substring(46,69)
							  +"\n"+maildata.substring(9,12)+"km/hr at "+ timestampData.substring(69,92)
							  +"\n"+maildata.substring(12)+"km/hr at "+ timestampData.substring(92,115)
							  +"\nYour speed Limit is 100 km/hr\nPlease Drive slowly\nSafe driving saves life.";
							 
							  if(verified.equals(checkSpeedandVin) && alert.equals(checkSpeedandVin))  senderservice.sendEmail(bd);
						   	 // String bd = "Hai There !\nYour Vehicle VIN No is : "+vin
								//	      +"\n"+"You Current Speed is "+sp+" km/hr"
									//      +"\nYour speed Limit is 100 km/hr\nPlease Drive slowly\nSafe driving saves life.";
						   	  System.out.println("  "+"\n----------------------------------------------------------------------------");
							  System.out.println("mail Sent "+vin);
							  System.out.println(bd);
							  System.out.println("  "+"----------------------------------------------------------------------------");
							  count = 0;
							  vinCounter.put(vin, count);
							  vinSpeeds.put(vin,"");
							  vinTimestamp.put(vin,"");
						  }
			  }
			  else {
				  count = 0;
				  vinCounter.put(vin, count);
				  vinSpeeds.put(vin,"");
				  vinTimestamp.put(vin,"");
				  
			  }
		   
		   
	   }else {
		   vinCounter.put(vin,0);
		   vinSpeeds.put(vin,"");
		   vinTimestamp.put(vin,"");
		   if(sp>=100) {
			   vinCounter.put(vin,vinCounter.get(vin)+1);
			   vinSpeeds.put(vin,vinSpeeds.get(vin)+speeds);
			   vinTimestamp.put(vin,vinTimestamp.get(vin)+timeStamp);
			   //System.out.println(vinSpeeds.get(vin));
		   }
	   }
	  
  }catch(Exception e) { 
	  System.out.println("Error From DataBase");
    }
  
  	
 
  
  }
  }
