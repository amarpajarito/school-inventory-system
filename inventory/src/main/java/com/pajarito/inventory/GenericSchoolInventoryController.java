package com.pajarito.inventory;
import com.pajarito.inventory.model.SchoolInventory;
import com.pajarito.inventory.model.Equipment;
import com.pajarito.inventory.service.EquipmentService;
import com.pajarito.inventory.model.Category;
import com.pajarito.inventory.service.CategoryService;
import com.pajarito.inventory.model.Condition;
import com.pajarito.inventory.service.ConditionService;
import com.pajarito.inventory.model.Manufacturer;
import com.pajarito.inventory.service.ManufacturerService;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;
import javafx.application.Platform;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.stage.Window;
import lombok.Setter;
import javafx.util.StringConverter;
import java.net.URL;
import javafx.scene.Node;
import javafx.event.ActionEvent;

import java.text.SimpleDateFormat;
import java.util.ResourceBundle;
import java.util.Locale;

public class GenericSchoolInventoryController implements Initializable{
	@Setter
	CreateSchoolInventoryController createSchoolInventoryController;

	@Setter
	DeleteSchoolInventoryController deleteSchoolInventoryController ;

	@Setter
	EditSchoolInventoryController editSchoolInventoryController;

	@Setter
	ManageSchoolInventoryController manageSchoolInventoryController;

	@Setter
	Stage stage;

	@Setter
	Scene splashScene;

	@Setter
	Scene manageScene;

	@Setter
	public ListView<SchoolInventory> lvEcommerces;

	@Setter
	public static SchoolInventory selectedItem;
	public TextField txtId;
	public TextField txtName;
	public TextField txtDescription;
	public ComboBox<Equipment> cmbEquipment;
	public TextField txtEquipmentName;
	public ComboBox<Category> cmbCategory;
	public TextField txtCategoryName;
	public ComboBox<Manufacturer> cmbManufacturer;
	public TextField txtManufacturerName;
	public TextField txtQuantityAvailable;
	public ComboBox<Condition> cmbCondition;
	public TextField txtConditionName;
	public TextField txtSerialNumber;

	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) {
		Equipment[] equipments =  (Equipment[]) EquipmentService.getService().getAll();
		cmbEquipment.getItems().addAll(equipments);
		StringConverter<Equipment> equipmentConverter = new StringConverter<Equipment>() {
			@Override
			public String toString(Equipment equipment) {
			if(equipment==null)
				return "";
			else
				return equipment.toString();
			}
			@Override
			public Equipment fromString(String s) {
				if(!s.isEmpty()){
					for (Equipment equipment : equipments) {
						if (s.equals(equipment.getName())){
							return equipment;
						}
					}
				}
				return null;
			}
		};
		cmbEquipment.setConverter(equipmentConverter);
		Category[] categorys =  (Category[]) CategoryService.getService().getAll();
		cmbCategory.getItems().addAll(categorys);
		StringConverter<Category> categoryConverter = new StringConverter<Category>() {
			@Override
			public String toString(Category category) {
			if(category==null)
				return "";
			else
				return category.toString();
			}
			@Override
			public Category fromString(String s) {
				if(!s.isEmpty()){
					for (Category category : categorys) {
						if (s.equals(category.getName())){
							return category;
						}
					}
				}
				return null;
			}
		};
		cmbCategory.setConverter(categoryConverter);
		Condition[] conditions =  (Condition[]) ConditionService.getService().getAll();
		cmbCondition.getItems().addAll(conditions);
		StringConverter<Condition> conditionConverter = new StringConverter<Condition>() {
			@Override
			public String toString(Condition condition) {
			if(condition==null)
				return "";
			else
				return condition.toString();
			}
			@Override
			public Condition fromString(String s) {
				if(!s.isEmpty()){
					for (Condition condition : conditions) {
						if (s.equals(condition.getName())){
							return condition;
						}
					}
				}
				return null;
			}
		};
		cmbCondition.setConverter(conditionConverter);
		Manufacturer[] manufacturers =  (Manufacturer[]) ManufacturerService.getService().getAll();
		cmbManufacturer.getItems().addAll(manufacturers);
		StringConverter<Manufacturer> inventoryConverter = new StringConverter<Manufacturer>() {
			@Override
			public String toString(Manufacturer inventory) {
			if(inventory==null)
				return "";
			else
				return inventory.toString();
			}
			@Override
			public Manufacturer fromString(String s) {
				if(!s.isEmpty()){
					for (Manufacturer inventory : manufacturers) {
						if (s.equals(inventory.getName())){
							return inventory;
						}
					}
				}
				return null;
			}
		};
		cmbManufacturer.setConverter(inventoryConverter);
		init();
	}
	protected void init(){
		System.out.println("Invoked from Generic Controller");
	}
	protected SchoolInventory toObject(boolean isEdit){
		SchoolInventory schoolInventory= new SchoolInventory();
		try {
			if(isEdit) {
				schoolInventory.setId(Integer.parseInt(txtId.getText()));
			}
			schoolInventory.setName(txtName.getText());
			schoolInventory.setDescription(txtDescription.getText());
			Equipment equipment = cmbEquipment.getSelectionModel().getSelectedItem();
			schoolInventory.setEquipmentId(equipment.getId());
			schoolInventory.setEquipmentName(equipment.getName());
			Category category = cmbCategory.getSelectionModel().getSelectedItem();
			schoolInventory.setCategoryId(category.getId());
			schoolInventory.setCategoryName(category.getName());
			Condition condition = cmbCondition.getSelectionModel().getSelectedItem();
			schoolInventory.setConditionId(condition.getId());
			schoolInventory.setConditionName(condition.getName());
			schoolInventory.setQuantityAvailable(Double.parseDouble(txtQuantityAvailable.getText()));
			Manufacturer manufacturer = cmbManufacturer.getSelectionModel().getSelectedItem();
			schoolInventory.setManufacturerId(manufacturer.getId());
			schoolInventory.setManufacturerName(manufacturer.getName());
			schoolInventory.setSerialNumber(txtSerialNumber.getText());
		}catch (Exception e){
			showErrorDialog("Error" ,e.getMessage());
		}
		return schoolInventory;
	}
	protected void setFields(String action){
		String formattedDate;
		SchoolInventory schoolInventory = GenericSchoolInventoryController.selectedItem;
		SimpleDateFormat formatter = new SimpleDateFormat("mm/dd/yyyy", Locale.ENGLISH);
		txtId.setText(Integer.toString(schoolInventory.getId()));
		txtName.setText(schoolInventory.getName());
		txtDescription.setText(schoolInventory.getDescription());
		Equipment equipment = EquipmentService.getService().get(schoolInventory.getEquipmentId());
		cmbEquipment.getSelectionModel().select(equipment);
		if(action.equals("Create") || action.equals("Edit")){
			cmbEquipment.setVisible(true);
			txtEquipmentName.setVisible(false);
			cmbEquipment.getSelectionModel().select(equipment);
		}
		else{
			cmbEquipment.setVisible(false);
			txtEquipmentName.setVisible(true);
			txtEquipmentName.setText(equipment.getName());
		}
		txtEquipmentName.setText(schoolInventory.getEquipmentName());
		Category category = CategoryService.getService().get(schoolInventory.getCategoryId());
		cmbCategory.getSelectionModel().select(category);
		if(action.equals("Create") || action.equals("Edit")){
			cmbCategory.setVisible(true);
			txtCategoryName.setVisible(false);
			cmbCategory.getSelectionModel().select(category);
		}
		else{
			cmbCategory.setVisible(false);
			txtCategoryName.setVisible(true);
			txtCategoryName.setText(category.getName());
		}
		txtCategoryName.setText(schoolInventory.getCategoryName());
		Condition status = ConditionService.getService().get(schoolInventory.getConditionId());
		cmbCondition.getSelectionModel().select(status);
		if(action.equals("Create") || action.equals("Edit")){
			cmbCondition.setVisible(true);
			txtConditionName.setVisible(false);
			cmbCondition.getSelectionModel().select(status);
		}
		else{
			cmbCondition.setVisible(false);
			txtConditionName.setVisible(true);
			txtConditionName.setText(status.getName());
		}
		txtConditionName.setText(schoolInventory.getConditionName());
		txtQuantityAvailable.setText(Double.toString(schoolInventory.getQuantityAvailable()));
		Manufacturer manufacturer = ManufacturerService.getService().get(schoolInventory.getManufacturerId());
		cmbManufacturer.getSelectionModel().select(manufacturer);
		if(action.equals("Create") || action.equals("Edit")){
			cmbManufacturer.setVisible(true);
			txtManufacturerName.setVisible(false);
			cmbManufacturer.getSelectionModel().select(manufacturer);
		}
		else{
			cmbManufacturer.setVisible(false);
			txtManufacturerName.setVisible(true);
			txtManufacturerName.setText(manufacturer.getName());
		}
		txtManufacturerName.setText(schoolInventory.getManufacturerName());
		txtSerialNumber.setText(schoolInventory.getSerialNumber());
	}

	protected void clearFields(String action){
		txtId.setText("");
		//txtName.setText("");
		//txtDescription.setText("");
		cmbEquipment.getSelectionModel().clearSelection();
		txtEquipmentName.setText("");
		if(action.equals("Create") || action.equals("Edit")){
			cmbEquipment.setVisible(true);
			txtEquipmentName.setVisible(false);
		}
		else{
			cmbEquipment.setVisible(false);
			txtEquipmentName.setVisible(true);
		}
		//txtProductName.setText("");
		cmbCategory.getSelectionModel().clearSelection();
		txtCategoryName.setText("");
		if(action.equals("Create") || action.equals("Edit")){
			cmbCategory.setVisible(true);
			txtCategoryName.setVisible(false);
		}
		else{
			cmbCategory.setVisible(false);
			txtCategoryName.setVisible(true);
		}
		//txtCategoryName.setText("");
		cmbCondition.getSelectionModel().clearSelection();
		txtConditionName.setText("");
		if(action.equals("Create") || action.equals("Edit")){
			cmbCondition.setVisible(true);
			txtConditionName.setVisible(false);
		}
		else{
			cmbCondition.setVisible(false);
			txtConditionName.setVisible(true);
		}
		//txtStatusName.setText("");
		//txtQuantityAvailable.setText("");
		cmbManufacturer.getSelectionModel().clearSelection();
		txtManufacturerName.setText("");
		if(action.equals("Create") || action.equals("Edit")){
			cmbManufacturer.setVisible(true);
			txtManufacturerName.setVisible(false);
		}
		else{
			cmbManufacturer.setVisible(false);
			txtManufacturerName.setVisible(true);
		}
		//txtInventoryName.setText("");
		//txtPrice.setText("");
	}

	protected void enableFields(boolean enable){
		txtName.editableProperty().set(enable);
		txtDescription.editableProperty().set(enable);
		cmbEquipment.editableProperty().set(enable);
		txtEquipmentName.editableProperty().set(enable);
		txtEquipmentName.editableProperty().set(enable);
		cmbCategory.editableProperty().set(enable);
		txtCategoryName.editableProperty().set(enable);
		txtCategoryName.editableProperty().set(enable);
		cmbCondition.editableProperty().set(enable);
		txtConditionName.editableProperty().set(enable);
		txtConditionName.editableProperty().set(enable);
		txtQuantityAvailable.editableProperty().set(enable);
		cmbManufacturer.editableProperty().set(enable);
		txtManufacturerName.editableProperty().set(enable);
		txtManufacturerName.editableProperty().set(enable);
		txtSerialNumber.editableProperty().set(enable);
	}

	public int getId(){
		return Integer.parseInt(txtId.getText());
	}

	protected void showErrorDialog(String message, String expandedMessage){
		Alert alert = new Alert(Alert.AlertType.ERROR);
		alert.setHeaderText(message);
		alert.getDialogPane().setExpandableContent(new ScrollPane(new TextArea(expandedMessage)));
		alert.showAndWait();
	}
	public void onBack(ActionEvent actionEvent) {
		Node node = ((Node) (actionEvent.getSource()));
		Window window = node.getScene().getWindow();
		window.hide();
		stage.setScene(manageScene);
		stage.show();
	}
	public void onClose(ActionEvent actionEvent) {
		Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Exit and loose changes? " , ButtonType.YES, ButtonType.NO, ButtonType.CANCEL);
		alert.showAndWait();
		if (alert.getResult() == ButtonType.YES) {
			Platform.exit();
		}
	}
	LocalDate toLocalDate(Date date){
		Instant instant = date.toInstant();
		ZoneId z = ZoneId.of("Singapore");
		ZonedDateTime zdt = instant.atZone( z );
		return zdt.toLocalDate();
	}
	protected Date toDate(LocalDate ld){
		ZoneId z = ZoneId.of("Singapore");
		ZonedDateTime zdt = ld.atStartOfDay(z);
		Instant instant  = zdt.toInstant();
		return Date.from(instant);
	}
}

