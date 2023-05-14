import java.awt.event.ActionListener;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Random;
import java.util.Collections;

public class Keypad extends JPanel implements ActionListener, ModuleTemplate {

    public Timer timer;

    public JPanel backpanel;

    public JButton Icon1;
    public JButton Icon2;
    public JButton Icon3;
    public JButton Icon4;

    public ImageIcon C1S1;
    public ImageIcon C1S2;
    public ImageIcon C1S3;
    public ImageIcon C1S4;
    public ImageIcon C1S5;
    public ImageIcon C1S6;
    public ImageIcon C1S7;

    public ImageIcon C2S1;
    public ImageIcon C2S2;
    public ImageIcon C2S3;
    public ImageIcon C2S4;
    public ImageIcon C2S5;
    public ImageIcon C2S6;
    public ImageIcon C2S7;
    
    public ImageIcon C3S1;
    public ImageIcon C3S2;
    public ImageIcon C3S3;
    public ImageIcon C3S4;
    public ImageIcon C3S5;
    public ImageIcon C3S6;
    public ImageIcon C3S7;

    public ImageIcon image1;
    public ImageIcon image2;
    public ImageIcon image3;
    public ImageIcon image4;

    public int width = 300;
    public int height = 200;
    public int selectedcol;
    public int colindex;
    public int i, index1, index2, index3, index4, j, num1, num2, num3, num4, a, b, c, d, u;
    public ArrayList<ImageIcon> column1 = new ArrayList<>();
    public ArrayList<ImageIcon> column2 = new ArrayList<>();
    public ArrayList<ImageIcon> column3 = new ArrayList<>();
    public ArrayList<Integer> nums = new ArrayList<>();
    public ArrayList<Integer> selectednums = new ArrayList<>();
    public ArrayList<Integer> buttonspressed = new ArrayList<>();
    public ArrayList<ImageIcon> chosencol;

    public boolean button1pressed;
    public boolean pressed;
    public boolean found = false;
    public boolean struck, omgiwon;

    public Random randomcol;

    public Keypad(){
        setLayout(new GridLayout(1,1,10,10));
        backpanel = new JPanel();
        backpanel.setBackground(Color.GRAY);
        backpanel.setLayout(new GridLayout(2,2,10,10));
        randomcol = new Random();
        struck = false;
        omgiwon = false;


        //images used for the symbols

        C1S1 = new ImageIcon(new ImageIcon("Modules/KeypadImages/C1S1.png").getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT));
        C1S2 = new ImageIcon(new ImageIcon("Modules/KeypadImages/C1S2.png").getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT));
        C1S3 = new ImageIcon(new ImageIcon("Modules/KeypadImages/C1S3.png").getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT));
        C1S4 = new ImageIcon(new ImageIcon("Modules/KeypadImages/C1S4.png").getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT));
        C1S5 = new ImageIcon(new ImageIcon("Modules/KeypadImages/C1S5.png").getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT));
        C1S6 = new ImageIcon(new ImageIcon("Modules/KeypadImages/C1S6.png").getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT));
        C1S7 = new ImageIcon(new ImageIcon("Modules/KeypadImages/C1S7.png").getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT));

        C2S1 = new ImageIcon(new ImageIcon("Modules/KeypadImages/C2S1.png").getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT));
        C2S2 = new ImageIcon(new ImageIcon("Modules/KeypadImages/C2S2.png").getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT));
        C2S3 = new ImageIcon(new ImageIcon("Modules/KeypadImages/C2S3.png").getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT));
        C2S4 = new ImageIcon(new ImageIcon("Modules/KeypadImages/C2S4.png").getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT));
        C2S5 = new ImageIcon(new ImageIcon("Modules/KeypadImages/C2S5.png").getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT));
        C2S6 = new ImageIcon(new ImageIcon("Modules/KeypadImages/C2S6.png").getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT));
        C2S7 = new ImageIcon(new ImageIcon("Modules/KeypadImages/C2S7.png").getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT));

        C3S1 = new ImageIcon(new ImageIcon("Modules/KeypadImages/C3S1.png").getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT));
        C3S2 = new ImageIcon(new ImageIcon("Modules/KeypadImages/C3S2.png").getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT));
        C3S3 = new ImageIcon(new ImageIcon("Modules/KeypadImages/C3S3.png").getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT));
        C3S4 = new ImageIcon(new ImageIcon("Modules/KeypadImages/C3S4.png").getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT));
        C3S5 = new ImageIcon(new ImageIcon("Modules/KeypadImages/C3S5.png").getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT));
        C3S6 = new ImageIcon(new ImageIcon("Modules/KeypadImages/C3S6.png").getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT));
        C3S7 = new ImageIcon(new ImageIcon("Modules/KeypadImages/C3S7.png").getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT));
        
        column1.add(C1S1);
        column1.add(C1S2);
        column1.add(C1S3);
        column1.add(C1S4);
        column1.add(C1S5);
        column1.add(C1S6);
        column1.add(C1S7);

        column2.add(C2S1);
        column2.add(C2S2);
        column2.add(C2S3);
        column2.add(C2S4);
        column2.add(C2S5);
        column2.add(C2S6);
        column2.add(C2S7);

        column3.add(C3S1);
        column3.add(C3S2);
        column3.add(C3S3);
        column3.add(C3S4);
        column3.add(C3S5);
        column3.add(C3S6);
        column3.add(C3S7);

        nums.add(0);
        nums.add(1);
        nums.add(2);
        nums.add(3);
        nums.add(4);
        nums.add(5);
        nums.add(6);


        timer = new Timer(1000, new ActionListener() {
			public void actionPerformed(ActionEvent e){
                
                if(selectednums.equals(buttonspressed)){
                    timer.stop();
                    omgiwon = true;
                    backpanel.remove(Icon1);
                    backpanel.remove(Icon2);
                    backpanel.remove(Icon3);
                    backpanel.remove(Icon4);
                    backpanel.setBackground(Color.GREEN);
                    
                }else if(buttonspressed.size()==4){
                    start();
                }
			}
		});

        selectedcol = randomcol.nextInt(3);

        switch(selectedcol){
            case 0: chosencol = column1;
            break;
            case 1: chosencol = column2;
            break;
            case 2: chosencol = column3;
            break;
        }
        
        //this is the initial index of the randomized numbers for the images || this is shuffled
        Collections.shuffle(nums);
        index1 = nums.get(0);
        index2 = nums.get(1);
        index3 = nums.get(2);
        index4 = nums.get(3);

        image1 = chosencol.get(index1);
        image2 = chosencol.get(index2);
        image3 = chosencol.get(index3);
        image4 = chosencol.get(index4);

        //I made a new arraylist and added the selected indexes into it so that I can sort it to check it with the pressed buttons 
        //This is the arraylist where the selected images are arranged so you can finally check it with the pressed button
        selectednums.add(index1);
        selectednums.add(index2);
        selectednums.add(index3);
        selectednums.add(index4);
        Collections.sort(selectednums);

        index1 = selectednums.get(0);
        index2 = selectednums.get(1);
        index3 = selectednums.get(2);
        index4 = selectednums.get(3);

        i = 0;
        while(!found){
            if(image1 == chosencol.get(j)){
                num1 = j;
                found = true;
            }else{
                j++;
            }
        i++;
        }
        found = false;
        num1 = j;

        while(!found){
            if(image2 == chosencol.get(a)){
                num2 = a;
                found = true;
            }else{

                a++;
            }
        i++;
        }
        found = false;
        num2 = a;

        i = 0;
        while(!found){
            if(image3 == chosencol.get(b)){
                num3 = b;
                found = true;
            }else{
                b++;
            }
        i++;
        }
        found = false;
        num3 = b;

        while(!found){
            if(image4 == chosencol.get(c)){
                num4 = c;
                found = true;
            }else{
                c++;
            }
        i++;
        }
        num4 = c;

        i=0;
        

        timer.start();     
        setUpComponents();
        backpanel.add(Icon1);
        backpanel.add(Icon2);
        backpanel.add(Icon3);
        backpanel.add(Icon4);
        add(backpanel);
    }

    public void start(){
        buttonspressed = new ArrayList<>();
        u++;
        struck = true;
    }

    public boolean solved(boolean i){
        return i;
    }

    public void setUpComponents(){
        Icon1 = new JButton(image1);
        Icon1.addActionListener(new ActionListener(){  
            public void actionPerformed(ActionEvent e){  
                buttonspressed.add(num1);
                    }  
        }); 

        Icon2 = new JButton(image2);
        Icon2.addActionListener(new ActionListener(){  
            public void actionPerformed(ActionEvent e){  
                buttonspressed.add(num2);
                    }  
        }); 

        Icon3 = new JButton(image3);
        Icon3.addActionListener(new ActionListener(){  
            public void actionPerformed(ActionEvent e){  
                buttonspressed.add(num3);
                    }  
        }); 

        Icon4 = new JButton(image4);
        Icon4.addActionListener(new ActionListener(){  
            public void actionPerformed(ActionEvent e){  
                buttonspressed.add(num4);
                    }  
        }); 

    }

    @Override
    public void actionPerformed(ActionEvent e) {}
    
}