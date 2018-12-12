import javax.swing.Icon;
import javax.swing.ImageIcon;

class King extends Piece{
    King(boolean available, int x, int y, String color){
        super(available,x,y,color); 
        if(color=="white"){icon = new ImageIcon("WKing.png");}
        else{icon = new ImageIcon("BKing.png");}
    }
            
    @Override
    boolean isValid(ChessBoard board, int fromX, int fromY, int toX, int toY){
        if(!super.isValid(board, fromX, fromY, toX,toY)){return false;}
        if(Math.pow(toX-fromX,2)+Math.pow(toY-fromY,2)>2){return false;}
        
        return true;
    }
    
}