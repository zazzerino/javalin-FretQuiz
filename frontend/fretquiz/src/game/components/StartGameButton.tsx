import React from 'react';
import { useSelector } from 'react-redux';
import { sendStartGame } from '../../websocket/game';
import { selectGameId } from '../gameSlice';

export function StartGameButton() {
  const gameId = useSelector(selectGameId);

  return (
    <div className="StartGameButton">
      <button onClick={() => gameId && sendStartGame(gameId)}>
        Start Game
      </button>
    </div>
  );
}
