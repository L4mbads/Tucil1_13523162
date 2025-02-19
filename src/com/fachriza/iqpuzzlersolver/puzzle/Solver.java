package com.fachriza.iqpuzzlersolver.puzzle;

import java.util.List;
import java.util.Queue;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;

import com.fachriza.iqpuzzlersolver.lib.config.Config;
import com.fachriza.iqpuzzlersolver.puzzle.Board;
import com.fachriza.iqpuzzlersolver.puzzle.Block;

public class Solver {
    public enum SolverState {
        NOT_STARTED,
        SUCCESS,
        FAIL_NO_SOLUTION,
        FAIL_OVER_PIECE,
        FAIL_FINISHED
    }

    private Config config;
    private SolverState state = SolverState.NOT_STARTED;

    private Deque<Block> stack;
    private Deque<Block> placed;

    public Solver(Config config) {
        this.config = config;
    }

    public SolverState solve() {
        if (state != SolverState.NOT_STARTED) {
            return SolverState.FAIL_FINISHED;
        }

        stack = new ArrayDeque<Block>();
        placed = new ArrayDeque<Block>();

        for (Block block : config.getBlocks()) {
            stack.offer(block);
        }

        tryPlaceBlock();

        System.out.println(config.getBoard());
        return SolverState.SUCCESS;

    }

    private boolean tryPlaceBlock() {
        if (stack.isEmpty())
            return true;

        boolean hasPlaced = false;
        Board board = config.getBoard();
        Block block = stack.pop();

        for (int i = 0; i < config.getHeight(); i++) {
            for (int j = 0; j < config.getWidth(); j++) {
                System.out.println(String.valueOf(i) + ", " + String.valueOf(j));
                if (board.getElement(i, j) != 0) {
                    continue;
                }
                Block modified = block.getMirroredXY();
                if (board.placeBlock(i, j, block)) {
                    placed.push(block);
                    hasPlaced = true;
                    break;
                }
                if (board.placeBlock(i, j, modified)) {
                    placed.push(block);
                    hasPlaced = true;
                    break;
                }
                modified = block.getMirroredX();
                if (board.placeBlock(i, j, modified)) {
                    placed.push(block);
                    hasPlaced = true;
                    break;
                }
                modified = modified.getMirroredXY();
                if (board.placeBlock(i, j, modified)) {
                    placed.push(block);
                    hasPlaced = true;
                    break;
                }
                modified = block.getMirroredY();
                if (board.placeBlock(i, j, modified)) {
                    placed.push(block);
                    hasPlaced = true;
                    break;
                }
                modified = modified.getMirroredXY();
                if (board.placeBlock(i, j, modified)) {
                    placed.push(block);
                    hasPlaced = true;
                    break;
                }
                modified = block.getMirroredX().getMirroredY();
                if (board.placeBlock(i, j, modified)) {
                    placed.push(block);
                    hasPlaced = true;
                    break;
                }
                modified = modified.getMirroredXY();
                if (board.placeBlock(i, j, modified)) {
                    placed.push(block);
                    hasPlaced = true;
                    break;
                }

            }
            if (hasPlaced)
                break;
        }

        if (hasPlaced) {
            tryPlaceBlock();
        } else {
            stack.push(block);
        }

        return hasPlaced;
    }

}
