package view;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import control.EdicaoControl;
import control.ExemplarControl;
import entity.Edicao;
import entity.Exemplar;
import entity.Titulo;
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
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.util.Callback;

public class ExemplarBoundary implements BoundaryContent, EventHandler<ActionEvent> {
	
	private ExemplarControl controlEx = new ExemplarControl();
	
	private GridPane panGrid;
	private BorderPane painelPrincipal;
	
	private Button btnAdicionar = new Button("Adicionar");
	private Button btnPesquisar = new Button("Pesquisar");
	private Button btnExcluir = new Button("Excluir");
	
	private TextField txtNumExemplar = new TextField();
	private TextField txtValorCompra = new TextField();
	private TextField txtValorVenda = new TextField();
	
	ObservableList<Titulo>titulos = TituloBoundary.controlTi.getLista();
	ObservableList<Edicao> edicoes = FXCollections.observableArrayList();
	
	private ComboBox<Titulo> comboTitulo = new ComboBox<>();
	private ComboBox<Edicao> comboEdicao = new ComboBox<>();
	
	private TableView table = new TableView();

	
	public ExemplarBoundary() { 
		panGrid = new GridPane();
		painelPrincipal = new BorderPane();
		
		panGrid.add(new Label("Titulo"), 0, 0);
		panGrid.add(comboTitulo, 1,0 );
		
		panGrid.add(new Label("Edição"), 2, 0);
		panGrid.add(comboEdicao, 3,0 );
		
		panGrid.add(new Label("Num exemplar"), 0, 1);
		panGrid.add(txtNumExemplar, 1, 1);
		
		panGrid.add(new Label("Valor Compra"), 0, 2);
		panGrid.add(txtValorCompra, 1, 2);
		
		panGrid.add(new Label("Valor Venda"), 2, 2);
		panGrid.add(txtValorVenda, 3, 2);
		
		panGrid.add(btnAdicionar, 0, 3);
		panGrid.add(btnPesquisar, 1, 3);
		panGrid.add(btnExcluir, 2, 3);
		
		comboTitulo.setEditable(false);
		comboTitulo.setItems(titulos);
		
		comboTitulo.setOnAction((e)-> {
			System.out.println(comboTitulo.getValue().toString());
			List<Edicao> ed = new LinkedList<Edicao>();
			ed = this.controlEx.updateEdicaoList(comboTitulo.getValue().toString());
	     	edicoes.clear();
	     	for (Edicao edicao : ed) {
	     		edicoes.add(edicao);
	     	}
			comboEdicao.setItems(edicoes);
		});
		
		
		comboEdicao.setEditable(false);
		
		
		panGrid.setHgap(10);
		panGrid.setVgap(10);
		
		btnAdicionar.addEventHandler(ActionEvent.ANY, this);
		btnPesquisar.addEventHandler(ActionEvent.ANY, this);
		
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
		
		
		table.getColumns().addAll(columnTitulo, columnEdicao,columnExexplar,columnCompra,columnVenda);
		table.setItems(controlEx.getLista());
		table.getSelectionModel().selectedItemProperty().addListener(
				new ChangeListener<Exemplar>() {
					@Override
					public void changed(ObservableValue<? extends Exemplar> observable, 
							Exemplar oldValue,
							Exemplar newValue) {
						entidadeParaBoundary(newValue);
					}
				});
		
	}
	public Pane generateForm() { 
		return painelPrincipal;
	}
	@Override
	public void handle(ActionEvent event) {
			if (event.getTarget() == btnAdicionar) {
				controlEx.adicionar(boundaryParaEntidade());
			} else if (event.getTarget() == btnPesquisar) {
				//String exemplar = txtNumExemplar.getText();
				String titulo = comboTitulo.getValue().getTitulo();
				controlEx.pesquisarPorTipo(titulo);			
			//	entidadeParaBoundary(e);
			}
		}
	private void entidadeParaBoundary(Exemplar e) {
		if (e != null) {
			txtNumExemplar.setText(String.valueOf(e.getExemplar()));
			txtValorCompra.setText(String.valueOf(e.getValorCompra()));
			txtValorVenda.setText(String.valueOf(e.getValorVenda()));
		}
		
	}
	private Exemplar boundaryParaEntidade() {
		Exemplar e = new Exemplar();
		try {
			e.setEdicao(comboEdicao.getValue());
			e.setExemplar(txtNumExemplar.getText());
			e.setValorCompra(Double.parseDouble(txtValorCompra.getText()));
			e.setValorVenda(Double.parseDouble(txtValorVenda.getText()));
		} catch (Exception f) {
			f.printStackTrace();
		}
		return e;
	}
}
