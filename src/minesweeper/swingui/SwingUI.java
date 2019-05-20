package minesweeper.swingui;

import minesweeper.IUserInterface;
import minesweeper.Minesweeper;
import minesweeper.Settings;
import minesweeper.core.Field;
import minesweeper.core.GameState;
import minesweeper.core.Tile;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;
import java.awt.event.*;
import java.util.Formatter;

public class SwingUI extends JFrame implements IUserInterface {

    /**
     * Playing field.
     */
    private Field field;

    /**
     * Panel for all components.
     */
    private JPanel contentPanel;

    /**
     * Info panel.
     */
    private JPanel infoPanel;

    /**
     * Panel for playing field.
     */
    private JPanel fieldPanel;

    /**
     * Panel for reamining mines.
     */
    private JPanel remainingMinesPanel;

    /**
     * Remaining mines label.
     */
    private JLabel remainingMinesLabel;

    /**
     * Timer of game.
     */
    private Timer timer;

    /**
     * Panel for elapsed time.
     */
    private JPanel elapsedTimePanel;

    /**
     * Elapsed time label.
     */
    private JLabel elapsedTimeLabel;

    /**
     * Panel for new game button.
     */
    private JPanel newGameButtonPanel;

    /**
     * New game button.
     */
    private JButton newGameButton;

    /**
     * Menu bar.
     */
    private JMenuBar jMenuBar;

    /**
     * Game menu in menu bar.
     */
    private JMenu gameMenu;

    /**
     * Help menu in menu bar.
     */
    private JMenu helpMenu;

    /**
     * About menu item in help menu.
     */
    private JMenuItem aboutMenuItem;

    /**
     * New menu item in game menu.
     */
    private JMenuItem newMenuItem;

    /**
     * Difficulty group in game menu.
     */
    private ButtonGroup difficultyGroup;

    /**
     * Beginner radio button in game menu.
     */
    private JRadioButtonMenuItem beginnerRadioButtonMenuItem;

    /**
     * Intermediate radio button in game menu.
     */
    private JRadioButtonMenuItem intermediateRadioButtonMenuItem;

    /**
     * Expert radio button in game menu.
     */
    private JRadioButtonMenuItem expertRadioButtonMenuItem;

    /**
     * Exit game item in game menu.
     */
    private JMenuItem exitMenuItem;

    /**
     * Progress bar of game.
     */
    private JProgressBar progressBar;


    /**
     * Constructor.
     */
    public SwingUI() {
        initUI();
    }

    /**
     * Init all elements for GUI.
     */
    private void initUI() {
        contentPanel = new JPanel();
        contentPanel.setLayout(new BorderLayout());
        contentPanel.setBorder(
                BorderFactory.createBevelBorder(BevelBorder.RAISED));

        getContentPane().add(contentPanel, BorderLayout.CENTER);

        setTitle("Minesweeper");
        setIconImage(new ImageIcon(
                getClass().getResource("/img/logo.gif")).getImage());

        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        createMenuBar();
        createProgressBar();
        createPlayingField();
        createInfoPanel();

        pack();
        setResizable(false);
        setVisible(true);
    }

    private void createMenuBar() {
        jMenuBar = new JMenuBar();

        createGameMenu();
        createHelpMenu();

        setJMenuBar(jMenuBar);
    }

    /**
     * Creates game menu in menu bar.
     */
    private void createGameMenu() {
        gameMenu = new JMenu("Game");

        newMenuItem = new JMenuItem("New");
        newMenuItem.addActionListener(actionEvent -> {
            Minesweeper.getInstance().newGame();
        });

        createDifficultyGroup();

        exitMenuItem = new JMenuItem("Exit");
        exitMenuItem.setToolTipText("Exit application");
        exitMenuItem.addActionListener(actionEvent -> {
            System.exit(0);
        });

        gameMenu.add(newMenuItem);
        gameMenu.addSeparator();
        gameMenu.add(beginnerRadioButtonMenuItem);
        gameMenu.add(intermediateRadioButtonMenuItem);
        gameMenu.add(expertRadioButtonMenuItem);
        gameMenu.addSeparator();
        gameMenu.add(exitMenuItem);

        jMenuBar.add(gameMenu);
    }

    /**
     * Creates help menu in menu bar.
     */
    private void createHelpMenu() {
        helpMenu = new JMenu("Help");

        aboutMenuItem = new JMenuItem("About");

        String about =
                "Minesweeper 1.0\n" +
                "Peter Skuhra\n" +
                "AIA DKM\n" +
                "OOP in Java\n" +
                "2018/2019";

        aboutMenuItem.addActionListener(actionEvent -> {
            JOptionPane.showMessageDialog(
                    null,
                    about,
                    "About",
                    JOptionPane.INFORMATION_MESSAGE);
        });

        helpMenu.add(aboutMenuItem);
        jMenuBar.add(helpMenu);
    }

    /**
     * Creates difficulty group in menu.
     */
    private void createDifficultyGroup() {
        difficultyGroup = new ButtonGroup();

        beginnerRadioButtonMenuItem =
                new JRadioButtonMenuItem("Beginner");
        intermediateRadioButtonMenuItem =
                new JRadioButtonMenuItem("Intermediate");
        expertRadioButtonMenuItem =
                new JRadioButtonMenuItem("Expert");

        difficultyGroup.add(beginnerRadioButtonMenuItem);
        difficultyGroup.add(intermediateRadioButtonMenuItem);
        difficultyGroup.add(expertRadioButtonMenuItem);

        if (Minesweeper.getInstance().getSetting().equals(Settings.BEGINNER)) {
            beginnerRadioButtonMenuItem.setSelected(true);
        }
        else if (Minesweeper.getInstance().getSetting().equals(Settings.INTERMEDIATE)) {
            intermediateRadioButtonMenuItem.setSelected(true);
        }
        else if ((Minesweeper.getInstance().getSetting().equals(Settings.EXPERT))) {
            expertRadioButtonMenuItem.setSelected(true);
        }

        beginnerRadioButtonMenuItem.addItemListener(itemEvent -> {
            if (itemEvent.getStateChange() == ItemEvent.SELECTED) {
                Minesweeper.getInstance().setSetting(Settings.BEGINNER);
                Minesweeper.getInstance().newGame();
            }
        });

        intermediateRadioButtonMenuItem.addItemListener(itemEvent -> {
            if (itemEvent.getStateChange() == ItemEvent.SELECTED) {
                Minesweeper.getInstance().setSetting(Settings.INTERMEDIATE);
                Minesweeper.getInstance().newGame();
            }
        });

        expertRadioButtonMenuItem.addItemListener(itemEvent -> {
            if (itemEvent.getStateChange() == ItemEvent.SELECTED) {
                Minesweeper.getInstance().setSetting(Settings.EXPERT);
                Minesweeper.getInstance().newGame();
            }
        });
    }

    /**
     * Creates playing field.
     */
    private void createPlayingField() {
        fieldPanel = new JPanel();

        fieldPanel.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createEmptyBorder(
                                5, 5, 5, 5),
                        BorderFactory.createBevelBorder(
                                BevelBorder.LOWERED)
                )
        );

        contentPanel.add(fieldPanel, BorderLayout.CENTER);
    }

    /**
     * Creates info panel.
     */
    private void createInfoPanel() {
        infoPanel = new JPanel();
        infoPanel.setLayout(new BorderLayout());
        infoPanel.setBorder(javax.swing.BorderFactory.createCompoundBorder(
                javax.swing.BorderFactory.createEmptyBorder(
                        5, 5, 5, 5),
                javax.swing.BorderFactory.createBevelBorder(
                        javax.swing.border.BevelBorder.LOWERED)));

        contentPanel.add(infoPanel, BorderLayout.NORTH);

        createRemainingMinesPanel();
        createElapsedTimePanel();
        createNewGameButton();
    }

    /**
     * Creates remaining mines panel.
     */
    private void createRemainingMinesPanel() {
        remainingMinesPanel = new JPanel();
        remainingMinesPanel.setLayout(new BorderLayout());
        remainingMinesPanel.setBorder(
                BorderFactory.createBevelBorder(BevelBorder.LOWERED));
        infoPanel.add(remainingMinesPanel, BorderLayout.WEST);

        remainingMinesLabel = new JLabel();
        remainingMinesLabel.setBackground(Color.black);
        remainingMinesLabel.setFont(new Font("DialogInput", 1, 24));
        remainingMinesLabel.setForeground(Color.GREEN);
        remainingMinesLabel.setHorizontalAlignment(SwingConstants.CENTER);
        remainingMinesLabel.setText("888");
        remainingMinesLabel.setMaximumSize(new Dimension(80, 30));
        remainingMinesLabel.setMinimumSize(new Dimension(80, 30));
        remainingMinesLabel.setOpaque(true);
        remainingMinesLabel.setPreferredSize(new Dimension(80, 30));

        remainingMinesPanel.add(remainingMinesLabel, BorderLayout.CENTER);
    }

    /**
     * Updates remaining mines label.
     */
    private void updateRemainingMinesLabel() {
        StringBuilder stringBuilder = new StringBuilder();
        new Formatter(stringBuilder).format(
                "%03d",
                field.getRemainingMineCount());

        remainingMinesLabel.setText(stringBuilder.toString());
    }

    /**
     * Creates elapsed time panel.
     */
    private void createElapsedTimePanel() {
        initTimer();

        elapsedTimePanel = new JPanel();
        elapsedTimePanel.setLayout(new BorderLayout());
        elapsedTimePanel.setBorder(BorderFactory.createBevelBorder(
                BevelBorder.LOWERED));
        infoPanel.add(elapsedTimePanel, BorderLayout.EAST);

        elapsedTimeLabel = new JLabel();
        elapsedTimeLabel.setBackground(Color.black);
        elapsedTimeLabel.setFont(new Font("DialogInput", 1, 24));
        elapsedTimeLabel.setForeground(Color.GREEN);
        elapsedTimeLabel.setHorizontalAlignment(SwingConstants.CENTER);
        elapsedTimeLabel.setText("88:88");
        elapsedTimeLabel.setMaximumSize(new Dimension(80, 30));
        elapsedTimeLabel.setMinimumSize(new Dimension(80, 30));
        elapsedTimeLabel.setOpaque(true);
        elapsedTimeLabel.setPreferredSize(new Dimension(80, 30));

        elapsedTimePanel.add(elapsedTimeLabel, BorderLayout.CENTER);
    }

    /**
     * Inits timer.
     */
    private void initTimer() {
        ActionListener actionListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if (field.getState() == GameState.PLAYING) {
                    updateElapsedTimeLabel();
                }
            }
        };

        int delay = 100;
        timer = new Timer(delay, actionListener);
    }

    /**
     * Updates elapsed time label.
     */
    private void updateElapsedTimeLabel() {
        StringBuilder stringBuilder = new StringBuilder();

        int rawSeconds = Minesweeper.getInstance().getPlayingSeconds();
        int minutes = rawSeconds / 60;
        int seconds = rawSeconds % 60;

        new Formatter(stringBuilder).format("%02d:%02d", minutes, seconds);

        elapsedTimeLabel.setText(stringBuilder.toString());
    }

    /**
     * Creates new game button.
     */
    private void createNewGameButton() {
        newGameButtonPanel = new JPanel();
        newGameButtonPanel.setLayout(new BorderLayout());
        newGameButtonPanel.setBorder(BorderFactory.createBevelBorder(
                BevelBorder.LOWERED));
        infoPanel.add(newGameButtonPanel, BorderLayout.CENTER);

        newGameButton = new JButton();
        newGameButton.setIcon(
                new ImageIcon(getClass().getResource("/img/smile.gif")));
        newGameButton.setFocusPainted(false);
        newGameButton.setFocusable(false);
        newGameButton.setMargin(new Insets(2, 2, 2, 2));
        newGameButton.setPreferredSize(new Dimension(50, 50));

        newGameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                Minesweeper.getInstance().newGame();
            }
        });

        newGameButtonPanel.add(newGameButton, BorderLayout.CENTER);
    }

    /**
     * Creates progress bar.
     */
    private void createProgressBar() {
        progressBar = new JProgressBar();
        progressBar.setBorder(BorderFactory.createEtchedBorder());
        add(progressBar, BorderLayout.SOUTH);
        progressBar.setStringPainted(true);
    }

    /**
     * Updates progress bar.
     */
    private void updateProgressBar() {
        int allTilesCount = field.getRowCount() * field.getColumnCount();
        int clearTilesCount = allTilesCount - field.getMineCount();
        int openTilesCount = field.getNumberOf(Tile.State.OPEN);

        int openTilesPercent = (openTilesCount * 100) / clearTilesCount;

        progressBar.setValue(openTilesPercent);
    }

    /**
     * Creates mouse listener for playing field.
     *
     * @return mouse listener for playing field
     */
    private MouseListener createFieldMouseListener() {
        MouseListener mouseListener = new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                if (field.getState() == GameState.PLAYING) {
                    TileComponent tileComponent;

                    if (SwingUtilities.isLeftMouseButton(mouseEvent)) {
                        tileComponent = (TileComponent) mouseEvent.getSource();
                        field.openTile(
                                tileComponent.getRow(),
                                tileComponent.getColumn());
                        update();
                    }

                    if (SwingUtilities.isRightMouseButton(mouseEvent)) {
                        tileComponent = (TileComponent) mouseEvent.getSource();
                        field.markTile(
                                tileComponent.getRow(),
                                tileComponent.getColumn());
                        update();
                    }

                    if (field.getState() == GameState.FAILED) {
                        JOptionPane.showMessageDialog(
                                null,
                                "You lose!",
                                "Defeat",
                                JOptionPane.INFORMATION_MESSAGE);

                        Minesweeper.getInstance().newGame();
                    }

                    else if (field.getState() == GameState.SOLVED) {
                        JOptionPane.showMessageDialog(
                                null,
                                "You win",
                                "Victory",
                                JOptionPane.INFORMATION_MESSAGE);
                        Minesweeper.getInstance().newGame();
                    }
                }
            }

            @Override
            public void mousePressed(MouseEvent mouseEvent) {

            }

            @Override
            public void mouseReleased(MouseEvent mouseEvent) {

            }

            @Override
            public void mouseEntered(MouseEvent mouseEvent) {

            }

            @Override
            public void mouseExited(MouseEvent mouseEvent) {

            }
        };

        return mouseListener;
    }

    /**
     * Starts new game.
     *
     * @param field field of mines and clues
     */
    @Override
    public void newGameStarted(Field field) {
        this.field = field;

        fieldPanel.removeAll();
        fieldPanel.setLayout(
                new GridLayout(field.getRowCount(), field.getColumnCount()));

        for (int i = 0; i < field.getRowCount(); ++i) {
            for (int j = 0; j < field.getColumnCount(); ++j) {
                TileComponent tileComponent =
                        new TileComponent(field.getTile(i, j), i, j);
                fieldPanel.add(tileComponent);
                tileComponent.addMouseListener(createFieldMouseListener());
            }
        }

        update();
        pack();

        timer.start();
    }

    /**
     * Updates GUI.
     */
    @Override
    public void update() {
        TileComponent tileComponent;

        for (int i = 0; i < fieldPanel.getComponentCount(); ++i) {
            tileComponent = (TileComponent) fieldPanel.getComponent(i);
            tileComponent.updateStyle();

            if (field.getState() == GameState.FAILED) {
                tileComponent.updateFailedStyle();
            }
            else if (field.getState() == GameState.SOLVED) {
                tileComponent.updateSolvedStyle();
            }
        }

        updateRemainingMinesLabel();
        updateElapsedTimeLabel();
        updateProgressBar();
    }
}
