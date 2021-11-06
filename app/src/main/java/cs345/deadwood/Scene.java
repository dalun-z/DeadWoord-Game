package cs345.deadwood;

public class Scene {
    int starRoleCount;
    int payout;

    public Scene(int roles, int payout) {
        this.starRoleCount = roles;
        this.payout = payout;
    }

    public int getPayout() {
        return this.payout;
    }
}
