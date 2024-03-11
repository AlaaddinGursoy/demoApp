public class App {

    double demo(int[] x_pos,int[] y_pos, int targetX, int targetY){

        if(x_pos == null || y_pos == null){
            return -1;
        }

        if(x_pos.length != y_pos.length || x_pos.length <= 0 || y_pos.length <= 0 ){
            return -1;
        }



        double minLength=Math.sqrt(Math.pow(Math.abs(x_pos[0]-targetX),2)+Math.pow(Math.abs(y_pos[0]-targetY),2));

        for(int i=1; i<x_pos.length; i++){

            if(minLength > Math.sqrt(Math.pow(Math.abs(x_pos[i]-targetX),2)+Math.pow(Math.abs(y_pos[i]-targetY),2))){
                minLength = Math.sqrt(Math.pow(Math.abs(x_pos[i]-targetX),2)+Math.pow(Math.abs(y_pos[i]-targetY),2));
            }
        }
        return minLength;

    }


}
