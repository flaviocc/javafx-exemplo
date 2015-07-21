package formularioLogin;

import javax.swing.JOptionPane;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class LoginApp extends Application {

	private AnchorPane pane;
	private TextField txLogin;
	private PasswordField txSenha;
	private Button btEntrar, btSair;
	private static Stage stage;

	@Override
	public void start(Stage stage) throws Exception {
		initComponents();
		initListeners();
		Scene scene = new Scene(pane);
		scene.getStylesheets().add(getClass().getResource("login.css").toExternalForm());
		stage.setScene(scene);
		// Remove a opção de maximizar a tela
		stage.setResizable(false);
		// Dá um título para a tela
		stage.setTitle("Login - GolFX");
		stage.show();
		initLayout();
		LoginApp.stage = stage;

	}

	private void initComponents() {
		pane = new AnchorPane();
		pane.setPrefSize(400, 300);
		// Css
		pane.getStyleClass().add("pane");

		txLogin = new TextField();
		txLogin.setPromptText("Digite aqui seu login");

		txSenha = new PasswordField();
		txSenha.setPromptText("Digite aqui a senha");

		btEntrar = new Button("Entrar");

		btSair = new Button("Sair");
		pane.getChildren().addAll(txLogin, txSenha, btEntrar, btSair);

	}

	private void initLayout() {
		// alinhando os componentes
		txLogin.setLayoutX(calcularLargura(pane.getWidth(), txLogin.getWidth()));
		txLogin.setLayoutY(50);
		txSenha.setLayoutX(calcularLargura(pane.getWidth(), txSenha.getWidth()));
		txSenha.setLayoutY(100);
		btEntrar.setLayoutX(calcularLargura(pane.getWidth(), btEntrar.getWidth()));
		btEntrar.setLayoutY(150);
		btSair.setLayoutX(calcularLargura(pane.getWidth(), btSair.getWidth()));
		btSair.setLayoutY(200);
		
	}

	private void initListeners() {
		btSair.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				fecharAplicacao();
			}
		});

		btEntrar.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				logar();

			}
		});
	}

	private void fecharAplicacao() {
		System.exit(0);
	}

	private void logar() {
		if (txLogin.getText().equals("admin") && txSenha.getText().equals("casadocodigo")) {
			try {
				new VitrineApp().start(new Stage());
				LoginApp.getStage().close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			JOptionPane.showMessageDialog(null, "Login e/ou senha inválidos", "Erro", JOptionPane.ERROR_MESSAGE);
		}
	}

	public double calcularLargura(double paneLargura, double componenteLargura) {
		return (paneLargura - componenteLargura) / 2;

	}

	public static void main(String[] args) {
		launch(args);
	}

	public static Stage getStage() {
		return stage;
	}

}
