package view;

import entity.Edição;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

public class ExemplarBoundary implements BoundaryContent {
	private GridPane panGrid;
	public ExemplarBoundary() { 
		panGrid = new GridPane();
		panGrid.add(new ComboBox<Edição>(), 0, 0);
		panGrid.add(new Label("Num Exemplar"), 0, 1);
		panGrid.add(new TextField(), 1, 1);
		panGrid.add(new Button("Adicionar"), 0, 3);
		panGrid.add(new Button("Pesquisar"), 1, 3);
		panGrid.add(new Button("Excluir"), 2, 3);
		panGrid.setHgap(10);
		panGrid.setVgap(10);
	}
	public Pane generateForm() { 
		return panGrid;
	}
}
