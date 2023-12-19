package Game;

import GUI.ChessColor;
import Pieces.*;

public class Board {
    
    private Piece[][] board;
    private int size=8;
    private PlayerTurn turnTracker;
    private StateHandler stateTracker;
    private String message;
    private Piece selectedPiece;

    public Board() {
        this.board = new Piece[size][size];
        turnTracker = new PlayerTurn(); // Playerturn is initialized to white
        stateTracker = new StateHandler(); // Handles the different states
        setupPieces();
        initMessage();
    }

    /*
     * Method which sets up all the pieces on the chess board.
     */
    private void setupPieces() {
        for (int row=0; row<size; row++) {
            ChessColor color = ChessColor.BLACK; // Default color, to be changed.
            for (int col=0; col<size; col++) {

                if (row > 5 && row < 8) color=ChessColor.WHITE;

                // Setup all the pieces
                if (row==1 || row==6) {
                    Piece pawn = new Pawn(row, col, PieceType.PAWN, color, this);
                    board[row][col] = pawn; }
                else if (row==0 || row==7) {
                    if (col==0 || col==7) {
                        Piece rook = new Rook(row, col, PieceType.ROOK, color, this);
                        board[row][col] = rook; }
                    else if (col==1 || col==6) {
                        Piece knight = new Knight(row, col, PieceType.KNIGHT, color, this);
                        board[row][col] = knight; }
                    else if (col==2 || col==5) {
                        Piece bishop = new Bishop(row, col, PieceType.BISHOP, color, this);
                        board[row][col] = bishop;
                    }
                    else if (col==3) {
                        Piece queen = new Queen(row, col, PieceType.QUEEN, color, this);
                        board[row][col] = queen;
                    }
                    else if (col==4) {
                        Piece king = new King(row, col, PieceType.KING, color, this);
                        board[row][col] = king;
                    }
                }
            }
        }
    }

    /*
     * Method which returns the piece at a certain row and column on the board.
     * If no piece there, it returns null.
     */
    public Piece getPieceAt(int row, int col) {
        return this.board[row][col];
    }

    public boolean movePieceFrom(int fromRow, int fromCol) {
        if (this.board[fromRow][fromCol] == null) {
            message="You need to choose any of your pieces.";
            return false;
        }
        else if (this.board[fromRow][fromCol].getColor().equals(turnTracker.getPlayerTurn())) {
            if (stateTracker.getState().equals(GameState.SELECT)) stateTracker.changeState();
            selectedPiece = this.board[fromRow][fromCol];
            message = "Selected " + selectedPiece.getColor().toString().toLowerCase() + " " + selectedPiece.getType().toString().toLowerCase() + ".";
            return true;
        }
        else {
            message = "You need to select a piece of your color.";
            return false; }
    }

    public boolean movePieceTo(int row, int col) {
        if (stateTracker.getState().equals(GameState.DEPLOY)) {
            if (this.selectedPiece.move(row, col)) {
                board[row][col] = selectedPiece;

                // Check if promotion is occuring, and handle it by converting the selected pawn to a queen
                if (this.selectedPiece instanceof Pawn) {
                    Pawn pawn = (Pawn) selectedPiece;
                    if (pawn.promotion()) {
                        ChessColor color = row==0 ? ChessColor.WHITE : ChessColor.BLACK;
                        this.selectedPiece = new Queen(row, col, PieceType.QUEEN, color, this);
                        setPiece(row, col, this.selectedPiece);
                        String clr = color == ChessColor.WHITE ? "White" : "Black";
                        message = clr + " pawn has been promoted.";
                    }
                }
                // Check if this piece now has put the opposite king in 'check'
                this.selectedPiece.check();
                changeTurnAndState();
                return true;
            }
        }
        return false;
    }

    /*
     * Performs automatic move.
     */
    public void automaticMove() {
        Pair<Integer, Integer> coordinates = this.selectedPiece.getAvailableMoves().get(0);
        int newRow = coordinates.getRow();
        int newCol = coordinates.getCol();
        
        // Clear the original position
        int originalRow = this.selectedPiece.getRow();
        int originalCol = this.selectedPiece.getCol();
        board[originalRow][originalCol] = null;

        // Place the piece in the new position
        board[newRow][newCol] = this.selectedPiece;

        // Update the piece's internal position
        this.selectedPiece.updateCoordinates(newRow, newCol);

        message = "Only one move is possible for the chosen piece. Suggestion has been made for the next move.";
    }

    private void changeTurnAndStateMessage() {
        if (turnTracker.getPlayerTurn()==ChessColor.BLACK) {
            message = "Black's turn.";
        }
        else message = "White's turn";
    }

    /*
     * Changes the player turn (black/white) and state (select/deploy) for the next move.
     */
    public void changeTurnAndState() {
        turnTracker.changePlayerTurn();
        stateTracker.changeState();
        changeTurnAndStateMessage();
    }

    /*
     * Returns the piece which is selected, in order to
     * move it properly.
     */
    public Piece getSelectedPiece() {
        return this.selectedPiece;
    }

    /*
     * Removes a piece at a certain row and column.
     */
    public void removePiece(int row, int col) {
        board[row][col] = null;
    }

    /*
     * Sets the message on the label.
     */
    public void setMessage(String msg) {
        this.message = msg;
    }

    /*
     * Returns the message.
     */
    public String getMessage() {
        return this.message;
    }

    /*
     * Returns the size of the chess board.
     */
    public int getSize() {
        return this.size;
    }

    /*
     * Returns if a row and col is in valid position
     */
    public boolean isValidPosition(int row, int col) {
        if (row < 0 || col < 0 || row >= size || col >= size) {
            return false;
        }
        else return true;
    }

    /*
     * Sets a piece at a certain row and column.
     */
    public void setPiece(int row, int col, Piece piece) {
        board[row][col] = piece;
        piece.updateCoordinates(row, col);
    }

    private void initMessage() {
        this.message = "Welcome to chess. White begins playing.";
    }
}
