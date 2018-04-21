package presentation;


import logic.FrameType;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import static logic.Controller.DEBUG;
import static logic.FrameType.Lavish;
import static logic.FrameType.Ornate;
import static logic.FrameType.Simple;

/**
 *
 * @author RODA
 */
public class GUI extends JFrame implements UI {
    private final String TITLE = "Glarmester";
    private final String MSG_HEIGHT    = "Enter height: (in cm.)";
    private final String MSG_WIDTH     = "Enter width: (in cm.)";
    private final String MSG_FRAMETYPE = "Select frametype:";
    private final String MSG_PRICE     = "Total price: kr. %.2f";
    private final String ERROR_MSG_NAN = "Invalid input - must be a positiv number!";

   public GUI(){
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        this.setTitle(TITLE);
       
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception ex) {
            if(DEBUG) ex.printStackTrace();
        }
   }
   
   @Override
    public double getFrameHeight() {
        return getInput(MSG_HEIGHT, "100") / 100.0;
    }

    @Override
    public double getFrameWidth() {
        return getInput(MSG_WIDTH, "160") / 100.0;
    }

    @Override
    public FrameType getFrameType() {
        String[] options = {Simple.toString(), Ornate.toString(), Lavish.toString()};
        int res = getSelection(MSG_FRAMETYPE, options, 0);
        switch(res){
            case 0: return Simple;
            case 1: return Ornate;
            case 2: return Lavish;
        }
        return null;
    }

    @Override
    public void displayPrice(double price) {
        showMessage(String.format(MSG_PRICE, price));
    }
    
    private double getInput(String msg, String defaultValue){
        try {
            JPanel panel = new JPanel();
            panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
            JLabel lbl = new JLabel(msg);
            JTextField tf = new JTextField();
            tf.setColumns(20);
            tf.setText(defaultValue);
            JButton btn = new JButton("OK");
            
            lbl.setAlignmentX(LEFT_ALIGNMENT);
            tf.setAlignmentX(LEFT_ALIGNMENT);
            btn.setAlignmentX(LEFT_ALIGNMENT);
            
            JPanel errorPanel = new JPanel();
            errorPanel.add(Box.createVerticalStrut(10));
            JLabel errorlbl = new JLabel(ERROR_MSG_NAN);
            errorlbl.setForeground(Color.red);
            errorPanel.add(errorlbl);
            errorPanel.setVisible(false);
            errorPanel.setAlignmentX(LEFT_ALIGNMENT);
            
            panel.add(Box.createVerticalStrut(10));
            panel.add(lbl);
            panel.add(Box.createVerticalStrut(10));
            panel.add(tf);
            panel.add(errorPanel);
            panel.add(Box.createVerticalStrut(10));
            panel.add(btn);
            panel.add(Box.createVerticalStrut(10));
            
            this.add(panel);
            this.pack();
            this.center();
            CountDownLatch latch = new CountDownLatch(1);
            btn.addActionListener((ActionEvent event) -> {
                try{
                    int t = Integer.parseInt(tf.getText());
                    if(t <= 0) throw new NumberFormatException("Negativ number!");
                    latch.countDown();
                } catch(NumberFormatException e){
                    errorPanel.setVisible(true);
                    GUI.this.pack();
                    GUI.this.center();
                }
            });
            this.setVisible(true);
            latch.await(1, TimeUnit.MINUTES);
            this.setVisible(false);
            this.remove(panel);
            return Integer.parseInt(tf.getText());
        } catch (InterruptedException ex) {
            if(DEBUG) ex.printStackTrace();
            return Double.NaN;
        }
    }
    
    private int getSelection(String msg, String[] options, int defaultValue){
        try {
            JPanel panel = new JPanel();
            panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
            JLabel lbl = new JLabel(msg);
            JComboBox cb = new JComboBox(options);
            cb.setSelectedIndex(defaultValue);
            JButton btn = new JButton("OK");
            
            lbl.setAlignmentX(LEFT_ALIGNMENT);
            cb.setAlignmentX(LEFT_ALIGNMENT);
            btn.setAlignmentX(LEFT_ALIGNMENT);
            
            panel.add(Box.createVerticalStrut(10));
            panel.add(lbl);
            panel.add(Box.createVerticalStrut(10));
            panel.add(cb);
            panel.add(Box.createVerticalStrut(10));
            panel.add(btn);
            panel.add(Box.createVerticalStrut(10));
            
            this.add(panel);
            this.pack();
            this.center();
            CountDownLatch latch = new CountDownLatch(1);
            btn.addActionListener((ActionEvent event) -> {
                latch.countDown();
            });
            this.setVisible(true);
            latch.await(1, TimeUnit.MINUTES);
            this.setVisible(false);
            this.remove(panel);
            return cb.getSelectedIndex();
        } catch (InterruptedException ex) {
            if(DEBUG) ex.printStackTrace();
            return -1;
        }
    }

    private void showMessage(String msg){
        try {
            JPanel panel = new JPanel();
            panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
            JLabel lbl = new JLabel(msg);
            JButton btn = new JButton("OK");
            
            lbl.setAlignmentX(LEFT_ALIGNMENT);
            btn.setAlignmentX(LEFT_ALIGNMENT);
            
            panel.add(Box.createVerticalStrut(10));
            panel.add(lbl);
            panel.add(Box.createVerticalStrut(10));
            panel.add(btn);
            panel.add(Box.createVerticalStrut(10));
            
            this.add(panel);
            this.pack();
            this.center();
            CountDownLatch latch = new CountDownLatch(1);
            btn.addActionListener((ActionEvent event) -> {
                latch.countDown();
            });
            this.setVisible(true);
            latch.await(1, TimeUnit.MINUTES);
            this.setVisible(false);
            this.remove(panel);
        } catch (InterruptedException ex) {
            if(DEBUG) ex.printStackTrace();
        }
    }
    
    private void center(){
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        int y = dim.width/2-this.getSize().width/2;
        int x = dim.height/2-this.getSize().height/2;
        this.setLocation(y, x);
   }
}
