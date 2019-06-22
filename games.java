package Tetris;  
import java.awt.*;  
import java.awt.event.*;  
import javax.swing.*;  
public class games extends JFrame  
{  
    public games()  
    {  
        setSize(DEFAULT_WIDTH,DEFAULT_HEIGHT);  
        setResizable(false);//不可设置大小  
  
        infoPanel=new InfoPanel();  
        gamePanel=new GamePanel();  
        timer=new Timer(500,new timeActionListener());//新建一个定时器对象，每0.5s触发一次  
  
        Container contentPane=getContentPane();  
  
        contentPane.add(infoPanel,BorderLayout.WEST);  
        contentPane.add(gamePanel,BorderLayout.CENTER);  
  
        //全局键盘监控事件  
        Toolkit toolkit = Toolkit.getDefaultToolkit();  
        toolkit.addAWTEventListener(new ImplAWTEventListener(), AWTEvent.KEY_EVENT_MASK);  
  
    }  
  
    public static void startTimer()  
    {  
        timer.start();  
    }  
    public static void stopTimer()  
    {  
        timer.stop();  
    }  
    private static Timer timer;  
    //每触发一次，方块便向下移动一格  
    private class timeActionListener implements ActionListener  
    {  
        public void actionPerformed(ActionEvent e)  
        {  
            gamePanel.move(DIRECTION_NONE);  
            infoPanel.setScore(gamePanel.checkLine());  
            infoPanel.repaint();  
        }  
    }  
    //全局事件，用来监控键盘输入  
    class ImplAWTEventListener implements AWTEventListener  
    {  
        @Override  
        public void eventDispatched(AWTEvent event) {  
            if (event.getClass() == KeyEvent.class) {  
                // 被处理的事件是键盘事件.  
                KeyEvent keyEvent = (KeyEvent) event;  
                if (keyEvent.getID() == KeyEvent.KEY_PRESSED) {  
                    //按下时你要做的事情  
                    keyPressed(keyEvent);  
                } else if (keyEvent.getID() == KeyEvent.KEY_RELEASED) {  
                    //放开时你要做的事情  
                    keyReleased(keyEvent);  
                }  
            }  
        }  
  
        private void keyPressed(KeyEvent e)  
        {  
            int keyCode=e.getKeyCode();  
            if(keyCode==KeyEvent.VK_LEFT)  
            {  
                gamePanel.move(DIRECTION_LEFT);  
                infoPanel.setScore(gamePanel.checkLine());  
            }  
            else if(keyCode==KeyEvent.VK_RIGHT)  
            {  
                gamePanel.move(DIRECTION_RIGHT);  
                infoPanel.setScore(gamePanel.checkLine());  
            }  
            else if(keyCode==KeyEvent.VK_UP)  
            {  
                gamePanel.changePosition();  
                infoPanel.setScore(gamePanel.checkLine());  
            }  
            else if(keyCode==KeyEvent.VK_DOWN)  
            {  
                gamePanel.moveToBottom();  
                infoPanel.setScore(gamePanel.checkLine());  
            }  
        }  
        private void keyReleased(KeyEvent event) {}  
    }  
  
  
    InfoPanel infoPanel;  
    GamePanel gamePanel;  
    private final int DEFAULT_WIDTH=550;  
    private final int DEFAULT_HEIGHT=633;  
    private int DIRECTION_LEFT=-1;  
    private int DIRECTION_RIGHT=1;  
    private int DIRECTION_NONE=0;  
}  

[java] view plain copy
package MyGame;  
  
  
  
/** 
 * Created by zu on 2015/3/30. 
 */  
/* 
存储各种block的类，每个类都有一个getPosition方法，这个方法接受一个参考点的坐标，然后返回其他各点坐标 
 */  
public class Blocks  
{  
    public int[][] getLocation(int x,int y)  
    {  
        return new int[4][2];  
    }  
    public void changePosition()  
    {  
        currentMethod++;  
        if(currentMethod>3)  
            currentMethod=0;  
    }  
    protected int currentMethod=0;  
  
}  
  
class OneBlock extends Blocks//立形  
{  
    public int[][] getLocation(int x,int y)  
    {  
        int[][] points=new int[4][2];  
        points[0]=new int[]{x,y};  
        if(currentMethod==0)  
        {  
            points[1]=new int[]{x-1,y};  
            points[2]=new int[]{x+1,y};  
            points[3]=new int[]{x,y-1};  
        }  
        else if(currentMethod==1)  
        {  
            points[1]=new int[]{x,y-1};  
            points[2]=new int[]{x,y-2};  
            points[3]=new int[]{x-1,y-1};  
        }  
        else if(currentMethod==2)  
        {  
            points[1]=new int[]{x,y-1};  
            points[2]=new int[]{x-1,y-1};  
            points[3]=new int[]{x+1,y-1};  
        }  
        else if(currentMethod==3)  
        {  
            points[1]=new int[]{x,y-1};  
            points[2]=new int[]{x,y-2};  
            points[3]=new int[]{x+1,y-1};  
        }  
        return points;  
    }  
  
}  
  
class LeftTwoBlock extends Blocks  
{  
    public int[][] getLocation(int x,int y)  
    {  
        int[][] points=new int[4][2];  
        points[0]=new int[]{x,y};  
        if(currentMethod==0)  
        {  
            points[1]=new int[]{x,y-1};  
            points[2]=new int[]{x,y-2};  
            points[3]=new int[]{x-1,y-2};  
        }  
        else if(currentMethod==1)  
        {  
            points[1]=new int[]{x-1,y};  
            points[2]=new int[]{x+1,y};  
            points[3]=new int[]{x+1,y-1};  
        }  
        else if(currentMethod==2)  
        {  
            points[1]=new int[]{x,y-1};  
            points[2]=new int[]{x,y-2};  
            points[3]=new int[]{x+1,y};  
        }  
        else if(currentMethod==3)  
        {  
            points[1]=new int[]{x,y-1};  
            points[2]=new int[]{x+1,y-1};  
            points[3]=new int[]{x+2,y-1};  
        }  
        return points;  
    }  
}  
  
class RightTwoBlock extends Blocks  
{  
    public int[][] getLocation(int x,int y)  
    {  
        int[][] points=new int[4][2];  
        points[0]=new int[]{x,y};  
        if(currentMethod==0)  
        {  
            points[1]=new int[]{x,y-1};  
            points[2]=new int[]{x,y-2};  
            points[3]=new int[]{x+1,y-2};  
        }  
        else if(currentMethod==1)  
        {  
            points[1]=new int[]{x,y-1};  
            points[2]=new int[]{x-1,y-1};  
            points[3]=new int[]{x-2,y-1};  
        }  
        else if(currentMethod==2)  
        {  
            points[1]=new int[]{x-1,y};  
            points[2]=new int[]{x,y-1};  
            points[3]=new int[]{x,y-2};  
        }  
        else if(currentMethod==3)  
        {  
            points[1]=new int[]{x,y-1};  
            points[2]=new int[]{x+1,y};  
            points[3]=new int[]{x+2,y};  
        }  
        return points;  
    }  
}  
  
class LeftThreeBlock extends Blocks  
{  
    public int[][] getLocation(int x,int y)  
    {  
        int[][] points=new int[4][2];  
        points[0]=new int[]{x,y};  
        if(currentMethod==0||currentMethod==2)  
        {  
            points[1]=new int[]{x,y-1};  
            points[2]=new int[]{x-1,y-1};  
            points[3]=new int[]{x-1,y-2};  
        }  
        else if(currentMethod==1||currentMethod==3)  
        {  
            points[1]=new int[]{x-1,y};  
            points[2]=new int[]{x,y-1};  
            points[3]=new int[]{x+1,y-1};  
        }  
  
        return points;  
    }  
}  
  
class RightThreeBlock extends Blocks  
{  
    public int[][] getLocation(int x,int y)  
    {  
        int[][] points=new int[4][2];  
        points[0]=new int[]{x,y};  
        if(currentMethod==0||currentMethod==2)  
        {  
            points[1]=new int[]{x,y-1};  
            points[2]=new int[]{x+1,y-1};  
            points[3]=new int[]{x+1,y-2};  
        }  
        else if(currentMethod==1||currentMethod==3)  
        {  
            points[1]=new int[]{x+1,y};  
            points[2]=new int[]{x,y-1};  
            points[3]=new int[]{x-1,y-1};  
        }  
  
        return points;  
    }  
}  
  
class FourBlock extends Blocks  
{  
    public int[][] getLocation(int x,int y)  
    {  
        int[][] points=new int[4][2];  
        points[0]=new int[]{x,y};  
        points[1]=new int[]{x+1,y};  
        points[2]=new int[]{x,y-1};  
        points[3]=new int[]{x+1,y-1};  
  
        return points;  
    }  
}  
  
class FiveBlock extends Blocks  
{  
    public int[][] getLocation(int x,int y)  
    {  
        int[][] points=new int[4][2];  
        points[0]=new int[]{x,y};  
        if(currentMethod==0||currentMethod==2)  
        {  
            points[1]=new int[]{x,y-1};  
            points[2]=new int[]{x,y-2};  
            points[3]=new int[]{x,y-3};  
        }  
        else if(currentMethod==1||currentMethod==3)  
        {  
            points[1]=new int[]{x+1,y};  
            points[2]=new int[]{x+2,y};  
            points[3]=new int[]{x-1,y};  
        }  
  
        return points;  
    }  
}  