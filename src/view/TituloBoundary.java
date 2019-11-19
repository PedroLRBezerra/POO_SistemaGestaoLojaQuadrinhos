package view;


import control.TituloControl;
import entity.Titulo;
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
	public static TituloControl controlTi = new TituloControl(); //criação do TituloControl estatico
	
	private GridPane panGrid;
	private BorderPane painelPrincipal;
	
	private Button btnAdicionar = new Button("Adicionar");
	private Button btnPesquisar = new Button("Pesquisar");
	private Button btnExcluir = new Button("Excluir");
	private TextField txtTitulo = new TextField();
	private TextField txtAutor = new TextField();
	
	private TableView table = new TableView();
	
	public TituloBoundary() {
		painelPrincipal = new BorderPane();
		painelPrincipal.setStyle("-fx-padding: 10px");
		panGrid = new GridPane();
		
		panGrid.add(new Label("Titulo"), 0, 0);
		panGrid.add(txtTitulo, 1, 0);
		
		panGrid.add(new Label("Autor"), 0, 1);
		panGrid.add(txtAutor, 1, 1);
		
		panGrid.add(btnAdicionar, 0, 3);
		panGrid.add(btnPesquisar, 1, 3);
		panGrid.add(btnExcluir, 2, 3);
		panGrid.add(table,0,4);
		
		panGrid.setHgap(10);
		panGrid.setVgap(10);
		
		btnAdicionar.addEventHandler(ActionEvent.ANY, this);
		btnPesquisar.addEventHandler(ActionEvent.ANY, this);
		
		painelPrincipal.setTop(panGrid);
		painelPrincipal.setCenter(table);
		
		addTableColumns();
		
	}
	private void addTableColumns() {
		TableColumn<Titulo, String> columnTitulo = new TableColumn<>("Titulo");
		columnTitulo.setCellValueFactory(
				new PropertyValueFactory<Titulo, String>("titulo"));
		
		TableColumn<Titulo, String> columnAutor = new TableColumn<>("Autor");
		columnAutor.setCellValueFactory(
				new PropertyValueFactory<Titulo, String>("autor"));
		
		table.getColumns().addAll(columnTitulo, columnAutor);
		table.setItems(controlTi.getLista());
		
	}
	public Pane generateForm() { 
		return painelPrincipal;
	}
	@Override
	public void handle(ActionEvent event) {
			if (event.getTarget() == btnAdicionar) {
				controlTi.adicionar(boundaryParaEntidade());
			} else if (event.getTarget() == btnPesquisar) {
				String titulo = txtTitulo.getText();
				controlTi.pesquisarPorTipo(titulo);			
			//	entidadeParaBoundary(t);
			}
		}
	//mover da entidade para a tela
	private void entidadeParaBoundary(Titulo t) {
		if (t != null) { 
			txtTitulo.setText(t.getTitulo());
			txtAutor.setText(t.getAutor());
		}
		
	}
	
	//mover da tela para a entidade
	private Titulo boundaryParaEntidade() {
		Titulo t = new Titulo();
		try {
			t.setTitulo(txtTitulo.getText());
			t.setAutor(txtAutor.getText());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return t;
	}
}
