package com.fachriza.iqpuzzlersolver.puzzle;

import java.text.DecimalFormat;
import java.util.ArrayDeque;
import java.util.Deque;

import com.fachriza.iqpuzzlersolver.lib.config.Config;

public class Solver {
    private final static DecimalFormat df = new DecimalFormat("0.0000");

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

    private int cases = 0;

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

        // start brute-force
        long startTime = System.nanoTime();
        tryPlaceBlock();
        long endTime = System.nanoTime();

        timeElapsedInNs = endTime - startTime;

        return state;

    }

    private boolean tryPlaceBlock() {
        // base cases
        if (state == SolverState.FAIL_OVER_PIECE) {
            return false;
        }

        Board board = config.getBoard();

        if (stack.isEmpty()) {
            if (board.isSolved()) {
                state = SolverState.SUCCESS;
            } else {
                // number of blocks "beads" should be equal to the number of grids
                state = SolverState.FAIL_LESS_PIECE;
            }
            // early return
            return true;
        }

        // recursion case
        Block block = stack.pop();
        boolean boardIsFull = true;

        for (int i = 0; i < config.getHeight(); i++) {
            for (int j = 0; j < config.getWidth(); j++) {

                if (board.getElement(j, i) != 0) {
                    continue;
                }

                // manually set the center ID because we removed it from the blocks coordinates
                board.setElement(j, i, block.getID());

                boardIsFull = false;

                for (int k = 0; k < block.getVariantsNum(); k++) {
                    if (board.placeBlock(j, i, block, k)) {
                        // increment cases count if placement was successful
                        cases++;
                        if (tryPlaceBlock())
                            return true;
                        board.removeBlock(j, i, block, k);
                    }
                }
                // manually remove the center
                board.setElement(j, i, (byte) 0);
            }
        }
        stack.push(block);
        if (boardIsFull) {
            // no available grid to even place the center of the block
            // early return
            state = SolverState.FAIL_OVER_PIECE;
            return true;
        }
        return false;
    }

    public Config getConfig() {
        return config;
    }

    public long getTimeElapsedInNs() {
        return timeElapsedInNs;
    }

    public int getCases() {
        return cases;
    }

    public Deque<Block> getStack() {
        return stack;
    }

    public String getResult() {
        StringBuilder sb = new StringBuilder();
        String NL = System.lineSeparator();

        switch (state) {
            case SolverState.SUCCESS:
                sb.append("SUCCESS");
                sb.append(NL);
                break;

            case SolverState.FAIL_NO_SOLUTION:
                sb.append("FAIL: No valid solution");
                sb.append(NL);
                break;

            case SolverState.FAIL_OVER_PIECE:
                sb.append("FAIL: Too much blocks");
                sb.append(NL);
                for (Block block : stack) {
                    sb.append("Block " + ((char) block.getID()) + " was never used");
                    sb.append(NL);
                }
                break;

            case SolverState.FAIL_LESS_PIECE:
                sb.append("FAIL: Need more blocks");
                break;

            default:
                break;
        }

        sb.append(NL);
        sb.append(config.getBoard());
        sb.append(NL);
        sb.append("Time : " + df.format(getTimeElapsedInNs() * 0.000001) + " ms");
        sb.append(NL);
        sb.append("Cases: " + getCases());
        sb.append(NL);

        return sb.toString();
    }

}
