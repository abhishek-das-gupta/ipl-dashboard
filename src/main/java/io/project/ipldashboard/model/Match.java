package io.project.ipldashboard.model;

import io.project.ipldashboard.data.MatchInput;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * POJO for Match table residing in the embedded database.
 * */

public class Match {
    private long id;
    private String city;
    private LocalDateTime date;
    private String playerOfMatch;
    private String venue;
    private String team1; // team1 always bats
    private String team2; // team2 always fields
    private String tossWinner;
    private String tossDecision;
    private String matchWinner;
    private String result;
    private String resultMargin;
    private String umpire1;
    private String umpire2;

    public Match() {
    }

    public Match(MatchInput matchInput) {
        this.id = Long.parseLong(matchInput.getId());
        this.city = matchInput.getCity();
        this.date = LocalDateTime.parse(matchInput.getDate());
        this.playerOfMatch = matchInput.getPlayerOfMatch();
        this.venue = matchInput.getVenue();
        String firstInningsTeam, secondInningsTeam;
        if (matchInput.getTossDecision().equalsIgnoreCase("bat")) {
            firstInningsTeam = matchInput.getTossWinner();
            secondInningsTeam = matchInput.getTeam1().equalsIgnoreCase(firstInningsTeam) ?
                    matchInput.getTeam2() : matchInput.getTeam1();
        } else {
            secondInningsTeam = matchInput.getTossWinner();
            firstInningsTeam = matchInput.getTeam1().equalsIgnoreCase(secondInningsTeam) ?
                    matchInput.getTeam2() : matchInput.getTeam1();
        }
        this.team1 = firstInningsTeam;
        this.team2 = secondInningsTeam;
        this.tossWinner = matchInput.getTossWinner();
        this.tossDecision = matchInput.getTossWinner();
        this.result = matchInput.getResult();
        this.resultMargin = matchInput.getResultMargin();
        this.umpire1 = matchInput.getUmpire1();
        this.umpire2 = matchInput.getUmpire2();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public String getPlayerOfMatch() {
        return playerOfMatch;
    }

    public void setPlayerOfMatch(String playerOfMatch) {
        this.playerOfMatch = playerOfMatch;
    }

    public String getVenue() {
        return venue;
    }

    public void setVenue(String venue) {
        this.venue = venue;
    }

    public String getTeam1() {
        return team1;
    }

    public void setTeam1(String team1) {
        this.team1 = team1;
    }

    public String getTeam2() {
        return team2;
    }

    public void setTeam2(String team2) {
        this.team2 = team2;
    }

    public String getTossWinner() {
        return tossWinner;
    }

    public void setTossWinner(String tossWinner) {
        this.tossWinner = tossWinner;
    }

    public String getTossDecision() {
        return tossDecision;
    }

    public void setTossDecision(String tossDecision) {
        this.tossDecision = tossDecision;
    }

    public String getMatchWinner() {
        return matchWinner;
    }

    public void setMatchWinner(String matchWinner) {
        this.matchWinner = matchWinner;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getResultMargin() {
        return resultMargin;
    }

    public void setResultMargin(String resultMargin) {
        this.resultMargin = resultMargin;
    }

    public String getUmpire1() {
        return umpire1;
    }

    public void setUmpire1(String umpire1) {
        this.umpire1 = umpire1;
    }

    public String getUmpire2() {
        return umpire2;
    }

    public void setUmpire2(String umpire2) {
        this.umpire2 = umpire2;
    }
}