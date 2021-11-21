import { React, useEffect, useState } from 'react';
import { useParams } from 'react-router';
import { MatchDetailCard } from '../component/MatchDetailCard';
import { MatchSmallCard } from '../component/MatchSmallCard';

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
                <h1>{team.teamName}</h1>
                <MatchDetailCard teamName = {teamName} match = {team.matches[0]} />
                {team.matches.slice(1).map(match => <MatchSmallCard key = {match.id} teamName = {teamName} match = {match} /> )}
            </div>
        );
    } else {
        return <h1>Team not found!</h1>
    }
}