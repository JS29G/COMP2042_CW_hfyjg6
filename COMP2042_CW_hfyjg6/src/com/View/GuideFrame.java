package com.View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;

public class GuideFrame extends JComponent implements KeyListener,MouseListener,MouseMotionListener {
    private JLabel background;
    private ImageIcon image;
    private static final int FRAME_WIDTH = 700;
    private static final int FRAME_HEIGHT = 500;
    private JLabel guideTitle;
    private JButton backButton;

    //Create guide window
    public GuideFrame(){
        //drawGuideMenu();
        //enableButton();

        this.initialize();
        image = new ImageIcon(getClass().getResource("/guideBrick.jpg"));
        background = new JLabel(image);
        background.setSize(FRAME_WIDTH,FRAME_HEIGHT);

        background.add(guideTitle);
        background.add(backButton);

        this.setSize(FRAME_WIDTH,FRAME_HEIGHT);
        //this.setTitle("Guide Menu");
        //this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.add(background);
    }

    private void initialize(){
        //this.setPreferredSize(new Dimension(DEF_WIDTH,DEF_HEIGHT));
        this.setFocusable(true);
        this.requestFocusInWindow();
        //this.addKeyListener(this);
        //this.addMouseListener(this);
        //this.addMouseMotionListener(this);
    }

    private void drawGuideMenu() {
        guideTitle = new JLabel();
        guideTitle.setText("<html>" + "<h1>Guide</h1>" +
                "Your goal is to hit the wall with a rubber ball<br/>" +
                "However, you cannot drop the small ball<br/>" +

                "<b>Game Controls: </b><br/>" +
                "<b>SPACE</b> - start/pause the game. <br/>" +
                "<b>A</b> - move the player bar left <br/>" +
                "<b>D</b> - move the player bar right <br/>" +
                "<b>ESC</b> - enter/exit pause menu <br/>" +
                "<b>ALT+SHIFT+F1</b> - open console <br/><br/>" +
                " +  </html>");

        guideTitle.setBounds(60,0,600,450);
        guideTitle.setFont(new Font(null, Font.PLAIN, 15));
    }

    //Return button
    public void enableButton(){
        backButton = new JButton();
        backButton.setBounds(550, 400, 100, 30);
        backButton.setText("Back");

        backButton.setForeground(Color.WHITE);
        backButton.setBackground(Color.BLACK);
        backButton.addActionListener((java.awt.event.ActionListener) this);
    }

/*    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==backButton){
            dispose();
        }
    }*/

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }
}