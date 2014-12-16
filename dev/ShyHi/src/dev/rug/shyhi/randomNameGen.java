package dev.rug.shyhi;

import java.util.Random;

public class randomNameGen {
	private static String[] adjArr= {"numerous","flagrant","wild","comfortable","incandescent","rare","pushy","insidious","workable","dirty","doubtful","cruel","friendly","productive","busy","tough","thinkable","repulsive","colorful","excellent","succinct","honorable","aggressive","flawless","narrow"};
	private static String[] nounArr = {"zebra","ocelot","mongoose","turtle","potato","moose"};
	
	public static String randomName(){
		Random rand = new Random();
	    int randomNum = rand.nextInt(adjArr.length-1);
	    String adj = adjArr[randomNum];
	    int randNum = rand.nextInt(nounArr.length-1);
	    String noun = nounArr[randNum];
	    return adj+" "+noun;
	}
}	
