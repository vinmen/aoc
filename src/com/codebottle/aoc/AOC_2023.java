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
		//day3();
		day4();
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
			Map<String, List<Integer>> map = new HashMap<String, List<Integer>>();
			
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
					
					if(day3_part1_helper(line, prev_line, next_line, match.start(), match.end() - 1)) {						
						total = total + Integer.parseInt(match.group());
					}
					
					day3_part2_helper(line, prev_line, next_line, i, 
							Integer.parseInt(match.group()), match.start(), match.end() - 1, map);
				}
			}	
	
			out_1 = String.valueOf(total);
			
			int total2 = 0;
			for(String s : map.keySet()) {
				if(map.get(s).size() > 1) {
					total2 = total2 + (map.get(s).get(0) * map.get(s).get(1));
				}
			}
			
			out_2 = String.valueOf(total2);
			System.out.println("Day3 :: " + out_1 + " :: " + out_2);
			
		} catch (Exception e) {
			System.out.println("Error occured::" + e.getMessage());
		}
	}
	
	public boolean day3_part1_helper(String line, String prev_line, String next_line, int start_index, int end_index) {		
		
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
	
	public void day3_part2_helper(String line, String prev_line, String next_line, 
			int line_index, int number, int start_index, int end_index, Map<String, List<Integer>> map) {		
		
		String key = "";
		
		if(start_index > 0 && line.charAt(start_index - 1) == '*')
			key =  String.valueOf(line_index) + "," + String.valueOf(start_index - 1);							
		
		if(end_index < line.length() - 1 && line.charAt(end_index + 1) == '*')
			key = String.valueOf(line_index) + "," + String.valueOf(end_index + 1);		
		
		int left_index = start_index - 1;		
		if(start_index == 0)
			left_index = 0;
		
		int right_index = end_index + 1;		
		if(end_index == line.length() - 1)
			right_index = line.length() - 1;		
		
		if(! prev_line.equals("")) {
			for(int x = left_index; x <= right_index; x++) {
				if(prev_line.charAt(x) == '*')
					key = String.valueOf(line_index - 1) + "," + String.valueOf(x);
			}				
		}
		
		if(! next_line.equals("")) {
			for(int x = left_index; x <= right_index; x++) {
				if(next_line.charAt(x) != '.')
					key = String.valueOf(line_index + 1) + "," + String.valueOf(x);
			}				
		}
		
		if(!key.equals("")) {						
			if(map.containsKey(key)) {
				map.get(key).add(number);
			}
			else {
				List<Integer> data = new ArrayList<Integer>();
				data.add(number);
				map.put(key, data);
			}
		}
		
	}
	
	public void day4() {

		try {
			String out_1 = "";
			String out_2 = "";
			String input_file = System.getProperty("user.dir") + "\\bin\\input\\2023\\day4.txt";
			List<String> inputs = Files.readAllLines(Paths.get(input_file));			
			
			int total = 0;		
			for (String s : inputs) {
				
				s = s.substring(s.indexOf(":") + 2);
				String[] temp = s.split(" \\| ");
				List<String> winners = Arrays.asList(temp[0].replace("  ", " ").split(" "));
				List<String> numbers = Arrays.asList(temp[1].replace("  ", " ").split(" "));
				int win_count = 0;
				
				for(String num : numbers) {
					if(winners.contains(num))
						win_count++;					
				}
				
				if(win_count > 0)
					total = total + (int) Math.pow(2, win_count - 1);	
			}			

			out_1 = String.valueOf(total);
			
			int total2 = 0;
			int card_number = 0;
			Pattern pattern = Pattern.compile("\\d+");
			Map<Integer, Integer> map = new HashMap<Integer, Integer>();
			for(int i = 0; i < inputs.size(); i++) {
				map.put(i + 1, 1);
			}
			
			for (String s : inputs) {
				
				Matcher m = pattern.matcher(s.substring(0, s.indexOf(":")));	
				while (m.find()) {
					card_number = Integer.valueOf(m.group());
				}	
				
				s = s.substring(s.indexOf(":") + 2);
				String[] temp = s.split(" \\| ");
				List<String> winners = Arrays.asList(temp[0].replace("  ", " ").split(" "));
				List<String> numbers = Arrays.asList(temp[1].replace("  ", " ").split(" "));
				int win_count = 0;
				
				for(String num : numbers) {
					if(winners.contains(num))
						win_count++;					
				}
				
				if(win_count > 0 || card_number < map.size()) {
					for(int j = 0; j < map.get(card_number); j++) {
						for(int i = 1; i <= win_count; i++) {
							map.put(card_number + i, map.get(card_number + i) + 1);
						}
					}
				}					
			}
			
			for(Integer count : map.values()) {
				total2 = total2 + count;
			}
			
			out_2 = String.valueOf(total2);
			System.out.println("Day4 :: " + out_1 + " :: " + out_2);
			
		} catch (Exception e) {
			System.out.println("Error occured::" + e.getMessage());
		}
	}
	
}
