package com.mycompany.proyectobanco.presentacion;

import com.mycompany.proyectobanco.dtos.NuevaCuentaDTO;
import com.mycompany.proyectobanco.dtos.ObjetosBoDTO;
import com.mycompany.proyectobanco.entidades.Cliente;
import com.mycompany.proyectobanco.entidades.Cuenta;
import com.mycompany.proyectobanco.negocio.NegocioException;
import java.util.GregorianCalendar;
import javax.swing.JOptionPane;

/**
 *
 * @author Diego
 */
public class CreacionCuentaFORM extends javax.swing.JFrame {

    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(CreacionCuentaFORM.class.getName());

    private final ObjetosBoDTO objetosBO;
    private Cliente clienteLogeado;

    public CreacionCuentaFORM(ObjetosBoDTO objetosBO, Cliente clienteLogeado) {
        this.objetosBO = objetosBO;
        this.clienteLogeado = clienteLogeado;
        initComponents();

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlGenerarRetiroSinCuenta = new javax.swing.JPanel();
        lblTitulo = new javax.swing.JLabel();
        lblNumCuenta = new javax.swing.JLabel();
        txtContrasenia = new javax.swing.JTextField();
        btnCrearCuenta = new javax.swing.JButton();
        lblContrasenia = new javax.swing.JLabel();
        txtNumCuenta = new javax.swing.JTextField();
        lblApellidoPaterno1 = new javax.swing.JLabel();
        btnVolverAtras = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        pnlGenerarRetiroSinCuenta.setBackground(new java.awt.Color(153, 153, 153));

        lblTitulo.setFont(new java.awt.Font("Times New Roman", 0, 24)); // NOI18N
        lblTitulo.setForeground(new java.awt.Color(0, 0, 0));
        lblTitulo.setText("Creación de la cuenta");

        lblNumCuenta.setFont(new java.awt.Font("Arial", 1, 20)); // NOI18N
        lblNumCuenta.setForeground(new java.awt.Color(0, 0, 0));
        lblNumCuenta.setText("Su numero de cuenta es:");

        txtContrasenia.setBackground(new java.awt.Color(255, 255, 255));
        txtContrasenia.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        txtContrasenia.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        btnCrearCuenta.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        btnCrearCuenta.setText("Crear Cuenta");
        btnCrearCuenta.addActionListener(this::btnCrearCuentaActionPerformed);

        lblContrasenia.setFont(new java.awt.Font("Arial", 1, 20)); // NOI18N
        lblContrasenia.setForeground(new java.awt.Color(0, 0, 0));
        lblContrasenia.setText("Ingrese la contraseña:");

        txtNumCuenta.setBackground(new java.awt.Color(255, 255, 255));
        txtNumCuenta.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        txtNumCuenta.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        lblApellidoPaterno1.setFont(new java.awt.Font("Arial", 1, 20)); // NOI18N
        lblApellidoPaterno1.setForeground(new java.awt.Color(0, 0, 0));

        btnVolverAtras.setBackground(new java.awt.Color(153, 153, 153));
        btnVolverAtras.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        btnVolverAtras.setForeground(new java.awt.Color(0, 0, 0));
        btnVolverAtras.setText(">");
        btnVolverAtras.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        btnVolverAtras.addActionListener(this::btnVolverAtrasActionPerformed);

        javax.swing.GroupLayout pnlGenerarRetiroSinCuentaLayout = new javax.swing.GroupLayout(pnlGenerarRetiroSinCuenta);
        pnlGenerarRetiroSinCuenta.setLayout(pnlGenerarRetiroSinCuentaLayout);
        pnlGenerarRetiroSinCuentaLayout.setHorizontalGroup(
            pnlGenerarRetiroSinCuentaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlGenerarRetiroSinCuentaLayout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(pnlGenerarRetiroSinCuentaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlGenerarRetiroSinCuentaLayout.createSequentialGroup()
                        .addComponent(lblApellidoPaterno1, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlGenerarRetiroSinCuentaLayout.createSequentialGroup()
                        .addGroup(pnlGenerarRetiroSinCuentaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblNumCuenta, javax.swing.GroupLayout.DEFAULT_SIZE, 249, Short.MAX_VALUE)
                            .addGroup(pnlGenerarRetiroSinCuentaLayout.createSequentialGroup()
                                .addComponent(lblContrasenia, javax.swing.GroupLayout.PREFERRED_SIZE, 227, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnlGenerarRetiroSinCuentaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtContrasenia, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtNumCuenta, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(39, 39, 39))))
            .addGroup(pnlGenerarRetiroSinCuentaLayout.createSequentialGroup()
                .addGroup(pnlGenerarRetiroSinCuentaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlGenerarRetiroSinCuentaLayout.createSequentialGroup()
                        .addComponent(btnVolverAtras)
                        .addGap(149, 149, 149)
                        .addComponent(lblTitulo))
                    .addGroup(pnlGenerarRetiroSinCuentaLayout.createSequentialGroup()
                        .addGap(203, 203, 203)
                        .addComponent(btnCrearCuenta, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlGenerarRetiroSinCuentaLayout.setVerticalGroup(
            pnlGenerarRetiroSinCuentaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlGenerarRetiroSinCuentaLayout.createSequentialGroup()
                .addGroup(pnlGenerarRetiroSinCuentaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlGenerarRetiroSinCuentaLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(lblTitulo))
                    .addComponent(btnVolverAtras))
                .addGap(73, 73, 73)
                .addGroup(pnlGenerarRetiroSinCuentaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblNumCuenta)
                    .addComponent(txtNumCuenta, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(34, 34, 34)
                .addGroup(pnlGenerarRetiroSinCuentaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblContrasenia)
                    .addComponent(txtContrasenia, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addComponent(lblApellidoPaterno1)
                .addGap(29, 29, 29)
                .addComponent(btnCrearCuenta, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(132, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlGenerarRetiroSinCuenta, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlGenerarRetiroSinCuenta, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCrearCuentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCrearCuentaActionPerformed
        this.crearCuenta();
    }//GEN-LAST:event_btnCrearCuentaActionPerformed

    private void btnVolverAtrasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVolverAtrasActionPerformed
        volverAtras();
    }//GEN-LAST:event_btnVolverAtrasActionPerformed

    private void crearCuenta() {
        try {
            String numCuenta = objetosBO.getCuentasBO().generarNumeroCuenta();
            txtNumCuenta.setText(numCuenta);
            
            long idCliente = Long.parseLong(clienteLogeado.getIdCliente());
            long saldo = 0;
            GregorianCalendar fechaApertura = new GregorianCalendar();
            
            
            NuevaCuentaDTO cuentaDTO = new NuevaCuentaDTO(numCuenta, Cuenta.Estado.ACTIVO, fechaApertura, saldo, idCliente);
            objetosBO.getCuentasBO().crearCuenta(cuentaDTO);
            JOptionPane.showMessageDialog(
                    this,
                    "Se ha creado la cuenta correctamente",
                    "Información",
                    JOptionPane.INFORMATION_MESSAGE
            );
        } catch (NegocioException ex) {
            JOptionPane.showMessageDialog(this,
                    "Error al crear cuenta: " + ex.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }

    }

    private void volverAtras() {
        this.dispose();
        new MenuPrincipalFORM(objetosBO, clienteLogeado).setVisible(true);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCrearCuenta;
    private javax.swing.JButton btnVolverAtras;
    private javax.swing.JLabel lblApellidoPaterno1;
    private javax.swing.JLabel lblContrasenia;
    private javax.swing.JLabel lblNumCuenta;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JPanel pnlGenerarRetiroSinCuenta;
    private javax.swing.JTextField txtContrasenia;
    private javax.swing.JTextField txtNumCuenta;
    // End of variables declaration//GEN-END:variables
}
