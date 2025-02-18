package com.lab;

import java.util.Scanner;
import java.io.IOException;
import java.io.InputStream;

public class Minesweeper {
    static char SAFE_CELL = '.';
    static char MINE_CELL = 'X' ;
    static int IS_SAFE = 0;
    static int IS_MINE = 1;
    int fieldX, fieldY;
    int[][] cells;
    String fieldFileName;
    int mineCount = 0; 

    public Minesweeper(String fieldFile) {
        this.fieldFileName = fieldFile;
        initFromFile(fieldFileName);
    }

    public Minesweeper(int fieldX, int fieldY) {
        this.fieldX = fieldX;
        this.fieldY = fieldY;
        this.cells = new int[fieldX][fieldY];
        for (int i=0; i<fieldX; i++) {
            for (int j=0; j<fieldY; j++) {
                cells[i][j] = IS_SAFE;
            }
        }
    }

    void displayField() {
       
        for (int i = 0; i < fieldX; i++) {
            for (int j = 0; j < fieldY; j++) {
             
                if (cells[i][j] == IS_MINE) {
                    System.out.print(MINE_CELL);
                } else {
                    System.out.print(SAFE_CELL);
                }
            }
            System.out.println();
        }
        
       
        System.out.println("Mines Left: " + mineCount);
    }

    void setMineCell(int x, int y) {
        if (cells[x][y] != IS_MINE) { 
            cells[x][y] = IS_MINE;
            mineCount++; }
    }

    void initFromFile(String mineFieldFile) {
        InputStream is = getClass().getClassLoader().getResourceAsStream(mineFieldFile);
        if (is == null) {
            System.out.println("File not found.");
            return;
        }
 
        Scanner scanner = new Scanner(is);
   
        fieldX = scanner.nextInt();
        fieldY = scanner.nextInt();
        cells = new int[fieldX][fieldY];
 

        for (int i = 0; i < fieldX; i++) {
            for (int j = 0; j < fieldY; j++) {
                cells[i][j] = IS_SAFE;
            }
        }
 
        scanner.nextLine();  
        int row = 0;
        while (scanner.hasNextLine() && row < fieldX) {
            String line = scanner.nextLine().trim();
            for (int col = 0; col < fieldY && col < line.length(); col++) {
                if (line.charAt(col) == MINE_CELL) {
                    cells[row][col] = IS_MINE;
                    mineCount++; 
                }
            }
            row++;
        }
        scanner.close();
    }
}
