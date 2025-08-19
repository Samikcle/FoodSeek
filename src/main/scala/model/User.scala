package model

import controller.LayoutController
import javafx.fxml.FXMLLoader
import javafx.scene.Parent

class User (
             _userID: Int,
             _username: String,
             _password: String,
             _name: String,
             _phoneNo: String,
             _accountType: String
           ) extends Account(_userID, _username, _password, _name, _phoneNo, _accountType) {

  override def setupLayout(): Parent = {
    val loader = new FXMLLoader(getClass.getResource("/view/Layout.fxml"))
    val root = loader.load[javafx.scene.Parent]()
    val controller = loader.getController[LayoutController]
    controller.disableAdminMenus()
    root
  }

}
