/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package challange5_javaapplet;

import java.awt.Color;
import java.awt.Graphics;
import java.io.DataInputStream;
import java.io.IOException;
import static java.lang.Double.NaN;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.*;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Challange5_JavaApplet extends JPanel{
    
    
    public static int sampleSet = 20;
    
    public static int receivedCounter = 0;
    
    public static int S1counter = 0;
    public static int S2counter = 0;
    public static int S3counter = 0;
    public static int S4counter = 0;
    public static int S5counter = 0;
    public static int S6counter = 0;
    public static int rect;
    public static int circle;
    public static int flagMax1 = 0 ;
    public static int flagMax2 = 0;
    
    public static double X, Y;
    public static int locationX = -1;
    public static int locationY = -1;
    
    
    
    public static double solveQuadratic(double a, double b, double c)
    {
        if(Math.sqrt(Math.pow(b,2) - 4*a*c)== 0)
        {
            return -b/(2*a);
        }
        else
        {
            double root1, root2;
            root1 = (-b + Math.sqrt(Math.pow(b,2) - 4*a*c))/(2*a);
            root2 = (-b - Math.sqrt(Math.pow(b,2) - 4*a*c))/(2*a);
            return root1;
        }    
            
    }
    
    public static int localization (double M1, double M2 , int FlagMax1, int FlagMax2)
    {
        double C1, C2, C3, C4;
        
        double R1 = 0, R2 = 0;
        double X1 = 0, X2= 0, Y1 = 0, Y2 =0;
        double A, B, C;
        //double X, Y;
        
        R1 = M1;
        R2 = M2;
        
        
        switch (FlagMax1) 
        {
            case 1:
                    X1 = 21;
                    Y1 = 1;
                    //R1 = sensor1(M1);
                    break;
            case 2:
                    X1 = 3;
                    Y1 = 1;
                    //R1 = sensor2(M1);
                    break;
            case 3:
                    X1 = 24;
                    Y1 = 30;
                    //R1 = sensor3(M1);
                    break;
            case 4:
                    X1 = 1;
                    Y1 = 30;
                    //R1 = sensor4(M1);
                    break;
            case 5:
                    X1 = 20;
                    Y1 = 60;
                    //R1 = sensor5(M1);
                    break;
            case 6:
                    X1 = 4;
                    Y1 = 60;
                    //R1 = sensor6(M1);
                    break;
        }            
         
        switch (FlagMax2) 
        {
            case 1:
                    X2 = 21;
                    Y2 = 0;
                    //R2 = sensor1(M2);
                    break;
            case 2:
                    X2 = 3;
                    Y2 = 0;
                    //R2 = sensor2(M2);
                    break;
            case 3:
                    X2 = 24;
                    Y2 = 30;
                    //R2 = sensor3(M2);
                    break;
            case 4:
                    X2 = 0;
                    Y2 = 30;
                    //R2 = sensor4(M2);
                    break;
            case 5:
                    X2 = 3;
                    Y2 = 60;
                    //R2 = sensor5(M2);
                    break;
            case 6:
                    X2 = 21;
                    Y2 = 60;
                    //R2 = sensor6(M2);
                    break;
        }
        
        System.out.println("In localization " + "R1 : "+ R1 + " R2: " + R2);
        System.out.println();
        System.out.println("Constant " + "X1 : "+ X1 + " Y1: " + Y1 + "X2 : "+ X2 + " Y2: " + Y2);
        C1 = (R1*R1) - (R2*R2) - (X1*X1) + (X2*X2) - (Y1*Y1) + (Y2*Y2) ;
        C2 = (2*X2 - 2*X1);
        C3 = (2*Y2 - 2*Y1);
        C4 = C1 - X1;
        A = (C3*C3) + (C2*C2);
        B = -2 * ( (C3*C4) - (Y1*C2*C2));
        C = (Y1*Y1) + (C4*C4) + (C2*C2*R1*R1);
        
        System.out.println();
        System.out.println("C1: "+ C1 + "C2: "+ C2 + "C3: "+ C3 + "C4: " + C4 + "A: " +A + "B: "+B + "C: "+C);
        System.out.println();
        double a = A;
        double b = B;
        double c = C;
        double root = 0, root1 = 0, root2 = 0;
        double y1 , y2, y3 ;
        double x1 , x2 , x3 ;
        Y= 0;
        if(Math.sqrt(Math.pow(b,2) - 4*a*c) >  0)
        {
            
            System.out.println("In real number loop");
            y2 = (-b + Math.sqrt(Math.pow(b,2) - 4*a*c))/(2*a);
            y3 = (-b - Math.sqrt(Math.pow(b,2) - 4*a*c))/(2*a);
            if( y2 >= 0 && y2 <= 60)
                Y = y2 ;
            if(y3 >= 0 && y3 <= 60)
                Y = y3;
        }
        else
        {
            Y = -b/(2*a);
        }
        
        
        //Y = solveQuadratic(A, B, C);
        
        X = ( C1 - Y * C3)/ C2;
        //Y = Y + 15;
        System.out.println("X: " + X + "Y: " + Y);
        
        
        if(X < 25 && X >0 && Y >0 && Y <60)
        
        {
            rect =0;
            circle =1;
        }
        else
        {
            rect =1;
            circle =0;
        }

        System.out.println("Rect: " + rect + "circle: " + circle);
        return FlagMax1;        
    }        
    
    public static void sortRSSIValue(double s1rssiValueAvg, double s2rssiValueAvg, double s3rssiValueAvg, double s4rssiValueAvg, double s5rssiValueAvg, double s6rssiValueAvg )
    {
        double RSSIarrayDist [] = new double [6];
        double swap;
        int c,d;
        int n = 6;
        double R1, R2;
        double Range1;
        double Range2;
        double Range3;
        double Range4;
        double Range5;
        double Range6;
        double max1 = 0;
        double max2 = 0;
    
        
        
        Range1 = sensor1(s1rssiValueAvg);
        Range2 = sensor2(s2rssiValueAvg);
        Range3 = sensor3(s3rssiValueAvg);
        Range4 = sensor4(s4rssiValueAvg);
        Range5 = sensor5(s5rssiValueAvg);
        Range6 = sensor6(s6rssiValueAvg);
        
        if (Range1 > 35)
        {
            Range1 =35;
        }    
        if (Range2 > 35)
        {
            Range2 =35;
        }
        if (Range3 > 35)
        {
            Range3 =35;
        }
        if (Range4 > 35)
        {
            Range4 =35;
        }
        if (Range5 > 35)
            
        {
            Range5 =35;
        }
        if (Range6 > 35)
        {
            Range6 =35;
        }
        
        RSSIarrayDist [0] = Range1;
        RSSIarrayDist [1] = Range2;
        RSSIarrayDist [2] = Range3;
        RSSIarrayDist [3] = Range4;
        RSSIarrayDist [4] = Range5;
        RSSIarrayDist [5] = Range6;
        
        
        System.out.println("R1: "+ Range1 + " R2: " + Range2 +" R3: "+ Range3 + " R4: " + Range4 + " R5: " + Range5 + " R6: " + Range6);
        
        
        
        for (c = 0 ; c < ( n - 1 ); c++)
        {
            for (d = 0 ; d < n - c - 1; d++)
            {
              if (RSSIarrayDist[d] > RSSIarrayDist[d+1]) /* For decreasing order use < */
              {
                swap = RSSIarrayDist[d];
                RSSIarrayDist[d]   = RSSIarrayDist[d+1];
                RSSIarrayDist[d+1] = swap;
              }
            }
        }
                
                
        System.out.println("For before Flag");        
        max1 = RSSIarrayDist[0];
        int i =1;
        
        if(max1 == Range1)
        {
            flagMax1 = 1;
            if(Range2 < Range3 && Range2 != NaN)
            {
                max2 = Range2;
                flagMax2 = 2;
            }
            else
            {
                max2 = Range3;
                flagMax2 = 3;
            }    
        }   
        
        if(max1 == Range2)
        {
            flagMax1 = 2;
            if(Range4 < Range1 && Range4 != NaN)
            {
                max2 = Range4;
                flagMax2 = 4;
            }
            else
            {
                max2 = Range1;
                flagMax2 = 1;
            }    
        }    
            
            
        if(max1 == s3rssiValueAvg)
        {   
            System.out.println("For before Flag");
            flagMax1 = 3;
            if(Range1 < Range5 && Range1 != NaN)
            {
                max2 = Range1;
                flagMax2 = 1;
            }
            else
            {
                max2 = Range5;
                flagMax2 = 5;
            }    
                
        }   
        
        if(max1 == Range4)
        {   
            
            flagMax1 = 4;
            if(Range2 < Range6 &&  Range2 != NaN)
            {
                max2 = Range2;
                flagMax2 = 2;
            }
            else
            {
                max2 = Range6;
                flagMax2 = 6;
            }    
                
        }
        
        if(max1 == Range5)
        {
            flagMax1 = 5;
            if(Range6 < Range3 && Range6 != NaN)
            {
                max2 = Range6;
                flagMax2 = 6;
            }
            else
            {
                max2 = Range3;
                flagMax2 = 3;
            }    
                
        }
        
        if(max1 == Range6)
        {
            flagMax1 = 6;
            if(Range5 < Range4 && Range5 != NaN)
            {
                max2 = Range5;
                flagMax2 = 5;
            }
            else
            {
                max2 = Range4;
                flagMax2 = 4;
            }    
                
        }
        
        
        System.out.println("For after Flag");        
        
        
        System.out.println( flagMax1 + " : max1 "  + max1 + "      " +  flagMax2 + " : max2 "  + max2);       
        
        //System.out.println("In localization " + "R1 : "+ R1 + " R2: " + R2);
        
        localization (max1, max2 , flagMax1, flagMax2);
            
    }
      
    public static double sensor1 (double x)     // 79B0 
    {
        return  1.9505498020868934e+000 * Math.pow(x,0) +  5.2215200536947148e-001 * Math.pow(x,1) +  2.8763560601013691e-002 * Math.pow(x,2);
    }
   
    public static double sensor5 (double x)     // 7DF3 
    {
        return -4.5995145469217906e+000 * Math.pow(x,0) + -2.7253606708760725e-001 * Math.pow(x,1) +  1.5466681818986203e-002 * Math.pow(x,2);
    }
    
    public static double sensor2 (double x)     // 78FB 
    {
        return  1.2326205095257823e+000 * Math.pow(x,0)    +  4.9223564037444589e-001 * Math.pow(x,1)    +  3.0965092590689833e-002 * Math.pow(x,2);
    }
    
    public static double sensor4 (double x)     // 45BB 
    {
        return  6.6029699970802156e+000 * Math.pow(x,0) +  1.1607338733622301e+000 * Math.pow(x,1) +  3.1008898623061689e-002 * Math.pow(x,2);
    }
    
    public static double sensor3 (double x)     // 80F5 
    {
        return -3.4550312399971828e-001 * Math.pow(x,0)    + -9.3910614802363457e-002 * Math.pow(x,1)  +  8.3951776337415976e-003 * Math.pow(x,2);
    }
    
    public static double sensor6 (double x)     // 79B0 
    {
        return -2.0284848484848485e+001 * Math.pow(x,0) + -3.0765512265512269e+000 * Math.pow(x,1) +  8.8143338143338149e-002 * Math.pow(x,2);
    }    
    
    
    public static void makeCountZero()
    {
        
                
    
    }        
    
    public void paint (Graphics g) {
        g.setColor(Color.LIGHT_GRAY);
        g.fillRect (10, 10, 240, 600);
        g.setColor(Color.BLACK);
        g.fillRect(10,10,10,10);
        g.setColor(Color.white);
        g.fillRect(10,600,10,10);
        g.setColor(Color.BLUE);
        
        int flag = flagMax1;
        if(rect == 1)
        {
            if (flag == 1)
            g.fillRect(10, 10, 120, 200);    //breadth, height
            if (flag == 2)
            g.fillRect(130, 10, 120, 200);
            if (flag == 3)
            g.fillRect(10, 210, 120, 200);
            if (flag == 4)
            g.fillRect(130, 210, 120, 200);
            if (flag == 5)
            g.fillRect(10, 410, 120, 200);
            if (flag == 6)
            g.fillRect(130, 410, 120, 200);
        }
        else if(circle == 1)
        {
            g.fillOval(250 - ((int)X*10), 10 + ((int)Y*10), 60 , 60);
        }
   
        
    }
   
    
    public static void main(String[] args) throws IOException 
    {
        int Socketio_port = 6060;
        Connection conn = null;
        Statement stmt = null;
        JFrame f = new JFrame();
        
        f.setSize(300, 660);
        f.add(new Challange5_JavaApplet());
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setVisible(true);
        ServerSocket serverSocket  = new ServerSocket(Socketio_port);
        System.out.println("Waiting for client connection ");
        Socket server = serverSocket.accept();
        System.out.println("Connected to the client");
        
        double s1rssiValue = 0;
        double s2rssiValue = 0;
        double s3rssiValue = 0;
        double s4rssiValue = 0;
        double s5rssiValue = 0;
        double s6rssiValue = 0;
        int rssiValue;
        String spotAddress;
        
        while(true)
        {
           
            DataInputStream in = new DataInputStream(server.getInputStream());
            rssiValue = in.readInt();
            spotAddress = in.readUTF();
            System.out.println(receivedCounter + "Address" + spotAddress + "Values: " + rssiValue );
            
            if (spotAddress.equals("0014.4F01.0000.79B0")) // SPOT S1
            {
             S1counter++;
             s1rssiValue = s1rssiValue + rssiValue;
             receivedCounter ++;
            }    

            if (spotAddress.equals("0014.4F01.0000.78FB"))  //SPOT S2
            {
             S2counter++;
             s2rssiValue = s2rssiValue + rssiValue;
             receivedCounter ++;
            }

            if (spotAddress.equals("0014.4F01.0000.80F5"))  // SPOT S3
            {
             S3counter++;
             s3rssiValue = s3rssiValue + rssiValue;
             receivedCounter ++;
            }

            if (spotAddress.equals("0014.4F01.0000.45BB"))  // SPOT S4
            {
             S4counter++;
             s4rssiValue = s4rssiValue +  rssiValue;
             receivedCounter ++;
            }

            if (spotAddress.equals("0014.4F01.0000.7DF3"))  // SPOT S5
            {
             S5counter++;
             s5rssiValue = s5rssiValue + rssiValue;
             receivedCounter ++;
            }

            if (spotAddress.equals("0014.4F01.0000.358F"))  // SPOT S6
            {
             S6counter++;
             s6rssiValue = s6rssiValue + rssiValue;
             receivedCounter ++;
            }


            if(receivedCounter > sampleSet)
            {
               s1rssiValue = s1rssiValue / S1counter;  // Taking individual avg
                s2rssiValue = s2rssiValue / S2counter;
                s3rssiValue = s3rssiValue / S3counter;
                s4rssiValue = s4rssiValue / S4counter;
                s5rssiValue = s5rssiValue / S5counter;
                s6rssiValue = s6rssiValue / S6counter;
                
                System.out.println("s1rrsi: " + s1rssiValue + " s2rrsi: " + s2rssiValue + " s3rrsi: " + s3rssiValue + " s4rrsi: " + s4rssiValue 
                        + " s5rrsi: " + s5rssiValue + " s6rrsi: " + s6rssiValue );
                
                sortRSSIValue( s1rssiValue, s2rssiValue,s3rssiValue,s4rssiValue,s5rssiValue,s6rssiValue );
                
                f.repaint(); 
                receivedCounter = 0;
                
                s1rssiValue = 0;
                s2rssiValue = 0;
                s3rssiValue = 0;
                s4rssiValue = 0;
                s5rssiValue = 0;
                s6rssiValue = 0;
                       
                S1counter = 0;
                S2counter = 0;
                S3counter = 0;
                S4counter = 0;
                S5counter = 0;
                S6counter = 0;
                
            }      
               
        }

    }
    
   
}    
    
    