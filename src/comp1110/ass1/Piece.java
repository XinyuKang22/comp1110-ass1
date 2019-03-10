package comp1110.ass1;

/**
 * An enumeration representing the seven pieces in the IQStars game.
 * <p>
 * You may want to look at the 'Planet' example in the Oracle enum tutorial for
 * an example of an enumeration.
 * <p>
 * http://docs.oracle.com/javase/tutorial/java/javaOO/enum.html
 */
public enum Piece {
    A(new Hex[]{new Hex(0, 0), new Hex(1, 0), new Hex(2, 0)}), // purple
    B(new Hex[]{new Hex(0, 0), new Hex(1, 0), new Hex(1, 1)}), // orange
    C(new Hex[]{new Hex(0, 0), new Hex(1, 0), new Hex(1, 1), new Hex(1, 2)}), // pink
    D(new Hex[]{new Hex(0, 0), new Hex(1, 0), new Hex(0, 1), new Hex(1, 1)}), // red
    E(new Hex[]{new Hex(0, 0), new Hex(1, 0), new Hex(1, 1), new Hex(2, 2)}), // green
    F(new Hex[]{new Hex(0, 0), new Hex(1, 0), new Hex(2, 0), new Hex(0, 1)}), // yellow
    G(new Hex[]{new Hex(0, 0), new Hex(1, 0), new Hex(0, 1), new Hex(1, 2)}); // blue

    public static final double MAX_PIECE_WIDTH = 3;
    public static final char INVALID_SPACE = '!';

    /**
     * A list of spaces covered by this piece in its default rotation.
     * Each space in the list is given as an offset from the origin (0,0).
     */
    public final Hex[] shape;

    Piece(Hex[] shape) {
        this.shape = shape;
    }

    public char getId() {
        return this.name().charAt(0);
    }

    /**
     * Return indices corresponding to which board spaces would be covered
     * by this piece, given a correct provided placement.
     * If a part of the piece would lie off the board in a given orientation,
     * return '!' for that part of the piece.
     * The order of the indices in the array does not matter.
     * <p>
     * Examples:
     * Given the piece placement string 'AAB' would return the indices: {'A', 'H', 'O'}.
     * Given the piece placement string 'CPD' would return the indices: {'B', 'H', 'O', 'P'}.
     * Given the piece placement string 'DAB' would return the indices: {'!', 'A', 'H', 'N'}.
     * <p>
     * Hint: You can associate values with each entry in the enum using a constructor,
     * so you could use that to somehow encode the properties of each of the twelve pieces.
     * Then in this method you could use the value to calculate the required indices.
     * <p>
     * See the 'Grade' enum given in the O2 lecture as part of the lecture code (live coding),
     * for an example of an enum with associated state and constructors.
     * <p>
     * The tutorial here: http://docs.oracle.com/javase/tutorial/java/javaOO/enum.html
     * has an example of a Planet enum, which includes two doubles in each planet's
     * constructor representing the mass and radius.   Those values are used in the
     * surfaceGravity() method, for example.
     *
     * @param hex         the index of the hex in which the origin of the piece is placed ('A' to 'Z')
     * @param orientation which orientation the tile is in ('A' to 'F')
     * @return A set of indices corresponding to the board positions that would be covered by this piece
     */
    char[] getCovered(char hex, char orientation) {
        // FIXME Task 6: implement code that correctly creates an array of chars specifying the indices of the covered spaces
        char piece = getId();

        char[] location = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};
        int[] rows = {1, 1, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 2, 3, 3, 3, 3, 3, 3, 3, 4, 4, 4, 4, 4, 4};
        int HexNum = 0;
        for (int i = 0; i < 26; i++) {
            if (hex == location[i]) {
                HexNum = i;
                break;
            }
        }
        int RowNum = rows[HexNum];

        int[] indices = {0, 0, 0, 0};
        int[] rowList = {0, 0, 0, 0};
        if (piece == 'A') {
            if (orientation == 'A') {
                indices[0] = HexNum;
                indices[1] = HexNum + 1;
                indices[2] = HexNum + 2;
                rowList[0] = RowNum;
                rowList[1] = RowNum;
                rowList[2] = RowNum;
            } else if (orientation == 'B') {
                indices[0] = HexNum;
                indices[1] = HexNum + 7;
                indices[2] = HexNum + 14;
                rowList[0] = RowNum;
                rowList[1] = RowNum + 1;
                rowList[2] = RowNum + 2;
            } else if (orientation == 'C') {
                indices[0] = HexNum;
                indices[1] = HexNum + 6;
                indices[2] = HexNum + 12;
                rowList[0] = RowNum;
                rowList[1] = RowNum + 1;
                rowList[2] = RowNum + 2;
            } else if (orientation == 'D') {
                indices[0] = HexNum;
                indices[1] = HexNum - 1;
                indices[2] = HexNum - 2;
                rowList[0] = RowNum;
                rowList[1] = RowNum;
                rowList[2] = RowNum;
            } else if (orientation == 'E') {
                indices[0] = HexNum;
                indices[1] = HexNum - 7;
                indices[2] = HexNum - 14;
                rowList[0] = RowNum;
                rowList[1] = RowNum - 1;
                rowList[2] = RowNum - 2;
            } else if (orientation == 'F') {
                indices[0] = HexNum;
                indices[1] = HexNum - 6;
                indices[2] = HexNum - 12;
                rowList[0] = RowNum;
                rowList[1] = RowNum - 1;
                rowList[2] = RowNum - 2;
            }
        } else if (piece == 'B') {
            if (orientation == 'A') {
                indices[0] = HexNum;
                indices[1] = HexNum + 1;
                indices[2] = HexNum + 8;
                rowList[0] = RowNum;
                rowList[1] = RowNum;
                rowList[2] = RowNum + 1;
            } else if (orientation == 'B') {
                indices[0] = HexNum;
                indices[1] = HexNum + 7;
                indices[2] = HexNum + 13;
                rowList[0] = RowNum;
                rowList[1] = RowNum + 1;
                rowList[2] = RowNum + 2;
            } else if (orientation == 'C') {
                indices[0] = HexNum;
                indices[1] = HexNum + 5;
                indices[2] = HexNum + 6;
                rowList[0] = RowNum;
                rowList[1] = RowNum + 1;
                rowList[2] = RowNum + 1;
            } else if (orientation == 'D') {
                indices[0] = HexNum;
                indices[1] = HexNum - 1;
                indices[2] = HexNum - 8;
                rowList[0] = RowNum;
                rowList[1] = RowNum;
                rowList[2] = RowNum - 1;
            } else if (orientation == 'E') {
                indices[0] = HexNum;
                indices[1] = HexNum - 7;
                indices[2] = HexNum - 13;
                rowList[0] = RowNum;
                rowList[1] = RowNum - 1;
                rowList[2] = RowNum - 2;
            } else if (orientation == 'F') {
                indices[0] = HexNum;
                indices[1] = HexNum - 6;
                indices[2] = HexNum - 5;
                rowList[0] = RowNum;
                rowList[1] = RowNum - 1;
                rowList[2] = RowNum - 1;
            }
        } else if (piece == 'C') {
            if (orientation == 'A') {
                indices[0] = HexNum;
                indices[1] = HexNum + 1;
                indices[2] = HexNum + 8;
                indices[3] = HexNum + 14;
                rowList[0] = RowNum;
                rowList[1] = RowNum;
                rowList[2] = RowNum + 1;
                rowList[3] = RowNum + 2;
            } else if (orientation == 'B') {
                indices[0] = HexNum;
                indices[1] = HexNum + 7;
                indices[2] = HexNum + 12;
                indices[3] = HexNum + 13;
                rowList[0] = RowNum;
                rowList[1] = RowNum + 1;
                rowList[2] = RowNum + 2;
                rowList[3] = RowNum + 2;
            } else if (orientation == 'C') {
                indices[0] = HexNum;
                indices[1] = HexNum - 2;
                indices[2] = HexNum + 5;
                indices[3] = HexNum + 6;
                rowList[0] = RowNum;
                rowList[1] = RowNum;
                rowList[2] = RowNum + 1;
                rowList[3] = RowNum + 1;
            } else if (orientation == 'D') {
                indices[0] = HexNum;
                indices[1] = HexNum - 1;
                indices[2] = HexNum - 8;
                indices[3] = HexNum - 14;
                rowList[0] = RowNum;
                rowList[1] = RowNum;
                rowList[2] = RowNum - 1;
                rowList[3] = RowNum - 2;
            } else if (orientation == 'E') {
                indices[0] = HexNum;
                indices[1] = HexNum - 7;
                indices[2] = HexNum - 13;
                indices[3] = HexNum - 12;
                rowList[0] = RowNum;
                rowList[1] = RowNum - 1;
                rowList[2] = RowNum - 2;
                rowList[3] = RowNum - 2;
            } else if (orientation == 'F') {
                indices[0] = HexNum;
                indices[1] = HexNum + 2;
                indices[2] = HexNum - 6;
                indices[3] = HexNum - 5;
                rowList[0] = RowNum;
                rowList[1] = RowNum;
                rowList[2] = RowNum - 1;
                rowList[3] = RowNum - 1;
            }
        } else if (piece == 'D') {
            if (orientation == 'A') {
                indices[0] = HexNum;
                indices[1] = HexNum + 1;
                indices[2] = HexNum + 7;
                indices[3] = HexNum + 8;
                rowList[0] = RowNum;
                rowList[1] = RowNum;
                rowList[2] = RowNum + 1;
                rowList[3] = RowNum + 1;
            } else if (orientation == 'B') {
                indices[0] = HexNum;
                indices[1] = HexNum + 6;
                indices[2] = HexNum + 7;
                indices[3] = HexNum + 13;
                rowList[0] = RowNum;
                rowList[1] = RowNum + 1;
                rowList[2] = RowNum + 1;
                rowList[3] = RowNum + 2;
            } else if (orientation == 'C') {
                indices[0] = HexNum;
                indices[1] = HexNum - 1;
                indices[2] = HexNum + 5;
                indices[3] = HexNum + 6;
                rowList[0] = RowNum;
                rowList[1] = RowNum;
                rowList[2] = RowNum + 1;
                rowList[3] = RowNum + 1;
            } else if (orientation == 'D') {
                indices[0] = HexNum;
                indices[1] = HexNum - 1;
                indices[2] = HexNum - 7;
                indices[3] = HexNum - 8;
                rowList[0] = RowNum;
                rowList[1] = RowNum;
                rowList[2] = RowNum - 1;
                rowList[3] = RowNum - 1;
            } else if (orientation == 'E') {
                indices[0] = HexNum;
                indices[1] = HexNum - 7;
                indices[2] = HexNum - 6;
                indices[3] = HexNum - 13;
                rowList[0] = RowNum;
                rowList[1] = RowNum - 1;
                rowList[2] = RowNum - 1;
                rowList[3] = RowNum - 2;
            } else if (orientation == 'F') {
                indices[0] = HexNum;
                indices[1] = HexNum + 1;
                indices[2] = HexNum - 6;
                indices[3] = HexNum - 5;
                rowList[0] = RowNum;
                rowList[1] = RowNum;
                rowList[2] = RowNum - 1;
                rowList[3] = RowNum - 1;
            }
        } else if (piece == 'E') {
            if (orientation == 'A') {
                indices[0] = HexNum;
                indices[1] = HexNum + 1;
                indices[2] = HexNum + 8;
                indices[3] = HexNum + 15;
                rowList[0] = RowNum;
                rowList[1] = RowNum;
                rowList[2] = RowNum + 1;
                rowList[3] = RowNum + 2;
            } else if (orientation == 'B') {
                indices[0] = HexNum;
                indices[1] = HexNum + 7;
                indices[2] = HexNum + 13;
                indices[3] = HexNum + 19;
                rowList[0] = RowNum;
                rowList[1] = RowNum + 1;
                rowList[2] = RowNum + 2;
                rowList[3] = RowNum + 3;
            } else if (orientation == 'C') {
                indices[0] = HexNum;
                indices[1] = HexNum + 6;
                indices[2] = HexNum + 5;
                indices[3] = HexNum + 4;
                rowList[0] = RowNum;
                rowList[1] = RowNum + 1;
                rowList[2] = RowNum + 1;
                rowList[3] = RowNum + 1;
            } else if (orientation == 'D') {
                indices[0] = HexNum;
                indices[1] = HexNum - 1;
                indices[2] = HexNum - 8;
                indices[3] = HexNum -15;
                rowList[0] = RowNum;
                rowList[1] = RowNum;
                rowList[2] = RowNum - 1;
                rowList[3] = RowNum - 2;
            } else if (orientation == 'E') {
                indices[0] = HexNum;
                indices[1] = HexNum - 7;
                indices[2] = HexNum - 13;
                indices[3] = HexNum - 19;
                rowList[0] = RowNum;
                rowList[1] = RowNum - 1;
                rowList[2] = RowNum - 2;
                rowList[3] = RowNum - 3;
            } else if (orientation == 'F') {
                indices[0] = HexNum;
                indices[1] = HexNum - 6;
                indices[2] = HexNum - 5;
                indices[3] = HexNum - 4;
                rowList[0] = RowNum;
                rowList[1] = RowNum - 1;
                rowList[2] = RowNum - 1;
                rowList[3] = RowNum - 1;
            }
        } else if (piece == 'F') {
            if (orientation == 'A') {
                indices[0] = HexNum;
                indices[1] = HexNum + 1;
                indices[2] = HexNum + 2;
                indices[3] = HexNum + 7;
                rowList[0] = RowNum;
                rowList[1] = RowNum;
                rowList[2] = RowNum;
                rowList[3] = RowNum + 1;
            } else if (orientation == 'B') {
                indices[0] = HexNum;
                indices[1] = HexNum + 6;
                indices[2] = HexNum + 7;
                indices[3] = HexNum + 14;
                rowList[0] = RowNum;
                rowList[1] = RowNum + 1;
                rowList[2] = RowNum + 1;
                rowList[3] = RowNum + 2;
            } else if (orientation == 'C') {
                indices[0] = HexNum;
                indices[1] = HexNum - 1;
                indices[2] = HexNum + 6;
                indices[3] = HexNum + 12;
                rowList[0] = RowNum;
                rowList[1] = RowNum;
                rowList[2] = RowNum + 1;
                rowList[3] = RowNum + 2;
            } else if (orientation == 'D') {
                indices[0] = HexNum;
                indices[1] = HexNum - 1;
                indices[2] = HexNum - 2;
                indices[3] = HexNum - 7;
                rowList[0] = RowNum;
                rowList[1] = RowNum;
                rowList[2] = RowNum;
                rowList[3] = RowNum - 1;
            } else if (orientation == 'E') {
                indices[0] = HexNum;
                indices[1] = HexNum - 7;
                indices[2] = HexNum - 6;
                indices[3] = HexNum - 14;
                rowList[0] = RowNum;
                rowList[1] = RowNum - 1;
                rowList[2] = RowNum - 1;
                rowList[3] = RowNum - 2;
            } else if (orientation == 'F') {
                indices[0] = HexNum;
                indices[1] = HexNum + 1;
                indices[2] = HexNum - 6;
                indices[3] = HexNum - 12;
                rowList[0] = RowNum;
                rowList[1] = RowNum;
                rowList[2] = RowNum - 1;
                rowList[3] = RowNum - 2;
            }
        } else if (piece == 'G') {
            if (orientation == 'A') {
                indices[0] = HexNum;
                indices[1] = HexNum + 1;
                indices[2] = HexNum + 7;
                indices[3] = HexNum + 14;
                rowList[0] = RowNum;
                rowList[1] = RowNum;
                rowList[2] = RowNum + 1;
                rowList[3] = RowNum + 2;
            } else if (orientation == 'B') {
                indices[0] = HexNum;
                indices[1] = HexNum + 6;
                indices[2] = HexNum + 7;
                indices[3] = HexNum + 12;
                rowList[0] = RowNum;
                rowList[1] = RowNum + 1;
                rowList[2] = RowNum + 1;
                rowList[3] = RowNum + 2;
            } else if (orientation == 'C') {
                indices[0] = HexNum;
                indices[1] = HexNum - 1;
                indices[2] = HexNum - 2;
                indices[3] = HexNum + 6;
                rowList[0] = RowNum;
                rowList[1] = RowNum;
                rowList[2] = RowNum;
                rowList[3] = RowNum + 1;
            } else if (orientation == 'D') {
                indices[0] = HexNum;
                indices[1] = HexNum - 1;
                indices[2] = HexNum - 7;
                indices[3] = HexNum - 14;
                rowList[0] = RowNum;
                rowList[1] = RowNum;
                rowList[2] = RowNum - 1;
                rowList[3] = RowNum - 2;
            } else if (orientation == 'E') {
                indices[0] = HexNum;
                indices[1] = HexNum - 7;
                indices[2] = HexNum - 6;
                indices[3] = HexNum - 12;
                rowList[0] = RowNum;
                rowList[1] = RowNum - 1;
                rowList[2] = RowNum - 1;
                rowList[3] = RowNum - 2;
            } else if (orientation == 'F') {
                indices[0] = HexNum;
                indices[1] = HexNum + 1;
                indices[2] = HexNum + 2;
                indices[3] = HexNum - 6;
                rowList[0] = RowNum;
                rowList[1] = RowNum;
                rowList[2] = RowNum;
                rowList[3] = RowNum - 1;
            }
        }
        char[] toReturn;
        toReturn = new char[indices.length];
        for (int x = 0; x < 4; x++) {
            if (indices[x] >= 0 && indices[x] <= 25) {
                if (rows[indices[x]] == rowList[x]) {
                    toReturn[x] = location[indices[x]];
                } else {
                    toReturn[x] = '!';
                }
            } else {
                toReturn[x] = '!';
            }
        }
            if (piece == 'A' || piece == 'B') {
                char[] toReturnSpecial = {toReturn[0], toReturn[1], toReturn[2]};
                return toReturnSpecial;
            } else {
                return toReturn;
            }
            //return new char[]{};
        }

}
