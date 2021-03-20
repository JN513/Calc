import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Calc extends JFrame {
    private JTextField input;
    private JLabel inputLabel;
    private GridLayout mainLayout;
    private JButton verifiPrimo;
    private JButton getFatorial;
    private JButton getDivisores;
    private JTextArea result;
    private JLabel resultLabel;
    private JButton clearButton;

    public Calc(String title) throws HeadlessException {
        super(title);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        initComponents();
        this.setPreferredSize(new Dimension(500, 100));
        this.pack();
    }

    private void initComponents(){

        mainLayout = new GridLayout(0, 2);

        clearButton = new JButton("Limpar");

        input = new JTextField();
        input.setText("0");

        inputLabel = new JLabel("Numero");

        verifiPrimo = new JButton("E primo?");

        getFatorial = new JButton("Fatorial");

        getDivisores = new JButton("Divisores");

        resultLabel = new JLabel("Resultado:");
        
        result = new JTextArea();
        result.setEditable(false);
        result.setWrapStyleWord(true);
        result.setText("0");

        verifiPrimo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e_primo((int)Integer.parseInt(input.getText()))){
                    result.setText("O numero e Primo");
                }else{
                    result.setText("O numero não e Primo");
                }
            }
        });

        getFatorial.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int numero = (int)Integer.parseInt(input.getText());

                result.setText(fatorial(1, numero));
            }
        });

        getDivisores.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int numero = (int)Integer.parseInt(input.getText());

                result.setText(divisores(numero));

            }
        });

        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                input.setText("");
            }
        });

        this.setLayout(mainLayout);
        this.add(inputLabel);
        this.add(input);
        this.add(verifiPrimo);
        this.add(getFatorial);
        this.add(getDivisores);
        this.add(clearButton);
        this.add(resultLabel);
        this.add(result);
    }

    static boolean e_primo(int x){
        if(x < 2) return false;
        if(x == 2) return true;
        if(x % 2 == 0) return false;

        for(int i = 3; i <= Math.pow(x, 0.5); i+=2)
            if(x % i == 0)
                return false;

        return true;
    }

    static String fatorial(int tamanho, int numero){
        int resp[] = new int[20003];
        resp[0]=1;
        int transporte, aux;

        for(int i = 1; i <= numero; i++){
            transporte = 0;

            for(int j = 0; j < tamanho; j++){
                aux=i*resp[j]+transporte;
                resp[j]=aux%10;
                transporte = aux/10;
            }



            while(transporte > 0){
                resp[tamanho] = transporte%10;
                transporte = transporte/10;
                tamanho++;
            }
        }

        String resposta = "";

        for(int i = tamanho-1; i >= 0; i--){
            resposta += resp[i];
        }

        return  resposta;
    }

    static String divisores(int numero){
        String resposta = "";

        for(int i = 1; i <= numero/2; i++){
            if(numero%i == 0){
                resposta += i;
                resposta += ", ";
            }
        }

        return resposta;
    }

    public static void main(String[] args) {
        Calc frame = new Calc("Calc");
        frame.setVisible(true);
    }

}
