import javax.swing.ImageIcon;

class Pawn extends Piece{
    Pawn(boolean available, int x, int y, String color){
        super(available,x,y,color); 
        if("white".equals(color)){icon = new ImageIcon("WPawn.png");}
        else{icon = new ImageIcon("BPawn.png");}
    }
    @Override
    boolean isValid(ChessBoard board, int fromX, int fromY, int toX, int toY){
        if(!super.isValid(board, fromX, fromY, toX,toY)){return false;}
        if("white".equals(color)){
          if(board.pieces[toX][toY]!=null && fromX-toX == 1 && Math.abs(fromY-toY)==1 && "black".equals(board.pieces[toX][toY].color)){return true;}
          if(board.pieces[toX][toY]!=null && "black".equals(board.pieces[toX][toY].color)){return false;}
          if(fromX==6){
              if(board.pieces[5][toY]!=null && "black".equals(board.pieces[5][toY].color)){return false;}
              if(Math.abs(fromY-toY)>0){return false;} //om ej capture
              if(fromX-toX>2 || fromX-toX<0){return false;}
              
          }
          else{
              if(Math.abs(fromY-toY)>0){return false;} //om ej capture
              if(fromX-toX>1 || fromX-toX<0){return false;}
          }
        }
          if("black".equals(color)){
          if(board.pieces[toX][toY]!=null && toX-fromX == 1 && Math.abs(fromY-toY)==1 && "white".equals(board.pieces[toX][toY].color)){return true;}
          if(board.pieces[toX][toY]!=null&&"white".equals(board.pieces[toX][toY].color)){return false;}
          if(fromX==1){
              if(board.pieces[2][toY]!=null && "white".equals(board.pieces[2][toY].color)){return false;}
              if(Math.abs(fromY-toY)>0){return false;} //om ej capture
              if(toX-fromX>2 || toX-fromX<0){return false;}
              
          }
          else{
              if(Math.abs(fromY-toY)>0){return false;} //om ej capture
              if(toX-fromX>1 || toX-fromX<0){return false;}
          }
        }
        return true;
    }
    
}