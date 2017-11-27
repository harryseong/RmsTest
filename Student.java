// This file contains: Variables, User-defined methods(accessors,mutators,pass arguments,return a value),
//                     this, Aggregation, File I/O, Wrapper

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Student
{
	private String firstName;
	private String lastName;
	private int studentID;
	private String netID;
	private String gradeYear;
	private String emailAddress;
	private String phoneNumber;
	private String major;
	private Dorm dorm;
	private Room room;
	private MealPlan mealPlan;
	
	// Constructor for Student object.
	public Student(String firstName, String lastName, int studentID, String netID, String gradeYear, String emailAddress, String phoneNumber, String major)
	{
		this.firstName=firstName;
		this.lastName=lastName;
		this.studentID=studentID;
		this.netID=netID;
		this.gradeYear=gradeYear;
		this.emailAddress=emailAddress;
		this.phoneNumber=phoneNumber;
		this.major=major;
	}
	
	// This method will check for dorms with vacancies to assign a student to a dorm.
	// It will also check and assign a student to an unoccupied room.
	public void assignDorm(Dorm dorm)
	{
		this.dorm=dorm;
	}
	
	// Mutator
	public void assignRoom(Room room)
	{
		this.room=room;
	}
	
	// Mutator
	public void assignMealPlan(MealPlan mealPlan)
	{
		this.mealPlan=mealPlan;
	}
	
	// Accessor
	public String getStudentName()
	{
		String studentName=this.lastName+", "+this.firstName;
		return studentName;
	}
	
	// Accessor
	public String getStudentID()
	{
		String studentID=Integer.toString(this.studentID);
		return studentID;
	}
	
	// Accesor
	public String getStudentRoomNumber()
	{
		return Integer.toString(room.getRoomNumber());
	}
	
	// Print to Screen
	public void getStudentInfo()
	{
		String firstName=this.firstName;
		String lastName=this.lastName;
		int studentID=this.studentID;
		String netID=this.netID;
		String gradeYear=this.gradeYear;
		String emailAddress=this.emailAddress;
		String phoneNumber=this.phoneNumber;
		String major=this.major;
		String dormName=this.dorm.getDormName();
		int roomNumber=this.room.getRoomNumber();
		String mealPlanName=this.mealPlan.getMealPlanName();
		
		System.out.printf("Name:           %s %s\n",firstName,lastName);
		System.out.printf("Student ID #:   %d\n",studentID);
		System.out.printf("NetID:          %s\n",netID);
		System.out.printf("Year:           %s\n",gradeYear);
		System.out.printf("Email Address:  %s\n",emailAddress);
		System.out.printf("Phone #:        %s\n",phoneNumber);
		System.out.printf("Declared Major: %s\n",major);	
		System.out.printf("Dorm:           %s\n",dormName);	
		System.out.printf("Room #:         %d\n",roomNumber);
		System.out.printf("Meal Plan:      %s\n",mealPlanName);
	}
	
	// Print to File
	public void getStudentInfo(PrintWriter outputFile)
	{
		String firstName=this.firstName;
		String lastName=this.lastName;
		int studentID=this.studentID;
		String netID=this.netID;
		String gradeYear=this.gradeYear;
		String emailAddress=this.emailAddress;
		String phoneNumber=this.phoneNumber;
		String major=this.major;
		String dormName=this.dorm.getDormName();
		int roomNumber=this.room.getRoomNumber();
		String mealPlanName=this.mealPlan.getMealPlanName();
		
		outputFile.println("Name:           "+firstName+" "+lastName);
		outputFile.println("Student ID #:   "+studentID);
		outputFile.println("NetID:          "+netID);
		outputFile.println("Year:           "+gradeYear);
		outputFile.println("Email Address:  "+emailAddress);
		outputFile.println("Phone #:        "+phoneNumber);
		outputFile.println("Declared Major: "+major);	
		outputFile.println("Dorm:           "+dormName);	
		outputFile.println("Room #:         "+roomNumber);
		outputFile.println("Meal Plan:      "+mealPlanName);
	}
	
}