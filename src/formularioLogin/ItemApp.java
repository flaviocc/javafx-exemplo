package formularioLogin;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class ItemApp extends Application {

	private AnchorPane pane;
	private ImageView imgItem;
	private Label lbPreco, lbDescricao;
	private Button btAddCarrinho;
	private static Stage stage;
	private static Produto produto;
	private static int index;
	private static String[] images = { "http://www.sportcenterlopes.com.br/images/250_topper_campo_2009replic.jpg",
			"http://1.bp.blogspot.com/_H8uGs8K8kaY/TLZTXR8nIgI/AAAAAAAAF_0/BvpxdqGF4PE/s1600/luva_umbro.png",
			"http://bimg2.mlstatic.com/camisa-nike-active-importada-manga-longa-esportiva-vermelha_MLB-F-199843960_1391.jpg",
			"http://www.showtenis.com.br/images/_product/979/979112/chuteira-nike-mercurial-glide-3-fg-campo--199fd9.jpg",
			"http://www.katy.com.br/imagens/produtos/original/caneleira-topper-trainning-difusion-13340619502673137.jpg" };

	@Override
	public void start(Stage primaryStage) throws Exception {
		pane = new AnchorPane();
		pane.setPrefSize(600, 400);

		imgItem = new ImageView(new Image(images[index]));
		imgItem.setLayoutX(0);
		imgItem.setLayoutY(0);
		imgItem.setFitHeight(200);
		imgItem.setFitWidth(300);

		lbPreco = new Label("Preço R$" + produto.getPreco());
		lbPreco.setLayoutX(400);
		lbPreco.setLayoutY(200);
		
		lbDescricao = new Label("Descrição " + produto.getProduto());
		lbDescricao.setLayoutX(400);
		lbDescricao.setLayoutY(250);

		btAddCarrinho = new Button("Adicionar ao carrinho");
		btAddCarrinho.setLayoutX(400);
		btAddCarrinho.setLayoutY(300);

		pane.getChildren().addAll(imgItem, lbPreco, lbDescricao, btAddCarrinho);

		Scene scene = new Scene(pane);
		primaryStage.setScene(scene);
		// Remove a opção de maximizar a tela
		primaryStage.setResizable(false);
		// Dá um título para a tela
		primaryStage.setTitle(produto.getProduto());
		primaryStage.show();

		ItemApp.stage = primaryStage;

	}

	public static Stage getStage() {
		return stage;
	}

	public static void setStage(Stage stage) {
		ItemApp.stage = stage;
	}

	public static Produto getProduto() {
		return produto;
	}

	public static void setProduto(Produto produto) {
		ItemApp.produto = produto;
	}

	public static int getIndex() {
		return index;
	}

	public static void setIndex(int index) {
		ItemApp.index = index;
	}

}
