class Manager implements Employee {

    @Override
    public int getMonthSalary() {
        int earnedMoney = (int) (50000 + 500000 * Math.random());
        int monthSalary = 50000 + earnedMoney / 20;
        return monthSalary;
    }

    }


