package org.example;

public class Employee {
    public long id;
    public String firstName;
    public String lastName;
    public String country;
    public int age;

    public long getId() {
        return id;
    }
    public void setId(String id) {
        this.id = Long.parseLong(id);
    }
    public String getfirstName() {
        return firstName;
    }
    public void setfirstName(String name) {
        this.firstName = name;
    }
    public String getlastName() {
        return lastName;
    }
    public void setlastName(String name) {
        this.lastName = name;
    }
    public int getAge() {
        return age;
    }
    public void setAge(String age) {
        this.age = Integer.parseInt(age);
    }
    public String getCountry() {
        return country;
    }
    public void setCountry(String country) {
        this.country = country;
    }

//    @Override
//    public String toString() {
//         return "{" + id + "::" + lastName + "::" + firstName +"::" + country + "::" + age + "}";
//     }
    @Override
    public String toString() {
    return "Employee {" + "id=" + id + ", " + "firstName =" + "'"+ firstName + "'" + ", " + "lastName =" + "'" + lastName + "'" + ", " + "country = " + "'" + country + "'" + ", " + "age = " + age + "}";
    }

    public Employee() {
        // Пустой конструктор
    }

    public Employee(long id, String firstName, String lastName, String country, int age) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.country = country;
        this.age = age;
    }
}
