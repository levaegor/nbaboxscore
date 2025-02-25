public class TeamStats {
    private Team team;
    private Match match;
    private int points;
    private int rebounds;
    private int assists;

    public TeamStats(Team team, Match match){
        this.team = team;
        this.match = match;
    }

    public Team getTeam() {
        return team;
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
