package tp2;
import java.awt.EventQueue;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;

import org.openstreetmap.gui.jmapviewer.Coordinate;
import org.openstreetmap.gui.jmapviewer.JMapViewer;
import org.openstreetmap.gui.jmapviewer.MapMarkerDot;
import org.openstreetmap.gui.jmapviewer.MapPolygonImpl;
import org.openstreetmap.gui.jmapviewer.interfaces.MapPolygon;
import javax.swing.JPanel;
import java.awt.FlowLayout;
import java.awt.Dimension;
import java.awt.Component;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextPane;
import javax.swing.UIManager;

import java.awt.Font;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;

public class Interfaz {

	private JFrame frame;
	private JMapViewer mapa;
	private ArrayList<Coordinate> provincias;
	private Coordinate jujuy, salta, formosa, catamarca, tucuman, santiago, chaco, corrientes, misiones, santaFe, laRioja, cordoba, 
						entreRios, sanJuan, sanLuis, buenosAires, caba, mendoza, laPampa, neuquen, rioNegro, chubut, santaCruz, tierra;
	
	private ArrayList<MapPolygon> lineas;
	private Grafo grafo;
	private List<Arista> aristasAgm;
	private JPanel panel2;
	private JTextField text_p1, text_p2;
	private JLabel l_y, agregarPeso, lblNewLabel;
	private JTextField text_peso;
	private JLabel cartel_error;
	private JLabel cartel_error2;
	private JButton btnNewButton, bot_agregar;
	private JTextPane infoProvincias;
	private JButton b_reiniciar;
	private int cantProvincias;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Interfaz window = new Interfaz();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Interfaz() {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 800, 700);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		panel2 = new JPanel();
		panel2.setBackground(new Color(192, 192, 192));
		panel2.setBounds(0, 0, 400, 600);
		frame.getContentPane().add(panel2);
		panel2.setLayout(null);
		
		mapa = new JMapViewer();
		mapa.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				e.consume();
			}
		});
		
		
		mapa.setBounds(0, 0, 444, 661);
		mapa.setAlignmentY(Component.TOP_ALIGNMENT);
		mapa.setAlignmentX(Component.LEFT_ALIGNMENT);
		mapa.setPreferredSize(new Dimension(500, 600));
		mapa.setZoomControlsVisible(false);
		
		panel2.add(mapa);
		
		text_p1 = new JTextField();
		text_p1.setBounds(494, 48, 56, 20);
		panel2.add(text_p1);
		text_p1.setColumns(10);
		
		text_p2 = new JTextField();
		text_p2.setBounds(593, 48, 56, 20);
		panel2.add(text_p2);
		text_p2.setColumns(10);
		
		agregarPeso = new JLabel("Agregar peso arista entre:");
		agregarPeso.setFont(new Font("Arial", Font.BOLD, 11));
		agregarPeso.setBounds(454, 11, 160, 26);
		panel2.add(agregarPeso);
		
		l_y = new JLabel("y");
		l_y.setBounds(568, 41, 27, 34);
		panel2.add(l_y);
		
		lblNewLabel = new JLabel("Peso:");
		lblNewLabel.setFont(new Font("Arial", Font.BOLD, 11));
		lblNewLabel.setBounds(454, 87, 46, 14);
		panel2.add(lblNewLabel);
		
		text_peso = new JTextField();
		text_peso.setBounds(504, 84, 46, 20);
		panel2.add(text_peso);
		text_peso.setColumns(10);
		
		
		provincias = new ArrayList<Coordinate>();
		lineas = new ArrayList<MapPolygon>();
		
		cantProvincias = 24;
		grafo = new Grafo(cantProvincias);
		agregarVecinos();
		
		definirProvincias();
		
		listaProvincias();
		
		Coordinate coordenada = new Coordinate(-40.217898, -66.831649);
		mapa.setDisplayPosition(coordenada, 4);
		
		for (Coordinate p:provincias) {
			mapa.addMapMarker(new MapMarkerDot(p));
		}
		
		crearUniones();
		
		cartel_error = new JLabel("No son limitrofes!");
		cartel_error.setVisible(false);
		cartel_error.setFont(new Font("Arial", Font.BOLD, 9));
		cartel_error.setBounds(578, 79, 89, 30);
		panel2.add(cartel_error);
		
		cartel_error2 = new JLabel("Ya tiene peso!");
		cartel_error2.setFont(new Font("Arial", Font.BOLD, 9));
		cartel_error2.setVisible(false);
		cartel_error2.setBounds(578, 87, 86, 14);
		panel2.add(cartel_error2);
		
	
		bot_agregar = new JButton("Agregar");
		bot_agregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cartel_error.setVisible(false);
				cartel_error2.setVisible(false);
				agregarPesoArista();
			}
		});
		bot_agregar.setBackground(new Color(217, 217, 217));
		bot_agregar.setBorderPainted(false);
		bot_agregar.setBounds(685, 103, 89, 23);
		panel2.add(bot_agregar);
		
		infoProvincias = new JTextPane();
		infoProvincias.setBackground(new Color(192, 192, 192));
		infoProvincias.setEditable(false);
		infoProvincias.setText("Informacion sobre la referencia a provincias:\n"
				+ "Jujuy = 0\n" + "Salta = 1\n" + "Formosa = 2\n" + "Catamarca = 3\n" + "Tucuman = 4\n" + "Santiago del Estero = 5\n" + "Chaco = 6\n"
				+ "Corrientes = 7\n" + "Misiones = 8\n" + "Santa Fe = 9\n" + "La Rioja = 10\n" + "Cordoba = 11\n" + "Entre Rios = 12\n" + "San Juan = 13\n"
				+ "San Luis = 14\n" + "Buenos Aires = 15\n" + "CABA = 16\n" + "Mendoza = 17\n" + "La Pampa = 18\n" + "Neuquen = 19\n" + "Rio Negro = 20\n"
				+ "Chubut = 21\n" + "Santa Cruz = 22\n" + "Tierra Del Fuego = 23");
		infoProvincias.setBounds(454, 163, 221, 453);
		panel2.add(infoProvincias);
		

		
		btnNewButton = new JButton("Generar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ejecutarAGM();
			}
		});
		btnNewButton.setBackground(new Color(217, 217, 217));
		btnNewButton.setBorderPainted(false);
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnNewButton.setBounds(685, 627, 89, 23);
		panel2.add(btnNewButton);
		
		b_reiniciar = new JButton("Reiniciar");
		b_reiniciar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				grafo.inicializarMatrizAdyPesos();
				removerLineas();
				crearUniones();
			}
		});
		b_reiniciar.setBorderPainted(false);
		b_reiniciar.setBackground(new Color(217, 217, 217));
		b_reiniciar.setBounds(454, 627, 89, 23);
		panel2.add(b_reiniciar);
	}
	
	private static List<Coordinate> createLine(Coordinate start, Coordinate end) {
        List<Coordinate> linePoints = new ArrayList<>();
        linePoints.add(start);
        linePoints.add(end);
        linePoints.add(end);
        return linePoints;
    }
	
	private void crearUniones() {
		for (int i = 0; i < grafo.tamano(); i++) {
			for (int j = 0; j < grafo.tamano(); j++) {
				if (grafo.existeArista(i, j)) {
					MapPolygon poligono = new MapPolygonImpl(createLine(provincias.get(i), provincias.get(j)));
					mapa.addMapPolygon(poligono);
					lineas.add(poligono);
				}
			}
		}
	}
	
	private void agregarPesoArista() {
		if (grafo.pesoArista(Integer.parseInt(text_p1.getText()), Integer.parseInt(text_p2.getText()), Integer.parseInt(text_peso.getText())) == 0) {
			cartel_error2.setVisible(true);
		}
		
		else if(grafo.pesoArista(Integer.parseInt(text_p1.getText()), Integer.parseInt(text_p2.getText()), Integer.parseInt(text_peso.getText())) == -1) {
			cartel_error.setVisible(true);
		}
	}
	
	private void ejecutarAGM() {
		removerLineas();
		aristasAgm = grafo.kruskal();
		for (int i = 0; i < aristasAgm.size(); i++) {
				MapPolygon poligono = new MapPolygonImpl(createLine(provincias.get(aristasAgm.get(i).getOrigen()), provincias.get(aristasAgm.get(i).getDestino())));
				mapa.addMapPolygon(poligono);
				lineas.add(poligono);
		}
		
		
	}
	
	private void removerLineas() {
		mapa.removeAllMapPolygons();
	}
	
	private void definirProvincias() {
		jujuy = new Coordinate(-23.025768, -65.944995); //jujuy 0
		salta = new Coordinate(-25.314132, -65.004313); //salta 1
		formosa = new Coordinate(-24.722177, -60.353167); //formosa 2
		catamarca = new Coordinate(-26.960915, -67.272746); //catamarca 3
		tucuman = new Coordinate(-27.009372, -65.428461); //tucuman 4
		santiago = new Coordinate(-27.702253, -63.357377); //santiago del estero 5
		chaco = new Coordinate(-26.598685, -60.371284); //chaco 6
		corrientes = new Coordinate(-28.819982, -57.896771); //corrientes 7
		misiones = new Coordinate(-26.687990, -54.302694); //misiones 8
		santaFe = new Coordinate(-30.654618, -60.910952); //santa fe 9
		laRioja = new Coordinate(-30.091630, -66.721795); // la rioja 10
		cordoba = new Coordinate(-31.955522, -63.707614); //cordoba 11
		entreRios = new Coordinate(-32.060922, -59.295102); //entre rios 12
		sanJuan = new Coordinate(-31.023815, -68.828111); // san juan 13
		sanLuis = new Coordinate(-34.002958, -65.967739); //san luis 14
		buenosAires = new Coordinate(-36.454344, -60.092248); //buenos aires 15
		caba = new Coordinate(-34.61901, -58.392455); // caba 16
		mendoza = new Coordinate(-34.832806, -68.480683); //mendoza 17
		laPampa = new Coordinate(-37.370260, -65.820034); //la pampa 18
		neuquen = new Coordinate(-38.535750, -69.958821); //neuquen 19
		rioNegro = new Coordinate(-40.206777, -67.021021); //rio negro 20
		chubut = new Coordinate(-43.922356, -68.776310); //chubut 21
		santaCruz = new Coordinate(-48.785080, -70.032728); //santa cruz 22
		tierra = new Coordinate(-54.569293, -66.962762); //tierra del  23
	}
	
	private void agregarVecinos() {
		grafo.agregarVecinos(0, 1);
		grafo.agregarVecinos(1, 2);
		grafo.agregarVecinos(1, 3);
		grafo.agregarVecinos(1, 4);
		grafo.agregarVecinos(1, 5);
		grafo.agregarVecinos(1, 6);
		grafo.agregarVecinos(2, 6);
		grafo.agregarVecinos(3, 4);
		grafo.agregarVecinos(3, 5);
		grafo.agregarVecinos(3, 10);
		grafo.agregarVecinos(3, 11);
		grafo.agregarVecinos(4, 5);
		grafo.agregarVecinos(5, 6);
		grafo.agregarVecinos(5, 9);
		grafo.agregarVecinos(5, 11);
		grafo.agregarVecinos(6, 7);
		grafo.agregarVecinos(6, 9);
		grafo.agregarVecinos(7, 9);
		grafo.agregarVecinos(7, 12);
		grafo.agregarVecinos(7, 8);
		grafo.agregarVecinos(9, 11);
		grafo.agregarVecinos(9, 12);
		grafo.agregarVecinos(9, 15);
		grafo.agregarVecinos(10, 11);
		grafo.agregarVecinos(10, 13);
		grafo.agregarVecinos(10, 14);
		grafo.agregarVecinos(11, 14);
		grafo.agregarVecinos(11, 15);
		grafo.agregarVecinos(11, 18);
		grafo.agregarVecinos(12, 15);
		grafo.agregarVecinos(13, 14);
		grafo.agregarVecinos(13, 17);
		grafo.agregarVecinos(14, 17);
		grafo.agregarVecinos(14, 18);
		grafo.agregarVecinos(15, 18);
		grafo.agregarVecinos(15, 20);
		grafo.agregarVecinos(15, 16);
		grafo.agregarVecinos(17, 18);
		grafo.agregarVecinos(17, 19);
		grafo.agregarVecinos(18, 19);
		grafo.agregarVecinos(18, 20);
		grafo.agregarVecinos(19, 20);
		grafo.agregarVecinos(20, 21);
		grafo.agregarVecinos(21, 20);
		grafo.agregarVecinos(21, 22);
		grafo.agregarVecinos(22, 23);
		
	}
	
	private void listaProvincias() {
		provincias.add(jujuy);
		provincias.add(salta);
		provincias.add(formosa);
		provincias.add(catamarca);
		provincias.add(tucuman);
		provincias.add(santiago);
		provincias.add(chaco);
		provincias.add(corrientes);
		provincias.add(misiones);
		provincias.add(santaFe);
		provincias.add(laRioja);
		provincias.add(cordoba);
		provincias.add(entreRios);
		provincias.add(sanJuan);
		provincias.add(sanLuis);
		provincias.add(buenosAires);
		provincias.add(caba);
		provincias.add(mendoza);
		provincias.add(laPampa);
		provincias.add(neuquen);
		provincias.add(rioNegro);
		provincias.add(chubut);
		provincias.add(santaCruz);
		provincias.add(tierra);
	}
}
