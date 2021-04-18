package FBD;

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
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.UIManager;

public class Acceso extends JPanel{
    
    private JTextField jTextField1 = new JTextField();
    private JPasswordField jPasswordField1 = new JPasswordField();
    private JLabel jLabel1 = new JLabel();
    private JLabel jLabel2 = new JLabel();
    private JButton jButton1 = new JButton();
    public static Conexion conexion;
    JPanel cartas;
    
    Principal p;
    private JLabel jLabel3 = new JLabel(new ImageIcon("C:\\Users\\JDK7\\Desktop\\proyectFBD\\pics_proyecto\\Usuario.jpg"));
    private JLabel jLabel4 = new JLabel(new ImageIcon("C:\\Users\\JDK7\\Desktop\\proyectFBD\\pics_proyecto\\llave.png"));


    JPanel jbInit() {
        this.setLayout( null );
        this.setSize(new Dimension(540, 355)); //496,357
        this.setOpaque(false);

        this.setBackground(new Color(109, 140, 237));
        jTextField1.setBounds(new Rectangle(180, 105, 175, 30));
        jTextField1.setVisible(true);
        
        jPasswordField1.setBounds(new Rectangle(180, 155, 175, 30));
        jPasswordField1.setVisible(true);
        
        jLabel1.setText("Usuario:");
        jLabel1.setBounds(new Rectangle(85, 105, 115, 30));
        jLabel1.setVisible(true);
        jLabel1.setFont(new Font("Arial",1,12));
        
        jLabel2.setText("Contrasena:");
        jLabel2.setBounds(new Rectangle(80, 155, 115, 30));
        jLabel2.setVisible(true);
        jLabel2.setFont(new Font("Arial",1,12));

        jButton1.setText("Acceder");
        jButton1.setBounds(new Rectangle(335, 230, 115, 25));
        jButton1.setVisible(true);

        jButton1.addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent e) {
                    jButton1_mouseClicked(e);
                }
            });

        jLabel3.setBounds(new Rectangle(365, 85, 60, 65));
        jLabel3.setMaximumSize(new Dimension(118, 100));
        jLabel3.setMinimumSize(new Dimension(118, 100));
        jLabel4.setBounds(new Rectangle(360, 150, 75, 50));
        this.add(jLabel4, null);
        this.add(jLabel3, null);
        this.add(jButton1, null);
        this.add(jLabel2, null);
        this.add(jLabel1, null);
        this.add(jPasswordField1, null);
        this.add(jTextField1, null);
        return this;
    }

    private void jButton1_mouseClicked(MouseEvent e) {
        if(jTextField1.getText().equals("")==true && String.valueOf(jPasswordField1.getPassword()).equals("")==true){
            JOptionPane.showMessageDialog(this,"Faltan llenar campos","Error",JOptionPane.ERROR_MESSAGE);
        }
        else{
            conexion = new Conexion(jTextField1.getText(),String.valueOf(jPasswordField1.getPassword()));
            boolean acc=conexion.initConexion();
            if(acc==true){
               this.p.initPaneles();
               menu();
               this.p.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
               /* AgregarProfesor a = new AgregarProfesor();
                a.initConexion();
                a.getAcademias();
                a.setMaterias();
                a.setInfo("Luis Ponce Hermosillo");
                a.setVisible(true);*/
            }
            else{
                jTextField1.setText("");
                jPasswordField1.setText("");
            }
        }
    }
    
    
    public void setCard(JPanel card){
        cartas=card;
    }
    
    public void menu(){
        jTextField1.setText("");
        jPasswordField1.setText("");
        CardLayout cl = (CardLayout)(cartas.getLayout());
        cl.show(cartas,"Menu");
    }
    
    public void setFrame(Principal p){
        this.p=p;
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
    
}
