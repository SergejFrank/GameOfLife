package otto.de;

public enum GameStatus {
    STARTED(true), PAUSED(false);

    boolean status;
    GameStatus(boolean value) {
        status = value;
    }

    boolean isStarted() {
        return status;
    }

    String getColor(){
        if(status){
            return "#27AE61";
        }
        else{
            return "#E84C3D";
        }
    }
}
