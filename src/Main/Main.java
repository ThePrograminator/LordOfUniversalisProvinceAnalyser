package Main;

import SearchObjects.Calculator;
import SearchObjects.LoadingBarObserver;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

import java.io.File;

public class Main extends Application
{
    private double progressValue = 0.0;
    private Label finderLoadingLabel = new Label();
    private Label directoryInputLabel = new Label("*No Directory Selected*");
    private Label directoryOutputLabel = new Label("*No Directory Selected*");
    private Label directoryInformationLabel = new Label("*No Directory Selected*");
    private ProgressBar pb = new ProgressBar(progressValue);
    private ProgressIndicator pi = new ProgressIndicator(progressValue);
    private TextArea logTextArea;
    private BorderPane root;
    private Scene scene;
    private VBox analyzeCenter;

    private Calculator calculator = new Calculator(this);

    @Override
    public void start(Stage primaryStage)
    {
        root = new BorderPane();

        primaryStage.setMinWidth(750);
        primaryStage.setMinHeight(750);
        primaryStage.setMaxWidth(750);
        primaryStage.setMaxHeight(750);

        if (true)
        {
            directoryInformationLabel.setText("C:\\Users\\Christian\\Desktop\\LordOfUniversalisProvince\\InformationMap");
            calculator.getFileHandler().setInformationDirectoryPath("C:\\Users\\Christian\\Desktop\\LordOfUniversalisProvince\\InformationMap");
        }

        createBottomLayout(primaryStage);
        createTopLayout(primaryStage);
        createCenterLayout(primaryStage);

        scene = new Scene(root, 750, 750);

        primaryStage.setTitle("Lord of Universalis - Create Province Information");
        primaryStage.setScene(scene);
        primaryStage.setOnCloseRequest(e -> Platform.exit());
        primaryStage.show();
    }

    public void createTopLayout(Stage primaryStage)
    {
        MenuBar menuBar = new MenuBar();
        menuBar.prefWidthProperty().bind(primaryStage.widthProperty());

        // --- Menu File
        Menu menuFile = new Menu("File");
        MenuItem province = new MenuItem("Province");
        province.setOnAction(event ->
        {
            System.out.println("hello world");
            Label label = new Label("Hello World");
            VBox vBox = new VBox();
            vBox.getChildren().addAll(label);
            root.setCenter(vBox);
        });
        menuFile.getItems().add(province);

        // --- Menu Edit
        Menu menuEdit = new Menu("Analyze");
        MenuItem analyze = new MenuItem("Analyze");
        analyze.setOnAction(event ->
        {
            System.out.println("hello world");
            root.setCenter(analyzeCenter);
        });
        menuEdit.getItems().add(analyze);

        // --- Menu View
        Menu menuView = new Menu("Configuration");

        menuBar.getMenus().addAll(menuFile, menuEdit, menuView);

        root.setTop(menuBar);
    }

    public void createCenterLayout(Stage primaryStage)
    {
        analyzeCenter = new VBox();

        Button startButton = new Button("Start");

        startButton.setOnAction(event ->
        {
            if (calculator.getFileHandler().getInformationDirectoryPath() == null)
            {
                this.calculator.getLogHandler().updateLogTextArea("|Configuration| Message => No Directory Path has been chosen");
                return;
            }
            this.calculator.getLogHandler().updateLogTextArea("|Configuration| Message => Valid Directory chosen");

            new LoadingBarObserver(calculator);

            Thread thread = new Thread(calculator);
            thread.start();
        });

        HBox loadingBarHbox = new HBox();

        Label loadingText = new Label("Loading: ");

        loadingBarHbox.getChildren().addAll(loadingText, pb, pi);

        //Input
        DirectoryChooser directoryInputChooser = new DirectoryChooser();
        configuringDirectoryChooser(directoryInputChooser);

        Button chooseDirectoryInputButton = new Button("Choose Input Directory");
        chooseDirectoryInputButton.setOnAction(event ->
        {
            File selectedDirectory = directoryInputChooser.showDialog(primaryStage);

            if(selectedDirectory == null)
            {
                this.calculator.getLogHandler().updateLogTextArea("|Configuration| Message => No Directory Path has been chosen");
                return;
            }

            boolean allFilesFound = calculator.getFileHandler().searchForAppropriatedFiles(selectedDirectory.getAbsolutePath());

            if(!allFilesFound)
            {
                directoryInputLabel.setText("Missing Files Needed - View Log");
                this.calculator.getLogHandler().updateLogTextArea("|Configuration| Message => Missing Files Needed: You need to make sure to include all files needed and with the correct spelling");
                return;
            }

            directoryInputLabel.setText(selectedDirectory.getAbsolutePath());
            calculator.getFileHandler().setInputDirectoryPath(selectedDirectory.getAbsolutePath());
        });

        HBox chooseDirectoryInputHbox = new HBox();

        chooseDirectoryInputHbox.getChildren().addAll(chooseDirectoryInputButton, directoryInputLabel);

        //Ouput
        DirectoryChooser directoryOutputChooser = new DirectoryChooser();
        configuringDirectoryChooser(directoryOutputChooser);

        Button chooseDirectoryOutputButton = new Button("Choose Output Directory");
        chooseDirectoryOutputButton.setOnAction(event ->
        {
            File selectedDirectory = directoryOutputChooser.showDialog(primaryStage);

            if(selectedDirectory == null)
            {
                this.calculator.getLogHandler().updateLogTextArea("|Configuration| Message => No Directory Path has been chosen");
                return;
            }

            boolean allFilesFound = calculator.getFileHandler().searchForAppropriatedFiles(selectedDirectory.getAbsolutePath());

            if(!allFilesFound)
            {
                directoryOutputLabel.setText("Missing Files Needed - View Log");
                this.calculator.getLogHandler().updateLogTextArea("|Configuration| Message => Missing Files Needed: You need to make sure to include all files needed and with the correct spelling");
                return;
            }

            directoryOutputLabel.setText(selectedDirectory.getAbsolutePath());
            calculator.getFileHandler().setOutputDirectoryPath(selectedDirectory.getAbsolutePath());
        });

        HBox chooseDirectoryOutputHbox = new HBox();

        chooseDirectoryOutputHbox.getChildren().addAll(chooseDirectoryOutputButton, directoryOutputLabel);

        //Information
        DirectoryChooser directoryInformationChooser = new DirectoryChooser();
        configuringDirectoryChooser(directoryInformationChooser);

        Button chooseDirectoryInformationButton = new Button("Choose Information Directory");
        chooseDirectoryInformationButton.setOnAction(event ->
        {
            File selectedDirectory = directoryInformationChooser.showDialog(primaryStage);

            if(selectedDirectory == null)
            {
                this.calculator.getLogHandler().updateLogTextArea("|Configuration| Message => No Directory Path has been chosen");
                return;
            }

            boolean allFilesFound = calculator.getFileHandler().searchForAppropriatedFiles(selectedDirectory.getAbsolutePath());

            if(!allFilesFound)
            {
                directoryInformationLabel.setText("Missing Files Needed - View Log");
                this.calculator.getLogHandler().updateLogTextArea("|Configuration| Message => Missing Files Needed: You need to make sure to include all files needed and with the correct spelling");
                return;
            }

            directoryInformationLabel.setText(selectedDirectory.getAbsolutePath());
            calculator.getFileHandler().setInformationDirectoryPath(selectedDirectory.getAbsolutePath());
        });

        HBox chooseDirectoryInformationHbox = new HBox();

        chooseDirectoryInformationHbox.getChildren().addAll(chooseDirectoryInformationButton, directoryInformationLabel);

        analyzeCenter.getChildren().addAll(startButton,chooseDirectoryInputHbox, chooseDirectoryOutputHbox, chooseDirectoryInformationHbox, finderLoadingLabel, loadingBarHbox);
        analyzeCenter.setSpacing(25);
        root.setCenter(analyzeCenter);
    }

    public void createBottomLayout(Stage primaryStage)
    {
        logTextArea = new TextArea();
        logTextArea.setEditable(false);
        logTextArea.setMaxWidth(primaryStage.getMinWidth());
        logTextArea.setMaxHeight(1000);

        root.setBottom(logTextArea);
    }


    public static void main(String[] args)
    {
        launch(args);
    }

    private void configuringDirectoryChooser(DirectoryChooser directoryChooser) {
        // Set title for DirectoryChooser
        directoryChooser.setTitle("Select Some Directories");

        // Set Initial Directory
        directoryChooser.setInitialDirectory(new File(System.getProperty("user.home")));
    }

    public double getProgressValue() {
        return progressValue;
    }

    public void setProgressValue(double progressValue) {
        this.progressValue = progressValue;
    }

    public ProgressBar getPb() {
        return pb;
    }

    public void setPb(ProgressBar pb) {
        this.pb = pb;
    }

    public ProgressIndicator getPi() {
        return pi;
    }

    public void setPi(ProgressIndicator pi) {
        this.pi = pi;
    }

    public Label getFinderLoadingLabel() {
        return finderLoadingLabel;
    }

    public void setFinderLoadingLabel(Label finderLoadingLabel) {
        this.finderLoadingLabel = finderLoadingLabel;
    }

    public TextArea getLogTextArea() {
        return logTextArea;
    }
}