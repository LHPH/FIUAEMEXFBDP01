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
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;

import java.awt.event.MouseEvent;

import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;

public class ConsultaAcd extends JPanel {
    private JLabel jLabel1 = new JLabel();
    private JLabel jLabel2 = new JLabel();
    private JLabel jLabel3 = new JLabel();
    private JLabel jLabel4 = new JLabel();
    private JLabel jLabel5 = new JLabel();
    private JScrollPane jScrollPane1 = new JScrollPane();
    private JScrollPane jScrollPane2 = new JScrollPane();
    private JList jList1 = new JList();
    private JList jList2 = new JList();
    private JButton jButton1 = new JButton();
    private JComboBox jComboBox1 = new JComboBox();
    int ind=1;
    
    Consulta2 cons;
    String academia="";
    DefaultListModel modelo1,modelo2;
    Conexion conexion;
    JPanel cartas;
    private JLabel jLabel6 = new JLabel();
    private JLabel jLabel7 = new JLabel();
    private JLabel jLabel8 = new JLabel();
    private JSeparator jSeparator1 = new JSeparator();

    JPanel jbInit()  {
        this.setLayout( null );
        this.setSize(new Dimension(540, 391));
        this.setPreferredSize(new Dimension(540, 391)); //469,391
        this.setOpaque(false);
        
        
        modelo1 = new DefaultListModel();
        modelo2=new DefaultListModel();
        jList1.setModel(modelo1);
        jList2.setModel(modelo2);
        
        jLabel1.setText("Nombre: ");
        jLabel1.setBounds(new Rectangle(55, 50, 150, 25));
        jLabel1.setFont(new Font("Arial",1,12));
        
        jLabel2.setText("Presidente:");
        jLabel2.setBounds(new Rectangle(50, 90, 80, 25));
        jLabel2.setFont(new Font("Arial",1,12));
        
        jLabel3.setText("Secretario:");
        jLabel3.setBounds(new Rectangle(50, 125, 75, 30));
        jLabel3.setFont(new Font("Arial",1,12));

        jLabel4.setText("Materias");
        jLabel4.setBounds(new Rectangle(55, 165, 130, 25));
        jLabel4.setFont(new Font("Arial",1,12));
        
        jLabel5.setText("Profesores");
        jLabel5.setBounds(new Rectangle(300, 165, 120, 30));
        jLabel5.setFont(new Font("Arial",1,12));

        jScrollPane1.setBounds(new Rectangle(55, 215, 190, 115));
        jScrollPane2.setBounds(new Rectangle(300, 215, 190, 115));

        jButton1.setText("Salir");
        jButton1.setBounds(new Rectangle(60, 350, 100, 25));

        jButton1.addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent e) {
                    jButton1_mouseClicked(e);
                }
            });
        jComboBox1.setBounds(new Rectangle(175, 50, 220, 25));

        jComboBox1.addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent e) {
                    jComboBox1_mouseClicked(e);
                }
            });
        jComboBox1.addItemListener(new ItemListener() {
                public void itemStateChanged(ItemEvent e) {
                    jComboBox1_itemStateChanged(e);
                }
            });
        jLabel6.setText("");
        jLabel6.setBounds(new Rectangle(125, 90, 380, 25));
        jLabel6.setFont(new Font("Arial",0,12));
        
        jLabel7.setText("");
        jLabel7.setBounds(new Rectangle(120, 125, 390, 30));
        jLabel7.setFont(new Font("Arial",0,12));


        jLabel8.setText("Academia");
        jLabel8.setFont(new Font("Arial",1,14));
        jLabel8.setBounds(new Rectangle(235, 5, 90, 30));
        jSeparator1.setBounds(new Rectangle(5, 30, 530, 25));
        this.add(jSeparator1, null);
        this.add(jLabel8, null);
        this.add(jLabel7, null);
        this.add(jLabel6, null);
        this.add(jComboBox1, null);
        this.add(jButton1, null);
        jScrollPane2.getViewport().add(jList2, null);
        this.add(jScrollPane2, null);
        jScrollPane1.getViewport().add(jList1, null);
        this.add(jScrollPane1, null);
        this.add(jLabel5, null);
        this.add(jLabel4, null);
        this.add(jLabel3, null);
        this.add(jLabel2, null);
        this.add(jLabel1, null);
        return this;
    }
    public boolean getAcademia(){
        boolean ind=true;
        String sql="SELECT NOMBRE_ACADEMIA FROM ACADEMIA";
        ResultSet res = conexion.getConsulta(sql);
        try{
            while(res.next()){
                jComboBox1.addItem(res.getObject("NOMBRE_ACADEMIA"));
            }
        }catch(SQLException e){
            JOptionPane.showMessageDialog(this,"No Hay Profesores En La Base De Datos","Error",JOptionPane.ERROR_MESSAGE);
        }
        return ind;
    }
    
    public void setCard(JPanel car){
        cartas=car;
    }
    
    public void initConexion(){
        conexion=Acceso.conexion;
    }
    
    public void setAcad(String acad){
        this.academia=acad;
        jComboBox1.setSelectedItem(acad);
        this.setInfo();
    }
    
    private void getProfesor(){
        String sql="SELECT DISTINCT NOMBRE FROM PROFESOR_UNIDAD WHERE ACADEMIA='"+this.academia+"'";
        ResultSet res =conexion.getConsulta(sql);
        try{
            while(res.next()){
                Object obj = res.getObject("NOMBRE");
                modelo2.addElement(obj);
            }
        }catch(SQLException e){
            System.out.println("Error en getMaterias");
        }
    }
    
    public void getMaterias(){
        String sql="SELECT NOMBRE FROM UNIDAD_APRENDIZAJE WHERE ACADEMIA='"+this.academia+"'";
        ResultSet res =conexion.getConsulta(sql);
        try{
            while(res.next()){
                Object obj = res.getObject("NOMBRE");
                modelo1.addElement(obj);
            }
        }catch(SQLException e){
            System.out.println("Error en getMaterias");
        }
    }
    
    public void getPresySec(){
        String sql="SELECT NOMBREP FROM ACADEMIA_PROFESOR WHERE PUESTO='Presidente' AND NOMBRE_ACADEMIA='"+this.academia+"'";
                String sql2="SELECT NOMBREP FROM ACADEMIA_PROFESOR WHERE PUESTO='Secretario' AND NOMBRE_ACADEMIA='"+this.academia+"'";
                System.out.println(sql);
                String pr="";
                String sec="";
                ResultSet res=conexion.getConsulta(sql);
                try{
                    res.next();
                    pr=res.getString("NOMBREP");
                    jLabel6.setText(pr);
                }catch(SQLException e){
                    jLabel6.setText("Esta academia no cuenta todavia con un presidente");
                }
                try{
                    ResultSet res2=conexion.getConsulta(sql2);
                    res2.next();
                    sec=res2.getString("NOMBREP");
                    jLabel7.setText(sec);
                }catch(SQLException e){
                    jLabel7.setText("Esta academia no cuenta todavia con un secretario");
                    }
        }
    
    public void setInfo(){
        this.getPresySec();
        this.getMaterias();
        this.getProfesor();
    }
    
    public void setConsulta2(Consulta2 c){
        cons=c;
    }


    private void jComboBox1_mouseClicked(MouseEvent e) {
        this.academia=jComboBox1.getSelectedItem().toString();
        this.setInfo();
    }
    
    private void jComboBox1_itemStateChanged(ItemEvent e) {
           if(e.getStateChange()==ItemEvent.SELECTED && ind>3){
               this.academia=jComboBox1.getSelectedItem().toString();
               modelo1.removeAllElements();
               modelo2.removeAllElements();
               this.setInfo();
           }
           ind++;
       }

    private void jButton1_mouseClicked(MouseEvent e) {
        jLabel4.setText("");
        jLabel5.setText("");
        jLabel6.setText("");
        jLabel7.setText("");
        modelo1.removeAllElements();
        modelo2.removeAllElements();
        CardLayout cl = (CardLayout)(cartas.getLayout());
        cl.show(cartas,"Menu");
        cons.setVisible(true);
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
