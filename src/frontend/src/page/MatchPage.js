import { React, useEffect, useState } from 'react';
import { useParams } from 'react-router';
import { MatchDetailCard } from '../component/MatchDetailCard';
import { MatchSmallCard } from '../component/MatchSmallCard';

export const MatchPage = () => {
    const [matches, setMatches] = useState([]);
    const {teamName, year} = useParams();
    useEffect(
        () => {
            const fetchMatches = async () => {
                const response = await fetch(`http://localhost:8080/team/${teamName}/matches?matchYear=${year}`);
                setMatches(await response.json());
            };
            fetchMatches();
        }, []
    );
    return (
        <div className = "MatchPage">
            <h1>Match Page</h1>
            {matches.map(match => <MatchDetailCard key = {match.id} teamName = {teamName} match = {match} />)}
        </div>
    );
}