package view;


import control.TituloControl;
import entity.Titulo;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

public class TituloBoundary implements BoundaryContent, EventHandler<ActionEvent> {
	
	private static TextField txtId = new TextField();
	
	public static TituloControl controlTi = new TituloControl(txtId); //criação do TituloControl estatico
	
	private GridPane panGrid;
	private BorderPane painelPrincipal;
	
	private Button btnAdicionar = new Button("Adicionar");
	private Button btnPesquisar = new Button("Pesquisar");
	private Button btnExcluir = new Button("Excluir");
	private TextField txtTitulo = new TextField();
	private TextField txtAutor = new TextField();
	private TextField txtTituloAlt = new TextField();
	
	private TableView table = new TableView();
	
	public TituloBoundary() {
		painelPrincipal = new BorderPane();
		painelPrincipal.setStyle("-fx-padding: 10px");
		panGrid = new GridPane();
		
		panGrid.add(new Label("ID"), 0, 0);
		panGrid.add(txtId, 1, 0);
		txtId.setDisable(true);
		
		panGrid.add(new Label("Titulo"), 0, 1);
		panGrid.add(txtTitulo, 1, 1);
		
		panGrid.add(new Label("Titulo alternativo"), 0, 2);
		panGrid.add(txtTituloAlt, 1, 2);
		
		panGrid.add(new Label("Autor"), 0, 3);
		panGrid.add(txtAutor, 1, 3);
		
		
		panGrid.add(btnAdicionar, 0, 4);
		panGrid.add(btnPesquisar, 1, 4);
		panGrid.add(btnExcluir, 2, 4);
		panGrid.add(table,0,5);
		
		panGrid.setHgap(10);
		panGrid.setVgap(10);
		
		btnAdicionar.addEventHandler(ActionEvent.ANY, this);
		btnPesquisar.addEventHandler(ActionEvent.ANY, this);
		btnExcluir.addEventHandler(ActionEvent.ANY,this);
		
		painelPrincipal.setTop(panGrid);
		painelPrincipal.setCenter(table);
		
		
		controlTi.proximoId();
		addTableColumns();
	}
	private void addTableColumns() {
		TableColumn<Titulo, String> columnTitulo = new TableColumn<>("Titulo");
		columnTitulo.setCellValueFactory(
				new PropertyValueFactory<Titulo, String>("titulo"));
		
		TableColumn<Titulo, String> columnAutor = new TableColumn<>("Autor");
		columnAutor.setCellValueFactory(
				new PropertyValueFactory<Titulo, String>("autor"));
		
		TableColumn<Titulo, Integer> columnId = new TableColumn<>("ID");
		columnId.setCellValueFactory(
				new PropertyValueFactory<Titulo, Integer>("id"));
		
		table.getColumns().addAll(columnId,columnTitulo, columnAutor);
		table.setItems(controlTi.getLista());
		table.getSelectionModel().selectedItemProperty().addListener(
				new ChangeListener<Titulo>() {
					@Override
					public void changed(ObservableValue<? extends Titulo> observable, 
							Titulo oldValue,
							Titulo newValue) {
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
				controlTi.proximoId();
				controlTi.adicionar(boundaryParaEntidade());
			} else if (event.getTarget() == btnPesquisar) {
				String titulo = txtTitulo.getText();
				controlTi.pesquisarPorTipo(titulo);			
			} else if(event.getTarget() == btnExcluir) {
				controlTi.exclui(boundaryParaEntidade());
			}
		}
	
	//mover da entidade para a tela
	private void entidadeParaBoundary(Titulo t) {
		if (t != null) { 
			txtTitulo.setText(t.getTitulo());
			txtAutor.setText(t.getAutor());
			txtTituloAlt.setText(t.getTitulo_alt());
			controlTi.proximoId();
		}
		
	}
	
	//mover da tela para a entidade
	private Titulo boundaryParaEntidade() {
		Titulo t = new Titulo();
		try {
			t.setTitulo(txtTitulo.getText());
			t.setAutor(txtAutor.getText());
			t.setTitulo_alt(txtTituloAlt.getText());
			t.setId(Integer.parseInt(txtId.getText()));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return t;
	}
}
