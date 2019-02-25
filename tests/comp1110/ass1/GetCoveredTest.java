package comp1110.ass1;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;

import java.util.Arrays;

import static org.junit.Assert.assertTrue;

public class GetCoveredTest {
    @Rule
    public Timeout globalTimeout = Timeout.seconds(30);

    private String charArrayToString(char[] in) {
        String out = "{";
        for (int i = 0; i < in.length; i++) {
            out += " " + in[i];
            if (i != in.length - 1)
                out += ",";
        }
        out += " }";
        return out;
    }

    private void checkIndices(Piece m, char hex, char orientation, char[] reference) {
        char[] actual = m.getCovered(hex, orientation);
        Arrays.sort(actual);
        Arrays.sort(reference);
        String r = charArrayToString(reference);
        String a = charArrayToString(actual);
        assertTrue("Incorrect indices. Expected " + r + ", but got " + a, a.equals(r));
    }

    @Test
    public void trivialPlacementA() {
        checkIndices(Piece.A, 'A', 'A', new char[]{'A', 'B', 'C'});
    }

    @Test
    public void trivialPlacementB() {
        checkIndices(Piece.B, 'P', 'A', new char[]{'P', 'Q', 'X'});
    }

    /**
     * Given the piece placement string 'AAB' would return the indices: {'A', 'H', 'O'}.
     * Given the piece placement string 'CPD' would return the indices: {'B', 'H', 'O', 'P'}.
     * Given the piece placement string 'DAB' would return the indices: {'!', 'A', 'H', 'N'}.
     */
    @Test
    public void testJavadocExamples() {
        checkIndices(Piece.A, 'A', 'B', new char[]{'A', 'H', 'O'});
        checkIndices(Piece.C, 'P', 'D', new char[]{'B', 'H', 'O', 'P'});
        checkIndices(Piece.D, 'A', 'B', new char[]{'!', 'A', 'H', 'N'});
    }

    @Test
    public void unrotatedInDifferentHexes() {
        for (char p = 'A'; p <= 'G'; p++) {
            Piece piece = Piece.valueOf(String.valueOf(p));
            for (char h = 'A'; h <= 'Z'; h++) {
                checkIndices(piece, h, 'A', indices[(p - 'A') * 26 * 6 + (h - 'A') * 6]);
            }
        }
    }

    @Test
    public void placeInDifferentOrientations() {
        for (char p = 'A'; p <= 'G'; p++) {
            Piece piece = Piece.valueOf(String.valueOf(p));
            for (char o = 'A'; o <= 'F'; o++) {
                checkIndices(piece, 'J', o, indices[(p - 'A') * 26 * 6 + 9 * 6 + (o - 'A')]);
            }
        }
    }

    @Test
    public void exhaustiveTest() {
        for (char p = 'A'; p <= 'G'; p++) {
            Piece piece = Piece.valueOf(String.valueOf(p));
            for (char h = 'A'; h <= 'Z'; h++) {
                for (char o = 'A'; o <= 'F'; o++) {
                    checkIndices(piece, h, o, indices[(p - 'A') * 26 * 6 + (h - 'A') * 6 + (o - 'A')]);
                }
            }
        }
    }

    private char[][] indices = new char[][]{new char[]{'A', 'B', 'C'}, new char[]{'A', 'H', 'O'}, new char[]{'A', '!', '!'}, new char[]{'A', '!', '!'}, new char[]{'A', '!', '!'}, new char[]{'A', '!', '!'}, new char[]{'B', 'C', 'D'}, new char[]{'B', 'I', 'P'}, new char[]{'B', 'H', 'N'}, new char[]{'B', 'A', '!'}, new char[]{'B', '!', '!'}, new char[]{'B', '!', '!'}, new char[]{'C', 'D', 'E'}, new char[]{'C', 'J', 'Q'}, new char[]{'C', 'I', 'O'}, new char[]{'C', 'B', 'A'}, new char[]{'C', '!', '!'}, new char[]{'C', '!', '!'}, new char[]{'D', 'E', 'F'}, new char[]{'D', 'K', 'R'}, new char[]{'D', 'J', 'P'}, new char[]{'D', 'C', 'B'}, new char[]{'D', '!', '!'}, new char[]{'D', '!', '!'}, new char[]{'E', 'F', 'G'}, new char[]{'E', 'L', 'S'}, new char[]{'E', 'K', 'Q'}, new char[]{'E', 'D', 'C'}, new char[]{'E', '!', '!'}, new char[]{'E', '!', '!'}, new char[]{'F', 'G', '!'}, new char[]{'F', 'M', 'T'}, new char[]{'F', 'L', 'R'}, new char[]{'F', 'E', 'D'}, new char[]{'F', '!', '!'}, new char[]{'F', '!', '!'}, new char[]{'G', '!', '!'}, new char[]{'G', '!', '!'}, new char[]{'G', 'M', 'S'}, new char[]{'G', 'F', 'E'}, new char[]{'G', '!', '!'}, new char[]{'G', '!', '!'}, new char[]{'H', 'I', 'J'}, new char[]{'H', 'O', 'V'}, new char[]{'H', 'N', '!'}, new char[]{'H', '!', '!'}, new char[]{'H', 'A', '!'}, new char[]{'H', 'B', '!'}, new char[]{'I', 'J', 'K'}, new char[]{'I', 'P', 'W'}, new char[]{'I', 'O', 'U'}, new char[]{'I', 'H', '!'}, new char[]{'I', 'B', '!'}, new char[]{'I', 'C', '!'}, new char[]{'J', 'K', 'L'}, new char[]{'J', 'Q', 'X'}, new char[]{'J', 'P', 'V'}, new char[]{'J', 'I', 'H'}, new char[]{'J', 'C', '!'}, new char[]{'J', 'D', '!'}, new char[]{'K', 'L', 'M'}, new char[]{'K', 'R', 'Y'}, new char[]{'K', 'Q', 'W'}, new char[]{'K', 'J', 'I'}, new char[]{'K', 'D', '!'}, new char[]{'K', 'E', '!'}, new char[]{'L', 'M', '!'}, new char[]{'L', 'S', 'Z'}, new char[]{'L', 'R', 'X'}, new char[]{'L', 'K', 'J'}, new char[]{'L', 'E', '!'}, new char[]{'L', 'F', '!'}, new char[]{'M', '!', '!'}, new char[]{'M', 'T', '!'}, new char[]{'M', 'S', 'Y'}, new char[]{'M', 'L', 'K'}, new char[]{'M', 'F', '!'}, new char[]{'M', 'G', '!'}, new char[]{'N', 'O', 'P'}, new char[]{'N', 'U', '!'}, new char[]{'N', '!', '!'}, new char[]{'N', '!', '!'}, new char[]{'N', '!', '!'}, new char[]{'N', 'H', 'B'}, new char[]{'O', 'P', 'Q'}, new char[]{'O', 'V', '!'}, new char[]{'O', 'U', '!'}, new char[]{'O', 'N', '!'}, new char[]{'O', 'H', 'A'}, new char[]{'O', 'I', 'C'}, new char[]{'P', 'Q', 'R'}, new char[]{'P', 'W', '!'}, new char[]{'P', 'V', '!'}, new char[]{'P', 'O', 'N'}, new char[]{'P', 'I', 'B'}, new char[]{'P', 'J', 'D'}, new char[]{'Q', 'R', 'S'}, new char[]{'Q', 'X', '!'}, new char[]{'Q', 'W', '!'}, new char[]{'Q', 'P', 'O'}, new char[]{'Q', 'J', 'C'}, new char[]{'Q', 'K', 'E'}, new char[]{'R', 'S', 'T'}, new char[]{'R', 'Y', '!'}, new char[]{'R', 'X', '!'}, new char[]{'R', 'Q', 'P'}, new char[]{'R', 'K', 'D'}, new char[]{'R', 'L', 'F'}, new char[]{'S', 'T', '!'}, new char[]{'S', 'Z', '!'}, new char[]{'S', 'Y', '!'}, new char[]{'S', 'R', 'Q'}, new char[]{'S', 'L', 'E'}, new char[]{'S', 'M', 'G'}, new char[]{'T', '!', '!'}, new char[]{'T', '!', '!'}, new char[]{'T', 'Z', '!'}, new char[]{'T', 'S', 'R'}, new char[]{'T', 'M', 'F'}, new char[]{'T', '!', '!'}, new char[]{'U', 'V', 'W'}, new char[]{'U', '!', '!'}, new char[]{'U', '!', '!'}, new char[]{'U', '!', '!'}, new char[]{'U', 'N', '!'}, new char[]{'U', 'O', 'I'}, new char[]{'V', 'W', 'X'}, new char[]{'V', '!', '!'}, new char[]{'V', '!', '!'}, new char[]{'V', 'U', '!'}, new char[]{'V', 'O', 'H'}, new char[]{'V', 'P', 'J'}, new char[]{'W', 'X', 'Y'}, new char[]{'W', '!', '!'}, new char[]{'W', '!', '!'}, new char[]{'W', 'V', 'U'}, new char[]{'W', 'P', 'I'}, new char[]{'W', 'Q', 'K'}, new char[]{'X', 'Y', 'Z'}, new char[]{'X', '!', '!'}, new char[]{'X', '!', '!'}, new char[]{'X', 'W', 'V'}, new char[]{'X', 'Q', 'J'}, new char[]{'X', 'R', 'L'}, new char[]{'Y', 'Z', '!'}, new char[]{'Y', '!', '!'}, new char[]{'Y', '!', '!'}, new char[]{'Y', 'X', 'W'}, new char[]{'Y', 'R', 'K'}, new char[]{'Y', 'S', 'M'}, new char[]{'Z', '!', '!'}, new char[]{'Z', '!', '!'}, new char[]{'Z', '!', '!'}, new char[]{'Z', 'Y', 'X'}, new char[]{'Z', 'S', 'L'}, new char[]{'Z', 'T', '!'}, new char[]{'A', 'B', 'I'}, new char[]{'A', 'H', 'N'}, new char[]{'A', '!', '!'}, new char[]{'A', '!', '!'}, new char[]{'A', '!', '!'}, new char[]{'A', '!', '!'}, new char[]{'B', 'C', 'J'}, new char[]{'B', 'I', 'O'}, new char[]{'B', 'H', '!'}, new char[]{'B', 'A', '!'}, new char[]{'B', '!', '!'}, new char[]{'B', '!', '!'}, new char[]{'C', 'D', 'K'}, new char[]{'C', 'J', 'P'}, new char[]{'C', 'I', 'H'}, new char[]{'C', 'B', '!'}, new char[]{'C', '!', '!'}, new char[]{'C', '!', '!'}, new char[]{'D', 'E', 'L'}, new char[]{'D', 'K', 'Q'}, new char[]{'D', 'J', 'I'}, new char[]{'D', 'C', '!'}, new char[]{'D', '!', '!'}, new char[]{'D', '!', '!'}, new char[]{'E', 'F', 'M'}, new char[]{'E', 'L', 'R'}, new char[]{'E', 'K', 'J'}, new char[]{'E', 'D', '!'}, new char[]{'E', '!', '!'}, new char[]{'E', '!', '!'}, new char[]{'F', 'G', '!'}, new char[]{'F', 'M', 'S'}, new char[]{'F', 'L', 'K'}, new char[]{'F', 'E', '!'}, new char[]{'F', '!', '!'}, new char[]{'F', '!', '!'}, new char[]{'G', '!', '!'}, new char[]{'G', '!', 'T'}, new char[]{'G', 'M', 'L'}, new char[]{'G', 'F', '!'}, new char[]{'G', '!', '!'}, new char[]{'G', '!', '!'}, new char[]{'H', 'I', 'P'}, new char[]{'H', 'O', 'U'}, new char[]{'H', 'N', '!'}, new char[]{'H', '!', '!'}, new char[]{'H', 'A', '!'}, new char[]{'H', 'B', 'C'}, new char[]{'I', 'J', 'Q'}, new char[]{'I', 'P', 'V'}, new char[]{'I', 'O', 'N'}, new char[]{'I', 'H', 'A'}, new char[]{'I', 'B', '!'}, new char[]{'I', 'C', 'D'}, new char[]{'J', 'K', 'R'}, new char[]{'J', 'Q', 'W'}, new char[]{'J', 'P', 'O'}, new char[]{'J', 'I', 'B'}, new char[]{'J', 'C', '!'}, new char[]{'J', 'D', 'E'}, new char[]{'K', 'L', 'S'}, new char[]{'K', 'R', 'X'}, new char[]{'K', 'Q', 'P'}, new char[]{'K', 'J', 'C'}, new char[]{'K', 'D', '!'}, new char[]{'K', 'E', 'F'}, new char[]{'L', 'M', 'T'}, new char[]{'L', 'S', 'Y'}, new char[]{'L', 'R', 'Q'}, new char[]{'L', 'K', 'D'}, new char[]{'L', 'E', '!'}, new char[]{'L', 'F', 'G'}, new char[]{'M', '!', '!'}, new char[]{'M', 'T', 'Z'}, new char[]{'M', 'S', 'R'}, new char[]{'M', 'L', 'E'}, new char[]{'M', 'F', '!'}, new char[]{'M', 'G', '!'}, new char[]{'N', 'O', 'V'}, new char[]{'N', 'U', '!'}, new char[]{'N', '!', '!'}, new char[]{'N', '!', '!'}, new char[]{'N', '!', 'A'}, new char[]{'N', 'H', 'I'}, new char[]{'O', 'P', 'W'}, new char[]{'O', 'V', '!'}, new char[]{'O', 'U', '!'}, new char[]{'O', 'N', '!'}, new char[]{'O', 'H', 'B'}, new char[]{'O', 'I', 'J'}, new char[]{'P', 'Q', 'X'}, new char[]{'P', 'W', '!'}, new char[]{'P', 'V', 'U'}, new char[]{'P', 'O', 'H'}, new char[]{'P', 'I', 'C'}, new char[]{'P', 'J', 'K'}, new char[]{'Q', 'R', 'Y'}, new char[]{'Q', 'X', '!'}, new char[]{'Q', 'W', 'V'}, new char[]{'Q', 'P', 'I'}, new char[]{'Q', 'J', 'D'}, new char[]{'Q', 'K', 'L'}, new char[]{'R', 'S', 'Z'}, new char[]{'R', 'Y', '!'}, new char[]{'R', 'X', 'W'}, new char[]{'R', 'Q', 'J'}, new char[]{'R', 'K', 'E'}, new char[]{'R', 'L', 'M'}, new char[]{'S', 'T', '!'}, new char[]{'S', 'Z', '!'}, new char[]{'S', 'Y', 'X'}, new char[]{'S', 'R', 'K'}, new char[]{'S', 'L', 'F'}, new char[]{'S', 'M', '!'}, new char[]{'T', '!', '!'}, new char[]{'T', '!', '!'}, new char[]{'T', 'Z', 'Y'}, new char[]{'T', 'S', 'L'}, new char[]{'T', 'M', 'G'}, new char[]{'T', '!', '!'}, new char[]{'U', 'V', '!'}, new char[]{'U', '!', '!'}, new char[]{'U', '!', '!'}, new char[]{'U', '!', '!'}, new char[]{'U', 'N', 'H'}, new char[]{'U', 'O', 'P'}, new char[]{'V', 'W', '!'}, new char[]{'V', '!', '!'}, new char[]{'V', '!', '!'}, new char[]{'V', 'U', 'N'}, new char[]{'V', 'O', 'I'}, new char[]{'V', 'P', 'Q'}, new char[]{'W', 'X', '!'}, new char[]{'W', '!', '!'}, new char[]{'W', '!', '!'}, new char[]{'W', 'V', 'O'}, new char[]{'W', 'P', 'J'}, new char[]{'W', 'Q', 'R'}, new char[]{'X', 'Y', '!'}, new char[]{'X', '!', '!'}, new char[]{'X', '!', '!'}, new char[]{'X', 'W', 'P'}, new char[]{'X', 'Q', 'K'}, new char[]{'X', 'R', 'S'}, new char[]{'Y', 'Z', '!'}, new char[]{'Y', '!', '!'}, new char[]{'Y', '!', '!'}, new char[]{'Y', 'X', 'Q'}, new char[]{'Y', 'R', 'L'}, new char[]{'Y', 'S', 'T'}, new char[]{'Z', '!', '!'}, new char[]{'Z', '!', '!'}, new char[]{'Z', '!', '!'}, new char[]{'Z', 'Y', 'R'}, new char[]{'Z', 'S', 'M'}, new char[]{'Z', 'T', '!'}, new char[]{'A', 'B', 'I', 'O'}, new char[]{'A', 'H', 'N', '!'}, new char[]{'A', '!', '!', '!'}, new char[]{'A', '!', '!', '!'}, new char[]{'A', '!', '!', '!'}, new char[]{'A', '!', '!', 'C'}, new char[]{'B', 'C', 'J', 'P'}, new char[]{'B', 'I', 'O', 'N'}, new char[]{'B', 'H', '!', '!'}, new char[]{'B', 'A', '!', '!'}, new char[]{'B', '!', '!', '!'}, new char[]{'B', '!', '!', 'D'}, new char[]{'C', 'D', 'K', 'Q'}, new char[]{'C', 'J', 'P', 'O'}, new char[]{'C', 'I', 'H', 'A'}, new char[]{'C', 'B', '!', '!'}, new char[]{'C', '!', '!', '!'}, new char[]{'C', '!', '!', 'E'}, new char[]{'D', 'E', 'L', 'R'}, new char[]{'D', 'K', 'Q', 'P'}, new char[]{'D', 'J', 'I', 'B'}, new char[]{'D', 'C', '!', '!'}, new char[]{'D', '!', '!', '!'}, new char[]{'D', '!', '!', 'F'}, new char[]{'E', 'F', 'M', 'S'}, new char[]{'E', 'L', 'R', 'Q'}, new char[]{'E', 'K', 'J', 'C'}, new char[]{'E', 'D', '!', '!'}, new char[]{'E', '!', '!', '!'}, new char[]{'E', '!', '!', 'G'}, new char[]{'F', 'G', '!', 'T'}, new char[]{'F', 'M', 'S', 'R'}, new char[]{'F', 'L', 'K', 'D'}, new char[]{'F', 'E', '!', '!'}, new char[]{'F', '!', '!', '!'}, new char[]{'F', '!', '!', '!'}, new char[]{'G', '!', '!', '!'}, new char[]{'G', '!', 'T', 'S'}, new char[]{'G', 'M', 'L', 'E'}, new char[]{'G', 'F', '!', '!'}, new char[]{'G', '!', '!', '!'}, new char[]{'G', '!', '!', '!'}, new char[]{'H', 'I', 'P', 'V'}, new char[]{'H', 'O', 'U', '!'}, new char[]{'H', 'N', '!', '!'}, new char[]{'H', '!', '!', '!'}, new char[]{'H', 'A', '!', '!'}, new char[]{'H', 'B', 'C', 'J'}, new char[]{'I', 'J', 'Q', 'W'}, new char[]{'I', 'P', 'V', 'U'}, new char[]{'I', 'O', 'N', '!'}, new char[]{'I', 'H', 'A', '!'}, new char[]{'I', 'B', '!', '!'}, new char[]{'I', 'C', 'D', 'K'}, new char[]{'J', 'K', 'R', 'X'}, new char[]{'J', 'Q', 'W', 'V'}, new char[]{'J', 'P', 'O', 'H'}, new char[]{'J', 'I', 'B', '!'}, new char[]{'J', 'C', '!', '!'}, new char[]{'J', 'D', 'E', 'L'}, new char[]{'K', 'L', 'S', 'Y'}, new char[]{'K', 'R', 'X', 'W'}, new char[]{'K', 'Q', 'P', 'I'}, new char[]{'K', 'J', 'C', '!'}, new char[]{'K', 'D', '!', '!'}, new char[]{'K', 'E', 'F', 'M'}, new char[]{'L', 'M', 'T', 'Z'}, new char[]{'L', 'S', 'Y', 'X'}, new char[]{'L', 'R', 'Q', 'J'}, new char[]{'L', 'K', 'D', '!'}, new char[]{'L', 'E', '!', '!'}, new char[]{'L', 'F', 'G', '!'}, new char[]{'M', '!', '!', '!'}, new char[]{'M', 'T', 'Z', 'Y'}, new char[]{'M', 'S', 'R', 'K'}, new char[]{'M', 'L', 'E', '!'}, new char[]{'M', 'F', '!', '!'}, new char[]{'M', 'G', '!', '!'}, new char[]{'N', 'O', 'V', '!'}, new char[]{'N', 'U', '!', '!'}, new char[]{'N', '!', '!', '!'}, new char[]{'N', '!', '!', '!'}, new char[]{'N', '!', 'A', 'B'}, new char[]{'N', 'H', 'I', 'P'}, new char[]{'O', 'P', 'W', '!'}, new char[]{'O', 'V', '!', '!'}, new char[]{'O', 'U', '!', '!'}, new char[]{'O', 'N', '!', 'A'}, new char[]{'O', 'H', 'B', 'C'}, new char[]{'O', 'I', 'J', 'Q'}, new char[]{'P', 'Q', 'X', '!'}, new char[]{'P', 'W', '!', '!'}, new char[]{'P', 'V', 'U', 'N'}, new char[]{'P', 'O', 'H', 'B'}, new char[]{'P', 'I', 'C', 'D'}, new char[]{'P', 'J', 'K', 'R'}, new char[]{'Q', 'R', 'Y', '!'}, new char[]{'Q', 'X', '!', '!'}, new char[]{'Q', 'W', 'V', 'O'}, new char[]{'Q', 'P', 'I', 'C'}, new char[]{'Q', 'J', 'D', 'E'}, new char[]{'Q', 'K', 'L', 'S'}, new char[]{'R', 'S', 'Z', '!'}, new char[]{'R', 'Y', '!', '!'}, new char[]{'R', 'X', 'W', 'P'}, new char[]{'R', 'Q', 'J', 'D'}, new char[]{'R', 'K', 'E', 'F'}, new char[]{'R', 'L', 'M', 'T'}, new char[]{'S', 'T', '!', '!'}, new char[]{'S', 'Z', '!', '!'}, new char[]{'S', 'Y', 'X', 'Q'}, new char[]{'S', 'R', 'K', 'E'}, new char[]{'S', 'L', 'F', 'G'}, new char[]{'S', 'M', '!', '!'}, new char[]{'T', '!', '!', '!'}, new char[]{'T', '!', '!', '!'}, new char[]{'T', 'Z', 'Y', 'R'}, new char[]{'T', 'S', 'L', 'F'}, new char[]{'T', 'M', 'G', '!'}, new char[]{'T', '!', '!', '!'}, new char[]{'U', 'V', '!', '!'}, new char[]{'U', '!', '!', '!'}, new char[]{'U', '!', '!', '!'}, new char[]{'U', '!', '!', '!'}, new char[]{'U', 'N', 'H', 'I'}, new char[]{'U', 'O', 'P', 'W'}, new char[]{'V', 'W', '!', '!'}, new char[]{'V', '!', '!', '!'}, new char[]{'V', '!', '!', '!'}, new char[]{'V', 'U', 'N', 'H'}, new char[]{'V', 'O', 'I', 'J'}, new char[]{'V', 'P', 'Q', 'X'}, new char[]{'W', 'X', '!', '!'}, new char[]{'W', '!', '!', '!'}, new char[]{'W', '!', '!', 'U'}, new char[]{'W', 'V', 'O', 'I'}, new char[]{'W', 'P', 'J', 'K'}, new char[]{'W', 'Q', 'R', 'Y'}, new char[]{'X', 'Y', '!', '!'}, new char[]{'X', '!', '!', '!'}, new char[]{'X', '!', '!', 'V'}, new char[]{'X', 'W', 'P', 'J'}, new char[]{'X', 'Q', 'K', 'L'}, new char[]{'X', 'R', 'S', 'Z'}, new char[]{'Y', 'Z', '!', '!'}, new char[]{'Y', '!', '!', '!'}, new char[]{'Y', '!', '!', 'W'}, new char[]{'Y', 'X', 'Q', 'K'}, new char[]{'Y', 'R', 'L', 'M'}, new char[]{'Y', 'S', 'T', '!'}, new char[]{'Z', '!', '!', '!'}, new char[]{'Z', '!', '!', '!'}, new char[]{'Z', '!', '!', 'X'}, new char[]{'Z', 'Y', 'R', 'L'}, new char[]{'Z', 'S', 'M', '!'}, new char[]{'Z', 'T', '!', '!'}, new char[]{'A', 'B', 'H', 'I'}, new char[]{'A', 'H', '!', 'N'}, new char[]{'A', '!', '!', '!'}, new char[]{'A', '!', '!', '!'}, new char[]{'A', '!', '!', '!'}, new char[]{'A', '!', 'B', '!'}, new char[]{'B', 'C', 'I', 'J'}, new char[]{'B', 'I', 'H', 'O'}, new char[]{'B', 'H', 'A', '!'}, new char[]{'B', 'A', '!', '!'}, new char[]{'B', '!', '!', '!'}, new char[]{'B', '!', 'C', '!'}, new char[]{'C', 'D', 'J', 'K'}, new char[]{'C', 'J', 'I', 'P'}, new char[]{'C', 'I', 'B', 'H'}, new char[]{'C', 'B', '!', '!'}, new char[]{'C', '!', '!', '!'}, new char[]{'C', '!', 'D', '!'}, new char[]{'D', 'E', 'K', 'L'}, new char[]{'D', 'K', 'J', 'Q'}, new char[]{'D', 'J', 'C', 'I'}, new char[]{'D', 'C', '!', '!'}, new char[]{'D', '!', '!', '!'}, new char[]{'D', '!', 'E', '!'}, new char[]{'E', 'F', 'L', 'M'}, new char[]{'E', 'L', 'K', 'R'}, new char[]{'E', 'K', 'D', 'J'}, new char[]{'E', 'D', '!', '!'}, new char[]{'E', '!', '!', '!'}, new char[]{'E', '!', 'F', '!'}, new char[]{'F', 'G', 'M', '!'}, new char[]{'F', 'M', 'L', 'S'}, new char[]{'F', 'L', 'E', 'K'}, new char[]{'F', 'E', '!', '!'}, new char[]{'F', '!', '!', '!'}, new char[]{'F', '!', 'G', '!'}, new char[]{'G', '!', '!', '!'}, new char[]{'G', '!', 'M', 'T'}, new char[]{'G', 'M', 'F', 'L'}, new char[]{'G', 'F', '!', '!'}, new char[]{'G', '!', '!', '!'}, new char[]{'G', '!', '!', '!'}, new char[]{'H', 'I', 'O', 'P'}, new char[]{'H', 'O', 'N', 'U'}, new char[]{'H', 'N', '!', '!'}, new char[]{'H', '!', 'A', '!'}, new char[]{'H', 'A', 'B', '!'}, new char[]{'H', 'B', 'I', 'C'}, new char[]{'I', 'J', 'P', 'Q'}, new char[]{'I', 'P', 'O', 'V'}, new char[]{'I', 'O', 'H', 'N'}, new char[]{'I', 'H', 'B', 'A'}, new char[]{'I', 'B', 'C', '!'}, new char[]{'I', 'C', 'J', 'D'}, new char[]{'J', 'K', 'Q', 'R'}, new char[]{'J', 'Q', 'P', 'W'}, new char[]{'J', 'P', 'I', 'O'}, new char[]{'J', 'I', 'C', 'B'}, new char[]{'J', 'C', 'D', '!'}, new char[]{'J', 'D', 'K', 'E'}, new char[]{'K', 'L', 'R', 'S'}, new char[]{'K', 'R', 'Q', 'X'}, new char[]{'K', 'Q', 'J', 'P'}, new char[]{'K', 'J', 'D', 'C'}, new char[]{'K', 'D', 'E', '!'}, new char[]{'K', 'E', 'L', 'F'}, new char[]{'L', 'M', 'S', 'T'}, new char[]{'L', 'S', 'R', 'Y'}, new char[]{'L', 'R', 'K', 'Q'}, new char[]{'L', 'K', 'E', 'D'}, new char[]{'L', 'E', 'F', '!'}, new char[]{'L', 'F', 'M', 'G'}, new char[]{'M', '!', 'T', '!'}, new char[]{'M', 'T', 'S', 'Z'}, new char[]{'M', 'S', 'L', 'R'}, new char[]{'M', 'L', 'F', 'E'}, new char[]{'M', 'F', 'G', '!'}, new char[]{'M', 'G', '!', '!'}, new char[]{'N', 'O', 'U', 'V'}, new char[]{'N', 'U', '!', '!'}, new char[]{'N', '!', '!', '!'}, new char[]{'N', '!', '!', '!'}, new char[]{'N', '!', 'H', 'A'}, new char[]{'N', 'H', 'O', 'I'}, new char[]{'O', 'P', 'V', 'W'}, new char[]{'O', 'V', 'U', '!'}, new char[]{'O', 'U', 'N', '!'}, new char[]{'O', 'N', 'H', '!'}, new char[]{'O', 'H', 'I', 'B'}, new char[]{'O', 'I', 'P', 'J'}, new char[]{'P', 'Q', 'W', 'X'}, new char[]{'P', 'W', 'V', '!'}, new char[]{'P', 'V', 'O', 'U'}, new char[]{'P', 'O', 'I', 'H'}, new char[]{'P', 'I', 'J', 'C'}, new char[]{'P', 'J', 'Q', 'K'}, new char[]{'Q', 'R', 'X', 'Y'}, new char[]{'Q', 'X', 'W', '!'}, new char[]{'Q', 'W', 'P', 'V'}, new char[]{'Q', 'P', 'J', 'I'}, new char[]{'Q', 'J', 'K', 'D'}, new char[]{'Q', 'K', 'R', 'L'}, new char[]{'R', 'S', 'Y', 'Z'}, new char[]{'R', 'Y', 'X', '!'}, new char[]{'R', 'X', 'Q', 'W'}, new char[]{'R', 'Q', 'K', 'J'}, new char[]{'R', 'K', 'L', 'E'}, new char[]{'R', 'L', 'S', 'M'}, new char[]{'S', 'T', 'Z', '!'}, new char[]{'S', 'Z', 'Y', '!'}, new char[]{'S', 'Y', 'R', 'X'}, new char[]{'S', 'R', 'L', 'K'}, new char[]{'S', 'L', 'M', 'F'}, new char[]{'S', 'M', 'T', '!'}, new char[]{'T', '!', '!', '!'}, new char[]{'T', '!', 'Z', '!'}, new char[]{'T', 'Z', 'S', 'Y'}, new char[]{'T', 'S', 'M', 'L'}, new char[]{'T', 'M', '!', 'G'}, new char[]{'T', '!', '!', '!'}, new char[]{'U', 'V', '!', '!'}, new char[]{'U', '!', '!', '!'}, new char[]{'U', '!', '!', '!'}, new char[]{'U', '!', 'N', '!'}, new char[]{'U', 'N', 'O', 'H'}, new char[]{'U', 'O', 'V', 'P'}, new char[]{'V', 'W', '!', '!'}, new char[]{'V', '!', '!', '!'}, new char[]{'V', '!', 'U', '!'}, new char[]{'V', 'U', 'O', 'N'}, new char[]{'V', 'O', 'P', 'I'}, new char[]{'V', 'P', 'W', 'Q'}, new char[]{'W', 'X', '!', '!'}, new char[]{'W', '!', '!', '!'}, new char[]{'W', '!', 'V', '!'}, new char[]{'W', 'V', 'P', 'O'}, new char[]{'W', 'P', 'Q', 'J'}, new char[]{'W', 'Q', 'X', 'R'}, new char[]{'X', 'Y', '!', '!'}, new char[]{'X', '!', '!', '!'}, new char[]{'X', '!', 'W', '!'}, new char[]{'X', 'W', 'Q', 'P'}, new char[]{'X', 'Q', 'R', 'K'}, new char[]{'X', 'R', 'Y', 'S'}, new char[]{'Y', 'Z', '!', '!'}, new char[]{'Y', '!', '!', '!'}, new char[]{'Y', '!', 'X', '!'}, new char[]{'Y', 'X', 'R', 'Q'}, new char[]{'Y', 'R', 'S', 'L'}, new char[]{'Y', 'S', 'Z', 'T'}, new char[]{'Z', '!', '!', '!'}, new char[]{'Z', '!', '!', '!'}, new char[]{'Z', '!', 'Y', '!'}, new char[]{'Z', 'Y', 'S', 'R'}, new char[]{'Z', 'S', 'T', 'M'}, new char[]{'Z', 'T', '!', '!'}, new char[]{'A', 'B', 'I', 'P'}, new char[]{'A', 'H', 'N', '!'}, new char[]{'A', '!', '!', '!'}, new char[]{'A', '!', '!', '!'}, new char[]{'A', '!', '!', '!'}, new char[]{'A', '!', '!', '!'}, new char[]{'B', 'C', 'J', 'Q'}, new char[]{'B', 'I', 'O', 'U'}, new char[]{'B', 'H', '!', '!'}, new char[]{'B', 'A', '!', '!'}, new char[]{'B', '!', '!', '!'}, new char[]{'B', '!', '!', '!'}, new char[]{'C', 'D', 'K', 'R'}, new char[]{'C', 'J', 'P', 'V'}, new char[]{'C', 'I', 'H', '!'}, new char[]{'C', 'B', '!', '!'}, new char[]{'C', '!', '!', '!'}, new char[]{'C', '!', '!', '!'}, new char[]{'D', 'E', 'L', 'S'}, new char[]{'D', 'K', 'Q', 'W'}, new char[]{'D', 'J', 'I', 'H'}, new char[]{'D', 'C', '!', '!'}, new char[]{'D', '!', '!', '!'}, new char[]{'D', '!', '!', '!'}, new char[]{'E', 'F', 'M', 'T'}, new char[]{'E', 'L', 'R', 'X'}, new char[]{'E', 'K', 'J', 'I'}, new char[]{'E', 'D', '!', '!'}, new char[]{'E', '!', '!', '!'}, new char[]{'E', '!', '!', '!'}, new char[]{'F', 'G', '!', '!'}, new char[]{'F', 'M', 'S', 'Y'}, new char[]{'F', 'L', 'K', 'J'}, new char[]{'F', 'E', '!', '!'}, new char[]{'F', '!', '!', '!'}, new char[]{'F', '!', '!', '!'}, new char[]{'G', '!', '!', '!'}, new char[]{'G', '!', 'T', 'Z'}, new char[]{'G', 'M', 'L', 'K'}, new char[]{'G', 'F', '!', '!'}, new char[]{'G', '!', '!', '!'}, new char[]{'G', '!', '!', '!'}, new char[]{'H', 'I', 'P', 'W'}, new char[]{'H', 'O', 'U', '!'}, new char[]{'H', 'N', '!', '!'}, new char[]{'H', '!', '!', '!'}, new char[]{'H', 'A', '!', '!'}, new char[]{'H', 'B', 'C', 'D'}, new char[]{'I', 'J', 'Q', 'X'}, new char[]{'I', 'P', 'V', '!'}, new char[]{'I', 'O', 'N', '!'}, new char[]{'I', 'H', 'A', '!'}, new char[]{'I', 'B', '!', '!'}, new char[]{'I', 'C', 'D', 'E'}, new char[]{'J', 'K', 'R', 'Y'}, new char[]{'J', 'Q', 'W', '!'}, new char[]{'J', 'P', 'O', 'N'}, new char[]{'J', 'I', 'B', '!'}, new char[]{'J', 'C', '!', '!'}, new char[]{'J', 'D', 'E', 'F'}, new char[]{'K', 'L', 'S', 'Z'}, new char[]{'K', 'R', 'X', '!'}, new char[]{'K', 'Q', 'P', 'O'}, new char[]{'K', 'J', 'C', '!'}, new char[]{'K', 'D', '!', '!'}, new char[]{'K', 'E', 'F', 'G'}, new char[]{'L', 'M', 'T', '!'}, new char[]{'L', 'S', 'Y', '!'}, new char[]{'L', 'R', 'Q', 'P'}, new char[]{'L', 'K', 'D', '!'}, new char[]{'L', 'E', '!', '!'}, new char[]{'L', 'F', 'G', '!'}, new char[]{'M', '!', '!', '!'}, new char[]{'M', 'T', 'Z', '!'}, new char[]{'M', 'S', 'R', 'Q'}, new char[]{'M', 'L', 'E', '!'}, new char[]{'M', 'F', '!', '!'}, new char[]{'M', 'G', '!', '!'}, new char[]{'N', 'O', 'V', '!'}, new char[]{'N', 'U', '!', '!'}, new char[]{'N', '!', '!', '!'}, new char[]{'N', '!', '!', '!'}, new char[]{'N', '!', 'A', '!'}, new char[]{'N', 'H', 'I', 'J'}, new char[]{'O', 'P', 'W', '!'}, new char[]{'O', 'V', '!', '!'}, new char[]{'O', 'U', '!', '!'}, new char[]{'O', 'N', '!', '!'}, new char[]{'O', 'H', 'B', '!'}, new char[]{'O', 'I', 'J', 'K'}, new char[]{'P', 'Q', 'X', '!'}, new char[]{'P', 'W', '!', '!'}, new char[]{'P', 'V', 'U', '!'}, new char[]{'P', 'O', 'H', 'A'}, new char[]{'P', 'I', 'C', '!'}, new char[]{'P', 'J', 'K', 'L'}, new char[]{'Q', 'R', 'Y', '!'}, new char[]{'Q', 'X', '!', '!'}, new char[]{'Q', 'W', 'V', 'U'}, new char[]{'Q', 'P', 'I', 'B'}, new char[]{'Q', 'J', 'D', '!'}, new char[]{'Q', 'K', 'L', 'M'}, new char[]{'R', 'S', 'Z', '!'}, new char[]{'R', 'Y', '!', '!'}, new char[]{'R', 'X', 'W', 'V'}, new char[]{'R', 'Q', 'J', 'C'}, new char[]{'R', 'K', 'E', '!'}, new char[]{'R', 'L', 'M', '!'}, new char[]{'S', 'T', '!', '!'}, new char[]{'S', 'Z', '!', '!'}, new char[]{'S', 'Y', 'X', 'W'}, new char[]{'S', 'R', 'K', 'D'}, new char[]{'S', 'L', 'F', '!'}, new char[]{'S', 'M', '!', '!'}, new char[]{'T', '!', '!', '!'}, new char[]{'T', '!', '!', '!'}, new char[]{'T', 'Z', 'Y', 'X'}, new char[]{'T', 'S', 'L', 'E'}, new char[]{'T', 'M', 'G', '!'}, new char[]{'T', '!', '!', '!'}, new char[]{'U', 'V', '!', '!'}, new char[]{'U', '!', '!', '!'}, new char[]{'U', '!', '!', '!'}, new char[]{'U', '!', '!', '!'}, new char[]{'U', 'N', 'H', 'B'}, new char[]{'U', 'O', 'P', 'Q'}, new char[]{'V', 'W', '!', '!'}, new char[]{'V', '!', '!', '!'}, new char[]{'V', '!', '!', '!'}, new char[]{'V', 'U', 'N', '!'}, new char[]{'V', 'O', 'I', 'C'}, new char[]{'V', 'P', 'Q', 'R'}, new char[]{'W', 'X', '!', '!'}, new char[]{'W', '!', '!', '!'}, new char[]{'W', '!', '!', '!'}, new char[]{'W', 'V', 'O', 'H'}, new char[]{'W', 'P', 'J', 'D'}, new char[]{'W', 'Q', 'R', 'S'}, new char[]{'X', 'Y', '!', '!'}, new char[]{'X', '!', '!', '!'}, new char[]{'X', '!', '!', '!'}, new char[]{'X', 'W', 'P', 'I'}, new char[]{'X', 'Q', 'K', 'E'}, new char[]{'X', 'R', 'S', 'T'}, new char[]{'Y', 'Z', '!', '!'}, new char[]{'Y', '!', '!', '!'}, new char[]{'Y', '!', '!', '!'}, new char[]{'Y', 'X', 'Q', 'J'}, new char[]{'Y', 'R', 'L', 'F'}, new char[]{'Y', 'S', 'T', '!'}, new char[]{'Z', '!', '!', '!'}, new char[]{'Z', '!', '!', '!'}, new char[]{'Z', '!', '!', '!'}, new char[]{'Z', 'Y', 'R', 'K'}, new char[]{'Z', 'S', 'M', 'G'}, new char[]{'Z', 'T', '!', '!'}, new char[]{'A', 'B', 'C', 'H'}, new char[]{'A', 'H', 'O', '!'}, new char[]{'A', '!', '!', '!'}, new char[]{'A', '!', '!', '!'}, new char[]{'A', '!', '!', '!'}, new char[]{'A', '!', '!', 'B'}, new char[]{'B', 'C', 'D', 'I'}, new char[]{'B', 'I', 'P', 'H'}, new char[]{'B', 'H', 'N', 'A'}, new char[]{'B', 'A', '!', '!'}, new char[]{'B', '!', '!', '!'}, new char[]{'B', '!', '!', 'C'}, new char[]{'C', 'D', 'E', 'J'}, new char[]{'C', 'J', 'Q', 'I'}, new char[]{'C', 'I', 'O', 'B'}, new char[]{'C', 'B', 'A', '!'}, new char[]{'C', '!', '!', '!'}, new char[]{'C', '!', '!', 'D'}, new char[]{'D', 'E', 'F', 'K'}, new char[]{'D', 'K', 'R', 'J'}, new char[]{'D', 'J', 'P', 'C'}, new char[]{'D', 'C', 'B', '!'}, new char[]{'D', '!', '!', '!'}, new char[]{'D', '!', '!', 'E'}, new char[]{'E', 'F', 'G', 'L'}, new char[]{'E', 'L', 'S', 'K'}, new char[]{'E', 'K', 'Q', 'D'}, new char[]{'E', 'D', 'C', '!'}, new char[]{'E', '!', '!', '!'}, new char[]{'E', '!', '!', 'F'}, new char[]{'F', 'G', '!', 'M'}, new char[]{'F', 'M', 'T', 'L'}, new char[]{'F', 'L', 'R', 'E'}, new char[]{'F', 'E', 'D', '!'}, new char[]{'F', '!', '!', '!'}, new char[]{'F', '!', '!', 'G'}, new char[]{'G', '!', '!', '!'}, new char[]{'G', '!', '!', 'M'}, new char[]{'G', 'M', 'S', 'F'}, new char[]{'G', 'F', 'E', '!'}, new char[]{'G', '!', '!', '!'}, new char[]{'G', '!', '!', '!'}, new char[]{'H', 'I', 'J', 'O'}, new char[]{'H', 'O', 'V', 'N'}, new char[]{'H', 'N', '!', '!'}, new char[]{'H', '!', '!', 'A'}, new char[]{'H', 'A', '!', 'B'}, new char[]{'H', 'B', '!', 'I'}, new char[]{'I', 'J', 'K', 'P'}, new char[]{'I', 'P', 'W', 'O'}, new char[]{'I', 'O', 'U', 'H'}, new char[]{'I', 'H', '!', 'B'}, new char[]{'I', 'B', '!', 'C'}, new char[]{'I', 'C', '!', 'J'}, new char[]{'J', 'K', 'L', 'Q'}, new char[]{'J', 'Q', 'X', 'P'}, new char[]{'J', 'P', 'V', 'I'}, new char[]{'J', 'I', 'H', 'C'}, new char[]{'J', 'C', '!', 'D'}, new char[]{'J', 'D', '!', 'K'}, new char[]{'K', 'L', 'M', 'R'}, new char[]{'K', 'R', 'Y', 'Q'}, new char[]{'K', 'Q', 'W', 'J'}, new char[]{'K', 'J', 'I', 'D'}, new char[]{'K', 'D', '!', 'E'}, new char[]{'K', 'E', '!', 'L'}, new char[]{'L', 'M', '!', 'S'}, new char[]{'L', 'S', 'Z', 'R'}, new char[]{'L', 'R', 'X', 'K'}, new char[]{'L', 'K', 'J', 'E'}, new char[]{'L', 'E', '!', 'F'}, new char[]{'L', 'F', '!', 'M'}, new char[]{'M', '!', '!', 'T'}, new char[]{'M', 'T', '!', 'S'}, new char[]{'M', 'S', 'Y', 'L'}, new char[]{'M', 'L', 'K', 'F'}, new char[]{'M', 'F', '!', 'G'}, new char[]{'M', 'G', '!', '!'}, new char[]{'N', 'O', 'P', 'U'}, new char[]{'N', 'U', '!', '!'}, new char[]{'N', '!', '!', '!'}, new char[]{'N', '!', '!', '!'}, new char[]{'N', '!', '!', 'H'}, new char[]{'N', 'H', 'B', 'O'}, new char[]{'O', 'P', 'Q', 'V'}, new char[]{'O', 'V', '!', 'U'}, new char[]{'O', 'U', '!', 'N'}, new char[]{'O', 'N', '!', 'H'}, new char[]{'O', 'H', 'A', 'I'}, new char[]{'O', 'I', 'C', 'P'}, new char[]{'P', 'Q', 'R', 'W'}, new char[]{'P', 'W', '!', 'V'}, new char[]{'P', 'V', '!', 'O'}, new char[]{'P', 'O', 'N', 'I'}, new char[]{'P', 'I', 'B', 'J'}, new char[]{'P', 'J', 'D', 'Q'}, new char[]{'Q', 'R', 'S', 'X'}, new char[]{'Q', 'X', '!', 'W'}, new char[]{'Q', 'W', '!', 'P'}, new char[]{'Q', 'P', 'O', 'J'}, new char[]{'Q', 'J', 'C', 'K'}, new char[]{'Q', 'K', 'E', 'R'}, new char[]{'R', 'S', 'T', 'Y'}, new char[]{'R', 'Y', '!', 'X'}, new char[]{'R', 'X', '!', 'Q'}, new char[]{'R', 'Q', 'P', 'K'}, new char[]{'R', 'K', 'D', 'L'}, new char[]{'R', 'L', 'F', 'S'}, new char[]{'S', 'T', '!', 'Z'}, new char[]{'S', 'Z', '!', 'Y'}, new char[]{'S', 'Y', '!', 'R'}, new char[]{'S', 'R', 'Q', 'L'}, new char[]{'S', 'L', 'E', 'M'}, new char[]{'S', 'M', 'G', 'T'}, new char[]{'T', '!', '!', '!'}, new char[]{'T', '!', '!', 'Z'}, new char[]{'T', 'Z', '!', 'S'}, new char[]{'T', 'S', 'R', 'M'}, new char[]{'T', 'M', 'F', '!'}, new char[]{'T', '!', '!', '!'}, new char[]{'U', 'V', 'W', '!'}, new char[]{'U', '!', '!', '!'}, new char[]{'U', '!', '!', '!'}, new char[]{'U', '!', '!', 'N'}, new char[]{'U', 'N', '!', 'O'}, new char[]{'U', 'O', 'I', 'V'}, new char[]{'V', 'W', 'X', '!'}, new char[]{'V', '!', '!', '!'}, new char[]{'V', '!', '!', 'U'}, new char[]{'V', 'U', '!', 'O'}, new char[]{'V', 'O', 'H', 'P'}, new char[]{'V', 'P', 'J', 'W'}, new char[]{'W', 'X', 'Y', '!'}, new char[]{'W', '!', '!', '!'}, new char[]{'W', '!', '!', 'V'}, new char[]{'W', 'V', 'U', 'P'}, new char[]{'W', 'P', 'I', 'Q'}, new char[]{'W', 'Q', 'K', 'X'}, new char[]{'X', 'Y', 'Z', '!'}, new char[]{'X', '!', '!', '!'}, new char[]{'X', '!', '!', 'W'}, new char[]{'X', 'W', 'V', 'Q'}, new char[]{'X', 'Q', 'J', 'R'}, new char[]{'X', 'R', 'L', 'Y'}, new char[]{'Y', 'Z', '!', '!'}, new char[]{'Y', '!', '!', '!'}, new char[]{'Y', '!', '!', 'X'}, new char[]{'Y', 'X', 'W', 'R'}, new char[]{'Y', 'R', 'K', 'S'}, new char[]{'Y', 'S', 'M', 'Z'}, new char[]{'Z', '!', '!', '!'}, new char[]{'Z', '!', '!', '!'}, new char[]{'Z', '!', '!', 'Y'}, new char[]{'Z', 'Y', 'X', 'S'}, new char[]{'Z', 'S', 'L', 'T'}, new char[]{'Z', 'T', '!', '!'}, new char[]{'A', 'B', 'H', 'O'}, new char[]{'A', 'H', '!', '!'}, new char[]{'A', '!', '!', '!'}, new char[]{'A', '!', '!', '!'}, new char[]{'A', '!', '!', '!'}, new char[]{'A', '!', 'B', 'C'}, new char[]{'B', 'C', 'I', 'P'}, new char[]{'B', 'I', 'H', 'N'}, new char[]{'B', 'H', 'A', '!'}, new char[]{'B', 'A', '!', '!'}, new char[]{'B', '!', '!', '!'}, new char[]{'B', '!', 'C', 'D'}, new char[]{'C', 'D', 'J', 'Q'}, new char[]{'C', 'J', 'I', 'O'}, new char[]{'C', 'I', 'B', 'A'}, new char[]{'C', 'B', '!', '!'}, new char[]{'C', '!', '!', '!'}, new char[]{'C', '!', 'D', 'E'}, new char[]{'D', 'E', 'K', 'R'}, new char[]{'D', 'K', 'J', 'P'}, new char[]{'D', 'J', 'C', 'B'}, new char[]{'D', 'C', '!', '!'}, new char[]{'D', '!', '!', '!'}, new char[]{'D', '!', 'E', 'F'}, new char[]{'E', 'F', 'L', 'S'}, new char[]{'E', 'L', 'K', 'Q'}, new char[]{'E', 'K', 'D', 'C'}, new char[]{'E', 'D', '!', '!'}, new char[]{'E', '!', '!', '!'}, new char[]{'E', '!', 'F', 'G'}, new char[]{'F', 'G', 'M', 'T'}, new char[]{'F', 'M', 'L', 'R'}, new char[]{'F', 'L', 'E', 'D'}, new char[]{'F', 'E', '!', '!'}, new char[]{'F', '!', '!', '!'}, new char[]{'F', '!', 'G', '!'}, new char[]{'G', '!', '!', '!'}, new char[]{'G', '!', 'M', 'S'}, new char[]{'G', 'M', 'F', 'E'}, new char[]{'G', 'F', '!', '!'}, new char[]{'G', '!', '!', '!'}, new char[]{'G', '!', '!', '!'}, new char[]{'H', 'I', 'O', 'V'}, new char[]{'H', 'O', 'N', '!'}, new char[]{'H', 'N', '!', '!'}, new char[]{'H', '!', 'A', '!'}, new char[]{'H', 'A', 'B', '!'}, new char[]{'H', 'B', 'I', 'J'}, new char[]{'I', 'J', 'P', 'W'}, new char[]{'I', 'P', 'O', 'U'}, new char[]{'I', 'O', 'H', '!'}, new char[]{'I', 'H', 'B', '!'}, new char[]{'I', 'B', 'C', '!'}, new char[]{'I', 'C', 'J', 'K'}, new char[]{'J', 'K', 'Q', 'X'}, new char[]{'J', 'Q', 'P', 'V'}, new char[]{'J', 'P', 'I', 'H'}, new char[]{'J', 'I', 'C', '!'}, new char[]{'J', 'C', 'D', '!'}, new char[]{'J', 'D', 'K', 'L'}, new char[]{'K', 'L', 'R', 'Y'}, new char[]{'K', 'R', 'Q', 'W'}, new char[]{'K', 'Q', 'J', 'I'}, new char[]{'K', 'J', 'D', '!'}, new char[]{'K', 'D', 'E', '!'}, new char[]{'K', 'E', 'L', 'M'}, new char[]{'L', 'M', 'S', 'Z'}, new char[]{'L', 'S', 'R', 'X'}, new char[]{'L', 'R', 'K', 'J'}, new char[]{'L', 'K', 'E', '!'}, new char[]{'L', 'E', 'F', '!'}, new char[]{'L', 'F', 'M', '!'}, new char[]{'M', '!', 'T', '!'}, new char[]{'M', 'T', 'S', 'Y'}, new char[]{'M', 'S', 'L', 'K'}, new char[]{'M', 'L', 'F', '!'}, new char[]{'M', 'F', 'G', '!'}, new char[]{'M', 'G', '!', '!'}, new char[]{'N', 'O', 'U', '!'}, new char[]{'N', 'U', '!', '!'}, new char[]{'N', '!', '!', '!'}, new char[]{'N', '!', '!', '!'}, new char[]{'N', '!', 'H', 'B'}, new char[]{'N', 'H', 'O', 'P'}, new char[]{'O', 'P', 'V', '!'}, new char[]{'O', 'V', 'U', '!'}, new char[]{'O', 'U', 'N', '!'}, new char[]{'O', 'N', 'H', 'A'}, new char[]{'O', 'H', 'I', 'C'}, new char[]{'O', 'I', 'P', 'Q'}, new char[]{'P', 'Q', 'W', '!'}, new char[]{'P', 'W', 'V', '!'}, new char[]{'P', 'V', 'O', 'N'}, new char[]{'P', 'O', 'I', 'B'}, new char[]{'P', 'I', 'J', 'D'}, new char[]{'P', 'J', 'Q', 'R'}, new char[]{'Q', 'R', 'X', '!'}, new char[]{'Q', 'X', 'W', '!'}, new char[]{'Q', 'W', 'P', 'O'}, new char[]{'Q', 'P', 'J', 'C'}, new char[]{'Q', 'J', 'K', 'E'}, new char[]{'Q', 'K', 'R', 'S'}, new char[]{'R', 'S', 'Y', '!'}, new char[]{'R', 'Y', 'X', '!'}, new char[]{'R', 'X', 'Q', 'P'}, new char[]{'R', 'Q', 'K', 'D'}, new char[]{'R', 'K', 'L', 'F'}, new char[]{'R', 'L', 'S', 'T'}, new char[]{'S', 'T', 'Z', '!'}, new char[]{'S', 'Z', 'Y', '!'}, new char[]{'S', 'Y', 'R', 'Q'}, new char[]{'S', 'R', 'L', 'E'}, new char[]{'S', 'L', 'M', 'G'}, new char[]{'S', 'M', 'T', '!'}, new char[]{'T', '!', '!', '!'}, new char[]{'T', '!', 'Z', '!'}, new char[]{'T', 'Z', 'S', 'R'}, new char[]{'T', 'S', 'M', 'F'}, new char[]{'T', 'M', '!', '!'}, new char[]{'T', '!', '!', '!'}, new char[]{'U', 'V', '!', '!'}, new char[]{'U', '!', '!', '!'}, new char[]{'U', '!', '!', '!'}, new char[]{'U', '!', 'N', '!'}, new char[]{'U', 'N', 'O', 'I'}, new char[]{'U', 'O', 'V', 'W'}, new char[]{'V', 'W', '!', '!'}, new char[]{'V', '!', '!', '!'}, new char[]{'V', '!', 'U', '!'}, new char[]{'V', 'U', 'O', 'H'}, new char[]{'V', 'O', 'P', 'J'}, new char[]{'V', 'P', 'W', 'X'}, new char[]{'W', 'X', '!', '!'}, new char[]{'W', '!', '!', '!'}, new char[]{'W', '!', 'V', 'U'}, new char[]{'W', 'V', 'P', 'I'}, new char[]{'W', 'P', 'Q', 'K'}, new char[]{'W', 'Q', 'X', 'Y'}, new char[]{'X', 'Y', '!', '!'}, new char[]{'X', '!', '!', '!'}, new char[]{'X', '!', 'W', 'V'}, new char[]{'X', 'W', 'Q', 'J'}, new char[]{'X', 'Q', 'R', 'L'}, new char[]{'X', 'R', 'Y', 'Z'}, new char[]{'Y', 'Z', '!', '!'}, new char[]{'Y', '!', '!', '!'}, new char[]{'Y', '!', 'X', 'W'}, new char[]{'Y', 'X', 'R', 'K'}, new char[]{'Y', 'R', 'S', 'M'}, new char[]{'Y', 'S', 'Z', '!'}, new char[]{'Z', '!', '!', '!'}, new char[]{'Z', '!', '!', '!'}, new char[]{'Z', '!', 'Y', 'X'}, new char[]{'Z', 'Y', 'S', 'L'}, new char[]{'Z', 'S', 'T', '!'}, new char[]{'Z', 'T', '!', '!'},};
}
