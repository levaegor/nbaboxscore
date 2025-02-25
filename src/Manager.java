import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Manager {
    private static String[] teamNames = {"Atlanta Hawks", "Boston Celtics", "Brooklyn Nets", "Charlotte Hornets",
            "Chicago Bulls", "Cleveland Cavaliers", "Dallas Mavericks", "Denver Nuggets",
            "Detroit Pistons", "Golden State Warriors", "Houston Rockets", "Indiana Pacers",
            "LA Clippers", "LA Lakers", "Memphis Grizzlies", "Miami Heat", "Milwaukee Bucks",
            "Minnesota Timberwolves", "New Orleans Pelicans", "New York Knicks",
            "Oklahoma City Thunder", "Orlando Magic", "Philadelphia 76ers", "Phoenix Suns",
            "Portland Trail Blazers", "Sacramento Kings", "San Antonio Spurs", "Toronto Raptors",
            "Utah Jazz", "Washington Wizards"};

    private static List<Team> teams = new ArrayList<Team>();
    private static List<Day> schedule = new ArrayList<Day>();

    public static void initTeams() {
        for (String teamName: teamNames) {
            Team team = new Team(teamName);
            teams.add(team);
        }
    }

    public static void initSchedule() {
        LocalDate startDate = LocalDate.of(2024, Month.OCTOBER, 22);
        LocalDate today = LocalDate.now();
        ArrayList<Day> schedule = new ArrayList<Day>();
        for (LocalDate d = startDate; d.isBefore(today); d = d.plusDays(1)) {
            Day gameDay = new Day(d);
            Collections.shuffle(teams);
            ArrayList<Team> shuffledTeams = new ArrayList<Team>(teams);
            Random random = new Random();
            int matchCount = random.nextInt(15 - 1) + 1;
            for (int i = 0; i < matchCount; i++) {
                Match match = new Match(gameDay, shuffledTeams.get(0), shuffledTeams.get(1));
                gameDay.addMatch(match);
                generateStats(match);
                shuffledTeams.remove(0);
                shuffledTeams.remove(0);
            }
            schedule.add(gameDay);
        }
    }

    public static void generateStats(Match match) {
    }

    public static List<Team> getTeams() {
        return teams;
    }
}
