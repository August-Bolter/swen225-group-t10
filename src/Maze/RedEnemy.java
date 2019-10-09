package Maze;

public class RedEnemy extends Enemy {
    public RedEnemy(int row, int col, String direction) {
        super(row, col, direction);
    }

    public void interact() {
        main.getFrame().displayInfo("Watch out for Charizards!");
        main.restartLevel();
    }

    @Override
    public void onTick() {
        if (main.getTimeRemaining() % 3 == 0) {
            main.addEnemy(shoot());
        }
    }

    public Fireblast shoot(){
        Fireblast fb = new Fireblast(getRow(), getCol(), direction.toString(), main);
        fb.setCurrentPos(main.getLevelBoard().getBoard()[getRow()][getCol()]);
        return fb;
    }
}
