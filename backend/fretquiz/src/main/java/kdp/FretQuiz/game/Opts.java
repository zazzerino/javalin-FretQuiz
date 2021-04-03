package kdp.FretQuiz.game;

import com.fasterxml.jackson.annotation.JsonIgnore;
import kdp.FretQuiz.theory.Fretboard;

/**
 * The game options. Can be changed by the client before each Round.
 */
public record Opts(@JsonIgnore
                   Fretboard fretboard,
                   int roundCount) {

    public static int DEFAULT_ROUND_COUNT = 1;

    public static final Opts DEFAULT = new Opts(Fretboard.DEFAULT, DEFAULT_ROUND_COUNT);
}
