package UI;

import PO.Club;
import PO.Matchs.MFLM;
import PO.Matchs.NoMoreGameException;

import javax.swing.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class ui_MFLM extends JFrame implements ActionListener {
    private JPanel imagePanel;
    private Icon background;
    private JTextArea info;
    private JButton run;
    private JButton save;
    private JButton load;
    private MFLM mflm;
    private ArrayList<Club> clubList;

    public ui_MFLM(ArrayList<Club> clubList) {
        this.clubList=clubList;
        mflm=new MFLM(1,1,clubList);
        java.net.URL imgURL = this.getClass().getResource("/pictures/game.jpg");
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

        this.setTitle("MFLMTest");                              //窗体标题显示
        this.setSize(960, 540);                  //窗体的大小
        this.setLocation(480,270);
        this.setLayout(null);

        info=new JTextArea();
        info.setSize(480,270);
        info.setLocation(240,80);

        info.setLineWrap(true);
        info.setEditable(false);
        info.setAlignmentX((float)0.5);
        JScrollPane JSP=new JScrollPane(info);
        JSP.setBounds(240,80,480,270);
        this.add(JSP);

        run=new JButton("Run");
        run.setSize(160,40);
        run.setLocation(240,400);
        this.add(run);
        run.addActionListener(this);
        run.setActionCommand("Run");

        save=new JButton("Save");
        save.setSize(160,40);
        save.setLocation(560,400);
        this.getContentPane().add(save);
        save.addActionListener(this);
        save.setActionCommand("Save");

        load=new JButton("Load");
        load.setSize(160,40);
        load.setLocation(40,400);
        this.getContentPane().add(load);
        load.addActionListener(this);
        load.setActionCommand("Load");

        this.repaint();
        this.setDefaultCloseOperation(2);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals("Run")) {
            try {
                String message=mflm.run();
                boolean isEnd=mflm.isEnd();
                if(isEnd) {
                    run.setEnabled(false);
                    run.setText("No More Games");
                }
                info.setText(message);

            } catch (NoMoreGameException e1) {
                e1.printStackTrace();
            }
        }

        if(e.getActionCommand().equals("New")) {
            mflm=new MFLM(1,1,clubList);
            run.setEnabled(true);
            info.setText("");
            run.setText("Run");
        }


        if(e.getActionCommand().equals("Save")){
                ByteArrayOutputStream byteOutStream = new ByteArrayOutputStream();
                ObjectOutputStream oos2 = null;
                try {
                    oos2 = new ObjectOutputStream(byteOutStream);
                    oos2.writeObject(mflm);
                    oos2.flush();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }

                try {
                    Date now = new Date();
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd_HHmmss");//可以方便地修改日期格式
                    String fileName = dateFormat.format( now );
                    ObjectOutputStream oos1 = new ObjectOutputStream(new FileOutputStream("src\\saves\\"+fileName+"saves.mfa"));
                    oos1.writeObject(byteOutStream.toByteArray());//必须所有引用的对象都实现序列化，否则抛出有java.io.NotSerializableException:这个异常
                    oos1.close();
                } catch (IOException e2) {
                    e2.printStackTrace();
                }

        }

        if(e.getActionCommand().equals("Load")){
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (ClassNotFoundException e1) {
                e1.printStackTrace();
            } catch (InstantiationException e1) {
                e1.printStackTrace();
            } catch (IllegalAccessException e1) {
                e1.printStackTrace();
            } catch (UnsupportedLookAndFeelException e1) {
                e1.printStackTrace();
            }
            int result = 0;
            File file = null;
            String path = null;
            JFileChooser fileChooser = new JFileChooser();

            fileChooser.setFileFilter(new FileNameExtensionFilter("mfa存档文件(.mfa)","mfa"));
            File directory = new File("src/saves");
            fileChooser.setCurrentDirectory(directory);
            System.out.println(directory.getPath());
            fileChooser.setDialogTitle("请选择要读取的文件...");
            fileChooser.setApproveButtonText("确定");
            fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
            result = fileChooser.showOpenDialog(this);
            if (JFileChooser.APPROVE_OPTION == result) {
                path=fileChooser.getSelectedFile().getPath();
                }

            byte[] bytes=null;
            MFLM smflm=null;
            File f = new File(path);

            ObjectInputStream ois2 = null;
            try {
                ois2 = new ObjectInputStream(new FileInputStream(f));
                bytes = (byte[])ois2.readObject();
                ois2.close();
            } catch (FileNotFoundException e1) {
                e1.printStackTrace();
            } catch (IOException e1) {
                e1.printStackTrace();
            } catch (ClassNotFoundException e1) {
                e1.printStackTrace();
            }


            ByteArrayInputStream byteInStream = new ByteArrayInputStream(bytes);
            try {
                ObjectInputStream ois3 = new ObjectInputStream(byteInStream);
                mflm= (MFLM)ois3.readObject();
                ois2.close();
            } catch (IOException e1) {
                e1.printStackTrace();
            } catch (ClassNotFoundException e1) {
                e1.printStackTrace();
            }
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

