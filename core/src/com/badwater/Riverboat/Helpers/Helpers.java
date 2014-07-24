package com.badwater.Riverboat.Helpers;

import java.util.Random;

public class Helpers {
	public static Random random = new Random();
	
	public static float getRandomFloat(){
		return random.nextFloat();
	}
	
	public static int getRandomRange(int max, int min){
		int retVal = random.nextInt((max - min) + 1) + min;
		return retVal;
	}

	public static boolean coinFlip(float f){
		float rn = random.nextFloat();
		if (rn < f){
			return true;
		}
	return false;
	}

}
