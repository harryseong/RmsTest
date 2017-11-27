// This file contains: Variables, Constants, Conditionals, Iterations,
//                     User-defined methods, Constructor, File I/O,ArrayList,
//					   this, String methods, Wrapper, Aggregation

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;


public class Dorm
{
	private String dormName;
	private String streetNumberName;
	private String city;
	private String state;
	private String zipCode;
	private int numberOfFloors;
	private int numberOfRooms;
	private int housingCostPerRoom;
	private ArrayList<Room> rooms=new ArrayList<>();
	private ArrayList<Room> occupiedRooms=new ArrayList<>();
	private boolean noVacancies=false;
	
	public Dorm(String dormName, String streetNumberName, String city, String state, String zipCode, 
			    int numberOfFloors, int numberOfRooms, int housingCostPerRoom)
	{
		this.dormName=dormName;
		this.streetNumberName=streetNumberName;
		this.city=city;
		this.state=state;
		this.zipCode=zipCode;
		this.numberOfFloors=numberOfFloors;
		this.numberOfRooms=numberOfRooms;
		this.housingCostPerRoom=housingCostPerRoom;
		
		prepareRooms();
	}
	
	// Constructor
	// This method creates room objects using the Room constructor and assigns them each a room number.
	public void prepareRooms()
	{
		int roomCount=0;
		Room room;
		
		for(int roomNumber=1;roomCount<numberOfRooms;++roomNumber)
		{
			for(int floor=1;floor<=numberOfFloors&&roomCount<numberOfRooms;++floor,++roomCount)
			{
				room=new Room(100*floor+roomNumber);
				this.rooms.add(room);
			}
		}
	}
	
	// Mutator
	public void updateOccupatedRoomList(Room room)
	{
		this.occupiedRooms.add(room);
	}
	
	// Mutator
	// Adds all occupied rooms to list of occupied rooms. Sets noVacancies flag to true if there are no more unoccupied rooms.
	// This will allow students to be assigned to dorms that have vacancies. Run this after assigning each student.
	public void updateOccupancy()
	{
		if((this.rooms.size())==(this.occupiedRooms.size()))
		{
			this.noVacancies=true;
		}
	}
	
	// Accessor
	public boolean getVacancyStatus()
	{
		return noVacancies;
	}	
	
	// Accessor
	public ArrayList<Room> getRooms()
	{
		return rooms;
	}
	
	// Accessor
	public String getDormName()
	{
		return dormName;
	}
	
	// Accessor
	public int getNumberOfOccupiedRooms()
	{
		return occupiedRooms.size();
	}
	
	// Accessor
	public int getNumberOfRooms()
	{
		return rooms.size();
	}
	
	// Print to Screen
	public void getDormOccupants()
	{
		String[][] studentNameList=new String[this.occupiedRooms.size()][3];
		
		for(int y=0;y<this.occupiedRooms.size();++y)
		{
			studentNameList[y][0]=this.occupiedRooms.get(y).getOccupant().getStudentName();
			studentNameList[y][1]=this.occupiedRooms.get(y).getOccupant().getStudentRoomNumber();
			studentNameList[y][2]=this.occupiedRooms.get(y).getOccupant().getStudentID();
		}
		
		// Arrays.sort(studentNameList);
		System.out.println("#     Room #   StudentID #   Student Name");
		System.out.println("---   ------   -----------   ---------------------");
		for(int z=0;z<this.occupiedRooms.size();++z)
		{
			if(z+1>99)
			{
				System.out.println((z+1)+".  "+studentNameList[z][1]+"      "+studentNameList[z][2]+"       "+studentNameList[z][0]);
			}
			else if(z+1>9)
			{
				System.out.println((z+1)+".   "+studentNameList[z][1]+"      "+studentNameList[z][2]+"       "+studentNameList[z][0]);
			}
			else
			{
				System.out.println((z+1)+".    "+studentNameList[z][1]+"      "+studentNameList[z][2]+"       "+studentNameList[z][0]);
			}	
		}
		System.out.println();
	}
	
	// Print to File
	public void getDormOccupants(PrintWriter outputFile)
	{
		String[][] studentNameList=new String[this.occupiedRooms.size()][3];
		
		for(int y=0;y<this.occupiedRooms.size();++y)
		{
			studentNameList[y][0]=this.occupiedRooms.get(y).getOccupant().getStudentName();
			studentNameList[y][1]=this.occupiedRooms.get(y).getOccupant().getStudentRoomNumber();
			studentNameList[y][2]=this.occupiedRooms.get(y).getOccupant().getStudentID();
		}
		
		outputFile.println("#     Room #   StudentID #   Student Name");
		outputFile.println("---   ------   -----------   ---------------------");
		for(int z=0;z<this.occupiedRooms.size();++z)
		{
			if(z+1>99)
			{
				outputFile.println((z+1)+".  "+studentNameList[z][1]+"      "+studentNameList[z][2]+"       "+studentNameList[z][0]);
			}
			else if(z+1>9)
			{
				outputFile.println((z+1)+".   "+studentNameList[z][1]+"      "+studentNameList[z][2]+"       "+studentNameList[z][0]);
			}
			else
			{
				outputFile.println((z+1)+".    "+studentNameList[z][1]+"      "+studentNameList[z][2]+"       "+studentNameList[z][0]);
			}	
		}
		outputFile.println();
	}
	
	// Print to Screen
	public void getDormInfo()
	{
		String dormName=this.dormName;
		String streetNumberName=this.streetNumberName;
		String city=this.city;
		String state=this.state;
		String zipCode=this.zipCode;
		int numberOfFloors=this.numberOfFloors;
		int numberOfRooms=this.numberOfRooms;
		int numberOfRoomsOccupied=this.occupiedRooms.size();
		int housingCostPerRoom=this.housingCostPerRoom;
		
		System.out.printf("Dorm Name:           %s\n",dormName);
		System.out.printf("Number of Floors:    %d\n",numberOfFloors);
		System.out.printf("Number of Rooms:     %d\n",numberOfRooms);
		System.out.printf("Occupied Rooms:      %d\n",numberOfRoomsOccupied);
		System.out.printf("Annual Cost of Stay: $%,d\n",housingCostPerRoom);
		System.out.printf("Dorm Address:        %s\n",streetNumberName);
		System.out.printf("                     %s, %s\n",city,state);
		System.out.printf("                     %s\n",zipCode);
	}
	
	// Print to File
	public void getDormInfo(PrintWriter outputFile)
	{
		String dormName=this.dormName;
		String streetNumberName=this.streetNumberName;
		String city=this.city;
		String state=this.state;
		String zipCode=this.zipCode;
		int numberOfFloors=this.numberOfFloors;
		int numberOfRooms=this.numberOfRooms;
		int numberOfRoomsOccupied=this.occupiedRooms.size();
		int housingCostPerRoom=this.housingCostPerRoom;
		
		outputFile.println("Dorm Name:           "+dormName);
		outputFile.println("Number of Floors:    "+numberOfFloors);
		outputFile.println("Number of Rooms:     "+numberOfRooms);
		outputFile.println("Occupied Rooms:      "+numberOfRoomsOccupied);
		outputFile.println("Annual Cost of Stay: "+housingCostPerRoom);
		outputFile.println("Dorm Address:        "+streetNumberName);
		outputFile.println("                     "+city+", "+state);
		outputFile.println("                     "+zipCode);
	}
}