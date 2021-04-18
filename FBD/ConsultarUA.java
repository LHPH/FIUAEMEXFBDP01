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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;

import java.awt.event.MouseEvent;

import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;

public class ConsultarUA extends JPanel {
    private JLabel jLabel1 = new JLabel();
    private JLabel jLabel2 = new JLabel();
    private JLabel jLabel3 = new JLabel();
    private JLabel jLabel4 = new JLabel();
    private JLabel jLabel5 = new JLabel();
    private JScrollPane jScrollPane1 = new JScrollPane();
    private JList jList1 = new JList();
    private JButton jButton1 = new JButton();
    private JComboBox jComboBox1 = new JComboBox();
    private JComboBox jComboBox2 = new JComboBox();
    private JLabel jLabel6 = new JLabel();
    private JLabel jLabel7 = new JLabel();
    
    int ind=1,ind2=1;
    DefaultListModel modelo;
    String horas="",clv="";
    JPanel cartas;
    Consulta3 cons;
    Conexion conexion;
    private JLabel jLabel8 = new JLabel();
    private JSeparator jSeparator1 = new JSeparator();


    JPanel jbInit(){
        this.setLayout( null );
        this.setSize(new Dimension(540, 361)); //438, 361
        this.setOpaque(false);
        
        modelo=new DefaultListModel();
        jList1.setModel(modelo);
        jList1.setFont(new Font("Arial",0,12));

        jLabel1.setText("Nombre:");
        jLabel1.setBounds(new Rectangle(105, 55, 60, 25));
        jLabel1.setFont(new Font("Arial",1,12));
        
        jLabel2.setText("Academia:");
        jLabel2.setBounds(new Rectangle(105, 95, 125, 30));
        jLabel2.setFont(new Font("Arial",1,12));
        
        jLabel3.setText("Clave:");
        jLabel3.setBounds(new Rectangle(105, 130, 50, 30));
        jLabel3.setFont(new Font("Arial",1,12));
        
        jLabel4.setText("Horas:");
        jLabel4.setBounds(new Rectangle(345, 130, 50, 25));
        jLabel4.setFont(new Font("Arial",1,12));
        
        jLabel5.setText("Profesores que imparten la materia:");
        jLabel5.setBounds(new Rectangle(20, 160, 260, 35));
        jLabel5.setFont(new Font("Arial",1,12));

        jScrollPane1.setBounds(new Rectangle(125, 200, 275, 105));
        
        jButton1.setText("Salir");
        jButton1.setBounds(new Rectangle(420, 315, 85, 25));

        jButton1.addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent e) {
                    jButton1_mouseClicked(e);
                }
            });
        jComboBox1.setBounds(new Rectangle(200, 55, 225, 25));
        jComboBox1.addItemListener(new ItemListener() {
                public void itemStateChanged(ItemEvent e) {
                    jComboBox1_itemStateChanged(e);
                }
            });
        jComboBox2.setBounds(new Rectangle(200, 95, 225, 25));
        jComboBox2.addItemListener(new ItemListener() {
                public void itemStateChanged(ItemEvent e) {
                    jComboBox2_itemStateChanged(e);
                }
            });
        jLabel6.setText("");
        jLabel6.setBounds(new Rectangle(145, 135, 95, 25));
        
        jLabel7.setText("");
        jLabel7.setBounds(new Rectangle(395, 130, 90, 25));

        jLabel8.setText("Unidad de Aprendizaje");
        jLabel8.setFont(new Font("Arial",1,14));
        jLabel8.setBounds(new Rectangle(185, 5, 175, 25));

        jSeparator1.setBounds(new Rectangle(0, 25, 540, 20));
        this.add(jSeparator1, null);
        this.add(jLabel8, null);
        this.add(jLabel7, null);
        this.add(jLabel6, null);
        this.add(jComboBox2, null);
        this.add(jComboBox1, null);
        this.add(jButton1, null);
        jScrollPane1.getViewport().add(jList1, null);
        this.add(jScrollPane1, null);
        this.add(jLabel5, null);
        this.add(jLabel4, null);
        this.add(jLabel3, null);
        this.add(jLabel2, null);
        this.add(jLabel1, null);
        return this;
    }
    
    public void getMaterias(String acad){
        String sql="SELECT NOMBRE FROM UNIDAD_APRENDIZAJE WHERE ACADEMIA='"+acad+"'";
        ResultSet res =conexion.getConsulta(sql);
        try{
            while(res.next()){
                Object obj = res.getObject("NOMBRE");
                jComboBox1.addItem(obj);
            }
        }catch(SQLException e){
            System.out.println("Error en getMaterias");
        }
    }
    
    public void getAcademia(){
        String sql="SELECT NOMBRE_ACADEMIA FROM ACADEMIA";
        ResultSet res = conexion.getConsulta(sql);
        try{
            while(res.next()){
                Object obj = res.getObject("NOMBRE_ACADEMIA");
                jComboBox2.addItem(obj);
            }
        }catch(SQLException e){
            System.out.println("Error en getacademia");
        }
    }
    
    public void setCard(JPanel car){
        cartas=car;
    }
    
    public void initConexion(){
        conexion=Acceso.conexion;
    }
    
    public void setInfo(String nomb){
        String sql="SELECT ACADEMIA,CLAVE_UA,T_HORAS FROM UNIDAD_APRENDIZAJE WHERE NOMBRE='"+nomb+"'";
        ResultSet res=conexion.getConsulta(sql);
        try{
            res.next();
            jLabel6.setText(res.getString("CLAVE_UA"));
            jLabel7.setText(res.getString("T_HORAS"));
            jComboBox1.setSelectedItem(nomb);
            jComboBox2.setSelectedItem(res.getString("ACADEMIA"));
            res.close();
            
            String sql2="SELECT NOMBRE FROM PROFESOR_UNIDAD WHERE MATERIA='"+nomb+"' AND SEMESTRE='"+this.setSemestre()+"'";
            ResultSet res2 =conexion.getConsulta(sql2);
            while(res2.next()){
                modelo.addElement(res2.getObject("NOMBRE"));
            }
            res2.close();
            
        }catch(SQLException e){
            System.out.println("Error en setinfo UA aaaaaaa");
        }
    }
    
    public void setInfo(){
        String sql="SELECT ACADEMIA,CLAVE_UA,T_HORAS FROM UNIDAD_APRENDIZAJE WHERE NOMBRE='"+jComboBox1.getSelectedItem()+"'";
        ResultSet res=conexion.getConsulta(sql);
        try{
            res.next();
            jLabel6.setText(res.getString("CLAVE_UA"));
            jLabel7.setText(res.getString("T_HORAS"));
            jComboBox2.setSelectedItem(res.getString("ACADEMIA"));
            
            String sql2="SELECT DISTINCT NOMBRE FROM PROFESOR_UNIDAD WHERE MATERIA='"+jComboBox1.getSelectedItem()+"' AND SEMESTRE='"+this.setSemestre()+"'";
            ResultSet res2 =conexion.getConsulta(sql2);
            while(res2.next()){
                modelo.addElement(res2.getObject("NOMBRE"));
            }
            res2.close();
        }catch(SQLException e){
            System.out.println("Error en setinfo UA xxxxxx");
        }
    }
    
    private String setSemestre(){
        Calendar cal = new GregorianCalendar();
        String sem="";
        int año=cal.get(Calendar.YEAR);
        int mes=cal.get(Calendar.MONTH);
        if(mes<=6){
            sem=año+"A";
        }
        else{
            sem=año+"B";
        }
        return sem;
    }
    
    public void setConsulta3(Consulta3 c){
        cons=c;
    }

    private void jButton1_mouseClicked(MouseEvent e) {
        jLabel6.setText("");
        jLabel7.setText("");
        modelo.removeAllElements();
        CardLayout cl = (CardLayout)(cartas.getLayout());
        cl.show(cartas,"Menu");
        cons.setVisible(true);
        
    }

    private void jComboBox1_itemStateChanged(ItemEvent e) {
        if(e.getStateChange()==ItemEvent.SELECTED && ind>3){
            modelo.removeAllElements();
            this.setInfo(jComboBox1.getSelectedItem().toString());
        }
        ind++;
    }

    private void jComboBox2_itemStateChanged(ItemEvent e) {
        if(e.getStateChange()==ItemEvent.SELECTED && ind2>3){
            jComboBox1.removeAllItems();
            modelo.removeAllElements();
            this.getMaterias(jComboBox2.getSelectedItem().toString());
            this.setInfo();
            System.out.println("s");
        }
        ind2++;
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
