import java.util.*;

/**
 * The LabClass class represents an enrolment list for one lab class. It stores
 * the time, room and participants of the lab, as well as the instructor's name.
 * 
 * 
 */
public class LabClass
{
    private String instructor;
    private String room;
    private String timeAndDay;
    private ArrayList<Student> students;
    private int capacity;
    
    /**
     * Create a LabClass with a maximum number of enrolments. All other details
     * are set to default values.
     */
    public LabClass(int maxNumberOfStudents)
    {
        instructor = "unknown";
        room = "unknown";
        timeAndDay = "unknown";
        capacity = maxNumberOfStudents;
        students = new ArrayList<Student>();        
    }

    /**
     * TODO: finish this method
     * Add a check to see that the limit is not exceeded.
     * if the number of students == capacity, then you say
     * "sorry, the class is full!"
     */
     public void enrollStudent(Student newStudent)
    {
    	 if (numberOfStudents() < capacity)
    	 {
    	 students.add(newStudent);
    	 }
    	 else
    	 {
         System.out.println("sorry, the class is full!");
    	 }
    }
    
    /**
     * TODO: complete this method
     * Return the number of students currently enrolled in this LabClass.
     */
    public int numberOfStudents()
    {
        return students.size();
    }
    
    /**
     * Set the room number for this LabClass.
     */
    public void setRoom(String roomNumber)
    {
        room = roomNumber;
    }
    
    /**
     * Set the time for this LabClass. The parameter should define the day
     * and the time of day, such as "Friday, 10am".
     */
    public void setTime(String timeAndDayString)
    {
        timeAndDay = timeAndDayString;
    }
    
    /**
     * Set the name of the instructor for this LabClass.
     */
    public void setInstructor(String instructorName)
    {
        instructor = instructorName;
    }
    
    /**
     * Print out a class list with other LabClass details to the standard
     * terminal.
     */
    public void printList()
    {
        System.out.println("Lab class " + timeAndDay);
        System.out.println("Instructor: " + instructor + "   Room: " + room);
        System.out.println("Class list:");
        
        for(int i =0; i < students.size(); i++)
        {
        System.out.println("First name = " + students.get(i).getPrename());
        System.out.println("Second name = " + students.get(i).getName());
        System.out.println("ID = " + students.get(i).getId());
        System.out.println("Credits = " + students.get(i).getCredits());
        System.out.println("*****************************************");
        }
        System.out.println("Number of students: " + numberOfStudents());
        
    }
}
