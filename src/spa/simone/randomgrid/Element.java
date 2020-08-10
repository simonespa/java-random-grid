package spa.simone.randomgrid;

public class Element {

    private boolean[] candidates;
    private int assigned;

    public Element() {
        candidates = new boolean[9];
        for (int i = 0; i < 9; i++) {
            candidates[i] = true;
        }
        assigned = 0;
    }

    public boolean contains(int number) {
        return candidates[number - 1];
    }

    public void remove(int number) {
        candidates[number - 1] = false;
    }
    
    public void assignCell(int number) {
        this.assigned = number;
    }
    
    public boolean isAssigned() {
        return assigned == 0;
    }
    
}