package Programacion.T02_Multihilo.Ejercicios;

public class E14_AdivinaNumero {
    public static void main(String[] args) {
        E14_Arbitro arbitro = new E14_Arbitro();

        // Crear varios jugadores
        E14_Jugador jugador1 = new E14_Jugador(arbitro, 1);
        E14_Jugador jugador2 = new E14_Jugador(arbitro, 2);
        E14_Jugador jugador3 = new E14_Jugador(arbitro, 3);
        E14_Jugador jugador4 = new E14_Jugador(arbitro, 4);

        // Iniciar los jugadores
        jugador1.start();
        jugador2.start();
        jugador3.start();
        jugador4.start();

        // Esperar a que los jugadores terminen
        try {
            jugador1.join();
            jugador2.join();
            jugador3.join();
            jugador4.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Juego terminado.");
    }
}
