package com.fachriza.iqpuzzlersolver.lib.config;

import java.util.List;
import java.util.ArrayList;

import com.fachriza.iqpuzzlersolver.puzzle.Block;
import com.fachriza.iqpuzzlersolver.puzzle.Board;

public class Config {
    private int boardHeight;
    private int boardWidth;
    private int blockNum;

    private Block[] blocks;
    private Board board;

    public void setBoardHeight(int N) {
        boardHeight = N;
    }

    public void setBoardWidth(int M) {
        boardWidth = M;
    }

    public void setBlockNum(int val) {
        blockNum = val;
    }

    public void build() {
        board = new Board(boardHeight, boardWidth);
        blocks = new Block[blockNum];
    }

    public Board getBoard() {
        return board;
    }

    public Block[] getBlocks() {
        return blocks;
    }

    public String toString() {
        return String.join(", ", String.valueOf(boardHeight), String.valueOf(boardWidth), String.valueOf(blockNum));
    }
}
