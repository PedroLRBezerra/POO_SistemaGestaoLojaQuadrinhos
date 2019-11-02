package view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

public class FuncionaroBoundary implements BoundaryContent {
	
	private GridPane panGrid;
	
	private Button btnAdicionar = new Button("Adicionar");
	private Button btnPesquisar = new Button("Pesquisar");
	private Button btnExcluir = new Button("Excluir");
	
	private TextField txtNome = new TextField();
	private TextField txtCPF = new TextField();
	private TextField txtTelefone = new TextField();
	private TextField txtNasc = new TextField();
	private TextField txtSalario = new TextField();
	private TextField txtLogin = new TextField();
	private TextField txtSenha = new TextField();
	
	ObservableList<String> cargo = 
			FXCollections.observableArrayList("Atendente", "Assistente de Estoque ", "Gerente");
	ComboBox<String> cmbCargo = new ComboBox<>();
	
	public FuncionaroBoundary() { 
		
		panGrid = new GridPane();
		
		panGrid.add(new Label("Nome"), 0, 1);
		panGrid.add(txtNome, 1, 1,0,3);
		panGrid.add(new Label("CPF"), 0, 2);
		panGrid.add(txtCPF, 1, 2);
		panGrid.add(new Label("Telefone"), 0, 3);
		panGrid.add(txtTelefone, 1, 3);
		panGrid.add(new Label("Dt Nasc."), 2, 3);
		panGrid.add(txtNasc, 4, 3);
		panGrid.add(cmbCargo, 0, 4);
		panGrid.add(new Label("Salario(R$)"), 3, 4);
		panGrid.add(txtSalario, 4, 4);
		panGrid.add(new Label("Login"), 0, 5);
		panGrid.add(txtLogin, 1,5);
		panGrid.add(new Label("Senha"), 2, 5);
		panGrid.add(txtSenha, 3, 5);
		panGrid.add(btnAdicionar, 0,6);
		panGrid.add(btnPesquisar, 1, 7);
		panGrid.add(btnExcluir, 2, 8);
		
		
		panGrid.setHgap(10);
		panGrid.setVgap(10);
		
		cmbCargo.setEditable(false);
		cmbCargo.setItems(cargo);
	}
	public Pane generateForm() { 
		return panGrid;
	}
}

