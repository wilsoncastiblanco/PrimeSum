/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package primesum;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 *
 * @author wilsoncastiblanco
 */
public class PrimeSum {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        int num = 1048574;

        System.out.println(primeSum(num));
    }

    private static ArrayList<Integer> primeSum(int num) {
        ArrayList<Integer> primes = getPrimes(num);

        return getPrimeSum(primes, num);
    }

    private static ArrayList<Integer> getPrimeSum(ArrayList<Integer> primes, int num) {
        ArrayList<Integer> result = new ArrayList<>();
        boolean found = false;
        int index = 0;
        int savedIndex  = index;
        while (!found) {
            if(index >= primes.size() - 1 || savedIndex >= primes.size() - 1){
                index = savedIndex++;
            }
            if (primes.get(savedIndex) + primes.get(index) == num) {
                result.add(primes.get(savedIndex));
                result.add(primes.get(index));
                System.err.println("Found");
                found = true;
            }else{
                index++;
            }
        }

        return result;
    }

    private static ArrayList<Integer> getPrimes(int num) {
        ArrayList<Integer> primes = new ArrayList<>();
        boolean[] flags = new boolean[num + 1];
        
        init(flags);
        int prime = 2;
        
        while(prime < num){
            crossOff(flags, prime);
            primes.add(prime);
            prime = getNextPrime(flags, prime);
            
        }
        primes.add(prime);
        return primes;
    }

    private static void init(boolean[] flags) {
        for(int i = 0; i < flags.length; i++){
            flags[i] = true;
        }
    }

    private static void crossOff(boolean[] flags, int prime) {
        for(int i = prime; i < flags.length; i+=prime){
            flags[i] = false;
        }
    }

    private static int getNextPrime(boolean[] flags, int prime) {
        int next = prime + 1;
        while(next < flags.length && !flags[next]){
            next++;
        }
        return next;
    }
}
