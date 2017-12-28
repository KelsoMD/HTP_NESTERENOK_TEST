import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class StudentGroup implements GroupOperationService {

	private Student[] students;

	public StudentGroup(int length) {
		this.students = new Student[length];
	}

	@Override
	public Student[] getStudents() {
		return this.students;
	}

	@Override
	public void setStudents(Student[] students) {
		if (students == null) {
			throw new IllegalArgumentException();
		}
		this.students = students;
	}

	@Override
	public Student getStudent(int index) {
		if (index < 0 && index >= this.students.length) {
			throw new IllegalArgumentException();
		}
		return this.students[index];
	}

	@Override
	public void setStudent(Student student, int index) {
		if (student == null || (index < 0 && index >= this.students.length)) {
			throw new IllegalArgumentException();
		}
		this.students[index] = student;
	}

	@Override
	public void addFirst(Student student) {
		if (student == null) {
			throw new IllegalArgumentException();
		}
		Student[] tempStudent = new Student[this.students.length + 1];
		tempStudent[0] = student;
		for (int i = 0; i < this.students.length; i++) {
			tempStudent[i + 1] = this.students[i];
		}
		this.students = tempStudent;
	}

	@Override
	public void addLast(Student student) {
		if (student == null) {
			throw new IllegalArgumentException();
		}
		Student[] tempStudents = new Student[this.students.length + 1];
		for (int i = 0; i < this.students.length; i++) {
			tempStudents[i] = this.students[i];
		}
		tempStudents[this.students.length] = student;
		this.students = tempStudents;
	}

	@Override
	public void add(Student student, int index) {
		if (student == null || (index < 0 && index >= this.students.length)) {
			throw new IllegalArgumentException();
		}
		Student[] tempStudents = new Student[this.students.length + 1];
		for (int i = 0; i < index; i++) {
			tempStudents[i] = this.students[i];
		}
		tempStudents[index] = student;
		for (int i = index; i < students.length; i++) {
			tempStudents[i + 1] = students[i];
		}
		this.students = tempStudents;
	}

	@Override
	public void remove(int index) {
		if (index < 0 && index >= this.students.length) {
			throw new IllegalArgumentException();
		}
		Student[] tempStudents = new Student[this.students.length - 1];
		for (int i = 0; i < index; i++) {
			tempStudents[i] = this.students[i];
		}
		for (int i = index; i < this.students.length - 1; i++) {
			tempStudents[i] = this.students[i + 1];
		}
		this.students = tempStudents;
	}

	@Override
	public void remove(Student student) {
		if (student == null) {
			throw new IllegalArgumentException();
		}
		int index = 0;
		for (int i = 0; i < this.students.length; i++) {
			if (this.students[i].equals(student)) {
				index = i;
			}
		}
		this.remove(index);
	}

	@Override
	public void removeFromIndex(int index) {
		if (index < 0 && index >= this.students.length) {
			throw new IllegalArgumentException();
		}
		Student[] tempStudents = new Student[index];
		for (int i = 0; i < tempStudents.length; i++) {
			tempStudents[i] = this.students[i];
		}
		this.students = tempStudents;
	}

	@Override
	public void removeFromElement(Student student) {
		if (student == null) {
			throw new IllegalArgumentException();
		}
		int index = 0;
		for (int i = 0; i < this.students.length; i++) {
			if (this.students[i].equals(student)) {
				index = i;
				break;
			}
		}
		this.removeFromIndex(index);
	}

	@Override
	public void removeToIndex(int index) {
		if (index < 0 && index >= this.students.length) {
			throw new IllegalArgumentException();
		}
		Student[] tempStudents = new Student[this.students.length - index];
		int j = 0;
		for (int i = index; i < this.students.length; i++) {
			tempStudents[j] = this.students[i];
			j++;
		}
		this.students = tempStudents;

	}

	@Override
	public void removeToElement(Student student) {
		if (student == null) {
			throw new IllegalArgumentException();
		}
		int index = 0;
		for (int i = 0; i < this.students.length; i++) {
			if (this.students[i].equals(student)) {
				index = i;
				break;
			}
		}
		this.removeToIndex(index);
	}

	@Override
	public void bubbleSort() {
		Student bubble = null;
		int flag = 1;
		while (flag != 0) {
			flag = 0;
			for (int i = 1; i < this.students.length; i++) {
				if (this.students[i - 1] == null && this.students[i] == null) {
					continue;
				}
				if (this.students[i - 1] == null && this.students[i] != null) {
					flag++;
					bubble = this.students[i];
					this.students[i] = this.students[i - 1];
					this.students[i - 1] = bubble;
				}
				if (this.students[i - 1] != null && this.students[i] == null) {
					continue;
				}
				if (this.students[i - 1] != null && this.students[i] != null) {
					if (this.students[i - 1].getFullName().compareToIgnoreCase(this.students[i].getFullName()) > 0) {
						flag++;
						bubble = this.students[i];
						this.students[i] = this.students[i - 1];
						this.students[i - 1] = bubble;
					}
				}
			}
		}
	}

	@Override
	public Student[] getByBirthDate(Date date) {
		if (date == null) {
			throw new IllegalArgumentException();
		}
		int count = 0;
		for (Student student : this.students) {
			if (student == null) {
				continue;
			}
			if (student.getBirthDate().equals(date)) {
				count++;
			}
		}
		Student[] stud = new Student[count];
		int j = 0;
		for (Student student : this.students) {
			if (student.getBirthDate().equals(date)) {
				stud[j] = student;
				j++;
			}
		}
		return stud;
	}

	@Override
	public Student[] getBetweenBirthDates(Date firstDate, Date lastDate) {
		if (firstDate == null || lastDate == null) {
			throw new IllegalArgumentException();
		}
		int count = 0;
		for (Student student : this.students) {
			if (student == null) {
				continue;
			}
			if (student.getBirthDate().after(firstDate) && student.getBirthDate().before(lastDate)) {
				count++;
			}
		}
		Student[] temp = new Student[count];
		int j = 0;
		for (Student student : this.students) {
			if (student == null) {
				continue;
			}
			if (student.getBirthDate().after(firstDate) && student.getBirthDate().before(lastDate)) {
				temp[j] = student;
				j++;
			}
		}
		return temp;
	}

	@Override
	public Student[] getNearBirthDate(Date date, int days) {
		if (date == null) {
			throw new IllegalArgumentException();
		}
		Calendar top = Calendar.getInstance();
		top.setTime(date);
		top.add(Calendar.DATE, days);
		Calendar bot = Calendar.getInstance();
		bot.setTime(date);
		bot.add(Calendar.DATE, -2);
		Calendar now = Calendar.getInstance();
		int count = 0;
		for (Student student : this.students) {
			if (student == null) {
				continue;
			}
			now.setTime(student.getBirthDate());
			if (now.after(bot) && now.before(top)) {
				count++;
			}
		}
		Student[] temp = new Student[count];
		int j = 0;
		for (Student student : this.students) {
			if (student == null) {
				continue;
			}
			now.setTime(student.getBirthDate());
			if (now.after(bot) && now.before(top)) {
				temp[j] = student;
				j++;
			}
		}
		return temp;
	}

	@Override
	public int getCurrentAgeByDate(int indexOfStudent) {
		if (indexOfStudent == 0) {
			throw new IllegalArgumentException();
		}
		Calendar today = Calendar.getInstance();
		Calendar bday = Calendar.getInstance();
		bday.setTime(this.students[indexOfStudent].getBirthDate());
		int diff = today.get(Calendar.YEAR) - bday.get(Calendar.YEAR);
		if (bday.get(Calendar.MONTH) > today.get(Calendar.MONTH)
				|| bday.get(Calendar.MONTH) == today.get(Calendar.MONTH)
						&& bday.get(Calendar.DATE) > today.get(Calendar.DATE)) {
			diff--;
		}

		return diff;
	}

	@Override
	public Student[] getStudentsByAge(int age) {
		int count = 0;
		Calendar today = Calendar.getInstance();
		Calendar bday = Calendar.getInstance();
		for (Student student : this.students) {
			if (student == null) {
				continue;
			}
			bday.setTime(student.getBirthDate());
			int diff = today.get(Calendar.YEAR) - bday.get(Calendar.YEAR);
			if (bday.get(Calendar.MONTH) > today.get(Calendar.MONTH)
					|| bday.get(Calendar.MONTH) == today.get(Calendar.MONTH)
							&& bday.get(Calendar.DATE) > today.get(Calendar.DATE)) {
				diff--;
			}
			if (diff == age) {
				count++;
			}
		}
		Student[] temp = new Student[count];
		int j = 0;
		for (Student student : this.students) {
			if (student == null) {
				continue;
			}
			bday.setTime(student.getBirthDate());
			int diff = today.get(Calendar.YEAR) - bday.get(Calendar.YEAR);
			if (bday.get(Calendar.MONTH) > today.get(Calendar.MONTH)
					|| bday.get(Calendar.MONTH) == today.get(Calendar.MONTH)
							&& bday.get(Calendar.DATE) > today.get(Calendar.DATE)) {
				diff--;
			}
			if (diff == age) {
				temp[j] = student;
				j++;
			}
		}

		return temp;
	}

	@Override
	public Student[] getStudentsWithMaxAvgMark() {
		double max = 0;
		int count = 0;
		for (Student student : this.students) {
			if (student == null) {
				continue;
			}
			if (student.getAvgMark() > max) {
				max = student.getAvgMark();
			}
		}
		for (Student student : this.students) {
			if (student == null) {
				continue;
			}
			if (student.getAvgMark() == max) {
				count++;
			}
		}
		Student[] temp = new Student[count];
		int j = 0;
		for (Student student : this.students) {
			if (student == null) {
				continue;
			}
			if (student.getAvgMark() == max) {
				temp[j] = student;
				j++;
			}
		}
		return temp;
	}

	@Override
	public Student getNextStudent(Student student) {
		if (student == null) {
			throw new IllegalArgumentException();
		}
		int index = 0;
		for (int i = 0; i < this.students.length; i++) {
			if (students[i] == null) {
				continue;
			}
			if (students[i].equals(student)) {
				index = i + 1;
				break;
			}
			if (index == this.students.length - 1) {
				return null;
			}
		}
		return this.students[index];
	}
}