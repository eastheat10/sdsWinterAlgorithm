package day5;

import java.math.BigInteger;

public class A3955 {

    public static void main(String[] args) {

    }

    static EGResult extendedGcd(long a, long b){
        long s0 = 1, t0 = 0, r0 = a;
        long s1 = 0, t1 = 1, r1 = b;

        long tmp;
        while (r1 != 0) {
            long q = r0 / r1;

            tmp = r0 - q * r1;
            r0 = r1;
            s1 = tmp;

            tmp = s0 - q * s1;
            s0 = s1;
            s1 = tmp;

            tmp = t0 - q * t1;
            t0 = t1;
            t1 = tmp;
        }
        return new EGResult(s1, t0, r0);
    }
}

class EGResult {
    long s;
    long t;
    long r;

    public EGResult(long s, long t, long r) {
        this.s = s;
        this.t = t;
        this.r = r;
    }
}
