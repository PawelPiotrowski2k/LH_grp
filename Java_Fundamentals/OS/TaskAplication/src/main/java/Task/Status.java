package Task;

public enum Status {
    SENT("Wyslany"),
    REJECTED("Odrzucony"),
    APROVED("zatwierdzono"),
    IN_PROGRESS("W trakcie realizacji"),
    ENDED("Zakończony");

    String value;
    Status(String value){
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
