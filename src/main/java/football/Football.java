package football;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;

@XmlRootElement(name = "Player")
@XmlType(propOrder = { "age", "number", "position", "salary" })
public class Football {
  //  @XmlElement(name = "Number1")
    private int number;
  //  @XmlElement(name = "Position1")
    private String position;
  //  @XmlElement(name = "Salary1")
    private int salary;
   // @XmlElement(name = "Age1")
    private int age;
    // XmLElementWrapper генерирует элемент-оболочку вокруг представления XML


    public void setAge(int age) {
        this.age = age;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public int getAge() {
        return age;
    }

    public int getNumber() {
        return number;
    }

    public String getPosition() {
        return position;
    }

    public int getSalary() {
        return salary;
    }

    @Override
    public String toString() {
        String result = "Number: " + getNumber() + ", position: " + getPosition() + ", salary: " + getSalary() + ", age: " + getAge();
        return result;
    }
}

