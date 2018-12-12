import javax.swing.Icon;
import javax.swing.ImageIcon;
class Queen extends Piece{
    Queen(boolean available, int x, int y, String color){
        super(available,x,y,color); 
        if("white".equals(color)){icon = new ImageIcon("WQueen.png");}
        else{icon = new ImageIcon("BQueen.png");}
    }
    
    @Override
    boolean isValid(ChessBoard board, int fromX, int fromY, int toX, int toY){
        if(!super.isValid(board, fromX, fromY, toX,toY)){return false;}
        Rectilinear obj =new Rectilinear(fromX,toX,fromY,toY);
        int path[][] = obj.getOrder();
        if(Math.abs(fromX-toX)==Math.abs(fromY-toY) || toX-fromX == 0 || fromY-toY == 0){
        for(int[] index : obj.getOrder()){
            if(path.length>0 && board.pieces[index[0]][index[1]]!=null){return false;}
        }}
        return !(!(Math.abs(fromX-toX)==Math.abs(fromY-toY)) && (!(toX-fromX == 0 || fromY-toY == 0)));
    }
    
}