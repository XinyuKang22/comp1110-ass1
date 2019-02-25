package comp1110.ass1;

import java.util.*;

/**
 * This class represents a game of IQ Stars, which is based directly on the puzzle
 * from SmartGames called "IQ Stars"
 * <p>
 * https://www.smartgames.eu/uk/one-player-games/iq-stars
 * <p>
 * This class provides the core logic of the puzzle, which is used by the GUI
 * to run the game in a window.
 * <p>
 * The board is composed of hexagonal spaces arranged in a grid.
 * Each space is assigned an index which is a letter of the alphabet,
 * using row-major order starting at the top left of the board.
 * Thus the top-left space has an index of 'A';
 * the top-right space has an index of 'G';
 * the bottom-left space has an index of 'U'; and
 * the bottom-right space has an index of 'Z'.
 * <p>
 * There are seven puzzle pieces which are composed of various numbers of linked
 * stars, arranged in a planar manner so that they may be laid on the hex grid.
 * Each piece's position is described in terms of its origin, which is the
 * top-left-most star when in its unrotated state (as illustrated above).
 * <p>
 * The puzzle uses a 'placement string' to represent the state of the board.
 * A placement string is composed of zero or more 'piece placement strings',
 * indicating the location of each piece that has already been placed on the board.
 * <p>
 * A piece placement string consists of three characters:
 * - The first character identifies which of the seven pieces is being placed ('A' to 'G').
 * - The second character identifies the column in which the origin of the piece is placed ('A' to 'Z').
 * - The third character identifies the rotation of the piece ('A' to 'F').
 * <p>
 * The default (unrotated) orientation is 'A'.
 * Orientation 'B' means the piece is rotated 60 degrees clockwise around its origin;
 * Orientation 'C' means the piece is rotated 120 degrees;
 * Orientation 'D' means the piece is rotated 180 degrees;
 * Orientation 'E' means the piece is rotated 240 degrees; and
 * Orientation 'F' means the piece is rotated 300 degrees.
 */
public class IQStars {
    /* a trivial objective that can be used to drive the game */
    public static final String TRIVIAL_OBJECTIVE = "ABACSDETCFWDGOD";
    public static final String TRIVIAL_SOLUTION = "ABABIACSDDGCETCFWDGOD";

    /* empty placement string for unplaced pieces */
    public static final String NOT_PLACED = "";

    /* a set of progressively harder objectives that can be used to drive the game */
    public static final String[][] SAMPLE_OBJECTIVES = {
            /* STARTER */
            {"CREDDCEUFFBCGSE", "AVADHBEAAFGCGEC", "AEABCADMCFBCGIB"},
            /* JUNIOR */
            {"ECAFNAGGC", "ACBDDAGVF", "AKCDBAFGC"},
            /* EXPERT */
            {"BPCFEA", "AUAEKC"},
            /* MASTER */
            {"BVF", "GKC"}
    };


    private String objective;         // the objective of this instance of the game
    private String solution;          // the solution to the current game

    /**
     * Constructor for a game, given a level of difficulty for the new game
     * <p>
     * This should create a new game with a single valid solution and a level of
     * difficulty that corresponds to the argument difficulty.
     *
     * @param difficulty A value between 0.0 (easiest) and 10.0 (hardest) specifying the desired level of difficulty.
     */
    public IQStars(double difficulty) {
        objective = establishInterestingObjective(difficulty);
        if (objective == null)
            objective = establishSimpleObjective(difficulty);
    }


    /**
     * Create a new game with the given objective.
     *
     * @param objective The objective for the new game
     */
    public IQStars(String objective) {
        this.objective = objective;
    }

    /**
     * @param piece the name of the piece, 'A'-'L'
     * @return true if the placement of the given piece is fixed in current puzzle objective;
     * otherwise return false
     */
    public boolean isPieceFixed(char piece) {
        // FIXME Task 2:  Replace the code below with code that correctly checks the objective
        return false;
    }

    /**
     * Determine whether a given piece placement string is valid.
     * A pieces placement string is valid if and only if:
     * the first character is a piece ID 'A'-'G';
     * the second character is a column 'A'-Z';
     * the third character is an orientation 'A'-'F'
     *
     * @return true if the given piece placement string is valid
     */
    public static boolean isValidPiecePlacement(String piecePlacementString) {
        // FIXME Task 3: Replace the code below with code that correctly checks whether the piece placement is valid
        return false;
    }

    /**
     * Set the game's objective using the given difficulty level and the sample
     * objectives provided in SAMPLE_OBJECTIVES.
     * <p>
     * The code should index into the samples according to the difficulty, using the
     * first arrays for difficulty values less than 2.5/10, the next for values
     * less than 5.0/10, the next for values less than 7.5/10, and the last for
     * the remaining values.
     * <p>
     * Note that difficulty is a double in the range 0.0 and 10.0.  It may take on any
     * value in the range 0.0 to 10.0.   Your task is to map those values to the
     * SAMPLE_OBJECTIVES provided.
     * <p>
     * The code should choose within the arrays randomly, so for a given difficulty
     * level, any one of the sample values might be used.
     * <p>
     * For example, if the difficulty level was 1/10, then the first array ('EASY')
     * of values should be used.   A random number generator should then choose
     * an index between 0 and 2 and set the objective accordingly, so if the randomly
     * generated value was 1, then it would choose the objective
     * "AGDDDIAAECCDFAAHGADGHBABJEBBKEAELDAD" and so on.
     *
     * @param difficulty A value between 0.0 (easiest) and 10.0 (hardest) specifying the desired level of difficulty.
     */
    public static String establishSimpleObjective(double difficulty) {
        // FIXME Task 4:  Replace the code below with code that draws from SAMPLE_OBJECTIVES
        return TRIVIAL_OBJECTIVE;
    }

    /**
     * Determine whether a given placement string is valid.
     * A placement string is valid if and only if:
     * it is composed of zero or more three-character piece placement strings,
     * each piece appears only once; and
     * no two pieces overlap.
     *
     * @return true if the given placement string is valid
     */
    public static boolean isValidPlacement(String placementString) {
        HashSet<Piece> pieces = new HashSet<>();
        HashSet<Character> covered = new HashSet<>();
        for (int offset = 0; offset < placementString.length(); offset += 3) {
            String piecePlacementString = placementString.substring(offset, offset + 3);
            if (!isValidPiecePlacement(piecePlacementString)) {
                return false;
            }
            Piece piece = Piece.valueOf(String.valueOf(piecePlacementString.charAt(0)));
            if (!pieces.add(piece)) {
                return false;
            }
            char[] pieceCover = piece.getCovered(piecePlacementString.charAt(1), piecePlacementString.charAt(2));
            for (char space : pieceCover) {
                if (space == Piece.INVALID_SPACE) {
                    return false;
                }
                if (!covered.add(space)) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Take a non-empty string composed of three-character piece placement strings,
     * and if the piece name is 'A' or 'D' and the orientation is any char 'D'-'F',
     * replace the orientation as follows:
     * 'D' -> 'A'
     * 'E' -> 'B'
     * 'F' -> 'C'
     * The orientation char for pieces 'B', 'C', and 'E' through 'G' should not be changed.
     * <p>
     * Examples:
     * <p>
     * in:  "ACDBGACEF"    out: "AAABGACEF"
     * in:  "BDCFGHDQD"    out: "BDCFGHDIA"
     * in:  "HACDREICD"    out: "HACDEBICD"
     * <p>
     * Hint: You may want to convert from String to array of char using toCharArray(), and then
     * do your work using the char array before converting back by creating a new String with
     * the char array as the argument to the constructor.
     *
     * @param in A string composed of three-character piece placement strings
     * @return the input string with corrected orientations for pieces 'A' through 'E'
     */
    public static String fixOrientations(String in) {
        // FIXME Task 5: implement code that correctly returns a canonical string according to the comment above.
        return null;
    }

    /**
     * Find all solutions to this game, and return them as an array of strings, each string
     * describing a placement of the pieces as a sequence of three-character piece placement
     * strings.
     * <p>
     * Invalid piece orientations should be fixed using the fixOrientation method.
     * <p>
     * Invalid solutions should not be returned by this method.
     *
     * @return An array of strings representing the set of all solutions to this puzzle.
     * If there are no solutions, the array should be empty (not null).
     */
    public String[] getSolutions() {
        // FIXME Task 7: replace this code with code that determines all solutions for this puzzle's objective
        return new String[]{TRIVIAL_SOLUTION};
    }

    /**
     * Set the game's objective using the given difficulty level.
     * <p>
     * This method should generate different objectives according to the following:
     * <p>
     * - It should respect the given difficulty, using some *principled* and *documented*
     * approach determining the difficulty of a particular objective.
     * <p>
     * - It should not use TRIVIAL_OBJECTIVE or objectives from SAMPLE_OBJECTIVES.
     * <p>
     * - It should provide a rich number of objectives (much more than SAMPLE_OBJECTIVES),
     * so that the player is not likely to be given the same objective repeatedly.
     * <p>
     * - It should offer a more graduated notion of difficulty levels, more than just the
     * four levels provided by SAMPLE_OBJECTIVES.   The tests expect to see difficulty
     * resolved to at least eight levels.
     * <p>
     * <p>
     * Note that difficulty is given as a double that is greater or equal to 0.0 and less than 10.0.
     * It may take on any value in the range [0,10).
     * <p>
     * This requires a deeper understanding of the problem, and some way of determining
     * what makes a particular objective difficult or easy.
     *
     * @param difficulty A value between 0.0 (easiest) and 10.0 (hardest) specifying the desired level of difficulty.
     */
    public static String establishInterestingObjective(double difficulty) {
        // FIXME Task 8: Replace this code with a good objective generator that does not draw from a simple set of samples
        return null;
    }

    /**
     * @return the objective of the current game.
     */
    public String getObjective() {
        return objective;
    }

    /**
     * Return the solution to the puzzle.  The solution is calculated lazily, so first
     * check whether it's already been calculated.
     *
     * @return A string representing the solution to this habitat.
     */
    public String getSolution() {
        if (solution == null) setSolution();
        return solution;
    }

    /**
     * Establish the solution to this puzzle.
     */
    private void setSolution() {
        String[] solutions = getSolutions();
        if (solutions.length != 1) {
            throw new IllegalArgumentException("IQStars " + objective + " " + (solutions.length == 0 ? " has no " : " has more than one ") + "solution");
        } else
            solution = solutions[0];
    }
}
