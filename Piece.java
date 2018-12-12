import java.util.ArrayList;
import javax.swing.Icon;

abstract class Piece{
    boolean available;
    String color;
    int x;  int y;
    Icon icon;
    ArrayList<Integer[]> availableSquares;
    Piece(boolean available, int x, int y, String color){
        this.color = color;
        this.available = available;
        this.x = x;
        this.y=y;
       // this.icon = icon;
    }
    public Icon getIcon(){return this.icon;}
    public String getColor(){return this.color;}
    boolean isValid(ChessBoard board,int fromX, int fromY, int toX, int toY){
        if(fromX==toX && fromY == toY){
            return false;
        }
            return !(!board.isEmpty(toX,toY) && (color == null ? board.getColor(toX,toY) == null : color.equals(board.getColor(toX,toY))));
        }
    ArrayList<Integer[]> getAvailableSquares(ChessBoard board){
        availableSquares = new ArrayList<>();
    for(int i = 0; i<8; i++){
        for(int j = 0; j<8; j++){
            if(Math.max(Math.abs(x-i), Math.abs(y-j))>0){
                Integer[] temp = new Integer[2];
                temp[0]=i; temp[1]=j;
                if( this.isValid(board, x, y, i, j)){
                    availableSquares.add(temp);
                }   
            }

        }
}   
    return availableSquares;
    }

}