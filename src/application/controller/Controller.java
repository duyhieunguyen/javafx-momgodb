package application.controller;

import java.io.File;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import application.model.Book;
import application.model.SupplierType;
import application.service.BookService;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Controller implements Initializable {

	private BookService bookService = new BookService();

	public List<Book> lisBooks = bookService.getAllBookSuppliers();

	private String id;
	@FXML
	private TableView<Book> tableView;

	@FXML
	private TableColumn<Book, String> supplierId;

	@FXML
	private TableColumn<Book, String> supplierName;

	@FXML
	private TableColumn<Book, String> books;

	@FXML
	private TableColumn<Book, String> newsPaper;

	@FXML
	private TableColumn<Book, String> journalsAndMagazines;

	@FXML
	private TableColumn<Book, String> address;

	@FXML
	private TableColumn<Book, String> contactNo;

	@FXML
	private TableColumn<Book, String> emailId;

	@FXML
	private TableColumn<Book, String> remarks;

	@FXML
	private TextField txtSupplierID;

	@FXML
	private TextField txtSupplierName;

	@FXML
	private CheckBox ckBooks;

	@FXML
	private CheckBox ckNewsPaper;

	@FXML
	private CheckBox ckJournalsAndMagazines;

	@FXML
	private TextField txtAddress;

	@FXML
	private TextField txtContactNo;

	@FXML
	private TextField txtEmailID;

	@FXML
	private TextArea txtArea;

	@FXML
	private DatePicker datePicker;

	@FXML
	private ComboBox<String> cbBox;

	@FXML
	private ImageView image;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		//GET TODAY
		LocalDate today = LocalDate.now();
		datePicker.setValue(today);
		//////////
		loadTable();
	}
	
	
	

	@FXML
	private void btnThem() {

		Book book = new Book();
		book.setSupplierId(txtSupplierID.getText());
		book.setSupplierName(txtSupplierName.getText());

		SupplierType supplierType = new SupplierType();
		if (ckBooks.isSelected()) {
			supplierType.setBooks("X");
		} else {
			supplierType.setBooks("");
		}
		if (ckNewsPaper.isSelected()) {
			supplierType.setNewspaper("X");
		} else {
			supplierType.setNewspaper("");
		}
		if (ckJournalsAndMagazines.isSelected()) {
			supplierType.setOurnalsAndMagazines("X");
		} else {
			supplierType.setOurnalsAndMagazines("");
		}

		book.setAddress(txtAddress.getText());
		book.setEmailId(txtEmailID.getText());
		book.setRemarks(txtArea.getText());
		book.setSupplierType(supplierType);

		book.setLop(cbBox.getValue());

		SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
		book.setDate(datePicker.getValue().toString());
		lisBooks.add(book);
		bookService.insertBookSupplier(book);

		loadTable();
		JOptionPane.showMessageDialog(null, "Thêm thành công !!!");

	}

	@FXML
	public void btnSua() {

		SupplierType supplierType = new SupplierType();
		if (ckBooks.isSelected()) {
			supplierType.setBooks("X");
		} else {
			supplierType.setBooks("");
		}
		if (ckNewsPaper.isSelected()) {
			supplierType.setNewspaper("X");
		} else {
			supplierType.setNewspaper("");
		}
		if (ckJournalsAndMagazines.isSelected()) {
			supplierType.setOurnalsAndMagazines("X");
		} else {
			supplierType.setOurnalsAndMagazines("");
		}

		Book book = new Book(txtSupplierID.getText(), txtSupplierName.getText(), txtAddress.getText(),
				txtContactNo.getText(), txtEmailID.getText(), txtArea.getText(), supplierType,
				datePicker.getValue().toString(), cbBox.getValue());
		int index = -1;
		for (Book b : lisBooks) {
			if (b.getSupplierId().equals(id)) {
				index = lisBooks.indexOf(b);
			}
		}
		if (index != -1) {
			lisBooks.remove(index);
			lisBooks.add(book);

		}
		System.out.println(book.getSupplierId() + id);
		bookService.updateBookSupplier(book, id);

		loadTable();
		JOptionPane.showMessageDialog(null, "Sửa thành công !!!");

	}

	@FXML
	public void btnXoa() {
		
		int input = JOptionPane.showConfirmDialog(null, "Bạn có muốn xóa không?","Xóa", JOptionPane.YES_NO_OPTION);
		if(input == 0) {
			int index = -1;
			for (Book b : lisBooks) {
				if (b.getSupplierId().equals(txtSupplierID.getText())) {
					index = lisBooks.indexOf(b);
				}
			}
			if (index != -1) {
				lisBooks.remove(index);
			}
			bookService.deleteBookSupplier(txtSupplierID.getText());
			System.out.println(input);
			loadTable();
			System.out.println(input);
		}
		
	}

	@FXML
	public void btnTaoMoi() {

		SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
		String dateString = format.format(new Date());
		txtSupplierID.setDisable(false);
		txtSupplierID.setText("");
		txtSupplierName.setText("");
		txtContactNo.setText("");
		txtAddress.setText("");
		txtEmailID.setText("");
		txtArea.setText("");
		LocalDate today = LocalDate.now();
		datePicker.setValue(today);

		if (ckBooks.isSelected()) {
			ckBooks.setSelected(false);
		}

		if (ckNewsPaper.isSelected()) {
			ckNewsPaper.setSelected(false);
		}
		if (ckJournalsAndMagazines.isSelected()) {
			ckJournalsAndMagazines.setSelected(false);
		}
	}

	@FXML
	public void showTable() {

		txtSupplierID.setDisable(true);
		Book b = tableView.getSelectionModel().getSelectedItem();

		id = b.getSupplierId();
		txtSupplierID.setText(b.getSupplierId());
		txtSupplierName.setText(b.getSupplierName());
		txtAddress.setText(b.getAddress());
		txtContactNo.setText(b.getContactNo());
		txtEmailID.setText(b.getEmailId());
		txtArea.setText(b.getRemarks());
		cbBox.setValue(b.getLop());

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate localDate = LocalDate.parse(b.getDate(), formatter);
		datePicker.setValue(localDate);

		if (b.getSupplierType().getBooks().equals("X")) {
			ckBooks.setSelected(true);
		}
		if (b.getSupplierType().getNewspaper().equals("X")) {
			ckNewsPaper.setSelected(true);
		}
		if (b.getSupplierType().getOurnalsAndMagazines().equals("X")) {
			ckJournalsAndMagazines.setSelected(true);
		}

//		ImageView img1 = new ImageView(new 
//				Image(getClass().getResourceAsStream("D:\\My Lap\\New folder\\tuan3\\BookSuppliers\\src\\application\\icon\\1.jpg")));
		File file = new File("src/application/icon/1.jpg");
		Image img1 = new Image(file.toURI().toString());
		image.setImage(img1);

	}

	public void loadTable() {

		//////////////////////
		supplierId.setCellValueFactory(new PropertyValueFactory<Book, String>("supplierId"));
		supplierName.setCellValueFactory(new PropertyValueFactory<Book, String>("supplierName"));

		books.setCellValueFactory(
				cellData -> new SimpleStringProperty(cellData.getValue().getSupplierType().getBooks()));
		newsPaper.setCellValueFactory(
				cellData -> new SimpleStringProperty(cellData.getValue().getSupplierType().getNewspaper()));
		journalsAndMagazines.setCellValueFactory(
				cellData -> new SimpleStringProperty(cellData.getValue().getSupplierType().getOurnalsAndMagazines()));

		address.setCellValueFactory(new PropertyValueFactory<Book, String>("address"));
		contactNo.setCellValueFactory(new PropertyValueFactory<Book, String>("contactNo"));
		emailId.setCellValueFactory(new PropertyValueFactory<Book, String>("emailId"));
		remarks.setCellValueFactory(new PropertyValueFactory<Book, String>("remarks"));

		ObservableList<Book> book = FXCollections.observableArrayList(lisBooks);
		tableView.setItems(book);

		//////////////////////////
		List<String> listCb = new ArrayList<String>();
		listCb.add("A");
		listCb.add("B");

		ObservableList<String> listSelectCb = FXCollections.observableArrayList(listCb);
		cbBox.setItems(listSelectCb);
		cbBox.setValue("A");
		/////////////////////////
	}

}
