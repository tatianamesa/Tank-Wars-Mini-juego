import java.util.Random;
import java.util.Scanner;

public class Principal
{
    public static int generarNumero(int min, int max){
        Random random = new Random();
        return random.nextInt((max - min) + 1) + min;
    }

    public static void main (String[] args){
        Tanque[][] tablero = new Tanque[2][2];
        int noTanques = Principal.generarNumero(1,4);
        int conteo = 0;
        System.out.println("Número de tanques: "+noTanques);

        for(int f=0; f<2; f++){
            for(int c=0; c<2; c++){
                int tipoTanque = Principal.generarNumero(1,2);
                if(tipoTanque==1 && conteo < noTanques){
                    System.out.println(tipoTanque+" -> Se crea un TanqueNormal");    
                }
                else if(tipoTanque==2 && conteo < noTanques){
                    System.out.println(tipoTanque+" -> Se crea un TanqueAlien");
                }

                if(tipoTanque == 1 && conteo < noTanques ){
                    Tanque tanquenormal = new TanqueNormal();
                    tablero[f][c] = tanquenormal;
                    conteo++;
                }
                else if(tipoTanque == 2 &&  conteo < noTanques){
                    Tanque tanquealien = new TanqueAlien();
                    tablero[f][c] = tanquealien;
                    conteo++;
                }
                else if(conteo >=noTanques){
                    break;
                }

            }

            if(conteo >noTanques){
                break;
            }
        }
        for(int f=0; f<tablero.length; f++){
            for(int c=0; c<tablero[0].length; c++){
                System.out.println(tablero[f][c]);
            }        
        }
        int conteoDisparos = 0;

        while(true){ 
            String resultado1 = tablero[0][0] != null && tablero[0][0].vida!= 0?tablero[0][0].referencia + (tablero[0][0].vida):"     ";
            String resultado2 = tablero[0][1] != null && tablero[0][1].vida!= 0?tablero[0][1].referencia + (tablero[0][1].vida):"     ";
            String resultado3 = tablero[1][0] != null && tablero[1][0].vida!= 0?tablero[1][0].referencia + (tablero[1][0].vida):"     ";
            String resultado4 = tablero[1][1] != null && tablero[1][1].vida!= 0?tablero[1][1].referencia + (tablero[1][1].vida):"     ";

            if(resultado1!= "     " || resultado2 != "     " || resultado3!= "     " || resultado4!= "     "){
                System.out.println( "-------------"
                    + "\n"+"|"+resultado1+"|"+resultado2+"|"+
                    "\n" + "-------------"+"\n"+"|"+resultado3+"|"+resultado4+"|"+"\n"+
                    "-------------") ;
            }
            else{
                System.out.println( "-------------"
                    + "\n"+"|"+resultado1+"|"+resultado2+"|"+
                    "\n" + "-------------"+"\n"+"|"+resultado3+"|"+resultado4+"|"+"\n"+
                    "-------------") ;
                System.out.println("----------FIN DEL JUEGO----------");        
                break;
            }

            System.out.println("---------- Bienvenido a Tank Wars Mini-juego ----------"+"\n"+
                "Elige una opcíon: \n"+
                "Oprime 1: Disparar \n"+
                "Oprime 2: Activar Bomba \n"+
                "Oprime 3: Activar Tanque mutante \n"+
                "Oprime 4: Invocar frase de la Abuela \n"+
                "Oprime 5: Ver la cantidad de disparos \n"+
                "Oprime 6: Leer cantidad de salud");

            Scanner scan = new Scanner(System.in);
            int opcion = scan.nextInt();

            switch(opcion){
                case 1:
                    System.out.println("Elige la coordenada del tanque al que quieres disparar \n"+
                        "Primero selecciona fila y después columna");
                    System.out.println("numero de fila");
                    int m = scan.nextInt();
                    System.out.println("numero de columna");
                    int n = scan.nextInt();

                    if(tablero[m][n]!=null){
                        Principal.disparar(tablero[m][n]);
                        conteoDisparos++;
                    }
                    break;

                case 2:    

                    boolean seDestruyoUnTanque = false;

                    while(seDestruyoUnTanque == false){
                        int o = Principal.generarNumero(0,1);
                        int p = Principal.generarNumero(0,1);
                        if(tablero[o][p]!=null && tablero[o][p].vida>0){
                            Principal.bombaAtomica(tablero[o][p]);
                            seDestruyoUnTanque = true;

                            System.out.println("Has destruido un tanque aleatoriamente");

                        }   
                    }
                    break;
                case 3:
                    int vidaMenor = tablero[0][0].vida;
                    Tanque tanqueMenor = tablero[0][0];
                    for(int x=0; x<tablero.length; x++){
                        for(int y=0; y<tablero[0].length; y++){

                            if(tablero[x][y]!= null && tablero[x][y].vida>0 && vidaMenor>tablero[x][y].vida){
                                vidaMenor = tablero[x][y].vida;  
                                tanqueMenor =  tablero[x][y]; 
                            }
                            
                        } 
                    }
                    for(int g=0; g<tablero.length; g++){
                        for(int h=0; h<tablero[0].length; h++){
                            if( tablero[g][h]!=null&&tablero[g][h].vida==vidaMenor){
                                Principal.tanqueMutante(tablero[g][h]);
                            }
                        }
                    }
                    
                    System.out.println("Se ha duplicado la vida del tanque más débil");    
                    break;

                case 4:
                    System.out.println(Principal.fraseAbuela()); 
                    break;

                case 5:
                    System.out.println("Llevas "+conteoDisparos+ " disparos hasta el momento...");
                    break;

                case 6:  
                    System.out.println("Elige la coordenada del tanque, para ver sus salud \n"+
                        "Primero selecciona fila y después columna");
                    int s = scan.nextInt();
                    int d = scan.nextInt();
                    if(tablero[s][d]!= null ){
                        System.out.println("La vida de este tanque es: "+Principal.mostrarSalud(tablero[s][d]) ); 
                    }   
                    break;
            }                                     
        }             
    }

    public static void disparar(Tanque posicion){
        posicion.vida = posicion.vida -5; 

    }

    public static void bombaAtomica(Tanque posicion){
        posicion.vida = 0;
        posicion=null;

    }

    public static void tanqueMutante(Tanque posicion){
        posicion.vida = posicion.vida*2; 
    }

    public static String fraseAbuela(){
        String frase = "¡¡¡ Nadie le gana a mi saber ancestral !!!";
        return frase; 
    }

    public static int mostrarSalud(Tanque posicion){
        int salud = posicion.vida;
        return salud; 
    }     

}


