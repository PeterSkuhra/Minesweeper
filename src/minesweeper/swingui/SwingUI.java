package minesweeper.swingui;

import minesweeper.IUserInterface;
import minesweeper.Minesweeper;
import minesweeper.Settings;
import minesweeper.core.Field;
import minesweeper.core.GameState;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;
import java.awt.event.*;

public class SwingUI extends JFrame implements IUserInterface {

    private Field field;

    private JPanel contentPanel;
    private JPanel infoPanel;
    private JPanel fieldPanel;

    private JPanel remainingMinesPanel;
    private JLabel remainingMinesLabel;
    private JPanel elapsedTimePanel;
    private JLabel elapsedTimeLabel;
    private JPanel newGameButtonPanel;
    private JButton newGameButton;

    /**************************************************************************
     * MENU BAR
     *************************************************************************/
    private JMenuBar jMenuBar;
    private JMenu gameMenu;

    private JMenuItem newMenuItem;

    private ButtonGroup difficultyGroup;
    private JRadioButtonMenuItem beginnerRadioButtonMenuItem;
    private JRadioButtonMenuItem intermediateRadioButtonMenuItem;
    private JRadioButtonMenuItem expertRadioButtonMenuItem;

    private JMenuItem bestTimesMenuItem;
    private JMenuItem exitMenuItem;

    /**************************************************************************
     * STATUS BAR
     *************************************************************************/
    private JLabel statusBar;



    /**
     * Constructor
     */
    public SwingUI() {
        initUI();

    }

    /**
     * Init all elements for GUI
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

        createGameMenuBar();
        createStatusBar();
        createPlayingField();
        createInfoPanel();

        pack();
        setResizable(false);
        setVisible(true);
    }

    /**
     * Create menu bar in main window
     */
    private void createGameMenuBar() {
        jMenuBar = new JMenuBar();

        gameMenu = new JMenu("Game");
        gameMenu.setMnemonic(KeyEvent.VK_F);

        newMenuItem = new JMenuItem("New");
        newMenuItem.setMnemonic(KeyEvent.VK_F);

        difficultyGroup = new ButtonGroup();
        beginnerRadioButtonMenuItem =
                new JRadioButtonMenuItem("Beginner");
        beginnerRadioButtonMenuItem.setSelected(true);
        intermediateRadioButtonMenuItem =
                new JRadioButtonMenuItem("Intermediate");
        expertRadioButtonMenuItem =
                new JRadioButtonMenuItem("Expert");

        bestTimesMenuItem = new JMenuItem("Best times");
        bestTimesMenuItem.setMnemonic(KeyEvent.VK_F);

        exitMenuItem = new JMenuItem("Exit");
        exitMenuItem.setMnemonic(KeyEvent.VK_F);
        exitMenuItem.setToolTipText("Exit application");
        exitMenuItem.addActionListener((event) -> System.exit(0));

        gameMenu.add(newMenuItem);
        gameMenu.addSeparator();
        gameMenu.add(beginnerRadioButtonMenuItem);
        gameMenu.add(intermediateRadioButtonMenuItem);
        gameMenu.add(expertRadioButtonMenuItem);
        gameMenu.addSeparator();
        gameMenu.add(bestTimesMenuItem);
        gameMenu.addSeparator();
        gameMenu.add(exitMenuItem);

        difficultyGroup.add(beginnerRadioButtonMenuItem);
        difficultyGroup.add(intermediateRadioButtonMenuItem);
        difficultyGroup.add(expertRadioButtonMenuItem);

        jMenuBar.add(gameMenu);
        setJMenuBar(jMenuBar);

        initDifficultyGroup();
    }

    /**
     *
     */
    private void initDifficultyGroup() {
        if (Minesweeper.getInstance().getSetting().equals(Settings.BEGINNER)) {
            beginnerRadioButtonMenuItem.setSelected(true);
        }
        else if (Minesweeper.getInstance().getSetting().equals(Settings.INTERMEDIATE)) {
            intermediateRadioButtonMenuItem.setSelected(true);
        }
        else if ((Minesweeper.getInstance().getSetting().equals(Settings.EXPERT))) {
            expertRadioButtonMenuItem.setSelected(true);
        }
    }

    /**
     *
     */
    private void createStatusBar() {
        statusBar = new JLabel("Remaining mines:");
        statusBar.setBorder(BorderFactory.createEtchedBorder());
        add(statusBar, BorderLayout.SOUTH);
    }

    /**
     *
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
     *
     */
    private void createInfoPanel() {
        infoPanel = new JPanel();
        infoPanel.setLayout(new BorderLayout());
        infoPanel.setBorder(javax.swing.BorderFactory.createCompoundBorder(
                javax.swing.BorderFactory.createEmptyBorder(
                        5,5,5,5),
                javax.swing.BorderFactory.createBevelBorder(
                        javax.swing.border.BevelBorder.LOWERED)));

        contentPanel.add(infoPanel, BorderLayout.NORTH);

        createRemainingMinesPanel();
        createElapsedTimePanel();
        createNewGameButton();
    }

    /**
     *
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
        remainingMinesLabel.setMaximumSize(new Dimension(50, 30));
        remainingMinesLabel.setMinimumSize(new Dimension(50, 30));
        remainingMinesLabel.setOpaque(true);
        remainingMinesLabel.setPreferredSize(new Dimension(50, 30));

        remainingMinesPanel.add(remainingMinesLabel, BorderLayout.CENTER);
    }

    /**
     *
     */
    private void createElapsedTimePanel() {
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
        elapsedTimeLabel.setText("888");
        elapsedTimeLabel.setMaximumSize(new Dimension(50, 30));
        elapsedTimeLabel.setMinimumSize(new Dimension(50, 30));
        elapsedTimeLabel.setOpaque(true);
        elapsedTimeLabel.setPreferredSize(new Dimension(50, 30));

        elapsedTimePanel.add(elapsedTimeLabel, BorderLayout.CENTER);
    }

    /**
     *
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
        newGameButton.setMargin(new Insets(2,2,2,2));
        newGameButton.setMaximumSize(new Dimension(50, 50));
        newGameButton.setMinimumSize(new Dimension(50, 50));
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
     *
     *
     * @return
     */
    private MouseListener createMouseListener() {
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
                    }

                    else if (field.getState() == GameState.SOLVED) {
                        JOptionPane.showMessageDialog(
                                null,
                                "You win",
                                "Victory",
                                JOptionPane.INFORMATION_MESSAGE);
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
     *
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
                tileComponent.addMouseListener(createMouseListener());
            }
        }

        update();
        pack();
    }

    /**
     *
     */
    @Override
    public void update() {
        TileComponent tileComponent;

        for (int i = 0; i < fieldPanel.getComponentCount(); ++i) {
            tileComponent = (TileComponent) fieldPanel.getComponent(i);
            tileComponent.updateStyle();
        }
    }

}
