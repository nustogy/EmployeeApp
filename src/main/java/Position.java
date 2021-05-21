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

    public static Position setPositionByPositionName(String positionName){
        switch(positionName){

            case "assistant": return ASSISTANT;
            case "board member": return BOARD_MEMBER;
            case "coordinator": return COORDINATOR;
            case "director": return DIRECTOR;
            case "manager": return MANAGER;
            case "specialist": return SPECIALIST;

        }
        return null;
    }
}
