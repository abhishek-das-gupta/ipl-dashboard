import { React, useEffect, useState } from 'react';
import { useParams } from 'react-router';
import { MatchDetailCard } from '../component/MatchDetailCard';
import { YearSelector } from '../component/YearSelector';
import './MatchPage.scss';


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
        }, [teamName, year]
    );
    return (
        <div className = "MatchPage">
            <div className = "YearSelector">
                <h3>Select Year</h3>
                <YearSelector teamName = {teamName}/>
            </div>    
            <div>
                <h1 className = "page-header"> 
                    {teamName} matches in year {year}
                </h1>
                {matches.map(match => <MatchDetailCard key = {match.id} teamName = {teamName} match = {match} />)}
            </div>
        </div>
    );
}