/**
 * La clase modela un sencillo podómetro que registra información
 * acerca de los pasos, distancia, ..... que una persona
 * ha dado en una semana. 
 * 
 * @author Jonathan Del Arco
 */
public class Podometro {
    private final char HOMBRE = 'H';
    private final char MUJER = 'M';
    
    //Estos valores representan un porcentaje  (45% y 41% respectivamente) sobre la altura de la persona que se usarán para calcular la longitud de su zancada
    private final double ZANCADA_HOMBRE = 0.45;
    private final double ZANCADA_MUJER = 0.41;
    
    private final int SABADO = 6;
    private final int DOMINGO = 7;
    
    private String marca;                   //guarda la marca del podómetro
    private double altura;                  //guarda la altura de la persona en centímetros
    private char sexo;                      //guarda el sexo de una persona (un carácter)
    private double longitudZancada;         //almacena la longitud de la zancada de la persona en centímetros
    
    private int totalPasosLaborables;       //guarda el nº de pasos dados en días laborable (de lunes a viernes)
    private int totalPasosSabado;           //guarda el nº de pasos dados el sábado
    private int totalPasosDomingo;          //guarda el nº de pasos dados el domingo
    
    private double totalDistanciaSemana;    //almacena la distancia recorrida a lo largo de toda la semana (en Kilómetros)
    private double totalDistanciaFinSemana; //almacena la distancia recorrida a lo largo del fin de semana (en Kilómetros)
    
    private double tiempo;                     //tiempo total caminado en toda la semana (en minutos)
    private int caminatasNoche;             //nº caminatas (paseos) dados a partir de las 21h. En toda la semana

    /** Inicializa el podómetro con la marca indicada por el parámetro. El resto de atributos se ponen a 0 y el sexo, por defecto, es mujer 
    CONSTRUCTOR */
    
    public Podometro(String nombreMarca) {
        marca = nombreMarca;
        altura = 0;
        sexo = 'M';
        longitudZancada = 0;
        totalPasosLaborables = 0;
        totalPasosSabado = 0;
        totalPasosDomingo = 0;
        totalDistanciaSemana = 0;
        totalDistanciaFinSemana = 0;
        tiempo = 0;
        caminatasNoche = 0;
    }

    /**Accesor para la marca
    */
    public String getMarca() {
        return marca;
    }

    /**
     * Simula la configuración del podómetro.
     * Recibe como parámetros la altura y el sexo de una persona
     * y asigna estos valores a los atributos correspondiente.
     * Asigna además el valor adecuado al atributo longitudZancada
     * 
     * (leer enunciado)
     *  
     */
    public void configurar(double queAltura, char queSexo) {      
        switch(queSexo){
            case 'H':
                longitudZancada = Math.ceil(ZANCADA_HOMBRE*queAltura);
            break;
            case 'M':
                longitudZancada = Math.floor(ZANCADA_MUJER*queAltura);
            break;
        }
        altura = queAltura;
        sexo = queSexo;
        longitudZancada = longitudZancada; //cm
    }

     /**
     *  Recibe cuatro parámetros que supondremos correctos:
     *    pasos - el nº de pasos caminados
     *    dia - nº de día de la semana en que se ha hecho la caminata 
     *              (1 - Lunes, 2 - Martes - .... - 6 - Sábado, 7 - Domingo)
     *    horaInicio – hora de inicio de la caminata
     *    horaFin – hora de fin de la caminata
     *    
     *    A partir de estos parámetros el método debe realizar ciertos cálculos
     *    y  actualizará el podómetro adecuadamente  
     *   
     *   (leer enunciado del ejercicio)
     */
    public void registrarCaminata(int pasos, int dia, int horaInicio,int horaFin){
        switch(dia){
            case 1:
            case 2:
            case 3:
            case 4:
            case 5: totalPasosLaborables += pasos;
            break;
            case 6: totalPasosSabado += pasos;
            break;
            case 7: totalPasosDomingo += pasos;
            break;    
        }
        
        if(sexo=='H'){
            totalDistanciaSemana += (totalPasosLaborables + totalPasosSabado + totalPasosDomingo)*ZANCADA_HOMBRE/1000;
            totalDistanciaFinSemana += (totalPasosSabado + totalPasosDomingo)*ZANCADA_HOMBRE/1000;
        } else if(sexo=='M') {
            totalDistanciaSemana += (totalPasosLaborables + totalPasosSabado + totalPasosDomingo)*ZANCADA_MUJER;
            totalDistanciaFinSemana += (totalPasosSabado + totalPasosDomingo)*ZANCADA_MUJER;
        }
        
        if(horaInicio>=2100){
            caminatasNoche++;
        }
        
        tiempo = horaFin - horaInicio;
    }
    
     /**
     * Muestra en pantalla la configuración del podómetro
     * (altura, sexo y longitud de la zancada)
     * 
     * (ver enunciado)
     *  
     */
    public void printConfiguracion() {
        System.out.println("\nConfiguración del podómetro");
        System.out.println("*********************************");
        System.out.println("Altura: " + altura/100 + " mtos");
        if(sexo=='H'){
            System.out.println("Sexo: HOMBRE");
        } else if (sexo=='M'){
            System.out.println("Sexo: MUJER");
        }
        System.out.println("Longitud zancada: " + longitudZancada/100 + " mtos");
    }

    /**
     * Muestra en pantalla información acerca de la distancia recorrida,
     * pasos, tiempo total caminado, ....
     * 
     * (leer enunciado)
     *  
     */
    public void printEstadísticas() {
        System.out.println("\nEstadísticas");
        System.out.println("*********************************");
        System.out.println("Distancia recorrida toda la semana: " + totalDistanciaSemana);
        System.out.println("Distancia recorrida fin de semana: " + totalDistanciaFinSemana);
        System.out.println("\nNº pasos días laborables: " + totalPasosLaborables);
        System.out.println("Nº pasos SÁBADO: " + totalPasosSabado);
        System.out.println("Nº pasos DOMINGO: " + totalPasosDomingo);
        System.out.println("\nNº caminatas realizadas a partir de las 21h.: " + caminatasNoche);
        double hor;
        double min;
        hor = Math.floor(tiempo/100);
        min = Math.floor(((tiempo/100)-hor)*100);
        if(min>=60){
            min = (min-60);
            hor = hor+1;
        }
        System.out.println("\nTiempo total caminado en la semana: " + (int)hor + "h. y " + (int)(min+1) + "m.");
        System.out.println("Día/s con más pasos caminados: " + diaMayorNumeroPasos());
    }  

    /**
     *  Calcula y devuelve un String que representa el nombre del día
     *  en el que se ha caminado más pasos - "SÁBADO"   "DOMINGO" o  "LABORABLES"
     */
    public String diaMayorNumeroPasos() {       
        int dMNP;
        String dia = "";
        
        if(totalPasosLaborables > totalPasosSabado){
            dMNP = totalPasosLaborables;
            dia = "LABORABLES";
         } else {
             dMNP = totalPasosSabado;
             dia = "SÁBADO";
         }
         
        if(totalPasosDomingo > dMNP){
            dMNP = totalPasosDomingo; 
            dia = "DOMINGO";
        }
        
        return dia;
    }
    
    
    /**
     * Restablecer los valores iniciales del podómetro
     * Todos los atributos se ponen a cero salvo el sexo
     * que se establece a MUJER. La marca no varía
     *  
     */    
    public void reset() {
        altura = 0;
        sexo = 'M';
        longitudZancada = 0;
        totalPasosLaborables = 0;
        totalPasosSabado = 0;
        totalPasosDomingo = 0;
        totalDistanciaSemana = 0;
        totalDistanciaFinSemana = 0;
        tiempo = 0;
        caminatasNoche = 0;
    }
} 
