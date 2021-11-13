package io.project.ipldashboard.data;

import io.project.ipldashboard.model.Match;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;

/**
 * Class that transforms <code>MatchInput</code> object to <code>Match</code> entity.
 * */

public class MatchDataProcessor implements ItemProcessor<MatchInput, Match> {
    private static final Logger log = LoggerFactory.getLogger(MatchDataProcessor.class);

    @Override
    public Match process(final MatchInput matchInput) throws Exception {
        return new Match(matchInput);
    }
}
