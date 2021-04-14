import React from 'react';
import { useSelector } from 'react-redux';
import { StartGameButton } from './StartGameButton';
import { selectReadyToStart } from './gameSlice';
import { PlayerList } from './PlayerList';
import { makeStyles } from '@material-ui/core/styles';
import Typography from '@material-ui/core/Typography';
import { Settings } from './Settings';

const useStyles = makeStyles({
  root: {
    '& > *': {
      marginTop: '1rem',
    }
  }
});

export function WaitingRoom() {
  const styles = useStyles();
  const readyToStart = useSelector(selectReadyToStart);

  return (
    <div className={styles.root}>
      <Typography variant='h2'>
        Waiting Room
      </Typography>
      <Settings />
      <PlayerList />
      {readyToStart && <StartGameButton />}
    </div>
  );
}
