// This file contains: Constants, Conditions, Iterations, User-defined methods(pass arguments,return a value),
//                     File I/O, Array, ArrayList, String methods, Wrapper

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;
import java.util.Scanner;

public class RmsTest
{
	public static void main(String[] arg) throws IOException
	{
		ArrayList<Dorm> constructedDorms=constructDorms();                      		  // 1. Construct dorms, prepare rooms.
		ArrayList<MealPlan> createdMealPlans=createMealPlans();                		      // 2. Create meal plans.
		int enrollmentNumber=introduction(constructedDorms);                      		  // 3. Introduction.
		ArrayList<Student> enrolledStudents=enrollStudents(enrollmentNumber);    		  // 4. Enroll students.
		assignDormRoom(enrolledStudents, constructedDorms);                       		  // 5. Assign dorms/rooms to students.
		assignMealPlan(enrolledStudents, createdMealPlans);					      		  // 6. Assign meal plan to students.
		
		for(int x=0;x==0;)
		{
			options(enrollmentNumber,enrolledStudents,constructedDorms,createdMealPlans); // 7. Provide options for seeing & creating reports.	
		}
								  
	}
	
	// 1. This method takes a .csv file of 14 dorms and their unique attributes and creates 14 Dorm objects into a ArrayList of Dorm objects.
	public static ArrayList<Dorm> constructDorms() throws IOException
	{
		final String DORMFILENAME="dorms.csv";
		final int NUMBEROFDORMS=14;
		File dormFile=new File(DORMFILENAME);
		Scanner inputDorms=new Scanner(dormFile);
		ArrayList<String> dormsInfoArray=new ArrayList<>();
		ArrayList<Dorm> dormsArray=new ArrayList<>();
		
		String dormName="";
		String streetNumberName="";
		String city="";
		String state="";
		String zipCode="";
		int numberOfFloors=0;
		int numberOfRooms=0;
		int housingCostPerRoom=0;
		
		while(inputDorms.hasNext())
		{
			dormsInfoArray.add(inputDorms.nextLine());
		}
		
		for(int x=0;x<NUMBEROFDORMS;++x)
		{
			String dormInfo=dormsInfoArray.get(x);
			String[] dormInfoArray=dormInfo.split(",");
			
			for(int y=0;y<dormInfoArray.length;++y)
			{
				switch(y)
				{
					case 0:
						dormName=dormInfoArray[y];
						break;
					case 1:
						streetNumberName=dormInfoArray[y];
						break;
					case 2:
						city=dormInfoArray[y];
						break;
					case 3:
						state=dormInfoArray[y];
						break;
					case 4:
						zipCode=dormInfoArray[y];
						break;
					case 5:
						numberOfFloors=Integer.parseInt(dormInfoArray[y]);
						break;
					case 6:
						numberOfRooms=Integer.parseInt(dormInfoArray[y]);
						break;
					case 7:
						housingCostPerRoom=Integer.parseInt(dormInfoArray[y]);
						break;
				}
			}
			Dorm dorm=new Dorm(dormName,streetNumberName,city,state,zipCode,numberOfFloors,numberOfRooms,housingCostPerRoom);
			dormsArray.add(dorm);
		}
		return dormsArray;
	}
	
	// 2. This method creates 3 types of meal plans.
	public static ArrayList<MealPlan> createMealPlans()
	{
		ArrayList<MealPlan> mealPlansArray=new ArrayList<>();
		String[] mealPlanNames={"A","B","C"};
		
		for(int x=0;x<mealPlanNames.length;++x)
		{
			MealPlan mealPlan=new MealPlan(mealPlanNames[x]);
			mealPlansArray.add(mealPlan);
		}
		
		return mealPlansArray;
	}	
	
	// 3. Intro. Input number of students to enroll.
	public static int introduction(ArrayList<Dorm> dormList)
	{
		int enrollmentNumber=0;
		int maxEnroll=0;
		Scanner keyboard=new Scanner(System.in);
		
		System.out.println();
		System.out.println("    ========================================");
		System.out.println("    | RRRRRRRR     MM      MM   SSSSSSSS   |");
		System.out.println("    | RR     RR    MMM    MMM  SSS    SSS  |");
		System.out.println("    | RR      RR   MMMM  MMMM  SS          |");
		System.out.println("    | RR     RR    MM MMMM MM  SSSSSSS     |");
		System.out.println("    | RRRRRRRR     MM  MM  MM    SSSSSSS   |");
		System.out.println("    | RR     RR    MM      MM          SS  |");
		System.out.println("    | RR      RR   MM      MM  SSS    SSS  |");
		System.out.println("    | RR      RR   MM      MM   SSSSSSSS   |");
		System.out.println("    ========================================");
		System.out.println();
		System.out.println("[i] Welcome to RMS, the Residential Management System for Northwestern University.");
		System.out.println("    This program will allow you to enroll students to the university, assign them");
		System.out.println("    to dormitory rooms on campus, and create reports on the occupancy of dorms.");
		System.out.println();
		
		for(int x=0;x<dormList.size();++x)
		{
			maxEnroll+=dormList.get(x).getNumberOfRooms();
		}
		
		System.out.print("[i] We will begin by enrolling students to the university. Currently, the total number\n");
		System.out.printf("    of rooms on campus is %,d, so a maximum of %,d students may be enrolled.\n",maxEnroll,maxEnroll);
		System.out.print("\n[!] Please enter the number of students to enroll: ");
		enrollmentNumber=keyboard.nextInt();
		
		while(enrollmentNumber<1 || enrollmentNumber>maxEnroll)
		{
			if(enrollmentNumber<1)
			{
				System.out.printf("\n[ERROR] You entered \"%,d\". At least 1 student must be enrolled.\n",enrollmentNumber);
			}
			if(enrollmentNumber>maxEnroll)
			{
				System.out.printf("\n[ERROR] You entered \"%,d\". Cannot enroll more than %,d students, as there are only %,d rooms.\n",enrollmentNumber,maxEnroll,maxEnroll);
			}
			System.out.print("\n[!] Please enter the number of students to enroll: ");
			enrollmentNumber=keyboard.nextInt();
		}
		
		return enrollmentNumber;
	}
	
	
	// 4. This method enrolls an inputted number of students.
	public static ArrayList<Student> enrollStudents(int n) throws IOException
	{
		Random random=new Random();
		final int NUMBERENROLLED=n;
		ArrayList<Student> enrolledStudentsArray=new ArrayList<>();
		
		for(int x=0;x<NUMBERENROLLED;++x)
		{
			String firstName=randomFirstName();
			String lastName=randomLastName();
			int studentID=2000000+x;
			String netID=(firstName.substring(0,2)).toLowerCase()+(lastName.substring(0,1)).toLowerCase()+Integer.toString(random.nextInt(900)+100);
			String gradeYear=randomGradeYear();
			String emailAddress=createEmailAddress(firstName,lastName,gradeYear);
			String phoneNumber="("+randomAreaCode()+") "+(random.nextInt(900)+100)+"-"+(random.nextInt(9000)+1000);
			String major=randomMajor();
			
			Student student=new Student(firstName,lastName,studentID,netID,gradeYear,emailAddress,phoneNumber,major);
			enrolledStudentsArray.add(student);
		}
		
		System.out.printf("\n[i] %,d students have been successfully enrolled and assigned to dorm rooms.\n",NUMBERENROLLED);
		
		return enrolledStudentsArray;
	}	
		
	// 4A. This method returns back a random first name from a .csv file list of first names.
	public static String randomFirstName() throws IOException
	{
		final String FIRSTNAMEFILENAME="firstNames.csv";
		File firstNameFile=new File(FIRSTNAMEFILENAME);
		Scanner inputFirstNames=new Scanner(firstNameFile);
		ArrayList<String> firstNamesArray=new ArrayList<>();
		Random random=new Random();
		
		while(inputFirstNames.hasNext())
		{
			firstNamesArray.add(inputFirstNames.next());
		}
		
		String firstName=firstNamesArray.get(random.nextInt(firstNamesArray.size()));
		
		return firstName;
	}
	
	// 4B. This method returns back a random last name from a .csv file list of last names.
	public static String randomLastName() throws IOException
	{
		final String LASTNAMEFILENAME="lastNames.csv";
		File lastNameFile=new File(LASTNAMEFILENAME);
		Scanner inputLastNames=new Scanner(lastNameFile);
		ArrayList<String> lastNamesArray=new ArrayList<>();
		Random random=new Random();
		
		while(inputLastNames.hasNext())
		{
			lastNamesArray.add(inputLastNames.next());
		}
		
		String lastName=lastNamesArray.get(random.nextInt(lastNamesArray.size()));
		
		return lastName;
	}
	
	// 4C. This method returns back a random grade year.
	public static String randomGradeYear()
	{
		Random random=new Random();
		ArrayList<String> gradeYearsArray=new ArrayList<>();
		gradeYearsArray.add("Freshman");
		gradeYearsArray.add("Sophomore");
		gradeYearsArray.add("Junior");
		gradeYearsArray.add("Senior");
		
		String gradeYear=gradeYearsArray.get(random.nextInt(gradeYearsArray.size()));
		
		return gradeYear;
	}
	
	// 4D. This method creates and returns a student email address.
	public static String createEmailAddress(String firstName,String lastName,String gradeYear)
	{
		int graduationYear=Calendar.getInstance().get(Calendar.YEAR);
		switch(gradeYear)
		{
			case "Freshman":
				graduationYear=graduationYear+4;
				break;
			case "Sophomore":
				graduationYear=graduationYear+3;
				break;
			case "Junior":
				graduationYear=graduationYear+2;
				break;
			case "Senior":
				graduationYear=graduationYear+1;
				break;
			default:
				graduationYear=graduationYear;
		}
		
		String emailAddress=(firstName+lastName+Integer.toString(graduationYear)+"@u.northwestern.edu").toLowerCase();
		
		return emailAddress;
	}
	
	// 4E. This method returns back a random area code from a .csv file list of real area codes in the U.S.
	public static String randomAreaCode() throws IOException
	{
		final String AREACODEFILENAME="areaCodes.csv";
		File areaCodeFile=new File(AREACODEFILENAME);
		Scanner inputAreaCodes=new Scanner(areaCodeFile);
		ArrayList<String> areaCodesArray=new ArrayList<>();
		Random random=new Random();
		
		while(inputAreaCodes.hasNext())
		{
			areaCodesArray.add(inputAreaCodes.nextLine());
		}
		
		String areaCode=areaCodesArray.get(random.nextInt(areaCodesArray.size()));
		
		return areaCode;
	}
	
	// 4F. This method returns back a random major from a .csv file list of majors at Northwestern University.
	public static String randomMajor() throws IOException
	{
		final String MAJORFILENAME="majors.csv";
		File majorFile=new File(MAJORFILENAME);
		Scanner inputMajors=new Scanner(majorFile);
		ArrayList<String> majorsArray=new ArrayList<>();
		Random random=new Random();
		
		while(inputMajors.hasNext())
		{
			majorsArray.add(inputMajors.nextLine());
		}
		
		String major=majorsArray.get(random.nextInt(majorsArray.size()));
		
		return major;
	}
	
	// 5. Assign each student to a dorm and room.
	public static void assignDormRoom(ArrayList<Student> studentList,ArrayList<Dorm> dormList)
	{
		for(int x=0;x<studentList.size();++x) // For each student...
		{
			Dorm assignedDorm=dormList.get(0);
			for(int y=0;y<dormList.size()&&assignedDorm.getVacancyStatus()==true;++y) // Until first Dorm object that has vacancy...
			{
				assignedDorm=dormList.get(y);                    // Assign Dorm.
			}
			studentList.get(x).assignDorm(assignedDorm);         // Student object field updated with assigned Dorm object.
			
			ArrayList<Room> roomList=assignedDorm.getRooms();
			Room assignedRoom=roomList.get(0);
			for(int z=0;z<roomList.size()&&assignedRoom.checkOccupancy()==true;++z)   // Until first Room object that is unoccupied...
			{
				assignedRoom=roomList.get(z);                    // Assign Room.
			}
			studentList.get(x).assignRoom(assignedRoom);         // Student object field updated with assigned Room object.
			assignedRoom.assignToRoom(studentList.get(x));       // Room object field updated with occupant Student object.
			assignedDorm.updateOccupatedRoomList(assignedRoom);  // Add the newly assigned Room object to the Dorm object's array of occupied Room objects.
			assignedDorm.updateOccupancy();                      // If all Rooms in the Dorm are now occupied, update Dorm object field to signify no vacancy.
		}
	}
	
	// 6. This method assigns a random meal plan to each student.
	public static void assignMealPlan(ArrayList<Student> studentList, ArrayList<MealPlan> mealPlanList) throws IOException
	{
		Random random=new Random();
		
		for(int x=0;x<studentList.size();++x)
		{
			studentList.get(x).assignMealPlan(mealPlanList.get(random.nextInt(3)));
		}
	}
	
	// 7. This method will provide navigation through the different menus.
	public static void options(int numberEnrolled,ArrayList<Student> studentList, ArrayList<Dorm> dormList, ArrayList<MealPlan> mealPlanList) throws IOException
	{
		String option="";
		Scanner keyboard=new Scanner(System.in);
		
		System.out.println();
		System.out.println();
		System.out.println("======[MAIN MENU]============================================");
		System.out.println();
		System.out.println("[?] What would you like to do?");
		System.out.println("         [1] View complete list of all enrolled students.");
		System.out.println("         [2] View complete list of all dorm buildings.");
		System.out.println("         [3] View list of meal plans.");
		System.out.println("         [4] View list of occupants in a specific dorm.");
		System.out.println("         [5] Lookup student information.");
		System.out.println("         [6] View existing reports.");
		System.out.println("         [x] Exit program.\n");
		System.out.print("    Enter Option: ");
		option=keyboard.nextLine().toLowerCase().trim();
		
		while(!option.equals("1")&&!option.equals("2")&&!option.equals("3")&&!option.equals("4")&&!option.equals("5")&&!option.equals("6")&&!option.equals("x"))
		{
			System.out.println();
			System.out.printf("[ERROR] Invalid input. You entered \"%s\". Please input one of the options provided.\n",option);
			System.out.println();
			System.out.println("[?] What would you like to do?");
			System.out.println("         [1] View complete list of all enrolled students.");
			System.out.println("         [2] View complete list of all dorm buildings.");
			System.out.println("         [3] View list of meal plans.");
			System.out.println("         [4] View list of occupants in a specific dorm.");
			System.out.println("         [5] Lookup student information.");
			System.out.println("         [6] View existing reports.");
			System.out.println("         [x] Exit program.\n");
			System.out.print("    Enter Option: ");
			option=keyboard.nextLine().toLowerCase().trim();
		}
		
		if(option.equals("1"))
		{
			printEnrolledStudents(studentList);
		}
		
		else if(option.equals("2"))
		{
			printConstructedDorms(dormList);
		}
		
		else if(option.equals("3"))
		{
			printMealPlans(mealPlanList);
		}
		
		else if(option.equals("4"))
		{
			String option4="";
			int option4Int=0;
			
			System.out.println();
			System.out.println("[?] For which dorm would you like to get a list of occupants?");
			
			for(int z=0;z<dormList.size();++z)
			{
				if((z+1)<10)
				{
					System.out.printf("         [%d]  %s\n",(z+1),dormList.get(z).getDormName());
				}
				else
				{
					System.out.printf("         [%d] %s\n",(z+1),dormList.get(z).getDormName());
				}
			}
			System.out.println("         [x]  Exit program.");
			System.out.print("\n    Enter Option: ");
			option4=keyboard.nextLine().toLowerCase().trim();
			
			while(!option4.equals("1")&&
				  !option4.equals("2")&&
				  !option4.equals("3")&&
				  !option4.equals("4")&&
				  !option4.equals("5")&&
				  !option4.equals("6")&&
				  !option4.equals("7")&&
				  !option4.equals("8")&&
				  !option4.equals("9")&&
				  !option4.equals("10")&&
				  !option4.equals("11")&&
				  !option4.equals("12")&&
				  !option4.equals("13")&&
				  !option4.equals("14")&&
				  !option4.equals("x"))
			{
				System.out.println();
				System.out.printf("[ERROR] Invalid input. You entered \"%s\". Please input one of the options provided.\n",option4);
				System.out.println();
				System.out.println("[?] For which dorm would you like to get a list of occupants?");
			
				for(int z=0;z<dormList.size();++z)
				{
					if((z+1)<10)
					{
						System.out.printf("         [%d]  %s\n",(z+1),dormList.get(z).getDormName());
					}
					else
					{
						System.out.printf("         [%d] %s\n",(z+1),dormList.get(z).getDormName());
					}
				}
				System.out.println("         [x]  Exit program.");
				System.out.print("\n    Enter Option: ");
				option4=keyboard.nextLine().toLowerCase().trim();
			}
			if(option4.equals("x"))
			{
				System.out.println("\n[i] Exiting program...");
				System.exit(0);
			}
			option4Int=Integer.parseInt(option4)-1;
			
			printDormOccupants(dormList, option4Int);
		}
		
		else if(option.equals("5"))
		{
			int maxStudentID=2000000+studentList.size()-1;
			
			System.out.println();
			System.out.print("[i] Please enter the Student ID # to see student details: ");
			int inputStudentID=keyboard.nextInt();
			int selectedStudentKey=inputStudentID-2000000;
			
			if(inputStudentID<2000000||inputStudentID>maxStudentID)
			{
				System.out.println();
				System.out.printf("[ERROR] You entered \"%d\". The entered Student ID number does not exist.\n",inputStudentID);
				System.out.println();
				System.out.print("[!] Please enter the Student ID # to see student details: ");
				inputStudentID=keyboard.nextInt();
				selectedStudentKey=inputStudentID-2000000;;
			}
			
			printStudentInfo(studentList,selectedStudentKey);
		}
		
		else if(option.equals("6"))
		{
			String existingFileName="";
			String tryAgain="y";
			
			System.out.print("\n[?] Enter name of an existing report to view. Exclude file extensions: ");
			existingFileName=keyboard.nextLine()+".txt";
			File file=new File(existingFileName);
			
			while(file.exists()==false&&(tryAgain.equals("y")||tryAgain.equals("yes")))
			{
				System.out.printf("\n[!] \"%s\" does not exist.\n",existingFileName);
				System.out.print("\n[?] Would you like to try a different file name? (Y/N): ");
				tryAgain=keyboard.nextLine().toLowerCase().trim();
				
				while(!tryAgain.equals("yes")&&!tryAgain.equals("y")&&!tryAgain.equals("no")&&!tryAgain.equals("n"))
				{
					System.out.printf("\n[ERROR] You entered \"%s\". Invalid input.\n",tryAgain);
					System.out.print("\nWould you like to enter a different file name? (Y/N): ");
					tryAgain=keyboard.nextLine().toLowerCase().trim();
				}
				
				if(tryAgain.equals("y")||tryAgain.equals("yes"))
				{
					System.out.print("\n[?] Enter name of an existing report to view. Exclude file extensions: ");
					existingFileName=keyboard.nextLine()+".txt";
					file=new File(existingFileName);
				}
			}
			
			if(file.exists()==true)
			{
				Scanner existingInputFile=new Scanner(file);
			
				while(existingInputFile.hasNext())
				{
					System.out.println(existingInputFile.nextLine());
				}
				
				existingInputFile.close();
			}
		}
		
		else if(option.equals("x"))
		{
			System.out.println("\n[i] Exiting program...");
			System.exit(0);
		}
	}
	
	
	// ============REPORT WRITING======REPORT WRITING======REPORT WRITING======REPORT WRITING================
	public static void printEnrolledStudents(ArrayList<Student> studentList) throws IOException
	{
		Date now=new Date();
		SimpleDateFormat dateFormatter=new SimpleDateFormat("MM.dd.yyyy hh:mm a");
		String timeStamp=dateFormatter.format(now);
		
		String[][] studentNameList=new String[studentList.size()][2];
		
		for(int x=0;x<studentList.size();++x)
		{
			studentNameList[x][0]=studentList.get(x).getStudentName();
			studentNameList[x][1]=studentList.get(x).getStudentID();
		}
		
		System.out.println();
		System.out.println("=====================[ENROLLED STUDENTS]=====================");
		System.out.println();
		System.out.println("[REPORT GENERATED] "+timeStamp);
		System.out.println();
		System.out.println("#      StudentID #   Student Name");
		System.out.println("----   -----------   ------------------");
		
		for(int y=0;y<studentList.size();++y)
		{
			if((y+1)>999)
			{
				System.out.println((y+1)+".  "+studentNameList[y][1]+"       "+studentNameList[y][0]);
			}
			else if((y+1)>99)
			{
				System.out.println((y+1)+".   "+studentNameList[y][1]+"       "+studentNameList[y][0]);
			}
			else if((y+1)>9)
			{
				System.out.println((y+1)+".    "+studentNameList[y][1]+"       "+studentNameList[y][0]);
			}
			else
			{
				System.out.println((y+1)+".     "+studentNameList[y][1]+"       "+studentNameList[y][0]);
			}
		}
		System.out.println();
		System.out.println("=======================[END OF REPORT]=======================");
		System.out.println();
		
		String saveAsFile=saveAsFileOption();
		if(saveAsFile.equals("y")||saveAsFile.equals("yes"))
		{
			PrintWriter outputFile=saveAsFile();
			
			outputFile.println();
			outputFile.println("=====================[ENROLLED STUDENTS]=====================");
			outputFile.println();
			outputFile.println("[REPORT GENERATED] "+timeStamp);
			outputFile.println();
			outputFile.println("#      StudentID #   Student Name");
			outputFile.println("----   -----------   ------------------");
			
			for(int y=0;y<studentList.size();++y)
			{
				if((y+1)>999)
				{
					outputFile.println((y+1)+".  "+studentNameList[y][1]+"       "+studentNameList[y][0]);
				}
				else if((y+1)>99)
				{
					outputFile.println((y+1)+".   "+studentNameList[y][1]+"       "+studentNameList[y][0]);
				}
				else if((y+1)>9)
				{
					outputFile.println((y+1)+".    "+studentNameList[y][1]+"       "+studentNameList[y][0]);
				}
				else
				{
					outputFile.println((y+1)+".     "+studentNameList[y][1]+"       "+studentNameList[y][0]);
				}
			}
			outputFile.println();
			outputFile.println("=======================[END OF REPORT]=======================");
			outputFile.println();
			
			outputFile.close();
			
			System.out.println("\n[i] File successfully saved.");
		}
	}
	
	public static void printConstructedDorms(ArrayList<Dorm> dormList) throws IOException
	{
		Date now=new Date();
		SimpleDateFormat dateFormatter=new SimpleDateFormat("MM.dd.yyyy hh:mm a");
		String timeStamp=dateFormatter.format(now);
		
		System.out.println();
		System.out.println("====================[DORMITORY BUILDINGS]====================");
		System.out.println();
		System.out.println("[REPORT GENERATED] "+timeStamp);
		System.out.println();
		
		for(int y=0;y<dormList.size();++y)
		{
			dormList.get(y).getDormInfo();
			System.out.println("------------------------------------------");
		}
		System.out.println();
		System.out.println("=======================[END OF REPORT]=======================");
		System.out.println();
		
		String saveAsFile=saveAsFileOption();
		if(saveAsFile.equals("y")||saveAsFile.equals("yes"))
		{
			PrintWriter outputFile=saveAsFile();
			
			outputFile.println();
			outputFile.println("====================[DORMITORY BUILDINGS]====================");
			outputFile.println();
			outputFile.println("[REPORT GENERATED] "+timeStamp);
			outputFile.println();
			
			for(int y=0;y<dormList.size();++y)
			{
				dormList.get(y).getDormInfo(outputFile);
				outputFile.println("------------------------------------------");
			}
			outputFile.println();
			outputFile.println("=======================[END OF REPORT]=======================");
			outputFile.println();
			
			outputFile.close();
			
			System.out.println("\n[i] File successfully saved.");
		}
	}
	
	public static void printMealPlans(ArrayList<MealPlan> mealPlanList) throws IOException
	{
		Date now=new Date();
		SimpleDateFormat dateFormatter=new SimpleDateFormat("MM.dd.yyyy hh:mm a");
		String timeStamp=dateFormatter.format(now);
	
		System.out.println();
		System.out.println("=========================[MEAL PLANS]========================");
		System.out.println();
		System.out.println("[REPORT GENERATED] "+timeStamp);
		System.out.println();
		
		for(int y=0;y<mealPlanList.size();++y)
		{
			mealPlanList.get(y).getMealPlanInfo();
			System.out.println("------------------------------------------");
		}
		System.out.println();
		System.out.println("=======================[END OF REPORT]=======================");
		System.out.println();
		
		String saveAsFile=saveAsFileOption();
		if(saveAsFile.equals("y")||saveAsFile.equals("yes"))
		{
			PrintWriter outputFile=saveAsFile();
			
			outputFile.println();
			outputFile.println("=========================[MEAL PLANS]========================");
			outputFile.println();
			outputFile.println("[REPORT GENERATED] "+timeStamp);
			outputFile.println();
			
			for(int y=0;y<mealPlanList.size();++y)
			{
				mealPlanList.get(y).getMealPlanInfo(outputFile);
				outputFile.println("------------------------------------------");
			}
			outputFile.println();
			outputFile.println("=======================[END OF REPORT]=======================");
			outputFile.println();
			
			outputFile.close();
			
			System.out.println("\n[i] File successfully saved.");
		}
	}
	
	public static void printDormOccupants(ArrayList<Dorm> dormList,int option4Int) throws IOException
	{
		Date now=new Date();
		SimpleDateFormat dateFormatter=new SimpleDateFormat("MM.dd.yyyy hh:mm a");
		String timeStamp=dateFormatter.format(now);		
	
		System.out.println();
		System.out.println("====================[DORMITORY OCCUPANTS]====================");
		System.out.println();
		System.out.println("[REPORT GENERATED] "+timeStamp);
		System.out.println();
		System.out.printf("Dormitory Name:  %s\n",dormList.get(option4Int).getDormName());
		System.out.printf("Number of Rooms: %d\n",dormList.get(option4Int).getNumberOfRooms());
		System.out.printf("Occupied Rooms:  %d\n",dormList.get(option4Int).getNumberOfOccupiedRooms());
		System.out.println();
		dormList.get(option4Int).getDormOccupants();
		System.out.println("=======================[END OF REPORT]=======================");
		System.out.println();
		
		String saveAsFile=saveAsFileOption();
		if(saveAsFile.equals("y")||saveAsFile.equals("yes"))
		{
			PrintWriter outputFile=saveAsFile();
			
			outputFile.println();
			outputFile.println("====================[DORMITORY OCCUPANTS]====================");
			outputFile.println();
			outputFile.println("[REPORT GENERATED] "+timeStamp);
			outputFile.println();
			outputFile.println("Dormitory Name:  "+dormList.get(option4Int).getDormName());
			outputFile.println("Number of Rooms: "+dormList.get(option4Int).getNumberOfRooms());
			outputFile.println("Occupied Rooms:  "+dormList.get(option4Int).getNumberOfOccupiedRooms());
			outputFile.println();
			dormList.get(option4Int).getDormOccupants(outputFile);
			outputFile.println("=======================[END OF REPORT]=======================");
			outputFile.println();
			
			outputFile.close();
			
			System.out.println("\n[i] File successfully saved.");
		}
	}
	
	public static void printStudentInfo(ArrayList<Student> studentList,int selectedStudentKey) throws IOException
	{
		Date now=new Date();
		SimpleDateFormat dateFormatter=new SimpleDateFormat("MM.dd.yyyy hh:mm a");
		String timeStamp=dateFormatter.format(now);		
		
		System.out.println();
		System.out.println("================[STUDENT INFORMATION REPORT]=================");
		System.out.println();
		System.out.println("[REPORT GENERATED] "+timeStamp);
		System.out.println();
		
		studentList.get(selectedStudentKey).getStudentInfo();
		System.out.println();

		System.out.println("=======================[END OF REPORT]=======================");
		System.out.println();
		
		String saveAsFile=saveAsFileOption();
		if(saveAsFile.equals("y")||saveAsFile.equals("yes"))
		{
			PrintWriter outputFile=saveAsFile();
			
			outputFile.println();
			outputFile.println("================[STUDENT INFORMATION REPORT]=================");
			outputFile.println();
			outputFile.println("[REPORT GENERATED] "+timeStamp);
			outputFile.println();
			
			studentList.get(selectedStudentKey).getStudentInfo(outputFile);
			outputFile.println();

			outputFile.println("=======================[END OF REPORT]=======================");
			outputFile.println();
			
			outputFile.close();
			
			System.out.println("\n[i] File successfully saved.");
		}
	}
	
	// This method asks user if they would like to save a report as a file.
	public static String saveAsFileOption() throws IOException
	{			
		String option;
		Scanner keyboard=new Scanner(System.in);
		
		System.out.print("[?] Would you like to save this report as a file? (Y/N): ");
		option=keyboard.nextLine().toLowerCase().trim();
		
		while(!option.equals("y")&&!option.equals("n")&&!option.equals("yes")&&!option.equals("no"))
		{
			System.out.printf("\n[ERROR] You entered \"%s\". Invalid input.\n",option);
			System.out.print("\n[?] Would you like to save this report as a file? (Y/N): ");
			option=keyboard.nextLine().toLowerCase().trim();
		}
		
		return option;
	}
	
	// This method saves a report as a file.
	public static PrintWriter saveAsFile() throws IOException
	{
		String fileName;
		String option="n";
		Scanner keyboard=new Scanner(System.in);
		
		System.out.print("\n[?] What would you like to name the report file? Exclude file extensions: ");
		fileName=keyboard.nextLine()+".txt";
		File file=new File(fileName);
		
		while(file.exists()==true&&(option.equals("n")||option.equals("no")))
		{
			System.out.printf("\n[!] \"%s\" already exists. Would you like to overwrite \"%s\"? (Y/N): ",fileName,fileName);
			option=keyboard.nextLine().toLowerCase().trim();
		
			while(!option.equals("y")&&!option.equals("n")&&!option.equals("yes")&&!option.equals("no"))
			{
				System.out.printf("\n[ERROR] You entered \"%s\". Invalid input.\n",option);
				System.out.printf("\n[?] Would you like to overwrite \"%s\"? (Y/N): ",fileName);
				option=keyboard.nextLine().toLowerCase().trim();
			}
			
			if(option.equals("n")||option.equals("no"))
			{
				System.out.print("\n[?] What would you like to name the report file? Exclude file extensions: ");
				fileName=keyboard.nextLine()+".txt";
				file=new File(fileName);
			}
		}
		PrintWriter outputFile=new PrintWriter(file);
		return outputFile;
	}	
}