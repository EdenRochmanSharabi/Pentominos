/**
 * @author Department of Data Science and Knowledge Engineering (DKE)
 * @version 2022.0
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.BoxLayout;
import java.awt.geom.Rectangle2D;


/**
 * This class takes care of all the graphics to display a certain state.
 */
   
public class UI extends JPanel //implements KeyListener
{
    /** 
     * Variables to make a visual space to see the tetris game.
     * JFrame is a frame variable.
     * JLabel are labels variables.
     * JButton are button variables.
     */
    private JFrame window; 
    private int[][] state;
    private int size;
    private JLabel label;
    private JLabel label2;
    String text = "TETRIS";
    JPanel buttonPane = new JPanel();
    public static JButton button;
    public static JButton pauseButton;
    public static JButton resetButton;
    public static JTextArea displayaArea;
    JTextField typingSpace;
    
  
/*
    public int keysToInt(int i) {
        switch (keyCode) {
            case KeyEvent.VK_LEFT: // left key <--
                return 0;
            case KeyEvent.VK_RIGHT: // right key -->
                return 1;
            case 26:
                return 2;
            case 103:
                return 3;
            case 'f':
                return 4;
            case 33:
                return 5;
            }
            return -1;
    }
    */
    /**
     * Listens to input from User
     */
    KeyListener listener = new KeyListener(){
        public void keyTyped(KeyEvent e) {
        }
        public void keyPressed(KeyEvent e) {
            System.out.println("ey");
            System.out.println("The key Pressed was: " + e.getKeyCode());
            Game.keyinput = e.getKeyCode();
            Game.interrupt = true;
        }
        public void keyReleased(KeyEvent e) {
        }
    };
    /**
     * Constructor for the GUI. Sets everything up
     * @param x x position of the GUI
     * @param y y position of the GUI
     * @param _size size of the GUI
     */

    /**
     * All these are labels for score purpose.
     * The creation of button; play, pause, reset.
     */
    public UI (int x, int y, int size) 
    {
        displayaArea = new JTextArea(); //the area for the keyboard to listen gets focused by the main function
        displayaArea.addKeyListener(listener);
        displayaArea.setEditable(false);
        label = new JLabel("Score: " + 0);
        label.setFont(new Font("Comics", Font.PLAIN, 16));
        label2 = new JLabel("Highest Score: " + 0);
        label2.setFont(new Font("Comics", Font.PLAIN, 15));
        button = new JButton(" PLAY");
        pauseButton = new JButton(" PAUSE");
        resetButton = new JButton(" RESET");

        this.size = size;
        setPreferredSize(new Dimension(x * size, y * size)); // This is the selection of size of the window.

        window = new JFrame(text); // Top text.
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(true);
        window.add(label, BorderLayout.BEFORE_FIRST_LINE);
        window.add(label2, BorderLayout.AFTER_LAST_LINE);
        JPanel listPane = new JPanel(); 
        JPanel flowPane = new JPanel();
        // flowPane.setLayout(FlowLayout.LEADING);
        flowPane.setVisible(true);
        flowPane.setBounds(flowPane.getBounds());
        flowPane.add(this, BorderLayout.CENTER);
        // flowPane.setLayout(new BoxLayout(flowPane, BoxLayout.Y_AXIS));
        listPane.add(flowPane, BorderLayout.CENTER);
        buttonPane.setLayout(new BoxLayout(buttonPane, BoxLayout.LINE_AXIS));
        buttonPane.setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 10));
        buttonPane.add(button);
        buttonPane.add(Box.createRigidArea(new Dimension(5, 0))); // for space between the Buttons
        buttonPane.add(pauseButton);
        buttonPane.add(Box.createRigidArea(new Dimension(5, 0))); 
        buttonPane.add(resetButton);
        //window.add(buttonPane);
        listPane.setFocusable(false);
        window.add(displayaArea);
        window.add(listPane, BorderLayout.CENTER);
        window.add(buttonPane, BorderLayout.PAGE_END);
        window.pack();
        window.setVisible(true);

        state = new int[x][y];
        for (int i = 0; i < state.length; i++)
        {
            for (int j = 0; j < state[i].length; j++)
            {
                state[i][j] = -1;
            }
        }
    }

    /**
     * This function is called BY THE SYSTEM if required for a new frame, uses the state stored by the UI class.
     */
    public void paintComponent(Graphics g)
    {
        Graphics2D localGraphics2D = (Graphics2D) g;
        localGraphics2D.setColor(Color.DARK_GRAY);
        localGraphics2D.fill(getVisibleRect());

        //draw lines
        localGraphics2D.setColor(Color.DARK_GRAY);
        for (int i = 0; i <= state.length; i++)
        {
            localGraphics2D.drawLine(i * size, 0, i * size, state[0].length * size);
        }
        for (int i = 0; i <= state[0].length; i++)
        {
            localGraphics2D.drawLine(0, i * size, state.length * size, i * size);
        }
        
        //draw blocks
        for (int i = 0; i < state.length; i++)
        {
            for (int j = 0; j < state[0].length; j++)
            {
                localGraphics2D.setColor(GetColorOfID(state[i][j]));
                localGraphics2D.fill(new Rectangle2D.Double(i * size + 1, j * size + 1, size - 1, size - 1));
            }
        }
        window.addKeyListener(listener);
    }
    


    /**
     * Decodes the ID of a pentomino into a color
     * @param i ID of the pentomino to be colored
     * @return the color to represent the pentomino. It uses the class Color (more in ICS2 course in Period 2)
     */
    private Color GetColorOfID(int i)
    {
        if(i==0) {return Color.BLUE;}
        else if(i==1) {return Color.ORANGE;}
        else if(i==2) {return Color.CYAN;}
        else if(i==3) {return Color.GREEN;}
        else if(i==4) {return Color.MAGENTA;}
        else if(i==5) {return Color.PINK;}
        else if(i==6) {return Color.RED;}
        else if(i==7) {return Color.YELLOW;}
        else if(i==8) {return new Color(25, 50, 0);}
        else if(i==9) {return new Color(0, 0, 100);}
        else if(i==10) {return new Color(100, 0,0);}
        else if(i==11) {return new Color(0, 100, 0);}
        else {return Color.BLACK;}
    }

    /**
     * This function should be called to update the displayed state (makes a copy)
     * @param _state information about the new state of the GUI
     */
    public void setState(int[][] _state, int score, int highestScore)
    {
        for (int i = 0; i < state.length; i++)
        {
            for (int j = 0; j < state[i].length; j++)
            {
                state[i][j] = _state[i][j];
            }
        }
        label.setText("Score: " + score + " Highest Score: " + highestScore);
        //Tells the system a frame update is required
        // System.out.println(window.getBounds().getHeight());
        size = ((int)((window.getBounds().getWidth()/281)*(((window.getBounds().getHeight()/772)))*45));
        
        System.out.println(size);
        repaint();
    }
}
