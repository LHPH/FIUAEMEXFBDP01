package FBD;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;

import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.awt.event.MouseAdapter;

import java.awt.event.MouseEvent;

import java.sql.ResultSet;

import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Menu extends JPanel {
    AgregarProfesor ap;
    ConsultaP cp;
    AgregarUnidad au;
    ConsultarUA cua;
    ConsultaAcd ca;
    AgregarReunion ar;
    ConsultaR cr;
    Asistencia asi;
    Conexion conexion;
    Principal principal;
    
    private JButton jButton1 = new JButton(new ImageIcon("C:\\Users\\JDK7\\Desktop\\proyectFBD\\pics_proyecto\\reunion2.jpg"));
    private JButton jButton2 = new JButton();
    JPanel cartas;
    private JLabel jLabel1 = new JLabel();
    private JButton jButton3 = new JButton( new ImageIcon("C:\\Users\\JDK7\\Desktop\\proyectFBD\\pics_proyecto\\images.jpg"));
    private JButton jButton5 = new JButton(new ImageIcon("C:\\Users\\JDK7\\Desktop\\proyectFBD\\pics_proyecto\\u_Aprendize.jpg"));
    private JButton jButton6 = new JButton(new ImageIcon("C:\\Users\\JDK7\\Desktop\\proyectFBD\\pics_proyecto\\academia.jpg"));
    private JLabel jLabel2 = new JLabel();
    private JLabel jLabel3 = new JLabel();
    private JLabel jLabel4 = new JLabel();
    private JLabel jLabel5 = new JLabel();

    JPanel jbInit() {
        this.setLayout( null );
        this.setSize(new Dimension(540, 357)); //496,357
        this.setOpaque(false);
        
        jLabel1.setText("MENU PRINCIPAL");
        jLabel1.setFont(new Font("Arial", 1, 16));
        jLabel1.setForeground(Color.BLACK);
        jLabel1.setBounds(new Rectangle(200, 15, 170, 40));
        
        
    //    jButton1.setText("Reuniones");
        jButton1.setBounds(new Rectangle(55, 75, 100, 65));
        jButton1.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    reunion(e);
                }
            });

        jButton1.addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent e) {
                    jButton1_mouseClicked(e);
                }
            });
        jButton2.setText("Salir");
        jButton2.setBounds(new Rectangle(55, 310, 100, 30));
        jButton2.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    salir(e);
                }
            });
        
    //    jButton3.setText("Profesores");
        jButton3.setBounds(new Rectangle(355, 75, 100, 60));
        jButton3.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    profesor(e);
                }
            });


       // jButton5.setText("U. Aprendizaje");
        jButton5.setBounds(new Rectangle(360, 185, 100, 63));
        jButton5.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    unidadAprendizaje(e);
                }
            });

        //jButton6.setText("Academia");
        jButton6.setBounds(new Rectangle(60, 185, 100, 63));
        jButton6.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    academia(e);
                }
            });

        jButton6.addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent e) {
                    jButton6_mouseClicked(e);
                }
            });
        jLabel2.setText("REUNION");
        jLabel2.setFont(new Font("Arial", 1, 14));
        jLabel2.setBounds(new Rectangle(70, 150, 75, 15));
        jLabel3.setText("PRFESORES");
        jLabel3.setFont(new Font("Arial", 1, 14));
        jLabel3.setBounds(new Rectangle(355, 150, 100, 20));
        jLabel4.setText("ACADEMIAS");
        jLabel4.setFont(new Font("Arial", 1, 14));
        jLabel4.setBounds(new Rectangle(65, 260, 90, 20));
        jLabel5.setText("UNIDADES DE APRENDIZAJE");
        jLabel5.setFont(new Font("Arial", 1, 14));
        jLabel5.setBounds(new Rectangle(320, 265, 200, 20));
        this.add(jLabel5, null);
        this.add(jLabel4, null);
        this.add(jLabel3, null);
        this.add(jLabel2, null);
        this.add(jButton6, null);
        this.add(jButton5, null);
        this.add(jButton3, null);
        this.add(jLabel1, null);
        this.add(jButton2, null);
        this.add(jButton1, null);
        return this;
    }
    
    public void initConexion(){
        conexion=Acceso.conexion;
    }

    private void reunion(ActionEvent e) {
       
        
       
    }

    private void salir(ActionEvent e) {
        conexion.cleanUp();
        CardLayout cl = (CardLayout)(cartas.getLayout());
        cl.show(cartas,"Acceso");
        principal.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    private void profesor(ActionEvent e){
        if(this.compExistencia()==true){
            Consulta a = new Consulta();
           // a.setLayout(new BorderLayout());
            //a.add(new GradientPane(),BorderLayout.CENTER);
            a.setPanel(ap);
            a.setPanel2(cp);
            a.setCard(cartas);
            a.setVisible(true);
        }
        else{
        JOptionPane.showMessageDialog(this,"No Puede Acceder a Esta Seccion Hasta Que de De Alta una Academia","Error",JOptionPane.ERROR_MESSAGE);
            }
        
       
        
    }
    
    private void unidadAprendizaje(ActionEvent e){
        //CardLayout cl = (CardLayout)(cartas.getLayout());
        //cl.show(cartas,"Menu U.Aprendizaje");
        if(this.compExistencia()==true){
            Consulta3 a = new Consulta3();
           // a.setLayout(new BorderLayout());
            //a.add(new GradientPane(),BorderLayout.CENTER);
            a.setPanel(au,cua);
            a.setCard(cartas);
            a.getAcademia();
            a.setInfoTable();
            a.setVisible(true);
        }
        else{
            JOptionPane.showMessageDialog(this,"No Puede Acceder a Esta Seccion Hasta Que de De Alta una Academia","Error",JOptionPane.ERROR_MESSAGE);
        }
       
    }
    
    private void academia(ActionEvent e){
       
        
    }
    
    
    public void setCard(JPanel card){
        cartas=card;
    }
    
    public void setProf(AgregarProfesor a,ConsultaP b){
        ap=a;
        cp=b;
    }
    
    public void setUA(AgregarUnidad a,ConsultarUA b){
        au=a;
        cua=b;
    }
    
    public void setAcd(ConsultaAcd a){
        this.ca=a;
    }
    
    public void setReun(AgregarReunion a,Asistencia b,ConsultaR c){
        this.ar=a;
        this.asi=b;
        this.cr=c;
    }
    
    public void setFrame(Principal p){
        principal=p;
    }
    
    public boolean compExistencia(){
        String sql="SELECT COUNT(NOMBRE_ACADEMIA) C FROM ACADEMIA";
        ResultSet res=conexion.getConsulta(sql);
        int num=0;
        boolean ind=false;
        try{
            res.next();
            num=res.getInt("C");
        }catch(SQLException e){
            num=0;
        }
        if(num==0){
            ind=false;
        }
        else{
            ind=true;
        }
        return ind;
    }
    
    public void paintComponent(Graphics g) {
                Graphics2D g2d = (Graphics2D)g;
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                GradientPaint gradientPaint =
                    new GradientPaint(new Point(0, 0),Color.ORANGE, new Point(0, getHeight() / 2),
                                      Color.ORANGE);
                g2d.setPaint(gradientPaint);
                g2d.fillRect(0, 0, getWidth(), getHeight());
                super.paintComponent(g);

    
    }

    private void jButton1_mouseClicked(MouseEvent e) {
        if(this.compExistencia()==true){
            Consultar4 a = new Consultar4();
            //a.setLayout(new BorderLayout());
            //a.add(new GradientPane(),BorderLayout.CENTER);
            a.setPanel(ar, asi, cr);
            a.setCard(cartas);
            a.getAcademia();
            a.setInfoTable();
            a.setVisible(true);
        }
        else{
        JOptionPane.showMessageDialog(this,"No Puede Acceder a Esta Seccion Hasta Que de De Alta una Academia","Error",JOptionPane.ERROR_MESSAGE);
            }
    }

    private void jButton6_mouseClicked(MouseEvent e) {
        Consulta2 a = new Consulta2();
        // a.setLayout(new BorderLayout());
        //a.add(new GradientPane(),BorderLayout.CENTER);
        a.setCard(cartas);
        a.setPanel(ca);
        a.setVisible(true);
    }
}
