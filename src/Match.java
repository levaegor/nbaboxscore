import java.util.ArrayList;

public class Match {
    private Day day;
    private Team team1;
    private Team team2;
    public TeamStats stats1;
    public TeamStats stats2;
    public ArrayList<PlayerStats> playerStats1;
    public ArrayList<PlayerStats> playerStats2;

    public Match(Day day, Team team1, Team team2) {
        this.day = day;
        this.team1 = team1;
        this.team2 = team2;
        this.stats1 = new TeamStats(team1, this);
        this.stats2 = new TeamStats(team2, this);
        this.playerStats1 = new ArrayList<PlayerStats>();
        this.playerStats2 = new ArrayList<PlayerStats>();
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

    public void addPlayerStats1(PlayerStats playerStats) {
        this.playerStats1.add(playerStats);
    }

    public void addPlayerStats2(PlayerStats playerStats) {
        this.playerStats2.add(playerStats);
    }
}