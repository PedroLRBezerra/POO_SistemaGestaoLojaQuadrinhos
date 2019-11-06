package POO.ProjetoLoja_Quadrinho;

import java.text.SimpleDateFormat;
import java.util.Date;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class ClienteBoundery extends Application implements EventHandler<ActionEvent> {
	private ClienteControl control = new ClienteControl();
	private TextField txtId = new TextField();
	private TextField txtNome = new TextField();
	private TextField txtCPF = new TextField();
	private TextField txttelefone = new TextField();
	private TextField txtemail = new TextField();
	private TextField txtdataNascimento = new TextField();
	private Button btnAdicionar = new Button("Adicionar");
	private Button btnPesquisar = new Button("Pesquisar");
	private TableView table = new TableView();
	private SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

	@Override
	public void start(Stage primaryStage) throws Exception {
		BorderPane painelPrincipal = new BorderPane();
		painelPrincipal.setStyle("-fx-padding: 10px");
		FlowPane painelBotoes = new FlowPane();
		GridPane painelCampos = new GridPane();
		ColumnConstraints col0 = new ColumnConstraints();
		col0.setPercentWidth(30);
		ColumnConstraints col1 = new ColumnConstraints();
		col1.setPercentWidth(70);
		painelCampos.getColumnConstraints().addAll(col0, col1);

		painelPrincipal.setTop(painelCampos);
		painelPrincipal.setCenter(table);
		painelPrincipal.setBottom(painelBotoes);

		painelCampos.add(new Label("Id"), 0, 0);
		painelCampos.add(txtId, 1, 0);
		painelCampos.add(new Label("Nome"), 0, 1);
		painelCampos.add(txtNome, 1, 1);
		painelCampos.add(new Label("CPF"), 0, 2);
		painelCampos.add(txtCPF, 1, 2);
		painelCampos.add(new Label("Telefone"), 0, 3);
		painelCampos.add(txttelefone, 1, 3);
		painelCampos.add(new Label("Email"), 0, 4);
		painelCampos.add(txtemail, 1, 4);
		painelCampos.add(new Label("Data Nascimento"), 0, 5);
		painelCampos.add(txtdataNascimento, 1, 5);

		painelBotoes.getChildren().addAll(btnAdicionar, btnPesquisar);

		addTableColumns();

		btnAdicionar.addEventHandler(ActionEvent.ANY, this);
		btnPesquisar.addEventHandler(ActionEvent.ANY, this);
		painelBotoes.setHgap(15);
		Scene scn = new Scene(painelPrincipal, 400, 200);

		primaryStage.setScene(scn);
		primaryStage.setTitle("Gestão de Clientes");
		primaryStage.show();
	}

	public void addTableColumns() { 
		TableColumn<Cliente, Long> columnId = new TableColumn<>("Id");
		columnId.setCellValueFactory(
				new PropertyValueFactory<Cliente, Long>("id"));

		TableColumn<Cliente, String> columnNome = new TableColumn<>("Nome");
		columnNome.setCellValueFactory(
				new PropertyValueFactory<Cliente, String>("nome"));

		TableColumn<Cliente, String> columnCPF = new TableColumn<>("CPF");
		columnCPF.setCellValueFactory(
				new PropertyValueFactory<Cliente, String>("CPF"));
		
		TableColumn<Cliente, String> columntelefone = new TableColumn<>("telefone");
		columntelefone.setCellValueFactory(
				new PropertyValueFactory<Cliente, String>("telefone"));
		
		TableColumn<Cliente, String> columnemail = new TableColumn<>("Email");
		columnemail.setCellValueFactory(
				new PropertyValueFactory<Cliente, String>("Email"));
		
		TableColumn<Cliente, String> columnnasc = new TableColumn<>("Data Nascimento");
		columnnasc.setCellValueFactory(
				new PropertyValueFactory<Cliente, String>("Data Nascimento"));
		
		table.getColumns().addAll(columnId, columnNome, columnCPF,columntelefone,columnemail,columnnasc);
		table.setItems(control.getLista());
	}

	public Cliente boundaryParaEntidade() { 
		Cliente c = new Cliente();
		try {
			c.setId(Long.parseLong(txtId.getText()));
			c.setNome(txtNome.getText());
			c.setCPF(txtCPF.getText());
			c.setTelefone(txttelefone.getText());
			c.setemail(txtemail.getText());
			Date d = sdf.parse(txtdataNascimento.getText());
			c.setdataNascimento(d);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return c;
	}

	public void entidadeParaBoundary(Cliente c) { 
		if (c != null) { 
			txtNome.setText(c.getNome());
			txtCPF.setText(c.getCPF());
			txttelefone.setText(c.getTelefone());
			txtId.setText(String.valueOf(c.getId()));
			txtemail.setText(c.getemail());
			String strData = sdf.format(c.getdataNascimento());
			txtdataNascimento.setText(strData);
		}
	}
	@Override
	public void handle(ActionEvent event) {
		if (event.getTarget() == btnAdicionar) { 
			control.adicionar(boundaryParaEntidade());
		} else if (event.getTarget() == btnPesquisar) {
			String nome = txtNome.getText();
			Cliente c = control.pesquisarPorNomes(nome);			
			entidadeParaBoundary(c);
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}
