package com.fachriza.iqpuzzlersolver.lib.config;

import com.fachriza.iqpuzzlersolver.puzzle.Block;
import com.fachriza.iqpuzzlersolver.puzzle.Board;

public class Config {
    private int boardHeight;
    private int boardWidth;
    private int blockNum;
    private int blockNeff = 0;

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

    public int getHeight() {
        return boardHeight;
    }

    public int getWidth() {
        return boardWidth;
    }

    public void addBlock(Block block) {
        blocks[blockNeff] = block;
        blockNeff++;
    }

    public int getBlockNum() {
        return blockNum;
    }

    public String toString() {
        return String.join(", ", String.valueOf(boardHeight), String.valueOf(boardWidth), String.valueOf(blockNum));
    }
}
