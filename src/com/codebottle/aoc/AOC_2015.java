package com.codebottle.aoc;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class AOC_2015 {

	public AOC_2015() {
	}

	public void run() {
		day1();
		day2();
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

			System.out.println("Day1::Part1::" + out_1 + "::Part2::" + out_2);
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
			System.out.println("Day2::Part1::" + out_1 + "::Part2::" + out_2);
		} catch (Exception e) {
			System.out.println("Error occured::" + e.getMessage());
		}
	}

}
