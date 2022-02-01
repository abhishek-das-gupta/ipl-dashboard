import { React, useEffect, useState } from 'react';
import { useParams } from 'react-router';
import { MatchDetailCard } from '../component/MatchDetailCard';
import { MatchSmallCard } from '../component/MatchSmallCard';
import { PieChart } from 'react-minimal-pie-chart';
import { Link } from 'react-router-dom';

import './TeamPage.scss';

export const TeamPage = () => {

    const [team, setTeam] = useState({ matches: [] });
    const { teamName } = useParams(); 
    useEffect(
        () => {
            const fetchMatches = async () => {
                const response = await fetch(`http://localhost:8080/team/${teamName}`);
                setTeam(await response.json());
            };
            fetchMatches();
        }, [teamName]
    );
    if (team && team.teamName) {
        return (
            <div className="TeamPage">
                <div className="team-name-section">
                    <h1 className="team-name">{team.teamName}</h1>
                </div>
                <div className="win-loss-section">
                    Wins / Losses
                                <PieChart
                data={[
                    { title: 'Losses', value: team.totalMatches - team.totalWins, color: '#a34d5d' },
                    { title: 'Wins', value: team.totalWins, color: '#4da375' },
                ]}
                />
                </div>
                <div className = "match-detail-section">
                    <h3>Latest Matches</h3>
                    <MatchDetailCard teamName = {teamName} match = {team.matches[0]} />
                </div>
                {team.matches.slice(1).map(match => <MatchSmallCard key = {match.id} teamName = {teamName} match = {match} /> )}
                <div className="more-link">
                    <Link to = {`/teams/${team.teamName}/matches/${process.env.REACT_APP_DATA_END_YEAR}`}>More ></Link>
                </div>
            </div>
        );
    } else {
        return <h1>Team not found!</h1>
    }
}