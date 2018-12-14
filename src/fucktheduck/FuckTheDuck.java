/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fucktheduck;

public class FuckTheDuck {

    private int score = 0;
    private int level = 1;
    private int lives = 3;
    private int missed = 0;
    private int x = 0, y = 100, dx = 0, dy = 0, screenWidth, screenHeight;
    private boolean isGameOver = false;
    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new StartScreen().setVisible(true);
            }
        });
        
    }
//    
//    public void updateScore(){
//        score++;
//        if (score == 3){
//            level++;
//            changeToLevel(level);
//        }
//    }
//    
//    public void changeToLevel(int level){
//        
//    }
//    
//    public void updateLives(){
//        lives--;
//        if (lives == 0){
//            gameOver();
//        }
//    }
//    
//    public void gameOver(){
//        
//    }
//    
//    public void playGame(){
//        x += dx;
//        y += dy;
//    }
//    
//    
//    public class GameThread implements Runnable {
//
//        static final int DUCK_NUMBER = 10;
//        private Thread thread;
//        private int i;
//
//        public GameThread() {
//           
//        }
//
//        public void start() {
//            reset();
//            thread = new Thread(this);
//            thread.start();
//        }
//
//        public void stop() {
//            
//        }
//
//        private void reset() {
//            
//        }
//
//        @Override
//        public void run() {
//            while (!isGameOver) {
//                
//            }
//            stop();
//        }
//    }
}




