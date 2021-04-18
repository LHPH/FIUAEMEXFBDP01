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

public class Consulta extends JDialog {
    
    AgregarProfesor Panel;
    ConsultaP Panel2;
    Conexion conexion=Acceso.conexion;
    JPanel cartas;
    DefaultTableModel modelo;
    TableColumn columna;
    int ind=1,ind2=1;
    private JButton jButton1 = new JButton();
    private JScrollPane jScrollPane1 = new JScrollPane();
    private JTable jTable1 = new JTable();
    private JComboBox jComboBox1 = new JComboBox();
    private JRadioButton jRadioButton1 = new JRadioButton();
    private JRadioButton jRadioButton2 = new JRadioButton();
    private JLabel jLabel1 = new JLabel();
    private JLabel jLabel2 = new JLabel();
    private JComboBox jComboBox2 = new JComboBox();
    private JButton jButton2 = new JButton();
    private JButton jButton3 = new JButton();
    private JButton jButton4 = new JButton();

    public Consulta() {
        try {
            jbInit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void jbInit() throws Exception {
        this.setSize(new Dimension(613, 345));
        this.getContentPane().setLayout( null );
        this.setTitle("Profesor");
        this.setModal(true);
        this.setDefaultCloseOperation(JDialog.HIDE_ON_CLOSE);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        
        modelo= new DefaultTableModel();
            modelo.addColumn("Nombre");
            modelo.addColumn("Academia");
            modelo.addColumn("Materia");
            modelo.addColumn("Semestre");
                  
            jTable1.setModel(modelo);
            jTable1.setDefaultRenderer(Object.class,new MiRender());
            
        columna = jTable1.getColumnModel().getColumn(0);
        columna.setPreferredWidth(120);
              
              
        columna = jTable1.getColumnModel().getColumn(1);
        columna.setPreferredWidth(80);
               
        columna = jTable1.getColumnModel().getColumn(2);
        columna.setPreferredWidth(80);
        
        columna = jTable1.getColumnModel().getColumn(3);
        columna.setPreferredWidth(20);
        
            
            jComboBox1.setBounds(new Rectangle(250, 15, 160, 20));

        jComboBox1.addItemListener(new ItemListener() {
                public void itemStateChanged(ItemEvent e) {
                    jComboBox1_itemStateChanged(e);
                }
            });
        jRadioButton1.setText("Academia");
            jRadioButton1.setBounds(new Rectangle(95, 15, 75, 25));
            jRadioButton1.addMouseListener(new MouseAdapter() {
                          public void mouseClicked(MouseEvent e) {
                              jRadioButton1_mouseClicked(e);
                          }
                      });
                   
            jRadioButton2.setText("Materia");
            jRadioButton2.setBounds(new Rectangle(175, 15, 70, 25));
            jRadioButton2.addMouseListener(new MouseAdapter() {
                          public void mouseClicked(MouseEvent e) {
                              jRadioButton2_mouseClicked(e);
                          }
                      });
                  
            jLabel1.setText("Consultar por:");
            jLabel1.setBounds(new Rectangle(10, 10, 100, 30));

        jLabel2.setText("Semestre:");
        jLabel2.setBounds(new Rectangle(415, 15, 55, 25));
        
        jComboBox2.setBounds(new Rectangle(470, 15, 110, 20));
       /* jComboBox2.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    jComboBox2_actionPerformed(e);
                }
            });*/

        jComboBox2.addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent e) {
                    jComboBox2_mouseClicked(e);
                }
            });
        jComboBox2.addItemListener(new ItemListener() {
                public void itemStateChanged(ItemEvent e) {
                    jComboBox2_itemStateChanged(e);
                }
            });
        jButton1.setText("Consultar");
        jButton1.setBounds(new Rectangle(265, 255, 100, 20));
        jButton1.addMouseListener(new MouseAdapter() {
                      public void mouseClicked(MouseEvent e) {

                    consultarP(e);
                }
                  });
        jButton1.setEnabled(false);
          
        jButton2.setText("Modificar");
        jButton2.setBounds(new Rectangle(370, 255, 105, 20));
        jButton2.setEnabled(false);
        jButton2.addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent e) {
                    modificarP(e);
                }
            });
        
        jButton3.setText("Eliminar");
        jButton3.setBounds(new Rectangle(485, 255, 95, 20));
        jButton3.setEnabled(false);
        jButton3.addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent e) {
                    eliminarP(e);
                }
            });
        
        jButton4.setText("Agregar");
        jButton4.setBounds(new Rectangle(160, 255, 100, 20));
        jButton4.addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent e) {
                    agregarP(e);
                }
            });    
            //this.getAcademia();
                  
        jScrollPane1.setBounds(new Rectangle(25, 65, 540, 175));
        jScrollPane1.getViewport().add(jTable1, null);

        this.getContentPane().add(jButton4, null);
        this.getContentPane().add(jButton3, null);
        this.getContentPane().add(jButton2, null);
        this.getContentPane().add(jComboBox2, null);
        this.getContentPane().add(jLabel2, null);
        this.getContentPane().add(jLabel1, null);
        this.getContentPane().add(jRadioButton2, null);
        this.getContentPane().add(jRadioButton1, null);
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
    
    public void getMaterias(){
        boolean ind=true;
        String sql="SELECT NOMBRE FROM UNIDAD_APRENDIZAJE";
        ResultSet res =conexion.getConsulta(sql);
        try{
            while(res.next()){
                Object obj = res.getObject("NOMBRE");
                jComboBox1.addItem(obj);
            }
            ind=true;
            this.getSemestre();
        }catch(SQLException e){
            System.out.println("Error en getMaterias");
        }
    }
   
    public boolean getAcademia(){
        boolean ind=true;
        String sql="SELECT NOMBRE_ACADEMIA FROM ACADEMIA";
        if(conexion==null)
            System.out.println("ASI ES");
        ResultSet res = conexion.getConsulta(sql);
        try{
            while(res.next()){
                Object obj = res.getObject("NOMBRE_ACADEMIA");
                jComboBox1.addItem(obj);
            }
            ind=true;
            this.getSemestre();
        }catch(SQLException e){
            System.out.println("Error en getacademia");
            jRadioButton1.setEnabled(false);
            jRadioButton2.setEnabled(false);
            jButton2.setEnabled(false);
            jButton3.setEnabled(false);
            jButton4.setEnabled(false);
            JOptionPane.showMessageDialog(this,"No Hay Profesores En La Base De Datos","Error",JOptionPane.ERROR_MESSAGE);
            ind=false;
        }
        return ind;
    }
    
    public void getSemestre(){
        String sql="SELECT DISTINCT SEMESTRE FROM PROFESOR_UNIDAD ORDER BY SEMESTRE ASC";
        ResultSet res=conexion.getConsulta(sql);
        try{
            while(res.next()){
                Object a = res.getObject("SEMESTRE");
                jComboBox2.addItem(a);
            }
            jButton1.setEnabled(true);
            jButton2.setEnabled(true);
            jButton3.setEnabled(true);
            jButton4.setEnabled(true);
        }catch(SQLException e){
            
        }
    }
    
    
    public void setInfoTable(){
        String sql="";
            if(jRadioButton1.isSelected()==true){
            
            sql="SELECT NOMBRE,ACADEMIA,MATERIA,SEMESTRE FROM PROFESOR_UNIDAD WHERE ACADEMIA='"+jComboBox1.getSelectedItem()+"' AND SEMESTRE='"+jComboBox2.getSelectedItem()+"' ORDER BY MATERIA ASC";
            System.out.println(sql);
            ResultSet res = conexion.getConsulta(sql);
            try{

                while(res.next()){
                    Object [] a =new Object[4];
                    for(int i=0;i<4;i++){
                        a[i]=res.getObject(i+1);
                    }
                    
                    modelo.addRow(a);
                }
            }catch(SQLException e){
                System.out.println("Error en SetInfo Table: 1");
            }
            }
            if(jRadioButton2.isSelected()==true){
                sql="SELECT NOMBRE,ACADEMIA,MATERIA,SEMESTRE FROM PROFESOR_UNIDAD WHERE MATERIA='"+jComboBox1.getSelectedItem()+"' AND SEMESTRE='"+jComboBox2.getSelectedItem()+"'";
                ResultSet res = conexion.getConsulta(sql);
                try{
                    while(res.next()){
                        Object [] a =new Object[4];
                        for(int i=0;i<4;i++){
                            a[i]=res.getObject(i+1);
                        }
                        modelo.addRow(a);
                    }
                }catch(SQLException e){
                    System.out.println("Error en SetInfo Table: 1");
                }
            }
    }
    
    private void jRadioButton1_mouseClicked(MouseEvent e) {
        if(jRadioButton1.isSelected()==true){
            jComboBox1.removeAllItems();
            jRadioButton1.setSelected(true);
            jRadioButton2.setSelected(false);
            this.limpiar();
            this.getAcademia();
            this.setInfoTable();
        }
    }
    
    private void jRadioButton2_mouseClicked(MouseEvent e) {
        if(jRadioButton2.isSelected()==true){
            jComboBox1.removeAllItems();
            jRadioButton1.setSelected(false);
            jRadioButton2.setSelected(true);
            this.limpiar();
            this.getMaterias();
            this.limpiar();
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
    
    private void jComboBox2_mouseClicked(MouseEvent e) {
        if(jComboBox2.getItemCount()!=0 && ind2>2) {
            this.limpiar();
            this.setInfoTable();
        }
        ind2++;
    }
    
    public void setPanel(AgregarProfesor panel){
        this.Panel=panel;    
    }
    
    public void setPanel2(ConsultaP panel){
        Panel2=panel;
    }

    private void agregarP(MouseEvent e) {
        try{
           // nomb=modelo.getValueAt(jTable1.getSelectedRow(),0).toString();
                CardLayout cl = (CardLayout)(cartas.getLayout());
                cl.show(cartas,"Agregar Profesor");
                Panel.getAcademias();
                Panel.setMaterias();
                Panel.setConsulta(this);
                //Panel.setInfo("Luis Ponce Hermosillo");      
                this.setVisible(false);
        }catch(ArrayIndexOutOfBoundsException f){
            JOptionPane.showMessageDialog(this,"No Se Ha Seleccionado Nada","Error",JOptionPane.ERROR_MESSAGE);
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
                if(jComboBox1.getItemCount()!=0 ){
                    CardLayout cl = (CardLayout)(cartas.getLayout());
                    cl.show(cartas,"Agregar Profesor");
                    Panel.getAcademias();
                    Panel.setMaterias();
                    Panel.setInfo(nomb);
                    Panel.setConsulta(this);
                    this.setVisible(false);
                }else{
                  JOptionPane.showMessageDialog(this,"No Se Ha Seleccionado Nada","Error",JOptionPane.ERROR_MESSAGE);   
                }
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
            if(jComboBox1.getItemCount()!=0 ){
               int opc= JOptionPane.showConfirmDialog(this,"¿Esta Seguro de Querer Eliminar al Profesor: "+nomb+" ?","Eliminar Profesor",JOptionPane.YES_NO_CANCEL_OPTION,JOptionPane.INFORMATION_MESSAGE);
               if(opc==JOptionPane.YES_OPTION){
                   String sql="DELETE FROM ACADEMIA_PROFESOR WHERE NOMBREP='"+nomb+"'";
                   conexion.Ingresarsentencia(sql);
                   String sql2="DELETE FROM PROFESOR_UNIDAD WHERE NOMBRE='"+nomb+"'";
                   conexion.Ingresarsentencia(sql2);
                   String sql3="DELETE FROM PROFESOR WHERE NOMBRE||' '||A_PATERNO||' '||A_MATERNO='"+nomb+"'";
                   conexion.Ingresarsentencia(sql3);
                   JOptionPane.showMessageDialog(this,"Registros Eliminados","Exito",JOptionPane.INFORMATION_MESSAGE);  
                   this.limpiar();
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

    private void consultarP(MouseEvent e) {
            if(modelo.getRowCount()==0){
                JOptionPane.showMessageDialog(this,"Error, No Ha Seleccionado Un Registro","Error",JOptionPane.ERROR_MESSAGE);
            }
            else{
        String nomb="";
            
        try{
            nomb=modelo.getValueAt(jTable1.getSelectedRow(),0).toString();
            if(jComboBox1.getItemCount()!=0 ){
                CardLayout cl = (CardLayout)(cartas.getLayout());
                cl.show(cartas,"Consultar P");
                Panel2.setInfo(nomb);
                Panel2.setConsulta(this);
                this.setVisible(false);
            }else{
              JOptionPane.showMessageDialog(this,"No Se Ha Seleccionado Nada","Error",JOptionPane.ERROR_MESSAGE); 
                System.out.println("sss");
            }
        }catch(ArrayIndexOutOfBoundsException f){
            JOptionPane.showMessageDialog(this,"No Se Ha Seleccionado Nada","Error",JOptionPane.ERROR_MESSAGE);
            System.out.println("xxx");
        }
        }
    }

    private void jComboBox1_itemStateChanged(ItemEvent e) {
        if(jComboBox1.getItemCount()!=0 && ind>2){
            this.limpiar();
            this.setInfoTable();
        }
        ind++;
    }

    private void jComboBox2_itemStateChanged(ItemEvent e) {
        if(jComboBox1.getItemCount()!=0 && ind2>2){
            this.limpiar();
            this.setInfoTable();
        }
        ind2++;
    }
}
