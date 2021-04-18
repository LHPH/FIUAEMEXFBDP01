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

import java.text.SimpleDateFormat;

import java.util.Date;

import java.util.GregorianCalendar;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextArea;

public class ConsultaR extends JPanel {
    DefaultListModel modelo;
    Conexion conexion;
    Consultar4 cons;
    int ID;
    JPanel cartas;
    JScrollPane scroll;
    Date fecha;
    String nomAcad="";
    private JLabel jLabel1 = new JLabel();
    private JLabel jLabel2 = new JLabel();
    private JLabel jLabel3 = new JLabel();
    private JLabel jLabel4 = new JLabel();
    private JLabel jLabel5 = new JLabel();
    private JLabel jLabel6 = new JLabel();
    private JLabel jLabel7 = new JLabel();
    private JLabel jLabel8 = new JLabel();
    private JLabel jLabel9 = new JLabel();
    private JLabel jLabel10 = new JLabel();
    private JLabel jLabel11 = new JLabel();
    private JLabel jLabel12 = new JLabel();
    private JLabel jLabel13 = new JLabel();
    private JLabel jLabel14 = new JLabel();
    private JLabel jLabel15 = new JLabel();
    private JScrollPane jScrollPane1 = new JScrollPane();
    private JScrollPane jScrollPane2 = new JScrollPane();
    private JScrollPane jScrollPane3 = new JScrollPane();
    private JList jList1 = new JList();
    private JButton jButton1 = new JButton();
    private JButton jButton2 = new JButton();
    private JTextArea jTextArea1 = new JTextArea();
    private JTextArea jTextArea2 = new JTextArea();
    private JLabel jLabel16 = new JLabel();
    private JLabel jLabel17 = new JLabel();
    private JSeparator jSeparator1 = new JSeparator();

    JPanel jbInit(){
        this.setLayout( null );
        this.setSize(new Dimension(540, 572));
        this.setPreferredSize(new Dimension(540, 572)); //475,572  , 355
        this.setOpaque(false);
        
        modelo=new DefaultListModel();
        jList1.setFont(new Font("Arial",0,12));
        jList1.setModel(modelo);
        
        jLabel1.setText("Fecha:");
        jLabel1.setBounds(new Rectangle(25, 50, 130, 25));
        jLabel1.setFont(new Font("Arial",1,12));
        
        jLabel2.setText("Hora:");
        jLabel2.setBounds(new Rectangle(205, 50, 100, 25));
        jLabel2.setFont(new Font("Arial",1,12));
        
        jLabel3.setText("Academia:");
        jLabel3.setBounds(new Rectangle(25, 90, 60, 25));
        jLabel3.setFont(new Font("Arial",1,12));
        
        jLabel4.setText("Tipo:");
        jLabel4.setBounds(new Rectangle(25, 125, 85, 25));
        jLabel4.setFont(new Font("Arial",1,12));
        
        jLabel5.setText("Semestre:");
        jLabel5.setBounds(new Rectangle(375, 50, 70, 25));
        jLabel5.setFont(new Font("Arial",1,12));
        
        jLabel6.setText("Lugar:");
        jLabel6.setBounds(new Rectangle(25, 160, 165, 25));
        jLabel6.setFont(new Font("Arial",1,12));
        
        jLabel7.setText("Asunto:");
        jLabel7.setBounds(new Rectangle(25, 205, 115, 20));
        jLabel7.setFont(new Font("Arial",1,12));
        
        jLabel8.setText("Acuerdos:");
        jLabel8.setBounds(new Rectangle(20, 290, 110, 25));
        jLabel8.setFont(new Font("Arial",1,12));
        
        jLabel9.setText("Profesores que asistieron a la reunion:");
        jLabel9.setBounds(new Rectangle(20, 385, 255, 25));
        jLabel9.setFont(new Font("Arial",1,12));

        jLabel10.setText("jLabel10");
        jLabel10.setBounds(new Rectangle(80, 50, 80, 25));
        
        jLabel11.setText("jLabel11");
        jLabel11.setBounds(new Rectangle(245, 50, 80, 25));
        
        jLabel12.setText("jLabel12");
        jLabel12.setBounds(new Rectangle(95, 90, 355, 25));
        
        jLabel13.setText("jLabel13");
        jLabel13.setBounds(new Rectangle(440, 50, 95, 25));
        
        jLabel14.setText("jLabel14");
        jLabel14.setBounds(new Rectangle(65, 125, 345, 25));
        
        jLabel15.setText("jLabel15");
        jLabel15.setBounds(new Rectangle(70, 160, 370, 25));
        
        jLabel16.setText("Generar");
        jLabel16.setBounds(new Rectangle(330, 520, 75, 25));
        jLabel16.setFont(new Font("Arial",1,12));

        jLabel17.setText("Reunion de "+nomAcad);
        jLabel17.setFont(new Font("Aral",1,14));
        jLabel17.setBounds(new Rectangle(95, 10, 410, 25));
        jSeparator1.setBounds(new Rectangle(0, 40, 535, 2));
        jScrollPane1.setBounds(new Rectangle(75, 225, 390, 65));
        jScrollPane2.setBounds(new Rectangle(75, 315, 390, 65));
        jScrollPane3.setBounds(new Rectangle(120, 420, 280, 65));
        
        jButton1.setText("Regresar");
        jButton1.setBounds(new Rectangle(40, 520, 95, 25));

        jButton1.addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent e) {
                    jButton1_mouseClicked(e);
                }
            });
        jButton2.setText("Acta");
        jButton2.setBounds(new Rectangle(390, 520, 95, 25));

        jButton2.addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent e) {
                    generarActa(e);
                }
            });
        jTextArea1.setLineWrap(true);
        jTextArea1.setFont(new Font("Arial",0,12));
        jTextArea1.setEditable(false);

        jTextArea2.setLineWrap(true);
        jTextArea2.setEditable(false);
        jTextArea2.setFont(new Font("Arial",0,12));

        this.add(jSeparator1, null);
        this.add(jLabel17, null);
        this.add(jLabel16, null);
        this.add(jButton2, null);
        this.add(jButton1, null);
        jScrollPane3.getViewport().add(jList1, null);
        this.add(jScrollPane3, null);
        jScrollPane2.getViewport().add(jTextArea2, null);
        this.add(jScrollPane2, null);
        jScrollPane1.getViewport().add(jTextArea1, null);
        this.add(jScrollPane1, null);
        this.add(jLabel15, null);
        this.add(jLabel14, null);
        this.add(jLabel13, null);
        this.add(jLabel12, null);
        this.add(jLabel11, null);
        this.add(jLabel10, null);
        this.add(jLabel9, null);
        this.add(jLabel8, null);
        this.add(jLabel7, null);
        this.add(jLabel6, null);
        this.add(jLabel5, null);
        this.add(jLabel4, null);
        this.add(jLabel3, null);
        this.add(jLabel2, null);
        this.add(jLabel1, null);
        return this;
    }
    
    public void setInfo(int Id){
        this.ID=Id;
        String sql="SELECT TIPO_R,LUGAR,ASUNTO,ACUERDOS,HORA,FECHA,SEMESTRE,NOMBRE_ACADEMIA FROM REUNION " +
            "WHERE ID_REUNION="+Id+"";
        ResultSet res=conexion.getConsulta(sql);
        try{
            res.next();
            SimpleDateFormat conv= new SimpleDateFormat("dd/MM/yyyy");
            jLabel10.setText(conv.format(res.getDate("FECHA")));
            fecha=res.getDate("FECHA");
            jLabel11.setText(res.getString("HORA"));
            nomAcad=res.getString("NOMBRE_ACADEMIA");
            jLabel17.setText("Reunion de "+nomAcad);
            jLabel12.setText(res.getString("NOMBRE_ACADEMIA"));
            jLabel13.setText(res.getString("SEMESTRE"));
            jLabel14.setText(res.getString("TIPO_R"));
            jLabel15.setText(res.getString("LUGAR"));
            jTextArea1.setText(res.getString("ASUNTO"));
            jTextArea2.setText(res.getString("ACUERDOS"));
            res.close();
            
            String sql2="SELECT NOMBRE FROM PROFESOR_REUNION WHERE ID_REUNION="+Id+"";
            ResultSet res2=conexion.getConsulta(sql2);
            while(res2.next()){
                modelo.addElement(res2.getString("NOMBRE"));
            }
            res2.close();
        }catch(SQLException e){
            System.out.println("Error en ConsultaR");
        }
    }
    
    public void initConexion(){
        conexion=Acceso.conexion;
    }
    
    public void setCard(JPanel cartas){
        this.cartas=cartas;
    }
    
    public void setConsulta4(Consultar4 a){
        cons=a;
    }
    
    public void setScroll(JScrollPane a){
        scroll=a;
    }

    private void jButton1_mouseClicked(MouseEvent e) {
        modelo.removeAllElements();
        scroll.getViewport().setViewPosition(new Point(0,0));
        CardLayout cl = (CardLayout)(cartas.getLayout());
        cl.show(cartas,"Menu");
        cons.setVisible(true);
    }

    private void generarActa(MouseEvent e) {
        GregorianCalendar a = new GregorianCalendar();
        Date fechaAct =a.getGregorianChange();
        if(fechaAct.before(fecha)==true){
            Acta ac= new Acta(this.ID,this);
            ac.principal();
        }
        else{
            JOptionPane.showMessageDialog(this,"No se puede generar el acta porque la reunion\n no se ha llevado a cabo","Aviso",JOptionPane.INFORMATION_MESSAGE);
        }
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
