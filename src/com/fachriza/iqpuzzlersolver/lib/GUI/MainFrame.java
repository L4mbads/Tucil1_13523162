package com.fachriza.iqpuzzlersolver.lib.GUI;

import javax.swing.*;

import com.fachriza.iqpuzzlersolver.lib.SaveHandler;
import com.fachriza.iqpuzzlersolver.lib.config.Config;
import com.fachriza.iqpuzzlersolver.puzzle.Game;
import com.fachriza.iqpuzzlersolver.puzzle.Solver.SolverState;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class MainFrame {
    private JFrame frame;
    private JButton selectFileButton, processFileButton;
    private JLabel statusLabel;
    private File selectedFile;
    private DrawPanel drawPanel;

    public MainFrame() {
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Exception e) {
            e.printStackTrace();
        }
        frame = new JFrame("IQPuzzlerSolver");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);
        frame.setLayout(new BorderLayout());

        JPanel topPanel = new JPanel();
        selectFileButton = new JButton("Select File");
        processFileButton = new JButton("Process File");
        processFileButton.setEnabled(false);
        statusLabel = new JLabel("No file selected.");

        topPanel.add(selectFileButton);
        topPanel.add(processFileButton);
        topPanel.add(statusLabel);

        drawPanel = new DrawPanel();

        frame.add(topPanel, BorderLayout.NORTH);
        frame.add(drawPanel, BorderLayout.CENTER);

        Path configDir = Paths.get("config");

        selectFileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser(configDir.toFile());
                int returnValue = fileChooser.showOpenDialog(frame);
                if (returnValue == JFileChooser.APPROVE_OPTION) {
                    selectedFile = fileChooser.getSelectedFile();
                    statusLabel.setText("Selected: " + selectedFile.getName());
                    processFileButton.setEnabled(true);
                }
            }
        });

        processFileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Thread(() -> processFile()).start();
            }
        });

        frame.setVisible(true);
    }

    private void processFile() {
        SwingUtilities.invokeLater(() -> statusLabel.setText("Processing..."));

        Game game;
        try (BufferedReader reader = new BufferedReader(new FileReader(selectedFile))) {
            game = new Game(null);
            game.readConfig(selectedFile.getName());
            game.start();
        } catch (Exception e) {
            SwingUtilities.invokeLater(() -> statusLabel.setText("Error processing file."));
            return;
        }

        drawPanel.setGame(game);
        if (game.getSolver().getState() == SolverState.SUCCESS) {
            SwingUtilities.invokeLater(
                    () -> statusLabel.setText(String.format("SUCCESS Time: %f ms, %d cases",
                            game.getSolver().getTimeElapsedInNs() * 1e-6, game.getSolver().getCases())));
        } else {
            final String code;
            switch (game.getSolver().getState()) {
                case SolverState.FAIL_LESS_PIECE:
                    code = "Need more blocks";
                    break;
                case SolverState.FAIL_OVER_PIECE:
                    code = "Too much blocks";
                    break;
                case SolverState.FAIL_NO_SOLUTION:
                    code = "No valid solution";
                    break;
                default:
                    code = "";
                    break;
            }
            SwingUtilities.invokeLater(
                    () -> statusLabel.setText(String.format("FAILED(%s) Time: %f ms, %d cases", code,
                            game.getSolver().getTimeElapsedInNs() * 1e-6, game.getSolver().getCases())));

        }
        SaveHandler.promptSave(new Scanner(System.in), game.getSolver());
    }

    private static class DrawPanel extends JPanel {
        private Game game = null;

        public void setGame(Game game) {
            this.game = game;
            repaint();
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g;
            if (game == null)
                return;
            Config config = game.getConfig();

            int height = getHeight() / config.getHeight();
            int width = getWidth() / config.getWidth();

            int blockSize = Math.min(height, width);
            int paddingX = getWidth() - blockSize * config.getWidth();
            int paddingY = getHeight() - blockSize * config.getHeight();
            int startX = paddingX / 2;
            int startY = paddingY / 2;
            Font font = new Font("Arial", Font.BOLD, 24);
            g2d.setFont(font);
            for (int i = 0; i < config.getHeight(); i++) {
                for (int j = 0; j < config.getWidth(); j++) {
                    int elem = config.getBoard().getElement(j, i);
                    if (elem < 65)
                        continue;
                    int sphereX = j * blockSize + startX;
                    int sphereY = i * blockSize + startY;
                    int diameter = blockSize + 1;

                    g2d.setColor(com.fachriza.iqpuzzlersolver.lib.Color.colorMap
                            .get(com.fachriza.iqpuzzlersolver.lib.Color.colorTable[elem - 65]));
                    g2d.fillOval(sphereX, sphereY, diameter, diameter);
                    if (elem == 65 || elem == 73 || elem == 81) {
                        g2d.setColor(Color.WHITE);
                    } else {
                        g2d.setColor(Color.BLACK);
                    }

                    FontMetrics fm = g2d.getFontMetrics();
                    int textWidth = fm.stringWidth(".");
                    int textHeight = fm.getAscent();

                    int textX = sphereX + (diameter - textWidth) / 2 - 5;
                    int textY = sphereY + (diameter + textHeight) / 2 - 5;

                    g2d.drawString(String.valueOf((char) elem), textX, textY);

                }
            }
        }
    }

    public void OpenGUI() {
        SwingUtilities.invokeLater(MainFrame::new);
    }
}
