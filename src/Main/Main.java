package Main;

import Main.View.LogHandler;
import Main.View.LogType;
import Main.View.Observer;
import Model.Map.MapInformationService;
import SearchObjects.FinderHandler;
import Main.View.LoadingBarObserver;
import SearchObjects.ProvincePrinter;
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
    private boolean filesLoaded;

    private FileHandler fileHandler = new FileHandler(this);
    private FinderHandler finderHandler = new FinderHandler(this);
    private ProvincePrinter provincePrinter = new ProvincePrinter(this);
    private LogHandler logHandler = new LogHandler(this);


    @Override
    public void start(Stage primaryStage)
    {
        root = new BorderPane();

        primaryStage.setMinWidth(750);
        primaryStage.setMinHeight(750);
        primaryStage.setMaxWidth(750);
        primaryStage.setMaxHeight(750);

        createBottomLayout(primaryStage);
        createTopLayout(primaryStage);
        createCenterLayout(primaryStage);

        scene = new Scene(root, 750, 750);

        primaryStage.setTitle("Lord of Universalis - Create ProvinceInformation Information");
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
        MenuItem province = new MenuItem("ProvinceInformation");
        province.setOnAction(event ->
        {
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
            if(filesLoaded)
                return;

            if (fileHandler.getInputDirectoryPath() == null)
            {
                this.logHandler.updateLogTextArea(LogType.ERROR,"No Input Directory Path has been chosen");
                return;
            }
            else if (fileHandler.getOutputDirectoryPath() == null)
            {
                this.logHandler.updateLogTextArea(LogType.ERROR,"No Output Directory Path has been chosen");
                return;
            }
            else if (fileHandler.getInformationDirectoryPath() == null)
            {
                this.logHandler.updateLogTextArea(LogType.ERROR,"No Information Directory Path has been chosen");
                return;
            }

            this.logHandler.updateLogTextArea(LogType.CONFIGURATION,"Message => All valid Directories");

            this.finderHandler.attach(new LoadingBarObserver(this.finderHandler));

            Thread thread = new Thread(finderHandler);
            thread.start();
        });

        Button printButton = new Button("Print");

        printButton.setOnAction(event ->
        {
            if(!filesLoaded)
                return;

            ProvincePrinter provincePrinter = new ProvincePrinter(this);
            provincePrinter.printProvincesToFiles(MapInformationService.getInstance().getProvinceList());
        });

        HBox buttonHbox = new HBox();
        buttonHbox.getChildren().addAll(startButton, printButton);

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
                this.logHandler.updateLogTextArea(LogType.ERROR,"No Input Directory Path has been chosen");
                return;
            }

            File[] listOfFiles = selectedDirectory.listFiles();

            if(listOfFiles == null || listOfFiles.length <= 0)
            {
                this.logHandler.updateLogTextArea(LogType.ERROR,"No Files Detected in Input Directory Path");
                return;
            }

            directoryInputLabel.setText(selectedDirectory.getAbsolutePath());
            fileHandler.setInputDirectoryPath(selectedDirectory.getAbsolutePath());
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
                this.logHandler.updateLogTextArea(LogType.ERROR,"No Output Directory Path has been chosen");
                return;
            }

            directoryOutputLabel.setText(selectedDirectory.getAbsolutePath());
            fileHandler.setOutputDirectoryPath(selectedDirectory.getAbsolutePath());
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
                this.logHandler.updateLogTextArea(LogType.ERROR,"No Information Directory Path has been chosen");
                return;
            }

            boolean allFilesFound = fileHandler.searchForAppropriatedFiles(selectedDirectory.getAbsolutePath());

            if(!allFilesFound)
            {
                directoryInformationLabel.setText("Missing Files Needed - View Log");
                this.logHandler.updateLogTextArea(LogType.ERROR,"Missing Files Needed: You need to make sure to include all files needed and with the correct spelling");
                return;
            }

            directoryInformationLabel.setText(selectedDirectory.getAbsolutePath());
            fileHandler.setInformationDirectoryPath(selectedDirectory.getAbsolutePath());
        });

        HBox chooseDirectoryInformationHbox = new HBox();

        chooseDirectoryInformationHbox.getChildren().addAll(chooseDirectoryInformationButton, directoryInformationLabel);

        analyzeCenter.getChildren().addAll(buttonHbox,chooseDirectoryInputHbox, chooseDirectoryOutputHbox, chooseDirectoryInformationHbox, finderLoadingLabel, loadingBarHbox);
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

    public boolean isFilesLoaded() {
        return filesLoaded;
    }

    public void setFilesLoaded(boolean filesLoaded) {
        this.filesLoaded = filesLoaded;
    }

    public FinderHandler getFinderHandler() {
        return finderHandler;
    }

    public ProvincePrinter getProvincePrinter() {
        return provincePrinter;
    }

    public LogHandler getLogHandler() {
        return logHandler;
    }

    public FileHandler getFileHandler() {
        return fileHandler;
    }
}