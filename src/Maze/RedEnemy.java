package Maze;

public class RedEnemy extends Enemy {
    public RedEnemy(int row, int col) {
        super(row, col);
    }

    public void interact() {
        main.getFrame().displayInfo("Game over you lil bitch ass");
    }

    public void shoot(){
        Fireblast fb = new Fireblast(getRow(), getCol());
        fb.setCurrentPos(main.getLevelBoard().getBoard()[getRow()][getCol()]);
        fb.moveBlast();
    }


}