package src;

class Emozioni {   
    float AMAZEMENT;
    float SOLEMNITY; 
    float TENDERNESS;
    float NOSTALGIA;
    float CALMNESS;
    float POWER;
    float JOY;
    float TENSION; 
    float SADNESS;

    public Emozioni(String[] data) {  
        this.AMAZEMENT  = Float.parseFloat(data[0]);
        this.SOLEMNITY  = Float.parseFloat(data[1]); 
        this.TENDERNESS = Float.parseFloat(data[2]);
        this.NOSTALGIA  = Float.parseFloat(data[3]);
        this.CALMNESS   = Float.parseFloat(data[4]);
        this.POWER      = Float.parseFloat(data[5]);
        this.JOY        = Float.parseFloat(data[6]);
        this.TENSION    = Float.parseFloat(data[7]); 
        this.SADNESS    = Float.parseFloat(data[8]);
    }
}