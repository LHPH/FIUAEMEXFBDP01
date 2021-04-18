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

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class Asistencia extends JPanel {
    DefaultTableModel modelo;
    JPanel cartas;
    Conexion conexion;
    Consultar4 cons;
    int ind=1,ind2=1;
    private JScrollPane jScrollPane1 = new JScrollPane();
    private JLabel jLabel1 = new JLabel();
    private JLabel jLabel2 = new JLabel();
    private JComboBox jComboBox1 = new JComboBox();
    private JComboBox jComboBox2 = new JComboBox();
    private JTable jTable1 = new JTable();
    private JButton jButton1 = new JButton();
    private JLabel jLabel3 = new JLabel();
    private JSeparator jSeparator1 = new JSeparator();


    JPanel jbInit()  {
        this.setLayout( null );
        this.setSize(new Dimension(540, 350)); //492,350
        this.setOpaque(false);
        
        modelo = new DefaultTableModel();
        modelo.addColumn("Profesor");
        modelo.addColumn("Asistencias");
        jTable1.setModel(modelo);
        
        jScrollPane1.setBounds(new Rectangle(75, 120, 390, 155));
        
        jLabel1.setText("Academia:");
        jLabel1.setBounds(new Rectangle(50, 60, 110, 25));
        jLabel1.setFont(new Font("Arial",1,12));
        
        jLabel2.setText("Semestre:");
        jLabel2.setBounds(new Rectangle(335, 60, 100, 20));
        jLabel2.setFont(new Font("Arial",1,12));
        
        jComboBox1.setBounds(new Rectangle(135, 60, 185, 20));
        jComboBox1.addItemListener(new ItemListener() {
                public void itemStateChanged(ItemEvent e) {
                    jComboBox1_itemStateChanged(e);
                }
            });
        jComboBox2.setBounds(new Rectangle(410, 60, 75, 20));

        jComboBox2.addItemListener(new ItemListener() {
                public void itemStateChanged(ItemEvent e) {
                    jComboBox2_itemStateChanged(e);
                }
            });
        jButton1.setText("Salir");
        jButton1.setBounds(new Rectangle(370, 300, 95, 25));

        jButton1.addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent e) {
                    jButton1_mouseClicked(e);
                }
            });
        
        jLabel3.setText("Asistencia de Profesores");
        jLabel3.setBounds(new Rectangle(185, 10, 200, 25));
        jLabel3.setFont(new Font("Arial",1,14));

        jSeparator1.setBounds(new Rectangle(0, 35, 535, 2));
        this.add(jSeparator1, null);
        this.add(jLabel3, null);
        this.add(jButton1, null);
        this.add(jComboBox2, null);
        this.add(jComboBox1, null);
        this.add(jLabel2, null);
        this.add(jLabel1, null);
        jScrollPane1.getViewport().add(jTable1, null);
        this.add(jScrollPane1, null);
        return this;
    }
    
    public void setCard(JPanel cart){
        cartas=cart;
    }
    
    public void initConexion(){
        conexion=Acceso.conexion;
    }
    
    public void setConsulta4(Consultar4 c){
        cons=c;
    }


    private void jButton1_mouseClicked(MouseEvent e) {
        this.limpiar();
        jComboBox1.removeAllItems();
        jComboBox2.removeAllItems();
      /*  Consultar4 a = new Consultar4();
        a.setPanel(this);
        a.setCard(cartas);
        a.getAcademia();
        a.setInfoTable();*/
        CardLayout cl = (CardLayout)(cartas.getLayout());
        cl.show(cartas,"Menu");
        cons.setVisible(true);
        
    }
    
    public boolean getAcademia(){
        boolean ind=true;
        String sql="SELECT NOMBRE_ACADEMIA FROM ACADEMIA";
        ResultSet res = conexion.getConsulta(sql);
        try{
            while(res.next()){
                Object obj = res.getObject("NOMBRE_ACADEMIA");
                jComboBox1.addItem(obj);
            }
            this.getSemestre();
        }catch(SQLException e){
            System.out.println("Error en getacademia");
            JOptionPane.showMessageDialog(this,"No Hay Profesores En La Base De Datos","Error",JOptionPane.ERROR_MESSAGE);
            ind=false;
        }
        return ind;
    }
    
    public void getSemestre(){
        String sql="SELECT DISTINCT SEMESTRE FROM REUNION ORDER BY SEMESTRE DESC";
        ResultSet res=conexion.getConsulta(sql);
        try{
            while(res.next()){
                Object a = res.getObject("SEMESTRE");
                jComboBox2.addItem(a);
            }
        }catch(SQLException e){
            
        }
    }
    
    public void setInfoTable(){
        System.out.println("jajaja");
        String sem=jComboBox2.getSelectedItem().toString();
        String acad=jComboBox1.getSelectedItem().toString();
        String sql="SELECT DISTINCT NOMBRE,COUNT(ID_REUNION) AS C FROM PROFESOR_REUNION WHERE SEMESTRE='"+sem+"' AND " +
            "ACADEMIA='"+acad+"' GROUP BY NOMBRE";
        ResultSet res=conexion.getConsulta(sql);
        try{
            while(res.next()){
                Object [] obj = new Object[2];
                for(int i=0;i<2;i++){
                    obj[i]=res.getObject(i+1);
                }
                modelo.addRow(obj);
            }
        }catch(SQLException e){
            System.out.println("Error en SetInfoTable Asis");
        }
    }
    
    private void limpiar(){
        int num=jTable1.getRowCount();
        if(jTable1.getRowCount()!=0){
            for(int i=num-1;i>=0;i--){
                modelo.removeRow(i);
            }
        }
    }

    private void jComboBox1_itemStateChanged(ItemEvent e) {
        if(e.getStateChange()==ItemEvent.SELECTED && ind>2){
            this.limpiar();
            this.setInfoTable();
        }
        ind++;
    }

    private void jComboBox2_itemStateChanged(ItemEvent e) {
        if(e.getStateChange()==ItemEvent.SELECTED && ind2>2){
            this.limpiar();
            this.setInfoTable();
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
