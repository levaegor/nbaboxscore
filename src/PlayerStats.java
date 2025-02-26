public class PlayerStats {
    private Player player;
    private Match match;
    private int points;
    private int rebounds;
    private int assists;

    public PlayerStats(Player player, Match match) {
        this.player = player;
        this.match = match;
    }

    public Player getPlayer() {
        return player;
    }

    public Match getMatch() {
        return match;
    }

    public int getPoints() {
        return points;
    }

    public int getRebounds() {
        return rebounds;
    }

    public int getAssists() {
        return assists;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public void setRebounds(int rebounds) {
        this.rebounds = rebounds;
    }

    public void setAssists(int assists) {
        this.assists = assists;
    }

}
