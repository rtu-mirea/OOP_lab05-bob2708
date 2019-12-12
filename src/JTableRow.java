import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class JTableRow {
    public JTableRow() {


            JFrame frame = new JFrame();
            JTable table = new JTable();

            // create a table model and set a Column Identifiers to this model
            Object[] columns = {"Name", "Voices"};
            DefaultTableModel model = new DefaultTableModel();
            model.setColumnIdentifiers(columns);

            // set the model to the table
            table.setModel(model);

            // create JTextFields
            JTextField textName = new JTextField();
            JTextField textVoices = new JTextField();

            // create JButtons
            JButton btnAdd = new JButton("Add");
            JButton btnDelete = new JButton("Delete");
            JButton btnUpdate = new JButton("Update");

            textName.setBounds(20, 220, 100, 25);
            textVoices.setBounds(20, 250, 100, 25);

            btnAdd.setBounds(150, 220, 100, 25);
            btnUpdate.setBounds(150, 265, 100, 25);
            btnDelete.setBounds(150, 310, 100, 25);

            // create JScrollPane
            JScrollPane pane = new JScrollPane(table);
            pane.setBounds(0, 0, 880, 200);

            frame.setLayout(null);

            frame.add(pane);

            // add JTextFields to the jframe
            frame.add(textName);
            frame.add(textVoices);

            // add JButtons to the jframe
            frame.add(btnAdd);
            frame.add(btnDelete);
            frame.add(btnUpdate);

            // create an array of objects to set the row data
            Object[] row = new Object[2];

            for (Song song : ConcertSystem.login.songs) {
                row[0] = song.getName();
                row[1] = song.getVoices();
                model.addRow(row);
            }
            // button add row
            btnAdd.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) throws NumberFormatException {
                    try {
                        row[0] = textName.getText();
                        row[1] = textVoices.getText();
                        // add row to the model
                        ConcertSystem.login.songs.add(new Song(textName.getText(), Integer.parseInt(textVoices.getText())));
                        model.addRow(row);
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(null, "Неверный формат ввода","Ошибка",JOptionPane.ERROR_MESSAGE);
                    }
                }
            });

            // button remove row
            btnDelete.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // i = the index of the selected row
                    int i = table.getSelectedRow();
                    if (i >= 0) {
                        // remove a row from jtable
                        model.removeRow(i);
                        ConcertSystem.login.songs.remove(i);
                    } else {
                        System.out.println("Delete Error");
                    }
                }
            });

            // get selected row data From table to textfields
            table.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    // i = the index of the selected row
                    int i = table.getSelectedRow();
                    textName.setText(model.getValueAt(i, 0).toString());
                    textVoices.setText(model.getValueAt(i, 1).toString());
                }
            });

            // button update row
            btnUpdate.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) throws NumberFormatException {
                    // i = the index of the selected row
                    int i = table.getSelectedRow();
                    try {
                        if (i >= 0) {
                            ConcertSystem.login.songs.get(i).setVoices(Integer.parseInt(textVoices.getText()));
                            ConcertSystem.login.songs.get(i).setName(textName.getText());
                            model.setValueAt(textName.getText(), i, 0);
                            model.setValueAt(textVoices.getText(), i, 1);
                        } else {
                            System.out.println("Update Error");
                        }
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(null, "Неверный формат ввода","Ошибка",JOptionPane.ERROR_MESSAGE);
                    }
                }
            });

            frame.setSize(900, 400);
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
    }
}