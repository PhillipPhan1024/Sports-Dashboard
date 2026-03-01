import { useState, useEffect } from "react";
import axios from "axios";
import Scoreboard from "./components/Scoreboard";
import "./App.css";

function App() {
  const [games, setGames] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);

  const today = new Date().toLocaleDateString("en-CA");
  const fetchGames = async () => {
    try {
      const response = await axios.get(`/api/games?date=${today}`);
      const data = Array.isArray(response.data)
        ? response.data
        : response.data.data || [];
      setGames(data);
      setError(null);
    } catch (err) {
      setError("Failed to fetch games. Is the backend running?");
    } finally {
      setLoading(false);
    }
  };

  useEffect(() => {
    fetchGames();
    const interval = setInterval(fetchGames, 30000); // poll every 30s
    return () => clearInterval(interval); // cleanup on unmount
    // eslint-disable-next-line react-hooks/exhaustive-deps
  }, []);

  return (
    <div className="app">
      <header className="header">
        <h1>🏀 NBA Live Dashboard</h1>
        <p className="date">{today}</p>
      </header>
      <main>
        {loading && <p className="status">Loading games...</p>}
        {error && <p className="status error">{error}</p>}
        {!loading && !error && games.length === 0 && (
          <p className="status">No games today.</p>
        )}
        {!loading && !error && <Scoreboard games={games} />}
      </main>
    </div>
  );
}

export default App;
