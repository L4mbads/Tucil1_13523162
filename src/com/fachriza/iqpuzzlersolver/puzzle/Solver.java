package com.fachriza.iqpuzzlersolver.puzzle;

import java.util.ArrayDeque;
import java.util.Deque;

import com.fachriza.iqpuzzlersolver.lib.config.Config;

public class Solver {
    public enum SolverState {
        NOT_STARTED,
        SUCCESS,
        FAIL_NO_SOLUTION,
        FAIL_OVER_PIECE,
        FAIL_LESS_PIECE
    }

    private long timeElapsedInNs;
    private Config config;
    private SolverState state = SolverState.NOT_STARTED;

    private Deque<Block> stack = new ArrayDeque<Block>();

    public int cases = 0;

    public Solver(Config config) {
        this.config = config;

        for (Block block : config.getBlocks()) {
            stack.offer(block);
        }
    }

    public SolverState solve() {
        if (state != SolverState.NOT_STARTED) {
            return state;
        }
        state = SolverState.FAIL_NO_SOLUTION;

        long startTime = System.nanoTime();
        boolean hasSolution = tryPlaceBlock();
        long endTime = System.nanoTime();

        timeElapsedInNs = endTime - startTime;

        if (hasSolution)
            if (!config.getBoard().isSolved()) {
                state = SolverState.FAIL_LESS_PIECE;
            } else {
                state = SolverState.SUCCESS;
            }
        return state;

    }

    private boolean tryPlaceBlock() {
        if (state == SolverState.FAIL_OVER_PIECE) {
            return false;
        }
        if (stack.isEmpty()) {
            state = SolverState.SUCCESS;
            return true;
        }

        Board board = config.getBoard();
        Block block = stack.pop();
        boolean boardIsFull = true;

        for (int i = 0; i < config.getHeight(); i++) {
            for (int j = 0; j < config.getWidth(); j++) {

                if (board.getElement(j, i) != 0) {
                    continue;
                }
                boardIsFull = false;

                if (board.placeBlock(j, i, block)) {
                    cases++;
                    if (tryPlaceBlock())
                        return true;
                    board.removeBlock(j, i, block);
                }
                Block modified = block.getMirroredXY();
                if (board.placeBlock(j, i, modified)) {
                    cases++;
                    if (tryPlaceBlock())
                        return true;
                    board.removeBlock(j, i, modified);
                }
                modified = block.getMirroredX();
                if (board.placeBlock(j, i, modified)) {
                    cases++;
                    if (tryPlaceBlock())
                        return true;
                    board.removeBlock(j, i, modified);
                }
                modified = modified.getMirroredXY();
                if (board.placeBlock(j, i, modified)) {
                    cases++;
                    if (tryPlaceBlock())
                        return true;
                    board.removeBlock(j, i, modified);
                }
                modified = modified.getMirroredY();
                if (board.placeBlock(j, i, modified)) {
                    cases++;
                    if (tryPlaceBlock())
                        return true;
                    board.removeBlock(j, i, modified);
                }
                modified = modified.getMirroredXY();
                if (board.placeBlock(j, i, modified)) {
                    cases++;
                    if (tryPlaceBlock())
                        return true;
                    board.removeBlock(j, i, modified);
                }
                modified = block.getMirroredY();
                if (board.placeBlock(j, i, modified)) {
                    cases++;
                    if (tryPlaceBlock())
                        return true;
                    board.removeBlock(j, i, modified);
                }
                modified = modified.getMirroredXY();
                if (board.placeBlock(j, i, modified)) {
                    cases++;
                    if (tryPlaceBlock())
                        return true;
                    board.removeBlock(j, i, modified);
                }

            }
        }
        stack.push(block);
        if (boardIsFull) {
            state = SolverState.FAIL_OVER_PIECE;
        }
        return false;
    }

    public long getTimeElapsedInNs() {
        return timeElapsedInNs;
    }

}
