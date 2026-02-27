import React from "react";
import GameCard from "./GameCard";

function Scoreboard({ games }) {
  return (
    <div className="scoreboard">
      {games.map((game) => (
        <GameCard key={game.id} game={game} />
      ))}
    </div>
  );
}

export default Scoreboard;
