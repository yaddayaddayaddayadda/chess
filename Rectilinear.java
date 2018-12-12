public class Rectilinear {
    private final int a;
    private final int b;
    private int fromX, toX, fromY, toY;
    public int[][] order;
    private static int steps;
    public Rectilinear(int fromX, int toX, int fromY, int toY){
        steps = -1+Math.max(Math.abs(fromX-toX),Math.abs(fromY-toY));
        order = new int[steps][2];
        this.fromX=fromX; this.toX = toX; this.fromY = fromY; this.toY = toY;
        a = (int) Math.signum(toX-fromX); b = (int) Math.signum(toY-fromY);
        }
        int[][] getOrder(){
            for(int i = 0; i<steps; i++){
                order[i][0] = fromX+(i+1)*a;
                order[i][1] = fromY + (i+1)*b;
            }
            return order;
        }
}
