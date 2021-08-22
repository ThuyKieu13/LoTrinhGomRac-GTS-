/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gts;

import java.awt.BasicStroke;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Vector;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Net 101 Gaming
 */
public class TimDuongDiNganNhat extends javax.swing.JFrame {

    private Image mshi = new ImageIcon("2.jpg").getImage();
    Dimension d;
    Connection connection = null;
    Statement sta = null;
    ResultSet res = null;
    DefaultTableModel dsQuanHuyen;

    public TimDuongDiNganNhat() {
        initComponents();
        this.setTitle("Tìm đường đi ngắn nhất để dọn rác (GTS)");
        d = new Dimension();
        d.width = mshi.getWidth(null) + 350;
        d.height = mshi.getHeight(null);
        this.setSize(d);
        this.setLocationRelativeTo(null);
        ketNoiCSDL();
    }

    private void ketNoiCSDL() {
        String strSever = "DESKTOP-GIU8V36\\SQLEXPRESS";
        String strDatabase = "QuanHuyen";
        String strUserName = "sa";
        String strPassword = "123";
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(TimDuongDiNganNhat.class.getName()).log(Level.SEVERE, null, ex);
        }
        String connectionURL = "jdbc:sqlserver://" + strSever
                + ":1433; databaseName =" + strDatabase
                + ";user=" + strUserName + ";password=" + strPassword;
        try {
            connection = DriverManager.getConnection(connectionURL);
            sta = connection.createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(TimDuongDiNganNhat.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (connection != null) {
            System.out.println("Ket noi thanh cong den csdl");
        } else {
            System.out.println("Loi ket noi, vui long kiem tra lai");
        }
    }

    public void paint(Graphics g) {//vẽ bản đồ và xác định vị trí của các quận/huyện
        setForeground(Color.RED);
        g.drawImage(mshi, 0, 0, pnBanDo);//vẽ hìnt từ ảnh tới hết panel Bản đồ
        DSCacQuan quan;
        ArrayList<DSCacQuan> ds = new ArrayList<>();// tạo mảng
        dsQuanHuyen = new DefaultTableModel();// tạo bảng
        tbQuanHuyen.setModel(dsQuanHuyen); 
        String[] a = {"Mã quận", "Tên quận/huyện", "Tọa độ x", "Tọa độ y"};
        dsQuanHuyen.setColumnIdentifiers(a);
        String sqlSelect = "SELECT MaQuan,TenQuan,ToaDoX,ToaDoY FROM QuanHuyen";
        try {
            sta = connection.createStatement();
            res = sta.executeQuery(sqlSelect);
            while (res.next()) {
                String ma = res.getString("MaQuan");
                String tenQuan = res.getString("TenQuan");
                int x = res.getInt("ToaDoX");
                int y = res.getInt("ToaDoY");
                Vector<Object> vec = new Vector<Object>();
                vec.add(ma);// đẩy dữ liệu lên 
                vec.add(tenQuan);
                vec.add(x);
                vec.add(y);
                dsQuanHuyen.addRow(vec);// gán vào từng dòng
                quan = new DSCacQuan(tenQuan, new Diem2D(x, y));// tạo quận mới và điểm 2d mới
                ds.add(quan);
            }
            Graphics2D g2d = (Graphics2D) g;// thư viện đồ họa
            BasicStroke bs2 = new BasicStroke(9, BasicStroke.CAP_ROUND,
                    BasicStroke.JOIN_BEVEL);// tăng đồ dày g2d
            g2d.setStroke(bs2);
            g2d.setColor(Color.red);
            ds.forEach((dsq) -> {
                int x = dsq.getToaDo().getX();// duyệt 
                int y = dsq.getToaDo().getY();
                g2d.drawLine(x, y, x, y); //vẽ
            });
        } catch (SQLException ex) {
            Logger.getLogger(TimDuongDiNganNhat.class.getName()).log(Level.SEVERE, null, ex);
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

        pnBanDo = new javax.swing.JPanel();
        pnXuLy = new javax.swing.JPanel();
        btGTS2 = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        tbQuanHuyen = new javax.swing.JTable();
        lbKetQua = new javax.swing.JLabel();
        tfBatDau = new javax.swing.JTextField();
        btGTS1 = new javax.swing.JButton();
        btXoa = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        taKetQua = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(102, 102, 255));

        pnBanDo.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        javax.swing.GroupLayout pnBanDoLayout = new javax.swing.GroupLayout(pnBanDo);
        pnBanDo.setLayout(pnBanDoLayout);
        pnBanDoLayout.setHorizontalGroup(
            pnBanDoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        pnBanDoLayout.setVerticalGroup(
            pnBanDoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        pnXuLy.setBackground(new java.awt.Color(102, 102, 255));

        btGTS2.setText("GTS2");
        btGTS2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btGTS2ActionPerformed(evt);
            }
        });

        jScrollPane3.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        tbQuanHuyen.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Tên Quận/Huyện", "Tọa độ x", "Tọa độ y"
            }
        ));
        tbQuanHuyen.setEnabled(false);
        jScrollPane3.setViewportView(tbQuanHuyen);

        lbKetQua.setFont(new java.awt.Font("Times New Roman", 0, 24)); // NOI18N
        lbKetQua.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        btGTS1.setText("GTS1");
        btGTS1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btGTS1ActionPerformed(evt);
            }
        });

        btXoa.setText("CLEAR");
        btXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btXoaActionPerformed(evt);
            }
        });

        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        jScrollPane1.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        taKetQua.setColumns(20);
        taKetQua.setRows(5);
        taKetQua.setEnabled(false);
        jScrollPane1.setViewportView(taKetQua);

        javax.swing.GroupLayout pnXuLyLayout = new javax.swing.GroupLayout(pnXuLy);
        pnXuLy.setLayout(pnXuLyLayout);
        pnXuLyLayout.setHorizontalGroup(
            pnXuLyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 233, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnXuLyLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(pnXuLyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnXuLyLayout.createSequentialGroup()
                        .addGroup(pnXuLyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnXuLyLayout.createSequentialGroup()
                                .addGap(8, 8, 8)
                                .addComponent(lbKetQua, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(pnXuLyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(tfBatDau, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(pnXuLyLayout.createSequentialGroup()
                                    .addComponent(btGTS1, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(35, 35, 35)
                                    .addComponent(btGTS2, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(pnXuLyLayout.createSequentialGroup()
                        .addGap(61, 61, 61)
                        .addComponent(btXoa)
                        .addGap(72, 72, 72))))
            .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 291, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        pnXuLyLayout.setVerticalGroup(
            pnXuLyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnXuLyLayout.createSequentialGroup()
                .addGap(7, 7, 7)
                .addComponent(tfBatDau, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnXuLyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btGTS1)
                    .addComponent(btGTS2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btXoa)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lbKetQua, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(56, 56, 56))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(pnBanDo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 437, Short.MAX_VALUE)
                .addComponent(pnXuLy, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(pnXuLy, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addGap(190, 190, 190))
            .addGroup(layout.createSequentialGroup()
                .addComponent(pnBanDo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(586, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public double GTS1(int v) { //tìm đường đi ngắn nhất của từ điểm cho sẳn(v)
        double kq = 0;
        double maTran[][];
        int t = 0; //biến đếm
        int n = 0, m = 0, u = 0, w = 0;
        double d = 0, min = 99999;
        n = tbQuanHuyen.getRowCount();
        m = n;
        int flag[] = new int[n]; //cờ kiểm tra điểm đã đi qua
        maTran = new double[n][m];

        for (int i = 0; i < n; i++) {   //tạo ma trận khoảng cách
            for (int j = 0; j < m; j++) {
                if (i == j) {
                    maTran[i][j] = 999999;
                } else {
                    int x1 = (int) tbQuanHuyen.getValueAt(i, 2);
                    int y1 = (int) tbQuanHuyen.getValueAt(i, 3);
                    int x2 = (int) tbQuanHuyen.getValueAt(j, 2);
                    int y2 = (int) tbQuanHuyen.getValueAt(j, 3);
                    d = Math.sqrt(Math.pow((x2 - x1), 2) + Math.pow((y2 - y1), 2));//khoảng cách giữ 2 điểm đưa vào ma trận đường đi
                    double roundOff = Math.floor(d * 100) / 100; //lấy 2 số thập phân sau dấu ,
                    maTran[i][j] = roundOff;
                }
            }
        }

        for (int i = 0; i < n; i++) {
            flag[i] = -1;//gán cờ cho các điểm -1 (chưa đi qua)
        }
        u = v;//gán u = vị trí bắt đầu (v)
        flag[u] = 1;//vị trí đã đi qua gán cờ 1 (đã đi qua)
        do {
            for (int j = 0; j < m; j++) {
                if (maTran[u][w] > maTran[u][j] && flag[j] != 1) {//kiểm tra điểm đã đi qua chưa và có phải là ngắn nhất không
                    min = maTran[u][j];//gán đường đi ngắn nhất
                    w = j;//gán w là đỉnh có thể đi qua
                }
            }
            kq += min;//cộng đường đi vào kết quả
            t += 1;
            u = w;//xét lại điểm xuất phát
            flag[w] = 1;//vị trí đã đi qua gán cờ 1
        } while (t < n);
        kq += maTran[v][u];
        return kq;//trả về đường đi tốt nhất
    }

    private void btGTS2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btGTS2ActionPerformed
        // Tìm đường đi tốt nhất 
        Graphics g = getGraphics(); //thư viện đồ hoạ
        String tour = "";
        double kq = 0;
        double maTran[][];
        int t = 0;
        int n = 0, m = 0, u = 0, w = 0;
        double d = 0, min = 99999, bestCost = 99999;
        n = tbQuanHuyen.getRowCount();
        m = n;
        int flag[] = new int[n];
        maTran = new double[n][m];
        int v = 0;
        for (int i = 0; i < n; i++) {//tìm điểm xuất phát có đường đi tốt nhất
            if (bestCost > GTS1(i)) {
                bestCost = GTS1(i);
                v = i;//gán v là điểm xuất phát
            }
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (i == j) {
                    maTran[i][j] = 999999;
                } else {
                    int x1 = (int) tbQuanHuyen.getValueAt(i, 2);
                    int y1 = (int) tbQuanHuyen.getValueAt(i, 3);
                    int x2 = (int) tbQuanHuyen.getValueAt(j, 2);
                    int y2 = (int) tbQuanHuyen.getValueAt(j, 3);
                    d = Math.sqrt(Math.pow((x2 - x1), 2) + Math.pow((y2 - y1), 2));
                    double roundOff = Math.floor(d * 100) / 100;
                    maTran[i][j] = roundOff;
                }
            }
        }
        for (int i = 0; i < n; i++) {
            flag[i] = -1;
        }
        u = v;
        flag[u] = 1;
        do {
            for (int j = 0; j < m; j++) {
                if (maTran[u][w] > maTran[u][j] && flag[j] != 1) {
                    min = maTran[u][j];
                    w = j;
                }
            }
            try {
                TimeUnit.SECONDS.sleep(1); //thời gian vẽ đường đi
                int x1 = (int) tbQuanHuyen.getValueAt(u, 2);//lấy tọa độ x,y của 2 điểm có đường đi ngắn nhất
                int y1 = (int) tbQuanHuyen.getValueAt(u, 3);
                int x2 = (int) tbQuanHuyen.getValueAt(w, 2);
                int y2 = (int) tbQuanHuyen.getValueAt(w, 3);
                Graphics2D g2d = (Graphics2D) g;    //thư viện vẽ
                BasicStroke bs2 = new BasicStroke(3, BasicStroke.CAP_ROUND, 
                        BasicStroke.JOIN_BEVEL);
                g2d.setStroke(bs2); // chỉnh đậm nhạt cho đường đi
                g2d.setColor(Color.red); //chỉnh màu
                g2d.drawLine(x1, y1, x2, y2);//vẽ đường đi của các điểm
            } catch (InterruptedException ex) {
                Logger.getLogger(TimDuongDiNganNhat.class.getName()).log(Level.SEVERE, null, ex);
            }

            tour = tour + tbQuanHuyen.getValueAt(u, 1).toString() + "\n";//danh sách đường đi
            kq += min;
            t += 1;
            u = w;
            flag[w] = 1;
        } while (t < n);
        //Vẽ lại đường đi từ điểm kết thúc về lại điểm bắt đầu
        u = v;
        int x1 = (int) tbQuanHuyen.getValueAt(u, 2);
        int y1 = (int) tbQuanHuyen.getValueAt(u, 3);
        int x2 = (int) tbQuanHuyen.getValueAt(w, 2);
        int y2 = (int) tbQuanHuyen.getValueAt(w, 3);
        Graphics2D g2d = (Graphics2D) g;
        BasicStroke bs2 = new BasicStroke(3, BasicStroke.CAP_ROUND,
                BasicStroke.JOIN_BEVEL);
        g2d.setStroke(bs2);
        g2d.setColor(Color.red);
        g2d.drawLine(x1, y1, x2, y2);//vẽ đường đi
        tour = tour + tbQuanHuyen.getValueAt(u, 1).toString();
        kq += maTran[w][u];
        double roundOff = Math.floor(kq * 100) / 100;
        taKetQua.setText(tour);//xuất kết quả sau khi tối ưu
        lbKetQua.setText(String.valueOf(roundOff) + " km"); //xuất tổng chi phí
    }//GEN-LAST:event_btGTS2ActionPerformed

    private void btGTS1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btGTS1ActionPerformed
        // TODO add your handling code here:
        if (tfBatDau.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Hãy nhập điểm bắt đầu!");
        } else {
            Graphics g = getGraphics();
            String tour = "";
            double kq = 0;
            double maTran[][];
            int t = 0;
            int n = 0, m = 0, u = 0, w = 0;
            double d = 0, min = 99999;
            n = tbQuanHuyen.getRowCount();
            m = n;
            int flag[] = new int[n];
            maTran = new double[n][m];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    if (i == j) {
                        maTran[i][j] = 999999;
                    } else {
                        int x1 = (int) tbQuanHuyen.getValueAt(i, 2);
                        int y1 = (int) tbQuanHuyen.getValueAt(i, 3);
                        int x2 = (int) tbQuanHuyen.getValueAt(j, 2);
                        int y2 = (int) tbQuanHuyen.getValueAt(j, 3);
                        d = Math.sqrt(Math.pow((x2 - x1), 2) + Math.pow((y2 - y1), 2));
                        double roundOff = Math.floor(d * 100) / 100;
                        maTran[i][j] = roundOff;
                    }
                }
            }
            for (int i = 0; i < n; i++) {
                flag[i] = -1; 
            }
            for (int c = 0; c < n; c++) {
                if (tbQuanHuyen.getValueAt(c, 1).equals(tfBatDau.getText())) {
                    u = c;
                }
            }
            flag[u] = 1;
            do {
                for (int j = 0; j < m; j++) {
                    if (maTran[u][w] > maTran[u][j] && flag[j] != 1) {
                        min = maTran[u][j];
                        w = j;
                    }
                }
                try {
                    TimeUnit.SECONDS.sleep(1);
                    int x1 = (int) tbQuanHuyen.getValueAt(u, 2);//lấy tọa độ x,y của 2 điểm có đường đi ngắn nhất
                    int y1 = (int) tbQuanHuyen.getValueAt(u, 3);
                    int x2 = (int) tbQuanHuyen.getValueAt(w, 2);
                    int y2 = (int) tbQuanHuyen.getValueAt(w, 3);
                    Graphics2D g2d = (Graphics2D) g;
                    BasicStroke bs2 = new BasicStroke(3, BasicStroke.CAP_ROUND,
                            BasicStroke.JOIN_BEVEL);
                    g2d.setStroke(bs2);
                    g2d.setColor(Color.red);
                    g2d.drawLine(x1, y1, x2, y2);//vẽ đường đi của các điểm
                } catch (InterruptedException ex) {
                    Logger.getLogger(TimDuongDiNganNhat.class.getName()).log(Level.SEVERE, null, ex);
                }
                tour = tour + tbQuanHuyen.getValueAt(u, 1).toString() + "\n";//danh sách đường đi
                kq += min;
                t += 1;
                u = w;
                flag[w] = 1;
            } while (t < n);
            for (int v = 0; v < n; v++) {
                if (tbQuanHuyen.getValueAt(v, 1).equals(tfBatDau.getText())) {
                    u = v;
                }
            }
            int x1 = (int) tbQuanHuyen.getValueAt(u, 2);//lấy tọa độ điểm cuối đến điểm bắt đầu
            int y1 = (int) tbQuanHuyen.getValueAt(u, 3);
            int x2 = (int) tbQuanHuyen.getValueAt(w, 2);
            int y2 = (int) tbQuanHuyen.getValueAt(w, 3);
            Graphics2D g2d = (Graphics2D) g;
            BasicStroke bs2 = new BasicStroke(3, BasicStroke.CAP_ROUND,
                    BasicStroke.JOIN_BEVEL);
            g2d.setStroke(bs2);
            g2d.setColor(Color.red);
            g2d.drawLine(x1, y1, x2, y2);//vẽ đường đi
            tour = tour + tbQuanHuyen.getValueAt(u, 1).toString();
            double roundOff = Math.floor(kq * 100) / 100;
            taKetQua.setText(tour);//xuất kết quả sau khi tối ưu
            lbKetQua.setText(String.valueOf(roundOff) + " km");
        }
    }//GEN-LAST:event_btGTS1ActionPerformed

    private void btXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btXoaActionPerformed
        // TODO add your handling code here:
        new TimDuongDiNganNhat().setVisible(true);
    }//GEN-LAST:event_btXoaActionPerformed

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
            java.util.logging.Logger.getLogger(TimDuongDiNganNhat.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TimDuongDiNganNhat.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TimDuongDiNganNhat.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TimDuongDiNganNhat.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TimDuongDiNganNhat().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btGTS1;
    private javax.swing.JButton btGTS2;
    private javax.swing.JButton btXoa;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel lbKetQua;
    private javax.swing.JPanel pnBanDo;
    private javax.swing.JPanel pnXuLy;
    private javax.swing.JTextArea taKetQua;
    private javax.swing.JTable tbQuanHuyen;
    private javax.swing.JTextField tfBatDau;
    // End of variables declaration//GEN-END:variables

}
