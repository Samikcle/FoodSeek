package controller

import javafx.fxml.{FXML, FXMLLoader}
import javafx.scene.Node
import javafx.scene.layout.Pane

class LayoutController {
  @FXML private var Content: Pane = _

  @FXML private var FoodBankMenu: Pane = _
  @FXML private var GiveawayMenu: Pane = _
  @FXML private var DiscountMenu: Pane = _
  @FXML private var ManageMenu: Pane = _
  @FXML private var RequestMenu: Pane = _
  @FXML private var Profile: Pane = _

  @FXML
  def initialize(): Unit = {
    loadPage("/view/Foodbanks.fxml")

    FoodBankMenu.setOnMouseClicked(_ => loadPage("/view/Foodbanks.fxml"))
    GiveawayMenu.setOnMouseClicked(_ => loadPage("/view/Giveaway.fxml"))
    DiscountMenu.setOnMouseClicked(_ => loadPage("/view/Discount.fxml"))
    ManageMenu.setOnMouseClicked(_ => loadPage("/view/Manage.fxml"))
    RequestMenu.setOnMouseClicked(_ => loadPage("/view/Requests.fxml"))
    Profile.setOnMouseClicked(_ => loadPage("/view/Profile.fxml"))
  }

  private def loadPage(fxmlPath: String): Unit = {
    val loader = new FXMLLoader(getClass.getResource(fxmlPath))
    val content: Node = loader.load()
    Content.getChildren.clear()
    Content.getChildren.add(content)
  }
}
