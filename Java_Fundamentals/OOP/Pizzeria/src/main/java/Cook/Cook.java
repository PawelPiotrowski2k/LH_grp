package Cook;

public class Cook {
    private final String name;
    private boolean isBussy;

    public Cook(String name, boolean isBussy) {
        this.name = name;
        this.isBussy = isBussy;
    }

    public boolean isBussy() {
        return isBussy;
    }
    public void setBussy(boolean isBussy){
        this.isBussy = isBussy;
    }
}
