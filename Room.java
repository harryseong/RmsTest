// This file contains: Variables, User-defined methods (accessor,mutator,pass arguments,return a value), Constructor, 
//                     Aggregation

import java.util.ArrayList;

public class Room
{
	private int roomNumber;
	private Student occupant=null;
	private boolean occupied=false;
	
	// Contructor used when rooms are prepared.
	public Room(int roomNumber)
	{
		this.roomNumber=roomNumber;
	}
	
	// Constructor if a new room must be prepared, added, and assigned.
	public Room(int roomNumber,Student occupant,boolean occupied)
	{
		this.roomNumber=roomNumber;
		this.occupant=occupant;
		this.occupied=occupied;
	}
	
	// This mutator method records which student is assigned to the room and set the flag to occupied=true.
	public void assignToRoom(Student occupant)
	{
		this.occupant=occupant;
		this.occupied=true;
	}
	
	// This accessor method returns back the room number of the room object.
	public int getRoomNumber()
	{
		return roomNumber;
	}
	
	// This accessor method returns back the occupant.
	public Student getOccupant()
	{
		return occupant;
	}
	
	// This access method returns back the occupied status.
	public boolean checkOccupancy()
	{
		return occupied;
	}
}