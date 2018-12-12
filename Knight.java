import javax.swing.Icon;
import javax.swing.ImageIcon;

class Knight extends Piece{
    Knight(boolean available, int x, int y, String color){
        super(available,x,y,color); 
        if(color=="white"){icon = new ImageIcon("WKnight.png");}
        else{icon = new ImageIcon("BKnight.png");}
    }
            
    @Override
    boolean isValid(ChessBoard board, int fromX, int fromY, int toX, int toY){
        if(!super.isValid(board, fromX, fromY, toX,toY)){return false;}
        if(!(Math.abs(fromX-toX)==1 && Math.abs(fromY-toY) == 2 || Math.abs(fromX-toX)==2 && Math.abs(fromY-toY) == 1)){return false;}
        return true;
    }
    
}