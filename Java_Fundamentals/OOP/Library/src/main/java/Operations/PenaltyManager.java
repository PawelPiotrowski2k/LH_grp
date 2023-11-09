package Operations;

import Models.User;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public class PenaltyManager {
    private final Map<String, Integer> penaltyPointsMap;
    private final Map<String, LocalDateTime> banDate;


    public PenaltyManager() {
        this.penaltyPointsMap = new HashMap<>();
        this.banDate = new HashMap<>();
    }


    public void setBanDate(LocalDateTime localDate, User user) {
        banDate.compute(user.getId(), (key, oldDate) -> localDate);
    }

    public void addPenaltyPoints(String id, int points) {
        penaltyPointsMap.compute(id, (key, value) -> value != null ? value + points : points);
    }

    public void subPenaltyPoints(String id, int points) {
        penaltyPointsMap.compute(id, (key, value) -> value != null ? value - points : points);
    }

    public int getPenaltyPoints(User user) {
        return penaltyPointsMap.getOrDefault(user.getId(), 0);
    }

    public int calculateRentBan(int penaltyPoints) {
        int montsOfBan;
        if (penaltyPoints > 10) {
            montsOfBan = penaltyPoints / 10;
            return montsOfBan;
        }
        return 0;
    }

    public boolean isPenalty(User user) {
        if (banDate.containsKey(user) && banDate.get(user).isAfter(LocalDateTime.now())) {
            return true;
        }
        user.setSuspended(false);
        return false;
    }

    public void banProcedure(User user) {
        if (getPenaltyPoints(user) > 10) {
            int montsOfBan = calculateRentBan(getPenaltyPoints(user));
            setBanDate(LocalDateTime.now().plusMonths(montsOfBan), user);
            subPenaltyPoints(user.getId(), montsOfBan * 10);
            user.setSuspended(true);
        }
    }
}
