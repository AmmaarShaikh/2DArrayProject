public class Boat {
    private int size;
    private String name;
    private boolean faceDown;

    public Boat (String name, int size){
        this.name = name;
        this.size = size;
        faceDown = true;
    }

    public String getName(){
        return name;
    }

    public int getSize(){
        return size;
    }

    public boolean getFaceDown(){
        return faceDown;
    }

    public void isfacedown(boolean faceDown){
        this.faceDown = faceDown;
    }
}
