package com.pajarito.inventory;
import com.pajarito.inventory.model.SchoolInventory;
import com.pajarito.inventory.service.SchoolInventoryService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.image.ImageView;
import javafx.scene.Scene;
import javafx.scene.Node;
import javafx.stage.Window;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import lombok.Setter;

public class ManageSchoolInventoryController extends GenericSchoolInventoryController {
	@Setter
	Stage stage;

	@Setter
	Scene createViewScene;

	@Setter
	Scene editViewScene;

	@Setter
	Scene deleteViewScene;

	public ImageView ecommerceImage;
	@FXML
	public Button btnCreate;

	@FXML
	public Button btnEdit;

	@FXML
	public Button btnDelete;

	@FXML
	public Button btnClose;

	@FXML
	public Button imageButton;
	SchoolInventory selectedItem;

	@FXML
	private ListView<SchoolInventory> lvSchoolInventories;

		public void refresh() {
			SchoolInventory[] schoolInventories = SchoolInventoryService.getService().getAll();
			lvSchoolInventories.getItems().clear();
			lvSchoolInventories.getItems().addAll(schoolInventories);
			enableFields(false);
		}

	@Override
	public void init() {
		try {
			refresh();
		}
		catch (Exception e){
			showErrorDialog("Message: ", e.getMessage());
		}
	}

	public void onAction(MouseEvent mouseEvent) {
		GenericSchoolInventoryController.selectedItem = lvSchoolInventories.getSelectionModel().getSelectedItem();
		if(GenericSchoolInventoryController.selectedItem == null) {
			return;
		}
		setFields("Manage");
	}
	public void onCreate(ActionEvent actionEvent)  throws Exception {
		Node node = ((Node) (actionEvent.getSource()));
		Scene currentScene = node.getScene();
		Window window = currentScene.getWindow();
		window.hide();
		if(createViewScene == null){
			FXMLLoader fxmlLoader = new FXMLLoader(ManageSchoolInventoryJFXApp.class.getResource("create-schoolInventory-view.fxml"));
			Parent root = fxmlLoader.load();
			CreateSchoolInventoryController controller = fxmlLoader.getController();
			controller.setStage(stage);
			createViewScene = new Scene(root, 440, 540);
			controller.setManageSchoolInventoryController(this);
			controller.setManageScene(manageScene);
			controller.setSplashScene(splashScene);
		}
		stage.setTitle("Create School Inventory");
		stage.setScene(createViewScene);
		stage.show();
	}
	public void onEdit(ActionEvent actionEvent)  throws Exception {
		if(GenericSchoolInventoryController.selectedItem == null){
			showErrorDialog("Please select a school inventory from the list", "Cannot edit");
		return;
		}
		Node node = ((Node) (actionEvent.getSource()));
		Scene currentScene = node.getScene();
		Window window = currentScene.getWindow();
		window.hide();
		if(editViewScene == null){
			FXMLLoader fxmlLoader = new FXMLLoader(ManageSchoolInventoryJFXApp.class.getResource("edit-schoolInventory-view.fxml"));
			Parent root = fxmlLoader.load();
			EditSchoolInventoryController controller = fxmlLoader.getController();
			controller.setStage(stage);
			editViewScene = new Scene(root, 440, 540);
			controller.setManageSchoolInventoryController(this);
			controller.setManageScene(manageScene);
			controller.setSplashScene(splashScene);
		}
		stage.setTitle("Edit School Inventory");
		stage.setScene(editViewScene);
		stage.show();
	}
	public void onDelete(ActionEvent actionEvent)  throws Exception {
		if(GenericSchoolInventoryController.selectedItem == null){
			showErrorDialog("Please select a school inventory from the list", "Cannot delete");
		return;
		}
		Node node = ((Node) (actionEvent.getSource()));
		Scene currentScene = node.getScene();
		Window window = currentScene.getWindow();
		window.hide();
		if(deleteViewScene == null){
			FXMLLoader fxmlLoader = new FXMLLoader(ManageSchoolInventoryJFXApp.class.getResource("delete-schoolInventory-view.fxml"));
			Parent root = fxmlLoader.load();
			DeleteSchoolInventoryController controller = fxmlLoader.getController();
			controller.setStage(stage);
			deleteViewScene = new Scene(root, 440, 540);
			controller.setManageSchoolInventoryController(this);
			controller.setManageScene(manageScene);
			controller.setSplashScene(splashScene);
		}
		stage.setTitle("Delete School Inventory");
		stage.setScene(deleteViewScene);
		stage.show();
	}
	public void onClose(ActionEvent actionEvent) {
		super.onClose(actionEvent);
	}
}
