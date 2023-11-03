import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class PenaltyManager {
    private final Map<User, Integer> penaltyPointsMap;
    private final Map<User, LocalDate> banDate;
    private final Map<Book, LocalDate> deadLineDate;

    public PenaltyManager() {
        this.penaltyPointsMap = new HashMap<>();
        this.banDate = new HashMap<>();
        this.deadLineDate = new HashMap<>();
    }


    public void setBanDate(LocalDate localDate, User user) {
        banDate.put(user, localDate);
    }

    public LocalDate getBanDate(User user) {
        return banDate.get(user);
    }

    public void setDeadLineDate(Book book, LocalDate localDate) {
        deadLineDate.put(book, localDate);
    }

    public LocalDate getDeadLineDate(Book book) {
        return deadLineDate.get(book);
    }

    public void increasePenaltyPoints(User user, int points) {
        penaltyPointsMap.put(user, penaltyPointsMap.getOrDefault(user, 0) + points);
    }

    public int getPenaltyPoints(User user) {
        return penaltyPointsMap.getOrDefault(user, 0);
    }

    public void resetPenaltyPoints(User user) {
        penaltyPointsMap.put(user, 0);
    }


}
