public enum Status {
    SENT("wysłano"),
    REJECTED("odrzucono"),
    APROVED("zatwierdzono"),
    IN_PROGRESS("w trakcie realizacji"),
    ENDED("zakończono");

    String value;
    Status(String value){
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
