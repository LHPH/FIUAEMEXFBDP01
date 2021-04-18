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

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

public class ConsultaP extends JPanel {
    private JLabel jLabel1 = new JLabel();
    private JLabel jLabel2 = new JLabel();
    private JLabel jLabel3 = new JLabel();
    private JLabel jLabel4 = new JLabel();
    private JLabel jLabel5 = new JLabel();
    private JScrollPane jScrollPane1 = new JScrollPane();
    JScrollPane scroll;
    private JTable jTable1 = new JTable();
    
    TableColumn columna;
    Consulta cons;
    String nombre="";
    JPanel cartas;
    Conexion conexion;
    DefaultTableModel modelo;
    private JButton jButton1 = new JButton();
    private JLabel jLabel6 = new JLabel();
    private JSeparator jSeparator1 = new JSeparator();
    private JSeparator jSeparator2 = new JSeparator();
    private JLabel jLabel7 = new JLabel();
    private JLabel jLabel8 = new JLabel();
    private JLabel jLabel9 = new JLabel();
    private JLabel jLabel10 = new JLabel();
    private JLabel jLabel11 = new JLabel();

    JPanel jbInit() {
        this.setLayout( null );
        this.setSize(new Dimension(540, 499));
        this.setPreferredSize(new Dimension(540, 499)); //496,499
        this.setOpaque(false);
        
        
        
        modelo = new DefaultTableModel();
        modelo.addColumn("Materia");
        modelo.addColumn("Academia");
        modelo.addColumn("Semestre");
        //Object []a={"jajaja","hola","siis"};
        //modelo.addRow(a);
        jTable1.setModel(modelo);
        
        columna = jTable1.getColumnModel().getColumn(0);
        columna.setPreferredWidth(110);
                  
        columna = jTable1.getColumnModel().getColumn(1);
        columna.setPreferredWidth(80);
                  
        columna = jTable1.getColumnModel().getColumn(2);
        columna.setPreferredWidth(10);
                   
        
        jLabel1.setText("Nombre: ");
        jLabel1.setBounds(new Rectangle(30, 50, 60, 25));
        jLabel1.setFont(new Font("Arial",1,12));
        
        jLabel2.setText("RFC: ");
        jLabel2.setBounds(new Rectangle(345, 50, 35, 25));
        jLabel2.setFont(new Font("Arial",1,12));
        
        jLabel3.setText("Grado Academico: ");
        jLabel3.setBounds(new Rectangle(15, 110, 110, 30));
        jLabel3.setFont(new Font("Arial",1,12));
        
        jLabel4.setText("Correo Electronico: ");
        jLabel4.setBounds(new Rectangle(265, 110, 115, 30));
        jLabel4.setFont(new Font("Arial",1,12));
        
        jLabel5.setText("Academia y Materias:");
        jLabel5.setBounds(new Rectangle(10, 150, 210, 25));
        jLabel5.setFont(new Font("Arial",1,12));
        
        jLabel6.setText("");
        jLabel6.setBounds(new Rectangle(5, 195, 515, 25));
        jLabel6.setFont(new Font("Arial",1,12));


        jSeparator1.setBounds(new Rectangle(5, 45, 535, 5));
        jSeparator2.setBounds(new Rectangle(5, 185, 490, 5));
        
        jLabel7.setText("Profesor");
        jLabel7.setBounds(new Rectangle(235, 15, 90, 20));
        jLabel7.setFont(new Font("Arial",1,16));

        jLabel8.setText("");
        jLabel8.setBounds(new Rectangle(95, 50, 200, 25));
        jLabel8.setFont(new Font("Arial",0,12));
        
        jLabel9.setText("");
        jLabel9.setBounds(new Rectangle(385, 50, 125, 25));
        jLabel9.setFont(new Font("Arial",0,12));
        
        jLabel10.setText("");
        jLabel10.setBounds(new Rectangle(125, 110, 130, 30));
        jLabel10.setFont(new Font("Arial",0,12));
        
        jLabel11.setText("");
        jLabel11.setBounds(new Rectangle(385, 115, 145, 25));
        jLabel11.setFont(new Font("Arial",0,12));
        
        jButton1.setText("Salir");
        jButton1.setBounds(new Rectangle(25, 435, 105, 20));

        jButton1.addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent e) {
                    regresar(e);
                }
            });
        jScrollPane1.setBounds(new Rectangle(50, 245, 430, 160));


        this.add(jLabel11, null);
        this.add(jLabel10, null);
        this.add(jLabel9, null);
        this.add(jLabel8, null);
        this.add(jLabel7, null);
        this.add(jSeparator2, null);
        this.add(jSeparator1, null);
        this.add(jLabel6, null);
        this.add(jButton1, null);
        jScrollPane1.getViewport().add(jTable1, null);
        this.add(jScrollPane1, null);
        this.add(jLabel5, null);
        this.add(jLabel4, null);
        this.add(jLabel3, null);
        this.add(jLabel2, null);
        this.add(jLabel1, null);
        return this;
    }
    
    private void limpiar(){
        int num=jTable1.getRowCount();
        if(jTable1.getRowCount()!=0){
            for(int i=num-1;i>=0;i--){
                modelo.removeRow(i);
            }
        }
    }
    
    public void setCard(JPanel car){
        cartas=car;
    }
    
    public void initConexion(){
        conexion=Acceso.conexion;
    }
    
    public void initTable(){
        String sql="SELECT DISTINCT MATERIA,ACADEMIA,SEMESTRE FROM PROFESOR_UNIDAD WHERE NOMBRE='"+nombre+"' ORDER BY SEMESTRE ASC,MATERIA ASC";
        ResultSet res=conexion.getConsulta(sql);
        try{
            while(res.next()){
                Object []a = new Object[3];
                for(int i=0;i<3;i++){
                    a[i]=res.getObject(i+1);
                }
                modelo.addRow(a);
            }
        }catch(SQLException e){
            System.out.println("Error en initTable");
        }
    }
    
    public void setInfo(String nomb){
        this.nombre=nomb;
        String sql="SELECT RFC,GRADO,EMAIL FROM PROFESOR WHERE NOMBRE||' '||A_PATERNO||' '||A_MATERNO='"+nomb+"'";
        ResultSet res=conexion.getConsulta(sql);
        try{
            res.next();
            jLabel8.setText(nomb);
            jLabel9.setText(res.getString("RFC"));
            jLabel10.setText(res.getString("GRADO"));
            jLabel11.setText(res.getString("EMAIL"));
            
            String sql2="SELECT DISTINCT PUESTO,NOMBRE_ACADEMIA FROM ACADEMIA_PROFESOR WHERE NOMBREP='"+nomb+"'";
            ResultSet res2=conexion.getConsulta(sql2);
            while(res2.next()){
                if(res2.getString("PUESTO").equals("Presidente")==true || res2.getString("PUESTO").equals("Secretario")==true){
                    jLabel6.setText(res2.getString("PUESTO")+" de la academia:  "+res2.getString("NOMBRE_ACADEMIA"));
                    break;
                }
            }
            this.initTable();
        }catch(SQLException e){
            
        }
    }
    
    public void setScroll(JScrollPane p){
        scroll=p;
    }
    
    public void setConsulta(Consulta a){
        cons=a;
    }

    private void regresar(MouseEvent e) {
        scroll.getViewport().setViewPosition(new Point(0,0));//Poner el scroll al principio
        jLabel8.setText("");
        jLabel9.setText("");
        jLabel10.setText("");
        jLabel11.setText("");
        jLabel6.setText("");
        this.limpiar();
        CardLayout cl = (CardLayout)(cartas.getLayout());
        cl.show(cartas,"Menu");
        cons.limpiar();
        cons.setInfoTable();
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
