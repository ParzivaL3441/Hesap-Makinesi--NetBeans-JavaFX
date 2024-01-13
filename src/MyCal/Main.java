// Bu, ana kod .......
package MyCal;
// JavaFX paketleri .......
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class Main extends Application {

    // GUI'lar için sahneler oluşturuluyor...
    private static Stage EngStage = null;
    private static Stage AboutStage = null;
    private static Stage HelpStage = null;

    // Ana GUI.......
    @Override
    public void start(Stage primaryStage) throws Exception {
        // UI'yi yüklemek için try kullanın ........
       try {
           // Ana GUI'yi yükleme ........
           Parent root = FXMLLoader.load(getClass().getResource("MainCal.fxml"));
           primaryStage.setTitle("Gedık  unıversitesi hesap makınesı");                      // Hesap makinesinin adı ....
           primaryStage.setScene(new Scene(root, 333, 559));                                 // Hesap makinesinin başlangıç ​​pozisyonu .....
           primaryStage.setResizable(false);                                                 // Pencere boyutunu değiştirilemez olarak ayarlama..... 
           Image icon = new Image(getClass().getResourceAsStream("icon/Calculator.png"));    // Simge konumu ........
           primaryStage.getIcons().add(icon);                                                // Uygulama için simgeyi ayarlama ... 
           
          
           primaryStage.setOpacity(0.9);                                                     // Pencereyi şeffaf yapma.......
           primaryStage.show();                                                              // Uygulamayı göster.......
           
       }catch (Exception e){                                                                 // İstisnaları yakalama ......
           e.printStackTrace();
       }
        HookesLawOluştur();
        StressCalOluştur();
        HelpOluştur();

    }

    // Hooke yasası için GUI...
    public void HookesLawOluştur(){
        EngStage = new Stage ();
        EngStage.setTitle("Hooke's Law Calculations");                                    // Başlık çubuğunun adı ...............
        EngStage.setAlwaysOnTop(true);                                                    // her zaman en üstte ....................
        EngStage.setResizable(false);                                                     // pencere boyutunu kalıcı olarak ayarlama..........
        EngStage.initModality(Modality.APPLICATION_MODAL);

        EngStage.setOpacity(0.8);                                                         // Pencere için opaklığı ayarlama .....
        Image icon = new Image(getClass().getResourceAsStream("icon/Calculator.png"));    // Simge konumu .........................
        EngStage.getIcons().add(icon);                                                    // Simgeyi ayarlama .......................  

    }

    // Stres Hesaplamaları için GUI....
    public void StressCalOluştur(){
        AboutStage = new Stage ();
        AboutStage.setTitle("Stress Calculator");
        AboutStage.setAlwaysOnTop(true);
        AboutStage.setResizable(false);                                                     // pencere boyutunu kalıcı olarak ayarlama..........
        AboutStage.initModality(Modality.APPLICATION_MODAL);

        AboutStage.setOpacity(0.8);                                                         // Pencere için opaklığı ayarlama .....
        Image icon = new Image(getClass().getResourceAsStream("icon/Calculator.png"));      // Simge konumu .........................
        AboutStage.getIcons().add(icon);                                                    // Simgeyi ayarlama .......................  
        
    }
    // Yardım için GUI....
    public void HelpOluştur(){
        HelpStage = new Stage ();
        HelpStage.setTitle("....Yardım....");
        HelpStage.setAlwaysOnTop(true);
        HelpStage.setResizable(false);
        HelpStage.initModality(Modality.APPLICATION_MODAL);

        HelpStage.setOpacity(0.8);
        Image icon = new Image(getClass().getResourceAsStream("icon/Malwarebytes.png"));
        HelpStage.getIcons().add(icon);

    }

    // Stages'lar private olduğundan dolayı bazı getter'lar oluşturuluyor....

    public static Stage getHookesLaw(){
        return EngStage;
    }

    public static Stage getStressCal(){
        return AboutStage;
    }

    public static Stage getHelp(){
        return HelpStage;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
