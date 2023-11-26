public class TableManager {
    private final int numerOfTables;
    private int freeTables;

    public TableManager(int numerOfTables) {
        this.numerOfTables = numerOfTables;
        this.freeTables = numerOfTables;
    }


    public boolean assignCustomerToTable(){
        if(freeTables > 0){
            freeTables -= 1;
            return true;
        }else{
            return false;
        }

    }
    public void cleanTable(){
        freeTables += 1;
    }
}
