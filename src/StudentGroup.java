import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * A fix-sized array of students
 * array length should always be equal to the number of stored elements
 * after the element was removed the size of the array should be equal to the number of stored elements
 * after the element was added the size of the array should be equal to the number of stored elements
 * null elements are not allowed to be stored in the array
 * 
 * You may add new methods, fields to this class, but DO NOT RENAME any given class, interface or method
 * DO NOT PUT any classes into packages
 *
 */
public class StudentGroup implements StudentArrayOperation {

	private Student[] students;
	
	/**
	 * DO NOT remove or change this constructor, it will be used during task check
	 * @param length
	 */
	public StudentGroup(int length) {
		this.students = new Student[length];
	}

	@Override
	public Student[] getStudents() {
		
		return students;
	}

	@Override
	public void setStudents(Student[] students) {
		if (students==null)
			throw new IllegalArgumentException();
		else
			this.students=students;
	}

	@Override
	public Student getStudent(int index) {
		if (index<0||index>=students.length)
			throw new IllegalArgumentException();
		return students[index];
	}

	@Override
	public void setStudent(Student student, int index) {
		if (student==null||index<0||index>=students.length)
			throw new IllegalArgumentException();
		else
			students[index]=student;
	}

	@Override
	public void addFirst(Student student) {
		if (student==null)
			throw new IllegalArgumentException();
		else {
		 Student[] std=new Student[students.length+1];
		 for(int i=1;i<=students.length;i++)
			 std[i]=students[i-1];
		 std[0]=student;
		 this.students = new Student[std.length];
		 students=std;
		}
	}
	@Override
	public void addLast(Student student) {
		if (student == null)
			throw new IllegalArgumentException();
		else {
		Student[] std=new Student[students.length+1];
		 for(int i=0;i<students.length;i++)
		 std[i]=students[i];
		 std[students.length]=student;
		 this.students = new Student[std.length];
		 students=std;
		}
	}

	@Override
	public void add(Student student, int index) {
		if (student==null||index<0||index>=students.length)
			throw new IllegalArgumentException();
		else {
		Student[] std=new Student[students.length+1];
		for(int i=0;i<students.length;i++)
			std[i]=students[i];
		students[index]=student;
		for(int i=index+1;i<std.length;i++)
			std[i]=students[i-1];
		this.students = new Student[std.length];
		students=std;
		}
	}

	@Override
	public void remove(int index) {
		if (index<0||index>=this.students.length)
			throw new IllegalArgumentException();
		else {
			Student[] std = new Student[this.students.length-1];
			for(int i=0;i<index;i++) 
				std[i]=students[i];

			for(int i=index;i<std.length;i++){
				std[i]=students[i+1];
			}
			this.students = new Student[std.length];
			students=std;
		}
	}

	@Override
	public void remove(Student student) {
		int flag=0;
		if(student == null)
			throw new IllegalArgumentException();
		else {
			for(int i=0;i<students.length;i++) {
				if(students[i]==student) {
					flag=1;	
					remove(i);
					break;
				}
			}
		}
		if(flag==0) {
			throw new IllegalArgumentException("Student not exist");
		}
	}

	@Override
	public void removeFromIndex(int index) {
		if (index<0||index>=students.length)
			throw new IllegalArgumentException();
		else {
			Student[] std = new Student[index + 1];
			for (int i=0;i<=index;i++) {
				std[i] = this.students[i];
			}
			this.students = new Student[index + 1];
			students = std;
		}
	}

	@Override
	public void removeFromElement(Student student) {
		if (student==null)
			throw new IllegalArgumentException();
		else {
			for(int i=0;i<students.length;i++) {
				if(students[i]==student)
					removeFromIndex(i);
					break;
			}
		}
	}

	@Override
	public void removeToIndex(int index) {
		if (index< 0 || index >= this.students.length)
			throw new IllegalArgumentException();
		else {
			Student std[]=new Student[students.length-index]; 
			for(int i=index;i<students.length;i++)
				std[i-index]=students[i];
			this.students = new Student[students.length-index];
			students=std;
		}
	}

	@Override
	public void removeToElement(Student student) {
		if (student==null)
			throw new IllegalArgumentException();
		else {
			for(int i=0;i<students.length;i++) {
				if(students[i]==student)
					removeToIndex(i);
					break;
			}
		}
	}

	@Override
	public void bubbleSort() {
		Student temp;
		for (int i = 0; i < students.length-1; i++)       
		       for (int j = 0; j < students.length-i-1; j++) 
		           if (students[j].getId() > students[j+1].getId()){
		        	   temp=students[j];
		        	   students[j]=students[j+1];
		        	   students[j+1]=temp;
		           }
		}

	@Override
	public Student[] getByBirthDate(Date date) {
		if(date==null)
			throw new IllegalArgumentException();
		else {
			int k=0;
			Student[] std=new Student[students.length];
			for(int i=0;i<students.length;i++) {
				boolean f=date.before(this.students[i].getBirthDate());
				boolean g=date.equals(this.students[i].getBirthDate());
				if (f||g) {
					std[k]=students[i];
					k++;
				}					
			}
			Student[] stds=new Student[k];
			for(int i=0;i<k;i++)
				stds[i]=std[i];
			return std;
		}
	}

	@Override
	public Student[] getBetweenBirthDates(Date firstDate, Date lastDate) {
		if(firstDate == null || lastDate == null)
			throw new IllegalArgumentException();
		return null;
	}

	@Override
	public Student[] getNearBirthDate(Date date, int days) {
		// Add your implementation here
		return null;
	}

	@Override
	public int getCurrentAgeByDate(int indexOfStudent) {
		if(indexOfStudent==0)
			throw new IllegalArgumentException();
		else {
			Date dt=students[indexOfStudent].getBirthDate();
					
			Calendar birth= Calendar.getInstance();
			birth.setTime(dt);
		    Calendar today = Calendar.getInstance();
		    int age = today.get(Calendar.YEAR) - birth.get(GregorianCalendar.YEAR);
		    if (
		            today.get(Calendar.MONTH) < birth.get(GregorianCalendar.MONTH) ||
		            (
		                    today.get(Calendar.MONTH) == birth.get(GregorianCalendar.MONTH) &&
		                    today.get(Calendar.DAY_OF_MONTH) < birth.get(GregorianCalendar.DAY_OF_MONTH)
		            )
		        ) {
		        age--;
		    }
		 
		    return age;
		}
	}

	@Override
	public Student[] getStudentsByAge(int age) {
		// Add your implementation here
            
		return null;
	}

	@Override
	public Student[] getStudentsWithMaxAvgMark() {
		// Add your implementation here
		double max=0;
		int count=0;
		Student std[]=new Student[students.length];
		for(int i=0;i<students.length;i++) {
			if(max<students[i].getAvgMark())
				max=students[i].getAvgMark();
		}
		for(int i=0;i<students.length;i++) {
			if(students[i].getAvgMark()==max) {
				std[count]=students[i];
				count++;
			}
		}
		Student stds[]=new Student[count];
		for(int i=0;i<count;i++)
			stds[i]=std[i];
		return std;
	}

	@Override
	public Student getNextStudent(Student student) {
		if (student == null)
			throw new IllegalArgumentException();
			else {
		for(int i=0;i<students.length;i++) {
			if(students[i]==student)
				return students[i+1];
		}
		return null;
			}
	}
}
