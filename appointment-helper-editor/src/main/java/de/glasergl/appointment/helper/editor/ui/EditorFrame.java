package de.glasergl.appointment.helper.editor.ui;

import de.glasergl.appointment.helper.editor.ui.appointmentField.AppointmentFieldWrapperList;
import de.glasergl.appointment.helper.util.file.AppointmentsConfigurationHandler;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.io.IOException;

/**
 * Frame for the Editor.
 *
 * @author glasergl
 */
public final class EditorFrame extends JFrame {
    private final AppointmentFieldWrapperList appointmentsFields;
    private final Header header;
    private final Footer footer;

    public EditorFrame(final AppointmentsConfigurationHandler handler) throws IOException {
        super("TerminEditor");
        setIconImage(ImageIO.read(getClass().getResourceAsStream("/EditorIcon.png")));
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.appointmentsFields = new AppointmentFieldWrapperList(handler);
        this.header = new Header(appointmentsFields);
        this.footer = new Footer(appointmentsFields);
        setup();
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        footer.requestFocusForAddButton();
    }

    private void setup() {
        final Container contentPane = getContentPane();
        final JScrollPane scrollWrapper = wrapInJScrollPane(appointmentsFields);
        contentPane.add(header, BorderLayout.NORTH);
        contentPane.add(scrollWrapper, BorderLayout.CENTER);
        contentPane.add(footer, BorderLayout.SOUTH);
    }

    private JScrollPane wrapInJScrollPane(final AppointmentFieldWrapperList appointmentsFields) {
        final JScrollPane scrollWrapper = new JScrollPane(appointmentsFields, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        appointmentsFields.setParentScrollWrapper(scrollWrapper);
        scrollWrapper.setBorder(new EmptyBorder(0, 0, 0, 0));
        scrollWrapper.setPreferredSize(appointmentsFields.getPreferredViewSize());
        final JScrollBar verticalScrollBar = scrollWrapper.getVerticalScrollBar();
        verticalScrollBar.setUnitIncrement(10);
        return scrollWrapper;
    }

    /**
     * If every depicted Appointment is stored, this Frame is normally disposed.
     * Else, the user gets asked whether he wants to save or discard the current
     * appointments or cancel the dispose-operation via a JOptionPane. If the
     * appointments cannot be saved, the error with another JOptionPane is showed.
     */
    @Override
    public void dispose() {
        if (appointmentsFields.matchesConfiguration()) {
            super.dispose();
        } else {
            final String title = "Zustand nicht gespeichert";
            final String message = "Der aktuelle Zustand der Termine ist nicht gespeichert.\nSpeichern?";
            final int answerOfUser = JOptionPane.showConfirmDialog(this, message, title,
                    JOptionPane.YES_NO_CANCEL_OPTION);
            if (answerOfUser == JOptionPane.YES_OPTION) {
                if (appointmentsFields.allRepresentValidAppointments()) {
                    appointmentsFields.storeAll();
                    super.dispose();
                } else {
                    final String errorTitle = "Speichern nicht möglich";
                    final String errorMessage = "Speichern ist aufgrund fehlerhafter Termine nicht möglich.\nBeheben Sie ihre Eingabe und versuchen Sie es erneut.";
                    JOptionPane.showMessageDialog(this, errorMessage, errorTitle, JOptionPane.ERROR_MESSAGE);
                }
            } else if (answerOfUser == JOptionPane.NO_OPTION) {
                super.dispose();
            }
        }
    }

}
