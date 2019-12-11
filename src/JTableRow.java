import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class JTableRow {

    public JTableRow() {

        // create JFrame and JTable
        JFrame frame = new JFrame();
        JTable table = new JTable();

        // create a table model and set a Column Identifiers to this model
        Object[] columns = {"Song name","Voices"};
        DefaultTableModel model = new DefaultTableModel();
        model.setColumnIdentifiers(columns);

        // set the model to the table
        table.setModel(model);

        // create JScrollPane
        JScrollPane pane = new JScrollPane(table);
        pane.setBounds(5, 5, 300, 150);

        frame.setLayout(null);

        frame.add(pane);

        // create an array of objects to set the row data
        Object[] row = new Object[2];
        for(Song song : ConcertSystem.login.songs) {
            row[0] = song.getName();
            row[1] = song.getVoices();
            model.addRow(row);
        }

        frame.setSize(330,200);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}