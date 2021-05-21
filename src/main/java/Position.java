public enum Position {

    ASSISTANT("assistant"), BOARD_MEMBER("board member"), COORDINATOR("coordinator"), DIRECTOR("director"),  MANAGER("manager"), SPECIALIST("specialist");

    private String positionName;

    Position(String positionName) {
        this.positionName = positionName;
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
