package spa.simone.randomgrid;

public class SubGrid {

    public enum Direction {
        VERTICAL, HORIZONTAL
    }
    private Element[][] grid;
    private int[] counter;

    public SubGrid() {
        grid = new Element[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                grid[i][j] = new Element();
            }
        }
        counter = new int[9];
        for (int i = 0; i < 9; i++) {
            counter[i] = 9;
        }
    }

    private boolean hasOnlyOne(int number) {
        return counter[number - 1] == 1;
    }
    
    private boolean isAssigned(int number) {
        return counter[number - 1] == 0;
    }
    
    private void remove(int i, int j, int number) {
        grid[i][j].remove(number);
        counter[number - 1]--;
    }

    public boolean hasConflicts(int number, int index, Direction direction) {
        if (isAssigned(number)) {
            return false;
        }
        index = index % 3;
        if (direction.equals(Direction.VERTICAL)) {
            for (int i = 0; i < 3; i++) {
                if (hasOnlyOne(number)) {
                    if (!grid[i][index].isAssigned() && grid[i][index].contains(number)) {
                        return true;
                    }
                } else {
                    if (!grid[i][index].isAssigned() && grid[i][index].contains(number)) {
                        remove(i, index, number);
                    }
                }
            }
        } else {
            for (int i = 0; i < 3; i++) {
                if (hasOnlyOne(number)) {
                    if (!grid[index][i].isAssigned() && grid[index][i].contains(number)) {
                        return true;
                    }
                } else {
                    if (!grid[index][i].isAssigned() && grid[index][i].contains(number)) {
                        remove(index, i, number);
                    }
                }
            }
        }
        return false;
    }
}