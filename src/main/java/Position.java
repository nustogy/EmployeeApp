public enum Position {

    ASSISTANT("assistant", 1000, 3000), BOARD_MEMBER("board member", 10000, 300000), COORDINATOR("coordinator", 5000, 15000),
    DIRECTOR("director", 20000, 50000),  MANAGER("manager", 5000, 13000), SPECIALIST("specialist",3000,7000);

    private String positionName;
    private int minSalary;
    private int maxSalary;

    public int getMinSalary() {
        return minSalary;
    }

    public int getMaxSalary() {
        return maxSalary;
    }

    Position(String positionName) {
        this.positionName = positionName;
    }

    Position(String positionName, int minSalary, int maxSalary) {
        this.positionName = positionName;
        this.minSalary = minSalary;
        this.maxSalary = maxSalary;
    }

    @Override
    public String toString() {
        return positionName;
    }

    public void setPositionName(String positionName) {
        this.positionName = positionName;
    }

    public String getPositionName() {
        return positionName;
    }

}
