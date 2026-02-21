package ejem1;

public class Etapas {
    public static final double[][] COORDENADAS = {
        {41.1579, -8.6291},  // Oporto
        {41.3167, -8.5333},  // Vairão
        {41.5333, -8.6167},  // Barcelos
        {41.7667, -8.5833},  // Ponte de Lima
        {41.9167, -8.5667},  // Rubiães
        {42.0500, -8.6500},  // Tui
        {42.1667, -8.6167},  // O Porriño
        {42.2833, -8.6000},  // Redondela
        {42.4333, -8.6500},  // Pontevedra
        {42.6033, -8.6433},  // Caldas de Reis
        {42.7333, -8.6500},  // Padrón
        {42.8782, -8.5448}   // Santiago
    };

    public static final String[] NOMBRES = {
        "Oporto", "Vairão", "Barcelos", "Ponte de Lima", "Rubiães",
        "Tui", "O Porriño", "Redondela", "Pontevedra", "Caldas de Reis",
        "Padrón", "Santiago"
    };

    public static int calcularEtapa(double lat, double lon) {
        int etapaMasCercana = 0;
        double distanciaMinima = Double.MAX_VALUE;

        for (int i = 0; i < COORDENADAS.length; i++) {
            double distancia = calcularDistancia(lat, lon, COORDENADAS[i][0], COORDENADAS[i][1]);
            if (distancia < distanciaMinima) {
                distanciaMinima = distancia;
                etapaMasCercana = i;
            }
        }
        return etapaMasCercana;
    }

    private static double calcularDistancia(double lat1, double lon1, double lat2, double lon2) {
        final int R = 6371;
        double dLat = Math.toRadians(lat2 - lat1);
        double dLon = Math.toRadians(lon2 - lon1);
        double a = Math.sin(dLat/2) * Math.sin(dLat/2) +
                   Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) *
                   Math.sin(dLon/2) * Math.sin(dLon/2);
        return R * 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
    }
}
