package model

import controller.LayoutController
import javafx.fxml.FXMLLoader
import javafx.scene.Parent
import main.MyApp

class Owner(
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

    getOwnedFoodbank() match {
      case Some(fb) =>
        MyApp.setOwnedFoodbank(fb)
      case None =>
        println(s"No foodbank found for owner with userID = $userID")
    }

    root
  }

  def getOwnedFoodbank(): Option[Foodbank] = {
    MyApp.foodbanks.find(_.owner == userID)
  }
}
