public class Operator implements Employee {


    public Operator(Company company) {
        company.hire(getMonthSalary());
    }

    /**    Вам нужно создать в классах сотрудников конструкторы, принимающие в качестве
   параметров Company company и при создании сотрудников передавать туда компанию.**/

    @Override
    public int getMonthSalary() {
        int monthSalary = 60000;
        return monthSalary;
    }
}
