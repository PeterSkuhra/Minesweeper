package minesweeper.swingui;

import minesweeper.IUserInterface;
import minesweeper.core.Field;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;
import java.awt.event.KeyEvent;

public class SwingUI extends JFrame implements IUserInterface {

    private Field field;

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

    private JPanel playingField;

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
        setTitle("Minesweeper");
        setSize(300, 400);
        //pack();
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        createMenuBar();
        createStatusBar();
        createPlayingField();

        this.setVisible(true);
    }

    /**
     * Create menu bar in main window
     */
    private void createMenuBar() {
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
        playingField = new JPanel();

        playingField.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createEmptyBorder(
                                3, 5, 5, 5),
                        BorderFactory.createBevelBorder(
                                BevelBorder.LOWERED)
                        )
                );

        getContentPane().add(playingField, BorderLayout.CENTER);
    }

    /**
     *
     *
     * @param field field of mines and clues
     */
    @Override
    public void newGameStarted(Field field) {
        this.field = field;

        playingField.removeAll();
        playingField.setLayout(
                new GridLayout(field.getRowCount(), field.getColumnCount()));

        for (int i = 0; i < field.getRowCount(); ++i) {
            for (int j = 0; j < field.getColumnCount(); ++j) {
                playingField.add(new TileComponent(field.getTile(i, j), i, j));
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

        for (int i = 0; i < playingField.getComponentCount(); ++i) {
            tileComponent = (TileComponent) playingField.getComponent(i);
            tileComponent.updateStyle();
        }
    }

}
