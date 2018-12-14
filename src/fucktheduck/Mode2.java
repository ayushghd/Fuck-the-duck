/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fucktheduck;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.PrintWriter;
import static java.lang.Math.cos;
import static java.lang.Math.sin;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JLabel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

/**
 *
 * @author AJAY KUMAR
 */
public class Mode2 extends javax.swing.JFrame {

    private int score;
    private int level;
    private int lives;
    private int x1, y1;
    private int x2, y2;
    private double k1, k2;
    private int bulletind, ducks, bullets;
    private int flag1, flag2, sc = 0;
    private boolean isGameOver = false;
    private int level_score[] = new int[10];
    private int level_lives[] = new int[10];
    private int[] req_score = new int[10];
    private ArrayList<Integer> stats;
    private JLabel bullet[] = new JLabel[50];;
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    Rectangle r;
    double Y, X;
    Thread gameThread;
    
    public Mode2() {
        initComponents();
                
        this.addMouseListener(new MouseClick());
        Toolkit toolkit = getToolkit();
        Image cursorImage = toolkit.getImage(getClass()
                    .getResource("/images/gun-pointer.png"));
        Cursor lightPenCursor;
        lightPenCursor = toolkit.createCustomCursor(cursorImage,
 new Point(0, 0), "lightPenCursor");
    setCursor(lightPenCursor);
   
        level_score[1] = 3;
        level_score[2] = 5;
        level_lives[1] = 5;
        level_lives[2] = 8;
        req_score[1] = 3;
        req_score[2] = 5;
         stats = new ArrayList<Integer>();
        reset();
        
    }

    private void addBullets(int n){
        for (int i = 1; i <= n; i++){
            bullet[i] = new JLabel(new javax.swing.ImageIcon(getClass().getResource("/images/bulletsmall.png")));
            bulletPanel.add(bullet[i], i-1);
        }
    }
    
    private void reset() {
            isGameOver = false;
            score = 0;
            level = 1;
            lives = level_lives[1];
            bulletind = 5;
            ducks = level_lives[1];
            bullets = ducks;
            k1 = 1;
            k2 = 1;
            resetCoordinates();
            flag1 = 0;
            flag2 = 0;
            sc = 0;
            bulletPanel.removeAll();
            duckPanel.removeAll();
            duckPanel.removeAll();
            
            showStats.setVisible(false);
            playAgain.setVisible(false);
            addBullets(5);
            
            round2.setVisible(false);
            dog.setVisible(false);
            gameOver.setVisible(false);
            round2.setVisible(false);
            round1.setVisible(true);
            chidiya1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/leftducksmall.gif")));
            chidiya2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/leftducksmall.gif")));
            System.out.println("jjjj");
            gameThread = new Thread(new Runnable(){
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
            //        System.out.println(""+x1+ " "+y1 + " " + x2 + " "+y2);
                    
                    if (chidiya1.isVisible() && (y1 < 0 || x1 < 0 || x1 > 550)){
                        flag1 = 1;
                        chidiya1.setVisible(false);
                        lives--;
                        ducks--;
                        JLabel j = new JLabel(new javax.swing.ImageIcon(getClass().getResource("/images/ducksmall.png")));
                        duckPanel.add(j);
                        Thread.sleep(10);
                    }
                    if (chidiya2.isVisible() && (y2 < 0 || x2 < 0 || x2 > 550)){
                        flag2 = 1;
                        chidiya2.setVisible(false);
                        lives--;
                        ducks--;
                        JLabel j = new JLabel(new javax.swing.ImageIcon(getClass().getResource("/images/ducksmall.png")));
                        duckPanel.add(j);
                        Thread.sleep(10);
                    }
                    
                    if ((flag1 == 1 && flag2 == 1)||(!chidiya1.isVisible()) && !chidiya2.isVisible()){
                        resetCoordinates();
                    }
                    
                    if (score != req_score[level] && (ducks<=0 || bullets<=0))
                        GameOver();
                    calculatexy();
                    chidiya1.setLocation(x1, y1);
                    chidiya2.setLocation(x2, y2);
                    
                    livescore.setText(sc+"");
                    checkScore();
                    checkLives();
                    Thread.sleep(100);
                } catch (InterruptedException ex) {
                    System.out.println("an error occured during game thread execution");
                }
            }
           }
        });
            gameThread.start();
        }
    
    private void resetCoordinates(){
        flag1 = 0;
        flag2 = 0;
        y1 = 2+((int)(Math.random()*1000))%200;
        chidiya1.setLocation(x1, y1);
        int r = ((int)(Math.random()*100))%2;
        if (r == 0){
            k1 = (1.2*Math.abs(k1));
            chidiya1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/leftducksmall.gif")));
            x1 = 550;
        }
        else{
            k1 = -1.2*(Math.abs(k1));
            chidiya1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/rightducksmall.gif")));
            x1 = 0;    
        }
        
        y2 = 2+((int)(Math.random()*1000))%200;
        chidiya2.setLocation(x2, y2);
        r = ((int)(Math.random()*100))%2;
        if (r == 0){
            k2 = (1.2*Math.abs(k2));
            chidiya2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/leftducksmall.gif")));
            x2 = 550;
        }
        else{
            k2 = -1.2*(Math.abs(k2));
            chidiya2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/rightducksmall.gif")));
            x2 = 0;    
        }
         System.out.println(""+x1+ " "+y1 + " " + x2 + " "+y2);
        
        chidiya1.setVisible(true);
        chidiya2.setVisible(true);
    }
    
    private void reduceLives(){
                if (!isGameOver){
                    resetCoordinates();
                    lives--;
                    round2.setVisible(true);
                    dog.setVisible(true);
                    shootSound();
                }
    }
    
    private void calculatexy(){               
                x1 -= 10*k1;
                x2 -= 10*k2;       
                //System.out.println(""+x1+ " "+y1 + " " + x2 + " "+y2);
    }
    
    private void checkScore(){
            if (score == req_score[level]){
                level++;
                k1 = 1;
                k2 = 1;
                score = 0;
                lives = level_lives[level];
                ducks = level_lives[level];
                bullets = level_lives[level];
                bulletind = lives;
                bulletPanel.removeAll();
                duckPanel.removeAll();
                addBullets(lives);
                resetCoordinates();
                round2.setVisible(true);
                roundIndicator.setIcon (new javax.swing.ImageIcon(getClass().getResource("/images/r2.png")));
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException ex) {
                    Logger.getLogger(GameThread.class.getName()).log(Level.SEVERE, null, ex);
                }
                round2.setVisible(false);
                resetCoordinates();
            }
        }
    
    
    private void GameOver(){
                isGameOver = true;
                playAgain.setVisible(true);
                showStats.setVisible(true);
                stats.add(sc);
                
                try
                {
                    FileWriter fw = new FileWriter("data2.dat",true);
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
                try{
                    InputStream inputStream = getClass().getResourceAsStream("/sounds/gameClear.wav");
                    AudioStream audioStream = new AudioStream(inputStream);
                    AudioPlayer.player.start(audioStream);
                }
                catch (Exception e){

                }
                gameOver.setVisible(true);
                dog.setVisible(true);
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException ex) {
                    Logger.getLogger(GameThread.class.getName()).log(Level.SEVERE, null, ex);
                }
    }
    
    private void checkLives(){
            if (lives == 0){
                
                GameOver();
            }
        }
    
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
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        bulletPanel = new javax.swing.JPanel();
        playAgain = new javax.swing.JButton();
        showStats = new javax.swing.JButton();
        chidiya1 = new javax.swing.JLabel();
        chidiya2 = new javax.swing.JLabel();
        livescore = new javax.swing.JLabel();
        scoreboard = new javax.swing.JLabel();
        round2 = new javax.swing.JLabel();
        round1 = new javax.swing.JLabel();
        gameOver = new javax.swing.JLabel();
        roundIndicator = new javax.swing.JLabel();
        dog = new javax.swing.JLabel();
        duckPanel = new javax.swing.JPanel();
        stage = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        bulletPanel.setOpaque(false);
        getContentPane().add(bulletPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 320, 210, 60));

        playAgain.setText("Play Again");
        playAgain.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                playAgainActionPerformed(evt);
            }
        });
        getContentPane().add(playAgain, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 50, -1, -1));

        showStats.setText("Stats");
        showStats.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                showStatsActionPerformed(evt);
            }
        });
        getContentPane().add(showStats, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 330, -1, -1));

        chidiya1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/rightducksmall.gif"))); // NOI18N
        chidiya1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                chidiya1MouseClicked(evt);
            }
        });
        getContentPane().add(chidiya1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 30, 80, 100));

        chidiya2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/leftducksmall.gif"))); // NOI18N
        chidiya2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                chidiya2MouseClicked(evt);
            }
        });
        getContentPane().add(chidiya2, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 180, 80, -1));

        livescore.setForeground(new java.awt.Color(255, 255, 255));
        livescore.setText("0");
        getContentPane().add(livescore, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 340, -1, -1));

        scoreboard.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/scoreboard.jpg"))); // NOI18N
        getContentPane().add(scoreboard, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 340, -1, -1));

        round2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/round2.png"))); // NOI18N
        getContentPane().add(round2, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 150, -1, -1));

        round1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/round1.png"))); // NOI18N
        getContentPane().add(round1, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 150, -1, -1));

        gameOver.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/gameOver.png"))); // NOI18N
        getContentPane().add(gameOver, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 160, -1, -1));

        roundIndicator.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/r1.png"))); // NOI18N
        getContentPane().add(roundIndicator, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, -1));

        dog.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/dogsmall_gif.gif"))); // NOI18N
        getContentPane().add(dog, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 130, 120, 160));

        duckPanel.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        duckPanel.setOpaque(false);
        getContentPane().add(duckPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 350, 260, 30));

        stage.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/stage.png"))); // NOI18N
        getContentPane().add(stage, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 20, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void chidiya1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_chidiya1MouseClicked
        if (!isGameOver && !round1.isVisible() && !round2.isVisible()){
            score++;
            sc += 100*level;
            bulletPanel.remove(bullet[bulletind--]);
            chidiya1.setVisible(false);
            JLabel j = new JLabel(new javax.swing.ImageIcon(getClass().getResource("/images/whiteduck.png")));
            duckPanel.add(j);
            shootSound();
            try {
                Thread.sleep(10);
            } catch (InterruptedException ex) {
                Logger.getLogger(Mode2.class.getName()).log(Level.SEVERE, null, ex);
            }
            bullets--;
            ducks--;
        }
        
        System.out.println("Chidiya clicked");
    }//GEN-LAST:event_chidiya1MouseClicked

    private void chidiya2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_chidiya2MouseClicked
        if (!isGameOver && !round1.isVisible() && !round2.isVisible()){
            score++;
            sc += 100*level;
            chidiya2.setVisible(false);
            JLabel j = new JLabel(new javax.swing.ImageIcon(getClass().getResource("/images/whiteduck.png")));
            duckPanel.add(j);
            bulletPanel.remove(bullet[bulletind--]);
            shootSound();
            try {
                Thread.sleep(10);
            } catch (InterruptedException ex) {
                Logger.getLogger(Mode2.class.getName()).log(Level.SEVERE, null, ex);
            }
            bullets--;
            ducks--;
        }
        System.out.println("Chidiya clicked");

    }//GEN-LAST:event_chidiya2MouseClicked

    private void playAgainActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_playAgainActionPerformed
        reset();
    }//GEN-LAST:event_playAgainActionPerformed

    private void showStatsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_showStatsActionPerformed
       DefaultCategoryDataset dcd = new DefaultCategoryDataset();
       
       try
        {
            FileReader fr = new FileReader("data2.dat");
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

    
    /**
     * @param args the command line arguments
     */
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
            java.util.logging.Logger.getLogger(Mode2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Mode2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Mode2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Mode2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Mode2().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel bulletPanel;
    private javax.swing.JLabel chidiya1;
    private javax.swing.JLabel chidiya2;
    private javax.swing.JLabel dog;
    private javax.swing.JPanel duckPanel;
    private javax.swing.JLabel gameOver;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel livescore;
    private javax.swing.JButton playAgain;
    private javax.swing.JLabel round1;
    private javax.swing.JLabel round2;
    private javax.swing.JLabel roundIndicator;
    private javax.swing.JLabel scoreboard;
    private javax.swing.JButton showStats;
    private javax.swing.JLabel stage;
    // End of variables declaration//GEN-END:variables



    class MouseClick implements MouseListener {

    @Override
    public void mouseClicked(MouseEvent arg0) { 
        if (!isGameOver && !round1.isVisible() && !round2.isVisible()){
            //bulletPanel.remove(bullet[bulletind--]);
            bulletPanel.setVisible(false);
            bulletPanel.remove(bullet[bulletind--]);
            try {
                Thread.sleep(10);
            } catch (InterruptedException ex) {
                Logger.getLogger(Mode2.class.getName()).log(Level.SEVERE, null, ex);
            }
            lives--;
            bullets--;
            
            bulletPanel.setVisible(true);
            shootSound();
            
            System.out.println("Mouse clicked");
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