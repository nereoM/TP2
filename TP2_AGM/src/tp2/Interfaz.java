package tp2;
import java.awt.EventQueue;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;

import org.openstreetmap.gui.jmapviewer.Coordinate;
import org.openstreetmap.gui.jmapviewer.JMapViewer;
import org.openstreetmap.gui.jmapviewer.MapMarkerDot;
import org.openstreetmap.gui.jmapviewer.MapPolygonImpl;
import org.openstreetmap.gui.jmapviewer.interfaces.ICoordinate;
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
import javax.swing.text.JTextComponent;

import java.awt.Font;
import java.awt.Point;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseWheelListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import javax.swing.SwingConstants;
import javax.swing.JCheckBox;

public class Interfaz {

	private JFrame frame;
	private JMapViewer mapa;
	private ArrayList<Coordinate> provincias;
	private Coordinate fijarMapa, jujuy, salta, formosa, catamarca, tucuman, santiago, chaco, corrientes, misiones, santaFe, laRioja, cordoba, 
						entreRios, sanJuan, sanLuis, buenosAires, caba, mendoza, laPampa, neuquen, rioNegro, chubut, santaCruz, tierra;
	private ArrayList<MapPolygon> lineas;
	private Grafo grafo;
	private List<Arista> aristasAgm;
	private ArrayList<JLabel> labels;
	private JPanel panel2;
	private JTextField text_p1, text_p2, regiones, text_peso;
	private JLabel cartel_error, l_sumaPesos, cartel_error2, l_y, agregarPeso, l_indice;
	private JButton bot_generar, bot_agregar, bot_reiniciar;
	private JTextPane infoProvincias;
	private int cantProvincias, cantAristas, cantRegiones, numDevuelto;
	private Color naranja, rojo;
	private JLabel l_cantRegiones;
	private LectorTxt lector;
	private JCheckBox automatico, manual;

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
		frame.setBounds(500, 200, 800, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		
		definirObjetosPantalla();
		
		definirProvincias();
		
		listaProvincias();
		
		botones();
		
		fijarMapa = new Coordinate(-40.217898, -66.831649);
		mapa.setDisplayPosition(fijarMapa, 4);
		
		naranja = new Color(255, 165, 0);
		rojo = new Color(255, 0, 0);
		
		agregarMarcadores();
		
		grafo = new Grafo(this.cantProvincias);
		
		agregarVecinos();
		
		lector = new LectorTxt();
		
		crearUnionesIniciales();
	}
	
	private void agregarPesosPorArchivo() {
		int cantNoAgregadas = lector.leerArchivo(grafo);
		if (cantNoAgregadas > 0) {
			cartel_error2.setText("No se agregaron " + cantNoAgregadas + " similaridades.");
			cartel_error2.setVisible(true);
		}
	}

	private void agregarMarcadores() {
		for (Coordinate p:provincias) {
			MapMarkerDot punto = new MapMarkerDot(p);
			punto.setBackColor(naranja);
			mapa.addMapMarker(punto);
		}
		
	}

	private void botones() {
		bot_agregar = new JButton("Agregar");
		bot_agregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cartel_error.setVisible(false);
				try {
					verificarTextPanels();
					agregarPesoArista();
					regiones.setEnabled(false);
					cartel_error2.setVisible(false);
				}
				catch (IllegalArgumentException e2) {
					cartel_error.setText("No pueden estar vacios!");
					cartel_error.setVisible(true);
				}
			}
		});
		bot_agregar.setBackground(new Color(207, 207, 207));
		bot_agregar.setBorderPainted(false);
		bot_agregar.setBounds(685, 147, 89, 23);
		bot_agregar.setEnabled(false);
		panel2.add(bot_agregar);
		
		
		
		bot_generar = new JButton("Generar");
		bot_generar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					cantRegiones = Integer.parseInt(regiones.getText());
					try {
						if (grafo.getCantAristasConPeso() != cantAristas) {
							throw new RuntimeException();
						}
						ejecutarAGM();
					}
					catch (RuntimeException e3) {
						cartel_error2.setText("No se agregaron todas las similaridades");
						cartel_error2.setVisible(true);
					}
				}
				catch (IllegalArgumentException e1) {
					cartel_error.setText("# de regiones no validas!");
					cartel_error.setVisible(true);
				}
				
			}
		});
		bot_generar.setBackground(new Color(207, 207, 207));
		bot_generar.setBorderPainted(false);
		bot_generar.setFont(new Font("Tahoma", Font.PLAIN, 11));
		bot_generar.setBounds(679, 526, 95, 26);
		panel2.add(bot_generar);
		
		
		bot_reiniciar = new JButton("Reiniciar");
		bot_reiniciar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (manual.isSelected()) {
					reiniciarMatrizPesos();
				}
				else if (automatico.isSelected()) {
					agregarPesosPorArchivo();
				}
				removerLineas();
				crearUnionesIniciales();
				l_sumaPesos.setVisible(false);
				for(JLabel l:labels) {
					l.setVisible(false);
				}
				regiones.setEnabled(true);
				regiones.setText("");
			}
		});
		bot_reiniciar.setBorderPainted(false);
		bot_reiniciar.setBackground(new Color(207, 207, 207));
		bot_reiniciar.setBounds(454, 524, 95, 26);
		panel2.add(bot_reiniciar);
		
		manual = new JCheckBox("Manualmente");
		manual.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (manual.isSelected()) {
					text_p1.setEnabled(true);
					text_p2.setEnabled(true);
					text_peso.setEnabled(true);
					bot_agregar.setEnabled(true);
					reiniciarMatrizPesos();
				}
			}
		});
		manual.setBackground(new Color(207, 207, 207));
		manual.setBounds(452, 32, 97, 23);
		panel2.add(manual);
		
		automatico = new JCheckBox("Automatico");
		automatico.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (automatico.isSelected()) {
					agregarPesosPorArchivo();
				}
			}
		});
		automatico.setBackground(new Color(207, 207, 207));
		automatico.setBounds(452, 58, 97, 23);
		panel2.add(automatico);
		
	}

	private void reiniciarMatrizPesos() {
		grafo.inicializarMatrizAdyPesos();
		
	}

	private void definirObjetosPantalla() {
		
		panel2 = new JPanel();
		panel2.setBackground(new Color(225, 225, 225));
		panel2.setBounds(0, 0, 400, 600);
		frame.getContentPane().add(panel2);
		panel2.setLayout(null);
		
		mapa = new JMapViewer();
		mapa.setBounds(0, 0, 444, 561);
		mapa.setAlignmentY(Component.TOP_ALIGNMENT);
		mapa.setAlignmentX(Component.LEFT_ALIGNMENT);
		mapa.setPreferredSize(new Dimension(500, 600));
		mapa.setZoomControlsVisible(false);
		mapa.setFocusable(false);
		
		panel2.add(mapa);
		
		l_sumaPesos = new JLabel("");
		l_sumaPesos.setVerticalAlignment(SwingConstants.TOP);
		l_sumaPesos.setFont(new Font("Arial", Font.BOLD, 12));
		l_sumaPesos.setBounds(10, 11, 211, 34);
		mapa.add(l_sumaPesos);
		l_sumaPesos.setVisible(false);
		
		text_p1 = new JTextField();
		text_p1.setBackground(new Color(207, 207, 207));
		text_p1.setFont(new Font("Arial", Font.BOLD, 11));
		text_p1.setBounds(493, 111, 56, 20);
		panel2.add(text_p1);
		text_p1.setColumns(10);
		text_p1.setEnabled(false);
		
		text_p2 = new JTextField();
		text_p2.setBackground(new Color(207, 207, 207));
		text_p2.setFont(new Font("Arial", Font.BOLD, 11));
		text_p2.setBounds(593, 111, 56, 20);
		panel2.add(text_p2);
		text_p2.setColumns(10);
		text_p2.setEnabled(false);
		
		agregarPeso = new JLabel("Agregar similaridad entre:");
		agregarPeso.setFont(new Font("Arial", Font.BOLD, 11));
		agregarPeso.setBounds(454, 74, 195, 26);
		panel2.add(agregarPeso);
		
		l_y = new JLabel("y");
		l_y.setBounds(570, 103, 13, 34);
		panel2.add(l_y);
		
		
		l_indice = new JLabel("Indice:");
		l_indice.setFont(new Font("Arial", Font.BOLD, 11));
		l_indice.setBounds(458, 151, 46, 14);
		panel2.add(l_indice);
		
		text_peso = new JTextField();
		text_peso.setBackground(new Color(207, 207, 207));
		text_peso.setFont(new Font("Arial", Font.BOLD, 11));
		text_peso.setBounds(504, 149, 46, 20);
		text_peso.setColumns(10);
		text_peso.setEnabled(false);
		panel2.add(text_peso);
		
		cantAristas = 46;
		labels = new ArrayList<JLabel>();
		for (int i = 0; i < cantAristas; i++) {
			JLabel label = new JLabel();
			label.setVisible(false);
			labels.add(label);
		}
		
		cartel_error = new JLabel();
		cartel_error.setVisible(false);
		cartel_error.setFont(new Font("Arial", Font.BOLD, 9));
		cartel_error.setBounds(659, 111, 125, 30);
		panel2.add(cartel_error);
	
		
		infoProvincias = new JTextPane();
		infoProvincias.setFont(new Font("Arial", Font.BOLD, 11));
		infoProvincias.setBackground(new Color(225, 225, 225));
		infoProvincias.setEditable(false);
		infoProvincias.setText("Informacion sobre la referencia a provincias:\n"
				+ "Jujuy = 0\n" + "Salta = 1\n" + "Formosa = 2\n" + "Catamarca = 3\n" + "Tucuman = 4\n" + "Santiago del Estero = 5\n" + "Chaco = 6\n"
				+ "Corrientes = 7\n" + "Misiones = 8\n" + "Santa Fe = 9\n" + "La Rioja = 10\n" + "Cordoba = 11\n" + "Entre Rios = 12\n" + "San Juan = 13\n"
				+ "San Luis = 14\n" + "Buenos Aires = 15\n" + "CABA = 16\n" + "Mendoza = 17\n" + "La Pampa = 18\n" + "Neuquen = 19\n" + "Rio Negro = 20\n"
				+ "Chubut = 21\n" + "Santa Cruz = 22\n" + "Tierra Del Fuego = 23");
		infoProvincias.setBounds(454, 176, 286, 339);
		panel2.add(infoProvincias);
		
		l_cantRegiones = new JLabel("Cantidad de Regiones necesarias:");
		l_cantRegiones.setFont(new Font("Arial", Font.BOLD, 11));
		l_cantRegiones.setBounds(454, 11, 195, 20);
		panel2.add(l_cantRegiones);
		
		regiones = new JTextField();
		regiones.setBackground(new Color(207, 207, 207));
		regiones.setFont(new Font("Arial", Font.BOLD, 11));
		regiones.setBounds(659, 12, 56, 20);
		panel2.add(regiones);
		regiones.setColumns(10);
		
		cartel_error2 = new JLabel("");
		cartel_error2.setFont(new Font("Arial", Font.BOLD, 9));
		cartel_error2.setVisible(false);
		cartel_error2.setBounds(567, 67, 184, 14);
		panel2.add(cartel_error2);
	}

	private static List<Coordinate> createLine(Coordinate start, Coordinate end) {
        List<Coordinate> linePoints = new ArrayList<>();
        linePoints.add(start);
        linePoints.add(end);
        linePoints.add(end);
        return linePoints;
    }
	
	private void crearUnionesIniciales() {
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
		numDevuelto = grafo.agregarPesoArista(Integer.parseInt(text_p1.getText()), Integer.parseInt(text_p2.getText()), Integer.parseInt(text_peso.getText()));
		
		if (numDevuelto == -1) {
			cartel_error.setText("No son limitrofes!");
			cartel_error.setVisible(true);
		}
		else if (numDevuelto == -2) {
			cartel_error.setText("Indice no puede ser 0!");
			cartel_error.setVisible(true);
		}
		else if (numDevuelto == 2) {
			cartel_error.setText("No tienen relacion!");
			cartel_error.setVisible(true);
		}
	}

	private void ejecutarAGM() {
		removerLineas();
		aristasAgm = grafo.kruskal();
		if (cantRegiones > aristasAgm.size()+1) {
			throw new IllegalArgumentException();
		}
		quitarK_1Aristas();
		crearUnionesAgm();
		l_sumaPesos.setText("Distancia final: " + sumaPesos(aristasAgm));
		l_sumaPesos.setVisible(true);
	}
	
	private void quitarK_1Aristas() {
		int k = cantRegiones - 1;
		for (int i = 0; i < k; i++) {
			int max = aristasAgm.get(0).getPeso();
			Arista aMax = aristasAgm.get(0);
			for (Arista a:aristasAgm) {
				if (a.getPeso() > max) {
					max = a.getPeso();
					aMax = a;
				}
			}
			aristasAgm.remove(aMax);
		}
	}
	
	private void crearUnionesAgm () {
		for (int i = 0; i < aristasAgm.size(); i++) {
			MapPolygon poligono = new MapPolygonImpl(createLine(provincias.get(aristasAgm.get(i).getOrigen()), provincias.get(aristasAgm.get(i).getDestino())));
			mapa.addMapPolygon(poligono);
			mostrarPesosAristas(i, aristasAgm);
		}
	}

	private void mostrarPesosAristas(int i, List<Arista> aristasAgm2) {
		double centroidLat = 0, centroidLon = 0;
		JLabel l = labels.get(i);
		l.setText(aristasAgm.get(i).getPeso() + "");
		centroidLat += (provincias.get(aristasAgm.get(i).getOrigen()).getLat() +  provincias.get(aristasAgm.get(i).getDestino()).getLat())/2.0;
		centroidLon += (provincias.get(aristasAgm.get(i).getOrigen()).getLon() + provincias.get(aristasAgm.get(i).getDestino()).getLon())/2.0;
		Point punto = mapa.getMapPosition(new Coordinate(centroidLat, centroidLon));
		l.setBounds(punto.x, punto.y, 150, 10);
		l.setForeground(rojo);
		l.setFont(new Font("Arial", Font.BOLD, 11));
		l.setVisible(true);
		mapa.add(l);
	}

	private int sumaPesos(List<Arista> aristas) {
		int suma = 0;
		for (Arista a:aristas) {
			suma += a.getPeso();
		}
		return suma;
	}
	
	private void removerLineas() {
		mapa.removeAllMapPolygons();
	}
	
	private void verificarTextPanels() {
		if (text_p1.getText().isEmpty() || text_p2.getText().isEmpty() || regiones.getText().isEmpty() || text_peso.getText().isEmpty()) {
			throw new IllegalArgumentException();
		}
	}
	
	private void definirProvincias() {
		provincias = new ArrayList<Coordinate>();
		lineas = new ArrayList<MapPolygon>();
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
		this.cantProvincias = provincias.size();
	}
}