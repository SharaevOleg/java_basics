class Manager implements Employee {

    Integer salary;

    public Manager(Company company) {
       salary = (int) (0 + 50_0000 * Math.random());;
    }

    @Override
    public Integer getMonthSalary() {
        int monthSalary = 33333 + salary/100*5;
        return monthSalary;
    }
//    double salaryManager;
//    private double income;
//
//    public Manager(double income){
//        this.income = income;
//    }
//    public void setIncome(double income){
//        this.income = income;
//    }
//
//    @Override
//    public double getMonthSalary() {
//        salaryManager = 40_000;
//        if(income != income){
//            return salaryManager * 0.5;
//        } else
//            return salaryManager;
//    }
}


