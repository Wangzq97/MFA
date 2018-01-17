package UI;

import PO.Club;

import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;

public class ui extends JFrame implements ActionListener {
    private JPanel imagePanel;
    private Icon background;
    private JTextArea info;
    private ArrayList<Club> clubList;

    public ui(ArrayList<Club> clubList) {
        this.clubList=clubList;
        java.net.URL imgURL = this.getClass().getResource("/pictures/background.jpg");
        background = new ImageIcon(imgURL);// 背景图片
        JLabel label = new JLabel(background);// 把背景图片显示在一个标签里面
        // 把标签的大小位置设置为图片刚好填充整个面板
        label.setBounds(0, 0, background.getIconWidth(),
                background.getIconHeight());
        // 把内容窗格转化为JPanel，否则不能用方法setOpaque()来使内容窗格透明
        imagePanel = (JPanel) this.getContentPane();
        imagePanel.setOpaque(false);
        // 内容窗格默认的布局管理器为BorderLayout
        imagePanel.setLayout(new FlowLayout());

        this.getLayeredPane().setLayout(null);
        // 把背景图片添加到分层窗格的最底层作为背景
        this.getLayeredPane().add(label, new Integer(Integer.MIN_VALUE));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(background.getIconWidth(), background.getIconHeight());
        this.setResizable(false);
        this.setVisible(true);

        java.net.URL iconURL = this.getClass().getResource("/pictures/icon.jpg");
        ImageIcon icon=new ImageIcon(iconURL); //图标
        this.setIconImage(icon.getImage());

        this.setTitle("MFATest");                              //窗体标题显示
        this.setSize(1200, 630);                  //窗体的大小
        this.setLocation(390,225);
        this.setLayout(null);

        info=new JTextArea();
        info.setSize(480,270);
        info.setLocation(240,80);

        info.setLineWrap(true);
        info.setEditable(false);
        info.setAlignmentX((float)0.5);
        JScrollPane JSP=new JScrollPane(info);
        JSP.setBounds(240,80,480,270);
        JSP.setVisible(false);
        this.add(JSP);

        JButton newMFLM=new JButton("New MFLM");
        newMFLM.setSize(160,40);
        newMFLM.setLocation(240,400);
        this.add(newMFLM);
        newMFLM.addActionListener(this);
        newMFLM.setActionCommand("newMFLM");

        JButton newMC=new JButton("New MC");
        newMC.setSize(160,40);
        newMC.setLocation(800,400);
        this.getContentPane().add(newMC);
        newMC.addActionListener(this);
        newMC.setActionCommand("newMC");

        this.repaint();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals("newMFLM")) {
            ui_MFLM mflmui=new ui_MFLM(clubList);
            //mflmui.setDefaultCloseOperation(2);
        }

        if(e.getActionCommand().equals("newMC")) {

        }
    }
   /* private void linkToServer() {
        try {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            RemoteHelper remoteHelper = RemoteHelper.getInstance();
            remoteHelper.setRemote(Naming.lookup("rmi://106.14.201.23:8888/LogicRemoteObject"));

        } catch (MalformedURLException e) {
            e.printStackTrace();

        } catch (RemoteException e) {
            e.printStackTrace();

        } catch (NotBoundException e) {
            e.printStackTrace();

        }
    }*/
}
