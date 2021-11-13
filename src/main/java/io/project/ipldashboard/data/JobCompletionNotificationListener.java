package io.project.ipldashboard.data;

import io.project.ipldashboard.model.Team;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.listener.JobExecutionListenerSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.Map;

/**
 * Listener class that logs when batch jobs get successfully completed.
 */

@Component
public class JobCompletionNotificationListener extends JobExecutionListenerSupport {

    private static final Logger log = LoggerFactory.getLogger(JobCompletionNotificationListener.class);

    private final EntityManager em;

    @Autowired
    public JobCompletionNotificationListener(EntityManager em) {
        this.em = em;
    }

    @Override
    @Transactional
    public void afterJob(JobExecution jobExecution) {
        if (jobExecution.getStatus() == BatchStatus.COMPLETED) {
            log.info("!!! JOB FINISHED! Time to verify the results");

            Map<String, Team> mapNameToTeam = new HashMap<>();
            em.createQuery("select m.team1, count(*) from Match m group by m.team1", Object[].class)
                    .getResultList()
                    .stream()
                    .map(it -> new Team((String) it[0], (long) it[1]))
                    .forEach(it -> mapNameToTeam.put(it.getTeamName(), it));

            em.createQuery("select m.team2, count(*) from Match m group by m.team2", Object[].class)
                    .getResultList()
                    .forEach(it -> {
                        Team team = mapNameToTeam.getOrDefault(it[0], new Team((String) it[0], 0));
                        team.setTotalMatches(team.getTotalMatches() + (long) it[1]);
                    });

            em.createQuery("select m.matchWinner, count(*) from Match m group by m.matchWinner", Object[].class)
                    .getResultList()
                    .forEach(it -> {
                        Team team = mapNameToTeam.get(it[0]);
                        if (team != null) {
                            team.setTotalWins((long) it[1]);
                        }
                    });
            mapNameToTeam.values().forEach(em::persist);
            mapNameToTeam.values().forEach(System.out::println);
        }
    }
}
