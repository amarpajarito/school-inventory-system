package com.pajarito.inventory;
import com.pajarito.inventory.model.SchoolInventory;
import com.pajarito.inventory.service.SchoolInventoryService;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.stage.Window;
import javafx.scene.image.ImageView;

public class CreateSchoolInventoryController extends GenericSchoolInventoryController {
	public ImageView imgEcommerce;
	@Override
	public void init() {
		clearFields("Edit");
		enableFields(true);
	}
	public void onSubmit(ActionEvent actionEvent) {
		try {
			SchoolInventory schoolInventory = toObject(false);
			SchoolInventory newSchoolInventory = SchoolInventoryService.getService().create(schoolInventory);
			manageSchoolInventoryController.refresh();
			Node node = ((Node) (actionEvent.getSource()));
			Window window = node.getScene().getWindow();
			window.hide();
			stage.setTitle("Manage School Inventory");
			stage.setScene(manageScene);
			stage.show();
		}
		catch (Exception e){
			showErrorDialog("Error encountered creating school inventory", e.getMessage());
		}
	}
	public void onClose(ActionEvent actionEvent) {
		super.onClose(actionEvent);
	}
}
