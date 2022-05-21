public class FT {


    /**
     * The code is taken from the textbook provided (H. Gould, J. Tobochnik, W. Christian)
     */
    public static void transform(double[] real, double[] imag) {
        int N = real.length;
        int pow = 0;
        while (N / 2 > 0) {
            if (N % 2 == 0) {
                pow++;
                N /= 2;
            } else {
                throw new IllegalArgumentException("Number of points in this FFT implementation must be even.");
            }
        }
        int N2 = N / 2;
        int jj = N2;
        for (int i = 1; i < N - 1; i++) {
            if (i < jj) {
                double tempRe = real[jj];
                double tempIm = imag[jj];
                real[jj] = real[i];
                imag[jj] = imag[i];
                real[i] = tempRe;
                imag[i] = tempIm;
            }
            int k = N2;
            while (k <= jj) {
                jj = jj - k;
                k = k / 2;
            }
            jj = jj + k;
        }
        jj = 1;
        for (int p = 1; p <= pow; p++) {
            int inc = 2 * jj;
            double
                    wp1 = 1, wp2 = 0;
            double theta = Math.PI / jj;
            double cos = Math.cos(theta), sin = -Math.sin(theta);
            for (int j = 0; j < jj; j++) {
                for (int i = j; i < N; i += inc) {
                    int ip = i + jj;
                    double tempRe = wp1 * real[ip] - wp2 * imag[ip];
                    double tempIm = wp2 * real[ip] + wp1 * imag[ip];
                    real[ip] = real[i] - tempRe;
                    imag[ip] = imag[i] - tempIm;
                    real[i] = real[i] + tempRe;
                    imag[i] = imag[i] + tempIm;
                }
                double temp = wp1;
                wp1 = wp1 * cos - wp2 * sin;
                wp2 = temp * sin + wp2 * cos;
            }
            jj = inc;
        }
    }
}

