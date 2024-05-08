package tp2;

import java.util.*;

public class AGMPrim {
    private static final int INF = Integer.MAX_VALUE;

    // Método para encontrar el árbol generador mínimo utilizando el algoritmo de Prim
    public static List<Integer> primMST(Grafo grafo) {
    	/*
        int cantVertices = grafo.tamano(); // Número de nodos
        boolean[] marcados = new boolean[cantVertices]; // Array para marcar nodos visitados
        int[] clave = new int[cantVertices]; // Array para almacenar las claves mínimas
        int[] nodosPadre = new int[cantVertices]; // Array para almacenar los nodos padre en el AGM

        // Inicializar claves como infinito y marcar todos los nodos como no visitados
        Arrays.fill(clave, INF);
        Arrays.fill(nodosPadre, -1);

        // Clave del primer nodo como 0 para iniciar el AGM
        clave[0] = 0;

        // Almacenar las aristas del AGM
        List<Arista> agm = new ArrayList<>();

        // Construir el MST
        for (int i = 0; i < cantVertices-1; i++) {
            int u = claveMin(clave, marcados); // Obtener el vértice con la clave mínima no incluido en el AGM
            marcados[u] = true; // Marcar el vértice como visitado

            // Actualizar las claves y los nodos padre de los vértices adyacentes
            
            
            //for (Arista arista : grafo.adjList.get(u)) {
             //   int vertice = arista.devolverDestino();
             //   if (!marcados[vertice] && arista.devolverPeso() < clave[vertice]) {
              //      nodosPadre[vertice] = u;
              //      clave[vertice] = arista.devolverPeso();
             //   }
          //  }
           // 
            
            for (int vert = 0; vert < cantVertices; vert++) {
                if (grafo.devolverContCoord(u, vert) && !marcados[vert] && 1 < clave[vert]) {
                    nodosPadre[vert] = u;
                    clave[vert] = 1;
                }
            }
        }

        // Construir la lista de aristas del MST
        for (int i = 1; i < cantVertices; i++) {
            agm.add(new Arista(nodosPadre[i], i, clave[i]));
        }

        return agm;
        */
    	
    	
    	
    	
        int cantVertices = grafo.tamano();
        Set<Integer> marcados = new HashSet<>();
        List<Integer> agm = new ArrayList<>();

        // Inicializar el conjunto de vértices incluidos
        marcados.add(0);

        while (marcados.size() < cantVertices) {
            int pesoMin = Integer.MAX_VALUE;
            int verticeMin = -1;
            int verticeAdy = -1;

            // Buscar la arista más pequeña que conecta un vértice incluido con uno no incluido
            for (int vertice : marcados) {
                for (int i = 0; i < cantVertices; i++) {
                    if (!marcados.contains(i) && grafo.existeArista(vertice, i) && grafo.devolverContCoord(vertice, i) < pesoMin) {
                        pesoMin = grafo.devolverContCoord(vertice, i);
                        verticeMin = vertice;
                        verticeAdy = i;
                    }
                }
            }

            // Agregar el vértice y la arista al árbol generador mínimo
            marcados.add(verticeAdy);
            agm.add(verticeMin);
            agm.add(verticeAdy);
        }

        return agm;
    	
    	
    	
    	
    	
    }

    // Método para encontrar el vértice con la clave mínima no incluido en el AGM
    private static int claveMin(int[] clave, boolean[] marcado) {
        int min = INF;
        int minIndice = -1;

        for (int i = 0; i < clave.length; i++) {
            if (!marcado[i] && clave[i] < min) {
                min = clave[i];
                minIndice = i;
            }
        }

        return minIndice;
    }
    
}