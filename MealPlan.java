// This file contains: Variables, Conditions, User-defined methods (accessors,return a value),
//                     Constructor, File I/O

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class MealPlan
{
	String mealPlanName;
	int boardMealsPerQtr;
	int wildCatPointsPerQtr;
	
	// Constructor - Default
	public MealPlan()
	{
		this.boardMealsPerQtr=0;
		this.wildCatPointsPerQtr=0;
	}
	
	// Constructor
	public MealPlan(String plan)
	{
		if(plan.equals("A"))
		{
			this.mealPlanName="A";
			this.boardMealsPerQtr=140;
			this.wildCatPointsPerQtr=105;
		}
		else if(plan.equals("B"))
		{
			this.mealPlanName="B";
			this.boardMealsPerQtr=117;
			this.wildCatPointsPerQtr=300;			
		}
		else if(plan.equals("C"))
		{
			this.mealPlanName="C";
			this.boardMealsPerQtr=210;
			this.wildCatPointsPerQtr=150;			
		}
	}
	
	// Accessor
	public String getMealPlanName()
	{
		return this.mealPlanName;
	}
	
	// Accessor
	public int getBoardMealsPerQtr()
	{
		return this.boardMealsPerQtr;
	}
	
	// Accessor
	public int getWildCatPointsPerQtr()
	{
		return this.wildCatPointsPerQtr;
	}
	
	// Print to Screen
	public void getMealPlanInfo()
	{
		System.out.printf("Meal Plan:                  %s\n",mealPlanName);
		System.out.printf("Board Meals Per Quarter:    %d\n",boardMealsPerQtr);
		System.out.printf("Wildcat Points Per Quarter: %d\n",wildCatPointsPerQtr);
	}
	
	// Print to File
	public void getMealPlanInfo(PrintWriter outputFile)
	{
		outputFile.println("Meal Plan:                  "+mealPlanName);
		outputFile.println("Board Meals Per Quarter:    "+boardMealsPerQtr);
		outputFile.println("Wildcat Points Per Quarter: "+wildCatPointsPerQtr);
	}
}