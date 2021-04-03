import React from 'react';
import { useSelector } from 'react-redux';
import { CreateGameButton } from '../game/components/CreateGameButton';
import { Fretboard } from '../game/components/Fretboard';
import { NextRoundButton } from '../game/components/NextRoundButton';
import { StartGameButton } from '../game/components/StartGameButton';
import { Stave } from '../game/components/Stave';
import { UserScore } from '../game/components/UserScore';
import { selectGameId, selectGameIsOver, selectReadyToStart, selectRoundIsOver } from '../game/gameSlice';

function RoundOver() {
  return (
    <div className="RoundOver">
      <NextRoundButton />
    </div>
  );
}

function GameCanvas() {
  const gameId = useSelector(selectGameId);
  const readyToStart = useSelector(selectReadyToStart);
  const roundIsOver = useSelector(selectRoundIsOver);

  return (
    <div className="GameCanvas">
      <Stave />
      <Fretboard />
      {!gameId && <CreateGameButton />}
      {readyToStart && <StartGameButton />}
      {roundIsOver && <RoundOver />}
    </div>
  );
}

function GameOver() {
  return (
    <div className="GameOver">
      <h2>Game Over</h2>
      <UserScore />
      <CreateGameButton />
    </div>
  )
}

export function Game() {
  const gameIsOver = useSelector(selectGameIsOver);

  return (
    <div className="Game">
      {gameIsOver
        ? <GameOver />
        : <GameCanvas />}
    </div>
  );
}