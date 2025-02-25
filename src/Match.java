public class Match {
    private Day day;
    private Team team1;
    private Team team2;
    private TeamStats stats1;
    private TeamStats stats2;

    public Match(Day day, Team team1, Team team2) {
        this.day = day;
        this.team1 = team1;
        this.team2 = team2;
        this.stats1 = new TeamStats(team1, this);
        this.stats2 = new TeamStats(team2, this);
    }

    public Day getDay() {
        return day;
    }

    public Team getTeam1() {
        return team1;
    }

    public Team getTeam2() {
        return team2;
    }

    public TeamStats getStats1() {
        return stats1;
    }

    public TeamStats getStats2() {
        return stats2;
    }
}
