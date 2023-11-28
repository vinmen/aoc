package com.codebottle;

import com.codebottle.aoc.AOC_2015;

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
	}
}
