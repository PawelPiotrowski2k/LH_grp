public class TableManager {
    private final int numerOfTables;
    private int freeTables;

    public TableManager(int numerOfTables, int freeTables) {
        this.numerOfTables = numerOfTables;
        this.freeTables = freeTables;
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
