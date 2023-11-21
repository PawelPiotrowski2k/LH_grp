package Models;

public class Cook {
    private final String name;

    public Cook(String name) {
        this.name = name;
    }
    public void prepareFood(){
        System.out.println("preparing...");
    }
}
