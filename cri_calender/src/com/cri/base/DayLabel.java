package com.cri.base;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class DayLabel extends JLabel {
    	private Color DAY_BG;
		private Dimension DAY_LABEL_SIZE;

		public DayLabel(String s) {
    	super(s);
    	this.setHorizontalAlignment(SwingConstants.CENTER);
    	this.setVerticalAlignment(SwingConstants.CENTER);
    	this.setOpaque(true);
    	this.setBackground(DAY_BG);
    	this.setPreferredSize(DAY_LABEL_SIZE);
    	}
}



	

 

