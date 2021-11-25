package io.project.ipldashboard.repository;

import io.project.ipldashboard.model.Match;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface MatchRepository extends CrudRepository<Match, Long> {

    List<Match> getByTeam1OrTeam2OrderByDateDesc(String teamName1, String teamName2, Pageable pageable);

    @Query("SELECT m FROM Match m WHERE (m.team1 = :team_name or m.team2 = :team_name) AND (m.date between :start_date and :end_date)")
    List<Match> getMatchesPlayedByTeamBetweenDates(
            @Param("team_name") String teamName,
            @Param("start_date") LocalDate startDate,
            @Param("end_date") LocalDate endDate);

    List<Match> getByTeam1AndDateBetweenOrTeam2AndDateBetweenOrderByDateDesc(
            String team1, LocalDate date1, LocalDate date2,
            String team2, LocalDate date3, LocalDate date4);

    default List<Match> getLatestMatchesUsingCount(String teamName, int count) {
        return getByTeam1OrTeam2OrderByDateDesc(teamName, teamName, PageRequest.of(0, count));
    }
}
