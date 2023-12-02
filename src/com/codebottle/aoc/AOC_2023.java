package com.codebottle.aoc;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class AOC_2023 {
	
	public AOC_2023() {}
	
	public void run() {
		day1();		
	}
	
	public void day1() {

		try {
			String out_1 = "";
			String out_2 = "";
			String input_file = System.getProperty("user.dir") + "\\bin\\input\\2023\\day1.txt";
			List<String> inputs = Files.readAllLines(Paths.get(input_file));	
						
			int total = 0;	
			for (String s : inputs) {
				s = s.replaceAll("[a-z]", "");
				if(s.length() == 1) {
					s = Character.toString(s.charAt(0)) + Character.toString(s.charAt(0));
				}
				else if(s.length() > 2) {
					s = Character.toString(s.charAt(0)) + Character.toString(s.charAt(s.length() - 1));					
				}				
				total = total + Integer.parseInt(s);
			}	
			
			int total2 = 0;
			for (String s : inputs) {
				
				s = s.replace("one", "o1e").replace("two", "t2o").replace("three", "t3e").replace("four", "f4r")
						.replace("five", "f5e").replace("six", "s6x").replace("seven", "s7n").replace("eight", "e8t")
						.replace("nine", "n9e");
								
				s = s.replaceAll("[a-z]", "");
				
				if(s.length() == 1) {
					s = Character.toString(s.charAt(0)) + Character.toString(s.charAt(0));
				}
				else if(s.length() > 2) {
					s = Character.toString(s.charAt(0)) + Character.toString(s.charAt(s.length() - 1));					
				}	
				
				total2 = total2 + Integer.parseInt(s);				
			}

			out_1 = String.valueOf(total);
			out_2 = String.valueOf(total2);
			System.out.println("Day1 :: " + out_1 + " :: " + out_2);
			
		} catch (Exception e) {
			System.out.println("Error occured::" + e.getMessage());
		}
	}

}
