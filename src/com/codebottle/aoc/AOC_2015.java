package com.codebottle.aoc;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.security.*;
import java.math.BigInteger;

public class AOC_2015 {

	public AOC_2015() {
	}
	
//	public void day() {
//		try {
//			String out_1 = "";
//			String out_2 = "";
//			String input_file = System.getProperty("user.dir") + "\\bin\\input\\2015\\day6.txt";
//			List<String> inputs = Files.readAllLines(Paths.get(input_file));
//
//			for (String s : inputs) {
//				
//			}
//
//			out_1 = String.valueOf(0);
//			out_2 = String.valueOf(0);
//			System.out.println("Day2 :: " + out_1 + " :: " + out_2);
//		} catch (Exception e) {
//			System.out.println("Error occured::" + e.getMessage());
//		}
//	}

	public void run() {
		// day1();
		// day2();
		// day3();
		// day4();
		//day5();
		//day6();
		//day7();
	}

	public void day1() {

		try {
			String input_file = System.getProperty("user.dir") + "\\bin\\input\\2015\\day1.txt";
			String input = new String(Files.readAllBytes(Paths.get(input_file)));
			String out_1 = "";
			String out_2 = "";

			// part1
			int count = 0;
			for (int i = 0; i < input.length(); i++) {
				if (input.charAt(i) == '(') {
					count++;
				} else if (input.charAt(i) == ')') {
					count--;
				}
			}
			out_1 = String.valueOf(count);

			// part2
			count = 0;
			for (int i = 0; i < input.length(); i++) {
				if (input.charAt(i) == '(') {
					count++;
				} else if (input.charAt(i) == ')') {
					count--;
				}
				if (count == -1) {
					count = i + 1;
					break;
				}
			}
			out_2 = String.valueOf(count);

			System.out.println("Day1 :: " + out_1 + " :: " + out_2);
		} catch (Exception e) {
			System.out.println("Error occured::" + e.getMessage());
		}
	}

	public void day2() {

		try {
			String out_1 = "";
			String out_2 = "";
			String input_file = System.getProperty("user.dir") + "\\bin\\input\\2015\\day2.txt";
			List<String> inputs = Files.readAllLines(Paths.get(input_file));

			int l, w, h;
			int a1, a2, a3;
			int temp_low = 0;
			int total_sum1 = 0;
			int total_sum2 = 0;

			for (String s : inputs) {
				String[] lwh = s.split("x");
				l = Integer.parseInt(lwh[0]);
				w = Integer.parseInt(lwh[1]);
				h = Integer.parseInt(lwh[2]);

				a1 = l * w;
				a2 = w * h;
				a3 = l * h;

				temp_low = a1;
				if (a2 < temp_low)
					temp_low = a2;

				if (a3 < temp_low)
					temp_low = a3;

				total_sum1 = total_sum1 + 2 * (a1 + a2 + a3) + temp_low;

				if (w < l) {
					temp_low = l;
					l = w;
					w = temp_low;
				}
				if (h < w) {
					temp_low = w;
					w = h;
					h = temp_low;
				}

				total_sum2 = total_sum2 + 2 * (l + w) + (l * w * h);
			}

			out_1 = String.valueOf(total_sum1);
			out_2 = String.valueOf(total_sum2);
			System.out.println("Day2 :: " + out_1 + " :: " + out_2);
		} catch (Exception e) {
			System.out.println("Error occured::" + e.getMessage());
		}
	}

	public void day3() {

		try {
			String input_file = System.getProperty("user.dir") + "\\bin\\input\\2015\\day3.txt";
			String input = new String(Files.readAllBytes(Paths.get(input_file)));
			String out_1 = "";
			String out_2 = "";

			// part 1

			int x = 0;
			int y = 0;

			Set<String> set = new HashSet<String>();
			set.add("0,0");

			for (int i = 0; i < input.length(); i++) {
				if (input.charAt(i) == '^')
					y++;
				else if (input.charAt(i) == '>')
					x++;
				else if (input.charAt(i) == '<')
					x--;
				else if (input.charAt(i) == 'v')
					y--;

				if (!set.contains(String.valueOf(x) + "," + String.valueOf(y)))
					set.add(String.valueOf(x) + "," + String.valueOf(y));
			}

			out_1 = String.valueOf(set.size());

			// part 2

			int x1 = 0;
			int y1 = 0;
			int x2 = 0;
			int y2 = 0;
			boolean santa_flag = true;

			Set<String> set2 = new HashSet<String>();
			set2.add("0,0");

			for (int i = 0; i < input.length(); i++) {
				if (santa_flag) {
					if (input.charAt(i) == '^')
						y1++;
					else if (input.charAt(i) == '>')
						x1++;
					else if (input.charAt(i) == '<')
						x1--;
					else if (input.charAt(i) == 'v')
						y1--;

					if (!set2.contains(String.valueOf(x1) + "," + String.valueOf(y1)))
						set2.add(String.valueOf(x1) + "," + String.valueOf(y1));
				} else {
					if (input.charAt(i) == '^')
						y2++;
					else if (input.charAt(i) == '>')
						x2++;
					else if (input.charAt(i) == '<')
						x2--;
					else if (input.charAt(i) == 'v')
						y2--;

					if (!set2.contains(String.valueOf(x2) + "," + String.valueOf(y2)))
						set2.add(String.valueOf(x2) + "," + String.valueOf(y2));
				}

				santa_flag = !santa_flag;
			}

			out_2 = String.valueOf(set2.size());

			System.out.println("Day3 :: " + out_1 + " :: " + out_2);
		} catch (Exception e) {
			System.out.println("Error occured::" + e.getMessage());
		}
	}

	public void day4() {
		try {
			String out_1 = "";
			String out_2 = "";
			String input_file = System.getProperty("user.dir") + "\\bin\\input\\2015\\day4.txt";
			String input = new String(Files.readAllBytes(Paths.get(input_file)));
			MessageDigest md = MessageDigest.getInstance("MD5");
			byte[] bytesOfMessage;
			byte[] theMD5digest;
			BigInteger no;

			for (int i = 1; i < Integer.MAX_VALUE; i++) {
				bytesOfMessage = (input + String.valueOf(i)).getBytes();
				theMD5digest = md.digest(bytesOfMessage);
				no = new BigInteger(1, theMD5digest);
				if (no.toString(16).length() == 27 && out_1.equals("")) {
					out_1 = String.valueOf(i);
					if (!out_2.equals(""))
						break;
				}
				if (no.toString(16).length() == 26 && out_2.equals("")) {
					out_2 = String.valueOf(i);
					if (!out_1.equals(""))
						break;
				}
			}

			System.out.println("Day4 :: " + out_1 + " :: " + out_2);
		} catch (Exception e) {
			System.out.println("Error occured::" + e.getMessage());
		}
	}

	public void day5() {
		try {
			String out_1 = "";
			String out_2 = "";
			String input_file = System.getProperty("user.dir") + "\\bin\\input\\2015\\day5.txt";
			List<String> inputs = Files.readAllLines(Paths.get(input_file));

			int nice_strings = 0;
			String temp = "";

			for (String s : inputs) {
				temp = s.replace("a", "").replace("e", "").replace("i", "").replace("o", "").replace("u", "");
				if (s.length() - temp.length() < 3)
					continue;

				if (s.contains("ab") || s.contains("cd") || s.contains("pq") || s.contains("xy"))
					continue;

				boolean doubles = false;
				for (int i = 1; i < s.length(); i++) {
					if (s.charAt(i) == s.charAt(i - 1)) {
						doubles = true;
						break;
					}
				}

				if (!doubles)
					continue;

				nice_strings++;
			}
			out_1 = String.valueOf(nice_strings);

			nice_strings = 0;
			for (String s : inputs) {
				boolean flag1 = false;
				boolean flag2 = false;

				for (int i = 0; i < s.length() - 3; i++) {
					for (int j = i + 2; j < s.length() - 1; j++) {
						if (s.charAt(i) == s.charAt(j) && s.charAt(i + 1) == s.charAt(j + 1)) {
							flag1 = true;
							break;
						}
					}
					if (flag1)
						break;
				}

				for (int i = 0; i < s.length() - 2; i++) {
					if (s.charAt(i) == s.charAt(i + 2)) {
						flag2 = true;
						break;
					}
				}

				if (flag1 && flag2)
					nice_strings++;

			}
			out_2 = String.valueOf(nice_strings);

			System.out.println("Day5 :: " + out_1 + " :: " + out_2);
		} catch (Exception e) {
			System.out.println("Error occured::" + e.getMessage());
		}
	}
	
	public void day6() {
		try {
			String out_1 = "";
			String out_2 = "";
			String input_file = System.getProperty("user.dir") + "\\bin\\input\\2015\\day6.txt";
			List<String> inputs = Files.readAllLines(Paths.get(input_file));
			
			int[][] lights = new int[1000][1000];
			int[][] brightness = new int[1000][1000];
			int x1 = 0;
			int y1 = 0;
			int x2 = 0;
			int y2 = 0;
			boolean off = false; 
			boolean	on = false;
			boolean toggle = false;
			
			for (String s : inputs) {	
				
				off = false;
				on = false;
				toggle = false;
				
				if(s.contains("off")) 
					off = true;
				else if(s.contains("on")) 
					on = true;
				else if(s.contains("toggle")) 
					toggle = true;
				
				s = s.replace(" through ", ",").replace("turn off ", "").replace("turn on ", "").replace("toggle ", "");
				String[] coordinates = s.split(",");
				x1 = Integer.parseInt(coordinates[0]);
				y1 = Integer.parseInt(coordinates[1]);
				x2 = Integer.parseInt(coordinates[2]);
				y2 = Integer.parseInt(coordinates[3]);
				
				
				for(int x = x1; x <= x2; x++) {
					for(int y = y1; y <= y2; y++) {
						if(off) {
							lights[x][y] = 0;
							if(brightness[x][y] > 0)
								brightness[x][y]--;
						}
						else if(on) {
							lights[x][y] = 1;
							brightness[x][y]++;
						}
						else if(toggle) {
							if(lights[x][y] == 1) {
								lights[x][y] = 0;
							}
							else {
								lights[x][y] = 1;
							}
							brightness[x][y] = brightness[x][y] + 2;
						}									
					}
				}				
			}
			int lights_on = 0;
			int total_brightness = 0;
			for(int x = 0; x < 1000; x++) {
				for(int y = 0; y < 1000; y++) {
					if(lights[x][y] == 1)
						lights_on++;
					total_brightness = total_brightness + brightness[x][y];
				}
			}

			out_1 = String.valueOf(lights_on);
			out_2 = String.valueOf(total_brightness);
			System.out.println("Day2 :: " + out_1 + " :: " + out_2);
		} catch (Exception e) {
			System.out.println("Error occured::" + e.getMessage());
		}
	}
	
	public void day7() {
	try {
		String out_1 = "";
		String out_2 = "";
		String input_file = System.getProperty("user.dir") + "\\bin\\input\\2015\\day7.txt";
		List<String> inputs = Files.readAllLines(Paths.get(input_file));
		
		Map<String, String> wires = new HashMap<>();
		List<String[]> circuits = new ArrayList<String[]>();
		
		/*
		    af AND ah -> ai--
		    fm OR fn -> fo--
			NOT lk -> ll
			hz RSHIFT 1 -> is--
			eo LSHIFT 15 -> es--
			0 -> c
            14146 -> b
		 * 
		 * 
		 * 
		 */
		String input1;
		String input2;
		String operand;
		String output;
		String value;
		String temp;
		String[] temp_wires;
		
		for (String s : inputs) {	
						
			if(s.contains("AND") || s.contains("OR") || s.contains("RSHIFT") || s.contains("LSHIFT")) {
				temp = s.replace(" AND ", ",").replace(" OR ", ",").replace(" RSHIFT ", ",").replace(" LSHIFT ", ",").replace(" -> ", ",");
				temp_wires = temp.split(",");
				
				wires.put(temp_wires[0], "-1");
				wires.put(temp_wires[2], "-1");	
				
				if(s.contains("AND") || s.contains("OR"))
					wires.put(temp_wires[1], "-1");	
				
				if(s.contains("AND"))
					circuits.add(new String[] {"&", temp_wires[0], temp_wires[1], temp_wires[2], "-1"});
				else if(s.contains("OR"))
					circuits.add(new String[] {"|", temp_wires[0], temp_wires[1], temp_wires[2], "-1"});
				else if(s.contains("RSHIFT"))
					circuits.add(new String[] {">>", temp_wires[0], temp_wires[1], temp_wires[2], "-1"});
				else if(s.contains("LSHIFT"))
					circuits.add(new String[] {"<<", temp_wires[0], temp_wires[1], temp_wires[2], "-1"});
			}
			else if(s.contains("NOT")) {
				temp = s.replace("NOT ", "").replace(" -> ", ",");
				temp_wires = temp.split(",");
				
				wires.put(temp_wires[0], "-1");
				wires.put(temp_wires[1], "-1");
				
				circuits.add(new String[] {"~", temp_wires[0], "", temp_wires[1], "-1"});
			}
			else {
				temp = s.replace(" -> ", ",");
				temp_wires = temp.split(",");	
				
				wires.put(temp_wires[0], "-1");
				wires.put(temp_wires[1], "-1");
				
				circuits.add(new String[] {"=", temp_wires[0], "", temp_wires[1], "-1"});
			}
		}
		
//		while(wires.get("a").equals("-1")) {
//			for (String[] arr : circuits) {
//				if(arr[4].equals("-1")) {
//					
//				}
//			}
//		}
		

		out_1 = String.valueOf(0);
		out_2 = String.valueOf(0);
		System.out.println("Day7 :: " + out_1 + " :: " + out_2);
	} catch (Exception e) {
		System.out.println("Error occured::" + e.getMessage());
	}
}
	
}
