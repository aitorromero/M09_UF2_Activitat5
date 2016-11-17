package m09_uf2_activitat5;

import java.util.concurrent.Semaphore;

public class Semafor {
    public static float saldoTotal=100;
    
    public static void main(String[] args) {
        /**
         * Declaramos el semaphore para poder cortar el acceso a la tarea que
         * estamos realizandousando .acquire() y permitir que entre solo cuando 
         * para por .release().
         */
        final Semaphore semaph = new Semaphore(1, true);
        
        /**
         * Declaramos dos runnables, uno para cada una de las acciones 
         * (ingresar y treure saldo)
         */
        final Runnable rIngres = new Runnable() {
            /**
             * Cortamos el acceso con .acquire y ingreesamos una cantidad. 
             * Volvemos a permitir el acceso con .release.
             */
            public void run() {
                try{
                    semaph.acquire();
                    ingressar(20);
                    System.out.println(Thread.currentThread().getName() +" "+ llegirSaldo());
                    semaph.release();
                    }catch(Exception e){
                    
                }
            }
            
        };
        
        final Runnable rTreu = new Runnable() {
            /**
             * Cortamos el acceso con .acquire y extraemos una cantidad. 
             * Volvemos a permitir el acceso con .release.
             */
            public void run() {
                try{
                    semaph.acquire();
                    treure(10);
                    System.out.println(Thread.currentThread().getName() +" "+ llegirSaldo());
                    semaph.release();
                }catch(Exception e){
                    
                }
            }
            
        };
        
        /**
         * Recorremos un bucle que hemos creado para ejecutar los Thread que 
         * ejecutaran los ingresos y extraeran el dinero.
         */
        for (int i = 0; i < 10; i++) {
            new Thread(rIngres).start();
        new Thread(rTreu).start();
        }

        
    }
    
    /**
     * En este metodo recibimos una cantidad de dinero a ingresar. Se lee el 
     * saldo actual y se le suma el ingreso. Por ultimo se añade a nuestra variable
     * final utilizando guardarSaldo().
     * @param diners 
     */
    public static void ingressar (float diners){
        float aux, saldo;
        aux=llegirSaldo();
        aux+=diners;
        saldo=aux;
        guardarSaldo(saldo);
    }
    
    /**
     * En este metodo recibimos una cantidad de dinero a retirar. Se lee el saldo
     * actual y se le resta el ingreso. Por ultimo se añade a nuestra variable
     * final utilizando guardarSaldo().
     * @param diners 
     */
    public static void treure (float diners){
        float aux, saldo;
        aux=llegirSaldo();
        aux-=diners;
        saldo=aux;
        guardarSaldo(saldo);
    }
    
    /**
     * Retorna el saldoTotal
     * @return 
     */
    public static float llegirSaldo(){
        return saldoTotal;
    }
    
    /**
     * Registra el sueldo en la variable sueldoTotal.
     * @param saldo
     * @return 
     */
    public static float guardarSaldo(float saldo){
        saldoTotal=saldo;
        return saldoTotal;
    }
    
}
