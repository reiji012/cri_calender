package com.cri.watch;
import java.text.SimpleDateFormat;
import java.util.Date;

public class WatchMainFrame {
	public static void main (String args[]) {
		Date d = new Date();

		SimpleDateFormat d1 = new SimpleDateFormat("yyyy/MM/dd");
		String c1 = d1.format(d);
		System.out.println(c1);

		SimpleDateFormat d2 = new SimpleDateFormat("hh:mm");
		String c2 = d2.format(d);
		System.out.println(c2);
	}


}
