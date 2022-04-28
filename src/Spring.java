public class Spring {
    private double k;
    public Spring(){
        k=1;
    }
    public Spring(double k){
        this.k=k;
    }
    public double getK(){
        return k;
    }
    private void setK(double k){
        this.k=k;
    }
    public double[] move(double t, double dt, double x0, double v0){
        return new double[1];
    }
    public double[] move(double t, double dt, double x0){
        return move(t, dt, x0, 0);
    }
    public double[] move(double t0, double t1, double dt, double x0, double v0){
        return new double[1];
    }
    public double[] move(double t0, double t1, double dt, double x0, double v0, double m){
        return new double[1];
    }

    //To be continued
}
