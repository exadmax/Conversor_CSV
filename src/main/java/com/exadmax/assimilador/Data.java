package com.exadmax.assimilador;

import java.text.ParseException;
import java.util.Date;

public class Data {
	

	public static void main(String[] args) throws ParseException {
		String date = "16/02/18";
		String dateFour = "16/02/18";
		Date d = null;

		// four digit input
		StringBuffer buffer = new StringBuffer();
		try {
			String tokens[] = dateFour.split("/"); // dd=0 MM=1 yyyy=2 or yy=2
			String year = tokens[2];
			if (year.length() < 3) {
				if (Integer.valueOf(year) > 70) {
					year = "19" + year;
					System.out.println(year);
				} else {
					year = "20" + year;
					System.out.println(year);
				}
			}
			buffer.append("[Date: year: " + year + " month : " + Integer.valueOf(tokens[1]) + " day:" + Integer.valueOf(tokens[0]) + " ] for date type " + date);
		} catch (Exception e) {
			System.out.println("ops");
		}
		System.out.println(buffer.toString());
	}

}
