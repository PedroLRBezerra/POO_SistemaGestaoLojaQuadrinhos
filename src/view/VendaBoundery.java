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
import javafx.beans.InvalidationListener;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.util.Callback;

public class VendaBoundery  implements BoundaryContent, EventHandler<ActionEvent>{
	private GridPane panGrid;
	private BorderPane painelPrincipal;
	
	private VendaControl controlV = new VendaControl();
	
	private Button btnAdd = new Button("Add");
	
	ObservableList<Titulo>titulos = TituloBoundary.controlTi.getLista();
	ObservableList<Edicao> edicoes  = FXCollections.observableArrayList();
	ObservableList<Exemplar> exemplaresCombo= FXCollections.observableArrayList();
	ObservableList<Cliente> clientes= FXCollections.observableArrayList();
	ObservableList<Exemplar> exemplaresVenda= FXCollections.observableArrayList();
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
		
		panGrid.add(btnAdd, 6, 0);
		
		
		panGrid.add(new Label("Cliente"), 0, 1);
		panGrid.add(comboCliente, 1, 1);
		
		btnAdd.addEventHandler(ActionEvent.ANY, this);
		
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
		comboEdicao.setOnAction((e)->{
			List<Exemplar> lex=new LinkedList<Exemplar>();
			lex=this.controlV.buscarExemplaresPorEdicao(comboEdicao.getValue().getId());
			exemplaresCombo.clear();
			for (Exemplar exemplar : lex) {
				exemplaresCombo.add(exemplar);
			}
			comboExemplar.setItems(exemplaresCombo);
		});
		
		comboExemplar.setEditable(false);
		
		buscarClientes();
		comboCliente.setItems(clientes);
		comboCliente.setEditable(false);

		panGrid.setHgap(10);
		panGrid.setVgap(10);
		
		painelPrincipal.setTop(panGrid);
		painelPrincipal.setBottom(table);
		
		addTableColumns();
	}

	private void addTableColumns() {
		TableColumn<Exemplar,Titulo> columnTitulo = new TableColumn<Exemplar,Titulo>("Titulo");
		columnTitulo.setCellValueFactory(new Callback<CellDataFeatures<Exemplar,Titulo>, ObservableValue<Titulo>>() {
		     public ObservableValue<Titulo> call(CellDataFeatures<Exemplar,Titulo> p) {
		         // p.getValue() returns the Person instance for a particular TableView row
		    	 ObservableValue<Titulo> o = new ObservableValue<Titulo>() {
					
					@Override
					public void removeListener(InvalidationListener listener) {
						// TODO Auto-generated method stub
						
					}
					
					@Override
					public void addListener(InvalidationListener listener) {
						// TODO Auto-generated method stub
						
					}
					
					@Override
					public void removeListener(ChangeListener<? super Titulo> listener) {
						// TODO Auto-generated method stub
						
					}
					
					@Override
					public Titulo getValue() {
						// TODO Auto-generated method stub
						return  p.getValue().getEdicao().getTitulo();
					}
					
					@Override
					public void addListener(ChangeListener<? super Titulo> listener) {
						// TODO Auto-generated method stub
						
					}
				};
		         return o;
		     }
		  });
		 

		
		TableColumn<Exemplar, Edicao> columnEdicao = new TableColumn<>("Edicao");
		columnEdicao.setCellValueFactory(
				new PropertyValueFactory<Exemplar,Edicao>("edicao"));
		
		TableColumn<Exemplar, Integer> columnExexplar = new TableColumn<>("Exemplar");
		columnExexplar.setCellValueFactory(
				new PropertyValueFactory<Exemplar,Integer>("exemplar"));
		TableColumn<Exemplar, Double> columnVenda = new TableColumn<>("Valor_Venda");
		columnVenda.setCellValueFactory(
				new PropertyValueFactory<Exemplar,Double>("valorVenda"));
		
		TableColumn<Exemplar, Double> columnCompra = new TableColumn<>("Valor_Compra");
		columnCompra.setCellValueFactory(
				new PropertyValueFactory<Exemplar,Double>("valorCompra"));
		
		
		table.getColumns().addAll(columnTitulo, columnEdicao,columnExexplar,columnVenda);
		table.setItems(exemplaresVenda);
//		table.getSelectionModel().selectedItemProperty().addListener(
//				new ChangeListener<Exemplar>() {
//					@Override
//					public void changed(ObservableValue<? extends Exemplar> observable, 
//							Exemplar oldValue,
//							Exemplar newValue) {
//						entidadeParaBoundary(newValue);
//					}
//				});
		
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
	public void handle(ActionEvent event) {
		if(event.getTarget()==btnAdd && comboExemplar.getValue()!=null) {
			venda.exemplarSelecionados.add(comboExemplar.getValue());
			exemplaresVenda.add(comboExemplar.getValue());
			exemplaresCombo.remove(comboExemplar.getValue());
		}
		
	}

	@Override
	public Pane generateForm() {
		return painelPrincipal;
	}

}
