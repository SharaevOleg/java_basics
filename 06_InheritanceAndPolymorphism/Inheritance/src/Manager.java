class Manager implements Employee {
    String name;
    Integer salary;

    public Manager(Company company) {
        this.name = getClass().getName();
        this.salary = getMonthSalary();
    }

    @Override
    public Integer getMonthSalary() {
        int earnedMoney = (int) (50000 + 500000 * Math.random());
        int monthSalary = 50000 + earnedMoney / 20;
        return monthSalary;
    }
}


