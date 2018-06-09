import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MainFrame extends JFrame {
    private JButton jbt = new JButton("RUN");
    private JButton jbt1 = new JButton("↑");
    private JButton jbt2 = new JButton("↓");
    private JButton jbt3 = new JButton("←");
    private JButton jbt4 = new JButton("→");
    private JButton jbt5 = new JButton("EXIT");
    private JLabel jlb = new JLabel();
    private Container cp;
    private ImageIcon icon = new ImageIcon("0.pnj.png");
    private JPanel jpnc = new JPanel(new GridLayout());
    private JPanel jpns = new JPanel(new GridLayout(1,6,3,3));
    private Timer t1 ,t2;
    private int dirFlag = 1;
    private int x=0,y=0,higx=50,higy=50;
    private float m = 0.0f;
    private boolean flag = false;
    public MainFrame(){
        init();
    }
    private void init (){
        this.setBounds(100,100,600,800);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        cp = this.getContentPane();


        t1 = new Timer(200, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                switch (dirFlag){
                    case 1:
                        if (y-4 > -330){
                            y-=10;
                        }else {
                            dirFlag = 2;
                        }
                        break;

                    case 2:
                        if (y+4< 380-higy){
                            y+=10;
                        }else {
                            dirFlag = 1;
                        }
                        break;

                    case 3:
                        if (x-4 > 0){
                            x-=10;
                        }else {
                            dirFlag = 4;
                        }
                        break;

                    case 4:
                        if (x + 4< 560-higx){
                            x+=10;
                        }else {
                            dirFlag = 3;
                        }
                        break;
                }
                jlb.setLocation(x,y);
            }
        });



        t2 = new Timer(100, new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if (x - higx == 0 && y - higy==0){
                    t2.stop();
                }else {
                    int newx,newy;
                    if (higx<x){
                        newx = higx + 1;
                    }else {
                        newx = higx - 1;
                    }
                    newy = Math.round(m*(float)(newx-x)+y);
                    higx = newx;
                    higy = newy;
                    jlb.setLocation(newx,newy);
                    System.out.println(newx +","+ newy);
                }
            }
        });


        cp.add(jpnc,BorderLayout.CENTER);
        cp.add(jpns,BorderLayout.SOUTH);
        jpnc.setLayout(null);

        jlb.setBounds(100,100,100,100);
        jlb.setIcon(icon);
        jpnc.add(jlb);
        jpns.add(jbt);
        jpns.add(jbt1);
        jpns.add(jbt2);
        jpns.add(jbt3);
        jpns.add(jbt4);
        jpns.add(jbt5);





        jlb.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                flag = true;
            }
        });

        jpnc.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (flag){
                    x = e.getX() - 25;
                    y = e.getY() - 25;
                    m = (float)(y-higy) /(float)(x-higx);
                    t2.start();
                    flag = false;
                }
            }
        });




        jbt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                t1.start();
            }
        });

        jbt1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dirFlag = 1;
            }
        });

        jbt2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dirFlag = 2;
            }
        });

        jbt3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dirFlag = 3;
            }
        });

        jbt4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dirFlag = 4;
            }
        });

        jbt5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

    }
}