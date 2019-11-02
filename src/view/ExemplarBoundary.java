package view;

import control.ExemplarControl;
import entity.Edição;
import entity.Exemplar;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

public class ExemplarBoundary implements BoundaryContent, EventHandler<ActionEvent> {
	
	private ExemplarControl controlEx = new ExemplarControl();
	
	private GridPane panGrid;
	
	private Button btnAdicionar = new Button("Adicionar");
	private Button btnPesquisar = new Button("Pesquisar");
	private Button btnExcluir = new Button("Excluir");
	
	private TextField txtNumExemplar = new TextField();
	
	ObservableList<Edição> edicoes = EdicaoBoundary.controlEd.getLista();
	private ComboBox<Edição> comboEdicao = new ComboBox<>();

	
	public ExemplarBoundary() { 
		panGrid = new GridPane();
		panGrid.add(comboEdicao, 0, 0);
		panGrid.add(new Label("Num exemplar"), 0, 1);
		panGrid.add(txtNumExemplar, 1, 1);
		
		panGrid.add(btnAdicionar, 0, 3);
		panGrid.add(btnPesquisar, 1, 3);
		panGrid.add(btnExcluir, 2, 3);
		
		comboEdicao.setEditable(false);
		comboEdicao.setItems(edicoes);
		
		
		panGrid.setHgap(10);
		panGrid.setVgap(10);
		
		btnAdicionar.addEventHandler(ActionEvent.ANY, this);
		btnPesquisar.addEventHandler(ActionEvent.ANY, this);
		
	}
	public Pane generateForm() { 
		return panGrid;
	}
	@Override
	public void handle(ActionEvent event) {
			if (event.getTarget() == btnAdicionar) {
				controlEx.adicionar(boundaryParaEntidade());
			} else if (event.getTarget() == btnPesquisar) {
				int exemplar = Integer.parseInt(txtNumExemplar.getText());
				Exemplar e = controlEx.pesquisarPorTipo(exemplar);			
				entidadeParaBoundary(e);
			}
		}
	private void entidadeParaBoundary(Exemplar e) {
		if (e != null) {
			txtNumExemplar.setText(String.valueOf(e.getExemplar()));
		}
		
	}
	private Exemplar boundaryParaEntidade() {
		Exemplar e = new Exemplar();
		try {
			e.setExemplar(Integer.parseInt(txtNumExemplar.getText()));
		} catch (Exception f) {
			f.printStackTrace();
		}
		return e;
	}
}
