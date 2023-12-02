package com.codebottle;

import com.codebottle.aoc.AOC_2015;
import com.codebottle.aoc.AOC_2023;

public class AOC {
	String year = "2015";
	
	public AOC() {}
	
	public AOC(String year) {
		this.year = year;
	}
	
	public void run() {
		if(year.equals("2015")) {
			AOC_2015 aco2015 = new AOC_2015();
			aco2015.run();
		}
		else if(year.equals("2023")) {
			AOC_2023 aco2023 = new AOC_2023();
			aco2023.run();
		}
	}
}
