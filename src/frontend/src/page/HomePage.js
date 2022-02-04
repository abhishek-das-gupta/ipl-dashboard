import { React, useEffect, useState } from 'react';
import { TeamTile } from '../component/TeamTile';
import './HomePage.scss';

export const HomePage = () => {
    const [teams, setTeams] = useState([]);
    useEffect(
        () => {
            const fetchTeams = async () => {
                const response = await fetch(`http://localhost:8080/team`);
                setTeams(await response.json());
            };
            fetchTeams();
        }, []
    );
    
    return (
        <div className = "HomePage">
            <div className = "header-section">
                <h1 className = "app-name">IPL Dashboard</h1>
            </div>
            <div className = "team-grid">
                {teams.map(team => <TeamTile key = {team.id} teamName = {team.teamName} />)}
            </div>
        </div>
    );
}