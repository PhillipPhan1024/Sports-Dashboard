function GameCard({ game }) {
  const isLive = game.status === "in progress";
  const isFinal = game.status === "Final";

  return (
    <div className={`game-card ${isLive ? "live" : ""}`}>
      {isLive && <span className="live-badge">● LIVE</span>}
      {isFinal && <span className="final-badge">FINAL</span>}

      <div className="teams">
        <div className="team">
          <span className="team-name">
            {game.home_team?.city} {game.home_team?.name}
          </span>
          <span className="score">{game.home_team_score}</span>
        </div>

        <div className="divider">vs</div>

        <div className="team visitor">
          <span className="team-name">
            {game.visitor_team?.city} {game.visitor_team?.name}
          </span>
          <span className="score">{game.visitor_team_score}</span>
        </div>
      </div>

      <div className="game-info">
        {isLive && (
          <span>
            Period {game.period} • {game.time}
          </span>
        )}
        {!isLive && !isFinal && (
          <span>
            {new Date(game.status).toLocaleTimeString([], {
              hour: "2-digit",
              minute: "2-digit",
              timeZoneName: "short",
            })}
          </span>
        )}
      </div>
    </div>
  );
}

export default GameCard;
