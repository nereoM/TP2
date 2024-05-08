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

public class Interfaz {

	private JFrame frame;
	private JMapViewer mapa;
	private ArrayList<Coordinate> puntos;

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
		
		mapa = new JMapViewer();
		mapa.setZoomControlsVisible(false);
		
		puntos = new ArrayList<Coordinate>();
		puntos.add(new Coordinate(-23.025768, -65.944995));
		puntos.add(new Coordinate(-25.314132, -65.004313));
		puntos.add(new Coordinate(-24.722177, -60.353167));
		puntos.add(new Coordinate(-26.960915, -67.272746));
		
		puntos.add(new Coordinate(-27.009372, -65.428461));
		puntos.add(new Coordinate(-27.702253, -63.357377));
		puntos.add(new Coordinate(-26.598685, -60.371284));
		puntos.add(new Coordinate(-28.819982, -57.896771));
		puntos.add(new Coordinate(-26.687990, -54.302694));
		puntos.add(new Coordinate(-30.654618, -60.910952));
		puntos.add(new Coordinate(-30.091630, -66.721795));
		puntos.add(new Coordinate(-31.955522, -63.707614));
		puntos.add(new Coordinate(-32.060922, -59.295102));
		puntos.add(new Coordinate(-31.023815, -68.828111));
		puntos.add(new Coordinate(-34.002958, -65.967739));
		puntos.add(new Coordinate(-36.454344, -60.092248));
		puntos.add(new Coordinate(-34.832806, -68.480683));
		puntos.add(new Coordinate(-37.370260, -65.820034));
		puntos.add(new Coordinate(-38.535750, -69.958821));
		puntos.add(new Coordinate(-40.206777, -67.021021));
		puntos.add(new Coordinate(-43.922356, -68.776310));
		puntos.add(new Coordinate(-48.785080, -70.032728));
		puntos.add(new Coordinate(-54.569293, -66.962762));
		
		//coordenadas.add(new Coordinate(-23.025768, -65.944995));
		//coordenadas.add(new Coordinate(-23.025768, -65.944995));
		
		Coordinate coordenada = new Coordinate(-40.217898, -66.831649);
		mapa.setDisplayPosition(coordenada, 4);
		
		//mapa.addMapPolygon(new MapPolygonImpl(coordenadas));
		for (Coordinate p:puntos) {
			mapa.addMapMarker(new MapMarkerDot(p));
		}
		
        for (int i = 0; i < puntos.size() - 1; i++) {
        	Coordinate start = puntos.get(i);
        	Coordinate end = puntos.get(i + 1);
        	MapPolygon poligono = new MapPolygonImpl(createLine(start, end));
        	mapa.addMapPolygon(poligono); 
		}
	
	
		frame.getContentPane().add(mapa);
	}
	
	private static List<Coordinate> createLine(Coordinate start, Coordinate end) {
        List<Coordinate> linePoints = new ArrayList<>();
        linePoints.add(start);
        linePoints.add(end);
        linePoints.add(end);
        return linePoints;
    }
	
	
	
	
	
	
	
	
	
	
	

}
