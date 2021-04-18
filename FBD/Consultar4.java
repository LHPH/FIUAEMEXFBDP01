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

import java.text.SimpleDateFormat;

import java.util.ArrayList;

import java.util.Date;
import java.util.GregorianCalendar;

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

public class Consultar4 extends JDialog {
    
    AgregarReunion Panel;
    ConsultaR Panel2;
    Asistencia asi;
    Conexion conexion=Acceso.conexion;
    JPanel cartas;
    DefaultTableModel modelo;
    TableColumn columna;
    int ind=1,ind2=1;
    ArrayList<Integer> ID = new ArrayList<Integer>();
    ArrayList<Date> Fecha = new ArrayList<Date>();
    private JButton jButton1 = new JButton();
    private JScrollPane jScrollPane1 = new JScrollPane();
    private JTable jTable1 = new JTable();
    private JComboBox jComboBox1 = new JComboBox();
    private JLabel jLabel1 = new JLabel();
    private JButton jButton2 = new JButton();
    private JButton jButton3 = new JButton();
    private JButton jButton4 = new JButton();
    private JComboBox jComboBox2 = new JComboBox();
    private JLabel jLabel2 = new JLabel();
    private JButton jButton5 = new JButton();

    public Consultar4() {
        try {
            jbInit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void jbInit() throws Exception {
        this.setSize(new Dimension(613, 345));
        this.getContentPane().setLayout( null );
        this.setTitle("Reuniones de Academia y Actas");
        this.setModal(true);
        this.setDefaultCloseOperation(JDialog.HIDE_ON_CLOSE);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        
            modelo= new DefaultTableModel();
            modelo.addColumn("Fecha");
            modelo.addColumn("Asunto");
            modelo.addColumn("Academia");
            modelo.addColumn("Semestre");
            //modelo.addColumn("Creditos");
            
            jTable1.setModel(modelo);
            
        columna = jTable1.getColumnModel().getColumn(0);
        columna.setPreferredWidth(20);
                  
                  
        columna = jTable1.getColumnModel().getColumn(1);
        columna.setPreferredWidth(150);
            
        columna = jTable1.getColumnModel().getColumn(2);
        columna.setPreferredWidth(150);
              
              
        columna = jTable1.getColumnModel().getColumn(3);
        columna.setPreferredWidth(10);
        
        jTable1.setDefaultRenderer(Object.class,new MiRender());
        
            jComboBox1.setBounds(new Rectangle(215, 20, 220, 20));
            jComboBox1.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        jComboBox1_actionPerformed(e);
                    }
                });


        jComboBox1.addItemListener(new ItemListener() {
                public void itemStateChanged(ItemEvent e) {
                    jComboBox1_itemStateChanged(e);
                }
            });
        jLabel1.setText("Academia:");
            jLabel1.setBounds(new Rectangle(145, 15, 50, 30));


        /* jComboBox2.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    jComboBox2_actionPerformed(e);
                }
            });*/

        jButton1.setText("Consultar");
        jButton1.setBounds(new Rectangle(135, 255, 100, 20));
        jButton1.addMouseListener(new MouseAdapter() {
                      public void mouseClicked(MouseEvent e) {

                    consultarUP(e);
                }
                  });
        jButton1.setEnabled(false);
          
        jButton2.setText("Modificar");
        jButton2.setBounds(new Rectangle(240, 255, 105, 20));
        jButton2.setEnabled(false);
        jButton2.addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent e) {
                    modificarUP(e);
                }
            });
        
        jButton3.setText("Eliminar");
        jButton3.setBounds(new Rectangle(350, 255, 95, 20));
        jButton3.setEnabled(false);
        jButton3.addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent e) {
                    eliminarUP(e);
                }
            });
        
        jButton4.setText("Agregar");
        jButton4.setBounds(new Rectangle(25, 255, 100, 20));
        jButton4.addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent e) {
                    agregarR(e);
                }
            });            
            
                   
            //this.getAcademia();

        jComboBox2.setBounds(new Rectangle(520, 20, 80, 20));

        jComboBox2.addItemListener(new ItemListener() {
                public void itemStateChanged(ItemEvent e) {
                    jComboBox2_itemStateChanged(e);
                }
            });
        jLabel2.setText("Semestre");
        jLabel2.setBounds(new Rectangle(445, 20, 75, 20));
        
        jButton5.setText("Asistencia");
        jButton5.setBounds(new Rectangle(455, 255, 90, 20));

        jButton5.addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent e) {
                    asistencia(e);
                }
            });
        jScrollPane1.setBounds(new Rectangle(25, 65, 540, 175));
        jScrollPane1.getViewport().add(jTable1, null);

        this.getContentPane().add(jButton5, null);
        this.getContentPane().add(jLabel2, null);
        this.getContentPane().add(jComboBox2, null);
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
            this.getSemestre();
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
    
    public void getSemestre(){
        String sql="SELECT DISTINCT SEMESTRE FROM REUNION ORDER BY SEMESTRE ASC";
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
        if(ID.size()!=0){
            ID.clear();
        }
        if(Fecha.size()!=0){
            Fecha.clear();
        }
        String sql="";
            sql="SELECT ID_REUNION,FECHA,ASUNTO,NOMBRE_ACADEMIA,SEMESTRE FROM REUNION WHERE NOMBRE_ACADEMIA='"+jComboBox1.getSelectedItem()+"' AND SEMESTRE='"+jComboBox2.getSelectedItem()+"' ORDER BY FECHA ASC,ID_REUNION ASC";
            ResultSet res = conexion.getConsulta(sql);
            try{
                while(res.next()){
                    ID.add(res.getInt("ID_REUNION"));
                    Object [] a =new Object[4];
                    SimpleDateFormat conv= new SimpleDateFormat("dd/MM/yyyy");
                    a[0]=conv.format(res.getDate("FECHA"));//convertir la fecha a un string
                    Fecha.add(res.getDate("FECHA"));
                    for(int i=1;i<4;i++){
                        
                        a[i]=res.getObject(i+2);
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

    private void jComboBox1_actionPerformed(ActionEvent e) {
        
    }
    
    public void setPanel(AgregarReunion panel,Asistencia as,ConsultaR cr){
        this.Panel=panel;    
        this.asi=as;
        this.Panel2=cr;
    }
    
    public void setPanel(AgregarReunion panel){
        this.Panel=panel; 
    }
    
    public void setPanel(Asistencia panel){
        this.asi=panel;
    }
    public void setPanel(ConsultaR panel){
        this.Panel2=panel;
    }
    
    public boolean hayProfesor(String area){
            boolean ind=true;
            String sql="SELECT DISTINCT COUNT(NOMBREP) AS C FROM ACADEMIA_PROFESOR WHERE NOMBRE_ACADEMIA='"+area+"'";
            ResultSet res=conexion.getConsulta(sql);
            try{
                res.next();
                if(res.getInt("C")>0){
                    ind=true;
                }
                else{
                    ind=false;
                }
            }catch(SQLException e){
                ind=false;
            }
            return ind;
        }
        
    private void agregarR(MouseEvent e) {
        if(this.hayProfesor(jComboBox1.getSelectedItem().toString())==true){
            try{
                int opc=JOptionPane.showConfirmDialog(this,"¿Agregar Reunion de "+jComboBox1.getSelectedItem()+"?","Agregar Reunion",JOptionPane.YES_NO_OPTION,JOptionPane.PLAIN_MESSAGE);
                if(opc==JOptionPane.YES_OPTION){
                    CardLayout cl = (CardLayout)(cartas.getLayout());
                    cl.show(cartas,"Agregar R");
                    Panel.setAcademia(jComboBox1.getSelectedItem().toString());  
                    Panel.setConsulta(this);
                    this.setVisible(false);
                }else{
                    return;
                }
            }catch(ArrayIndexOutOfBoundsException f){
                JOptionPane.showMessageDialog(this,"No Se Ha Seleccionado Nada","Error",JOptionPane.ERROR_MESSAGE);
            }
        }
        else{
            JOptionPane.showMessageDialog(this,"No se puede agregar una reunion hasta que por lo menos\n se encuentre un profesor registrado en la academia","Error",JOptionPane.ERROR_MESSAGE);
        }
    }

    private void modificarUP(MouseEvent e) {
        if(modelo.getRowCount()==0){
            JOptionPane.showMessageDialog(this,"Error, No Ha Seleccionado Una Reunion","Error",JOptionPane.ERROR_MESSAGE);
        }
        else{
            GregorianCalendar a = new GregorianCalendar();
            Date fechaAct =a.getGregorianChange();
            if(fechaAct.before(Fecha.get(jTable1.getSelectedRow()))){
                String fecha="";
                try{
                    fecha=modelo.getValueAt(jTable1.getSelectedRow(),0).toString();
                    String acad=modelo.getValueAt(jTable1.getSelectedRow(),2).toString();
                    if(jComboBox1.getItemCount()!=0 ){
                        CardLayout cl = (CardLayout)(cartas.getLayout());
                        cl.show(cartas,"Agregar R");    
                        System.out.println(ID.size());
                       Panel.setInfo(ID.get(jTable1.getSelectedRow())); 
                       Panel.setConsulta(this);
                        this.setVisible(false);
                    }else{
                      JOptionPane.showMessageDialog(this,"No Ha Seleccionado una Reunion","Error",JOptionPane.ERROR_MESSAGE);   
                    }
                }catch(ArrayIndexOutOfBoundsException f){
                    JOptionPane.showMessageDialog(this,"No ha Seleccionado una Reunion","Error",JOptionPane.ERROR_MESSAGE);
                }
            }else{
                JOptionPane.showMessageDialog(this,"La Reunion ya no se puede modificar","Aviso",JOptionPane.INFORMATION_MESSAGE);
            }
           
        }
        
        
    }

    private void eliminarUP(MouseEvent e) {
        if(modelo.getRowCount()==0){
            JOptionPane.showMessageDialog(this,"Error, No Ha Seleccionado Un Registro","Error",JOptionPane.ERROR_MESSAGE);
        }
        else{
        String fecha="";
        try{
            fecha=modelo.getValueAt(jTable1.getSelectedRow(),0).toString();
            if(jComboBox1.getItemCount()!=0 ){
               int opc= JOptionPane.showConfirmDialog(this,"¿Esta Seguro de Querer Eliminar Esta Reunion?","Eliminar Reunion",JOptionPane.YES_NO_OPTION,JOptionPane.INFORMATION_MESSAGE);
               if(opc==JOptionPane.YES_OPTION){
               
                   //String acad=modelo.getValueAt(jTable1.getSelectedRow(),2).toString();
                   //int id=this.getIDR(fecha,acad);
                   String sql="DELETE FROM PROFESOR_REUNION WHERE ID_REUNION="+ID.get(jTable1.getSelectedRow());
                   conexion.Ingresarsentencia(sql);
                   String sql2="DELETE FROM REUNION WHERE ID_REUNION="+ID.get(jTable1.getSelectedRow())+"";
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
                cl.show(cartas,"Consulta R");
                Panel2.setInfo(ID.get(jTable1.getSelectedRow()));
                Panel2.setConsulta4(this);
                this.setVisible(false);
            }
        }catch(ArrayIndexOutOfBoundsException h){
            System.out.println("Error");
        }
                
    }
  }
    
    public int getIDR(String fecha,String acad){
        String sql="SELECT ID_REUNION FROM REUNION WHERE FECHA=TO_DATE('"+fecha+"','DD MM YYYY') AND NOMBRE_ACADEMIA='"+acad+"'";
        ResultSet res =conexion.getConsulta(sql);
        int ind=0;
        try{
            res.next();
            ind=res.getInt("ID_REUNION");
        }catch(SQLException e){
            ind=0;
        }
        return ind;
    }

    private void jComboBox2_itemStateChanged(ItemEvent e) {
        if(e.getStateChange()==ItemEvent.SELECTED && ind>2){
            this.limpiar();
            this.setInfoTable();
        }
        ind++;
    }

    private void asistencia(MouseEvent e) {
        CardLayout cl = (CardLayout)(cartas.getLayout());
        cl.show(cartas,"Asistencia");
        asi.setConsulta4(this);
        asi.getAcademia();
        asi.setInfoTable();
        this.setVisible(false);
    }

    private void jComboBox1_itemStateChanged(ItemEvent e) {
        if(e.getStateChange()==ItemEvent.SELECTED && ind2>2){
            this.limpiar();
            this.setInfoTable();
        }
        ind2++;
    }
}

