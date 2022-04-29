package com.example.M1.Controllers;
import java.util.ArrayList;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.example.M1.Service.MQConfig;
import com.example.M1.Service.MoneService;

@CrossOrigin
@RestController
@Async


public class M1Controller {

		@Autowired
		private RabbitTemplate template;	
		@Autowired
		MoneService service;
		
		@PostMapping("/data")
		public String genereateVins(@RequestBody UserDetailsRequestData requestdetails ) {
			Integer vinCount = requestdetails.getVinCount();
			Integer delay = requestdetails.getDelay();
		    String same_Vin = requestdetails.getDifferent();
			System.out.println(vinCount);
			System.out.println(delay);
			System.out.println(same_Vin);
			String vin;
			ArrayList<String> myArr = new ArrayList<String>();
			
			switch(same_Vin) { 
			case "Y":                                   //For SAME VIN NUMBER
				vin = service.generateVin();
			    String sSpeed = service.generateSpeed();              //Generate VIN Number
			    for(int i = 0;i<vinCount;i++) {
					if(i==0) {
						myArr.add(sSpeed);
						String vinAndSpeedAndTime = vin+sSpeed+service.generateTimestamp();
						template.convertAndSend(MQConfig.EXCHANGE,
				                MQConfig.ROUTING_KEY, vinAndSpeedAndTime);
						service.addDelay(delay);
					}
					else {
						String updatedSpeed = service.getSpeedWithPercentage(sSpeed,delay);
						sSpeed = updatedSpeed;
						myArr.add(sSpeed);
						String vinAndSpeedAndTime = vin+String.format("%03d",Integer.parseInt(sSpeed))+service.generateTimestamp();
						template.convertAndSend(MQConfig.EXCHANGE,
				                MQConfig.ROUTING_KEY, vinAndSpeedAndTime);
						service.addDelay(delay);
					}
				}
				System.out.println(myArr);
				myArr.clear();
				return "DATA GENERATED";
		
			case "N":
				for(int i = 0;i<vinCount;i++)
				{
					//Delay starts
					 vin = service.generateVin();
					 service.addDelay(delay);
		             sSpeed = service.generateSpeed();
		             String vinAndSpeedAndTime = vin+sSpeed+ service.generateTimestamp();
		            
		             template.convertAndSend(MQConfig.EXCHANGE,
				                MQConfig.ROUTING_KEY, vinAndSpeedAndTime);
				}
				
				return "DATA GENERATED";
		}
			
			return "DATA GENERATED";
		}
		
	

}
