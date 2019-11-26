package view;

import java.util.LinkedList;
import java.util.List;

import control.EdicaoControl;
import control.ExemplarControl;
import control.VendaControl;
import entity.Cliente;
import entity.Edicao;
import entity.Exemplar;
import entity.Titulo;
import entity.Venda;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

public class VendaBoundery  implements BoundaryContent, EventHandler<ActionEvent>{
	private GridPane panGrid;
	private BorderPane painelPrincipal;
	
	private VendaControl controlV = new VendaControl();
	
	private Button btnAdd = new Button("Add");
	
	ObservableList<Titulo>titulos = TituloBoundary.controlTi.getLista();
	ObservableList<Edicao> edicoes  = FXCollections.observableArrayList();
	ObservableList<Exemplar> exemplaresCombo= FXCollections.observableArrayList();
	ObservableList<Cliente> clientes= FXCollections.observableArrayList();
	
	private ComboBox<Titulo> comboTitulo = new ComboBox<>();
	private ComboBox<Edicao> comboEdicao = new ComboBox<>();
	private ComboBox<Exemplar>comboExemplar =  new ComboBox<>();
	private ComboBox<Cliente> comboCliente = new ComboBox<Cliente>();
	
	private Venda venda = new Venda();
	
	private TableView table = new TableView();
	
	public VendaBoundery() {
		panGrid = new GridPane();
		painelPrincipal = new BorderPane();
		
		painelPrincipal.setStyle("-fx-padding: 10px");
		
		
		panGrid.add(new Label("Titulo"), 0, 0);
		panGrid.add(comboTitulo, 1,0 );
		
		panGrid.add(new Label("Edição"), 2, 0);
		panGrid.add(comboEdicao, 3,0 );
		
		panGrid.add(new Label("Exemplar"), 4, 0);
		panGrid.add(comboExemplar,5,0);
		
		
		
		panGrid.add(new Label("Cliente"), 0, 1);
		panGrid.add(comboCliente, 1, 1);
		
		
		comboTitulo.setEditable(false);
		comboTitulo.setItems(titulos);
		
		comboTitulo.setOnAction((e)-> {
			System.out.println(comboTitulo.getValue().toString());
			List<Edicao> ed = new LinkedList<Edicao>();
			ed = this.controlV.buscarEdicoesPorTitulo(comboTitulo.getValue().toString());
			edicoes.clear();
			for (Edicao edicao : ed) {
				edicoes.add(edicao);
			}
			comboEdicao.setItems(edicoes);
		});
		
		
		comboEdicao.setEditable(false);
		
		
//		comboEdicao.setOnAction((e)->{
//			this.controlEx.
//		});
//		
		buscarClientes();
		comboCliente.setItems(clientes);
		comboCliente.setEditable(false);

		panGrid.setHgap(10);
		panGrid.setVgap(10);
		
		painelPrincipal.setTop(panGrid);
	}

	private void buscarClientes() {
		ObservableList<Cliente> cli = FXCollections.observableArrayList();
		List<Cliente>l = new LinkedList<Cliente>();
		l=controlV.buscarClientes();
		for (Cliente cliente : l) {
			cli.add(cliente);
		}
		clientes=cli;
	}

	@Override
	public void handle(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Pane generateForm() {
		return painelPrincipal;
	}

}
