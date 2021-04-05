package kdp.FretQuiz.game;

import com.fasterxml.jackson.annotation.JsonProperty;
import kdp.FretQuiz.Util;
import kdp.FretQuiz.theory.Accidental;
import kdp.FretQuiz.theory.Fretboard;
import kdp.FretQuiz.theory.Note;

import java.util.ArrayList;
import java.util.List;

/**
 * The game options. Can be changed by the client before each game.
 */
public class Opts {

    private int roundCount;
    private Fretboard fretboard;
    private List<Integer> strings;
    private List<Accidental> accidentals;

    public static int DEFAULT_ROUND_COUNT = 4;

    public static List<Integer> DEFAULT_STRINGS =
            Util.range(1, Fretboard.DEFAULT.stringCount() + 1);

    public static List<Accidental> DEFAULT_ACCIDENTALS =
            List.of(Accidental.FLAT, Accidental.NONE, Accidental.SHARP);

    public Opts() {
        this.roundCount = DEFAULT_ROUND_COUNT;
        this.fretboard = Fretboard.DEFAULT;
        this.strings = DEFAULT_STRINGS;
        this.accidentals = DEFAULT_ACCIDENTALS;
    }

    public Note randomNote() {
        final var notes = new ArrayList<Note>();

        for (final var string : strings) {
            final var notesOnString = fretboard.notesOnString(string);
            notes.addAll(notesOnString);
        }

        return Util.randomElement(notes);
    }

    public Opts setRoundCount(int roundCount) {
        this.roundCount = roundCount;
        return this;
    }

    public Opts toggleString(int string) {
        strings = Util.toggle(this.strings, string);
        return this;
    }

    public Opts toggleAccidental(Accidental accidental) {
        accidentals = Util.toggle(this.accidentals, accidental);
        return this;
    }

    @JsonProperty("roundCount")
    public int roundCount() {
        return roundCount;
    }

    @JsonProperty("fretboard")
    public Fretboard fretboard() {
        return fretboard;
    }

    @JsonProperty("strings")
    public List<Integer> strings() {
        return strings;
    }

    @JsonProperty("accidentals")
    public List<Accidental> accidentals() {
        return accidentals;
    }
}
