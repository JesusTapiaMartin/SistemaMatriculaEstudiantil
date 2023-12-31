package Vista;

import Clases.*;
import Controlador.*;
import com.formdev.flatlaf.intellijthemes.FlatDarkPurpleIJTheme;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.List;


/**
 * Clase FmrAgregarAlumnos: Ventana para agregar, modificar y eliminar alumnos
 */
public class FmrAgregarAlumnos extends JDialog {
    // ========== ATRIBUTOS ==========
    private Alumno nuevoAlumno;

    // ----- PANEL -----
    private JPanel AgregarAlumno;
    private JPanel AgregarAlumnos;
    private JPanel AgregarApoderados;
    private JPanel Apoderado;
    private JPanel Alumno;

    // ----- TABBED PANEL -----
    private JTabbedPane tabbedPane1;

    // ----- JTABLE -----
    private JTable tblListado;

    // ----- TXT -----
    private JTextField txtRut;
    private JTextField txtNombres;
    private JTextField txtApellidos;
    private JTextField txtEdad;
    private JTextField txtFechaNacimiento;
    private JTextField txtDireccion;
    private JTextField txtEmail;
    private JTextField txtNacionalidad;
    private JTextField txtTelefono;
    private JTextField txtCiudad;
    private JTextField txtDatosAdicionales;
    private JTextField txtFechaMatricula;
    private JTextField txtCurso;
    private JTextField txtLetra;
    private JTextField txtElectivo;
    private JTextField txtEnfermedades;
    private JTextField txtCiudadApoderado;
    private JTextField txtRutApoderado;
    private JTextField txtNombresApoderado;
    private JTextField txtApellidosApoderado;
    private JTextField txtParentescoApoderado;
    private JTextField txtTelefonoApoderado;
    private JTextField txtDireccionApoderado;
    private JTextField txtObservacionesApoderado;

    // ----- BUTTON -----
    private JButton btnGrabar;
    private JButton btnLimpiar;
    private JButton btnSalir;
    private JButton btnBorrar;
    private JButton btnModificar;
    private JButton btnListar;
    private JButton btnBuscar;
    private JButton btnLimpiarApoderado;

    // ----- BUTTON GROUP -----
    private ButtonGroup grupoBotonesGenero;
    private ButtonGroup grupoBotonesGeneroApoderado;

    // ----- RDO -----
    private JRadioButton rdoMasculino;
    private JRadioButton rdoFemenino;
    private JRadioButton rdoOtro;
    private JRadioButton rdoMasculinoApoderado;
    private JRadioButton rdoFemeninoApoderado;
    private JRadioButton rdoOtroApoderado;

    // ----- LBL -----
    private JLabel lblDatos;
    private JLabel lblRut;
    private JLabel lblNombres;
    private JLabel lblApellidos;
    private JLabel lblEdad;
    private JLabel lblFechaNacimiento;
    private JLabel lblDireccion;
    private JLabel lblCiudad;
    private JLabel lblTelefono;
    private JLabel lblEmail;
    private JLabel lblNacionalidad;
    private JLabel lblDatosAdicionales;
    private JLabel lblFechaMatricula;
    private JLabel lblGenero;
    private JLabel lblEnfermedades;
    private JLabel lblCurso;
    private JLabel lblLetra;
    private JLabel lblElectivo;
    private JLabel lblAgregarAlumno;
    private JLabel lblAgregarApoderado;
    private JLabel lblRutApoderado;
    private JLabel lblNombresApoderado;
    private JLabel lblApellidosApoderado;
    private JLabel lblParentescoApoderado;
    private JLabel lblTelefonoApoderado;
    private JLabel lblCiudadApoderado;
    private JLabel lblDireccionApoderado;
    private JLabel lblGeneroApoderado;
    private JLabel lblObservacionesApoderado;

    // ----- COMBO BOX -----
    private JComboBox boxNacionalidad;
    private JComboBox boxCurso;
    private JComboBox boxLetra;
    private JComboBox boxElectivo;


    // ========== CONSTRUCTOR ==========
    public FmrAgregarAlumnos() {
        // ----- APARIENCIA -----
        try {
            UIManager.setLookAndFeel(new FlatDarkPurpleIJTheme());
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }

        // ----- PROPIEDADES -----
        setTitle("Agregar Alumno");
        setLocationRelativeTo(null);
        setModal(true);
        setSize(1000, 700);
        setResizable(false);
        setContentPane(AgregarAlumno);
        cargarListadoAlumnos(null);

        // ----- GRUPO DE BOTONES -----
        this.grupoBotonesGenero = new ButtonGroup();
        grupoBotonesGenero.add(rdoFemenino);
        grupoBotonesGenero.add(rdoMasculino);
        grupoBotonesGenero.add(rdoOtro);

        this.grupoBotonesGeneroApoderado = new ButtonGroup();
        grupoBotonesGeneroApoderado.add(rdoFemeninoApoderado);
        grupoBotonesGeneroApoderado.add(rdoMasculinoApoderado);
        grupoBotonesGeneroApoderado.add(rdoOtroApoderado);

        // ----- COMBO BOX -----
        String[] nacionalidades = {"Chilena", "Venezolana", "Haitiana", "Otra"};
        String[] cursos         = {"1°", "2°", "3°", "4°"};
        String[] letras         = {"A", "B", "C", "D"};
        String[] electivos      = {"Telecomunicaciones", "Agropecuaria"};

        boxNacionalidad.setModel(new DefaultComboBoxModel<>(nacionalidades));
        boxCurso.setModel(new DefaultComboBoxModel<>(cursos));
        boxLetra.setModel(new DefaultComboBoxModel<>(letras));
        boxElectivo.setModel(new DefaultComboBoxModel<>(electivos));

        comboBoxVacio();


        // ----- BOTÓN LIMPIAR ALUMNO-----
        btnLimpiar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                limpiar();
            }
        });


        // ----- BOTÓN LIMPIAR APODERADO -----
        btnLimpiarApoderado.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                limpiarApoderado();
            }
        });


        // ----- BOTÓN SALIR -----
        btnSalir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);


        // ----- BOTÓN BUSCAR ALUMNO POR RUT -----
        btnBuscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buscarAlumnoPorRut();
                btnLimpiar.setEnabled(false);
                btnLimpiarApoderado.setEnabled(false);
            }
        });


        // ----- BOTÓN LISTAR -----
        btnListar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cargarListadoAlumnos(null);
                limpiar();
                limpiarApoderado();
                btnBorrar.setEnabled(false);
                btnModificar.setEnabled(false);
                btnGrabar.setEnabled(true);
                btnLimpiar.setEnabled(true);
            }
        });


        // ----- BOTÓN GRABAR -----
        btnGrabar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (validarRut() && validarRutAlumnoApoderadoDistintos()) {
                    // Si el RUT es válido, ejecutas la lógica adicional y grabas
                    if (validarCampos()) {
                        grabarTotal();
                        limpiar();
                        limpiarApoderado();
                        cargarListadoAlumnos(null);
                    }
                }
            }
        });


        // ----- BOTÓN BORRAR -----
        btnBorrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                eliminarEstudiante();
                cargarListadoAlumnos(null);
                btnBorrar.setEnabled(false);
                btnModificar.setEnabled(false);
            }
        });
        btnBorrar.setEnabled(false);


        // ----- BOTÓN MODIFICAR -----
        btnModificar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                modificarAlumno();
                cargarListadoAlumnos(null);
                limpiar();
            }
        });
        btnModificar.setEnabled(false);



        // ----- CAMPOS DE TEXTO SIN PUNTO Y COMA -----
        JTextField[] camposDeTexto = {
                txtRut                  , txtNombres            , txtApellidos          , txtEdad                   ,
                txtFechaNacimiento      , txtDireccion          , txtEmail              , txtNacionalidad           ,
                txtTelefono             , txtCiudad             , txtDatosAdicionales   , txtFechaMatricula         ,
                txtCurso                , txtLetra              , txtElectivo           , txtEnfermedades           ,
                txtCiudadApoderado      , txtRutApoderado       , txtNombresApoderado   , txtApellidosApoderado     ,
                txtParentescoApoderado  , txtTelefonoApoderado  , txtDireccionApoderado , txtObservacionesApoderado
        };
        for (JTextField textField : camposDeTexto) {
            if (textField != null) {
                configurarCampoSinPuntoComa(textField);
            }
        }


        // ----- CAMPOS DE TEXTO SOLO NÚMEROS -----
        configurarCampoSoloNumeros(txtEdad, Integer.MAX_VALUE);
        configurarCampoSoloNumeros(txtTelefono, 9);
        configurarCampoSoloNumeros(txtTelefonoApoderado, 9);
        configurarCampoSoloNumeros(txtFechaMatricula, Integer.MAX_VALUE);
        configurarCampoSoloNumeros(txtFechaNacimiento, Integer.MAX_VALUE);


        // ----- CAMPOS DE TEXTO EN MAYÚSCULA -----
        JTextField[] camposDeTextoMayus = {
                txtNombres              , txtNombresApoderado   , txtApellidos              , txtApellidosApoderado,
                txtCiudad               , txtCiudadApoderado    , txtParentescoApoderado    ,
        };

        // Aplicar el método para cada campo
        for (JTextField campo : camposDeTextoMayus) {
            if (campo != null) {
                configurarCampoMayusculas(campo);
            }
        }


    // ----- CAMPOS DE TEXTO EN MAYÚSCULA Y NÚMEROS -----
        JTextField[] camposDeTextoMayusYNumeros = {
                txtDireccion            , txtEnfermedades           , txtEmail              , txtDatosAdicionales ,
                txtDireccionApoderado   , txtObservacionesApoderado
        };

        // Aplicar el método para cada campo
        for (JTextField campo : camposDeTextoMayusYNumeros) {
            if (campo != null) {
                configurarCamposMayusculasYNumeros(campo);
            }
        }
    }





    // ==================== CONFIGURAR CAMPOS SIN PUNTO Y COMA ====================
    private void configurarCampoSinPuntoComa(JTextField textField) {
        textField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (c == '.' || c == ',') {
                    e.consume();
                }
            }
        });
    }



    // ==================== CONFIGURAR CAMPOS SOLO NÚMEROS ====================
    private void configurarCampoSoloNumeros(JTextField campo, int maxLength) {
        campo.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char car = e.getKeyChar();
                if ((car < '0' || car > '9') || campo.getText().length() >= maxLength) {
                    e.consume();
                }
            }
        });
    }



    // ==================== CONFIGURAR CAMPOS EN MAYÚSCULA ====================
    private void configurarCampoMayusculas(JTextField campo) {
        campo.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (Character.isLowerCase(c)) {
                    String cad = ("" + c).toUpperCase();
                    c = cad.charAt(0);
                    e.setKeyChar(c);
                } else if (Character.isDigit(c)) {
                    e.consume();
                }
            }
        });
    }



    // ==================== CONFIGURAR CAMPOS EN MAYÚSCULA y NÚMEROS ====================
    private void configurarCamposMayusculasYNumeros(JTextField textField) {
        textField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (Character.isLetter(c)) {
                    // Convertir letras a mayúsculas
                    String cad = ("" + c).toUpperCase();
                    c = cad.charAt(0);
                    e.setKeyChar(c);
                }
            }
        });
    }



    /**
     * Se encarga de realizar la acción completa de grabar un nuevo alumno con su apoderado,
     * para esto primero verífica que los campos estén validados con el método "ValidarCampos".
     * Luego, utiliza el método "grabar" para obtener un objeto "Alumno", ejecuta "grabarApoderado"
     * para obtener un objeto "Apoderado". Finalmente, agrega el nuevo alumno al archivo CSV utilizando
     * la clase "Csv".
     */
    // ==================== GRABAR TOTAL ====================
    public void grabarTotal() {
        if (validarCampos()) {
            this.nuevoAlumno = grabar();
            grabarApoderado();
            Csv.agregarAlumnoCSV(this.nuevoAlumno);
        }
    }



    /**
     * Crea un objeto de tipo "Alumno" a partir de la información ingresada en los campos del formulario.
     * Los datos son obtenidos desde los campos de texto y los "combobox".
     * @return Un objeto "Alumno" con la información ingresada en la interfaz gráfica.
     */
    // ==================== GRABAR ALUMNO ====================
    public Alumno grabar() {
        String rutAlumno               = txtRut           .getText();
        String nombresAlumno           = txtNombres       .getText();
        String apellidosAlumno         = txtApellidos     .getText();
        String edadAlumno              = txtEdad          .getText();
        String fechaNacimientoAlumno   = txtFechaNacimiento.getText();
        String direccionAlumno         = txtDireccion     .getText();
        String ciudadAlumno            = txtCiudad        .getText();
        String telefonoAlumno          = txtTelefono      .getText();
        String emailAlumno             = txtEmail         .getText();
        String nacionalidadAlumno      = (String) boxNacionalidad.getSelectedItem();
        String fechaMatriculaAlumno    = txtFechaMatricula .getText();
        String enfermedadesAlumno      = txtEnfermedades  .getText();
        String cursoAlumno             = (String) boxCurso.getSelectedItem();
        String letraAlumno             = (String) boxLetra.getSelectedItem();
        String electivoAlumno          = (String) boxElectivo.getSelectedItem();
        String datosAdicionalesAlumno  = txtDatosAdicionales.getText();
        String generoAlumno            = obtenerGeneroAlumno();

        if (fechaNacimientoAlumno.trim().isEmpty()) {
            fechaNacimientoAlumno = " ";
        }
        if (emailAlumno.trim().isEmpty()) {
            emailAlumno = " ";
        }
        if (direccionAlumno.trim().isEmpty()) {
            direccionAlumno = " ";
        }
        if (enfermedadesAlumno.trim().isEmpty()) {
            enfermedadesAlumno = " ";
        }
        if (datosAdicionalesAlumno.trim().isEmpty()) {
            datosAdicionalesAlumno = " ";
        }
        if (generoAlumno.trim().isEmpty()) {
            generoAlumno = " ";
        }

        Alumno nuevoAlumno      = new Alumno(   rutAlumno             , nombresAlumno           , apellidosAlumno         , edadAlumno              ,
                fechaNacimientoAlumno , emailAlumno             , ciudadAlumno            , telefonoAlumno          ,
                nacionalidadAlumno    , fechaMatriculaAlumno    , direccionAlumno         , cursoAlumno             ,
                letraAlumno           , electivoAlumno          , enfermedadesAlumno      , datosAdicionalesAlumno  ,
                generoAlumno);
        return nuevoAlumno;
    }
    /**
     * Obtiene el género del alumno seleccionado en la interfaz gráfica.
     * Verifica cuál de los botones de opción está seleccionado y devuelve
     * el género seleccionado.
     * @return String que representa el género del alumno.
     */
    // ----- OBTENER GÉNERO -----
    private String obtenerGeneroAlumno() {
        String generoAlumno     = "";

        if (rdoMasculino.isSelected()) {
            return "Masculino";
        } else if (rdoFemenino.isSelected()) {
            return "Femenino";
        } else if (rdoOtro.isSelected()) {
            return "Otro";
        }
        return generoAlumno;
    }



    /**
     *
     */
    // -------------------- GRABAR APODERADO --------------------
    public void grabarApoderado() {
        String rutApoderado             = txtRutApoderado           .getText();
        String nombresApoderado         = txtNombresApoderado       .getText();
        String apellidosApoderado       = txtApellidosApoderado     .getText();
        String parentescoApoderado      = txtParentescoApoderado    .getText();
        String telefonoApoderado        = txtTelefonoApoderado      .getText();
        String ciudadApoderado          = txtCiudadApoderado        .getText();
        String direccionApoderado       = txtDireccionApoderado     .getText();
        String observacionesApoderado   = txtObservacionesApoderado .getText();
        String generoApoderado          = obtenerGeneroApoderado();

        if (observacionesApoderado.trim().isEmpty()) {
            observacionesApoderado = " ";
        }

        Apoderado nuevoApoderado = new Apoderado(   rutApoderado            , nombresApoderado          , apellidosApoderado        ,
                parentescoApoderado     , telefonoApoderado         , ciudadApoderado           ,
                direccionApoderado      , observacionesApoderado    , generoApoderado           );
        nuevoAlumno.setNuevoApoderado(nuevoApoderado);
    }
    // ----- Obtener género apoderado-----
    private String obtenerGeneroApoderado() {
        String generoApoderado = "";

        if (rdoMasculinoApoderado.isSelected()) {
            return "Masculino";
        } else if (rdoFemeninoApoderado.isSelected()) {
            return "Femenino";
        } else if (rdoOtroApoderado.isSelected()) {
            return "Otro";
        }
        return generoApoderado;
    }



    // ==================== VALIDAR RUT ====================
    private boolean validarRut() {
        String rutIngresado = txtRut.getText().replaceAll("\\.", "");

        // Validar rut
        if (rutIngresado.matches("^\\d{7,8}-[0-9kK]$")) {
            return true;
        } else {
            JOptionPane.showMessageDialog(this, "Rut Inválido \nForma válida --> 12345678-9", "Error de Validación", JOptionPane.ERROR_MESSAGE);
            // RUT inválido
            return false;
        }
    }



    // ==================== VALIDAR RUT ALUMNO Y APODERADO DISTINTOS ====================
    private boolean validarRutAlumnoApoderadoDistintos() {
        String rutAlumno = txtRut.getText().replaceAll("\\.", "");
        String rutApoderado = txtRutApoderado.getText().replaceAll("\\.", "");

        if (rutAlumno.equals(rutApoderado)) {
            JOptionPane.showMessageDialog(this, "El Rut del alumno y apoderado son iguales", "Error de Validación", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }



    // ==================== COMBO BOX EN BLANCO ====================
    private void comboBoxVacio() {
        boxNacionalidad .setSelectedIndex(-1);
        boxCurso        .setSelectedIndex(-1);
        boxLetra        .setSelectedIndex(-1);
        boxElectivo     .setSelectedIndex(-1);
    }



    // -------------------- VALIDAR CAMPOS --------------------
    private boolean validarCampos() {
        if (txtRut                  .getText().isEmpty() ||
            txtNombres              .getText().isEmpty() ||
            txtApellidos            .getText().isEmpty() ||
            txtEdad                 .getText().isEmpty() ||
            txtDireccion            .getText().isEmpty() ||
            txtCiudad               .getText().isEmpty() ||
            txtTelefono             .getText().isEmpty() ||
            boxNacionalidad         .getSelectedIndex() == -1 ||
            txtFechaMatricula       .getText().isEmpty() ||
            boxCurso                .getSelectedIndex() == -1 ||
            boxLetra                .getSelectedIndex() == -1 ||
            boxElectivo             .getSelectedIndex() == -1 ||
            txtRutApoderado         .getText().isEmpty() ||
            txtNombresApoderado     .getText().isEmpty() ||
            txtApellidosApoderado   .getText().isEmpty() ||
            txtParentescoApoderado  .getText().isEmpty() ||
            txtTelefonoApoderado    .getText().isEmpty() ||
            txtDireccionApoderado   .getText().isEmpty() ||
            txtCiudadApoderado      .getText().isEmpty() ||
            obtenerGeneroAlumno().isEmpty() ||
            obtenerGeneroApoderado().isEmpty()) {

            JOptionPane.showMessageDialog(this, "Faltan campos por rellenar", "Error de Validación", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }




    // ==================== LIMPIAR ALUMNO ====================
    public void limpiar() {
        txtRut              .setText(null);
        txtNombres          .setText(null);
        txtApellidos        .setText(null);
        txtEdad             .setText(null);
        txtFechaNacimiento  .setText(null);
        txtDireccion        .setText(null);
        txtCiudad           .setText(null);
        txtTelefono         .setText(null);
        txtEmail            .setText(null);
        boxNacionalidad     .setSelectedIndex(-1);
        txtDatosAdicionales .setText(null);
        txtFechaMatricula   .setText(null);
        txtEnfermedades     .setText(null);
        boxCurso            .setSelectedIndex(-1);
        boxLetra            .setSelectedIndex(-1);
        boxElectivo         .setSelectedIndex(-1);
        grupoBotonesGenero  .clearSelection();
    }



    // ==================== LIMPIAR APODERADO ====================
    public void limpiarApoderado() {
        txtRutApoderado             .setText(null);
        txtNombresApoderado         .setText(null);
        txtApellidosApoderado       .setText(null);
        txtParentescoApoderado      .setText(null);
        txtTelefonoApoderado        .setText(null);
        txtCiudadApoderado          .setText(null);
        txtDireccionApoderado       .setText(null);
        txtObservacionesApoderado   .setText(null);
        grupoBotonesGeneroApoderado .clearSelection();
    }



    // ==================== SET NUEVO ALUMNO ====================
    public void setNuevoAlumno(Alumno nuevoAlumno) {
        this.nuevoAlumno = nuevoAlumno;
    }



    // ==================== ELIMINAR ESTUDIANTE ====================
    private void eliminarEstudiante() {
        String rutEstudiante = txtRut.getText();

        if (!rutEstudiante.isEmpty()) {
            int confirmacion = JOptionPane.showConfirmDialog(this, "¿Estás seguro de eliminar al estudiante?", "Confirmar Borrado", JOptionPane.YES_NO_OPTION);

            if (confirmacion == JOptionPane.YES_OPTION) {
                Csv.eliminarAlumnoCSV(rutEstudiante);

                limpiar();
                limpiarApoderado();
                cargarListadoAlumnos(null);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Primero debes buscar al estudiante que deseas eliminar", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }



    // ==================== CARGAR LISTADO ALUMNOS ====================
    public void cargarListadoAlumnos(String filtroRut) {
        List<Object[]> alumnosData  = Csv.listadoAlumnos(filtroRut);

        // Modelo de tabla
        DefaultTableModel modelo    = new DefaultTableModel();

        String[] columnas = {   "Rut", "Nombres", "Apellidos", "Fecha Matrícula", "Nacionalidad", "Curso", "Letra", "Electivo", "Género"};
        modelo.setColumnIdentifiers(columnas);

        // Agregar los datos al modelo
        for (Object[] rowData : alumnosData) {
            Object[] rowDataToShow = {
                    rowData[0],     // Rut
                    rowData[1],     // Nombres
                    rowData[2],     // Apellidos
                    rowData[9],     // Fecha Matrícula
                    rowData[8],     // Nacionalidad
                    rowData[11],    // Curso
                    rowData[12],    // Letra
                    rowData[13],    // Electivo
                    rowData[16]     // Género
            };
            modelo.addRow(rowDataToShow);
        }

        // Establecer el modelo en la tabla
        tblListado.setModel(modelo);
    }



    // ==================== BUSCAR ALUMNO POR RUT ====================
    private void buscarAlumnoPorRut() {
        String filtroRut = txtRut.getText();
        cargarListadoAlumnos(filtroRut);

        List<Object[]> alumnosData = Csv.listadoAlumnos(filtroRut);

        if (!alumnosData.isEmpty()) {
            mostrarDatosAlumno(alumnosData.get(0));
            mostrarDatosApoderado(alumnosData.get(0));
            habilitarBotones(true);
        } else {
            JOptionPane.showMessageDialog(this, "No se encontró un alumno con el RUT especificado", "Advertencia", JOptionPane.WARNING_MESSAGE);
            resetearVistas();
            habilitarBotones(false);
        }
    }
    // ----- MOSTRAR DATOS ALUMNOS -----
    private void mostrarDatosAlumno(Object[] alumnoData) {
        txtRut                      .setText((String) alumnoData[0]);
        txtNombres                  .setText((String) alumnoData[1]);
        txtApellidos                .setText((String) alumnoData[2]);
        txtEdad                     .setText((String) alumnoData[3]);
        txtFechaNacimiento          .setText((String) alumnoData[4]);
        txtEmail                    .setText((String) alumnoData[5]);
        txtCiudad                   .setText((String) alumnoData[6]);
        txtTelefono                 .setText((String) alumnoData[7]);
        boxNacionalidad             .setSelectedItem((String) alumnoData[8]);
        txtFechaMatricula           .setText((String) alumnoData[9]);
        txtDireccion                .setText((String) alumnoData[10]);
        boxCurso                    .setSelectedItem((String) alumnoData[11]);
        boxLetra                    .setSelectedItem((String) alumnoData[12]);
        boxElectivo                 .setSelectedItem((String) alumnoData[13]);
        txtEnfermedades             .setText((String) alumnoData[14]);
        txtDatosAdicionales         .setText((String) alumnoData[15]);

        String generoAlumno = (String) alumnoData[16];
        seleccionarGenero(rdoMasculino, rdoFemenino, rdoOtro, generoAlumno);
    }
    // ----- MOSTRAR DATOS APODERADOS -----
    private void mostrarDatosApoderado(Object[] alumnoData) {
        txtRutApoderado             .setText((String) alumnoData[17]);
        txtNombresApoderado         .setText((String) alumnoData[18]);
        txtApellidosApoderado       .setText((String) alumnoData[19]);
        txtParentescoApoderado      .setText((String) alumnoData[20]);
        txtTelefonoApoderado        .setText((String) alumnoData[21]);
        txtCiudadApoderado          .setText((String) alumnoData[22]);
        txtDireccionApoderado       .setText((String) alumnoData[23]);
        txtObservacionesApoderado   .setText((String) alumnoData[24]);

        String generoApoderado = (String) alumnoData[25];
        seleccionarGenero(rdoMasculinoApoderado, rdoFemeninoApoderado, rdoOtroApoderado, generoApoderado);
    }
    // ----- SELECCIONAR GÉNERO -----
    private void seleccionarGenero(JRadioButton masculino, JRadioButton femenino, JRadioButton otro, String genero) {
        if (genero.equals("Masculino")) {
            masculino.setSelected(true);
        } else if (genero.equals("Femenino")) {
            femenino.setSelected(true);
        } else {
            otro.setSelected(true);
        }
    }
    // ----- HABILITAR BOTONES -----
    private void habilitarBotones(boolean habilitar) {
        btnGrabar.setEnabled(!habilitar);
        btnBorrar.setEnabled(habilitar);
        btnModificar.setEnabled(habilitar);
    }
    // ----- RESETEAR VISTAS -----
    private void resetearVistas() {
        cargarListadoAlumnos(null);
        limpiar();
        limpiarApoderado();
    }



    // ==================== CARGAR DATOS APODERADO EN INTERFAZ ====================
    public void cargarDatosApoderado(Apoderado apoderado) {
        if (apoderado != null) {
            txtRutApoderado             .setText(apoderado.getRut());
            txtNombresApoderado         .setText(apoderado.getNombres());
            txtApellidosApoderado       .setText(apoderado.getApellidos());
            txtParentescoApoderado      .setText(apoderado.getParentesco());
            txtTelefonoApoderado        .setText(apoderado.getTelefono());
            txtCiudadApoderado          .setText(apoderado.getCiudad());
            txtDireccionApoderado       .setText(apoderado.getDireccion());
            txtObservacionesApoderado   .setText(apoderado.getObservaciones());

            String generoApoderado = apoderado.getGenero();
            if (generoApoderado.equals("Masculino")) {
                rdoMasculinoApoderado   .setSelected(true);
            } else if (generoApoderado.equals("Femenino")) {
                rdoFemeninoApoderado    .setSelected(true);
            } else {
                rdoOtroApoderado        .setSelected(true);
            }
        }
    }



    // ==================== MODIFICAR ALUMNO ====================
    private void modificarAlumno() {
        if (validarCampos()) {
            // Obtener los datos modificados de los TextField
            Alumno alumnoModificado = grabar();

            Csv.modificarAlumno(alumnoModificado);

            JOptionPane.showMessageDialog(null, "Alumno modificado correctamente");
        }
    }
}