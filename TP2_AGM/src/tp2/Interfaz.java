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

public class Interfaz {

	private JFrame frame;
	private JMapViewer mapa;
	private ArrayList<Coordinate> provincias;
	private Coordinate jujuy, salta, formosa, catamarca, tucuman, santiago, chaco, corrientes, misiones, santaFe, laRioja, cordoba, 
						entreRios, sanJuan, sanLuis, buenosAires, caba, mendoza, laPampa, neuquen, rioNegro, chubut, santaCruz, tierra;
	
	private Grafo grafo;
	private JPanel panelMapa, panelGeneral, panel2;
	private JTextField text_p1, text_p2;
	private JLabel l_y;
	private JLabel lblNewLabel;
	private JTextField text_peso;

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
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 800, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//panelMapa.add(mapa);
		
		
		panel2 = new JPanel();
		panel2.setBackground(new Color(192, 192, 192));
		panel2.setBounds(0, 0, 400, 600);
		frame.getContentPane().add(panel2);
		panel2.setLayout(null);
		
		mapa = new JMapViewer();
		mapa.setBounds(0, 0, 444, 561);
		mapa.setAlignmentY(Component.TOP_ALIGNMENT);
		mapa.setAlignmentX(Component.LEFT_ALIGNMENT);
		mapa.setPreferredSize(new Dimension(500, 600));
		mapa.setZoomControlsVisible(false);
		//mapa.setBounds(0, 0, 0, 600);
		
		panel2.add(mapa);
		
		text_p1 = new JTextField();
		text_p1.setBounds(464, 48, 86, 20);
		panel2.add(text_p1);
		text_p1.setColumns(10);
		
		text_p2 = new JTextField();
		text_p2.setBounds(593, 48, 86, 20);
		panel2.add(text_p2);
		text_p2.setColumns(10);
		
		JLabel agregarPeso = new JLabel("Agregar peso arista entre:");
		agregarPeso.setBounds(454, 11, 160, 26);
		panel2.add(agregarPeso);
		
		l_y = new JLabel("y");
		l_y.setBounds(568, 41, 27, 34);
		panel2.add(l_y);
		
		lblNewLabel = new JLabel("Peso:");
		lblNewLabel.setBounds(454, 87, 46, 14);
		panel2.add(lblNewLabel);
		
		text_peso = new JTextField();
		text_peso.setBounds(504, 84, 46, 20);
		panel2.add(text_peso);
		text_peso.setColumns(10);
		
		JButton bot_agregar = new JButton("Agregar");
		bot_agregar.setBackground(new Color(217, 217, 217));
		bot_agregar.setBorderPainted(false);
		bot_agregar.setBounds(674, 103, 89, 23);
		panel2.add(bot_agregar);


		
		provincias = new ArrayList<Coordinate>();
		
		grafo = new Grafo(24);
		
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
		
		listaProvincias();
		
		Coordinate coordenada = new Coordinate(-40.217898, -66.831649);
		mapa.setDisplayPosition(coordenada, 4);
		
		//mapa.addMapPolygon(new MapPolygonImpl(coordenadas));
		for (Coordinate p:provincias) {
			mapa.addMapMarker(new MapMarkerDot(p));
		}
		
		crearUniones();
		/*
        for (int i = 0; i < provincias.size() - 1; i++) {
        	Coordinate start = provincias.get(i);
        	Coordinate end = provincias.get(i + 1);
        	MapPolygon poligono = new MapPolygonImpl(createLine(start, end));
        	mapa.addMapPolygon(poligono); 
		}
		*/
	
	
		
		
		
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
				}
			}
		}
	}
	
	private void agregarPesoArista() {
		
	}
	
	private void ejecutarAGM() {
		
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
