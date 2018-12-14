package fucktheduck;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.MouseInfo;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;
import java.awt.event.MouseEvent;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.swing.JLabel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;


public class GameThread extends javax.swing.JFrame{

    private int score, sc;
    private int level;
    private int lives;
    private double k;
    private int flag;
    private ArrayList<Integer> stats;
    private int x, y, dx, dy;
    private boolean isGameOver = false;
    private GameThread2 gameThread;
    private int level_score[] = new int[10];
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    Rectangle r;
    double Y, X;
    
    public GameThread() {
        initComponents();
        this.addMouseListener(new MouseClick());
        stats = new ArrayList<Integer>();
        flyAway.setVisible(false);
        Toolkit toolkit = getToolkit();
        Image cursorImage = toolkit.getImage(getClass().getResource("/images/gun-pointer.png"));
        Cursor lightPenCursor;
        lightPenCursor = toolkit.createCustomCursor(cursorImage,new Point(0, 0), "lightPenCursor");
        setCursor(lightPenCursor);

        System.out.print(chidiya.getX()+" "+chidiya.getY());
        for (int i = 1; i < 10; i++){
            level_score[i] = 5*i;
        }
        reset();
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        playAgain = new javax.swing.JButton();
        flyAway = new javax.swing.JLabel();
        round1 = new javax.swing.JLabel();
        gOver = new javax.swing.JLabel();
        dog = new javax.swing.JLabel();
        round2 = new javax.swing.JLabel();
        showStats = new javax.swing.JButton();
        remLives = new javax.swing.JLabel();
        chidiya = new javax.swing.JLabel();
        scoreLabel = new javax.swing.JLabel();
        scoreBoard = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        playAgain.setText("Play Again");
        playAgain.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                playAgainActionPerformed(evt);
            }
        });
        getContentPane().add(playAgain, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 40, -1, -1));

        flyAway.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/flyAway.png"))); // NOI18N
        getContentPane().add(flyAway, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 120, 220, -1));

        round1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/round1.png"))); // NOI18N
        getContentPane().add(round1, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 120, -1, -1));

        gOver.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/gameOver.png"))); // NOI18N
        getContentPane().add(gOver, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 140, -1, -1));

        dog.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/dogsmall_gif.gif"))); // NOI18N
        getContentPane().add(dog, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 120, -1, -1));

        round2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/round2.png"))); // NOI18N
        getContentPane().add(round2, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 120, -1, -1));

        showStats.setText("Show Stats");
        showStats.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                showStatsActionPerformed(evt);
            }
        });
        getContentPane().add(showStats, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 350, -1, -1));

        remLives.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/lives3.png"))); // NOI18N
        getContentPane().add(remLives, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 340, 60, 30));

        chidiya.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/leftducksmall.gif"))); // NOI18N
        chidiya.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                chidiyaMouseClicked(evt);
            }
        });
        getContentPane().add(chidiya, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 10, 80, 100));

        scoreLabel.setForeground(new java.awt.Color(255, 255, 255));
        scoreLabel.setText("0");
        getContentPane().add(scoreLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(524, 340, 70, 20));

        scoreBoard.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/scoreboard.jpg"))); // NOI18N
        getContentPane().add(scoreBoard, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 340, -1, -1));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/stage.png"))); // NOI18N
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 640, 390));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void reset() {
            isGameOver = false;
            score = 0;
            sc = 0;
            level = 1;
            lives = 3;
            flag = 0;
            k = 1;
            resetCoordinates();
            Y = 100;
            dy = 0;
            dx = 0;
            showStats.setVisible(false);
            playAgain.setVisible(false);
            flyAway.setVisible(false);
            dog.setVisible(false);
            gOver.setVisible(false);
            round2.setVisible(false);
            round1.setVisible(true);
            chidiya.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/leftducksmall.gif")));
            gameThread = new GameThread2();
            gameThread.start();
        }
    
    private void resetCoordinates(){
         y = 2+((int)(Math.random()*1000))%200;
        chidiya.setLocation(x, y);
        flag = 0;
        int r = ((int)(Math.random()*100))%2;
        if (r == 0){
            k = 1.1*Math.abs(k);
            chidiya.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/leftducksmall.gif")));
            x = 550;
        }
        else{
            k = -1.1*Math.abs(k);
            chidiya.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/rightducksmall.gif")));
            x = 0;    
        }
        dy = 0;
    }
    
    private void chidiyaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_chidiyaMouseClicked
        if (!isGameOver && flag==0 && !round1.isVisible() && !round2.isVisible()){
            k=1.1*Math.signum(k);
            
            score++;
            if (level == 1)
                sc += 100;
            else
                sc += 200;
            shootSound();
            resetCoordinates();
        }
    }//GEN-LAST:event_chidiyaMouseClicked

    private void shootSound(){
            try
            {
                InputStream inputStream = getClass().getResourceAsStream("/sounds/shoot.wav");
                AudioStream audioStream = new AudioStream(inputStream);
                AudioPlayer.player.start(audioStream);
            }
            catch (Exception e)
            {

            }
    }
    
        private void checkScore(){
            if (score == level_score[level]){
                level++;
                lives = 3;
                round2.setVisible(true);
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException ex) {
                    Logger.getLogger(GameThread.class.getName()).log(Level.SEVERE, null, ex);
                }
                round2.setVisible(false);
                resetCoordinates();
            }
        }
        
    private void showStatsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_showStatsActionPerformed
        DefaultCategoryDataset dcd = new DefaultCategoryDataset();
        
        try
        {
            FileReader fr = new FileReader("data.dat");
            BufferedReader br = new BufferedReader(fr);
            String s="";
            int i = 1;
            while ((s=br.readLine())!=null)
            {
                System.out.println(s);
                String ss = "Game - "+i;
                dcd.setValue(Integer.parseInt(s),ss,ss);
                i++;
            }
        }
        catch (Exception e)
        {
            System.out.println("Reading error");
        }
//        for (int i = 0; i < stats.size(); i++){
//            int val = stats.get(i);
//            String s = "Game - "+i;
//            dcd.setValue(val,s,s);
//        }
        JFreeChart jChart = ChartFactory.createBarChart("Game Stats", "Attempts", "Ducks Killed", dcd, PlotOrientation.VERTICAL, true, true, true);
        CategoryPlot plot = jChart.getCategoryPlot();
        plot.setRangeGridlinePaint(Color.BLACK);
        
        ChartFrame chartFrame = new ChartFrame("Game Stats", jChart, true);
        chartFrame.setVisible(true);
        chartFrame.setSize(700, 500);
    }//GEN-LAST:event_showStatsActionPerformed

    private void reduceLives(){
                if (!isGameOver && flag==0 && !round1.isVisible() && !round2.isVisible()){
                    resetCoordinates();
                    lives--;
                    flyAway.setVisible(true);
                    dog.setVisible(true);
                    flag = 1;
                    shootSound();
                }
    }
    
    private void checkLives(){
            if (lives == 3)
                remLives.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/lives3.png")));
            else if (lives == 2)
                remLives.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/lives2.png")));
            else if (lives == 1)
                remLives.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/lives1.png")));
            else if (lives == 0){
                
                isGameOver = true;
                playAgain.setVisible(true);
                showStats.setVisible(true);
                remLives.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/lives0.png")));
                stats.add(score);
                
                
                try
                {
                    FileWriter fw = new FileWriter("data.dat",true);
                    BufferedWriter bw = new BufferedWriter(fw);
                    PrintWriter p = new PrintWriter(bw);
                    p.println(""+sc);
                    p.flush();
                    fw.close();
                }
                catch (Exception e)
                {
                    System.out.println("Writing error");
                }
                
                flyAway.setVisible(false);
                try
                {
                    InputStream inputStream = getClass().getResourceAsStream("/sounds/gameClear.wav");
                    AudioStream audioStream = new AudioStream(inputStream);
                    AudioPlayer.player.start(audioStream);
                }
                catch (Exception e)
                {

                }
                gOver.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/gameOver.png")));
                gOver.setLocation(250, 100);
                gOver.setVisible(true);
                dog.setVisible(true);
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException ex) {
                    Logger.getLogger(GameThread.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        
        private void calculatexy(){
               if (level == 1)
                    x -= 10*k;
               else
                   x -= 1*(double)k;
               if (level == 2 && dy!=-10)
                        y = 100+(int)(1+80*Math.sin(Math.toDegrees(x))*Math.PI/4);
               if (flag == 1){
                   dy -= 20;
               }
               y += dy;
        }
        
    private void playAgainActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_playAgainActionPerformed
        reset();
    }//GEN-LAST:event_playAgainActionPerformed
  
    
    public class GameThread2 implements Runnable {

        private Thread thread;
        public GameThread2() {
            
        }
        public void start() {
            thread = new Thread(this);
            thread.start();
        }
        public void stop() {
            lives = 0;
            if (thread != null && thread.isAlive()) {
                thread.interrupt();
            }
        }
        
        @Override
        public void run() {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException ex) {
                Logger.getLogger(GameThread.class.getName()).log(Level.SEVERE, null, ex);
            }
            round1.setVisible(false);
            while (!isGameOver) {
                try {
                    System.out.println(""+x+" "+y);
                    if (y < 0){
                        resetCoordinates();
                        dog.setVisible(false);
                        flyAway.setVisible(false);
                        flag = 0;
                        dy = 0;
                    }
                    if (x < 0){
                        reduceLives();
                    }
                    else if (x > 550){
                        reduceLives();
                    }
                    calculatexy();
                    chidiya.setLocation(x, y);
                    scoreLabel.setText(sc+"");
                    checkScore();
                    checkLives();
                    if (level == 1)
                        Thread.sleep(100);
                    else
                       Thread.sleep(50);
                } catch (InterruptedException ex) {
                    System.out.println("an error occured during game thread execution");
                }
            }
            stop();
        }
    }
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(GameThread.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GameThread.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GameThread.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GameThread.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GameThread().setVisible(true);
            }
        });
    }
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel chidiya;
    private javax.swing.JLabel dog;
    private javax.swing.JLabel flyAway;
    private javax.swing.JLabel gOver;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JButton playAgain;
    private javax.swing.JLabel remLives;
    private javax.swing.JLabel round1;
    private javax.swing.JLabel round2;
    private javax.swing.JLabel scoreBoard;
    private javax.swing.JLabel scoreLabel;
    private javax.swing.JButton showStats;
    // End of variables declaration//GEN-END:variables

     class MouseClick implements MouseListener {

    @Override
    public void mouseClicked(MouseEvent arg0) { 
        if (!isGameOver){
            reduceLives();
        }
        System.out.println("Mouse clicked");
    }

    @Override
    public void mouseEntered(MouseEvent arg0) { }

    @Override
    public void mouseExited(MouseEvent arg0) { }

    @Override
    public void mousePressed(MouseEvent arg0) {
    }

    @Override
    public void mouseReleased(MouseEvent arg0) {

    }

    }
}
