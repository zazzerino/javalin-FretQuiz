import * as React from 'react';
import { FretboardDiagram } from 'fretboard-diagram';
import { useSelector } from 'react-redux';
import { selectUserId } from '../../user/userSlice';
import { selectGameId } from '../gameSlice';
import { FretCoord } from '../types';

const correctColor = 'lime';
const incorrectColor = 'deeppink';

export function Fretboard() {
  const userId = useSelector(selectUserId);
  const gameId = useSelector(selectGameId);

  React.useEffect(() => {

    // selects the element with the given `id` and draws a fretboard diagram there
    new FretboardDiagram({
      id: 'fretboard-canvas',
      drawDotOnHover: true,
      onClick: (coord: FretCoord) => {
        console.log('clicked ' + JSON.stringify(coord));
      }
    });
  });

  return (
    <div className="Fretboard">
      <div id="fretboard-canvas" />
    </div>
  )
}
