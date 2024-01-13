// Bu, hesap makinesi için ana kontrolcüdür......

// Cal için ana paket.....
package MyCal;


// JavaFX paketlerini içe aktarıyoruz ..........
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

// Hesap için Math paketini içe aktarıyoruz .........
import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

// Diğer önemli kütüphaneleri içe aktarıyoruz......
import java.net.URL;
import java.util.ResourceBundle;


// Hesaplamalar için ana sınıf ............
public final class Controller implements Initializable {


    public void insertAnswer(String answer ){
        CAL.setText(CAL.getText()+answer);
    }

// Başlangıcı yapılıyor.....
    private BigDecimal left;             // BigDecimal sınıfı tanımlandı.......
    private String selectOperator;
    private boolean numberInputting;

// İki metin alanını başlatma.......
    @FXML
    private TextField CAL;   // Hesaplamaları gösteren metin alanı.....        
    @FXML 
    private TextField ANS;   // Cevabı görüntülemek için metin alanı........


    /** hooke's law sahnesini çağırma*/
    public void HookesLawController(){
        try{                             // UI'yi yükleme için try kullanın ........
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/MyCal/HookesLaw/HookesLaw.fxml"));  // UI dosyasının konumu
            Parent root = loader.load();
            Main.getHookesLaw().setScene(new Scene(root));
            Main.getHookesLaw().show();  // sahneyi uygulamaya yükleyin .....



        }catch (Exception ex){         // İstisnaları yakalama ......
            System.out.println(ex);    // istisnayı yazdır .....
        }

    }
    /** Stres Hesaplamaları sahnesini çağırma*/
    public void StressController() {
        try {                                    // UI'yi yükleme için try kullanın ........
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/MyCal/StressCal/StressCal.fxml"));
            Parent root = loader.load();
            Main.getStressCal().setScene(new Scene(root));
            Main.getStressCal().show();


        } catch (Exception ex) {         // İstisnaları yakalama ......
            System.out.println(ex);      // istisnayı yazdır .....
        }
    }

    /** yardım sahnesini çağırma*/
    public void HelpController(){
        try{                                    // Try bloğu..
            
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/MyCal/Help/Help.fxml"));
            Parent root = loader.load();
            Main.getHelp().setScene(new Scene(root));
            Main.getHelp().show();



        }catch (Exception ex){           // İstisnaları yakalama ......
            System.out.println(ex);      // istisnayı yazdır .....
        }
    }

// Yapıcı metot...

    public Controller() {
        this.left = BigDecimal.ZERO ;
        this.selectOperator = "";
        this.numberInputting = false;
    }

    // Düğmelere eylemler atama..
    @FXML
    public void operateButton(ActionEvent evt){
        Button button = (Button)evt.getSource();
        String buttonText = button.getText();

        // temizle düğmesi
        if(buttonText.equals("TEMİZLE")){
            if (buttonText.equals("TEMİZLE")){
                left = BigDecimal.ZERO;
            }
            selectOperator = "";
            numberInputting = true;
            CAL.setText("");  // Giriş metni temizle .....
            ANS.setText("");  // Cevap metnini temizle .....
            return;
        }


        // sayısal düğmeler ve nokta...
        if (buttonText.matches("[0-9\\.]")){
            if (!numberInputting){
                numberInputting =true;
                CAL.clear();

            }
              CAL.appendText(buttonText);
              ANS.appendText(buttonText);    // Göstermek için metni ayarla .......
            return;
        }

        // Temel matematik işlemleri düğmeleri..
        if (buttonText.matches("[/x+-]")){
            left=new BigDecimal(CAL.getText());
            selectOperator = buttonText;
            numberInputting =false;
            CAL.appendText(buttonText);
            ANS.appendText(buttonText);    // Göstermek için metni ayarla .......
            return;

        }
        // Karekök düğmesi..
        if (buttonText.equals("Sqr")){
            left=new BigDecimal(CAL.getText());
            MathContext mc = new MathContext(6);
            BigDecimal kareKok= left.sqrt(mc);
            ANS.appendText(buttonText);
            CAL.setText(kareKok.toString());    // Göstermek için metni ayarla .......


        }
        // 2. kuvvet düğmesi..
        if (buttonText.equals("^2")){
            String a=CAL.getText();
            double kare =Math.pow(Double.parseDouble(a),2);
            CAL.setText(String.valueOf(kare));
            ANS.appendText(buttonText);
        }

        // 3. kuvvet düğmesi..
        if (buttonText.equals("^3")){
            String a=CAL.getText();
            double küp =Math.pow(Double.parseDouble(a),3);
            CAL.setText(String.valueOf(küp));    // Göstermek için metni ayarla .......
            ANS.appendText(buttonText);
        }

        // ANS düğmesi....
        if (buttonText.equals("ANS")) {
            String a=CAL.getText();
            insertAnswer(a.substring(1));
            ANS.appendText(buttonText);
            CAL.clear();                            // Cal'i temizle........
         return;
        }
        // 1/x düğmesi..
            if (buttonText.equals("1/x")){
            left=new BigDecimal(CAL.getText());
            BigDecimal bolu= BigDecimal.valueOf(1).divide(left,5,RoundingMode.HALF_UP);

            CAL.setText(bolu.toString());    // Göstermek için metni ayarla .......
        }

        // eşittir düğmesi...
        if (buttonText.equals("=")){
            final BigDecimal right = numberInputting ? new BigDecimal(CAL.getText()):left;

            left = calculate(selectOperator,left,right);
            CAL.setText(left.toString());    // Göstermek için metni ayarla .......
            ANS.appendText(button
