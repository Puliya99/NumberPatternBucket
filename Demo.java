import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
class NumberPatternObserver extends JFrame{ 
    public void update(int random){
        System.out.println("Observer");
    }
}
class OddWindow extends NumberPatternObserver{
    private JTextArea txt1;
    OddWindow(){
        initComponents();
    }
    void initComponents(){
        setSize(300, 300);
        setTitle("Odd Frame");
        setLayout(new FlowLayout());
        txt1=new JTextArea();
        txt1.setFont(new Font("",1,25));
        add(txt1);
        setVisible(true);
    }
    @Override
    public void update(int random){
        if (random % 2 == 1){
            txt1.append(random+"\n");
        }
    }
}
class EvenWindow extends NumberPatternObserver{
    private JTextArea txt2;
    EvenWindow(){
        initComponents();
    }
    void initComponents(){
        setSize(300, 300);
        setTitle("Even Frame");
        setLayout(new FlowLayout());
        txt2=new JTextArea();
        txt2.setFont(new Font("",1,25));
        add(txt2);
        setVisible(true);
    }
    @Override
    public void update(int random){
        if (random % 2 == 0){
            txt2.append(random+"\n");
        }
    }
}
class ArmstrongWindow extends NumberPatternObserver{
    private JTextArea txt3;
    ArmstrongWindow(){
        initComponents();
    }
    void initComponents(){
        setSize(300, 300);
        setTitle("Armstrong Frame");
        setLayout(new FlowLayout());
        txt3=new JTextArea();
        txt3.setFont(new Font("",1,25));
        add(txt3);
        setVisible(true);
    }
    @Override
    public void update(int random){
        int oldNumber =random,newNum=0,digits;
        while(random!=0){
            digits=random%10;
            newNum=newNum+(digits*digits*digits);
            random/=10;
        }if(oldNumber==newNum){
            txt3.append(random+"\n");
        }
    }
}
class PalindromeWindow extends NumberPatternObserver{
    private JTextArea txt4;
    PalindromeWindow(){
        initComponents();
    }
    void initComponents(){
        setSize(300, 300);
        setTitle("Palindrome Frame");
        setLayout(new FlowLayout());
        txt4=new JTextArea();
        txt4.setFont(new Font("",1,25));
        add(txt4);
        setVisible(true);
    }
    @Override
    public void update(int random){
        int newNumber=0,oldNumber=random;
        while(random!=0){
            newNumber=(newNumber*10)+(random%10);
            random=random/10;
        }if(newNumber==oldNumber){
            txt4.append(random+"\n");
        }
    }
}
class NumberPattern extends JFrame{
    private JTextField displayText;
    private JButton startButton;
    private JButton stopButton;
    private JPanel btnPanel;
    private JPanel txtPanel;
    private Random r;
    private OddWindow oddWindow;
    private EvenWindow evenWindow;
    private ArmstrongWindow armstrongWindow;
    private PalindromeWindow palindromeWindow;
    NumberPattern(){
        setSize(300, 150);
        setTitle("Main");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        displayText=new JTextField(20);
        startButton=new JButton("START");
        stopButton=new JButton("STOP");
        btnPanel=new JPanel();
        txtPanel=new JPanel();
        r =new Random();

        txtPanel.add(displayText);
        btnPanel.add(startButton);
        btnPanel.add(stopButton);

        startButton.addActionListener((event)->{
            randomNumber("start");
        });
        stopButton.addActionListener((event)->{
            System.exit(0);
        });
        
        add(txtPanel, BorderLayout.CENTER);
        add(btnPanel, BorderLayout.PAGE_END);
    }
    public void randomNumber(String option){
        new Thread(){
            public void run(){
                try{
                    while(option.equals("start")){
                        int random=r.nextInt(100000);
                        Thread.sleep(1000);
                        displayText.setText(random+"");
                        oddWindow.update(random);
                        evenWindow.update(random);
                        armstrongWindow.update(random);
                        palindromeWindow.update(random);
                    }
                }catch(Exception e){}
            }
        }.start();
    }
    public void setOddWindow(OddWindow oddWindow){
        this.oddWindow=oddWindow;
    }
    public void setEvenWindow(EvenWindow evenWindow){
        this.evenWindow=evenWindow;
    }
    public void setArmstrongWindow(ArmstrongWindow armstrongWindow){
        this.armstrongWindow=armstrongWindow;
    }
    public void setPalindromeWindow(PalindromeWindow palindromeWindow){
        this.palindromeWindow=palindromeWindow;
    }
}
class Demo{
    public static void main(String args[]){
        NumberPattern numberPattern =new NumberPattern();
        numberPattern.setOddWindow(new OddWindow());
        numberPattern.setEvenWindow(new EvenWindow());
        numberPattern.setArmstrongWindow(new ArmstrongWindow());
        numberPattern.setPalindromeWindow(new PalindromeWindow());
        numberPattern.setVisible(true);
    }
}
