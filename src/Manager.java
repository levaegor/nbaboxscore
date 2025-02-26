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
    static List<Day> schedule = new ArrayList<Day>();

    public static void initTeams() {
        for (String teamName: teamNames) {
            Team team = new Team(teamName);
            teams.add(team);
        }
        initPlayers();
    }

    public static void initPlayers() {
        for (Team team : teams) {
            List<Integer> jerseyNumbers = new ArrayList<Integer>();
            for (int i = 0; i <= 99; i++) {
                jerseyNumbers.add(i);
            }
            Collections.shuffle(jerseyNumbers);
            for (int i = 0; i < 15; i++) {
                Player player = new Player(jerseyNumbers.get(i), team);
                team.addPlayer(player);
            }
        }
    }


    public static ArrayList<Day> initSchedule() {
        LocalDate startDate = LocalDate.of(2025, Month.FEBRUARY, 25); //22.10.2024
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
                generateStats(match, shuffledTeams.get(0));
                generateStats(match, shuffledTeams.get(1));
                shuffledTeams.remove(0); //Java 8 :)
                shuffledTeams.remove(0);

//                for (PlayerStats playerStats: match.playerStats1) {
//                    System.out.println("\n" + playerStats.getPlayer().getJerseyNumber() + " " + playerStats.getPlayer().getTeam().getName()
//                            + " scored:" + playerStats.getPoints());
//                }
//                System.out.println(match.getTeam1().getName() + " scored" + match.stats1.getPoints());
//
//                for (PlayerStats playerStats: match.playerStats2) {
//                    System.out.println("\n" + playerStats.getPlayer().getJerseyNumber() + " " + playerStats.getPlayer().getTeam().getName()
//                            + " scored:" + playerStats.getPoints());
//                }
//                System.out.println(match.getTeam2().getName() + " scored" + match.stats2.getPoints());
            }
            schedule.add(gameDay);
        }

        return schedule;
    }

    public static void generateStats(Match match, Team team) {
        int teamPoints = 0, teamRebounds = 0, teamAssists = 0;
        ArrayList<Player> players = team.getPlayers();
        Collections.shuffle(players);
        List<Player> playedTonight = players.subList(0, 11);
        for (Player player: playedTonight) {
            PlayerStats playerStats = new PlayerStats(player, match);
            Random random = new Random();

            int playerPoints = (int) (random.nextGaussian() * 8.98 / 3.5 + 10.64); // mean = 10.64, std = 8.98
            playerStats.setPoints(playerPoints);
            teamPoints += playerPoints;

            int playerRebounds = (int) (random.nextGaussian() * 3.47 / 3.5 + 4.06); // mean = 4.06, std = 3.47
            playerStats.setRebounds(playerRebounds);
            teamRebounds += playerRebounds;

            int playerAssists = (int) (random.nextGaussian() * 2.66 / 3.5 + 2.49); // mean = 2.49, std = 2.66
            playerStats.setAssists(playerAssists);
            teamAssists += playerAssists;

            if (match.getTeam1() == team) {
                match.addPlayerStats1(playerStats);
            } else {
                match.addPlayerStats2(playerStats);
            }
        }
        if (match.getTeam1() == team) {
            match.getStats1().setPoints(teamPoints);
            match.getStats1().setRebounds(teamRebounds);
            match.getStats1().setAssists(teamAssists);
        } else {
            match.getStats2().setPoints(teamPoints);
            match.getStats2().setRebounds(teamRebounds);
            match.getStats2().setAssists(teamAssists);
        }
        if (match.getStats1().getPoints() > match.getStats2().getPoints()) {
            match.getStats1().setResult(true);
        } else if (match.getStats1().getPoints() < match.getStats2().getPoints()) {
            match.getStats2().setResult(true);
        } else { // лучший в мире за работой, костыль века
            int points1 = match.getStats1().getPoints();
            match.getStats1().setPoints(++points1);
            int player11 = match.getPlayerStats1().get(0).getPoints();
            match.getPlayerStats1().get(0).setPoints(++player11);
            match.getStats1().setResult(true);
        }
    }

    public static List<Team> getTeams() {
        return teams;
    }
}
