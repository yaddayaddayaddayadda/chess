import javax.swing.Icon;
import javax.swing.ImageIcon;
class Rook extends Piece{
    Rook(boolean available, int x, int y, String color){
        super(available,x,y,color); 
        if("white".equals(color)){icon = new ImageIcon("WRook.png");}
        else{icon = new ImageIcon("BRook.png");}
    }
    
    @Override
    boolean isValid(ChessBoard board, int fromX, int fromY, int toX, int toY){
        if(!super.isValid(board, fromX, fromY, toX,toY)){return false;}
        if(!(toX-fromX == 0 || fromY-toY == 0)){return false;}
                Rectilinear obj =new Rectilinear(fromX,toX,fromY,toY);
        int path[][] = obj.getOrder();
        for(int[] index : obj.getOrder()){
            if(path.length>0 && board.pieces[index[0]][index[1]]!=null){return false;}
        }

        return true;
    }
    
}