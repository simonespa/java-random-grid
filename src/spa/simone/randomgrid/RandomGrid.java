package spa.simone.randomgrid;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.Set;

/**
 *
 * @author Simone Spaccarotella
 */
public class RandomGrid {

    private Random random;
    private int[][] grid;
    private SubGrid[][] subGrid;
    /*
    private List<Integer> indexes;
    private List<Integer> numbers;
    private int count;
    */

    public RandomGrid() {
        random = new Random(System.currentTimeMillis());
        /*
        indexes = new LinkedList<>();
        for (int i = 0; i < 81; i++) {
            indexes.add(i);
        }
        numbers = new ArrayList<>(9);
        for (int i = 1; i <= 9; i++) {
            numbers.add(i);
        }
        Collections.shuffle(indexes, random);
        */
        grid = new int[9][9];
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                grid[i][j] = 0;
            }
        }
        subGrid = new SubGrid[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                subGrid[i][j] = new SubGrid();
            }
        }
    }

    public void initGrid(int times) {
        // Per ogni tentativo
        for (int count = 0; count < times; count++) {
            //resetNumber();
            boolean isNotAssigned = true;
            // Finché non è stato assegnato il "count-esimo" numero
            while (isNotAssigned) {
                // Acquisisce un indice casuale
                int index = newIndex();
                // Trasforma l'indice in una coppia ordinata
                int rowIndex = getRow(index);
                int columnIndex = getColumn(index);
                // Finché la cella resta vuota
                System.out.println("Grid[" + rowIndex + "][" + columnIndex + "] -> " + grid[rowIndex][columnIndex]);
                for (int i = 0; i < 9 && isEmpty(rowIndex, columnIndex); i++) {
                    // scegli un numero casuale
                    int number = newNumber();
                    System.out.println("Number " + (i+1) + ": " + number);
                    if (noConflictInRow(rowIndex, number)
                            && noConflictInColumn(columnIndex, number)
                            && noConflictInSubgrid(rowIndex, columnIndex, number)
                            && noConflictInAdjacentSubgrids(rowIndex, columnIndex, number)) {
                        
                        grid[rowIndex][columnIndex] = number;
                        isNotAssigned = false;
                        System.out.println("Grid[" + rowIndex + "][" + columnIndex + "] -> " + grid[rowIndex][columnIndex]);
                        System.out.println("");
                        
                    }
                }
            }
        }
    }

    private boolean noConflictInAdjacentSubgrids(int rowIndex, int columnIndex, int number) {
        int r = rowIndex / 3;
        int c = columnIndex / 3;
        int rIndex;
        int cIndex;
        // serve a trovare le sotto griglie adiacenti (modulo 3) sia lungo la colonna che
        // lungo la riga.
        for (int i = 1; i <= 2; i++) {
            rIndex = (r + i) % 3;
            cIndex = (c + i) % 3;
            if (subGrid[rIndex][c].hasConflicts(number, columnIndex, SubGrid.Direction.VERTICAL)
                    || subGrid[r][cIndex].hasConflicts(number, rowIndex, SubGrid.Direction.HORIZONTAL)) {
                return false;
            }
        }
        return true;
    }

    /*
     * Verifica che non ci siano duplicati nella stessa regione.
     */
    private boolean noConflictInSubgrid(int rowIndex, int columnIndex, int number) {
        /*
         * Siccome ci sono 9 sotto matrici, disposte su 3 righe e 3 colonne, questo metodo
         * calcola l'indice di riga e di colonna della sotto matrice.
         */
        int r = rowIndex / 3;
        int c = columnIndex / 3;
        // questo indice diventa il moltiplicatore, per calcolare gli indici di riga
        // e di colonna reali della cella.
        int iStart = r * 3;
        int jStart = c * 3;
        for (int i = iStart; i < iStart + 3; i++) {
            for (int j = jStart; j < jStart + 3; j++) {
                if (grid[i][j] == number) {
                    return false;
                }
            }
        }
        return true;
    }

    public void printGrid() {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (grid[i][j] == 0) {
                    System.out.print("_  ");
                } else {
                    System.out.print(grid[i][j] + "  ");
                }
            }
            System.out.println("");
        }
    }

    /*
     * Restituisce un numero casuale compreso tra 0 e 80 (estremi inclusi).
     * Questo numero identifica univocamente le 81 celle della griglia.
     * Attenzione, il metodo restituisce un numero diverso ad ogni chiamata.
     */
    private int newIndex() {
        //return indexes.remove(0);
        return random.nextInt(81);
    }

    /*
     * Restituisce un numero casuale compreso tra 1 e 9 (estremi inclusi). Questo
     * numero rappresenta il simbolo che verrà settato nelle celle della griglia.
     * Attenzione, il metodo restituisce un numero diverso ad ogni chiamata.
     */
    private int newNumber() {
        /*
        int n = numbers.get(count);
        count++;
        return n;
        */
        return random.nextInt(9) + 1;
    }
/*
    private void resetNumber() {
        count = 0;
        Collections.shuffle(numbers, random);
    }
    */

    /*
     * Verifica se la cella non è stata assegnata. Restituisce true se è vuota.
     */
    private boolean isEmpty(int i, int j) {
        return grid[i][j] == 0;
    }

    /*
     * Calcola l'indice di riga dal numero intero.
     */
    private int getRow(int number) {
        return number / 9;
    }

    /*
     * Calcola l'indice di colonna dal numero intero.
     */
    private int getColumn(int number) {
        return number % 9;
    }

    /*
     * Verifica che non ci siano duplicati nella stessa riga.
     */
    private boolean noConflictInRow(int rowIndex, int number) {
        for (int i = 0; i < 9; i++) {
            if (grid[rowIndex][i] == number) {
                return false;
            }
        }
        return true;
    }

    /*
     * Verifica che non ci siano duplicati nella stessa colonna.
     */
    private boolean noConflictInColumn(int columnIndex, int number) {
        for (int i = 0; i < 9; i++) {
            if (grid[i][columnIndex] == number) {
                return false;
            }
        }
        return true;
    }
}
