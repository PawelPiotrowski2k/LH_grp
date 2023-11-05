import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class PenaltyManager {
    private final Map<User, Integer> penaltyPointsMap;
    private final Map<User, LocalDate> banDate;


    public PenaltyManager() {
        this.penaltyPointsMap = new HashMap<>();
        this.banDate = new HashMap<>();
    }


    public void setBanDate(LocalDate localDate, User user) {
        banDate.compute(user, (key, oldDate) -> {
            return LocalDate.now();
        });
    }

    public Map<User, LocalDate> getBanDate() {
        return banDate;
    }

    public void increasePenaltyPoints(User user, int points) {
        penaltyPointsMap.compute(user, (key, value) -> {
            if (value != null) {
                return value + points;
            } else
                return points;
        });
    }

    public int getPenaltyPoints(User user) {
        return penaltyPointsMap.getOrDefault(user, 0);
    }

    public void resetPenaltyPoints(User user) {
        penaltyPointsMap.put(user, 0);
    }


}
