package com.codebottle.aoc;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AOC_2023 {
	
	public AOC_2023() {}
	
	public void run() {
		//day1();	
		//day2();
		day3();
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

	public void day2() {
		try {
			String out_1 = "";
			String out_2 = "";
			String input_file = System.getProperty("user.dir") + "\\bin\\input\\2023\\day2.txt";
			List<String> inputs = Files.readAllLines(Paths.get(input_file));	
			
			int gameID = 0;
			int total = 0;	
			String temp = "";
			boolean skip = false;
			String[] sets;
			String[] cubes;
			
			for (String s : inputs) {				
				temp = s.substring(0, s.indexOf(":") + 1);
				gameID = Integer.parseInt(temp.replace("Game ", "").replace(":", ""));
				s = s.replace(temp, "");
				skip = false;
				sets = s.split(";");					
				
				for (String set : sets) {
					cubes = set.split(",");
					for (String cube : cubes) {						
						Integer num = Integer.parseInt(cube.replace(" ","").replace("red", "").replace("green", "").replace("blue", ""));
						if(cube.contains("red") && num > 12) {
							skip = true;
							break;
						}
						else if(cube.contains("green") && num > 13) {
							skip = true;
							break;
						}						
						else if(cube.contains("blue") && num > 14) {
							skip = true;
							break;
						}						
					}
					if(skip) {
						break;
					}
				}
				
				if(!skip) {
					total = total + gameID;
				}
				
			}			
			out_1 = String.valueOf(total);	
			
			int total2 = 0;	
			int r = 1;
			int g = 1;
			int b = 1;
			
			for (String s : inputs) {				
				temp = s.substring(0, s.indexOf(":") + 1);				
				s = s.replace(temp, "");				
				sets = s.split(";");
				r = 1;
				g = 1;
				b = 1;
				
				for (String set : sets) {
					cubes = set.split(",");
					for (String cube : cubes) {						
						Integer num = Integer.parseInt(cube.replace(" ","").replace("red", "").replace("green", "").replace("blue", ""));
						if(cube.contains("red") && num > r) {							
							r = num;
						}
						else if(cube.contains("green") && num > g) {
							g = num;
						}						
						else if(cube.contains("blue") && num > b) {
							b = num;
						}						
					}					
				}
				
				total2 = total2 + ( r * g * b);				
			}
			out_2 = String.valueOf(total2);
			
			System.out.println("Day2 :: " + out_1 + " :: " + out_2);
			
		} catch (Exception e) {
			System.out.println("Error occured::" + e.getMessage());
		}
	}
	
	public void day3() {
		
		try {
			String out_1 = "";
			String out_2 = "";
			String input_file = System.getProperty("user.dir") + "\\bin\\input\\2023\\day3.txt";
			List<String> inputs = Files.readAllLines(Paths.get(input_file));
			
			int total = 0;
			Pattern pattern = Pattern.compile("\\d+");			
			
			for(int i = 0; i < inputs.size(); i++) {
				//System.out.println(inputs.get(i));
				Matcher match = pattern.matcher(inputs.get(i));
				while(match.find()) {
					//System.out.println(match.group() + "::" + match.start() + "::" + (match.end() - 1));
					
					String line = inputs.get(i);
					String prev_line = "";
					String next_line = "";
					
					if(i > 0) {
						prev_line = inputs.get(i - 1);
					}
					
					if(i < inputs.size() - 1) {
						next_line = inputs.get(i + 1);
					}					
					
					if(day3_check(line, prev_line, next_line, match.start(), match.end() - 1)) {						
						total = total + Integer.parseInt(match.group());
					}					
				}
			}	
	
			out_1 = String.valueOf(total);
			out_2 = String.valueOf(0);
			System.out.println("Day3 :: " + out_1 + " :: " + out_2);
			
		} catch (Exception e) {
			System.out.println("Error occured::" + e.getMessage());
		}
	}
	
	public boolean day3_check(String line, String prev_line, String next_line, int start_index, int end_index) {		
		
		if(start_index > 0 && line.charAt(start_index - 1) != '.')
			return true;		
		
		if(end_index < line.length() - 1 && line.charAt(end_index + 1) != '.')
			return true;		
		
		int left_index = start_index - 1;		
		if(start_index == 0)
			left_index = 0;
		
		int right_index = end_index + 1;		
		if(end_index == line.length() - 1)
			right_index = line.length() - 1;		
		
		if(! prev_line.equals("")) {
			for(int x = left_index; x <= right_index; x++) {
				if(prev_line.charAt(x) != '.')
					return true;
			}				
		}
		
		if(! next_line.equals("")) {
			for(int x = left_index; x <= right_index; x++) {
				if(next_line.charAt(x) != '.')
					return true;
			}				
		}
		
		return false;
	}
	
}
