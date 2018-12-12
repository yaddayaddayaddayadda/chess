import java.awt.*;
import javax.swing.*;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.AbstractSet;
import java.util.ArrayList;
import java.util.HashMap;

class ChessBoard extends JFrame implements ActionListener{
    private JButton[][] board;
    Container cc; 
    private JPanel knappanel;
    private JLabel mess;
    private Color walnutbrown = new Color(210,105,30);
    private Color lightgray = new Color(255,229,166);
    private Color lightbrown = new Color(255,179,0);
    public Piece[][] pieces;
    private ArrayList<Piece> whitepieces = new ArrayList<>();
    private ArrayList<Piece> blackpieces = new ArrayList<>();
    private ArrayList<ArrayList> possiblemoveswhite = new ArrayList<>();
    private ArrayList<ArrayList> possiblemovesblack = new ArrayList<>();
    private boolean turn;
    private boolean isCheck;
    private boolean hasSelectedPiece;
    private Piece currentPiece;
    private King wking;
    private King bking;
    ChessBoard(){
        hasSelectedPiece = false;
        turn = true;
        isCheck = false;
        pieces = new Piece[8][8];
        cc=getContentPane();
        setSize(600,600);
        mess = new JLabel("!");
        knappanel = new JPanel();
        board = new JButton[8][8];
            for (int i=0;i<8;i++){
                for (int j=0; j<8; j++){
                    board[i][j]=new JButton();
                    board[i][j].addActionListener(this);
                    board[i][j].setBackground(Color.white);
                    if((i+j)%2==1){board[i][j].setBackground(walnutbrown);}
                     knappanel.add(board[i][j]);
                    }
            }
         knappanel.setLayout(new GridLayout(8,8));
         cc.add(knappanel);
         cc.add(mess, BorderLayout.SOUTH);
         init();
         updateGame();
         getPiecesAsArrays();
         
    }
    private void init(){
        for(int i = 0; i<8; i++){
            pieces[6][i] = new Pawn(true,6,i,"white");
        }
                for(int i = 0; i<8; i++){
            pieces[1][i] = new Pawn(true,1,i,"black");
        }
        pieces[7][3] = new Queen(true,7,3,"white"); pieces[0][3] = new Queen(true,0,3,"black");
        pieces[7][0] = new Rook(true,7,0,"white"); pieces[7][7] = new Rook(true,7,7,"white");
        pieces[7][1] = new Knight(true,7,1,"white"); pieces[7][6] = new Knight(true,7,6,"white");
        pieces[7][2] = new Bishop(true,7,2,"white"); pieces[7][5] = new Bishop(true,7,5,"white");
        pieces[0][2] = new Bishop(true,0,2,"black"); pieces[0][5] = new Bishop(true,0,5,"black");
        pieces[0][1] = new Knight(true,0,1,"black"); pieces[0][6] = new Knight(true,0,6,"black");
        pieces[0][0] = new Rook(true,0,0,"black"); pieces[0][7] = new Rook(true,0,7,"black");
        wking = new King(true,7,4,"white"); pieces[7][4] = wking;
        bking = new King(true,0,4,"black"); pieces[0][4] = bking;
    }
    private void switchPlayer(){
        turn = !turn;
    }
    private int[] findButton(Object c) {
        int[] l = new int[2];
        for (int x = 0; x < 8; x++) {
            for (int y = 0; y < 8; y++) {
                if (c.equals(board[x][y])) {
                    l[0]=x; l[1] = y;
                }
            }
        }
        return l;
    }
    private boolean isCheck(){
       for(Piece p : blackpieces){
           for(Integer[] pos : p.getAvailableSquares(this)){
               
               if(wking.x == pos[0] && wking.y == pos[1]){
                   System.out.println("White in check");  
                   return true;
               }
           }
       }
              for(Piece p : whitepieces){
           for(Integer[] pos : p.getAvailableSquares(this)){
               if(bking.x == pos[0] && bking.y == pos[1]){
                   System.out.println("Black in check");
                   return true;
               }
           }
       }
       
        return false;
    }
    public String getColor(int i,int j){
        return pieces[i][j].color;    
    }
    private boolean OutOfCheck(int toX, int toY){
        Piece p = null;
        p=this.currentPiece;
        ArrayList<Piece> b = new ArrayList<Piece>();
        b = this.whitepieces;
        ArrayList<Piece> c = new ArrayList<Piece>();
        c = this.blackpieces;
        Piece[][] d = this.pieces;
        if(p.isValid(this, p.x, p.y, toX, toY)){
                        if(d[toX][toY] != null){           
                if("white".equals(d[toX][toY].color)){
                    b.remove(d[toX][toY]);
                }
                else{
                    c.remove(d[toX][toY]);
                }
            }   
            d[p.x][p.y] = null;
            p.x=toX;p.y=toY;
            //d[toX][toY] = p;                //this needs to be commented for some reason
        }
       if("white".equals(p.color)){
       for(Piece e : c){
           for(Integer[] pos : e.getAvailableSquares(this)){
               if(wking.x == pos[0] && wking.y == pos[1]){
                   return false;
               }
           }
       }}
       else{
                         for(Piece e : whitepieces){
           for(Integer[] pos : e.getAvailableSquares(this)){
               if(bking.x == pos[0] && bking.y == pos[1]){
                   return false;
               }
           }
       }
       }
        return true;
    }
    private void getPiecesAsArrays(){
        for(Piece[] plist : pieces){
            for(Piece p : plist){
                if(p!=null &&"white".equals(p.color)){
                    whitepieces.add(p);
                }
                if(p!=null &&"black".equals(p.color)){
                    blackpieces.add(p);
                }
            }
        }
    }
    public boolean isEmpty(int i,int j){
        return pieces[i][j] == null;
        
    }

    private void setCurrentPiece(Piece p){
        currentPiece=p;
        ArrayList<Integer[]> a = p.getAvailableSquares(this);
        for(Integer[] b : a){
            if((b[0]+b[1])%2 == 1){
                board[b[0]][b[1]].setBackground(lightbrown);
            }
            else{
                board[b[0]][b[1]].setBackground(lightgray);
            }
        }
        board[p.x][p.y].setBackground(Color.green);
        hasSelectedPiece = !hasSelectedPiece;
    }
    @Override
    public void move(int toX, int toY){
        if(currentPiece.isValid(this, currentPiece.x, currentPiece.y, toX, toY)){
            if(!OutOfCheck(toX,toY)){System.out.println("Not good");}
            for (int i=0;i<8;i++){
                for (int j=0; j<8; j++){
                    board[i][j].setBackground(Color.white);
                    if((i+j)%2==1){board[i][j].setBackground(walnutbrown);}
                    }
            }
            if(pieces[toX][toY] != null){           
                if("white".equals(pieces[toX][toY].color)){
                    whitepieces.remove(pieces[toX][toY]);
                }
                else{
                    blackpieces.remove(pieces[toX][toY]);
                }
            }   
            
            pieces[currentPiece.x][currentPiece.y] = null;
            currentPiece.x=toX;currentPiece.y=toY;
            pieces[toX][toY] = currentPiece;
            isCheck();
            switchPlayer();
        }
           
        else{
        if(pieces[toX][toY]!=null && (pieces[toX][toY].color).equals(currentPiece.color)){
                        for (int i=0;i<8;i++){
                for (int j=0; j<8; j++){

                    board[i][j].setBackground(Color.white);
                    if((i+j)%2==1){board[i][j].setBackground(walnutbrown);}
              
                    }
            }
            setCurrentPiece(pieces[toX][toY]);
            hasSelectedPiece = !hasSelectedPiece;}
        }
    }
    private void updateGame(){
        for(int i=0;i<8;i++){
            for(int j=0; j<8; j++){
                if((pieces[i][j])!=null){   
                    board[i][j].setIcon(pieces[i][j].icon);}
                else{board[i][j].setIcon(null);}
            }
            if(turn){setMessage("White is playing");}
            else{setMessage("Black is playing");}    
        }
    }
    private void setMessage(String mes){
        mess.setText(mes);
    }
    @Override
    public void actionPerformed(ActionEvent e){
        int[] pos = findButton(e.getSource());
        if(turn){
            if(!hasSelectedPiece){
                if(pieces[pos[0]][pos[1]]!=null&&"white".equals(pieces[pos[0]][pos[1]].color)){   
                    setCurrentPiece(pieces[pos[0]][pos[1]]);
                }        
            }
            else{move(pos[0],pos[1]); }} 
        
        if(!turn){
           
            if(hasSelectedPiece){
                if(pieces[pos[0]][pos[1]]!=null&&"black".equals(pieces[pos[0]][pos[1]].color)){
                    setCurrentPiece(pieces[pos[0]][pos[1]]);}
            }
            else{move(pos[0],pos[1]); }                 
        }
                    
        updateGame();
        
    }
    public static void main(String[] args){
        ChessBoard chessboard = new ChessBoard();
        chessboard.setSize(600,600);
        chessboard.setVisible(true);
        chessboard.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}