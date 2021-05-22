public class Employee {



    private String name, surname;
    private Position position;
    private int jobSeniority, salary;

    public Employee(String name, String surname, int jobSeniority, int salary, Position position) throws SalaryOutOfBoundsException {
        this.name = name;
        this.surname = surname;
        this.jobSeniority = jobSeniority;
        this.salary = salary;
        this.position = position;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public int getJobSeniority() {
        return jobSeniority;
    }

    public void setJobSeniority(int jobSeniority) {
        this.jobSeniority = jobSeniority;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return name + '\t'
                + surname + '\t'
                + jobSeniority + '\t'
                + salary + '\t'
                + position + '\n';
    }

    public boolean validateSalary() {
      return salary <= position.getMaxSalary() && salary >= position.getMinSalary();

    }
}
