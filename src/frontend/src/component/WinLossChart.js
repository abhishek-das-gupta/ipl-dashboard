import { PieChart } from 'react-minimal-pie-chart';
import './WinLossChart.scss';

export const WinLossChart = ({totalMatches, totalWins}) => {
    return (
            <div className = "WinLossChart">
                <div className="win-loss-section">
                    Wins / Losses
                    <PieChart
                    data={[
                        { title: 'Losses', value: totalMatches - totalWins, color: '#a34d5d' },
                        { title: 'Wins', value: totalWins, color: '#4da375' },
                    ]}
                    />
                </div>
            </div>
    );
}