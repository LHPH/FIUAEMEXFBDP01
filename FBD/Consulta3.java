package FBD;

import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.Rectangle;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;

import java.awt.event.MouseEvent;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

public class Consulta3 extends JDialog {
    
    AgregarUnidad Panel;
    ConsultarUA Panel2;
    Conexion conexion=Acceso.conexion;
    JPanel cartas;
    DefaultTableModel modelo;
    TableColumn columna;
    int ind=1;
    private JButton jButton1 = new JButton();
    private JScrollPane jScrollPane1 = new JScrollPane();
    private JTable jTable1 = new JTable();
    private JComboBox jComboBox1 = new JComboBox();
    private JLabel jLabel1 = new JLabel();
    private JButton jButton2 = new JButton();
    private JButton jButton3 = new JButton();
    private JButton jButton4 = new JButton();

    public Consulta3() {
        try {
            jbInit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void jbInit() throws Exception {
        this.setSize(new Dimension(613, 345));
        this.getContentPane().setLayout( null );
        this.setTitle("Unidades de Aprendizaje");
        this.setModal(true);
        this.setDefaultCloseOperation(JDialog.HIDE_ON_CLOSE);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        
            modelo= new DefaultTableModel();
            modelo.addColumn("Nombre");
            modelo.addColumn("Clave");
            modelo.addColumn("Horas");
            //modelo.addColumn("Creditos");
            jTable1.setModel(modelo);
                  
            columna = jTable1.getColumnModel().getColumn(0);
            columna.setPreferredWidth(220);
                    
            columna = jTable1.getColumnModel().getColumn(1);
            columna.setPreferredWidth(20);
            
        columna = jTable1.getColumnModel().getColumn(2);
        columna.setPreferredWidth(20);
            
            jTable1.setDefaultRenderer(Object.class,new MiRender());
            jComboBox1.setBounds(new Rectangle(380, 20, 185, 20));


        jComboBox1.addItemListener(new ItemListener() {
                public void itemStateChanged(ItemEvent e) {
                    jComboBox1_itemStateChanged(e);
                }
            });
        jLabel1.setText("Academia:");
            jLabel1.setBounds(new Rectangle(320, 15, 50, 30));

        jButton1.setText("Consultar");
        jButton1.setBounds(new Rectangle(265, 255, 100, 20));
        jButton1.addMouseListener(new MouseAdapter() {
                      public void mouseClicked(MouseEvent e) {

                    consultarUP(e);
                }
                  });
        jButton1.setEnabled(false);
          
        jButton2.setText("Modificar");
        jButton2.setBounds(new Rectangle(370, 255, 105, 20));
        jButton2.setEnabled(false);
        jButton2.addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent e) {
                    modificarUP(e);
                }
            });
        
        jButton3.setText("Eliminar");
        jButton3.setBounds(new Rectangle(485, 255, 95, 20));
        jButton3.setEnabled(false);
        jButton3.addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent e) {
                    eliminarUP(e);
                }
            });
        
        jButton4.setText("Agregar");
        jButton4.setBounds(new Rectangle(160, 255, 100, 20));
        jButton4.addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent e) {
                    agregarUP(e);
                }
            });           
            //this.getAcademia();
                  
        jScrollPane1.setBounds(new Rectangle(25, 65, 540, 175));
        jScrollPane1.getViewport().add(jTable1, null);

        this.getContentPane().add(jButton4, null);
        this.getContentPane().add(jButton3, null);
        this.getContentPane().add(jButton2, null);
        this.getContentPane().add(jLabel1, null);
        this.getContentPane().add(jComboBox1, null);
        this.getContentPane().add(jScrollPane1, null);
        this.getContentPane().add(jButton1, null);
    }
    
    public void initConexion(){
        conexion=Acceso.conexion;
    }
    
    public void setCard(JPanel car){
        cartas=car;    
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
            jButton1.setEnabled(true);
            jButton2.setEnabled(true);
            jButton3.setEnabled(true);
            jButton4.setEnabled(true);
        }catch(SQLException e){
            System.out.println("Error en getacademia");
            jButton1.setEnabled(false);
            jButton2.setEnabled(false);
            jButton3.setEnabled(false);
            jButton4.setEnabled(false);
            JOptionPane.showMessageDialog(this,"No Hay Profesores En La Base De Datos","Error",JOptionPane.ERROR_MESSAGE);
            ind=false;
        }
        return ind;
    }
    
    public void setInfoTable(){
        String sql="";
            sql="SELECT NOMBRE,CLAVE_UA,T_HORAS FROM UNIDAD_APRENDIZAJE WHERE ACADEMIA='"+jComboBox1.getSelectedItem()+"'";
            ResultSet res = conexion.getConsulta(sql);
            try{
                while(res.next()){
                    Object [] a =new Object[3];
                    for(int i=0;i<3;i++){
                        a[i]=res.getObject(i+1);
                    }
                    modelo.addRow(a);
                }
            }catch(SQLException e){
                System.out.println("Error en SetInfo Table: 1");
            }
    }
    
    public void limpiar(){
        int num=jTable1.getRowCount();
        if(jTable1.getRowCount()!=0){
            for(int i=num-1;i>=0;i--){
                modelo.removeRow(i);
            }
        }
    }
    
    public void setPanel(AgregarUnidad panel,ConsultarUA panel2){
        this.Panel=panel;    
        this.Panel2=panel2;
    }
    

    private void agregarUP(MouseEvent e) {
        try{
                CardLayout cl = (CardLayout)(cartas.getLayout());
                cl.show(cartas,"Agregar U");
                Panel.getAcademia();  
                Panel.setConsulta3(this);
                this.setVisible(false);
        }catch(ArrayIndexOutOfBoundsException f){
            JOptionPane.showMessageDialog(this,"No Se Ha Seleccionado Nada","Error",JOptionPane.ERROR_MESSAGE);
        }
    }

    private void modificarUP(MouseEvent e) {
        if(modelo.getRowCount()==0){
            JOptionPane.showMessageDialog(this,"Error, No Ha Seleccionado Un Registro","Error",JOptionPane.ERROR_MESSAGE);
        }
        else{
            String nomb="";
            try{
                nomb=modelo.getValueAt(jTable1.getSelectedRow(),0).toString();
                if(jComboBox1.getItemCount()!=0 ){
                    CardLayout cl = (CardLayout)(cartas.getLayout());
                    cl.show(cartas,"Agregar U");
                    Panel.getAcademia();
                    Panel.setInfo(nomb); 
                    Panel.setConsulta3(this);
                    this.setVisible(false);
                }else{
                  JOptionPane.showMessageDialog(this,"No Se Ha Seleccionado Nada","Error",JOptionPane.ERROR_MESSAGE);   
                }
            }catch(ArrayIndexOutOfBoundsException f){
                JOptionPane.showMessageDialog(this,"No Se Ha Seleccionado Nada","Error",JOptionPane.ERROR_MESSAGE);
            }
        }
        
        
    }

    private void eliminarUP(MouseEvent e) {
        if(modelo.getRowCount()==0){
            JOptionPane.showMessageDialog(this,"Error, No Ha Seleccionado Un Registro","Error",JOptionPane.ERROR_MESSAGE);
        }
        else{
        String nomb="";
        try{
            nomb=modelo.getValueAt(jTable1.getSelectedRow(),0).toString();
            if(jComboBox1.getItemCount()!=0 ){
               int opc= JOptionPane.showConfirmDialog(this,"¿Esta Seguro de Querer Eliminar la Unidad de Aprendizaje: "+nomb+" ?","Eliminar Profesor",JOptionPane.YES_NO_CANCEL_OPTION,JOptionPane.INFORMATION_MESSAGE);
               if(opc==JOptionPane.YES_OPTION){
                   String sql="DELETE FROM PROFESOR_UNIDAD WHERE MATERIA='"+nomb+"'";
                   conexion.Ingresarsentencia(sql);
                   String sql2="DELETE FROM UNIDAD_APRENDIZAJE WHERE NOMBRE='"+nomb+"'";
                   conexion.Ingresarsentencia(sql2);
                   JOptionPane.showMessageDialog(this,"Registros Eliminados","Exito",JOptionPane.INFORMATION_MESSAGE);  
                   this.limpiar();
                   this.setInfoTable();
               }
               else{
                   return;
               }
            }else{
              JOptionPane.showMessageDialog(this,"No Se Ha Seleccionado Nada","Error",JOptionPane.ERROR_MESSAGE);   
            }
        }catch(ArrayIndexOutOfBoundsException f){
            JOptionPane.showMessageDialog(this,"No Se Ha Seleccionado Nada","Error",JOptionPane.ERROR_MESSAGE);
        }
    }
    }

    private void consultarUP(MouseEvent e) {
            if(modelo.getRowCount()==0){
               JOptionPane.showMessageDialog(this,"Error, No hay ninguna materia disponible","Error",JOptionPane.ERROR_MESSAGE);
            }
            else{
        String nomb="";
            
        try{
            nomb=modelo.getValueAt(jTable1.getSelectedRow(),0).toString();
            if(jComboBox1.getItemCount()!=0 ){
                CardLayout cl = (CardLayout)(cartas.getLayout());
                cl.show(cartas,"Consulta U");
                Panel2.getMaterias(jComboBox1.getSelectedItem().toString());
                Panel2.getAcademia();
                Panel2.setInfo(nomb);
                Panel2.setConsulta3(this);
                this.setVisible(false);
            }
        }catch(ArrayIndexOutOfBoundsException h){
            System.out.println("Error");
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
}

