package formularioLogin;

import javafx.application.Application;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class VitrineApp extends Application {

	private AnchorPane pane;
	private TextField txPesquisa;
	private TableView<ItensProperty> tbVitrine;
	private TableColumn<ItensProperty, String> columnProduto;
	private TableColumn<ItensProperty, Double> columnPreco;
	private static ObservableList<ItensProperty> listItens = FXCollections.observableArrayList();
	private static Carrinho carrinho;

	public class ItensProperty {
		private SimpleStringProperty produto;
		private SimpleDoubleProperty preco;

		public ItensProperty(String produto, double preco) {
			this.produto = new SimpleStringProperty(produto);
			this.preco = new SimpleDoubleProperty(preco);
		}

		public String getProduto() {
			return produto.get();
		}

		public void setProduto(String produto) {
			this.produto.set(produto);
		}

		public double getPreco() {
			return preco.get();
		}

		public void setPreco(double preco) {
			this.preco.set(preco);
		}

	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		initComponents();
		initListeners();
		Scene scene = new Scene(pane);
		primaryStage.setScene(scene);
		// Remove a opção de maximizar a tela
		primaryStage.setResizable(false);
		// Dá um título para a tela
		primaryStage.setTitle("Vitrine - GolFX");
		primaryStage.show();
		// initLayout();

	}

	private void initComponents() {
		pane = new AnchorPane();
		pane.setPrefSize(800, 600);

		txPesquisa = new TextField();
		txPesquisa.setPromptText("Digite o item para pesquisa");
	
		tbVitrine = new TableView<>();
		tbVitrine.setPrefSize(780, 550);
		tbVitrine.setLayoutY(100);

		columnProduto = new TableColumn<>("Produto");
		columnPreco = new TableColumn<>("Preço");
		tbVitrine.getColumns().addAll(columnProduto, columnPreco);
		columnProduto.setCellValueFactory(new PropertyValueFactory<>("produto"));
		columnPreco.setCellValueFactory(new PropertyValueFactory<>("preco"));

		initItens();
		pane.getChildren().addAll(txPesquisa, tbVitrine);

		carrinho = new Carrinho();

	}

	private void initItens() {
		Vitrine v = new Vitrine();
		v.addProdutos(new Produto("Bola Topper", 15.00), new Produto("Luvas Umbro", 9.00),
				new Produto("Camisa Esportiva", 40.00), new Produto("Chuteira Nike Mercurial", 199.00),
				new Produto("Caneleira Topper", 10.00));
		for (Produto p : Vitrine.getProdutos()) {
			listItens.add(new ItensProperty(p.getProduto(), p.getPreco()));
		}

		tbVitrine.setItems(listItens);
	}

	private void initListeners() {
		txPesquisa.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				if (!txPesquisa.getText().equals("")) {
					tbVitrine.setItems(findItems());
				} else {
					tbVitrine.setItems(listItens);
				}

			}
		});
		
		tbVitrine.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<ItensProperty>() {

			@Override
			public void changed(ObservableValue<? extends ItensProperty> value, ItensProperty oldValue,
					ItensProperty newItem) {
				/*
				 *  Indicando os valores de produto e index para ItemApp
				 */
				ItemApp.setProduto(new Produto(newItem.getProduto(), newItem.getPreco()));
				ItemApp.setIndex(tbVitrine.getSelectionModel().getSelectedIndex());
				
				// Chamando o formulário de exibição de item
				try {
					new ItemApp().start(new Stage());
				} catch (Exception e) {
					e.printStackTrace();
				}
				
			}
		});
	}

	private ObservableList<ItensProperty> findItems() {
		ObservableList<ItensProperty> itensEncontrados = FXCollections.observableArrayList();

		for (ItensProperty itens : listItens) {
			if (itens.getProduto().contains(txPesquisa.getText())) {
				itensEncontrados.add(itens);
			}
		}
		return itensEncontrados;
	}

	public static void main(String[] args) {

	}

}
