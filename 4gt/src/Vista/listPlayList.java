package Vista;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import modelo.Cancion;

import javax.swing.JTextArea;

public class listPlayList extends JFrame {

	private static final long serialVersionUID = 1L;
	private JTable table;
	private JPanel contentPane;
	private DefaultTableModel model;
	private JTextArea textArea;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					listPlayList frame = new listPlayList();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public listPlayList() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 706, 626);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setTitle("Lista PlayList");

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBounds(10, 32, 691, 587);
		contentPane.add(panel);
		panel.setLayout(null);

		JButton btnNewButton = new JButton("Atras");
		btnNewButton.setBounds(0, 0, 90, 47);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GestionPlayList obj = new GestionPlayList();
				obj.setVisible(true);
				dispose();
			}
		});
		panel.setLayout(null);
		panel.setLayout(null);
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 20));
		panel.add(btnNewButton);
		
		JButton btnPerfil = new JButton("Perfil");
		btnPerfil.setIcon(new ImageIcon("img/usuu.png"));
		btnPerfil.setContentAreaFilled(false);
		btnPerfil.setBorder(null);
		
		btnPerfil.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentanaPerfil obj = new VentanaPerfil();
				obj.setVisible(true);
				dispose();
			}
		});
		btnPerfil.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnPerfil.setBounds(591, 11, 90, 58);
		panel.add(btnPerfil);
		

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(108, 194, 283, 328);
		panel.add(scrollPane);

		table = new JTable();
		table.setFont(new Font("Tahoma", Font.PLAIN, 15));
		table.setModel(new DefaultTableModel(new Object[][] {}, new String[] { "Nombre Canciones" }));
		table.getColumnModel().getColumn(0).setPreferredWidth(102);
		scrollPane.setViewportView(table);

		JButton btnAñadir = new JButton("Añadir");
		btnAñadir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
				GestionPlayList obj = new GestionPlayList();
				obj.setVisible(true);
				String textCopiado = textArea.getText();
				obj.setTextArea(textCopiado);
				dispose();
				
			
			}
		});
		btnAñadir.setBounds(355, 54, 89, 86);
		panel.add(btnAñadir);

		JButton btnAñadirLista = new JButton("Añadir a la lista");
		btnAñadirLista.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				 int[] lineaSelec = table.getSelectedRows();
			 
			        if (lineaSelec.length > 0) {
			            StringBuilder nombreTable = new StringBuilder();
			            for (int i = 0; i < lineaSelec.length; i++) {
			                int lineaSeleccionada = lineaSelec[i];
			                String nombreSeleccionado = table.getValueAt(lineaSeleccionada, 0).toString();
			                nombreTable.append(nombreSeleccionado).append("\n");
			            }
			            
			            textArea.append(nombreTable.toString());
			        }
			}
		});
		btnAñadirLista.setBounds(416, 247, 198, 78);
		panel.add(btnAñadirLista);
		
				textArea = new JTextArea();
				textArea.setBounds(108, 28, 237, 125);
				panel.add(textArea);
				
				JButton btnFavorito = new JButton("PlayList Favorita");
				btnFavorito.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
					
					
					}
				});
				btnFavorito.setBounds(454, 119, 116, 21);
				panel.add(btnFavorito);
	}

	public void valoresTablaCancion(List<Cancion> canciones) {
		String[] nombreCanciones = { "Nombre Canciones" };

		model = new DefaultTableModel(nombreCanciones, 0);
		table.setModel(model);

		for (Cancion cancion : canciones) {
			Object[] row = { cancion.getNombre() };
			model.addRow(row);
		}

	}

}
