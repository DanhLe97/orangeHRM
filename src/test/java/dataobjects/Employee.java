package dataobjects;

public class Employee {
    private String firstName;
    private String midName;
    private String lastName;
    private String employeeId;
    private String userName;
    private String passWord;
    private String confirmpassWord;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMidName() {
        return midName;
    }

    public void setMidName(String midName) {
        this.midName = midName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getConfirmpassWord() {
        return confirmpassWord;
    }

    public void setConfirmpassWord(String confirmpassWord) {
        this.confirmpassWord = confirmpassWord;
    }

    public Employee() {
    }

    public Employee(String firstName, String midName, String lastName, String employeeId, String userName, String passWord, String confirmpassWord) {
        this.firstName = firstName;
        this.midName = midName;
        this.lastName = lastName;
        this.employeeId = employeeId;
        this.userName = userName;
        this.passWord = passWord;
        this.confirmpassWord = confirmpassWord;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "firstName='" + firstName + '\'' +
                ", midName='" + midName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", employeeId='" + employeeId + '\'' +
                ", userName='" + userName + '\'' +
                ", passWord='" + passWord + '\'' +
                ", confirmpassWord='" + confirmpassWord + '\'' +
                '}';
    }

}
