package FBD;

import java.awt.CardLayout;
import java.awt.Component;
import java.awt.Dimension;

import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;

import java.awt.event.MouseEvent;

import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

public class Consulta2 extends JDialog {
    
    ConsultaAcd panel;
    Conexion conexion=Acceso.conexion;
    JPanel cartas;
    DefaultTableModel modelo;
    TableColumn columna;
    private JButton jButton1 = new JButton();
    private JScrollPane jScrollPane1 = new JScrollPane();
    private JTable jTable1 = new JTable();
    private JLabel jLabel1 = new JLabel();
    private JButton jButton2 = new JButton();
    private JButton jButton3 = new JButton();
    private JButton jButton4 = new JButton();

    public Consulta2() {
        try {
            jbInit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void jbInit() throws Exception {
        this.setSize(new Dimension(559, 345));
        this.getContentPane().setLayout( null );
        this.setTitle("Academias");
        this.setModal(true);
        this.setDefaultCloseOperation(JDialog.HIDE_ON_CLOSE);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        
        modelo= new DefaultTableModel();
            modelo.addColumn("Nombre");
            modelo.addColumn("Numero de Materias");
            modelo.addColumn("Numero de Profesores");
            jTable1.setModel(modelo);
            jTable1.setDefaultRenderer(Object.class,new MiRender());


        jLabel1.setText("Academias:");
        jLabel1.setBounds(new Rectangle(225, 15, 100, 30));
        jLabel1.setFont(new Font("Arial",1,14));

        jButton1.setText("Consultar");
        jButton1.setBounds(new Rectangle(135, 255, 100, 20));
        jButton1.addMouseListener(new MouseAdapter() {
                      public void mouseClicked(MouseEvent e) {

                    consultarP(e);
                }
                  });
        jButton1.setEnabled(false);
          
        jButton2.setText("Modificar Nombre");
        jButton2.setBounds(new Rectangle(245, 255, 140, 20));
        jButton2.setEnabled(false);
        jButton2.addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent e) {
                    modificarP(e);
                }
            });
        
        jButton3.setText("Eliminar");
        jButton3.setBounds(new Rectangle(400, 255, 95, 20));
        jButton3.setEnabled(false);
        jButton3.addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent e) {
                    eliminarP(e);
                }
            });
        
        jButton4.setText("Agregar");
        jButton4.setBounds(new Rectangle(25, 255, 100, 20));
        jButton4.addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent e) {
                    agregarP(e);
                }
            });
        
        
            columna = jTable1.getColumnModel().getColumn(0);
            columna.setPreferredWidth(120);
                  
                  
            columna = jTable1.getColumnModel().getColumn(1);
            columna.setPreferredWidth(20);
                   
            columna = jTable1.getColumnModel().getColumn(2);
            columna.setPreferredWidth(20);
            
                  
        jScrollPane1.setBounds(new Rectangle(25, 65, 495, 175));

        this.getContentPane().add(jButton4, null);
        this.getContentPane().add(jButton3, null);
        this.getContentPane().add(jButton2, null);
        this.getContentPane().add(jLabel1, null);
        jScrollPane1.getViewport().add(jTable1, null);
        this.getContentPane().add(jScrollPane1, null);
        this.getContentPane().add(jButton1, null);
        this.actDatos();
        this.getAcademia();
        
    }
    
    public void setCard(JPanel car){
        cartas=car;    
    }
    
   
    public boolean getAcademia(){
        boolean ind=true;
        String sql="SELECT NOMBRE_ACADEMIA,NUM_MATERIAS,NUM_PROF FROM ACADEMIA";
        ResultSet res = conexion.getConsulta(sql);
        try{
            while(res.next()){
                Object  [] obj = new Object[3];
                for(int i=0;i<3;i++){
                    obj[i]=res.getObject(i+1);
                }
                modelo.addRow(obj);
            }
            jButton1.setEnabled(true);
            jButton2.setEnabled(true);
            jButton3.setEnabled(true);
            jButton4.setEnabled(true);
        }catch(SQLException e){
            System.out.println("Error en getacademia");
            jButton1.setEnabled(false);
            jButton2.setEnabled(false);
            jButton3.setEnabled(false);
            jButton4.setEnabled(true);
            JOptionPane.showMessageDialog(this,"No Hay Profesores En La Base De Datos","Error",JOptionPane.ERROR_MESSAGE);
        }
        return ind;
    }
    
    private void limpiar(){
        int num=jTable1.getRowCount();
        if(jTable1.getRowCount()!=0){
            for(int i=num-1;i>=0;i--){
                modelo.removeRow(i);
            }
        }
    }
    
    public void setPanel(ConsultaAcd panel){
        this.panel=panel;    
    }
    
    public boolean compAcad(String acad){
        boolean ind=true;
        String sql="SELECT NOMBRE_ACADEMIA FROM ACADEMIA WHERE NOMBRE_ACADEMIA='"+acad+"'";
        ResultSet res=conexion.getConsulta(sql);
        try{
            res.getString("NOMBRE_ACADEMIA");
            ind=false;
        }catch(SQLException e){
              ind=true;
            }
        return ind;
    }
    
    private void agregarP(MouseEvent e) {
       String acad= JOptionPane.showInputDialog(this,"Ingrese el nombre de la nueva academia","Agregar Academia",JOptionPane.PLAIN_MESSAGE);
       if(acad==null || compAcad(acad)==false ){
          JOptionPane.showMessageDialog(this,"Nombre no valido","Error",JOptionPane.ERROR_MESSAGE);
       }
       else{
           String sql="INSERT INTO ACADEMIA VALUES('"+acad.toUpperCase()+"',0,0)";
           conexion.Ingresarsentencia(sql);
           JOptionPane.showMessageDialog(this,"La academia fue agregada","Exito",JOptionPane.INFORMATION_MESSAGE);
           this.limpiar();
           this.getAcademia();
       }
      
    }

    private void modificarP(MouseEvent e) {
        if(modelo.getRowCount()==0){
            JOptionPane.showMessageDialog(this,"Error, No Ha Seleccionado Un Registro","Error",JOptionPane.ERROR_MESSAGE);
        }
        else{
            String nomb="";
            try{
                nomb=modelo.getValueAt(jTable1.getSelectedRow(),0).toString();
                String nomAct=JOptionPane.showInputDialog(this,"Ingrese El Nuevo Nombre de La Academia: "+nomb,"Modificar Nombre",JOptionPane.PLAIN_MESSAGE);
                nomAct=nomAct.toUpperCase();
                String sql="INSERT INTO ACADEMIA VALUES('"+nomAct+"',0,0)";
                conexion.Ingresarsentencia(sql);
                String sql2="UPDATE ACADEMIA_PROFESOR SET NOMBRE_ACADEMIA='"+nomAct+"' WHERE NOMBRE_ACADEMIA='"+nomb+"'";
                conexion.Ingresarsentencia(sql2);
                String sql3="UPDATE UNIDAD_APRENDIZAJE SET ACADEMIA='"+nomAct+"' WHERE ACADEMIA='"+nomb+"'";
                conexion.Ingresarsentencia(sql3);
                String sql4="UPDATE PROFESOR_UNIDAD SET ACADEMIA='"+nomAct+"' WHERE ACADEMIA='"+nomb+"'";
                conexion.Ingresarsentencia(sql4);
                String sql5="DELETE FROM ACADEMIA WHERE NOMBRE_ACADEMIA='"+nomb+"'";
                conexion.Ingresarsentencia(sql5);
                this.actDatos();
                this.limpiar();
                this.getAcademia();
            }catch(ArrayIndexOutOfBoundsException f){
                JOptionPane.showMessageDialog(this,"No Se Ha Seleccionado Nada","Error",JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void eliminarP(MouseEvent e) {
        if(modelo.getRowCount()==0){
            JOptionPane.showMessageDialog(this,"Error, No Ha Seleccionado Un Registro","Error",JOptionPane.ERROR_MESSAGE);
        }
        else{
        String nomb="";
        try{
               nomb=modelo.getValueAt(jTable1.getSelectedRow(),0).toString();
        
               int opc= JOptionPane.showConfirmDialog(this,"¿Esta Seguro de Querer Eliminar la Academia: "+nomb+" y todo lo ralacionado a ella?","Eliminar Academia",JOptionPane.YES_NO_CANCEL_OPTION,JOptionPane.WARNING_MESSAGE);
               if(opc==JOptionPane.YES_OPTION){
                   String sql="DELETE FROM ACADEMIA_PROFESOR WHERE NOMBRE_ACADEMIA='"+nomb+"'";
                   conexion.Ingresarsentencia(sql);
                   String sql2="DELETE FROM PROFESOR_UNIDAD WHERE ACADEMIA='"+nomb+"'";
                   conexion.Ingresarsentencia(sql2);
                   String sql12="DELETE FROM UNIDAD_APRENDIZAJE WHERE ACADEMIA='"+nomb+"'";
                   conexion.Ingresarsentencia(sql12);
                   String sql3="DELETE FROM ACADEMIA WHERE NOMBRE_ACADEMIA='"+nomb+"'";
                   conexion.Ingresarsentencia(sql3);
                   JOptionPane.showMessageDialog(this,"Registros Eliminados","Exito",JOptionPane.INFORMATION_MESSAGE);  
                   this.limpiar();
                   this.getAcademia();
               }
               else{
                   return;
               }
        }catch(ArrayIndexOutOfBoundsException f){
            JOptionPane.showMessageDialog(this,"No Se Ha Seleccionado Nada","Error",JOptionPane.ERROR_MESSAGE);
        }
    }
    }

    private void consultarP(MouseEvent e) {
            if(modelo.getRowCount()==0){
                JOptionPane.showMessageDialog(this,"Error, No Ha Seleccionado Un Registro","Error",JOptionPane.ERROR_MESSAGE);
            }
            else{
        String nomb="";
            
        try{
            nomb=modelo.getValueAt(jTable1.getSelectedRow(),0).toString();
            CardLayout cl = (CardLayout)(cartas.getLayout());
            cl.show(cartas,"Consulta A");
            panel.getAcademia();
            panel.setAcad(nomb);
            panel.setConsulta2(this);
            this.setVisible(false);
        }catch(ArrayIndexOutOfBoundsException f){
            JOptionPane.showMessageDialog(this,"No Se Ha Seleccionado Nada","Error",JOptionPane.ERROR_MESSAGE);
        }
        }
    }
    
    private int numProf(String acad){
        int num;
        String sql="SELECT COUNT(NOMBRE) N FROM PROFESOR_UNIDAD WHERE ACADEMIA='"+acad+"'";
        ResultSet res=conexion.getConsulta(sql);
        try{
            res.next();
            num=res.getInt("N");
        }catch(SQLException e){
            num=0;
        }
        return num;
    }
    
    private int numMat(String acad){
        int num;
        String sql="SELECT COUNT(NOMBRE) N FROM UNIDAD_APRENDIZAJE WHERE ACADEMIA='"+acad+"'";
        ResultSet res=conexion.getConsulta(sql);
        try{
            res.next();
            num=res.getInt("N");
        }catch(SQLException e){
            num=0;
        }
        return num;
    }
    
    private void actDatos(){
        ArrayList lista = new ArrayList();
        String sql="SELECT NOMBRE_ACADEMIA FROM ACADEMIA";
        ResultSet res=conexion.getConsulta(sql);
        try{
            while(res.next()){
                lista.add(res.getObject("NOMBRE_ACADEMIA"));
            }
            for(int i=0;i<lista.size();i++){
                int numProf=this.numProf(lista.get(i).toString());
                int numMat=this.numMat(lista.get(i).toString());
                String sql2="UPDATE ACADEMIA SET NUM_PROF="+numProf+",NUM_MATERIAS="+numMat+" WHERE NOMBRE_ACADEMIA='"+lista.get(i)+"'";
                conexion.Ingresarsentencia(sql2);
            }
        }catch(SQLException e){
            
        }
    }
}

    class MiRender extends DefaultTableCellRenderer {
       public Component getTableCellRendererComponent (JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
             // Llamada a clase padre para no perder el aspecto
             super.getTableCellRendererComponent (table, value,isSelected,hasFocus,row,column);
            setToolTipText(value+"");
             setHorizontalAlignment(SwingConstants.CENTER);// metodo de JLabel
             return this;
        }
    }