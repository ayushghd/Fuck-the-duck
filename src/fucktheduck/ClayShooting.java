/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fucktheduck;


import java.awt.Container;
import java.awt.Cursor;
import java.awt.Image;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.InputStream;
import static java.lang.Math.*;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

public class ClayShooting extends javax.swing.JFrame {

    /**
     * Creates new form ClayShooting
     */
    
    public boolean isGameOver=false;
    private double X,Y;
    private int x,y;
    private double g=15;
    private double t=0;
    private double v=0;
    private double theta=0;
    private double speed[], angle[], gravity[];
    private int a,b;
    private int level = 1;
    private int k = 1;
    Thread gameThread;
    Cursor lightPenCursor;
    public ClayShooting() {
        initComponents();
        
        Toolkit toolkit = getToolkit();
        Image cursorImage = toolkit.getImage(getClass().getResource("/images/gun-pointer.png"));
        
        lightPenCursor = toolkit.createCustomCursor(cursorImage, new Point(0, 0), "lightPenCursor");
        setCursor(lightPenCursor);
         this.addMouseListener(new MouseClick());
        speed = new double[20];
        angle = new double[20];
        gravity = new double[20];
        System.out.println(chidiya.getY());
        for (int i = 1; i < 20; i++){
            speed[i] = 75 + i*25;
            angle[i] = PI/(4-0.1*i);
            gravity[i] = 15+10*i;
        }
        a=chidiya.getX();
        b=chidiya.getY();
        reset();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        gOver = new javax.swing.JLabel();
        livescore = new javax.swing.JLabel();
        scoreboard = new javax.swing.JLabel();
        chidiya = new javax.swing.JLabel();
        dog = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        panel = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        gOver.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/gameOver.png"))); // NOI18N
        getContentPane().add(gOver, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 130, -1, -1));

        livescore.setForeground(new java.awt.Color(255, 255, 255));
        livescore.setText("0");
        getContentPane().add(livescore, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 330, 60, -1));

        scoreboard.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/scoreboard.jpg"))); // NOI18N
        getContentPane().add(scoreboard, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 330, 140, 30));

        chidiya.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Web3.png"))); // NOI18N
        chidiya.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                chidiyaMouseClicked(evt);
            }
        });
        getContentPane().add(chidiya, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 210, 70, 80));

        dog.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/dogsmall_gif.gif"))); // NOI18N
        getContentPane().add(dog, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 120, 120, 190));

        jButton1.setText("Start");
        jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton1MouseClicked(evt);
            }
        });
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 350, -1, -1));

        panel.setOpaque(false);
        getContentPane().add(panel, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 340, 250, 40));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/stage.png"))); // NOI18N
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    private void reset(){
        dog.setVisible(false);
        gOver.setVisible(false);
        
        resetCoordinates();
        X=x;
        Y=y;
        
        gameThread = new Thread(new Runnable(){
           @Override
           public void run() {
               try {
                    while (!isGameOver) {
                        X=speed[level]*t*cos(angle[level]);
                        Y=speed[level]*t*sin(angle[level])-0.5*gravity[level]*t*t;
                        livescore.setText("" + (level-1));
                        t+=0.06;
                        x=(int)X;
                        y=(int)Y;
                        if ((b-y > 330) || (k == 1 && a+k*x > 550)||(k == -1 && a+k*x < 0)){
                            gameOver();
                        }
                        chidiya.setLocation(a+k*x,b-y);
                        livescore.setText(level-1+"");
                        Thread.sleep(20);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
           }
        });
    }
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        JLabel label=new JLabel("new label");
        //getContentPane().add(label, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));
        System.out.println("button clicked");
        reset();
        gameThread.start();
        label.setVisible(true);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MouseClicked
        // TODO add your handling code here:
        System.out.println("button clicked");
        isGameOver = false;
    }//GEN-LAST:event_jButton1MouseClicked

    private void chidiyaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_chidiyaMouseClicked
        level++;
        JLabel j = new JLabel (new javax.swing.ImageIcon(getClass().getResource("/images/ducksmall.png")));
        panel.add(j);
        System.out.println("Chidiya - "+chidiya.getX() + "  "+chidiya.getY());
       // System.out.println("Pointer - "+Container.getMousePosition() );
        
        reset();
    }//GEN-LAST:event_chidiyaMouseClicked

    private void gameOver(){
        if (!isGameOver){
            System.out.println("thingsome");
            isGameOver = true;
            dog.setVisible(true);
            gOver.setVisible(true);
            shootSound();
            level = 1;
            panel.removeAll();
        }
    }
    public void resetCoordinates(){
        t = 0;
        int r = ((int)(random()*100))%2;
       // System.out.println(r +  " " + random());
        if (r == 0){ 
            k = 1;
            a = 0;
            chidiya.setIcon (new javax.swing.ImageIcon(getClass().getResource("/images/Web3.png")));
        }
        else{
            k = -1;
            a = 550;
            chidiya.setIcon (new javax.swing.ImageIcon(getClass().getResource("/images/Web3.png")));
        }
        chidiya.setLocation(a, b);
        dog.setVisible(false);
        gOver.setVisible(false);
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
            java.util.logging.Logger.getLogger(ClayShooting.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ClayShooting.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ClayShooting.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ClayShooting.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ClayShooting().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel chidiya;
    private javax.swing.JLabel dog;
    private javax.swing.JLabel gOver;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel livescore;
    private javax.swing.JPanel panel;
    private javax.swing.JLabel scoreboard;
    // End of variables declaration//GEN-END:variables

    
    class MouseClick implements MouseListener {

    @Override
    public void mouseClicked(MouseEvent arg0) { 
        if (!isGameOver){
            gameOver();
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
