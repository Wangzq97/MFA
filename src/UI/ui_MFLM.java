package UI;

import PO.Club;
import PO.LeagueTableCell;
import PO.Matchs.MFLM;
import PO.Matchs.NoMoreGameException;
import PO.Player;

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
    private JButton another;
    private JButton save;
    private JButton load;
    private JButton table;
    private MFLM mflm;
    private ArrayList<Club> clubList;
    private boolean isChanged;

    public ui_MFLM(ArrayList<Club> clubList) {
        this.clubList=clubList;
        mflm=new MFLM(1,1,clubList);
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
        info.setForeground(Color.WHITE);
        JScrollPane JSP=new JScrollPane(info);
        JSP.setBounds(240,80,480,270);
        info.setOpaque(false);
        JSP.setOpaque(false);
        JSP.getViewport().setOpaque(false);
        this.add(JSP);

        another=new JButton("New");
        another.setSize(160,40);
        another.setLocation(40,400);
        this.add(another);
        another.addActionListener(this);
        another.setActionCommand("New");

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
        load.setLocation(760,400);
        this.getContentPane().add(load);
        load.addActionListener(this);
        load.setActionCommand("Load");

        table=new JButton("Table");
        table.setSize(160,40);
        table.setLocation(0,0);
        this.getContentPane().add(table);
        table.addActionListener(this);
        table.setActionCommand("Table");

        isChanged=false;


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
                isChanged=true;
                info.setText(message);

            } catch (NoMoreGameException e1) {
                e1.printStackTrace();
            }
        }

        if(e.getActionCommand().equals("New")) {
            if(isChanged){
                int n = JOptionPane.showConfirmDialog(this, "存在未保存的更改，确定要放弃保存吗？", "警告",JOptionPane.YES_NO_OPTION);
                if(n==-1||n==1) return;
            }
            mflm=new MFLM(1,1,clubList);
            run.setEnabled(true);
            info.setText("初始化完毕");
            run.setText("Run");
            isChanged=true;
        }


        if(e.getActionCommand().equals("Save")){
            saveMatch();
            isChanged=false;
        }

        if(e.getActionCommand().equals("Load")){
            loadMatch();
            isChanged=false;
        }

        if(e.getActionCommand().equals("Table")){
            printTable();
        }

    }

        private void printTable(){
            ArrayList<LeagueTableCell> printList=mflm.getLeagueTable().getPrintList();
            for(int i=0;i<printList.size();i++){
                LeagueTableCell cell=printList.get(i);
                System.out.println(cell.getClubName()+" "+cell.getHasPlayed()+" "+cell.getWins()+" "+cell.getDraws()+" "+cell.getLoses()+" "+cell.getGoals()+" "+cell.getLosts()+" "+cell.getScore());
            }
            System.out.println("*****************************");
            mflm.printGoldenGoal();
        }

        private void saveMatch(){
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
                ObjectOutputStream oos1 = new ObjectOutputStream(new FileOutputStream("src\\saves\\"+fileName+".mfa"));
                oos1.writeObject(byteOutStream.toByteArray());//必须所有引用的对象都实现序列化，否则抛出有java.io.NotSerializableException:这个异常
                oos1.close();
                JOptionPane.showMessageDialog(this, "保存成功，文件名为"+fileName+".mfa", "保存成功", JOptionPane.INFORMATION_MESSAGE);
            } catch (IOException e2) {
                e2.printStackTrace();
            }
        }

        private void loadMatch(){

            int result = 0;
            File file = null;
            String path = null;
            JFileChooser fileChooser = new JFileChooser();

            if(isChanged){
                int n = JOptionPane.showConfirmDialog(this, "存在未保存的更改，确定要放弃保存吗？", "警告",JOptionPane.YES_NO_OPTION);
                if(n==-1||n==1) return;
            }
            while(true) {
            fileChooser.setFileFilter(new FileNameExtensionFilter("mfa存档文件(.mfa)","mfa"));
            File directory = new File("src/saves");
            fileChooser.setCurrentDirectory(directory);
            System.out.println(directory.getPath());
            fileChooser.setDialogTitle("请选择要读取的文件...");
            fileChooser.setApproveButtonText("确定");
            fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
            result = fileChooser.showOpenDialog(this);

                if (JFileChooser.APPROVE_OPTION == result) {
                    path = fileChooser.getSelectedFile().getPath();
                    File f = new File(path);
                    if (!f.exists()) {
                        //应该弹出错误提示窗口
                        JOptionPane.showMessageDialog(null, "文件不存在，请重新选择", "错误的文件", JOptionPane.ERROR_MESSAGE);
                    }else{
                        break;
                    }
                }
            }
            if (path==null) return;

            byte[] bytes=null;
            MFLM smflm=null;
            File f = new File(path);

            //读取文件中的字节流
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


            //将字节流转为Match
            ByteArrayInputStream byteInStream = new ByteArrayInputStream(bytes);
            try {
                ObjectInputStream ois3 = new ObjectInputStream(byteInStream);
                mflm= (MFLM)ois3.readObject();
                ois2.close();
                info.setText("");
            } catch (IOException e1) {
                e1.printStackTrace();
            } catch (ClassNotFoundException e1) {
                e1.printStackTrace();
            }
            info.setText("");
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

