package view;

import java.text.DecimalFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JOptionPane;

import com.sun.javafx.fxml.BeanAdapter;

import control.EdicaoControl;
import control.ExemplarControl;
import control.VendaControl;
import entity.Cliente;
import entity.Edicao;
import entity.Exemplar;
import entity.Pagamento;
import entity.Titulo;
import entity.Venda;
import javafx.beans.InvalidationListener;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
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
import javafx.scene.control.TextField;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.util.Callback;

public class VendaBoundery  implements BoundaryContent, EventHandler<ActionEvent>{
	private GridPane panGridTop;
	private BorderPane painelPrincipal;
	private GridPane panGridBotton;
	
	private VendaControl controlV = new VendaControl();
	
	private Button btnAdd = new Button("Adicionar");
	private Button btnFinalizarPedido = new Button("Finalizar Pedido");
	private Button btnRemover = new Button("Remover Item");
	
	ObservableList<Titulo>titulos = TituloBoundary.controlTi.getLista();
	ObservableList<Edicao> edicoes  = FXCollections.observableArrayList();
	ObservableList<Exemplar> exemplaresCombo= FXCollections.observableArrayList();
	ObservableList<Cliente> clientes= FXCollections.observableArrayList();
	ObservableList<Exemplar> exemplaresVenda= FXCollections.observableArrayList();
	ObservableList<String> tiposPagamento= FXCollections.observableArrayList("Dinheiro","Cartão");
	
	private ComboBox<Titulo> comboTitulo = new ComboBox<>();
	private ComboBox<Edicao> comboEdicao = new ComboBox<>();
	private ComboBox<Exemplar>comboExemplar =  new ComboBox<>();
	private ComboBox<Cliente> comboCliente = new ComboBox<>();
	private ComboBox<String> comboTipoPagamento = new ComboBox<>();
	
	private Double valorTotal =0.0;
	private Double valorFinal=0.0;

	private Pagamento pgto = new Pagamento();
	
	private Label lblValorTotal = new Label(valorTotal+"");
	private Label lblDesconto = new Label(pgto.getDesconto()+"%");
	private Label lblValorFinal= new Label(valorFinal+"");
	
	private Venda venda = new Venda();
	
	
	private TableView table = new TableView();
	
	public VendaBoundery() {
		venda.setPagamento(pgto);
		
		panGridTop = new GridPane();
		panGridBotton=  new GridPane();
		painelPrincipal = new BorderPane();
		
		painelPrincipal.setStyle("-fx-padding: 10px");
		panGridBotton.setStyle("-fx-padding: 10px");
		panGridTop.setStyle("-fx-padding: 10px");
		
		panGridTop.add(new Label("Titulo"), 0, 0);
		panGridTop.add(comboTitulo, 1,0 );
		
		panGridTop.add(new Label("Edição"), 2, 0);
		panGridTop.add(comboEdicao, 3,0 );
		
		panGridTop.add(new Label("Exemplar"), 4, 0);
		panGridTop.add(comboExemplar,5,0);
		
		panGridTop.add(btnAdd, 6, 0);
		
		
		panGridTop.add(new Label("Cliente"), 0, 1);
		panGridTop.add(comboCliente, 1, 1);
		
		panGridTop.add(new Label("Forma Pagt"), 5, 1);
		panGridTop.add(comboTipoPagamento, 6, 1);
		
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
		
		comboCliente.setOnAction((e)->{
			venda.setCliente(comboCliente.getValue());
			updateDesconto();
		});
		
		comboTipoPagamento.setItems(tiposPagamento);
		comboTipoPagamento.setEditable(false);
		
		comboTipoPagamento.setOnAction((e)->{
			pgto.setTipoPagamento(comboTipoPagamento.getValue());
			updateDesconto();
		});
		
		panGridTop.setHgap(10);
		panGridTop.setVgap(10);
		
		
		//Grid Botton
		
		panGridBotton.add(btnRemover, 0, 0);
		panGridBotton.add(new Label("Valor : "), 15, 0);
		panGridBotton.add(lblValorTotal, 16, 0);
		panGridBotton.add(new Label("Desconto : "), 0, 1);
		panGridBotton.add(lblDesconto, 1, 1);
		panGridBotton.add(new Label("Valor Total : "), 39, 1);
		panGridBotton.add(lblValorFinal, 40, 1);
		panGridBotton.add(btnFinalizarPedido, 39, 2);
		

		btnRemover.addEventHandler(ActionEvent.ANY, this);
		btnFinalizarPedido.addEventHandler(ActionEvent.ANY, this);
		
		panGridBotton.setHgap(10);
		panGridBotton.setVgap(10);
		
	
		painelPrincipal.setTop(panGridTop);
		painelPrincipal.setCenter(table);
		painelPrincipal.setBottom(panGridBotton);
		
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
			updateValor();
		}
		if(event.getTarget()==btnRemover) {
			exemplaresVenda.remove(table.getSelectionModel().getSelectedItem());
			venda.exemplarSelecionados.remove(table.getSelectionModel().getSelectedItem());
			updateValor();
		}
		if(event.getTarget()==btnFinalizarPedido) {
			if(venda.exemplarSelecionados.isEmpty()) {
				JOptionPane.showMessageDialog(null, "Adicione Algum intem na venda");
			}else {
				venda.setFuncionario(controlV.getFunc());
				controlV.finalizarVenda(venda);
			}
		}
		
	}

	@Override
	public Pane generateForm() {
		return painelPrincipal;
	}
	
	private void updateValor() {
		double v =0;
		for (Exemplar e : venda.exemplarSelecionados) {
			v+=e.getValorVenda();
		}
		valorTotal=converterDoubleDoisDecimais(v);
		lblValorTotal.setText(valorTotal+"");
		updateValorFinal();
	}
	
	private void updateDesconto() {
		int d=0;
		if(venda.getCliente()!=null) {
			d+=5;
			Date data= new Date();
			if(venda.getCliente().getdataNascimento().getMonth()==data.getMonth()) {
				d+=10;
			}
		}
		if(comboTipoPagamento.getValue()=="Dinheiro") {
			d+=5;
		}
		pgto.setDesconto(d);
		lblDesconto.setText(pgto.getDesconto()+"%");
		updateValorFinal();
	}
	
	private void updateValorFinal() {
		double vf=0;
		vf=valorTotal-((valorTotal*pgto.getDesconto())/100);
		valorFinal=converterDoubleDoisDecimais(vf);
		lblValorFinal.setText(valorFinal+"");
	}
	
	public static double converterDoubleDoisDecimais(double precoDouble) {
	    DecimalFormat fmt = new DecimalFormat("0.00");      
	    String string = fmt.format(precoDouble);
	    String[] part = string.split("[,]");
	    String string2 = part[0]+"."+part[1];
	        double preco = Double.parseDouble(string2);
	    return preco;
	}

}
